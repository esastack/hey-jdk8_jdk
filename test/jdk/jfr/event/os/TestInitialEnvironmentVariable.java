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

package jdk.jfr.event.os;

import static jdk.testlibrary.Asserts.assertEquals;
import static jdk.testlibrary.Asserts.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdk.jfr.Recording;
import jdk.jfr.consumer.RecordedEvent;
import jdk.testlibrary.jfr.EventNames;
import jdk.testlibrary.jfr.Events;

public class TestInitialEnvironmentVariable {
    private final static String EVENT_NAME = EventNames.InitialEnvironmentVariable;

    public static void main(String[] args) throws Exception {
        Map<String, String> env = new HashMap<>();
        env.put("keytest1", "value1");
        env.put("keytest2", "value 2");

        Recording recording = new Recording();
        recording.enable(EVENT_NAME);
        recording.start();
        recording.stop();
        List<RecordedEvent> events = Events.fromRecording(recording);
        Events.hasEvents(events);
        for (RecordedEvent event : events) {
            System.out.println("Event: " + event);
            String key = Events.assertField(event, "key").notNull().getValue();
            String value = Events.assertField(event, "value").notNull().getValue();
            if (env.containsKey(key)) {
                assertEquals(value, env.get(key), "Wrong value for key: " + key);
                env.remove(key);
            }
        }
        assertTrue(env.isEmpty(), "Missing env keys " + env.keySet());
    }
}
