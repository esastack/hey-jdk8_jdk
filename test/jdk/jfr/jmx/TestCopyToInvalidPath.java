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

package jdk.jfr.jmx;

import java.io.IOException;
import java.nio.file.Paths;

import jdk.management.jfr.FlightRecorderMXBean;
import jdk.testlibrary.jfr.CommonHelper;
import jdk.testlibrary.jfr.SimpleEventHelper;

/**
 * @test
 * @library /lib/testlibrary
 * @run main/othervm jdk.jfr.jmx.TestCopyToInvalidPath
 */
public class TestCopyToInvalidPath {
    public static void main(String[] args) throws Throwable {
        FlightRecorderMXBean bean = JmxHelper.getFlighteRecorderMXBean();

        long recId = bean.newRecording();
        bean.startRecording(recId);
        SimpleEventHelper.createEvent(1);
        bean.stopRecording(recId);

        CommonHelper.verifyException(()->{bean.copyTo(recId, null);}, "copyTo(null)", NullPointerException.class);
        CommonHelper.verifyException(()->{bean.copyTo(recId, "");}, "copyTo('')", IOException.class);

        String p = Paths.get(".", "thisdir", "doesnot", "exists").toString();
        CommonHelper.verifyException(()->{bean.copyTo(recId, p);}, "copyTo(dirNotExist)", IOException.class);

        bean.closeRecording(recId);
    }
}
