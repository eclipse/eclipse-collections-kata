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

import java.util.Arrays;
import java.util.IntSummaryStatistics;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.factory.primitive.IntBags;
import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.factory.primitive.IntSets;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.ImmutableIntSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.tuple.primitive.IntIntPair;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ImmutableIntSetTest
{
    private final ImmutableIntSet set = IntSets.immutable.with(1, 2, 3, 4, 5);

    @Test
    @Tag("SOLUTION")
    public void newWithAndWithout()
    {
        // Add two values to the set
        ImmutableIntSet setWith = this.set.newWith(6).newWith(7);
        Assertions.assertEquals(IntInterval.oneTo(7).toSet(), setWith);
        Assertions.assertNotSame(this.set, setWith);

        // Add three values to the set
        ImmutableIntSet setWithAll = setWith.newWithAll(IntLists.immutable.with(8, 9, 10));
        Assertions.assertEquals(IntInterval.oneTo(10).toSet(), setWithAll);
        Assertions.assertNotSame(this.set, setWithAll);

        // Remove two values from the set
        ImmutableIntSet setWithout = setWithAll.newWithout(9).newWithout(10);
        Assertions.assertEquals(IntInterval.oneTo(8).toSet(), setWithout);
        Assertions.assertNotSame(this.set, setWithout);

        // Remove three values from the set
        ImmutableIntSet setWithoutAll = setWithout.newWithoutAll(IntLists.immutable.with(6, 7, 8));
        Assertions.assertEquals(IntInterval.oneTo(5).toSet(), setWithoutAll);
        Assertions.assertNotSame(this.set, setWithoutAll);
    }


    @Test
    @Tag("SOLUTION")
    public void select()
    {
        // Filter the set inclusively
        ImmutableIntSet evens = this.set.select(each -> each % 2 == 0);
        Assertions.assertEquals(IntSets.mutable.with(2, 4), evens);
    }

    @Test
    @Tag("SOLUTION")
    public void reject()
    {
        // Filter the set exclusively
        ImmutableIntSet odds = this.set.reject(each -> each % 2 == 0);
        Assertions.assertEquals(IntSets.mutable.with(1, 3, 5), odds);
    }

    @Test
    @Tag("SOLUTION")
    public void collect()
    {
        // Created a transformed set converting each int to a String
        ImmutableSet<String> collect = this.set.collect(String::valueOf);
        Assertions.assertEquals(Sets.mutable.with("1", "2", "3", "4", "5"), collect);
    }

    @Test
    @Tag("SOLUTION")
    public void collectInt()
    {
        // Created a transformed IntSet multiplying each value by 2
        MutableIntSet timesTwo = this.set.collectInt(each -> each * 2, IntSets.mutable.empty());
        Assertions.assertEquals(IntSets.mutable.with(2, 4, 6, 8, 10), timesTwo);
    }

    @Test
    @Tag("SOLUTION")
    public void chunk()
    {
        // Chunk the set two elements at a time
        RichIterable<IntIterable> chunk = this.set.chunk(2);
        Assertions.assertEquals(3, chunk.size());
    }

    @Test
    @Tag("SOLUTION")
    public void anySatisfy()
    {
        Assertions.assertTrue(this.set.anySatisfy(each -> each % 2 == 0));
        Assertions.assertFalse(this.set.anySatisfy(each -> each < 0));
    }

    @Test
    @Tag("SOLUTION")
    public void allSatisfy()
    {
        Assertions.assertFalse(this.set.allSatisfy(each -> each % 2 == 0));
        Assertions.assertTrue(this.set.allSatisfy(each -> each > 0));
    }

    @Test
    @Tag("SOLUTION")
    public void noneSatisfy()
    {
        Assertions.assertFalse(this.set.noneSatisfy(each -> each % 2 == 0));
        Assertions.assertTrue(this.set.noneSatisfy(each -> each < 0));
    }

    @Test
    @Tag("SOLUTION")
    public void contains()
    {
        Assertions.assertTrue(this.set.contains(3));
        Assertions.assertFalse(this.set.contains(6));
    }

    @Test
    @Tag("SOLUTION")
    public void containsAny()
    {
        Assertions.assertTrue(this.set.containsAny(2, 7));
        Assertions.assertFalse(this.set.containsAny(0, 7));
    }

    @Test
    @Tag("SOLUTION")
    public void containsAll()
    {
        Assertions.assertTrue(this.set.containsAll(2, 5));
        Assertions.assertFalse(this.set.containsAll(2, 7));
    }

    @Test
    @Tag("SOLUTION")
    public void containsNone()
    {
        Assertions.assertFalse(this.set.containsNone(2, 7));
        Assertions.assertTrue(this.set.containsNone(0, 7));
    }

    @Test
    @Tag("SOLUTION")
    public void makeString()
    {
        String makeString = this.set.makeString("/");
        Assertions.assertTrue(makeString.contains("/"));
        Assertions.assertTrue(makeString.contains("1"));
        Assertions.assertTrue(makeString.contains("2"));
        Assertions.assertTrue(makeString.contains("3"));
        Assertions.assertTrue(makeString.contains("4"));
        Assertions.assertTrue(makeString.contains("5"));
    }

    @Test
    @Tag("SOLUTION")
    public void testToString()
    {
        String string = this.set.toString();
        Assertions.assertTrue(string.contains(","));
        Assertions.assertTrue(string.contains("1"));
        Assertions.assertTrue(string.contains("2"));
        Assertions.assertTrue(string.contains("3"));
        Assertions.assertTrue(string.contains("4"));
        Assertions.assertTrue(string.contains("5"));
    }

    @Test
    @Tag("SOLUTION")
    public void toSortedArray()
    {
        int[] sortedInts = this.set.toSortedArray();
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sortedInts);
    }

    @Test
    @Tag("SOLUTION")
    public void toArray()
    {
        int[] ints = this.set.toArray();
        Arrays.sort(ints);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ints);
    }

    @Test
    @Tag("SOLUTION")
    public void toSortedList()
    {
        MutableIntList sortedList = this.set.toSortedList();
        Assertions.assertEquals(IntInterval.oneTo(5), sortedList);
    }

    @Test
    @Tag("SOLUTION")
    public void toBag()
    {
        MutableIntBag bag = this.set.toBag();
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
    }

    @Test
    @Tag("SOLUTION")
    public void toList()
    {
        MutableIntList list = this.set.toList().sortThis();
        Assertions.assertEquals(IntInterval.oneTo(5), list);
    }

    @Test
    @Tag("SOLUTION")
    public void toSet()
    {
        // Convert mutable set to an immutable set
        ImmutableIntSet immutableIntSet = this.set.toImmutable();
        Assertions.assertEquals(IntInterval.oneTo(5).toSet(), immutableIntSet);
        Assertions.assertSame(immutableIntSet, this.set);
    }

    @Test
    @Tag("SOLUTION")
    public void summaryStatistics()
    {
        IntSummaryStatistics stats = this.set.summaryStatistics();
        Assertions.assertEquals(15L, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(5, stats.getMax());
    }

    @Test
    @Tag("SOLUTION")
    public void maxIfEmpty()
    {
        int max = this.set.maxIfEmpty(0);
        Assertions.assertEquals(5, max);
    }

    @Test
    @Tag("SOLUTION")
    public void minIfEmpty()
    {
        int min = this.set.minIfEmpty(0);
        Assertions.assertEquals(1, min);
    }

    @Test
    @Tag("SOLUTION")
    public void median()
    {
        double median = this.set.medianIfEmpty(0.0);
        Assertions.assertEquals(3.0, median, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void average()
    {
        double average = this.set.averageIfEmpty(0.0);
        Assertions.assertEquals(3.0, average, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void sum()
    {
        long sum = this.set.sum();
        Assertions.assertEquals(15L, sum);
    }

    @Test
    @Tag("SOLUTION")
    public void cartesianProduct()
    {
        LazyIterable<IntIntPair> product =
                IntSets.mutable.with(1, 2).cartesianProduct(IntSets.mutable.with(3, 4));
        MutableSet<IntIntPair> expectedProduct =
                Sets.mutable.with(
                        PrimitiveTuples.pair(1, 3),
                        PrimitiveTuples.pair(1, 4),
                        PrimitiveTuples.pair(2, 3),
                        PrimitiveTuples.pair(2, 4));
        Assertions.assertEquals(expectedProduct, product.toSet());
    }

    @Test
    @Tag("SOLUTION")
    public void symmetricDifference()
    {
        ImmutableIntSet symmetricDifference =
                this.set.symmetricDifference(IntSets.mutable.with(4, 5, 6));
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 6), symmetricDifference);
    }

    @Test
    @Tag("SOLUTION")
    public void difference()
    {
        ImmutableIntSet difference = this.set.difference(IntSets.mutable.with(4, 5, 6));
        Assertions.assertEquals(IntInterval.oneTo(3).toSet(), difference);
    }

    @Test
    @Tag("SOLUTION")
    public void union()
    {
        ImmutableIntSet union = this.set.union(IntSets.mutable.with(4, 5, 6));
        Assertions.assertEquals(IntInterval.oneTo(6).toSet(), union);
    }

    @Test
    @Tag("SOLUTION")
    public void intersect()
    {
        ImmutableIntSet intersect = this.set.intersect(IntSets.mutable.with(4, 5, 6));
        Assertions.assertEquals(IntSets.mutable.with(4, 5), intersect);
    }
}
