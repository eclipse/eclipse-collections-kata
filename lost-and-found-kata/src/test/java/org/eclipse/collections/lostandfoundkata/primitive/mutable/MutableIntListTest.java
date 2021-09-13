/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.primitive.mutable;

import java.util.IntSummaryStatistics;
import java.util.Random;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.LazyIntIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.block.function.primitive.IntToIntFunction;
import org.eclipse.collections.api.block.function.primitive.IntToObjectFunction;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.tuple.primitive.IntIntPair;
import org.eclipse.collections.impl.factory.primitive.IntBags;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class MutableIntListTest
{
    private MutableIntList list;

    @BeforeEach
    void setUp()
    {
        this.list = IntLists.mutable.with(1, 2, 3, 4, 5);
    }

    /**
     * {@link MutableIntList#asReversed()} <br>
     * {@link MutableIntList#toReversed()} <br>
     */
    @Test
    public void reversing()
    {
        // Create a reverse view of the list
        LazyIntIterable asReverseLazy = this.list.asLazy();
        Assertions.assertEquals(IntInterval.fromTo(5, 1), asReverseLazy.toList());

        // Create a reverse copy of the list
        MutableIntList toReverseList = this.list;
        Assertions.assertEquals(IntInterval.fromTo(5, 1), toReverseList);
    }

    /**
     * {@link MutableIntList#shuffleThis()} <br>
     * {@link MutableIntList#shuffleThis(Random)} <br>
     */
    @Test
    public void shuffling()
    {
        // Copy the list and shuffle
        MutableIntList shuffled = this.list.toList().shuffleThis();
        Assertions.assertNotEquals(this.list, shuffled);

        // Copy the list and shuffle with a random seed value of 1. Hint: look at the Random seed value
        MutableIntList shuffledPredictable = this.list.toList().shuffleThis(new Random(1000000L));
        Assertions.assertEquals(IntLists.immutable.with(3, 4, 2, 5, 1), shuffledPredictable);
    }

    /**
     * {@link MutableIntList#with(int)} <br>
     * {@link MutableIntList#withAll(IntIterable)} <br>
     * {@link MutableIntList#without(int)} <br>
     * {@link MutableIntList#withAll(IntIterable)} <br>
     */
    @Test
    public void withAndWithout()
    {
        // Add the values 6 and 7 to the list using with
        MutableIntList listWith = this.list;
        Assertions.assertEquals(IntInterval.oneTo(7), listWith);
        Assertions.assertSame(this.list, listWith);

        // Add the values 8, 9, 10 to the list using withAll
        MutableIntList listWithAll = this.list.withAll(IntLists.immutable.empty());
        Assertions.assertEquals(IntInterval.oneTo(10), listWithAll);
        Assertions.assertSame(this.list, listWithAll);

        // Remove the values 9, 10 from the list using without
        MutableIntList listWithout = this.list;
        Assertions.assertEquals(IntInterval.oneTo(8), listWithout);
        Assertions.assertSame(this.list, listWithout);

        // Remove the values 6, 7, 8 from the list using withoutAll
        MutableIntList listWithoutAll = this.list.withoutAll(IntLists.immutable.empty());
        Assertions.assertEquals(IntInterval.oneTo(5), listWithoutAll);
        Assertions.assertSame(this.list, listWithoutAll);
    }

    /**
     * {@link MutableIntList#reverseThis()} <br>
     * {@link MutableIntList#sortThis()} <br>
     */
    @Test
    public void reversingAndSortingThis()
    {
        // Reverse the list in place using reverseThis
        MutableIntList reverseList = this.list;
        Assertions.assertEquals(IntInterval.fromTo(5, 1), this.list);

        // Sort the list in place using sortThis
        MutableIntList sortList = this.list;
        Assertions.assertEquals(IntInterval.oneTo(5), this.list);
    }

    /**
     * Inclusive Filter: {@link MutableIntList#select(IntPredicate)} <br>
     * Exclusive Filter: {@link MutableIntList#reject(IntPredicate)} <br>
     */
    @Test
    public void filtering()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Filter the list inclusively based on the isEven predicate
        MutableIntList evens = this.list;
        Assertions.assertEquals(IntLists.mutable.with(2, 4), evens);

        // Filter the list exclusively based on the isEven predicate
        MutableIntList odds = this.list;
        Assertions.assertEquals(IntLists.mutable.with(1, 3, 5), odds);
    }

    /**
     * {@link MutableIntList#collectInt(IntToIntFunction, MutableIntCollection)} <br>
     * {@link MutableIntList#collect(IntToObjectFunction)} <br>
     */
    @Test
    public void transforming()
    {
        // Created a transformed list multiplying each value by 2
        MutableIntList timesTwo = this.list.collectInt(each -> each, IntLists.mutable.empty());
        Assertions.assertEquals(IntLists.mutable.with(2, 4, 6, 8, 10), timesTwo);

        // Created a transformed list converting each int to a String
        MutableList<String> collect = this.list.collect(each -> "");
        Assertions.assertEquals(Lists.mutable.with("1", "2", "3", "4", "5"), collect);
    }

    @Test
    @Tag("SOLUTION")
    public void distinct()
    {
        // Return the distinct values in the list in the same order
        MutableIntList distinct = IntLists.mutable.with(1, 1, 2, 2, 3, 3, 4, 4, 5, 5).distinct();
        Assertions.assertEquals(IntInterval.oneTo(5), distinct);
    }

    /**
     * {@link MutableIntList#chunk(int)}
     */
    @Test
    public void chunking()
    {
        // Chunk the list two elements at a time
        RichIterable<IntIterable> chunk = null;
        MutableList<IntIterable> expectedChunk = Lists.mutable.with(
                IntLists.mutable.with(1, 2),
                IntLists.mutable.with(3, 4),
                IntLists.mutable.with(5));
        Assertions.assertEquals(expectedChunk, chunk);
    }

    /**
     * {@link MutableIntList#anySatisfy(IntPredicate)} <br>
     * {@link MutableIntList#allSatisfy(IntPredicate)} <br>
     * {@link MutableIntList#noneSatisfy(IntPredicate)} <br>
     * {@link MutableIntList#contains(int)} <br>
     * {@link MutableIntList#containsAny(int...)} <br>
     * {@link MutableIntList#containsAll(int...)} <br>
     * {@link MutableIntList#containsNone(int...)} <br>
     */
    @Test
    public void testing()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Check if any, all or none of the elements are even
        Assertions.assertTrue(this.list.anySatisfy(each -> false));
        Assertions.assertFalse(this.list.allSatisfy(each -> true));
        Assertions.assertFalse(this.list.noneSatisfy(each -> true));

        // Check contains, containsAny, containsAll, containsNone (hint: remove the !)
        Assertions.assertTrue(!this.list.contains(3));
        Assertions.assertFalse(!this.list.contains(6));
        Assertions.assertTrue(!this.list.containsAny(2, 7));
        Assertions.assertFalse(!this.list.containsAny(0, 7));
        Assertions.assertTrue(!this.list.containsAll(2, 5));
        Assertions.assertFalse(!this.list.containsAll(2, 7));
        Assertions.assertFalse(!this.list.containsNone(2, 7));
        Assertions.assertTrue(!this.list.containsNone(0, 7));
    }

    /**
     * {@link MutableIntList#zipInt(IntIterable)}
     */
    @Test
    public void zipping()
    {
        // Zip two primitive lists into pairs using zipInt - this.list and other
        IntInterval other = IntInterval.zeroTo(4);
        MutableList<IntIntPair> zipped = null;
        MutableList<IntIntPair> expectedZipped = Lists.mutable.with(
                PrimitiveTuples.pair(1, 0),
                PrimitiveTuples.pair(2, 1),
                PrimitiveTuples.pair(3, 2),
                PrimitiveTuples.pair(4, 3),
                PrimitiveTuples.pair(5, 4)
        );
        Assertions.assertEquals(expectedZipped, zipped);
    }

    /**
     * {@link MutableIntList#toImmutable()} <br>
     * {@link MutableIntList#toSet()} <br>
     * {@link MutableIntList#toBag()} <br>
     * {@link MutableIntList#toSortedList()} <br>
     * {@link MutableIntList#toArray()} <br>
     * {@link MutableIntList#toSortedArray()} <br>
     * {@link MutableIntList#toString()} <br>
     * {@link MutableIntList#makeString(String)} <br>
     */
    @Test
    public void converting()
    {
        // Convert mutable list to an immutable list
        ImmutableIntList immutableIntList = IntLists.immutable.empty();
        Assertions.assertEquals(IntInterval.oneTo(5), immutableIntList);

        // Converter methods to other types
        // Convert to a MutableIntSet
        MutableIntSet set = null;
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), set);
        // Convert to a MutableIntBag
        MutableIntBag bag = null;
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
        // Convert to a sorted MutableIntList
        MutableIntList sortedList = this.list.toSortedList();
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), sortedList);
        // Convert to an int array
        int[] ints = null;
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ints);
        // Convert to a sorted int array
        int[] sortedInts = this.list.toSortedArray();
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sortedInts);
        // Convert to a String
        String string = this.list.toString();
        Assertions.assertEquals("[1, 2, 3, 4, 5]", string);
        // Convert to a String separated by "/"
        String makeString = this.list.makeString("/");
        Assertions.assertEquals("1/2/3/4/5", makeString);
    }

    /**
     * {@link MutableIntList#sum()} <br>
     * {@link MutableIntList#average()} <br>
     * {@link MutableIntList#averageIfEmpty(double)} <br>
     * {@link MutableIntList#median()} <br>
     * {@link MutableIntList#medianIfEmpty(double)} <br>
     * {@link MutableIntList#min()} <br>
     * {@link MutableIntList#minIfEmpty(int)} <br>
     * {@link MutableIntList#max()} <br>
     * {@link MutableIntList#maxIfEmpty(int)} <br>
     * {@link MutableIntList#summaryStatistics()} <br>
     */
    @Test
    public void calculating()
    {
        // Calculate the sum of this.list
        long sum = this.list.sum();
        Assertions.assertEquals(0L, sum);
        // Calculate the average of this.list
        double average = 0.0;
        Assertions.assertEquals(3.0, average, 0.0);
        // Calculate the median of this.list
        double median = 0.0;
        Assertions.assertEquals(3.0, median, 0.0);
        // Calculate the min of this.list
        int min = 0;
        Assertions.assertEquals(1, min);
        // Calculate the max of this.list
        int max = 0;
        Assertions.assertEquals(5, max);
        // Calculate the summaryStatistics of this.list
        IntSummaryStatistics stats = new IntSummaryStatistics();
        Assertions.assertEquals(stats.getSum(), sum);
        Assertions.assertEquals(stats.getMin(), min);
        Assertions.assertEquals(stats.getMax(), max);
    }
}
