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
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.block.function.primitive.IntToIntFunction;
import org.eclipse.collections.api.block.function.primitive.IntToObjectFunction;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.ImmutableIntSet;
import org.eclipse.collections.api.set.primitive.IntSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.tuple.primitive.IntIntPair;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.primitive.IntBags;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * {@link org.eclipse.collections.api.factory.set.primitive.MutableIntSetFactory#with(int...)} <br>
 * @see IntSets <br>
 * <p/>
 * {@link MutableIntSet#with(int)} <br>
 * {@link MutableIntSet#withAll(IntIterable)} <br>
 * {@link MutableIntSet#without(int)} <br>
 * {@link MutableIntSet#withoutAll(IntIterable)} <br>
 * <p/>
 * Filtering: <br>
 * {@link MutableIntSet#select(IntPredicate)} <br>
 * {@link MutableIntSet#reject(IntPredicate)} <br>
 * <p/>
 * Transforming: <br>
 * {@link MutableIntSet#collectInt(IntToIntFunction, MutableIntCollection)} <br>
 * {@link MutableIntSet#collect(IntToObjectFunction)} <br>
 * <p/>
 * {@link MutableIntSet#chunk(int)} <br>
 * <p/>
 * Testing: <br>
 * {@link MutableIntSet#anySatisfy(IntPredicate)} <br>
 * {@link MutableIntSet#allSatisfy(IntPredicate)} <br>
 * {@link MutableIntSet#noneSatisfy(IntPredicate)} <br>
 * {@link MutableIntSet#contains(int)} <br>
 * {@link MutableIntSet#containsAny(int...)} <br>
 * {@link MutableIntSet#containsAll(int...)} <br>
 * {@link MutableIntSet#containsNone(int...)} <br>
 * <p/>
 * {@link MutableIntSet#toImmutable()} <br>
 * {@link MutableIntSet#toList()} <br>
 * {@link MutableIntSet#toBag()} <br>
 * {@link MutableIntSet#toSortedList()} <br>
 * {@link MutableIntSet#toArray()} <br>
 * {@link MutableIntSet#toSortedArray()} <br>
 * {@link MutableIntSet#toString()} <br>
 * {@link MutableIntSet#makeString(String)} <br>
 * <p/>
 * Calculating: <br>
 * {@link MutableIntSet#sum()} <br>
 * {@link MutableIntSet#average()} <br>
 * {@link MutableIntSet#averageIfEmpty(double)} <br>
 * {@link MutableIntSet#median()} <br>
 * {@link MutableIntSet#medianIfEmpty(double)} <br>
 * {@link MutableIntSet#min()} <br>
 * {@link MutableIntSet#minIfEmpty(int)} <br>
 * {@link MutableIntSet#max()} <br>
 * {@link MutableIntSet#maxIfEmpty(int)} <br>
 * {@link MutableIntSet#summaryStatistics()} <br>
 * <p/>
 * Set Arithmetic: <br>
 * {@link MutableIntSet#intersect(IntSet)} <br>
 * {@link MutableIntSet#union(IntSet)} <br>
 * {@link MutableIntSet#difference(IntSet)} <br>
 * {@link MutableIntSet#symmetricDifference(IntSet)} <br>
 * {@link MutableIntSet#cartesianProduct(IntSet)} <br>
 */
public class MutableIntSetTest
{
    private MutableIntSet set;

    @BeforeEach
    void setUp()
    {
        this.set = IntSets.mutable.with(1, 2, 3, 4, 5);
    }

    @Test
    public void withAndWithout()
    {
        // Add the values 6 and 7 to the set using with
        MutableIntSet setWith = this.set;
        Assertions.assertEquals(IntInterval.oneTo(7).toSet(), setWith);
        Assertions.assertSame(this.set, setWith);

        // Add the values 8, 9, 10 to the set using withAll
        MutableIntSet setWithAll = this.set.withAll(IntLists.immutable.empty());
        Assertions.assertEquals(IntInterval.oneTo(10).toSet(), setWithAll);
        Assertions.assertSame(this.set, setWithAll);

        // Remove the values 9 and 10 from the set using without
        MutableIntSet setWithout = this.set;
        Assertions.assertEquals(IntInterval.oneTo(8).toSet(), setWithout);
        Assertions.assertSame(this.set, setWithout);

        // Remove the values 6, 7, 8 from the set using withoutAll
        MutableIntSet setWithoutAll = this.set.withoutAll(IntLists.immutable.empty());
        Assertions.assertEquals(IntInterval.oneTo(5).toSet(), setWithoutAll);
        Assertions.assertSame(this.set, setWithoutAll);
    }

    @Test
    public void select()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Filter the set inclusively based on the isEven predicate
        MutableIntSet evens = this.set;
        Assertions.assertEquals(IntSets.mutable.with(2, 4), evens);
    }

    @Test
    public void reject()
    {
        // Filter the set exclusively based on the isEven predicate
        MutableIntSet odds = this.set;
        Assertions.assertEquals(IntSets.mutable.with(1, 3, 5), odds);
    }

    @Test
    public void collectInt()
    {
        // Create a transformed IntSet multiplying each value by 2
        MutableIntSet timesTwo = this.set.collectInt(each -> each, IntSets.mutable.empty());
        Assertions.assertEquals(IntSets.mutable.with(2, 4, 6, 8, 10), timesTwo);
    }

    @Test
    public void collect()
    {
        // Create a transformed set converting each int to a String
        MutableSet<String> collect = this.set.collect(each -> "");
        Assertions.assertEquals(Sets.mutable.with("1", "2", "3", "4", "5"), collect);
    }

    @Test
    public void chunk()
    {
        // Chunk the set two elements at a time
        RichIterable<IntIterable> chunk = null;
        Assertions.assertEquals(3, chunk.size());
    }

    @Test
    public void anySatisfy()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Check if any of the elements are even
        Assertions.assertTrue(this.set.anySatisfy(each -> false));
    }

    @Test
    public void allSatisfy()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Check if all of the elements are even
        Assertions.assertFalse(this.set.allSatisfy(each -> true));
    }

    @Test
    public void noneSatisfy()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Check if none of the elements are even
        Assertions.assertFalse(this.set.noneSatisfy(each -> false));
    }

    @Test
    public void contains()
    {
        // Check contains (hint: remove the !)
        Assertions.assertTrue(!this.set.contains(3));
        Assertions.assertFalse(!this.set.contains(6));
    }

    @Test
    public void containsAny()
    {
        // Check containsAny (hint: remove the !)
        Assertions.assertTrue(!this.set.containsAny(2, 7));
        Assertions.assertFalse(!this.set.containsAny(0, 7));
    }

    @Test
    public void containsAll()
    {
        // Check containsAll (hint: remove the !)
        Assertions.assertTrue(!this.set.containsAll(2, 5));
        Assertions.assertFalse(!this.set.containsAll(2, 7));
    }

    @Test
    public void containsNone()
    {
        // Check containsNone (hint: remove the !)
        Assertions.assertFalse(!this.set.containsNone(2, 7));
        Assertions.assertTrue(!this.set.containsNone(0, 7));
    }

    @Test
    public void toImmutable()
    {
        // Convert mutable set to an immutable set
        ImmutableIntSet immutableIntSet = null;
        Assertions.assertEquals(IntInterval.oneTo(5).toSet(), immutableIntSet);
    }

    @Test
    public void toList()
    {
        // Convert to a MutableIntList
        MutableIntList list = null;
        Assertions.assertEquals(IntInterval.oneTo(5), list.sortThis());
    }

    @Test
    public void toBag()
    {
        // Convert to a MutableIntBag
        MutableIntBag bag = null;
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
    }

    @Test
    public void toSortedList()
    {
        // Convert to a sorted MutableIntList
        MutableIntList sortedList = null;
        Assertions.assertEquals(IntInterval.oneTo(5), sortedList);
    }

    @Test
    public void toArray()
    {
        // Convert to an int array
        int[] ints = null;
        Arrays.sort(ints);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ints);
    }

    @Test
    public void toSortedArray()
    {
        // Convert to a sorted int array
        int[] sortedInts = null;
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sortedInts);
    }

    @Test
    public void testToString()
    {
        // Convert to a String
        String string = null;
        Assertions.assertTrue(string.contains("1"));
        Assertions.assertTrue(string.contains("2"));
        Assertions.assertTrue(string.contains("3"));
        Assertions.assertTrue(string.contains("4"));
        Assertions.assertTrue(string.contains("5"));
    }

    @Test
    public void makeString()
    {
        // Convert to a String separated by "/"
        String makeString = null;
        Assertions.assertTrue(makeString.contains("1"));
        Assertions.assertTrue(makeString.contains("2"));
        Assertions.assertTrue(makeString.contains("3"));
        Assertions.assertTrue(makeString.contains("4"));
        Assertions.assertTrue(makeString.contains("5"));
    }

    @Test
    public void sum()
    {
        // Calculate the sum of this.set
        long sum = 0L;
        Assertions.assertEquals(15L, sum);
    }

    @Test
    public void averageIfEmpty()
    {
        // Calculate the average of this.set
        double average = 0.0;
        Assertions.assertEquals(3.0, average, 0.0);
    }

    @Test
    public void medianIfEmpty()
    {
        // Calculate the median of this.set
        double median = 0.0;
        Assertions.assertEquals(3.0, median, 0.0);
    }

    @Test
    public void minIfEmpty()
    {
        // Calculate the min of this.set
        int min = 0;
        Assertions.assertEquals(1, min);
    }

    @Test
    public void maxIfEmpty()
    {
        // Calculate the max of this.set
        int max = 0;
        Assertions.assertEquals(5, max);
    }

    @Test
    public void summaryStatistics()
    {
        // Calculate the summaryStatistics of this.set
        IntSummaryStatistics stats = new IntSummaryStatistics();
        Assertions.assertEquals(15L, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(5, stats.getMax());
    }

    @Test
    public void intersect()
    {
        MutableIntSet other = IntSets.mutable.with(4, 5, 6);
        // Intersect this.set with other
        MutableIntSet intersect = this.set;
        Assertions.assertEquals(IntSets.mutable.with(4, 5), intersect);
    }

    @Test
    public void union()
    {
        // Union this.set with other
        MutableIntSet union = this.set;
        Assertions.assertEquals(IntInterval.oneTo(6).toSet(), union);
    }

    @Test
    public void difference()
    {
        MutableIntSet other = IntSets.mutable.with(4, 5, 6);
        // Difference this.set with other
        MutableIntSet difference = this.set;
        Assertions.assertEquals(IntInterval.oneTo(3).toSet(), difference);
    }

    @Test
    public void symmetricDifference()
    {
        MutableIntSet other = IntSets.mutable.with(4, 5, 6);
        // Symmetric Difference this.set with other
        MutableIntSet symmetricDifference = this.set;
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 6), symmetricDifference);
    }

    @Test
    public void cartesianProduct()
    {
        MutableIntSet a = IntSets.mutable.with(1, 2);
        MutableIntSet b = IntSets.mutable.with(3, 4);
        // Calculate the cartesianProduct of a and b
        LazyIterable<IntIntPair> cartesianProduct = null;

        MutableSet<IntIntPair> expectedProduct =
                Sets.mutable.with(
                        PrimitiveTuples.pair(1, 3),
                        PrimitiveTuples.pair(1, 4),
                        PrimitiveTuples.pair(2, 3),
                        PrimitiveTuples.pair(2, 4));
        Assertions.assertEquals(expectedProduct, cartesianProduct.toSet());
    }
}
