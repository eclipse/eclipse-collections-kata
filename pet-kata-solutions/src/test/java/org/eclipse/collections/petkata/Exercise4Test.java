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

import java.util.stream.Stream;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.set.primitive.IntSet;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * In this set of tests, wherever you see .stream() replace it with an Eclipse Collections alternative.
 */
public class Exercise4Test extends PetDomainForKata
{
    @Test
    @Tag("SOLUTION")
    public void getAgeStatisticsOfPets()
    {
        var petAges = this.people.flatCollectInt(Person::getPetAges, IntLists.mutable.empty());

        IntSet uniqueAges = petAges.toSet();
        var expected = IntSets.immutable.of(1, 2, 3, 4);
        Assertions.assertEquals(expected, uniqueAges);

        var stats = petAges.summaryStatistics();
        Assertions.assertEquals(stats.getMin(), petAges.minIfEmpty(0));
        Assertions.assertEquals(stats.getMax(), petAges.maxIfEmpty(0));
        Assertions.assertEquals(stats.getSum(), petAges.sum());
        Assertions.assertEquals(stats.getAverage(), petAges.averageIfEmpty(0.0), 0.0);
        Assertions.assertEquals(stats.getCount(), petAges.size());

        Assertions.assertTrue(petAges.allSatisfy(IntPredicates.greaterThan(0)));
        Assertions.assertTrue(petAges.allSatisfy(i -> i > 0));
        Assertions.assertFalse(petAges.anySatisfy(i -> i == 0));
        Assertions.assertTrue(petAges.noneSatisfy(i -> i < 0));
    }

    @Test
    @Tag("SOLUTION")
    public void bobSmithsPetNamesAsString()
    {
        // Find Bob Smith
        Person person = this.getPersonNamed("Bob Smith");

        // Get Bob Smith's pets' names
        String names = person.getPets()
                .collect(Pet::getName)
                .makeString(" & ");

        Assertions.assertEquals("Dolly & Spot", names);
    }

    @Test
    @Tag("SOLUTION")
    public void immutablePetCounts()
    {
        ImmutableBag<PetType> petTypes =
                this.people.countByEach(Person::getPetTypes).toImmutable();

        Assertions.assertEquals(2, petTypes.occurrencesOf(PetType.CAT));
        Assertions.assertEquals(2, petTypes.occurrencesOf(PetType.DOG));
        Assertions.assertEquals(2, petTypes.occurrencesOf(PetType.HAMSTER));
        Assertions.assertEquals(1, petTypes.occurrencesOf(PetType.SNAKE));
        Assertions.assertEquals(1, petTypes.occurrencesOf(PetType.TURTLE));
        Assertions.assertEquals(1, petTypes.occurrencesOf(PetType.BIRD));
    }

    /**
     * The purpose of this test is to determine the top 3 pet types.
     */
    @Test
    @Tag("SOLUTION")
    public void topThreePets()
    {
        var favorites = this.people
                .countByEach(Person::getPetTypes)
                .topOccurrences(3);

        Verify.assertSize(3, favorites);
        Assertions.assertTrue(Stream.of(PetType.CAT, PetType.DOG, PetType.HAMSTER)
                .allMatch(type -> favorites.containsBy(ObjectIntPair::getOne, type)));
    }

    @Test
    @Tag("SOLUTION")
    public void getMedianOfPetAges()
    {
        var petAges = this.people.flatCollectInt(Person::getPetAges, IntLists.mutable.empty());
        Assertions.assertEquals(2.0d, petAges.median(), 0.0);
    }
}
