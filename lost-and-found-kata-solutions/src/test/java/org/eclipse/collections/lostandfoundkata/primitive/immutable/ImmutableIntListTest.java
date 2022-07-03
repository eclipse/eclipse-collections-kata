/*
 * Copyright (c) 2022 The Bank of New York Mellon.
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
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.primitive.IntBags;
import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.factory.primitive.IntSets;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.tuple.primitive.IntIntPair;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ImmutableIntListTest
{
    private final ImmutableIntList list = IntLists.immutable.with(1, 2, 3, 4, 5);

    @Test
    @Tag("SOLUTION")
    public void asReversed()
    {
        // Create a reverse view of the list
        LazyIntIterable asReverseLazy = this.list.asReversed();
        Assertions.assertEquals(IntInterval.fromTo(5, 1), asReverseLazy.toList());
    }

    @Test
    @Tag("SOLUTION")
    public void toReversed()
    {
        // Create a reverse copy of the list
        ImmutableIntList toReverseList = this.list.toReversed();
        Assertions.assertEquals(IntInterval.fromTo(5, 1), toReverseList);
    }

    @Test
    @Tag("SOLUTION")
    public void newWithAndWithout()
    {
        // Add two values to the list
        ImmutableIntList listWith = this.list.newWith(6).newWith(7);
        Assertions.assertEquals(IntInterval.oneTo(7), listWith);
        Assertions.assertNotSame(this.list, listWith);

        // Add three values to the list
        ImmutableIntList listWithAll = listWith.newWithAll(IntLists.immutable.with(8, 9, 10));
        Assertions.assertEquals(IntInterval.oneTo(10), listWithAll);
        Assertions.assertNotSame(this.list, listWithAll);

        // Remove two values from the list
        ImmutableIntList listWithout = listWithAll.newWithout(9).newWithout(10);
        Assertions.assertEquals(IntInterval.oneTo(8), listWithout);
        Assertions.assertNotSame(this.list, listWithout);

        // Remove three values from the list
        ImmutableIntList listWithoutAll = listWithout.newWithoutAll(IntLists.immutable.with(6, 7, 8));
        Assertions.assertEquals(IntInterval.oneTo(5), listWithoutAll);
        Assertions.assertNotSame(this.list, listWithoutAll);
    }

    @Test
    @Tag("SOLUTION")
    public void select()
    {
        // Filter the list inclusively
        ImmutableIntList evens = this.list.select(each -> each % 2 == 0);
        Assertions.assertEquals(IntLists.mutable.with(2, 4), evens);
    }

    @Test
    @Tag("SOLUTION")
    public void reject()
    {
        // Filter the list exclusively
        ImmutableIntList odds = this.list.reject(each -> each % 2 == 0);
        Assertions.assertEquals(IntLists.mutable.with(1, 3, 5), odds);
    }

    @Test
    @Tag("SOLUTION")
    public void collectInt()
    {
        // Created a transformed IntList multiplying each value by 2
        MutableIntList timesTwo = this.list.collectInt(each -> each * 2, IntLists.mutable.empty());
        Assertions.assertEquals(IntLists.mutable.with(2, 4, 6, 8, 10), timesTwo);
    }

    @Test
    @Tag("SOLUTION")
    public void collect()
    {
        // Created a transformed list converting each int to a String
        ImmutableList<String> collect = this.list.collect(String::valueOf);
        Assertions.assertEquals(Lists.mutable.with("1", "2", "3", "4", "5"), collect);
    }

    @Test
    @Tag("SOLUTION")
    public void distinct()
    {
        // Return the distinct values in the list in the same order
        ImmutableIntList distinct = IntLists.immutable.with(1, 1, 2, 2, 3, 3, 4, 4, 5, 5).distinct();
        Assertions.assertEquals(IntInterval.oneTo(5), distinct);
    }

    @Test
    @Tag("SOLUTION")
    public void chunk()
    {
        // Chunk the list two elements at a time
        RichIterable<IntIterable> chunk = this.list.chunk(2);
        ImmutableList<IntIterable> expectedChunk = Lists.immutable.with(
                IntLists.mutable.with(1, 2),
                IntLists.mutable.with(3, 4),
                IntLists.mutable.with(5));
        Assertions.assertEquals(expectedChunk, chunk);
    }

    @Test
    @Tag("SOLUTION")
    public void containsNone()
    {
        Assertions.assertFalse(this.list.containsNone(2, 7));
        Assertions.assertTrue(this.list.containsNone(0, 7));
    }

    @Test
    @Tag("SOLUTION")
    public void containsAll()
    {
        Assertions.assertTrue(this.list.containsAll(2, 5));
        Assertions.assertFalse(this.list.containsAll(2, 7));
    }

    @Test
    @Tag("SOLUTION")
    public void containsAny()
    {
        Assertions.assertTrue(this.list.containsAny(2, 7));
        Assertions.assertFalse(this.list.containsAny(0, 7));
    }

    @Test
    @Tag("SOLUTION")
    public void contains()
    {
        Assertions.assertTrue(this.list.contains(3));
        Assertions.assertFalse(this.list.contains(6));
    }

    @Test
    @Tag("SOLUTION")
    public void noneSatisfy()
    {
        Assertions.assertFalse(this.list.noneSatisfy(each -> each % 2 == 0));
    }

    @Test
    @Tag("SOLUTION")
    public void allSatisfy()
    {
        Assertions.assertFalse(this.list.allSatisfy(each -> each % 2 == 0));
    }

    @Test
    @Tag("SOLUTION")
    public void anySatisfy()
    {
        Assertions.assertTrue(this.list.anySatisfy(each -> each % 2 == 0));
    }

    @Test
    @Tag("SOLUTION")
    public void zipInt()
    {
        // Zip two primitive lists into pairs
        ImmutableList<IntIntPair> zipped = this.list.zipInt(IntInterval.zeroTo(4));
        ImmutableList<IntIntPair> expectedZipped = Lists.immutable.with(
                PrimitiveTuples.pair(1, 0),
                PrimitiveTuples.pair(2, 1),
                PrimitiveTuples.pair(3, 2),
                PrimitiveTuples.pair(4, 3),
                PrimitiveTuples.pair(5, 4)
        );
        Assertions.assertEquals(expectedZipped, zipped);
    }

    @Test
    @Tag("SOLUTION")
    public void makeString()
    {
        String makeString = this.list.makeString("/");
        Assertions.assertEquals("1/2/3/4/5", makeString);
    }

    @Test
    @Tag("SOLUTION")
    public void testToString()
    {
        String string = this.list.toString();
        Assertions.assertEquals("[1, 2, 3, 4, 5]", string);
    }

    @Test
    @Tag("SOLUTION")
    public void toSortedArray()
    {
        int[] sortedInts = this.list.toSortedArray();
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sortedInts);
    }

    @Test
    @Tag("SOLUTION")
    public void toArray()
    {
        int[] ints = this.list.toArray();
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ints);
    }

    @Test
    @Tag("SOLUTION")
    public void toSortedList()
    {
        MutableIntList sortedList = this.list.toSortedList();
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), sortedList);
    }

    @Test
    @Tag("SOLUTION")
    public void toBag()
    {
        MutableIntBag bag = this.list.toBag();
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
    }

    @Test
    @Tag("SOLUTION")
    public void toSet()
    {
        MutableIntSet set = this.list.toSet();
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), set);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutable()
    {
        ImmutableIntList immutableIntList = this.list.toImmutable();
        Assertions.assertEquals(IntInterval.oneTo(5), immutableIntList);
        Assertions.assertSame(immutableIntList, this.list);
    }

    @Test
    @Tag("SOLUTION")
    public void maxIfEmpty()
    {
        int max = this.list.maxIfEmpty(0);
        Assertions.assertEquals(5, max);
    }

    @Test
    @Tag("SOLUTION")
    public void minIfEmpty()
    {
        int min = this.list.minIfEmpty(0);
        Assertions.assertEquals(1, min);
    }

    @Test
    @Tag("SOLUTION")
    public void medianIfEmpty()
    {
        double median = this.list.medianIfEmpty(0.0);
        Assertions.assertEquals(3.0, median, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void averageIfEmpty()
    {
        double average = this.list.averageIfEmpty(0.0);
        Assertions.assertEquals(3.0, average, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void sum()
    {
        long sum = this.list.sum();
        Assertions.assertEquals(15L, sum);
    }

    @Test
    @Tag("SOLUTION")
    public void summaryStatistics()
    {
        IntSummaryStatistics stats = this.list.summaryStatistics();
        Assertions.assertEquals(15L, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(5, stats.getMax());
    }
}
