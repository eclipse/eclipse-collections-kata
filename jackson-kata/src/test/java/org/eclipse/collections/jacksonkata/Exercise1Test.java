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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

public class Exercise1Test
{
    /**
     * Analyze Jackson exception reported when running this test.
     * Pay attention to "Cannot construct instance of org.eclipse.collections.jacksonkata.Pet" exception
     * message. What is missing on {@link Pet} class to enable Jackson to create a new instance? Make changes to
     * {@link Pet} class to make this test pass.
     */
    @Test
    @Tag("KATA")
    public void petSerialization() throws JsonProcessingException
    {
        ObjectMapper objectMapper = ObjectMapperUtils.createSimpleObjectMapper();

        Pet cat = new Pet(PetType.CAT, "Jack", 5);
        Pet deserializedCat = objectMapper.readValue(objectMapper.writeValueAsString(cat), Pet.class);
        Assert.assertEquals(cat, deserializedCat);
        Assert.assertSame(PetType.CAT, deserializedCat.getType());

        Pet dog = new Pet(PetType.DOG, "Bobby", 4);
        Pet deserializedDog = objectMapper.readValue(objectMapper.writeValueAsString(dog), Pet.class);
        Assert.assertEquals(dog, deserializedDog);
        Assert.assertSame(PetType.DOG, deserializedDog.getType());
    }
}
