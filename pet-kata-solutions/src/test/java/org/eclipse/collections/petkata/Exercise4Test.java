/*
 * Copyright (c) 2019 Goldman Sachs and others.
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
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.IntSet;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

import java.util.IntSummaryStatistics;

/**
 * In this set of tests, wherever you see .stream() replace it with an Eclipse Collections alternative.
 */
public class Exercise4Test extends PetDomainForKata {
    @Test
    public void getAgeStatisticsOfPets() {
        // Try to use a MutableIntList here instead
        // Hints: flatMap = flatCollect, map = collect, mapToInt = collectInt
        MutableIntList petAges = this.people
                .flatCollect(Person::getPets)
                .collectInt(Pet::getAge);

        // Try to use an IntSet here instead
        IntSet uniqueAges = petAges.toSet();

        // IntSummaryStatistics is a class in JDK 8 - Try and use it with MutableIntList.forEach()
        IntSummaryStatistics stats = new IntSummaryStatistics();
        petAges.forEach(stats::accept);

        // Is a Set<Integer> equal to an IntSet?
        // Hint: Try IntSets instead of Sets as the factory
        Assert.assertEquals(IntSets.immutable.of(1, 2, 3, 4), uniqueAges);

        // Try to leverage min, max, sum, average from the Eclipse Collections Primitive API
        Assert.assertEquals(stats.getMin(), petAges.min());
        Assert.assertEquals(stats.getMax(), petAges.max());
        Assert.assertEquals(stats.getSum(), petAges.sum());
        Assert.assertEquals(stats.getAverage(), petAges.average(), 0.0);
        Assert.assertEquals(stats.getCount(), petAges.size());

        // Hint: Match = Satisfy
        Assert.assertTrue(petAges.allSatisfy(IntPredicates.greaterThan(0)));
        Assert.assertTrue(petAges.allSatisfy(i -> i > 0));
        Assert.assertFalse(petAges.anySatisfy(i -> i == 0));
        Assert.assertTrue(petAges.noneSatisfy(i -> i < 0));
        Assert.assertEquals(2.0d, petAges.median(), 0.0);
    }

    @Test
    public void streamsToECRefactor1() {
        // Find Bob Smith
        Person person = this.people.detect(each -> each.named("Bob Smith"));

        // Get Bob Smith's pets' names
        String names = person.getPets()
                .collect(Pet::getName)
                .makeString(" & ");

        Assert.assertEquals("Dolly & Spot", names);
    }

    @Test
    public void streamsToECRefactor2() {
        // Hint: Try to replace the Map<PetType, Long> with a Bag<PetType>
        MutableBag<PetType> counts = this.people
                .asUnmodifiable()
                .flatCollect(Person::getPets)
                .countBy(Pet::getType);

        Assert.assertEquals(2, counts.occurrencesOf(PetType.CAT));
        Assert.assertEquals(2, counts.occurrencesOf(PetType.DOG));
        Assert.assertEquals(2, counts.occurrencesOf(PetType.HAMSTER));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.SNAKE));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.TURTLE));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.BIRD));
    }


    /**
     * The purpose of this test is to determine the top 3 pet types.
     */
    @Test
    public void streamsToECRefactor3() {
        // Hint: The result of groupingBy/counting can almost always be replaced by a Bag
        // Hint: Look for the API on Bag that might return the top 3 pet types
        MutableList<ObjectIntPair<PetType>> favorites = this.people
                .flatCollect(Person::getPets)
                .countBy(Pet::getType)
                .topOccurrences(3);

        Verify.assertSize(3, favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.CAT, 2), favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.DOG, 2), favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.HAMSTER, 2), favorites);
    }
}
