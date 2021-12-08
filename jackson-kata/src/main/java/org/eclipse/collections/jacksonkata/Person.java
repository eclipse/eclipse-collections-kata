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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.factory.primitive.IntObjectMaps;

public class Person
{
    private String firstName;
    private String lastName;
    private int age;

    //mark as json property
    private final MutableList<Pet> pets = Lists.mutable.empty();

    //mark as json property
    private final MutableIntObjectMap<MutableList<Pet>> petsByAge = IntObjectMaps.mutable.empty();

    //mark as json property
    private final MutableMap<PetType, MutableList<Pet>> petsByType = Maps.mutable.empty();

    public Person()
    {
    }

    public Person(String firstName, String lastName, int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public MutableList<Pet> getPetsByAge(int age)
    {
        return this.petsByAge.getIfAbsent(age, () -> Lists.mutable.empty());
    }

    public MutableList<Pet> getPetsByType(PetType petType)
    {
        return this.petsByType.getIfAbsent(petType, Lists.mutable::empty);
    }

    public boolean hasPet(PetType petType)
    {
        return this.petsByType.containsKey(petType);
    }

    @JsonIgnore
    public ImmutableList<Pet> getImmutablePets()
    {
        return this.pets.toImmutable();
    }

    public void addPet(PetType petType, String name, int age)
    {
        Pet pet = new Pet(petType, name, age);
        this.pets.add(pet);
        this.petsByAge.getIfAbsentPut(pet.getAge(), Lists.mutable::empty).add(pet);
        this.petsByType.getIfAbsentPut(pet.getType(), Lists.mutable::empty).add(pet);
    }

    @JsonIgnore
    public int getNumberOfPets()
    {
        return this.pets.size();
    }

    public int getAge()
    {
        return this.age;
    }
}
