/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
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

/**
 *  This isn't the test case: WorkerDeadlockTest.sh is.
 *  Refer to WorkerDeadlockTest.sh when running this test.
 * 
 * @bug 8236872
 * @summary The test tries to catch a deadlock by creating a new worker,
 * starting it, adding an empty job and immediately stopping it.
 * @modules jdk.jconsole/sun.tools.jconsole
 */

import sun.tools.jconsole.Worker;


public class WorkerDeadlockTest {
    private static final int REPEAT_NUMBER = 1000;

    public static void main(String[] args) {

        for (int i = 1; i < REPEAT_NUMBER; i++) {
            Worker worker = new Worker("worker-" + i);
            worker.start();
            worker.add(() -> { });
            worker.stopWorker();
            System.out.println("Worker " + i + " was successfully stopped");
        }
    }
}
