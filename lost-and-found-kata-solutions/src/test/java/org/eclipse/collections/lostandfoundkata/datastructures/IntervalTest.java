/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.datastructures;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.BiMaps;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.SortedBags;
import org.eclipse.collections.impl.factory.SortedMaps;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.list.Interval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class IntervalTest
{
    @Test
    @Tag("SOLUTION")
    public void oneToTen()
    {
        Interval intervalOneToTen = Interval.oneTo(10);
        var streamOneToTenToList = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamOneToTenToList, intervalOneToTen);
    }

    @Test
    @Tag("SOLUTION")
    public void zeroToNine()
    {
        Interval intervalZeroToNine = Interval.zeroTo(9);
        var streamZeroToNineToList = IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamZeroToNineToList, intervalZeroToNine);
    }

    @Test
    @Tag("SOLUTION")
    public void evensFromOneToTen()
    {
        Interval intervalEvensFromOneToTen = Interval.evensFromTo(1, 10);
        var streamEvensFromOneToTenToList =
                IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamEvensFromOneToTenToList, intervalEvensFromOneToTen);
    }

    @Test
    @Tag("SOLUTION")
    public void oddsFromZeroToNine()
    {
        Interval intervalOddsFromZeroToNine = Interval.oddsFromTo(0, 9);
        var streamOddsFromZeroToNineToList =
                IntStream.rangeClosed(0, 9).filter(i -> i % 2 != 0).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamOddsFromZeroToNineToList, intervalOddsFromZeroToNine);
    }

    @Test
    @Tag("SOLUTION")
    public void oneToTenByThree()
    {
        Interval intervalFromOneToTenByThree = Interval.oneToBy(10, 3);
        var listFromOneToTenByThree = List.of(1, 4, 7, 10);
        Assertions.assertEquals(listFromOneToTenByThree, intervalFromOneToTenByThree);
    }

    @Test
    @Tag("SOLUTION")
    public void zeroToNineByThree()
    {
        Interval intervalZeroToNineByThree = Interval.zeroToBy(9, 3);
        var listFromZeroToNineByThree = List.of(0, 3, 6, 9);
        Assertions.assertEquals(listFromZeroToNineByThree, intervalZeroToNineByThree);
    }

    @Test
    @Tag("SOLUTION")
    public void tenToOne()
    {
        Interval intervalFromTenToOne = Interval.fromTo(10, 1);
        var streamOneToTenToReverseOrderList =
                IntStream.rangeClosed(1, 10).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Assertions.assertEquals(streamOneToTenToReverseOrderList, intervalFromTenToOne);
    }

    @Test
    @Tag("SOLUTION")
    public void nineToZero()
    {
        Interval intervalFromNineToZero = Interval.fromTo(9, 0);
        var streamZeroToNineToReverseOrderList =
                IntStream.rangeClosed(0, 9).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Assertions.assertEquals(streamZeroToNineToReverseOrderList, intervalFromNineToZero);
    }

    @Test
    @Tag("SOLUTION")
    public void evensFromTenToOne()
    {
        Interval intervalEvensFromTenToOne = Interval.evensFromTo(10, 1);
        var streamEvensOneToTenToReverseOrderList =
                IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0)
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList());
        Assertions.assertEquals(streamEvensOneToTenToReverseOrderList, intervalEvensFromTenToOne);
    }

    @Test
    @Tag("SOLUTION")
    public void oddsFromNineToZero()
    {
        Interval intervalOddsFromNineToZero = Interval.oddsFromTo(9, 0);
        var streamOddsZeroToNineToReversOrderList =
                IntStream.rangeClosed(0, 9).filter(i -> i % 2 != 0)
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList());
        Assertions.assertEquals(streamOddsZeroToNineToReversOrderList, intervalOddsFromNineToZero);
    }

    @Test
    @Tag("SOLUTION")
    public void tenToOneByNegativeThree()
    {
        Interval intervalFromTenToOneByNegativeThree = Interval.fromToBy(10, 1, -3);
        var reverseListTenToOneByThree = List.of(10, 7, 4, 1);
        Assertions.assertEquals(reverseListTenToOneByThree, intervalFromTenToOneByNegativeThree);
    }

    @Test
    @Tag("SOLUTION")
    public void nineToZeroByNegativeThree()
    {
        Interval intervalFromNineToZeroByThree = Interval.fromToBy(9, 0, -3);
        var reverseListNineToZeroByThree = List.of(9, 6, 3, 0);
        Assertions.assertEquals(reverseListNineToZeroByThree, intervalFromNineToZeroByThree);
    }

    @Test
    @Tag("SOLUTION")
    public void toArray()
    {
        Integer[] array = Interval.oneTo(5).toArray();
        Integer[] expectedArray = {1, 2, 3, 4, 5};
        Assertions.assertArrayEquals(expectedArray, array);
    }

    @Test
    @Tag("SOLUTION")
    public void toIntArray()
    {
        int[] ints = Interval.oneTo(5).toIntArray();
        int[] expectedIntArray = {1, 2, 3, 4, 5};
        Assertions.assertArrayEquals(expectedIntArray, ints);
    }

    @Test
    @Tag("SOLUTION")
    public void intervalToString()
    {
        String string = Interval.oneTo(5).toString();
        String expectedString = "Interval from: 1 to: 5 step: 1 size: 5";
        Assertions.assertEquals(expectedString, string);
    }

    @Test
    @Tag("SOLUTION")
    public void toList()
    {
        List<Integer> list = Interval.oneTo(5).toList();
        var expectedList = List.of(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedList, list);
    }

    @Test
    @Tag("SOLUTION")
    public void streamCollectToList()
    {
        Stream<Integer> stream = Interval.oneTo(5).stream();
        var expectedList2 = List.of(1, 2, 3, 4, 5);
        List<Integer> streamToList = stream.collect(Collectors.toList());
        Assertions.assertEquals(expectedList2, streamToList);
    }

    @Test
    @Tag("SOLUTION")
    public void toSet()
    {
        Set<Integer> set = Interval.oneTo(5).toSet();
        var expectedSet = Set.of(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSet, set);
    }

    @Test
    @Tag("SOLUTION")
    public void toMap()
    {
        Map<Integer, Integer> map = Interval.oneTo(5).toMap(k -> k, v -> v);
        var expectedMap = Map.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        Assertions.assertEquals(expectedMap, map);
    }

    @Test
    @Tag("SOLUTION")
    public void toSortedMap()
    {
        SortedMap<Integer, Integer> sortedMap = Interval.oneTo(5).toSortedMap(Comparator.reverseOrder(), k -> k, v -> v);
        var expectedTreeMap = new TreeMap<Integer, Integer>(Comparator.reverseOrder());
        expectedTreeMap.putAll(Map.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5));
        Assertions.assertEquals(expectedTreeMap, sortedMap);
    }

    @Test
    @Tag("SOLUTION")
    public void toMutableList()
    {
        // Mutable Collections
        MutableList<Integer> mutableList = Interval.oneTo(5).toList();
        var expectedList = Lists.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedList, mutableList);
    }

    @Test
    @Tag("SOLUTION")
    public void toMutableSet()
    {
        MutableSet<Integer> mutableSet = Interval.oneTo(5).toSet();
        var expectedSet = Sets.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSet, mutableSet);
    }

    @Test
    @Tag("SOLUTION")
    public void toMutableBag()
    {
        MutableBag<Integer> mutableBag = Interval.oneTo(5).toBag();
        var expectedBag = Bags.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedBag, mutableBag);
    }

    @Test
    @Tag("SOLUTION")
    public void toMutableSortedList()
    {
        MutableList<Integer> mutableSortedList = Interval.oneTo(5).toSortedList();
        var expectedSortedList = Lists.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedList, mutableSortedList);
    }

    @Test
    @Tag("SOLUTION")
    public void toMutableSortedSet()
    {
        MutableSortedSet<Integer> mutableSortedSet = Interval.oneTo(5).toSortedSet();
        var expectedSortedSet = SortedSets.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedSet, mutableSortedSet);
    }

    @Test
    @Tag("SOLUTION")
    public void toMutableSortedBag()
    {
        MutableSortedBag<Integer> mutableSortedBag = Interval.oneTo(5).toSortedBag();
        var expectedSortedBag = SortedBags.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedBag, mutableSortedBag);
    }

    @Test
    @Tag("SOLUTION")
    public void toMutableMap()
    {
        MutableMap<Integer, Integer> mutableMap = Interval.oneTo(5).toMap(k -> k, v -> v);
        var expectedMap = Maps.mutable.with(1, 1, 2, 2, 3, 3, 4, 4).withKeyValue(5, 5);
        Assertions.assertEquals(expectedMap, mutableMap);
    }

    @Test
    @Tag("SOLUTION")
    public void toMutableSortedMap()
    {
        MutableSortedMap<Integer, Integer> mutableSortedMap = Interval.oneTo(5).toSortedMap(Comparator.reverseOrder(), k -> k, v -> v);
        var expectedSortedMap = SortedMaps.mutable.with(1, 1, 2, 2, 3, 3, 4, 4).withKeyValue(5, 5);
        Assertions.assertEquals(expectedSortedMap, mutableSortedMap);
    }

    @Test
    @Tag("SOLUTION")
    public void toMutableBiMap()
    {
        MutableBiMap<Integer, Integer> mutableBiMap = Interval.oneTo(5).toBiMap(k -> k, v -> v);
        var expectedBiMap = BiMaps.mutable.with(1, 1, 2, 2, 3, 3, 4, 4).withKeyValue(5, 5);
        Assertions.assertEquals(expectedBiMap, mutableBiMap);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableList()
    {
        ImmutableList<Integer> immutableList = Interval.oneTo(5).toImmutableList();
        var expectedList = Lists.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedList, immutableList);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableSet()
    {
        ImmutableSet<Integer> immutableSet = Interval.oneTo(5).toImmutableSet();
        var expectedSet = Sets.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSet, immutableSet);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableBag()
    {
        ImmutableBag<Integer> immutableBag = Interval.oneTo(5).toImmutableBag();
        var expectedBag = Bags.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedBag, immutableBag);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableSortedList()
    {
        ImmutableList<Integer> immutableSortedList = Interval.oneTo(5).toImmutableSortedList();
        var expectedSortedList = Lists.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedList, immutableSortedList);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableSortedSet()
    {
        ImmutableSortedSet<Integer> immutableSortedSet = Interval.oneTo(5).toImmutableSortedSet();
        var expectedSortedSet = SortedSets.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedSet, immutableSortedSet);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableSortedBag()
    {
        ImmutableSortedBag<Integer> immutableSortedBag = Interval.oneTo(5).toImmutableSortedBag();
        var expectedSortedBag = SortedBags.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedBag, immutableSortedBag);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableMap()
    {
        ImmutableMap<Integer, Integer> immutableMap = Interval.oneTo(5).toImmutableMap(k -> k, v -> v);
        var expectedMap = Maps.immutable.with(1, 1, 2, 2, 3, 3, 4, 4).newWithKeyValue(5, 5);
        Assertions.assertEquals(expectedMap, immutableMap);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableBiMap()
    {
        ImmutableBiMap<Integer, Integer> immutableBiMap = Interval.oneTo(5).toImmutableBiMap(k -> k, v -> v);
        var expectedBiMap = BiMaps.immutable.with(1, 1, 2, 2, 3, 3, 4, 4).newWithKeyValue(5, 5);
        Assertions.assertEquals(expectedBiMap, immutableBiMap);
    }
}
