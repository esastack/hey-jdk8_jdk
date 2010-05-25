/*
 * Copyright (c) 2008, Oracle and/or its affiliates. All rights reserved.
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
 * @summary Tests <float> element
 * @author Sergey Malenkov
 */

import java.beans.XMLDecoder;

public final class TestFloat extends AbstractTest {
    public static final String XML
            = "<java>\n"
            + " <float>0</float>\n"
            + " <float>100</float>\n"
            + " <float>-1e15</float>\n"
            + " <float>100e-20</float>\n"
            + "</java>";

    public static void main(String[] args) {
        new TestFloat().test(true);
    }

    @Override
    protected void validate(XMLDecoder decoder) {
        validate(0.0f, decoder.readObject());
        validate(100.0f, decoder.readObject());
        validate(-1e15f, decoder.readObject());
        validate(100e-20f, decoder.readObject());
    }

    private static void validate(float value, Object object) {
        if (!object.equals(Float.valueOf(value))) {
            throw new Error("float " + value + " expected");
        }
    }
}
