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

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * In the slides leading up to this exercise you should have learned about the following APIs.
 * <p/>
 * {@link MutableList#collect(Function)}<br>
 * {@link MutableList#select(Predicate)}<br>
 *
 * @see <a href="http://eclipse.github.io/eclipse-collections-kata/pet-kata/#/2">Exercise 1 Slides</a>
 */
public class Exercise1Test extends PetDomainForKata
{
    @Test
    @Tag("KATA")
    public void getFirstNamesOfAllPeople()
    {
        // Replace null, with a transformation method on MutableList.
        MutableList<String> firstNames = null; // this.people...

        var expectedFirstNames = Lists.mutable.with("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John");
        Assertions.assertEquals(expectedFirstNames, firstNames);
    }

    @Test
    @Tag("KATA")
    public void getNamesOfMarySmithsPets()
    {
        Person person = this.getPersonNamed("Mary Smith");
        MutableList<Pet> pets = person.getPets();

        // Replace null, with a transformation method on MutableList.
        MutableList<String> names = null; // pets...

        Assertions.assertEquals("Tabby", names.makeString());
    }

    @Test
    @Tag("KATA")
    @DisplayName("getPeopleWithCats 🐱")
    public void getPeopleWithCats()
    {
        // Replace null, with a positive filtering method on MutableList.
        MutableList<Person> peopleWithCats = null;  // this.people...

        var expectedFirstNames = Lists.mutable.with("Smith", "Smith");
        Assertions.assertEquals(expectedFirstNames, peopleWithCats.collect(Person::getLastName));
    }

    @Test
    @Tag("KATA")
    @DisplayName("getPeopleWithoutCats 🐱")
    public void getPeopleWithoutCats()
    {
        // Replace null, with a negative filtering method on MutableList.
        MutableList<Person> peopleWithoutCats = null;  // this.people...

        var expectedFirstNames = Lists.mutable.with("Smith", "Snake", "Bird", "Turtle", "Hamster", "Doe");
        Assertions.assertEquals(expectedFirstNames, peopleWithoutCats.collect(Person::getLastName));
    }
}
