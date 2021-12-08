/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.jacksonkata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.eclipsecollections.EclipseCollectionsModule;

/**
 * Utility for instantiating {@link ObjectMapper}.
 */
public class ObjectMapperUtils
{
    private ObjectMapperUtils()
    {
    }

    /**
     * Creates a new instance of {@link ObjectMapper}.
     * @return {@link ObjectMapper}
     */
    public static ObjectMapper createSimpleObjectMapper()
    {
        return new ObjectMapper();
    }

    /**
     * Registers support for {@link EclipseCollectionsModule} in {@link ObjectMapper}.
     * @return {@link ObjectMapper}
     */
    public static ObjectMapper createObjectMapperWithEclipseCollectionsSupport()
    {
        return ObjectMapperUtils.createSimpleObjectMapper().registerModule(new EclipseCollectionsModule());
    }
}
