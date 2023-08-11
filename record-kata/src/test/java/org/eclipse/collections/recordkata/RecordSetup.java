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
import org.junit.jupiter.api.BeforeEach;

public abstract class RecordSetup
{
    protected MutableList<OpenJDKDist> openJdkDistribution;

    /**
     *  This method creates a list of Open JDK Distribution records defined in
     *  {@link OpenJDKDist}
     *
     *  TODO: Create a new OpenJDKDist record for Red Hat Openjdk version 21 and append it to the end of the list.
     */
    @BeforeEach
    public void setUp()
    {
        this.openJdkDistribution = Lists.mutable.with(
                new OpenJDKDist("Azul", "Zulu", "11"),
                new OpenJDKDist("Azul", "Zulu", "17"),
                new OpenJDKDist("Azul", "Zulu", "18"),
                new OpenJDKDist("Oracle", "Openjdk", "18"),
                new OpenJDKDist("Oracle", "Openjdk", "19"),
                new OpenJDKDist("Oracle", "Openjdk", "20")
        );
    }
}
