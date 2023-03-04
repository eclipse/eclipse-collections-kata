/*
 * Copyright (c) 2022 Goldman Sachs and others.
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
    @Test
    @Tag("SOLUTION")
    public void getFieldNames()
    {
        MutableList<OpenJDKDist> jdkRecords = this.openJdkDistribution;
        var zuluJava11BuildName = this.openJdkDistribution.get(0).buildName();
        Assertions.assertNotNull(zuluJava11BuildName);
        Assertions.assertEquals(3, (this.openJdkDistribution.countBy(OpenJDKDist::company).occurrencesOf("Azul")));
        Assertions.assertIterableEquals(Lists.mutable.with("18", "19", "20"), this.openJdkDistribution.select(openJdk -> openJdk.company().equalsIgnoreCase("oracle")).collect(OpenJDKDist::version));
    }
}
