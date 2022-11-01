/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.petkata;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public class Person
{
    private final String firstName;
    private final String lastName;
    private final MutableList<Pet> pets = Lists.mutable.empty();

    public Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getFullName()
    {
        return this.firstName + ' ' + this.lastName;
    }

    public boolean named(String name)
    {
        return name.equals(this.getFullName());
    }

    public boolean hasPet(PetType petType)
    {
        return this.pets.containsBy(Pet::getType, petType);
    }

    public boolean hasPet(String petEmoji)
    {
        return this.hasPet(PetType.fromEmoji(petEmoji));
    }

    public MutableList<Pet> getPets()
    {
        return this.pets;
    }

    public Bag<PetType> getPetTypes()
    {
        return this.pets.countBy(Pet::getType);
    }

    public Bag<String> getPetEmojis()
    {
        return this.getPetTypes().collect(Object::toString, Bags.mutable.empty());
    }

    public Person addPet(PetType petType, String name, int age)
    {
        this.pets.add(new Pet(petType, name, age));
        return this;
    }

    public boolean isPetPerson()
    {
        return this.pets.notEmpty();
    }
}
