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

package jdk.jfr.jcmd;

import java.nio.file.Path;
import java.nio.file.Paths;

import jdk.testlibrary.jfr.FileHelper;
import jdk.testlibrary.process.OutputAnalyzer;

/**
 * @test
 * @summary Verify error when stopping with read-only file.
 * @key jfr
 * @requires vm.hasJFR
 * @library /lib/testlibrary
 * @run main/othervm jdk.jfr.jcmd.TestJcmdStopReadOnlyFile
 */
public class TestJcmdStopReadOnlyFile {


    public static void main(String[] args) throws Exception {
        String name = "TestJcmdStopReadOnlyFile";
        Path readonlyFile = FileHelper.createReadOnlyFile(Paths.get(".", name + ".jfr"));
        if (!FileHelper.isReadOnlyPath(readonlyFile)) {
            System.out.println("Could not create read-only file. Ignoring test.");
            return;
        }

        OutputAnalyzer output = JcmdHelper.jcmd("JFR.start", "name=" + name);
        JcmdAsserts.assertRecordingHasStarted(output);
        JcmdHelper.waitUntilRunning(name);

        output = JcmdHelper.jcmd("JFR.stop",
                "name=" + name,
                "filename=" + readonlyFile.toAbsolutePath());
        JcmdAsserts.assertFileNotFoundException(output, name);
        JcmdHelper.assertRecordingIsRunning(name);
        JcmdHelper.stopAndCheck(name);
    }

}
