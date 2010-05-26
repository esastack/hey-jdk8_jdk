/*
 * Copyright (c) 2004, Oracle and/or its affiliates. All rights reserved.
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

/*
 * @test
 * @bug 5018593
 * @summary Test that calling getMBeansFromURL(url) with a bogus URL on a
 *          given MLet instance throws a ServiceNotFoundException exception
 *          with a non null cause.
 * @author Luis-Miguel Alventosa
 * @run clean GetMBeansFromURLTest
 * @run build GetMBeansFromURLTest
 * @run main GetMBeansFromURLTest
 */

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.ServiceNotFoundException;
import javax.management.loading.MLet;

public class GetMBeansFromURLTest {

    public static void main(String[] args) throws Exception {

        boolean error = false;

        // Instantiate the MBean server
        //
        System.out.println("Create the MBean server");
        MBeanServer mbs = MBeanServerFactory.createMBeanServer();

        // Instantiate an MLet
        //
        System.out.println("Create the MLet");
        MLet mlet = new MLet();

        // Register the MLet MBean with the MBeanServer
        //
        System.out.println("Register the MLet MBean");
        ObjectName mletObjectName = new ObjectName("Test:type=MLet");
        mbs.registerMBean(mlet, mletObjectName);

        // Call getMBeansFromURL
        //
        System.out.println("Call mlet.getMBeansFromURL(<url>)");
        try {
            mlet.getMBeansFromURL("bogus://whatever");
            System.out.println("TEST FAILED: Expected " +
                               ServiceNotFoundException.class +
                               " exception not thrown.");
            error = true;
        } catch (ServiceNotFoundException e) {
            if (e.getCause() == null) {
                System.out.println("TEST FAILED: Got null cause in " +
                                   ServiceNotFoundException.class +
                                   " exception.");
                error = true;
            } else {
                System.out.println("TEST PASSED: Got non-null cause in " +
                                   ServiceNotFoundException.class +
                                   " exception.");
                error = false;
            }
            e.printStackTrace(System.out);
        }

        // Unregister the MLet MBean
        //
        System.out.println("Unregister the MLet MBean");
        mbs.unregisterMBean(mletObjectName);

        // Release MBean server
        //
        System.out.println("Release the MBean server");
        MBeanServerFactory.releaseMBeanServer(mbs);

        // End Test
        //
        System.out.println("Bye! Bye!");
        if (error) System.exit(1);
    }
}
