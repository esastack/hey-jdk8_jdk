/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
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

import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.Signature;
import java.util.*;

/**
 * @test
 * @bug 8225180
 * @requires os.family == "windows"
 * @summary SunMSCAPI Signature should throw InvalidKeyException when
 *          initialized with a null key
 */

public class NullKey {
    public static void main(String[] args) throws Exception {
        List<String> algs = Arrays.asList(
            "SHA256withRSA", "SHA256withECDSA", "RSASSA-PSS");
        for (String alg : algs) {
            Signature sig = Signature.getInstance(alg, "SunMSCAPI");
            try {
                sig.initSign(null);
            } catch (InvalidKeyException e) {
                // Expected
            }
            try {
                sig.initVerify((PublicKey)null);
            } catch (InvalidKeyException e) {
                // Expected
            }
        }
    }
}
