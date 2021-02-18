/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.petkata;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.set.primitive.IntSet;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
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
        var petAges = this.people
                .flatCollect(Person::getPets)
                .collectInt(Pet::getAge);

        IntSet uniqueAges = petAges.toSet();

        var stats = petAges.summaryStatistics();
        var expected = IntSets.immutable.of(1, 2, 3, 4);
        Assertions.assertEquals(expected, uniqueAges);

        Assertions.assertEquals(stats.getMin(), petAges.minIfEmpty(0));
        Assertions.assertEquals(stats.getMax(), petAges.maxIfEmpty(0));
        Assertions.assertEquals(stats.getSum(), petAges.sum());
        Assertions.assertEquals(stats.getAverage(), petAges.averageIfEmpty(0), 0.0);
        Assertions.assertEquals(stats.getCount(), petAges.size());

        Assertions.assertTrue(petAges.allSatisfy(IntPredicates.greaterThan(0)));
        Assertions.assertTrue(petAges.allSatisfy(i -> i > 0));
        Assertions.assertFalse(petAges.anySatisfy(i -> i == 0));
        Assertions.assertTrue(petAges.noneSatisfy(i -> i < 0));
    }

    @Test
    @Tag("SOLUTION")
    public void streamsToECRefactor1()
    {
        // Find Bob Smith
        Person person = this.people.detect(each -> each.named("Bob Smith"));

        // Get Bob Smith's pets' names
        String names = person.getPets()
                .collect(Pet::getName)
                .makeString(" & ");

        Assertions.assertEquals("Dolly & Spot", names);
    }

    @Test
    @Tag("SOLUTION")
    public void streamsToECRefactor2()
    {
        MutableBag<PetType> petTypes = this.people
                .asUnmodifiable()
                .flatCollect(Person::getPets)
                .countBy(Pet::getType);

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
    public void streamsToECRefactor3()
    {
        var favorites = this.people
                .flatCollect(Person::getPets)
                .countBy(Pet::getType)
                .topOccurrences(3);

        Verify.assertSize(3, favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.CAT, 2), favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.DOG, 2), favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.HAMSTER, 2), favorites);
    }

    @Test
    @Tag("SOLUTION")
    public void getMedianOfPetAges()
    {
        var petAges = this.people
                .flatCollect(Person::getPets)
                .collectInt(Pet::getAge);

        Assertions.assertEquals(2.0d, petAges.median(), 0.0);
    }
}
