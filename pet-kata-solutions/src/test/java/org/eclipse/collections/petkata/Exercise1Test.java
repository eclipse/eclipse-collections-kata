/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.petkata;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Exercise1Test extends PetDomainForKata
{
    @Test
    public void getFirstNamesOfAllPeople()
    {
        MutableList<Person> people = this.people;
        // Replace null, with a transformation method on MutableList.
        MutableList<String> firstNames = people.collect(Person::getFirstName);
        MutableList<String> expectedFirstNames = Lists.mutable.with("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John");
        Assert.assertEquals(expectedFirstNames, firstNames);
    }

    @Test
    public void getNamesOfMarySmithsPets()
    {
        Person person = this.getPersonNamed("Mary Smith");
        MutableList<Pet> pets = person.getPets();
        // Replace null, with a transformation method on MutableList.
        MutableList<String> names = pets.collect(eachPet -> eachPet.getName());
        Assert.assertEquals("Tabby", names.makeString());
    }

    @Test
    public void getPeopleWithCats()
    {
        MutableList<Person> people = this.people;
        MutableList<Person> peopleWithCats = people.select(person -> person.hasPet(PetType.CAT));
        Verify.assertSize(2, peopleWithCats);
    }

    @Test
    public void getPeopleWithoutCats()
    {
        MutableList<Person> people = this.people;
        MutableList<Person> peopleWithoutCats = people.reject(person -> person.hasPet(PetType.CAT));
        Verify.assertSize(6, peopleWithoutCats);
    }
}
