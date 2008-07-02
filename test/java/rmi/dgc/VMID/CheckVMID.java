/*
 * Copyright 1999-2008 Sun Microsystems, Inc.  All Rights Reserved.
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
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

/* @test
 * @bug 4171370
 * @summary VMIDs should be unique regardless of whether
 * an IP address can be obtained.  Instead of using an IP
 * address in a VMID, a secure hash (using SHA) of the IP
 * address is used.
 * @author Ann Wollrath
 *
 * @library ../../testlibrary
 * @build CheckVMID
 * @run main/othervm/policy=security.policy CheckVMID
 */

import java.rmi.dgc.VMID;
import java.net.InetAddress;

public class CheckVMID {

    public static void main(String[] args) {

        System.err.println("\nRegression test for bug 4171370\n");

        TestLibrary.suggestSecurityManager(null);

        try {
            System.err.println("Create a VMID");
            VMID vmid = new VMID();
            System.err.println("vmid = " + vmid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("TEST FAILED: " + e.toString());
        }
    }
}
