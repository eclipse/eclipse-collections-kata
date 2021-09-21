/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.primitive.immutable;

import java.util.IntSummaryStatistics;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.LazyIntIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.block.function.primitive.IntToIntFunction;
import org.eclipse.collections.api.block.function.primitive.IntToObjectFunction;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.tuple.primitive.IntIntPair;
import org.eclipse.collections.impl.factory.primitive.IntBags;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ImmutableIntListTest
{
    private final ImmutableIntList list = IntLists.immutable.with(1, 2, 3, 4, 5);

    /**
     * {@link ImmutableIntList#asReversed()} <br>
     * {@link ImmutableIntList#toReversed()} <br>
     */
    @Test
    public void reversing()
    {
        // Create a reverse view of the list
        LazyIntIterable asReverseLazy = this.list.asLazy();
        Assertions.assertEquals(IntInterval.fromTo(5, 1), asReverseLazy.toList());

        // Create a reverse copy of the list
        ImmutableIntList toReverseList = this.list;
        Assertions.assertEquals(IntInterval.fromTo(5, 1), toReverseList);
    }

    /**
     * {@link ImmutableIntList#newWith(int)} <br>
     * {@link ImmutableIntList#newWithAll(IntIterable)} <br>
     * {@link ImmutableIntList#newWithout(int)} <br>
     * {@link ImmutableIntList#newWithoutAll(IntIterable)} <br>
     */
    @Test
    public void newWithAndWithout()
    {
        // Add the values 6 and 7 to the list using newWith
        ImmutableIntList listWith = this.list;
        Assertions.assertEquals(IntInterval.oneTo(7), listWith);
        Assertions.assertNotSame(this.list, listWith);

        // Add the values 8, 9, 10 to the list using newWithAll
        ImmutableIntList listWithAll = listWith;
        Assertions.assertEquals(IntInterval.oneTo(10), listWithAll);
        Assertions.assertNotSame(this.list, listWithAll);

        // Remove the values 9 and 10 from the list using newWithout
        ImmutableIntList listWithout = listWithAll;
        Assertions.assertEquals(IntInterval.oneTo(8), listWithout);
        Assertions.assertNotSame(this.list, listWithout);

        // Remove the values 6, 7, 8 from the list using newWithoutAll
        ImmutableIntList listWithoutAll = listWithout;
        Assertions.assertEquals(IntInterval.oneTo(5), listWithoutAll);
        Assertions.assertNotSame(this.list, listWithoutAll);
    }

    /**
     * Inclusive Filter: {@link ImmutableIntList#select(IntPredicate)} <br>
     * Exclusive Filter: {@link ImmutableIntList#reject(IntPredicate)} <br>
     */
    @Test
    public void filtering()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Filter the list inclusively based on the isEven predicate
        ImmutableIntList evens = this.list;
        Assertions.assertEquals(IntLists.mutable.with(2, 4), evens);

        // Filter the list exclusively based on the isEven predicate
        ImmutableIntList odds = this.list;
        Assertions.assertEquals(IntLists.mutable.with(1, 3, 5), odds);
    }

    /**
     * {@link ImmutableIntList#collectInt(IntToIntFunction, MutableIntCollection)} <br>
     * {@link ImmutableIntList#collect(IntToObjectFunction)} <br>
     */
    @Test
    public void transforming()
    {
        // Create a transformed IntList multiplying each value by 2
        MutableIntList timesTwo = this.list.collectInt(each -> each, IntLists.mutable.empty());
        Assertions.assertEquals(IntLists.mutable.with(2, 4, 6, 8, 10), timesTwo);

        // Create a transformed list converting each int to a String
        ImmutableList<String> collect = this.list.collect(each -> "");
        Assertions.assertEquals(Lists.mutable.with("1", "2", "3", "4", "5"), collect);
    }

    /**
     * {@link ImmutableIntList#distinct()}
     */
    @Test
    public void distinct()
    {
        // Return the distinct values in the list in the same order
        ImmutableIntList distinct = IntLists.immutable.with(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        Assertions.assertEquals(IntInterval.oneTo(5), distinct);
    }

    /**
     * {@link ImmutableIntList#chunk(int)}
     */
    @Test
    public void chunking()
    {
        // Chunk the list two elements at a time
        RichIterable<IntIterable> chunk = null;
        ImmutableList<IntIterable> expectedChunk = Lists.immutable.with(
                IntLists.mutable.with(1, 2),
                IntLists.mutable.with(3, 4),
                IntLists.mutable.with(5));
        Assertions.assertEquals(expectedChunk, chunk);
    }

    /**
     * {@link ImmutableIntList#anySatisfy(IntPredicate)} <br>
     * {@link ImmutableIntList#allSatisfy(IntPredicate)} <br>
     * {@link ImmutableIntList#noneSatisfy(IntPredicate)} <br>
     * {@link ImmutableIntList#contains(int)} <br>
     * {@link ImmutableIntList#containsAny(int...)} <br>
     * {@link ImmutableIntList#containsAll(int...)} <br>
     * {@link ImmutableIntList#containsNone(int...)} <br>
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
     * {@link ImmutableIntList#zipInt(IntIterable)}
     */
    @Test
    public void zipping()
    {
        // Zip two primitive lists into pairs using zipInt - this.list and other
        IntInterval other = IntInterval.zeroTo(4);
        ImmutableList<IntIntPair> zipped = null;
        ImmutableList<IntIntPair> expectedZipped = Lists.immutable.with(
                PrimitiveTuples.pair(1, 0),
                PrimitiveTuples.pair(2, 1),
                PrimitiveTuples.pair(3, 2),
                PrimitiveTuples.pair(4, 3),
                PrimitiveTuples.pair(5, 4)
        );
        Assertions.assertEquals(expectedZipped, zipped);
    }

    /**
     * {@link ImmutableIntList#toImmutable()} <br>
     * {@link ImmutableIntList#toSet()} <br>
     * {@link ImmutableIntList#toBag()} <br>
     * {@link ImmutableIntList#toSortedList()} <br>
     * {@link ImmutableIntList#toArray()} <br>
     * {@link ImmutableIntList#toSortedArray()} <br>
     * {@link ImmutableIntList#toString()} <br>
     * {@link ImmutableIntList#makeString(String)} <br>
     */
    @Test
    public void converting()
    {
        // Convert immutable list to an immutable list
        IntList immutableIntList = this.list.toList();
        Assertions.assertEquals(IntInterval.oneTo(5), immutableIntList);
        Assertions.assertSame(immutableIntList, this.list);

        // Converter methods to other types
        // Convert to a MutableIntSet
        MutableIntSet set = null;
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), set);
        // Convert to a MutableIntBag
        MutableIntBag bag = null;
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
        // Convert to a sorted MutableIntList
        MutableIntList sortedList = null;
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), sortedList);
        // Convert to an int array
        int[] ints = null;
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ints);
        // Convert to a sorted int array
        int[] sortedInts = null;
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sortedInts);
        // Convert to a String
        String string = "";
        Assertions.assertEquals("[1, 2, 3, 4, 5]", string);
        // Convert to a String separated by "/"
        String makeString = "/";
        Assertions.assertEquals("1/2/3/4/5", makeString);
    }

    /**
     * {@link ImmutableIntList#sum()} <br>
     * {@link ImmutableIntList#average()} <br>
     * {@link ImmutableIntList#averageIfEmpty(double)} <br>
     * {@link ImmutableIntList#median()} <br>
     * {@link ImmutableIntList#medianIfEmpty(double)} <br>
     * {@link ImmutableIntList#min()} <br>
     * {@link ImmutableIntList#minIfEmpty(int)} <br>
     * {@link ImmutableIntList#max()} <br>
     * {@link ImmutableIntList#maxIfEmpty(int)} <br>
     * {@link ImmutableIntList#summaryStatistics()} <br>
     */
    @Test
    public void calculating()
    {
        // Math
        // Calculate the sum of this.list
        long sum = 0L;
        Assertions.assertEquals(15L, sum);
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
