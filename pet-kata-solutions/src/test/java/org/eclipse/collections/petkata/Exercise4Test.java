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
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.list.primitive.MutableIntListFactory;
import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.factory.primitive.IntSets;
import org.eclipse.collections.api.factory.set.primitive.MutableIntSetFactory;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.IntSet;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * In this set of tests, wherever you see .stream() replace it with an Eclipse Collections alternative.
 *
 * {@link org.eclipse.collections.api.list.primitive.MutableIntList}<br>
 * {@link org.eclipse.collections.api.set.primitive.IntSet}<br>
 * {@link org.eclipse.collections.impl.factory.primitive.IntSets}<br>
 * {@link org.eclipse.collections.impl.block.factory.primitive.IntPredicates}<br>
 * {@link org.eclipse.collections.api.bag.MutableBag}<br>
 * {@link org.eclipse.collections.api.list.MutableList}<br>
 * {@link MutableIntListFactory#empty()}<br>
 * {@link MutableIntSetFactory#empty()}<br>
 * {@link MutableList#flatCollectInt(Function, MutableIntCollection)}<br>
 * {@link MutableIntList#toSet()}<br>
 * {@link MutableIntList#summaryStatistics()}<br>
 * {@link MutableIntList#minIfEmpty(int)}<br>
 * {@link MutableIntList#maxIfEmpty(int)}<br>
 * {@link MutableIntList#sum()}<br>
 * {@link MutableIntList#averageIfEmpty(double)}<br>
 * {@link MutableIntList#size()}<br>
 * {@link IntPredicates#greaterThan(int)}<br>
 * {@link MutableIntList#anySatisfy(IntPredicate)}<br>
 * {@link MutableIntList#allSatisfy(IntPredicate)}<br>
 * {@link MutableIntList#noneSatisfy(IntPredicate)}<br>
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
    @DisplayName("bobSmithsPetNamesAsString - 🐱 🐶")
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
    public void immutablePetCountsByEmoji()
    {
        ImmutableBag<String> countsByEmoji =
                this.people.countByEach(Person::getPetEmojis).toImmutable();

        Assertions.assertEquals(
                Bags.immutable.withOccurrences("🐱", 2, "🐶", 2, "🐹", 2)
                        .newWith("🐍")
                        .newWith("🐢")
                        .newWith("🐦"),
                countsByEmoji
        );
    }

    /**
     * The purpose of this test is to determine the top 3 pet types.
     */
    @Test
    @Tag("SOLUTION")
    @DisplayName("topThreePets - 🐱 🐶 🐹")
    public void topThreePets()
    {
        var favorites = this.people
                .countByEach(Person::getPetEmojis)
                .topOccurrences(3);

        Verify.assertSize(3, favorites);
        Assertions.assertTrue(Stream.of("🐱", "🐶", "🐹")
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
