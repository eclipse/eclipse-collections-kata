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

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Exercise4Test extends PetDomainForKata
{
    @Test
    public void getAgeStatisticsOfPets()
    {
        MutableList<Integer> petAges = this.people
                .stream()
                .flatMap(person -> person.getPets().stream())
                .map(pet -> pet.getAge())
                .collect(Collectors.toCollection(FastList::new));

        Set<Integer> uniqueAges = petAges.toSet();
        IntSummaryStatistics stats = petAges.stream().mapToInt(i -> i).summaryStatistics();
        Assert.assertEquals(Sets.mutable.with(1, 2, 3, 4), uniqueAges);
        Assert.assertEquals(stats.getMin(), petAges.stream().mapToInt(i -> i).min().getAsInt());
        Assert.assertEquals(stats.getMax(), petAges.stream().mapToInt(i -> i).max().getAsInt());
        Assert.assertEquals(stats.getSum(), petAges.stream().mapToInt(i -> i).sum());
        Assert.assertEquals(stats.getAverage(), petAges.stream().mapToInt(i -> i).average().getAsDouble(), 0.0);
        Assert.assertEquals(stats.getCount(), petAges.size());
        Assert.assertTrue(petAges.stream().allMatch(i -> i > 0));
        Assert.assertFalse(petAges.stream().anyMatch(i -> i == 0));
        Assert.assertTrue(petAges.stream().noneMatch(i -> i < 0));

        Assert.fail("Refactor to Eclipse Collections");
    }

    @Test
    public void streamsToECRefactor1()
    {
        //find Bob Smith
        Person person =
                this.people.stream()
                        .filter(each -> each.named("Bob Smith"))
                        .findFirst().get();

        //get Bob Smith's pets' names
        String names =
                person.getPets().stream()
                        .map(Pet::getName)
                        .collect(Collectors.joining(" & "));

        Assert.assertEquals("Dolly & Spot", names);
        Assert.fail("Refactor to Eclipse Collections");
    }

    @Test
    public void streamsToECRefactor2()
    {
        Map<PetType, Long> countsStream =
                Collections.unmodifiableMap(
                        this.people.stream()
                                .flatMap(person -> person.getPets().stream())
                                .collect(Collectors.groupingBy(Pet::getType,
                                        Collectors.counting())));
        Assert.assertEquals(Long.valueOf(2L), countsStream.get(PetType.CAT));
        Assert.assertEquals(Long.valueOf(2L), countsStream.get(PetType.DOG));
        Assert.assertEquals(Long.valueOf(2L), countsStream.get(PetType.HAMSTER));
        Assert.assertEquals(Long.valueOf(1L), countsStream.get(PetType.SNAKE));
        Assert.assertEquals(Long.valueOf(1L), countsStream.get(PetType.TURTLE));
        Assert.assertEquals(Long.valueOf(1L), countsStream.get(PetType.BIRD));

        Assert.fail("Refactor to Eclipse Collections");
    }

    @Test
    public void streamsToECRefactor3()
    {
        List<Map.Entry<PetType, Long>> favoritesStream =
                this.people.stream()
                        .flatMap(p -> p.getPets().stream())
                        .collect(Collectors.groupingBy(Pet::getType, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparingLong(e -> -e.getValue()))
                        .limit(3)
                        .collect(Collectors.toList());
        Verify.assertSize(3, favoritesStream);
        Verify.assertContains(new AbstractMap.SimpleEntry<>(PetType.CAT, Long.valueOf(2)), favoritesStream);
        Verify.assertContains(new AbstractMap.SimpleEntry<>(PetType.DOG, Long.valueOf(2)), favoritesStream);
        Verify.assertContains(new AbstractMap.SimpleEntry<>(PetType.HAMSTER, Long.valueOf(2)), favoritesStream);

        Assert.fail("Refactor to Eclipse Collections");
    }
}
