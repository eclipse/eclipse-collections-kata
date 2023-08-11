/*
 * Copyright (c) 2023 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.recordkata;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class RecordTest extends RecordSetup
{
    /**
     *  This is the starting point of the Record Kata. We have created a record type called OpenJDKDist
     *  {@link OpenJDKDist}. Since our type is reused throughout our code and immutable, creating a record
     *  for this case reduces boilerplate code and saves time. Explore how this record was declared and
     *  complete the exercises below.
     * <p>
     *  There is an inherited setup method {@link RecordSetup}. The first step in this kata is to navigate
     *  there to create an instance of our record. Afterwards, make each of the failing tests pass using
     *  features of records and Eclipse Collections libraries.
     *
     */
    @Test
    @Tag("KATA")
    public void addRedHatOpenjdk21()
    {
        OpenJDKDist jdk21 = new OpenJDKDist("Red Hat", "Openjdk", "21");

        // Notice how we can just call assertEquals (which calls the .equals method) without
        // implementing an equals method for the OpenJDKDist record. The record
        // automatically creates an equals method that compares each field.
        Assertions.assertEquals(jdk21, this.openJdkDistribution.getLastOptional().get());
        Assertions.assertNotEquals(
                new OpenJDKDist("Red Hat", "Openjdk", "20"), jdk21);
    }

    /**
     * Now we know how to create a record. Lets use it!
     * <p>
     * TODO: Get the Azul Zulu version 11 Distribution from our list and extract each field.
     */
    @Test
    @Tag("KATA")
    public void getFieldNames()
    {
        OpenJDKDist zuluJava11 = null;
        Assertions.assertEquals("Azul", null);
        Assertions.assertEquals("Zulu", null);
        Assertions.assertEquals("11", null);
    }

    /**
     * We know how to use records. Combine them with EC methods for some expressive features while
     * simultaneously letting the code be concise. The next two methods will test this.
     * <p>
     * TODO: Get the number of JDK distributions that have Oracle as its company.
     */
    @Test
    @Tag("KATA")
    public void countOpenJDKDistForOracle()
    {
        int count = -1;
        Assertions.assertEquals(3, count);
    }

    /**
     * TODO: Get the versions of Azul Zulu JDK versions.
     */
    @Test
    @Tag("KATA")
    public void selectAllAzulZuluVersions()
    {
        MutableList<String> azulVersions = null;
        Assertions.assertIterableEquals(Lists.mutable.with("11", "17", "18"), azulVersions);
    }
}
