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

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.bag.MultiReaderBag;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.MultiReaderList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.api.partition.list.PartitionList;
import org.eclipse.collections.api.set.MultiReaderSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.collector.Collectors2;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.Interval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see MultiReaderList
 * @see MultiReaderSet
 * @see MultiReaderBag
 * @see LazyIterable
 * @see ParallelListIterable
 */
public class MultiReaderCollectionsTest extends AbstractThreadSafeCollectionsTest
{
    public static final Predicate<Integer> IS_EVEN = each -> 0 == each.intValue() % 2;

    /**
     * {@link org.eclipse.collections.api.factory.list.MultiReaderListFactory#empty()} <br>
     * {@link org.eclipse.collections.api.factory.set.MultiReaderSetFactory#empty()} <br>
     * {@link org.eclipse.collections.api.factory.bag.MultiReaderBagFactory#empty()} <br>
     * @see Lists
     * @see Sets
     * @see Bags
     */
    @Test
    public void empty()
    {
        // Create an empty MultiReaderList using Lists factory
        MultiReaderList<Integer> list = null;
        this.assertThreadSafe(list::add, list::size);

        // Create an empty MultiReaderSet using Lists factory
        MultiReaderSet<Integer> set = null;
        this.assertThreadSafe(set::add, set::size);

        // Create an empty MultiReaderBag using Lists factory
        MultiReaderBag<Integer> bag = null;
        this.assertThreadSafe(bag::add, bag::size);
    }

    /**
     * {@link org.eclipse.collections.api.factory.list.MultiReaderListFactory#with(Object[])} ()} <br>
     * {@link org.eclipse.collections.api.factory.set.MultiReaderSetFactory#with(Object[])} <br>
     * {@link org.eclipse.collections.api.factory.bag.MultiReaderBagFactory#with(Object[])} <br>
     * @see Lists
     * @see Sets
     * @see Bags
     */
    @Test
    public void createWith()
    {
        // Create a MultiReaderList with integers from 1 to 5 using Lists factory
        MultiReaderList<Integer> list = Lists.multiReader.empty();
        list.withReadLockAndDelegate(delegate ->
                Assertions.assertEquals(Interval.oneTo(5), delegate));

        // Create a MultiReaderSet with integers from 1 to 5 using Lists factory
        MultiReaderSet<Integer> set = Sets.multiReader.empty();
        set.withReadLockAndDelegate(delegate ->
                Assertions.assertEquals(Interval.oneTo(5).toSet(), delegate));

        // Create a MultiReaderBag with integers from 1 to 5 using Lists factory
        MultiReaderBag<Integer> bag = Bags.multiReader.empty();
        bag.withReadLockAndDelegate(delegate ->
                Assertions.assertEquals(Interval.oneTo(5).toBag(), delegate));
    }

    /**
     * {@link MultiReaderList#withReadLockAndDelegate(Procedure)}}
     */
    @Test
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
                    // Wrap statement with call to withReadLockAndDelegate
                    // Use delegate in lambda instead of allStrings
                    progressStrings.add("run: " + run + " : " + String.join("", allStrings));
                });

        var zeroToTenStrings = Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(zeroToTenStrings, uniqueStrings);
    }

    /**
     * {@link MultiReaderList#withWriteLockAndDelegate(Procedure)}}
     */
    @Test
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
                    // Wrap following two statements with call to withWriteLockAndDelegate
                    // Use delegate in lambda in place of allStrings
                    allStrings.addAll(randomIntStrings);
                    progressStrings.add("run: " + run + " : " + String.join("", allStrings));
                });

        var zeroToTenStrings = Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(zeroToTenStrings, uniqueStrings);
    }

    /**
     * {@link MutableList#makeString(String)}
     */
    @Test
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
                    // Replace String.join() with makeString() which is thread-safe to use with MultiReaderList
                    progressStrings.add("run: " + run + " : " + String.join("", allStrings));
                });

        var zeroToTenStrings = Interval.zeroTo(10).collect(Object::toString).toImmutableSet();
        Assertions.assertEquals(zeroToTenStrings, uniqueStrings);
    }

    /**
     * {@link MultiReaderList#withReadLockAndDelegate(Procedure)}}
     */
    @Test
    public void multiReaderListFiltering()
    {
        // Replace with a MultiReaderList with integers from 1 to 5
        MultiReaderList<Integer> list =
                Lists.multiReader.empty();

        // The assertEquals() method has a hidden iterator so wrap with a read-lock
        // Replace list with delegate inside of withReadLockAndDelegate()
        Assertions.assertEquals(Interval.oneTo(5), list);

        Predicate<Integer> isEven = each -> each % 2 == 0;
        // Replace stream filter code with a thread-safe direct call to select on list
        MutableList<Integer> evens = list.stream().filter(isEven).collect(Collectors2.toList());
        // Replace stream filter code with a thread-safe direct call to reject on list
        MutableList<Integer> odds = list.stream().filter(isEven.negate()).collect(Collectors2.toList());
        PartitionList<Integer> partition = list.partition(isEven);

        Assertions.assertEquals(List.of(2, 4), evens);
        Assertions.assertEquals(List.of(1, 3, 5), odds);
        Assertions.assertEquals(evens, partition.getSelected());
        Assertions.assertEquals(odds, partition.getRejected());
    }

    /**
     * {@link MultiReaderList#withReadLockAndDelegate(Procedure)}} <br>
     * {@link MultiReaderList#withWriteLockAndDelegate(Procedure)}} <br>
     */
    @Test
    public void multiReaderListHiddenIteratorPatterns()
    {
        MultiReaderList<Integer> list =
                Lists.multiReader.with(1, 2, 3, 4, 5);

        // The assertEquals() method has a hidden iterator so wrap with a read-lock
        Assertions.assertEquals(Interval.oneTo(5), list);

        // MultiReaderList throws on iterator() and iterator.remove() requires a write-lock to be taken
        // Wrap the following five statements in a write-lock
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();

        // The assertEquals() method has a hidden iterator so wrap with a read-lock
        // Wrap the following statement in a read-lock
        Assertions.assertEquals(List.of(3, 4, 5), list);

        List<Integer> four = List.of(4);
        // MultiReaderList throws on stream()
        // Wrap the following statement with a read-lock
        Assertions.assertEquals(
                four,
                list.stream().filter(IS_EVEN).collect(Collectors.toList()));
    }

    /**
     * {@link MultiReaderList#asLazy()} <br>
     * {@link LazyIterable#select(Predicate)} <br>
     * {@link LazyIterable#toList()} <br>
     * {@link MultiReaderList#asParallel(ExecutorService, int)} <br>
     * {@link ParallelListIterable#select(Predicate)} <br>
     * {@link ParallelListIterable#toList()} <br>
     */
    @Test
    public void multiReaderListSafeLazyPatterns()
    {
        MultiReaderList<Integer> list =
                Lists.multiReader.with(1, 2, 3, 4, 5);

        // The asLazy() method uses internal iterators so doesn't need a lock
        // Convert the LazyIterable below to a list using toList()
        Assertions.assertEquals(Interval.evensFromTo(1, 5),
                list.asLazy().select(IS_EVEN));

        // The asParallel() method uses internal iterators so doesn't need a lock
        // Convert the ParallelListIterable below to a list using toList()
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Assertions.assertEquals(
                Interval.evensFromTo(1, 5),
                list.asParallel(executorService, 1).select(IS_EVEN));
    }

    /**
     * {@link MultiReaderList#makeString(String)}
     */
    @Test
    public void makeString()
    {
        MultiReaderList<String> strings =
                Lists.multiReader.with("1", "2", "3", "4", "5");

        // Replace toString() with MultiReaderList thread-safe use of makeString()
        String string = strings.toString();
        Assertions.assertEquals("12345", string);
    }

    /**
     * {@link MultiReaderList#asLazy()} <br>
     * {@link LazyIterable#makeString(String)}
     */
    @Test
    public void asLazyMakeString()
    {
        MultiReaderList<String> strings =
                Lists.multiReader.with("1", "2", "3", "4", "5");

        // Replace asLazy().toString() with MultiReaderList thread-safe use of asLazy().makeString()
        String string = strings.asLazy().toString();
        Assertions.assertEquals("12345", string);
    }

    /**
     * {@link MultiReaderList#asParallel(ExecutorService, int)} <br>
     * {@link org.eclipse.collections.api.list.ParallelListIterable#makeString(String)} <br>
     */
    @Test
    public void asParallelMakeString()
    {
        MultiReaderList<String> strings =
                Lists.multiReader.with("1", "2", "3", "4", "5");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        // Replace toString() with MultiReaderList thread-safe use of asParallel().makeString
        String string = strings.asParallel(executor, 1).toString();
        Assertions.assertEquals("12345", string);
    }

    /**
     * {@link MultiReaderList#withReadLockAndDelegate(Procedure)}} <br>
     */
    @Test
    public void streamCollectorsJoiningWithReadLock()
    {
        MultiReaderList<String> strings =
                Lists.multiReader.with("1", "2", "3", "4", "5");

        // MultiReaderList throws on stream()
        // Wrap the following two statements with a read-lock
        String string = strings.stream().collect(Collectors.joining(""));
        Assertions.assertEquals("12345", string);
    }

    /**
     * {@link MultiReaderList#withReadLockAndDelegate(Procedure)}} <br>
     */
    @Test
    public void stringJoinWithReadLock()
    {
        MultiReaderList<String> strings =
                Lists.multiReader.with("1", "2", "3", "4", "5");

        // There is a hidden iterator in String.join()
        // Wrap the following two statements with a read-lock
        String string = String.join("", strings);
        Assertions.assertEquals("12345", string);
    }
}
