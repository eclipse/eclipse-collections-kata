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

import java.util.Objects;

public class Pet
{
    private PetType type;
    private String name;
    private int age;

    public Pet()
    {
    }

    public Pet(PetType type, String name, int age)
    {
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public PetType getType()
    {
        return this.type;
    }

    public String getName()
    {
        return this.name;
    }

    public int getAge()
    {
        return this.age;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }
        Pet pet = (Pet) o;
        return this.age == pet.age && this.type == pet.type && this.name.equals(pet.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.type, this.name, this.age);
    }
}
