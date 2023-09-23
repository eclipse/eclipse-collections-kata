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

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.collections.api.bag.MultiReaderBag;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.list.MultiReaderList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.partition.list.PartitionList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MultiReaderSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.Interval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class MultiReaderCollectionsTest extends AbstractThreadSafeCollectionsTest
{
    public static final Predicate<Integer> IS_EVEN = each -> 0 == each.intValue() % 2;

    @Test
    @Tag("SOLUTION")
    public void empty()
    {
        MultiReaderList<Integer> list = Lists.multiReader.empty();
        this.assertThreadSafe(list::add, list::size);

        MultiReaderSet<Integer> set = Sets.multiReader.empty();
        this.assertThreadSafe(set::add, set::size);

        MultiReaderBag<Integer> bag = Bags.multiReader.empty();
        this.assertThreadSafe(bag::add, bag::size);
    }

    @Test
    @Tag("SOLUTION")
    public void createWith()
    {
        MultiReaderList<Integer> list = Lists.multiReader.with(1, 2, 3, 4, 5);
        list.withReadLockAndDelegate(delegate ->
                Assertions.assertEquals(Interval.oneTo(5), delegate));

        MultiReaderSet<Integer> set = Sets.multiReader.with(1, 2, 3, 4, 5);
        set.withReadLockAndDelegate(delegate ->
                Assertions.assertEquals(Interval.oneTo(5).toSet(), delegate));

        MultiReaderBag<Integer> bag = Bags.multiReader.with(1, 2, 3, 4, 5);
        bag.withReadLockAndDelegate(delegate ->
                Assertions.assertEquals(Interval.oneTo(5).toBag(), delegate));
    }

    @Test
    @Tag("SOLUTION")
    public void safeWithReadLockAndIteratorMultiReader()
    {
        MultiReaderList<String> allStrings = Lists.multiReader.empty();
        MutableSet<String> uniqueStrings = Sets.multiReader.empty();
        MutableList<String> progressStrings = Lists.multiReader.empty();

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
                    // Client-side read lock makes iterator usage safe
                    allStrings.withReadLockAndDelegate(
                            delegate -> progressStrings.add("run: " + run + " : " + String.join("", delegate)));
                });

        var zeroToTenStrings = Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(zeroToTenStrings, uniqueStrings);
    }

    @Test
    @Tag("SOLUTION")
    public void safeWithWriteLockAndIteratorMultiReader()
    {
        MultiReaderList<String> allStrings = Lists.multiReader.empty();
        MutableSet<String> uniqueStrings = Sets.multiReader.empty();
        MutableList<String> progressStrings = Lists.multiReader.empty();

        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(run -> {
                    Random random = new Random(run);
                    List<String> randomIntStrings = IntStream.rangeClosed(1, 5)
                            .map(randomValue -> random.nextInt(11))
                            .mapToObj(Integer::toString)
                            .collect(Collectors.toList());
                    uniqueStrings.addAll(randomIntStrings);
                    // client-side write lock and multiple operations in lock produce
                    // more consistent results
                    allStrings.withWriteLockAndDelegate(delegate -> {
                        delegate.addAll(randomIntStrings);
                        progressStrings.add("run: " + run + " : " + String.join("", delegate));
                    });
                });

        var zeroToTenStrings = Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(zeroToTenStrings, uniqueStrings);
    }

    @Test
    @Tag("SOLUTION")
    public void safeNoHiddenIteratorMultiReader()
    {
        MutableList<String> allStrings = Lists.multiReader.empty();
        MutableSet<String> uniqueStrings = Sets.multiReader.empty();
        MutableList<String> progressStrings = Lists.multiReader.empty();

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
                    // makeString on MultiReaderList takes a read lock
                    progressStrings.add("run: " + run + " : " + allStrings.makeString(""));
                });

        var zeroToTenStrings = Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(zeroToTenStrings, uniqueStrings);
    }

    @Test
    @Tag("SOLUTION")
    @Disabled
    public void multiReaderListFiltering()
    {
        MultiReaderList<Integer> list =
                Lists.multiReader.with(1, 2, 3, 4, 5);

        // equals has a hidden iterator so use read lock
        list.withReadLockAndDelegate(delegate ->
                Assertions.assertEquals(Interval.oneTo(5), delegate));

        Predicate<Integer> isEven = each -> each % 2 == 0;
        MutableList<Integer> evens = list.select(isEven);
        MutableList<Integer> odds = list.reject(isEven);
        PartitionList<Integer> partition = list.partition(isEven);

        Assertions.assertEquals(List.of(2, 4), evens);
        Assertions.assertEquals(List.of(1, 3, 5), odds);
        Assertions.assertEquals(evens, partition.getSelected());
        Assertions.assertEquals(odds, partition.getRejected());
    }

    @Test
    @Tag("SOLUTION")
    public void multiReaderListHiddenIteratorPatterns()
    {
        MultiReaderList<Integer> list =
                Lists.multiReader.with(1, 2, 3, 4, 5);

        // equals has a hidden iterator so use read lock
        list.withReadLockAndDelegate(delegate ->
                Assertions.assertEquals(Interval.oneTo(5), delegate));

        // iterator throws and remove requires a write lock
        list.withWriteLockAndDelegate(delegate -> {
            Iterator<Integer> iterator = delegate.iterator();
            iterator.next();
            iterator.remove();
            iterator.next();
            iterator.remove();
        });

        // equals has a hidden iterator so use read lock
        list.withReadLockAndDelegate(delegate ->
                Assertions.assertEquals(List.of(3, 4, 5), delegate));

        // stream throws so use read lock
        List<Integer> four = List.of(4);
        list.withReadLockAndDelegate(delegate ->
                Assertions.assertEquals(
                        four,
                        delegate.stream().filter(IS_EVEN).collect(Collectors.toList())));
    }

    @Test
    @Tag("SOLUTION")
    public void multiReaderListSafeLazyPatterns()
    {
        MultiReaderList<Integer> list =
                Lists.multiReader.with(1, 2, 3, 4, 5);

        // asLazy uses internal iterators so doesn't need a lock
        Assertions.assertEquals(
                Interval.evensFromTo(1, 5),
                list.asLazy().select(IS_EVEN).toList());

        // asParallel uses internal iterators so doesn't need a lock
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Assertions.assertEquals(
                Interval.evensFromTo(1, 5),
                list.asParallel(executorService, 1).select(IS_EVEN).toList());
    }

    @Test
    @Tag("SOLUTION")
    public void makeString()
    {
        MultiReaderList<String> strings =
                Lists.multiReader.with("1", "2", "3", "4", "5");

        // MultiReaderList safe use of makeString
        String string = strings.makeString("");
        Assertions.assertEquals("12345", string);
    }

    @Test
    @Tag("SOLUTION")
    public void asLazyMakeString()
    {
        MultiReaderList<String> strings =
                Lists.multiReader.with("1", "2", "3", "4", "5");

        // MultiReaderList safe use of makeString
        String string = strings.asLazy().makeString("");
        Assertions.assertEquals("12345", string);
    }

    @Test
    @Tag("SOLUTION")
    public void asParallelMakeString()
    {
        MultiReaderList<String> strings =
                Lists.multiReader.with("1", "2", "3", "4", "5");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        String string = strings.asParallel(executor, 1).makeString("");
        Assertions.assertEquals("12345", string);
    }

    @Test
    @Tag("SOLUTION")
    public void streamCollectorsJoiningWithReadLock()
    {
        MultiReaderList<String> strings =
                Lists.multiReader.with("1", "2", "3", "4", "5");

        // MultiReaderList safe use of stream
        strings.withReadLockAndDelegate(delegate -> {
            String string = delegate.stream().collect(Collectors.joining(""));
            Assertions.assertEquals("12345", string);
        });
    }

    @Test
    @Tag("SOLUTION")
    public void stringJoinWithReadLock()
    {
        MultiReaderList<String> strings =
                Lists.multiReader.with("1", "2", "3", "4", "5");

        // MultiReaderList safe use of stream
        strings.withReadLockAndDelegate(delegate -> {
            String string = String.join("", delegate);

            Assertions.assertEquals("12345", string);
        });
    }
}
