/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.multireader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.SortedMaps;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.list.Interval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SynchronizedCollectionsTest extends AbstractThreadSafeCollectionsTest
{
    /**
     * {@link Collections#synchronizedList(List)} <br>
     * {@link Collections#synchronizedSet(Set)} <br>
     * {@link Collections#synchronizedSortedSet(SortedSet)} <br>
     * {@link Collections#synchronizedMap(Map)} <br>
     * {@link Collections#synchronizedSortedMap(SortedMap)} <br>
     */
    @Test
    public void createEmptyJDKSynchronized()
    {
        // Wrap the list in a synchronizedList
        List<Integer> list = new ArrayList<>();
        this.assertThreadSafe(list::add, list::size);

        // Wrap the set in a synchronizedSet
        Set<Integer> set = new HashSet<>();
        this.assertThreadSafe(set::add, set::size);

        // Wrap the sortedSet in a synchronizedSortedSet
        SortedSet<Integer> sortedSet = new TreeSet<>();
        this.assertThreadSafe(sortedSet::add, sortedSet::size);

        // Wrap the map in a synchronizedMap
        Map<Integer, Integer> map = new HashMap<>();
        this.assertThreadSafe(integer -> map.put(integer, integer), map::size);

        // Wrap the map in a synchronizedSortedMap
        SortedMap<Integer, Integer> sortedMap = new TreeMap<>();
        this.assertThreadSafe(integer -> sortedMap.put(integer, integer), sortedMap::size);
    }

    /**
     * {@link MutableList#asSynchronized()} <br>
     * {@link MutableSet#asSynchronized()} <br>
     * {@link MutableSortedSet#asSynchronized()} <br>
     * {@link MutableMap#asSynchronized()} <br>
     * {@link MutableSortedMap#asSynchronized()} <br>
     */
    @Test
    public void createEmptyECSynchronized()
    {
        // Convert the list to a synchronizedList by calling asSynchronized
        MutableList<Integer> list = Lists.mutable.<Integer>empty();
        this.assertThreadSafe(list::add, list::size);

        // Convert the set to a synchronizedSet by calling asSynchronized
        MutableSet<Integer> set = Sets.mutable.<Integer>empty();
        this.assertThreadSafe(set::add, set::size);

        // Convert the list to a synchronizedSorted by calling asSynchronized
        MutableSortedSet<Integer> sortedSet = SortedSets.mutable.<Integer>empty();
        this.assertThreadSafe(sortedSet::add, sortedSet::size);

        // Convert the list to a synchronizedMap by calling asSynchronized
        MutableMap<Integer, Integer> map = Maps.mutable.<Integer, Integer>empty();
        this.assertThreadSafe(integer -> map.put(integer, integer), map::size);

        // Convert the list to a synchronizedSortedMap by calling asSynchronized
        MutableSortedMap<Integer, Integer> sortedMap = SortedMaps.mutable.<Integer, Integer>empty();
        this.assertThreadSafe(integer -> sortedMap.put(integer, integer), sortedMap::size);
    }

    /**
     * {@link Collections#synchronizedList(List)} <br>
     * {@link Collections#synchronizedSet(Set)} <br>
     */
    @Test
    public void safeHiddenIteratorSynchronized()
    {
        // Wrap in a synchronizedList
        List<String> allStrings = new ArrayList<>();
        // Wrap in a synchronizedSet
        Set<String> uniqueStrings = new HashSet<>();
        // Wrap in a synchronizedList
        List<String> progressStrings = new ArrayList<>();

        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(run -> {
                    Random random = new Random(run);
                    List<String> randomIntStrings = IntStream.rangeClosed(1, 5)
                            .map(randomValue -> random.nextInt(11))
                            .mapToObj(Integer::toString)
                            .collect(Collectors.toList());
                    uniqueStrings.addAll(randomIntStrings);
                    allStrings.addAll(randomIntStrings);
                    // client-side lock fixes the problem
                    synchronized (allStrings)
                    {
                        progressStrings.add("run: " + run + " : " + String.join("", allStrings));
                    }
                });

        var expected = Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(expected, uniqueStrings);
    }

    /**
     * {@link Collections#synchronizedList(List)} <br>
     * {@link Collections#synchronizedSet(Set)} <br>
     */
    @Test
    public void safeAtomicHiddenIteratorSynchronized()
    {
        // Wrap in a synchronizedList
        List<String> allStrings = new ArrayList<>();
        // Wrap in a synchronizedSet
        Set<String> uniqueStrings = new HashSet<>();
        // Wrap in a synchronizedList
        List<String> progressStrings = new ArrayList<>();

        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(run -> {
                    Random random = new Random(run);
                    List<String> randomIntStrings = IntStream.rangeClosed(1, 5)
                            .map(randomValue -> random.nextInt(11))
                            .mapToObj(Integer::toString)
                            .collect(Collectors.toList());
                    uniqueStrings.addAll(randomIntStrings);
                    // client-side lock and multiple operations in lock produce
                    // more consistent results
                    synchronized (allStrings)
                    {
                        allStrings.addAll(randomIntStrings);
                        progressStrings.add("run: " + run + " : " + String.join("", allStrings));
                    }
                });

        var expected = Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(expected, uniqueStrings);
    }

    /**
     * {@link MutableList#asSynchronized()} <br>
     * {@link MutableSet#asSynchronized()} <br>
     */
    @Test
    public void safeInternalIteratorSynchronized()
    {
        // Make allStrings synchronized
        MutableList<String> allStrings = Lists.mutable.<String>empty();
        // Make uniqueStrings synchronized
        MutableSet<String> uniqueStrings = Sets.mutable.<String>empty();
        // Make progressStrings synchronized
        MutableList<String> progressStrings = Lists.mutable.<String>empty();

        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(run -> {
                    Random random = new Random(run);
                    List<String> randomIntStrings = IntStream.rangeClosed(1, 5)
                            .map(randomValue -> random.nextInt(11))
                            .mapToObj(Integer::toString)
                            .collect(Collectors.toList());
                    uniqueStrings.addAll(randomIntStrings);
                    allStrings.addAll(randomIntStrings);
                    // makeString on SynchronizedMutableList takes a lock
                    progressStrings.add("run: " + run + " : " + allStrings.makeString(""));
                });

        var expected = Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(expected, uniqueStrings);
    }

    /**
     * {@link MutableList#asSynchronized()} <br>
     * {@link MutableSet#asSynchronized()} <br>
     */
    @Test
    public void safeAsLazySynchronized()
    {
        // Make allStrings synchronized
        MutableList<String> allStrings = Lists.mutable.<String>empty();
        // Make uniqueStrings synchronized
        MutableSet<String> uniqueStrings = Sets.mutable.<String>empty();
        // Make progressStrings synchronized
        MutableList<String> progressStrings = Lists.mutable.<String>empty();

        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(run -> {
                    Random random = new Random(run);
                    List<String> randomIntStrings = IntStream.rangeClosed(1, 5)
                            .map(randomValue -> random.nextInt(11))
                            .mapToObj(Integer::toString)
                            .collect(Collectors.toList());
                    uniqueStrings.addAll(randomIntStrings);
                    allStrings.addAll(randomIntStrings);
                    // asLazy on SynchronizedMutableList uses internal iterators on the source collection
                    // which in this case will take locks when calling makeString
                    String joinedAllStrings = allStrings.asLazy().makeString("");
                    progressStrings.add("run: " + run + " : " + joinedAllStrings);
                });

        ImmutableSet<String> expected =
                Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(expected, uniqueStrings);
    }

    /**
     * {@link MutableList#asSynchronized()} <br>
     * {@link MutableSet#asSynchronized()} <br>
     */
    @Test
    public void safeAsParallelSynchronized()
    {
        // Make allStrings synchronized
        MutableList<String> allStrings = Lists.mutable.<String>empty();
        // Make uniqueStrings synchronized
        MutableSet<String> uniqueStrings = Sets.mutable.<String>empty();
        // Make progressStrings synchronized
        MutableList<String> progressStrings = Lists.mutable.<String>empty();

        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(run -> {
                    Random random = new Random(run);
                    List<String> randomIntStrings = IntStream.rangeClosed(1, 5)
                            .map(randomValue -> random.nextInt(11))
                            .mapToObj(Integer::toString)
                            .collect(Collectors.toList());
                    uniqueStrings.addAll(randomIntStrings);
                    allStrings.addAll(randomIntStrings);
                    // asLazy on SynchronizedMutableList uses internal iterators on the source collection
                    // which in this case will take locks when calling makeString
                    String joinedAllStrings =
                            allStrings.asParallel(Executors.newFixedThreadPool(2), 1).makeString("");
                    progressStrings.add("run: " + run + " : " + joinedAllStrings);
                });

        ImmutableSet<String> expected =
                Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(expected, uniqueStrings);
    }
}
