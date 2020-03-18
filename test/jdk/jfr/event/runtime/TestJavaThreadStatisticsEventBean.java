/*
 * Copyright (c) 2013, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package jdk.jfr.event.runtime;

import static jdk.testlibrary.Asserts.assertTrue;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.time.Duration;
import java.util.List;

import jdk.jfr.Recording;
import jdk.jfr.consumer.RecordedEvent;
import jdk.testlibrary.jfr.EventNames;
import jdk.testlibrary.jfr.Events;

/**
 * @test
 * @requires vm.hasJFR
 * @library /lib/testlibrary
 *
 * @run main/othervm jdk.jfr.event.runtime.TestJavaThreadStatisticsEventBean
 */
public class TestJavaThreadStatisticsEventBean {
    private final static String EVENT_NAME = EventNames.JavaThreadStatistics;

    // Compare JFR thread counts to ThreadMXBean counts
    public static void main(String[] args) throws Throwable {
        long mxDaemonCount = -1;
        long mxActiveCount = -1;

        // Loop until we are sure no threads were started during the recording.
        Recording recording = null;
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        long totalCountBefore = -1;
        while (totalCountBefore != mxBean.getTotalStartedThreadCount()) {
            recording = new Recording();
            recording.enable(EVENT_NAME).withThreshold(Duration.ofMillis(10));
            totalCountBefore = mxBean.getTotalStartedThreadCount();
            recording.start();
            mxDaemonCount = mxBean.getDaemonThreadCount();
            mxActiveCount = mxBean.getThreadCount();
            recording.stop();
            final String msg = "testCountByMXBean: threadsBefore=%d, threadsAfter=%d%n";
            System.out.format(msg, totalCountBefore, mxBean.getTotalStartedThreadCount());
        }

        List<RecordedEvent> events= Events.fromRecording(recording);
        boolean isAnyFound = false;
        for (RecordedEvent event : events) {
            System.out.println("Event:" + event);
            isAnyFound = true;
            Events.assertField(event, "daemonCount").equal(mxDaemonCount);
            Events.assertField(event, "activeCount").equal(mxActiveCount);
            Events.assertField(event, "accumulatedCount").atLeast(mxActiveCount);
            Events.assertField(event, "peakCount").atLeast(mxActiveCount);
        }
        assertTrue(isAnyFound, "Correct event not found");
    }

}
