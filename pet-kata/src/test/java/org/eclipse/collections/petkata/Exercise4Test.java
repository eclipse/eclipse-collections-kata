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

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * In this set of tests, wherever you see .stream() replace it with an Eclipse Collections alternative.
 * <p/>
 * {@link org.eclipse.collections.api.list.primitive.MutableIntList}<br>
 * {@link org.eclipse.collections.api.set.primitive.IntSet}<br>
 * {@link org.eclipse.collections.impl.factory.primitive.IntSets}<br>
 * {@link org.eclipse.collections.impl.block.factory.primitive.IntPredicates}<br>
 * {@link org.eclipse.collections.api.bag.MutableBag}<br>
 * {@link org.eclipse.collections.api.list.MutableList}<br>
 *
 * @see <a href="http://eclipse.github.io/eclipse-collections-kata/pet-kata/#/8">Exercise 4 Slides</a>
 */
public class Exercise4Test extends PetDomainForKata
{
    @Test
    public void getAgeStatisticsOfPets()
    {
        Assert.fail("Refactor to Eclipse Collections. Don't forget to comment this out or delete it when you are done.");

        // Try to use a MutableIntList here instead
        // Hints: flatMap = flatCollect, map = collect, mapToInt = collectInt
        var petAges = this.people
                .stream()
                .map(Person::getPets)
                .flatMap(List::stream)
                .mapToInt(Pet::getAge)
                .boxed()
                .collect(Collectors.toList());

        // Try to use an IntSet here instead
        var uniqueAges = Set.copyOf(petAges);

        // IntSummaryStatistics is a class in JDK 8 - Try IntList.summaryStatistics
        var stats = petAges.stream().mapToInt(Integer::intValue).summaryStatistics();

        // Is a Set<Integer> equal to an IntSet?
        // Hint: Try IntSets instead of Set as the factory
        var expectedSet = Set.of(1, 2, 3, 4);
        Assert.assertEquals(expectedSet, uniqueAges);

        // Try to leverage minIfEmpty, maxIfEmpty, sum, averageIfEmpty on IntList
        Assert.assertEquals(stats.getMin(), petAges.stream().mapToInt(i -> i).min().orElse(0));
        Assert.assertEquals(stats.getMax(), petAges.stream().mapToInt(i -> i).max().orElse(0));
        Assert.assertEquals(stats.getSum(), petAges.stream().mapToInt(i -> i).sum());
        Assert.assertEquals(stats.getAverage(), petAges.stream().mapToInt(i -> i).average().orElse(0.0), 0.0);
        Assert.assertEquals(stats.getCount(), petAges.size());

        // Hint: JDK xyzMatch = Eclipse Collections xyzSatisfy
        Assert.assertTrue(petAges.stream().allMatch(i -> i > 0));
        Assert.assertFalse(petAges.stream().anyMatch(i -> i == 0));
        Assert.assertTrue(petAges.stream().noneMatch(i -> i < 0));
    }

    @Test
    public void streamsToECRefactor1()
    {
        Assert.fail("Refactor to Eclipse Collections. Don't forget to comment this out or delete it when you are done.");

        //find Bob Smith
        Person person = this.people
                .stream()
                .filter(each -> each.named("Bob Smith"))
                .findFirst()
                .orElse(null);

        //get Bob Smith's pets' names
        String names = person.getPets()
                .stream()
                .map(Pet::getName)
                .collect(Collectors.joining(" & "));

        Assert.assertEquals("Dolly & Spot", names);
    }

    @Test
    public void streamsToECRefactor2()
    {
        Assert.fail("Refactor to Eclipse Collections. Don't forget to comment this out or delete it when you are done.");

        // Hint: Try to replace the immutable Map<PetType, Long> with an ImmutableBag<PetType>
        Map<PetType, Long> counts =
                Map.copyOf(this.people
                        .stream()
                        .flatMap(person -> person.getPets().stream())
                        .collect(Collectors.groupingBy(Pet::getType, Collectors.counting())));

        Assert.assertEquals(Long.valueOf(2L), counts.get(PetType.CAT));
        Assert.assertEquals(Long.valueOf(2L), counts.get(PetType.DOG));
        Assert.assertEquals(Long.valueOf(2L), counts.get(PetType.HAMSTER));
        Assert.assertEquals(Long.valueOf(1L), counts.get(PetType.SNAKE));
        Assert.assertEquals(Long.valueOf(1L), counts.get(PetType.TURTLE));
        Assert.assertEquals(Long.valueOf(1L), counts.get(PetType.BIRD));
    }

    /**
     * The purpose of this test is to determine the top 3 pet types.
     */
    @Test
    public void streamsToECRefactor3()
    {
        Assert.fail("Refactor to Eclipse Collections. Don't forget to comment this out or delete it when you are done.");

        // Hint: The result of groupingBy/counting can almost always be replaced by a Bag
        // Hint: Look for the API on Bag that might return the top 3 pet types
        var favorites = this.people
                .stream()
                .flatMap(p -> p.getPets().stream())
                .collect(Collectors.groupingBy(Pet::getType, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingLong(e -> -e.getValue()))
                .limit(3L)
                .collect(Collectors.toList());

        Verify.assertSize(3, favorites);

        // Hint: Look at PrimitiveTuples.pair(Object, int)
        Verify.assertContains(new AbstractMap.SimpleEntry<>(PetType.CAT, Long.valueOf(2)), favorites);
        Verify.assertContains(new AbstractMap.SimpleEntry<>(PetType.DOG, Long.valueOf(2)), favorites);
        Verify.assertContains(new AbstractMap.SimpleEntry<>(PetType.HAMSTER, Long.valueOf(2)), favorites);
    }
}
