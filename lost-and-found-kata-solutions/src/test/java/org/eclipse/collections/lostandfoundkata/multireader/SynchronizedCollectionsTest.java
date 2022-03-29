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
    @Test
    @Tag("SOLUTION")
    public void createEmptyJDKSynchronized()
    {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        this.assertThreadSafe(list::add, list::size);

        Set<Integer> set = Collections.synchronizedSet(new HashSet<>());
        this.assertThreadSafe(set::add, set::size);

        SortedSet<Integer> sortedSet = Collections.synchronizedSortedSet(new TreeSet<>());
        this.assertThreadSafe(sortedSet::add, sortedSet::size);

        Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());
        this.assertThreadSafe(integer -> map.put(integer, integer), map::size);

        SortedMap<Integer, Integer> sortedMap = Collections.synchronizedSortedMap(new TreeMap<>());
        this.assertThreadSafe(integer -> sortedMap.put(integer, integer), sortedMap::size);
    }

    @Test
    @Tag("SOLUTION")
    public void createEmptyECSynchronized()
    {
        MutableList<Integer> list = Lists.mutable.<Integer>empty().asSynchronized();
        this.assertThreadSafe(list::add, list::size);

        MutableSet<Integer> set = Sets.mutable.<Integer>empty().asSynchronized();
        this.assertThreadSafe(set::add, set::size);

        MutableSortedSet<Integer> sortedSet = SortedSets.mutable.<Integer>empty().asSynchronized();
        this.assertThreadSafe(sortedSet::add, sortedSet::size);

        MutableMap<Integer, Integer> map = Maps.mutable.<Integer, Integer>empty().asSynchronized();
        this.assertThreadSafe(integer -> map.put(integer, integer), map::size);

        MutableSortedMap<Integer, Integer> sortedMap = SortedMaps.mutable.<Integer, Integer>empty().asSynchronized();
        this.assertThreadSafe(integer -> sortedMap.put(integer, integer), sortedMap::size);
    }

    @Test
    @Tag("SOLUTION")
    public void safeHiddenIteratorSynchronized()
    {
        List<String> allStrings = Collections.synchronizedList(new ArrayList<>());
        Set<String> uniqueStrings = Collections.synchronizedSet(new HashSet<>());
        List<String> progressStrings = Collections.synchronizedList(new ArrayList<>());

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

        ImmutableSet<String> expected =
                Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(expected, uniqueStrings);
    }

    @Test
    @Tag("SOLUTION")
    public void safeAtomicHiddenIteratorSynchronized()
    {
        List<String> allStrings = Collections.synchronizedList(new ArrayList<>());
        Set<String> uniqueStrings = Collections.synchronizedSet(new HashSet<>());
        List<String> progressStrings = Collections.synchronizedList(new ArrayList<>());

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

        ImmutableSet<String> expected =
                Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(expected, uniqueStrings);
    }

    @Test
    @Tag("SOLUTION")
    public void safeInternalIteratorSynchronized()
    {
        MutableList<String> allStrings = Lists.mutable.<String>empty().asSynchronized();
        MutableSet<String> uniqueStrings = Sets.mutable.<String>empty().asSynchronized();
        MutableList<String> progressStrings = Lists.mutable.<String>empty().asSynchronized();

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

        ImmutableSet<String> expected =
                Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
    }

    @Test
    @Tag("SOLUTION")
    public void safeAsLazySynchronized()
    {
        MutableList<String> allStrings = Lists.mutable.<String>empty().asSynchronized();
        MutableSet<String> uniqueStrings = Sets.mutable.<String>empty().asSynchronized();
        MutableList<String> progressStrings = Lists.mutable.<String>empty().asSynchronized();

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

    @Test
    @Tag("SOLUTION")
    public void safeAsParallelSynchronized()
    {
        MutableList<String> allStrings = Lists.mutable.<String>empty().asSynchronized();
        MutableSet<String> uniqueStrings = Sets.mutable.<String>empty().asSynchronized();
        MutableList<String> progressStrings = Lists.mutable.<String>empty().asSynchronized();

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
