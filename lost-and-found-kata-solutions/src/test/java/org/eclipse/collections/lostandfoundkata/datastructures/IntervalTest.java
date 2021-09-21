/*
 * Copyright (c) 2021 The Bank of New York Mellon.
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
    public void creatingForward()
    {
        Interval intervalOneToTen = Interval.oneTo(10);
        var streamOneToTenToList = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamOneToTenToList, intervalOneToTen);

        Interval intervalZeroToNine = Interval.zeroTo(9);
        var streamZeroToNineToList = IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamZeroToNineToList, intervalZeroToNine);

        Interval intervalEvensFromOneToTen = Interval.evensFromTo(1, 10);
        var streamEvensFromOneToTenToList =
                IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamEvensFromOneToTenToList, intervalEvensFromOneToTen);

        Interval intervalOddsFromZeroToNine = Interval.oddsFromTo(0, 9);
        var streamOddsFromZeroToNineToList =
                IntStream.rangeClosed(0, 9).filter(i -> i % 2 != 0).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamOddsFromZeroToNineToList, intervalOddsFromZeroToNine);

        Interval intervalFromOneToTenByThree = Interval.oneToBy(10, 3);
        var listFromOneToTenByThree = List.of(1, 4, 7, 10);
        Assertions.assertEquals(listFromOneToTenByThree, intervalFromOneToTenByThree);

        Interval intervalZeroToNineByThree = Interval.zeroToBy(9, 3);
        var listFromZeroToNineByThree = List.of(0, 3, 6, 9);
        Assertions.assertEquals(listFromZeroToNineByThree, intervalZeroToNineByThree);
    }

    @Test
    @Tag("SOLUTION")
    public void creatingReverse()
    {
        Interval intervalFromTenToOne = Interval.fromTo(10, 1);
        var streamOneToTenToReverseOrderList =
                IntStream.rangeClosed(1, 10).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Assertions.assertEquals(streamOneToTenToReverseOrderList, intervalFromTenToOne);

        Interval intervalFromNineToZero = Interval.fromTo(9, 0);
        var streamZeroToNineToReverseOrderList =
                IntStream.rangeClosed(0, 9).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Assertions.assertEquals(streamZeroToNineToReverseOrderList, intervalFromNineToZero);

        Interval intervalEvensFromTenToOne = Interval.evensFromTo(10, 1);
        var streamEvensOneToTenToReverseOrderList =
                IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0)
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList());
        Assertions.assertEquals(streamEvensOneToTenToReverseOrderList, intervalEvensFromTenToOne);

        Interval intervalOddsFromNineToZero = Interval.oddsFromTo(9, 0);
        var streamOddsZeroToNineToReversOrderList =
                IntStream.rangeClosed(0, 9).filter(i -> i % 2 != 0)
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList());
        Assertions.assertEquals(streamOddsZeroToNineToReversOrderList, intervalOddsFromNineToZero);

        Interval intervalFromTenToOneByNegativeThree = Interval.fromToBy(10, 1, -3);
        var reverseListTenToOneByThree = List.of(10, 7, 4, 1);
        Assertions.assertEquals(reverseListTenToOneByThree, intervalFromTenToOneByNegativeThree);

        var reverseListNineToZeroByThree = List.of(9, 6, 3, 0);
        Assertions.assertEquals(reverseListNineToZeroByThree, Interval.fromToBy(9, 0, -3));
    }

    @Test
    @Tag("SOLUTION")
    public void converterMethodsToJDKTypes()
    {
        Interval integers = Interval.oneTo(5);

        // JDK Collection Types
        List<Integer> list = integers.toList();
        var expectedList = List.of(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedList, list);

        Set<Integer> set = integers.toSet();
        var expectedSet = Set.of(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSet, set);

        Map<Integer, Integer> map = integers.toMap(k -> k, v -> v);
        var expectedMap = Map.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        Assertions.assertEquals(expectedMap, map);

        Map<Integer, Integer> sortedMap = integers.toSortedMap(Comparator.reverseOrder(), k -> k, v -> v);
        var expectedTreeMap = new TreeMap<Integer, Integer>(Comparator.reverseOrder());
        expectedTreeMap.putAll(map);
        Assertions.assertEquals(expectedTreeMap, sortedMap);

        Integer[] array = integers.toArray();
        Integer[] expectedArray = {1, 2, 3, 4, 5};
        Assertions.assertArrayEquals(expectedArray, array);

        String string = integers.toString();
        String expectedString = "Interval from: 1 to: 5 step: 1 size: 5";
        Assertions.assertEquals(expectedString, string);

        int[] ints = integers.toIntArray();
        int[] expectedIntArray = {1, 2, 3, 4, 5};
        Assertions.assertArrayEquals(expectedIntArray, ints);

        Stream<Integer> stream = integers.stream();
        List<Integer> streamToList = stream.collect(Collectors.toList());
        Assertions.assertEquals(expectedList, streamToList);
    }

    @Test
    @Tag("SOLUTION")
    public void converterMethodsToMutableECTypes()
    {
        Interval integers = Interval.oneTo(5);

        // Mutable Collections
        MutableList<Integer> mutableList = integers.toList();
        var expectedList = Lists.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedList, mutableList);

        MutableSet<Integer> mutableSet = integers.toSet();
        var expectedSet = Sets.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSet, mutableSet);

        MutableBag<Integer> mutableBag = integers.toBag();
        var expectedBag = Bags.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedBag, mutableBag);

        MutableList<Integer> mutableSortedList = integers.toSortedList();
        var expectedSortedList = Lists.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedList, mutableSortedList);

        MutableSortedSet<Integer> mutableSortedSet = integers.toSortedSet();
        var expectedSortedSet = SortedSets.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedSet, mutableSortedSet);

        MutableSortedBag<Integer> mutableSortedBag = integers.toSortedBag();
        var expectedSortedBag = SortedBags.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedBag, mutableSortedBag);

        MutableMap<Integer, Integer> mutableMap = integers.toMap(k -> k, v -> v);
        var expectedMap = Maps.mutable.with(1, 1, 2, 2, 3, 3, 4, 4).withKeyValue(5, 5);
        Assertions.assertEquals(expectedMap, mutableMap);

        MutableSortedMap<Integer, Integer> mutableSortedMap = integers.toSortedMap(Comparator.reverseOrder(), k -> k, v -> v);
        var expectedSortedMap = SortedMaps.mutable.with(1, 1, 2, 2, 3, 3, 4, 4).withKeyValue(5, 5);
        Assertions.assertEquals(expectedSortedMap, mutableSortedMap);

        MutableBiMap<Integer, Integer> mutableBiMap = integers.toBiMap(k -> k, v -> v);
        var expectedBiMap = BiMaps.mutable.with(1, 1, 2, 2, 3, 3, 4, 4).withKeyValue(5, 5);
        Assertions.assertEquals(expectedBiMap, mutableBiMap);
    }

    @Test
    @Tag("SOLUTION")
    public void converterMethodsToImmutableECTypes()
    {
        Interval integers = Interval.oneTo(5);

        // Immutable Collections
        ImmutableList<Integer> immutableList = integers.toImmutableList();
        var expectedList = Lists.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedList, immutableList);

        ImmutableSet<Integer> immutableSet = integers.toImmutableSet();
        var expectedSet = Sets.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSet, immutableSet);

        ImmutableBag<Integer> immutableBag = integers.toImmutableBag();
        var expectedBag = Bags.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedBag, immutableBag);

        ImmutableList<Integer> immutableSortedList = integers.toImmutableSortedList();
        var expectedSortedList = Lists.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedList, immutableSortedList);

        ImmutableSortedSet<Integer> immutableSortedSet = integers.toImmutableSortedSet();
        var expectedSortedSet = SortedSets.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedSet, immutableSortedSet);

        ImmutableSortedBag<Integer> immutableSortedBag = integers.toImmutableSortedBag();
        var expectedSortedBag = SortedBags.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedBag, immutableSortedBag);

        ImmutableMap<Integer, Integer> immutableMap = integers.toImmutableMap(k -> k, v -> v);
        var expectedMap = Maps.immutable.with(1, 1, 2, 2, 3, 3, 4, 4).newWithKeyValue(5, 5);
        Assertions.assertEquals(expectedMap, immutableMap);

        ImmutableBiMap<Integer, Integer> immutableBiMap = integers.toImmutableBiMap(k -> k, v -> v);
        var expectedBiMap = BiMaps.immutable.with(1, 1, 2, 2, 3, 3, 4, 4).newWithKeyValue(5, 5);
        Assertions.assertEquals(expectedBiMap, immutableBiMap);
    }
}
