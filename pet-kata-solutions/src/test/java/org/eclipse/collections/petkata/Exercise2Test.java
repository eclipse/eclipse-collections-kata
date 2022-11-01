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
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * In the slides leading up to this exercise you should have learned about the following APIs.
 * <p/>
 * {@link MutableList#flatCollect(Function)}<br>
 * {@link MutableList#select(Predicate)}<br>
 * {@link MutableList#reject(Predicate)}<br>
 * {@link MutableList#count(Predicate)}<br>
 * {@link MutableList#detect(Predicate)}<br>
 * {@link MutableList#anySatisfy(Predicate)}<br>
 * {@link MutableList#allSatisfy(Predicate)}<br>
 * {@link MutableList#noneSatisfy(Predicate)}<br>
 * <br>
 * You should have also learned about the following methods as well.<br>
 * <br>
 * {@link MutableList#selectWith(Predicate2, Object)}<br>
 * {@link MutableList#rejectWith(Predicate2, Object)}<br>
 * {@link MutableList#countWith(Predicate2, Object)}<br>
 * {@link MutableList#detectWith(Predicate2, Object)}<br>
 * {@link MutableList#anySatisfyWith(Predicate2, Object)}<br>
 * {@link MutableList#allSatisfyWith(Predicate2, Object)}<br>
 * {@link MutableList#noneSatisfyWith(Predicate2, Object)}<br>
 * <br>
 *
 * @see <a href="http://eclipse.github.io/eclipse-collections-kata/pet-kata/#/4">Exercise 2 Slides</a>
 */
public class Exercise2Test extends PetDomainForKata
{
    @Test
    @Tag("SOLUTION")
    @DisplayName("doAnyPeopleHaveCats 🐱?")
    public void doAnyPeopleHaveCats()
    {
        Predicate<Person> predicate = person -> person.hasPet("🐱");

        Assertions.assertTrue(this.people.anySatisfy(predicate));
    }

    @Test
    @Tag("SOLUTION")
    public void doAllPeopleHavePets()
    {
        Predicate<Person> predicate = Person::isPetPerson;
        boolean result = this.people.allSatisfy(predicate);

        Assertions.assertFalse(result);
    }

    @Test
    @Tag("SOLUTION")
    @DisplayName("howManyPeopleHaveCats 🐱?")
    public void howManyPeopleHaveCats()
    {
        int count = this.people.count(person -> person.hasPet("🐱"));

        Assertions.assertEquals(2, count);
    }

    @Test
    @Tag("SOLUTION")
    public void findMarySmith()
    {
        Person result = this.people.detectWith(Person::named, "Mary Smith");

        Assertions.assertEquals("Mary", result.getFirstName());
        Assertions.assertEquals("Smith", result.getLastName());
    }

    @Test
    @Tag("SOLUTION")
    @DisplayName("findPetNamedSerpy 🐍")
    public void findPetNamedSerpy()
    {
        MutableList<Pet> petList = this.people.flatCollect(Person::getPets);

        Pet serpySnake = petList.detectWith((pet, name) -> pet.getName().equals(name), "Serpy");

        Assertions.assertEquals("🐍", serpySnake.getType().toString());
    }

    @Test
    @Tag("SOLUTION")
    public void getPeopleWithPets()
    {
        MutableList<Person> petPeople = this.people.select(Person::isPetPerson);

        Verify.assertSize(7, petPeople);
    }

    @Test
    @Tag("SOLUTION")
    public void getAllPetTypesOfAllPeople()
    {
        Function<Person, Iterable<PetType>> function = Person::getPetTypes;
        MutableSet<PetType> petTypes = this.people.flatCollect(function, Sets.mutable.empty());

        var expected =
                Sets.mutable.with(PetType.CAT, PetType.DOG, PetType.TURTLE, PetType.HAMSTER, PetType.BIRD, PetType.SNAKE);
        Assertions.assertEquals(expected, petTypes);
    }

    @Test
    @Tag("SOLUTION")
    public void getAllPetEmojisOfAllPeople()
    {
        Function<Person, Iterable<String>> function = Person::getPetEmojis;
        MutableSet<String> petEmojis = this.people.flatCollect(function, Sets.mutable.empty());

        var expected = Sets.mutable.with("🐱", "🐶", "🐢", "🐹", "🐦", "🐍");
        Assertions.assertEquals(expected, petEmojis);
    }

    @Test
    @Tag("SOLUTION")
    public void getFirstNamesOfAllPeople()
    {
        MutableList<String> firstNames = this.people.collect(Person::getFirstName);

        var expected =
                Lists.mutable.with("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John");
        Assertions.assertEquals(expected, firstNames);
    }

    @Test
    @Tag("SOLUTION")
    @DisplayName("doAnyPeopleHaveCatsRefactor 🐱?")
    public void doAnyPeopleHaveCatsRefactor()
    {
        boolean peopleHaveCatsLambda = this.people.anySatisfy(person -> person.hasPet("🐱"));
        Assertions.assertTrue(peopleHaveCatsLambda);

        boolean peopleHaveCatsMethodRef = this.people.anySatisfyWith(Person::hasPet, "🐱");
        Assertions.assertTrue(peopleHaveCatsMethodRef);
    }

    @Test
    @Tag("SOLUTION")
    @DisplayName("doAllPeopleHaveCatsRefactor 🐱?")
    public void doAllPeopleHaveCatsRefactor()
    {
        boolean peopleHaveCatsLambda = this.people.allSatisfy(person -> person.hasPet("🐱"));
        Assertions.assertFalse(peopleHaveCatsLambda);

        boolean peopleHaveCatsMethodRef = this.people.allSatisfyWith(Person::hasPet, "🐱");
        Assertions.assertFalse(peopleHaveCatsMethodRef);
    }

    @Test
    @Tag("SOLUTION")
    @DisplayName("getPeopleWithCatsRefactor 🐱?")
    public void getPeopleWithCatsRefactor()
    {
        MutableList<Person> peopleWithCatsMethodRef = this.people.selectWith(Person::hasPet, "🐱");

        Verify.assertSize(2, peopleWithCatsMethodRef);
    }

    @Test
    @Tag("SOLUTION")
    @DisplayName("getPeopleWithoutCatsRefactor 🐱?")
    public void getPeopleWithoutCatsRefactor()
    {
        MutableList<Person> peopleWithoutCatsMethodRef = this.people.rejectWith(Person::hasPet, "🐱");

        Verify.assertSize(6, peopleWithoutCatsMethodRef);
    }
}
