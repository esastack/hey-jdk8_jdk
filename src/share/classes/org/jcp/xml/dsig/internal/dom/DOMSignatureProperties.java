/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/*
 * Copyright (c) 2005, 2018, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: DOMSignatureProperties.java 1788465 2017-03-24 15:10:51Z coheigea $
 */
package org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;

import java.util.*;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * DOM-based implementation of SignatureProperties.
 *
 */
public final class DOMSignatureProperties extends BaseStructure
    implements SignatureProperties {

    private final String id;
    private final List<SignatureProperty> properties;

    /**
     * Creates a {@code DOMSignatureProperties} from the specified
     * parameters.
     *
     * @param properties a list of one or more {@link SignatureProperty}s. The
     *    list is defensively copied to protect against subsequent modification.
     * @param id the Id (may be {@code null})
     * @throws ClassCastException if {@code properties} contains any
     *    entries that are not of type {@link SignatureProperty}
     * @throws IllegalArgumentException if {@code properties} is empty
     * @throws NullPointerException if {@code properties}
     */
    public DOMSignatureProperties(List<DOMSignatureProperty> properties,
                                  String id)
    {
        if (properties == null) {
            throw new NullPointerException("properties cannot be null");
        } else if (properties.isEmpty()) {
            throw new IllegalArgumentException("properties cannot be empty");
        } else {
            this.properties = Collections.unmodifiableList(
                new ArrayList<>(properties));
            for (int i = 0, size = this.properties.size(); i < size; i++) {
                if (!(this.properties.get(i) instanceof SignatureProperty)) {
                    throw new ClassCastException
                        ("properties["+i+"] is not a valid type");
                }
            }
        }
        this.id = id;
    }

    /**
     * Creates a {@code DOMSignatureProperties} from an element.
     *
     * @param propsElem a SignatureProperties element
     * @throws MarshalException if a marshalling error occurs
     */
    public DOMSignatureProperties(Element propsElem)
        throws MarshalException
    {
        // unmarshal attributes
        id = DOMUtils.getIdAttributeValue(propsElem, "Id");

        List<SignatureProperty> newProperties = new ArrayList<>();
        Node firstChild = propsElem.getFirstChild();
        while (firstChild != null) {
            if (firstChild.getNodeType() == Node.ELEMENT_NODE) {
                String name = firstChild.getLocalName();
                String namespace = firstChild.getNamespaceURI();
                if (!"SignatureProperty".equals(name) || !XMLSignature.XMLNS.equals(namespace)) {
                    throw new MarshalException("Invalid element name: " + namespace + ":" + name +
                                               ", expected SignatureProperty");
                }
                newProperties.add(new DOMSignatureProperty((Element)firstChild));
            }
            firstChild = firstChild.getNextSibling();
        }
        if (newProperties.isEmpty()) {
            throw new MarshalException("properties cannot be empty");
        } else {
            this.properties = Collections.unmodifiableList(newProperties);
        }
    }

    @Override
    public List<SignatureProperty> getProperties() {
        return properties;
    }

    @Override
    public String getId() {
        return id;
    }

    public static void marshal(XmlWriter xwriter, SignatureProperties sigProps, String dsPrefix, XMLCryptoContext context)
        throws MarshalException
    {
        xwriter.writeStartElement(dsPrefix, "SignatureProperties", XMLSignature.XMLNS);

        // set attributes
        xwriter.writeIdAttribute("", "", "Id", sigProps.getId());

        // create and append any properties
        @SuppressWarnings("unchecked")
        List<SignatureProperty> properties = sigProps.getProperties();
        for (SignatureProperty property : properties) {
            DOMSignatureProperty.marshal(xwriter, property, dsPrefix, context);
        }

        xwriter.writeEndElement(); // "SignatureProperties"
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SignatureProperties)) {
            return false;
        }
        SignatureProperties osp = (SignatureProperties)o;

        boolean idsEqual = id == null ? osp.getId() == null
                                       : id.equals(osp.getId());

        return properties.equals(osp.getProperties()) && idsEqual;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hashCode();
        }
        result = 31 * result + properties.hashCode();

        return result;
    }
}
