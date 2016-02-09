/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.petkata;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.list.mutable.FastList;

public class Person
{
    private final String firstName;
    private final String lastName;
    private final MutableList<Pet> pets = FastList.newList();

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

    public boolean named(String name)
    {
        return name.equals(this.firstName + ' ' + this.lastName);
    }

    public boolean hasPet(PetType petType)
    {
        return this.pets.anySatisfyWith(Predicates2.attributeEqual(Pet::getType), petType);
    }

    public MutableList<Pet> getPets()
    {
        return this.pets;
    }

    public MutableBag<PetType> getPetTypes()
    {
        return this.pets.collect(Pet::getType, HashBag.newBag());
    }

    public Person addPet(PetType petType, String name, int age)
    {
        this.pets.add(new Pet(petType, name, age));
        return this;
    }

    public boolean isPetPerson()
    {
        return this.getNumberOfPets() >= 1;
    }

    public int getNumberOfPets()
    {
        return this.pets.size();
    }
}
