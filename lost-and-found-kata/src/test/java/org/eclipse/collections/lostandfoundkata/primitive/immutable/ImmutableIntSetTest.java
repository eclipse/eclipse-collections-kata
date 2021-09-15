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
import org.eclipse.collections.api.set.ImmutableSet;
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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ImmutableIntSetTest
{
    private final ImmutableIntSet set = IntSets.immutable.with(1, 2, 3, 4, 5);

    /**
     * {@link ImmutableIntSet#newWith(int)} <br>
     * {@link ImmutableIntSet#newWithAll(IntIterable)} <br>
     * {@link ImmutableIntSet#newWithout(int)} <br>
     * {@link ImmutableIntSet#newWithoutAll(IntIterable)} <br>
     */
    @Test
    public void newWithAndWithout()
    {
        // Add the values 6 and 7 to this.set using with
        ImmutableIntSet setWith = this.set;
        Assertions.assertEquals(IntInterval.oneTo(7).toSet(), setWith);
        Assertions.assertNotSame(this.set, setWith);

        // Add the values 8, 9, 10 to setWith using newWithAll
        ImmutableIntSet setWithAll = setWith.newWithAll(IntLists.immutable.empty());
        Assertions.assertEquals(IntInterval.oneTo(10).toSet(), setWithAll);
        Assertions.assertNotSame(this.set, setWithAll);

        // Remove the values 9 and 10 from setWithAll using newWithout
        ImmutableIntSet setWithout = setWithAll;
        Assertions.assertEquals(IntInterval.oneTo(8).toSet(), setWithout);
        Assertions.assertNotSame(this.set, setWithout);

        // Remove the values 6, 7, 8 from setWithout using newWithoutAll
        ImmutableIntSet setWithoutAll = setWithout.newWithoutAll(IntLists.immutable.empty());
        Assertions.assertEquals(IntInterval.oneTo(5).toSet(), setWithoutAll);
        Assertions.assertNotSame(this.set, setWithoutAll);
    }

    /**
     * Inclusive Filter: {@link ImmutableIntSet#select(IntPredicate)} <br>
     * Exclusive Filter: {@link ImmutableIntSet#reject(IntPredicate)} <br>
     */
    @Test
    public void filtering()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Filter the set inclusively based on the isEven predicate
        ImmutableIntSet evens = this.set;
        Assertions.assertEquals(IntSets.mutable.with(2, 4), evens);

        // Filter the set exclusively based on the isEven predicate
        ImmutableIntSet odds = this.set;
        Assertions.assertEquals(IntSets.mutable.with(1, 3, 5), odds);
    }

    /**
     * {@link ImmutableIntSet#collectInt(IntToIntFunction, MutableIntCollection)} <br>
     * {@link ImmutableIntSet#collect(IntToObjectFunction)} <br>
     */
    @Test
    public void transforming()
    {
        // Created a transformed IntSet multiplying each value by 2
        MutableIntSet timesTwo = this.set.collectInt(each -> each, IntSets.mutable.empty());
        Assertions.assertEquals(IntSets.mutable.with(2, 4, 6, 8, 10), timesTwo);

        // Created a transformed set converting each int to a String
        ImmutableSet<String> collect = this.set.collect(each -> "");
        Assertions.assertEquals(Sets.mutable.with("1", "2", "3", "4", "5"), collect);
    }

    /**
     * {@link ImmutableIntSet#chunk(int)}
     */
    @Test
    public void chunking()
    {
        // Chunk the set two elements at a time
        RichIterable<IntIterable> chunk = null;
        Assertions.assertEquals(3, chunk.size());
    }

    /**
     * {@link ImmutableIntSet#anySatisfy(IntPredicate)} <br>
     * {@link ImmutableIntSet#allSatisfy(IntPredicate)} <br>
     * {@link ImmutableIntSet#noneSatisfy(IntPredicate)} <br>
     * {@link ImmutableIntSet#contains(int)} <br>
     * {@link ImmutableIntSet#containsAny(int...)} <br>
     * {@link ImmutableIntSet#containsAll(int...)} <br>
     * {@link ImmutableIntSet#containsNone(int...)} <br>
     */
    @Test
    public void testing()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Check if any, all or none of the elements are even
        Assertions.assertTrue(this.set.anySatisfy(each -> false));
        Assertions.assertFalse(this.set.allSatisfy(each -> true));
        Assertions.assertFalse(this.set.noneSatisfy(each -> true));

        // Check contains, containsAny, containsAll, containsNone (hint: remove the !)
        Assertions.assertTrue(!this.set.contains(3));
        Assertions.assertFalse(!this.set.contains(6));
        Assertions.assertTrue(!this.set.containsAny(2, 7));
        Assertions.assertFalse(!this.set.containsAny(0, 7));
        Assertions.assertTrue(!this.set.containsAll(2, 5));
        Assertions.assertFalse(!this.set.containsAll(2, 7));
        Assertions.assertFalse(!this.set.containsNone(2, 7));
        Assertions.assertTrue(!this.set.containsNone(0, 7));
    }

    /**
     * {@link ImmutableIntSet#toImmutable()} <br>
     * {@link ImmutableIntSet#toList()} <br>
     * {@link ImmutableIntSet#toBag()} <br>
     * {@link ImmutableIntSet#toSortedList()} <br>
     * {@link ImmutableIntSet#toArray()} <br>
     * {@link ImmutableIntSet#toSortedArray()} <br>
     * {@link ImmutableIntSet#toString()} <br>
     * {@link ImmutableIntSet#makeString(String)} <br>
     */
    @Test
    public void converting()
    {
        // Convert the set to an immutable set
        ImmutableIntSet immutableIntSet = null;
        Assertions.assertEquals(IntInterval.oneTo(5).toSet(), immutableIntSet);
        Assertions.assertSame(immutableIntSet, this.set);

        // Converter methods to other types
        // Convert to a MutableIntList
        MutableIntList list = null;
        Assertions.assertEquals(IntInterval.oneTo(5), list.sortThis());
        // Convert to a MutableIntBag
        MutableIntBag bag = null;
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
        // Convert to a sorted MutableIntList
        MutableIntList sortedList = null;
        Assertions.assertEquals(IntInterval.oneTo(5), sortedList);
        // Convert to an int array
        int[] ints = null;
        Arrays.sort(ints);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ints);
        // Convert to a sorted int array
        int[] sortedInts = null;
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sortedInts);
        // Convert to a String
        String string = null;
        Assertions.assertTrue(string.contains("1"));
        Assertions.assertTrue(string.contains("2"));
        Assertions.assertTrue(string.contains("3"));
        Assertions.assertTrue(string.contains("4"));
        Assertions.assertTrue(string.contains("5"));
        // Convert to a String separated by "/"
        String makeString = null;
        Assertions.assertTrue(makeString.contains("1"));
        Assertions.assertTrue(makeString.contains("2"));
        Assertions.assertTrue(makeString.contains("3"));
        Assertions.assertTrue(makeString.contains("4"));
        Assertions.assertTrue(makeString.contains("5"));
    }

    /**
     * {@link ImmutableIntSet#sum()} <br>
     * {@link ImmutableIntSet#average()} <br>
     * {@link ImmutableIntSet#averageIfEmpty(double)} <br>
     * {@link ImmutableIntSet#median()} <br>
     * {@link ImmutableIntSet#medianIfEmpty(double)} <br>
     * {@link ImmutableIntSet#min()} <br>
     * {@link ImmutableIntSet#minIfEmpty(int)} <br>
     * {@link ImmutableIntSet#max()} <br>
     * {@link ImmutableIntSet#maxIfEmpty(int)} <br>
     * {@link ImmutableIntSet#summaryStatistics()} <br>
     */
    @Test
    public void calculating()
    {
        // Calculate the sum of this.set
        long sum = 0L;
        Assertions.assertEquals(15L, sum);
        // Calculate the average of this.set
        double average = 0.0;
        Assertions.assertEquals(3.0, average, 0.0);
        // Calculate the median of this.set
        double median = 0.0;
        Assertions.assertEquals(3.0, median, 0.0);
        // Calculate the min of this.set
        int min = 0;
        Assertions.assertEquals(1, min);
        // Calculate the max of this.set
        int max = 0;
        Assertions.assertEquals(5, max);
        // Calculate the summaryStatistics of this.set
        IntSummaryStatistics stats = new IntSummaryStatistics();
        Assertions.assertEquals(stats.getSum(), sum);
        Assertions.assertEquals(stats.getMin(), min);
        Assertions.assertEquals(stats.getMax(), max);
    }

    /**
     * {@link ImmutableIntSet#intersect(IntSet)} <br>
     * {@link ImmutableIntSet#union(IntSet)} <br>
     * {@link ImmutableIntSet#difference(IntSet)} <br>
     * {@link ImmutableIntSet#symmetricDifference(IntSet)} <br>
     * {@link ImmutableIntSet#cartesianProduct(IntSet)} <br>
     */
    @Test
    public void setArithmetic()
    {
        MutableIntSet other = IntSets.mutable.with(4, 5, 6);
        // Intersect this.set with other
        ImmutableIntSet intersect = this.set;
        Assertions.assertEquals(IntSets.mutable.with(4, 5), intersect);
        // Union this.set with other
        ImmutableIntSet union = this.set;
        Assertions.assertEquals(IntInterval.oneTo(6).toSet(), union);
        // Difference this.set with other
        ImmutableIntSet difference = this.set;
        Assertions.assertEquals(IntInterval.oneTo(3).toSet(), difference);
        // Symmetric Difference this.set with other
        ImmutableIntSet symmetricDifference = this.set;
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 6), symmetricDifference);

        // Calculate the cartesianProduct of a and b
        MutableIntSet a = IntSets.mutable.with(1, 2);
        MutableIntSet b = IntSets.mutable.with(3, 4);
        LazyIterable<IntIntPair> product = null;

        MutableSet<IntIntPair> expectedProduct =
                Sets.mutable.with(
                        PrimitiveTuples.pair(1, 3),
                        PrimitiveTuples.pair(1, 4),
                        PrimitiveTuples.pair(2, 3),
                        PrimitiveTuples.pair(2, 4));
        Assertions.assertEquals(expectedProduct, product.toSet());
    }
}
