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
import org.eclipse.collections.impl.factory.Lists;
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
        Assertions.assertEquals(
                IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList()),
                Interval.oneTo(10));
        Assertions.assertEquals(
                IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toList()),
                Interval.zeroTo(9));
        Assertions.assertEquals(
                IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList()),
                Interval.evensFromTo(1, 10));
        Assertions.assertEquals(
                IntStream.rangeClosed(0, 9).filter(i -> i % 2 != 0).boxed().collect(Collectors.toList()),
                Interval.oddsFromTo(0, 9));
        Assertions.assertEquals(
                Lists.immutable.with(1, 4, 7, 10),
                Interval.oneToBy(10, 3));
        Assertions.assertEquals(
                Lists.immutable.with(0, 3, 6, 9),
                Interval.zeroToBy(9, 3));
    }

    @Test
    @Tag("SOLUTION")
    public void creatingReverse()
    {
        Assertions.assertEquals(
                IntStream.rangeClosed(1, 10).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList()),
                Interval.fromTo(10, 1));
        Assertions.assertEquals(
                IntStream.rangeClosed(0, 9).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList()),
                Interval.fromTo(9, 0));
        Assertions.assertEquals(
                IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList()),
                Interval.evensFromTo(10, 1));
        Assertions.assertEquals(
                IntStream.rangeClosed(0, 9).filter(i -> i % 2 != 0).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList()),
                Interval.oddsFromTo(9, 0));
        Assertions.assertEquals(
                Lists.immutable.with(1, 4, 7, 10).toReversed(),
                Interval.fromToBy(10, 1, -3));
        Assertions.assertEquals(
                Lists.immutable.with(0, 3, 6, 9).toReversed(),
                Interval.fromToBy(9, 0, -3));
    }

    @Test
    @Tag("SOLUTION")
    public void converterMethods()
    {
        Interval integers = Interval.oneTo(10);

        // JDK Collection Types
        List<Integer> list = integers.toList();
        Set<Integer> set = integers.toSet();
        Map<Integer, Integer> map = integers.toMap(k -> k, v -> v);
        Map<Integer, Integer> sortedMap = integers.toSortedMap(Comparator.reverseOrder(), k -> k, v -> v);
        Integer[] array = integers.toArray();
        String string = integers.toString();
        int[] ints = integers.toIntArray();
        Stream<Integer> stream = integers.stream();

        // Mutable Collections
        MutableList<Integer> mutableList = integers.toList();
        MutableSet<Integer> mutableSet = integers.toSet();
        MutableBag<Integer> mutableBag = integers.toBag();
        MutableList<Integer> mutableSortedList = integers.toSortedList();
        MutableSortedSet<Integer> mutableSortedSet = integers.toSortedSet();
        MutableSortedBag<Integer> mutableSortedBag = integers.toSortedBag();
        MutableMap<Integer, Integer> mutableMap = integers.toMap(k -> k, v -> v);
        MutableSortedMap<Integer, Integer> mutableSortedMap = integers.toSortedMap(Comparator.reverseOrder(), k -> k, v -> v);
        MutableBiMap<Integer, Integer> mutableBiMap = integers.toBiMap(k -> k, v -> v);

        // Immutable Collections
        ImmutableList<Integer> immutableList = integers.toImmutableList();
        ImmutableSet<Integer> immutableSet = integers.toImmutableSet();
        ImmutableBag<Integer> immutableBag = integers.toImmutableBag();
        ImmutableList<Integer> immutableSortedList = integers.toImmutableSortedList();
        ImmutableSortedSet<Integer> immutableSortedSet = integers.toImmutableSortedSet();
        ImmutableSortedBag<Integer> immutableSortedBag = integers.toImmutableSortedBag();
        ImmutableMap<Integer, Integer> immutableMap = integers.toImmutableMap(k -> k, v -> v);
        ImmutableBiMap<Integer, Integer> immutableBiMap = integers.toImmutableBiMap(k -> k, v -> v);
    }
}
