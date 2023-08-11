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

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Multimaps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class RecordTest extends RecordSetup
{
    @Test
    @Tag("SOLUTION")
    public void addRedHatOpenjdk21()
    {
        OpenJDKDist jdk21 = new OpenJDKDist("Red Hat", "Openjdk", "21");
        Assertions.assertEquals(jdk21, this.openJdkDistribution.getLastOptional().get());
        Assertions.assertNotEquals(
                new OpenJDKDist("Red Hat", "Openjdk", "20"), jdk21);
    }

    @Test
    @Tag("SOLUTION")
    public void getFieldNames()
    {
        OpenJDKDist zuluJava11 = this.openJdkDistribution.get(0);
        Assertions.assertEquals("Azul", zuluJava11.company());
        Assertions.assertEquals("Zulu", zuluJava11.buildName());
        Assertions.assertEquals("11", zuluJava11.version());
    }

    @Test
    @Tag("SOLUTION")
    public void countOpenJDKDistForOracle()
    {
        int count = this.openJdkDistribution.countBy(OpenJDKDist::company).occurrencesOf("Oracle");
        Assertions.assertEquals(3, count);
    }

    @Test
    @Tag("SOLUTION")
    public void selectAllAzulZuluVersions()
    {
        MutableList<String> azulVersions =
                this.openJdkDistribution.select(
                        openJdk -> "azul".equalsIgnoreCase(openJdk.company())
                ).collect(OpenJDKDist::version);
        Assertions.assertIterableEquals(Lists.mutable.with("11", "17", "18"), azulVersions);
    }
}
