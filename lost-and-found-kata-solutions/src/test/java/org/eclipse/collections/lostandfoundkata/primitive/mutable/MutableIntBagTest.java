/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.primitive.mutable;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.ImmutableIntBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.primitive.IntBags;
import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.factory.primitive.IntSets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.tuple.primitive.IntIntPair;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.string.immutable.CharAdapter;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class MutableIntBagTest
{
    private MutableIntBag bag;

    @BeforeEach
    void setUp()
    {
        this.bag = IntBags.mutable.with(1, 2, 2, 3, 3, 3);
    }

    @Test
    @Tag("SOLUTION")
    public void addAndRemoveOccurrences()
    {
        // add and removeOccurrences
        this.bag.addOccurrences(4, 4);
        Assertions.assertEquals(4, this.bag.occurrencesOf(4));
        this.bag.removeOccurrences(4, 4);
        Assertions.assertEquals(0, this.bag.occurrencesOf(4));
    }

    @Test
    @Tag("SOLUTION")
    public void withAndWithout()
    {
        // Add two values to the bag
        MutableIntBag bagWith = this.bag.with(4).with(5);
        Assertions.assertEquals(IntBags.immutable.with(1, 2, 2, 3, 3, 3, 4, 5), bagWith);
        Assertions.assertSame(this.bag, bagWith);

        // Add three values to the bag
        MutableIntBag bagWithAll = this.bag.withAll(IntLists.immutable.with(6, 7, 8));
        Assertions.assertEquals(IntBags.immutable.with(1, 2, 2, 3, 3, 3, 4, 5, 6, 7, 8), bagWithAll);
        Assertions.assertSame(this.bag, bagWithAll);

        // Remove two values from the bag
        MutableIntBag bagWithout = this.bag.without(7).without(8);
        Assertions.assertEquals(IntBags.immutable.with(1, 2, 2, 3, 3, 3, 4, 5, 6), bagWithout);
        Assertions.assertSame(this.bag, bagWithout);

        // Remove three values from the bag
        MutableIntBag bagWithoutAll = this.bag.withoutAll(IntLists.immutable.with(4, 5, 6));
        Assertions.assertEquals(IntBags.immutable.with(1, 2, 2, 3, 3, 3), bagWithoutAll);
        Assertions.assertSame(this.bag, bagWithoutAll);
    }

    @Test
    @Tag("SOLUTION")
    public void select()
    {
        // Filter the bag inclusively
        MutableIntBag evens = this.bag.select(each -> each % 2 == 0);
        Assertions.assertEquals(IntBags.mutable.with(2, 2), evens);
    }

    @Test
    @Tag("SOLUTION")
    public void reject()
    {
        // Filter the bag exclusively
        MutableIntBag odds = this.bag.reject(each -> each % 2 == 0);
        Assertions.assertEquals(IntBags.mutable.with(1, 3, 3, 3), odds);
    }

    @Test
    @Tag("SOLUTION")
    public void collectInt()
    {
        // Created a transformed IntSet multiplying each value by 2
        MutableIntBag timesTwo = this.bag.collectInt(each -> each * 2, IntBags.mutable.empty());
        Assertions.assertEquals(IntBags.mutable.with(2, 4, 4, 6, 6, 6), timesTwo);
    }

    @Test
    @Tag("SOLUTION")
    public void collect()
    {
        // Created a transformed bag converting each int to a String
        MutableBag<String> collect = this.bag.collect(String::valueOf);
        Assertions.assertEquals(Bags.mutable.with("1", "2", "2", "3", "3", "3"), collect);
    }

    @Test
    @Tag("SOLUTION")
    public void chunk()
    {
        // Chunk the bag two elements at a time
        RichIterable<IntIterable> chunk = this.bag.chunk(2);
        Assertions.assertEquals(3, chunk.size());
    }

    @Test
    @Tag("SOLUTION")
    public void anySatisfy()
    {
        // Check if any, all or none of the elements are even
        Assertions.assertTrue(this.bag.anySatisfy(each -> each % 2 == 0));
    }

    @Test
    @Tag("SOLUTION")
    public void allSatisfy()
    {
        Assertions.assertFalse(this.bag.allSatisfy(each -> each % 2 == 0));
    }

    @Test
    @Tag("SOLUTION")
    public void noneSatisfy()
    {
        Assertions.assertFalse(this.bag.noneSatisfy(each -> each % 2 == 0));
    }

    @Test
    @Tag("SOLUTION")
    public void contains()
    {
        Assertions.assertTrue(this.bag.contains(3));
        Assertions.assertFalse(this.bag.contains(6));
    }

    @Test
    @Tag("SOLUTION")
    public void containsAny()
    {
        Assertions.assertTrue(this.bag.containsAny(2, 7));
        Assertions.assertFalse(this.bag.containsAny(0, 7));
    }

    @Test
    @Tag("SOLUTION")
    public void containsAll()
    {
        Assertions.assertTrue(this.bag.containsAll(2, 3));
        Assertions.assertFalse(this.bag.containsAll(2, 7));
    }

    @Test
    @Tag("SOLUTION")
    public void containsNone()
    {
        Assertions.assertFalse(this.bag.containsNone(2, 7));
        Assertions.assertTrue(this.bag.containsNone(0, 7));
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutable()
    {
        // Convert mutable bag to an immutable bag
        ImmutableIntBag immutableIntBag = this.bag.toImmutable();
        Assertions.assertEquals(this.bag, immutableIntBag);
    }

    @Test
    @Tag("SOLUTION")
    public void toList()
    {
        MutableIntList list = this.bag.toList();
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 2, 3, 3, 3), list.sortThis());
    }

    @Test
    @Tag("SOLUTION")
    public void toSet()
    {
        MutableIntSet set = this.bag.toSet();
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3), set);
    }

    @Test
    @Tag("SOLUTION")
    public void toSortedList()
    {
        MutableIntList sortedList = this.bag.toSortedList();
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 2, 3, 3, 3), sortedList);
    }

    @Test
    @Tag("SOLUTION")
    public void toArray()
    {
        int[] ints = this.bag.toArray();
        Arrays.sort(ints);
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 3, 3, 3}, ints);
    }

    @Test
    @Tag("SOLUTION")
    public void toSortedArray()
    {
        int[] sortedInts = this.bag.toSortedArray();
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 3, 3, 3}, sortedInts);
    }

    @Test
    @Tag("SOLUTION")
    public void testToString()
    {
        CharAdapter toStringAdapter = Strings.asChars(this.bag.toString());
        Assertions.assertEquals(1, toStringAdapter.count(each -> each == '1'));
        Assertions.assertEquals(2, toStringAdapter.count(each -> each == '2'));
        Assertions.assertEquals(3, toStringAdapter.count(each -> each == '3'));
    }

    @Test
    @Tag("SOLUTION")
    public void makeString()
    {
        CharAdapter makeStringAdapter = Strings.asChars(this.bag.makeString("/"));
        Assertions.assertEquals(1, makeStringAdapter.count(each -> each == '1'));
        Assertions.assertEquals(2, makeStringAdapter.count(each -> each == '2'));
        Assertions.assertEquals(3, makeStringAdapter.count(each -> each == '3'));
    }

    @Test
    @Tag("SOLUTION")
    public void sum()
    {
        long sum = this.bag.sum();
        Assertions.assertEquals(14L, sum);
    }

    @Test
    @Tag("SOLUTION")
    public void averageIfEmpty()
    {
        double average = this.bag.averageIfEmpty(0.0);
        Assertions.assertEquals(2.3, average, 0.1);
    }

    @Test
    @Tag("SOLUTION")
    public void medianIfEmpty()
    {
        double median = this.bag.medianIfEmpty(0.0);
        Assertions.assertEquals(2.5, median, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void minIfEmpty()
    {
        int min = this.bag.minIfEmpty(0);
        Assertions.assertEquals(1, min);
    }

    @Test
    @Tag("SOLUTION")
    public void maxIfEmpty()
    {
        int max = this.bag.maxIfEmpty(0);
        Assertions.assertEquals(3, max);
    }

    @Test
    @Tag("SOLUTION")
    public void summaryStatistics()
    {
        IntSummaryStatistics stats = this.bag.summaryStatistics();
        Assertions.assertEquals(14L, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(3, stats.getMax());
    }

    @Test
    @Tag("SOLUTION")
    public void occurrencesOf()
    {
        Assert.assertEquals(1, this.bag.occurrencesOf(1));
        Assert.assertEquals(2, this.bag.occurrencesOf(2));
        Assert.assertEquals(3, this.bag.occurrencesOf(3));
    }

    public void topOccurrences()
    {
        MutableList<IntIntPair> topPairs = this.bag.topOccurrences(1);
        Assertions.assertEquals(PrimitiveTuples.pair(3, 3), topPairs.getFirst());
    }

    public void bottomOccurrences()
    {
        MutableList<IntIntPair> bottomPairs = this.bag.bottomOccurrences(1);
        Assertions.assertEquals(PrimitiveTuples.pair(1, 1), bottomPairs.getFirst());
    }
}
