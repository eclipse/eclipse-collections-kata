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
import org.eclipse.collections.api.block.function.Function;
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
import org.junit.jupiter.api.Test;

/**
 * {@link Interval#oneTo(int)} <br>
 * {@link Interval#oneToBy(int, int)} <br>
 * {@link Interval#zeroTo(int)} <br>
 * {@link Interval#zeroToBy(int, int)} <br>
 * {@link Interval#evensFromTo(int, int)} <br>
 * {@link Interval#oddsFromTo(int, int)} <br>
 * {@link Interval#fromTo(int, int)} <br>
 * {@link Interval#evensFromTo(int, int)} <br>
 * {@link Interval#oddsFromTo(int, int)} <br>
 * {@link Interval#fromToBy(int, int, int)} <br>
 * {@link Interval#toList()} <br>
 * {@link Interval#toSet()} <br>
 * {@link Interval#toMap(Function, Function)} <br>
 * {@link Interval#toSortedMap(Function, Function)} <br>
 * {@link Interval#toArray()} <br>
 * {@link Interval#toString()} <br>
 * {@link Interval#toIntArray()} <br>
 * {@link Interval#stream()} <br>
 * {@link Interval#toBag()} <br>
 * {@link Interval#toSortedList()} <br>
 * {@link Interval#toSortedSet()} <br>
 * {@link Interval#toSortedBag()} <br>
 * {@link Interval#toBiMap(Function, Function)} <br>
 * {@link Interval#toImmutableList()} <br>
 * {@link Interval#toImmutableSet()} <br>
 * {@link Interval#toImmutableBag()} <br>
 * {@link Interval#toImmutableSortedList()} <br>
 * {@link Interval#toImmutableSortedSet()} <br>
 * {@link Interval#toImmutableSortedBag()} <br>
 * {@link Interval#toImmutableMap(Function, Function)} <br>
 * {@link Interval#toImmutableBiMap(Function, Function)} <br>
 *
 * @see Interval
 */
public class IntervalTest
{
    @Test
    public void oneToTen()
    {
        // Create an Interval from 1 to 10
        Interval intervalOneToTen = null;
        var streamOneToTenToList = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamOneToTenToList, intervalOneToTen);
    }

    @Test
    public void zeroToNine()
    {
        // Create an Interval from 0 to 9
        Interval intervalZeroToNine = null;
        var streamZeroToNineToList = IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamZeroToNineToList, intervalZeroToNine);
    }

    @Test
    public void evensFromOneToTen()
    {
        // Create an Interval of only evens from 1 to 10
        Interval intervalEvensFromOneToTen = null;
        var streamEvensFromOneToTenToList =
                IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamEvensFromOneToTenToList, intervalEvensFromOneToTen);
    }

    @Test
    public void oddsFromZeroToNine()
    {
        // Create an Interval of only odds from 0 to 9
        Interval intervalOddsFromZeroToNine = null;
        var streamOddsFromZeroToNineToList =
                IntStream.rangeClosed(0, 9).filter(i -> i % 2 != 0).boxed().collect(Collectors.toList());
        Assertions.assertEquals(streamOddsFromZeroToNineToList, intervalOddsFromZeroToNine);
    }

    @Test
    public void oneToTenByThree()
    {
        // Create an Interval from 1 to 10 by 3
        Interval intervalFromOneToTenByThree = null;
        var listFromOneToTenByThree = List.of(1, 4, 7, 10);
        Assertions.assertEquals(listFromOneToTenByThree, intervalFromOneToTenByThree);
    }

    @Test
    public void zeroToNineByThree()
    {
        // Create an Interval from 0 to 9 by 3
        Interval intervalZeroToNineByThree = null;
        var listFromZeroToNineByThree = List.of(0, 3, 6, 9);
        Assertions.assertEquals(listFromZeroToNineByThree, intervalZeroToNineByThree);
    }

    @Test
    public void tenToOne()
    {
        // Create an Interval from 10 to 1
        Interval intervalFromTenToOne = null;
        var streamOneToTenToReverseOrderList =
                IntStream.rangeClosed(1, 10).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Assertions.assertEquals(streamOneToTenToReverseOrderList, intervalFromTenToOne);
    }

    @Test
    public void nineToZero()
    {
        // Create an Interval from 9 to 0
        Interval intervalFromNineToZero = null;
        var streamZeroToNineToReverseOrderList =
                IntStream.rangeClosed(0, 9).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Assertions.assertEquals(streamZeroToNineToReverseOrderList, intervalFromNineToZero);
    }

    @Test
    public void evensFromTenToOne()
    {
        // Create an Interval of only evens from 10 to 1
        Interval intervalEvensFromTenToOne = null;
        var streamEvensOneToTenToReverseOrderList =
                IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0)
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList());
        Assertions.assertEquals(streamEvensOneToTenToReverseOrderList, intervalEvensFromTenToOne);
    }

    @Test
    public void oddsFromNineToZero()
    {
        // Create an Interval of only odds from 9 to 0
        Interval intervalOddsFromNineToZero = null;
        var streamOddsZeroToNineToReversOrderList =
                IntStream.rangeClosed(0, 9).filter(i -> i % 2 != 0)
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList());
        Assertions.assertEquals(streamOddsZeroToNineToReversOrderList, intervalOddsFromNineToZero);
    }

    @Test
    public void tenToOneByNegativeThree()
    {
        // Create an Interval from 10 to 1 by -3
        Interval intervalFromTenToOneByNegativeThree = null;
        var reverseListTenToOneByThree = List.of(10, 7, 4, 1);
        Assertions.assertEquals(reverseListTenToOneByThree, intervalFromTenToOneByNegativeThree);
    }

    @Test
    public void nineToZeroByNegativeThree()
    {
        // Create an Interval from 9 to 0 by -3
        Interval intervalFromNineToZeroByNegativeThree = null;
        var reverseListNineToZeroByThree = List.of(9, 6, 3, 0);
        Assertions.assertEquals(reverseListNineToZeroByThree, intervalFromNineToZeroByNegativeThree);
    }

    @Test
    public void toList()
    {
        // Convert Interval.oneTo(5) to a List
        List<Integer> list = null;
        var expectedList = List.of(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedList, list);
    }

    @Test
    public void toSet()
    {
        // Convert Interval.oneTo(5) to a Set
        Set<Integer> set = null;
        var expectedSet = Set.of(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSet, set);
    }

    @Test
    public void toMap()
    {
        // Convert Interval.oneTo(5) to a Map where keys and values are the same
        Map<Integer, Integer> map = null;
        var expectedMap = Map.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        Assertions.assertEquals(expectedMap, map);
    }

    @Test
    public void toSortedMap()
    {
        // Convert Interval.oneTo(5) to a SortedMap where keys and values are the same
        SortedMap<Integer, Integer> sortedMap = null;
        var expectedTreeMap = new TreeMap<Integer, Integer>(Comparator.reverseOrder());
        expectedTreeMap.putAll(Map.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5));
        Assertions.assertEquals(expectedTreeMap, sortedMap);
    }

    @Test
    public void toArray()
    {
        // Convert Interval.oneTo(5) to an array of Integer
        Integer[] array = null;
        Integer[] expectedArray = {1, 2, 3, 4, 5};
        Assertions.assertArrayEquals(expectedArray, array);
    }

    @Test
    public void intervalToString()
    {
        // Convert Interval.oneTo(5) to a String
        String string = null;
        String expectedString = "Interval from: 1 to: 5 step: 1 size: 5";
        Assertions.assertEquals(expectedString, string);
    }

    @Test
    public void toIntArray()
    {
        // Convert Interval.oneTo(5) to an int array
        int[] ints = null;
        int[] expectedIntArray = {1, 2, 3, 4, 5};
        Assertions.assertArrayEquals(expectedIntArray, ints);
    }

    @Test
    public void toStream()
    {
        // Convert Interval.oneTo(5) to a Stream
        Stream<Integer> stream = null;
        List<Integer> streamToList = stream.collect(Collectors.toList());
        Assertions.assertEquals(Interval.oneTo(5), streamToList);
    }

    @Test
    public void toMutableList()
    {
        // Convert Interval.oneTo(5) to a MutableList
        MutableList<Integer> mutableList = null;
        var expectedList = Lists.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedList, mutableList);
    }

    @Test
    public void toMutableSet()
    {
        // Convert Interval.oneTo(5) to a MutableSet
        MutableSet<Integer> mutableSet = null;
        var expectedSet = Sets.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSet, mutableSet);
    }

    @Test
    public void toMutableBag()
    {
        // Convert Interval.oneTo(5) to a MutableBag
        MutableBag<Integer> mutableBag = null;
        var expectedBag = Bags.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedBag, mutableBag);
    }

    @Test
    public void toMutableSortedList()
    {
        // Convert Interval.oneTo(5) to a sorted MutableList
        MutableList<Integer> mutableSortedList = null;
        var expectedSortedList = Lists.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedList, mutableSortedList);
    }

    @Test
    public void toMutableSortedSet()
    {
        // Convert Interval.oneTo(5) to a MutableSortedSet
        MutableSortedSet<Integer> mutableSortedSet = null;
        var expectedSortedSet = SortedSets.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedSet, mutableSortedSet);
    }

    @Test
    public void toMutableSortedBag()
    {
        // Convert Interval.oneTo(5) to a MutableSortedBag
        MutableSortedBag<Integer> mutableSortedBag = null;
        var expectedSortedBag = SortedBags.mutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedBag, mutableSortedBag);
    }

    @Test
    public void toMutableMap()
    {
        // Convert Interval.oneTo(5) to a MutableMap with keys and values the same
        MutableMap<Integer, Integer> mutableMap = null;
        var expectedMap = Maps.mutable.with(1, 1, 2, 2, 3, 3, 4, 4).withKeyValue(5, 5);
        Assertions.assertEquals(expectedMap, mutableMap);
    }

    @Test
    public void toMutableSortedMap()
    {
        // Convert Interval.oneTo(5) to a MutableSortedMap with keys and values the same
        MutableSortedMap<Integer, Integer> mutableSortedMap = null;
        var expectedSortedMap = SortedMaps.mutable.with(1, 1, 2, 2, 3, 3, 4, 4).withKeyValue(5, 5);
        Assertions.assertEquals(expectedSortedMap, mutableSortedMap);
    }

    @Test
    public void toMutableBiMap()
    {
        // Convert Interval.oneTo(5) to a MutableBiMap with keys and values the same
        MutableBiMap<Integer, Integer> mutableBiMap = null;
        var expectedBiMap = BiMaps.mutable.with(1, 1, 2, 2, 3, 3, 4, 4).withKeyValue(5, 5);
        Assertions.assertEquals(expectedBiMap, mutableBiMap);
    }

    @Test
    public void toImmutableList()
    {
        // Convert Interval.oneTo(5) to an ImmutableList
        ImmutableList<Integer> immutableList = null;
        var expectedList = Lists.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedList, immutableList);
    }

    @Test
    public void toImmutableSet()
    {
        // Convert Interval.oneTo(5) to an ImmutableSet
        ImmutableSet<Integer> immutableSet = null;
        var expectedSet = Sets.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSet, immutableSet);
    }

    @Test
    public void toImmutableBag()
    {
        // Convert Interval.oneTo(5) to an ImmutableBag
        ImmutableBag<Integer> immutableBag = null;
        var expectedBag = Bags.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedBag, immutableBag);
    }

    @Test
    public void toImmutableSortedList()
    {
        // Convert Interval.oneTo(5) to a sorted ImmutableList
        ImmutableList<Integer> immutableSortedList = null;
        var expectedSortedList = Lists.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedList, immutableSortedList);
    }

    @Test
    public void toImmutableSortedSet()
    {
        // Convert Interval.oneTo(5) to an ImmutableSortedSet
        ImmutableSortedSet<Integer> immutableSortedSet = null;
        var expectedSortedSet = SortedSets.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedSet, immutableSortedSet);
    }

    @Test
    public void toImmutableSortedBag()
    {
        // Convert Interval.oneTo(5) to an ImmutableSortedBag
        ImmutableSortedBag<Integer> immutableSortedBag = null;
        var expectedSortedBag = SortedBags.immutable.with(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedSortedBag, immutableSortedBag);
    }

    @Test
    public void toImmutableMap()
    {
        // Convert Interval.oneTo(5) to an ImmutableMap
        ImmutableMap<Integer, Integer> immutableMap = null;
        var expectedMap = Maps.immutable.with(1, 1, 2, 2, 3, 3, 4, 4).newWithKeyValue(5, 5);
        Assertions.assertEquals(expectedMap, immutableMap);
    }

    @Test
    public void toImmutableBiMap()
    {
        // Convert Interval.oneTo(5) to an ImmutableBiMap
        ImmutableBiMap<Integer, Integer> immutableBiMap = null;
        var expectedBiMap = BiMaps.immutable.with(1, 1, 2, 2, 3, 3, 4, 4).newWithKeyValue(5, 5);
        Assertions.assertEquals(expectedBiMap, immutableBiMap);
    }
}
