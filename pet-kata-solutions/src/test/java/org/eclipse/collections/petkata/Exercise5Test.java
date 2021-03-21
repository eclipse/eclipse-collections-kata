/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.petkata;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class Exercise5Test extends PetDomainForKata
{
    @Test
    @Tag("SOLUTION")
    public void partitionPetAndNonPetPeople()
    {
        PartitionMutableList<Person> partitionMutableList = this.people
                .partition(Person::isPetPerson);

        Verify.assertSize(7, partitionMutableList.getSelected());
        Verify.assertSize(1, partitionMutableList.getRejected());
    }

    @Test
    @Tag("SOLUTION")
    public void getOldestPet()
    {
        Pet oldestPet = this.people
                .flatCollect(Person::getPets)
                .maxBy(Pet::getAge);

        Assertions.assertEquals(PetType.DOG, oldestPet.getType());
        Assertions.assertEquals(4, oldestPet.getAge());
    }

    @Test
    @Tag("SOLUTION")
    public void getAveragePetAge()
    {
        double averagePetAge = this.people
                .flatCollect(Person::getPets)
                .collectDouble(Pet::getAge)
                .average();

        Assertions.assertEquals(1.8888888888888888, averagePetAge, 0.00001);
    }

    @Test
    @Tag("SOLUTION")
    public void addPetAgesToExistingSet()
    {
        MutableIntSet petAges = IntSets.mutable.with(5);

        this.people.flatCollect(Person::getPets)
                .collectInt(Pet::getAge, petAges);

        var expected = IntSets.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expected, petAges);
    }

    @Test
    @Tag("SOLUTION")
    public void refactorToEclipseCollections()
    {
        MutableList<Person> people = Lists.mutable.with(
                new Person("Mary", "Smith").addPet(PetType.CAT, "Tabby", 2),
                new Person("Bob", "Smith")
                        .addPet(PetType.CAT, "Dolly", 3)
                        .addPet(PetType.DOG, "Spot", 2),
                new Person("Ted", "Smith").addPet(PetType.DOG, "Spike", 4),
                new Person("Jake", "Snake").addPet(PetType.SNAKE, "Serpy", 1),
                new Person("Barry", "Bird").addPet(PetType.BIRD, "Tweety", 2),
                new Person("Terry", "Turtle").addPet(PetType.TURTLE, "Speedy", 1),
                new Person("Harry", "Hamster")
                        .addPet(PetType.HAMSTER, "Fuzzy", 1)
                        .addPet(PetType.HAMSTER, "Wuzzy", 1),
                new Person("John", "Doe")
        );

        MutableIntSet petAges = people
                .flatCollect(Person::getPets)
                .collectInt(Pet::getAge)
                .toSet();

        //extra bonus - convert to a primitive collection
        var expected = IntSets.mutable.with(1, 2, 3, 4);
        Assertions.assertEquals(expected, petAges);
    }
}
