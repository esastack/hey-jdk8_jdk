/*
 * Copyright (c) 1997, 2011, Oracle and/or its affiliates. All rights reserved.
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


package javax.security.cert;

/**
 * Certificate Expired Exception. This is thrown whenever the current
 * <code>Date</code> or the specified <code>Date</code> is after the
 * <code>notAfter</code> date/time specified in the validity period
 * of the certificate.
 *
 * <p><em>Note: The classes in the package <code>javax.security.cert</code>
 * exist for compatibility with earlier versions of the
 * Java Secure Sockets Extension (JSSE). New applications should instead
 * use the standard Java SE certificate classes located in
 * <code>java.security.cert</code>.</em></p>
 *
 * @since 1.4
 * @author Hemma Prafullchandra
 */
public class CertificateExpiredException extends CertificateException {

    private static final long serialVersionUID = 5091601212177261883L;
    /**
     * Constructs a CertificateExpiredException with no detail message. A
     * detail message is a String that describes this particular
     * exception.
     */
    public CertificateExpiredException() {
        super();
    }

    /**
     * Constructs a CertificateExpiredException with the specified detail
     * message. A detail message is a String that describes this
     * particular exception.
     *
     * @param message the detail message.
     */
    public CertificateExpiredException(String message) {
        super(message);
    }
}
