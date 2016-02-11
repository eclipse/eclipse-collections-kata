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
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.IntSet;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Exercise4Test extends PetDomainForKata
{
    @Test
    public void getAgeStatisticsOfPets()
    {
        IntList agesLazy = this.people.asLazy()
                .flatCollect(Person::getPets)
                .collectInt(Pet::getAge)
                .toList();
        IntSet uniqueAges = agesLazy.toSet();
        IntSummaryStatistics stats = new IntSummaryStatistics();
        agesLazy.forEach(stats::accept);
        Assert.assertEquals(IntHashSet.newSetWith(1, 2, 3, 4), uniqueAges);
        Assert.assertEquals(stats.getMin(), agesLazy.min());
        Assert.assertEquals(stats.getMax(), agesLazy.max());
        Assert.assertEquals(stats.getSum(), agesLazy.sum());
        Assert.assertEquals(stats.getAverage(), agesLazy.average(), 0.0);
        Assert.assertEquals(stats.getCount(), agesLazy.size());
        Assert.assertTrue(agesLazy.allSatisfy(IntPredicates.greaterThan(0)));
        Assert.assertTrue(agesLazy.allSatisfy(i -> i > 0));
        Assert.assertFalse(agesLazy.anySatisfy(i -> i == 0));
        Assert.assertTrue(agesLazy.noneSatisfy(i -> i < 0));
        Assert.assertEquals(2.0d, agesLazy.median(), 0.0);
    }

    @Test
    public void streamsToECRefactor1()
    {
        //find Bob Smith
        Person person = this.people.detect(each -> each.named("Bob Smith"));

        //get Bob Smith's pets' names
        String names = person.getPets().collect(Pet::getName).makeString(" & ");

        Assert.assertEquals("Dolly & Spot", names);
    }

    @Test
    public void streamsToECRefactor2()
    {
        MutableBag<PetType> counts =
                this.people
                        .asUnmodifiable()
                        .flatCollect(Person::getPets)
                        .collect(Pet::getType)
                        .toBag();
        Assert.assertEquals(2, counts.occurrencesOf(PetType.CAT));
        Assert.assertEquals(2, counts.occurrencesOf(PetType.DOG));
        Assert.assertEquals(2, counts.occurrencesOf(PetType.HAMSTER));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.SNAKE));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.TURTLE));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.BIRD));
    }

    @Test
    public void streamsToECRefactor3()
    {
        MutableList<ObjectIntPair<PetType>> favorites =
                this.people.asLazy()
                        .flatCollect(Person::getPets)
                        .collect(Pet::getType)
                        .toBag()
                        .topOccurrences(3);
        Verify.assertSize(3, favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.CAT, 2), favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.DOG, 2), favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.HAMSTER, 2), favorites);
    }
}
