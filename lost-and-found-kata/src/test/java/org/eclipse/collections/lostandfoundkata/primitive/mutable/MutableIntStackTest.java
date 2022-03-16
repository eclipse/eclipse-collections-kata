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

import java.util.IntSummaryStatistics;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.block.function.primitive.IntToIntFunction;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.stack.primitive.MutableIntStack;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntStacks;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@link org.eclipse.collections.api.factory.stack.primitive.MutableIntStackFactory#with} <br>
 * @see IntStacks <br>
 * <p/>
 * {@link MutableIntStack#push(int)} <br>
 * {@link MutableIntStack#pop()} <br>
 * {@link MutableIntStack#pop(int)} <br>
 * {@link MutableIntStack#peek()} <br>
 * {@link MutableIntStack#peek(int)} <br>
 * {@link org.eclipse.collections.api.factory.stack.primitive.MutableIntStackFactory#withAll(IntIterable)} <br>
 * <p/>
 * Filtering: <br>
 * {@link MutableIntStack#select(IntPredicate)} <br>
 * {@link MutableIntStack#reject(IntPredicate)} <br>
 * <p/>
 * Transforming: <br>
 * {@link MutableIntStack#collectInt(IntToIntFunction, MutableIntCollection)} <br>
 * <p/>
 * Converting: <br>
 * {@link MutableIntStack#toList()} <br>
 * {@link MutableIntStack#toSortedList()} <br>
 * {@link MutableIntStack#toSet()} <br>
 * {@link MutableIntStack#toBag()} <br>
 * <p/>
 * Calculating: <br>
 * {@link MutableIntStack#sum()} <br>
 * {@link MutableIntStack#average()} <br>
 * {@link MutableIntStack#averageIfEmpty(double)} <br>
 * {@link MutableIntStack#median()} <br>
 * {@link MutableIntStack#medianIfEmpty(double)} <br>
 * {@link MutableIntStack#min()} <br>
 * {@link MutableIntStack#minIfEmpty(int)} <br>
 * {@link MutableIntStack#max()} <br>
 * {@link MutableIntStack#maxIfEmpty(int)} <br>
 * {@link MutableIntStack#summaryStatistics()} <br>
 */
public class MutableIntStackTest
{
    private MutableIntStack stack;

    @BeforeEach
    void setUp()
    {
        this.stack = IntStacks.mutable.with(1, 2, 3, 4, 5);
    }

    @Test
    public void pushPopAndPeek()
    {
        // push 6, 7, 8 onto the this.stack
        this.stack.push(0);
        this.stack.push(0);
        this.stack.push(0);

        // withAll
        MutableIntStack expected = IntStacks.mutable.withAll(IntInterval.oneTo(8));
        Assertions.assertEquals(expected, this.stack);

        // pop and pop(count)
        // pop one element off of this.stack
        int popOne = 0;
        Assertions.assertEquals(8, popOne);
        // pop two elements off of this.stack
        IntList popTwo = null;
        Assertions.assertEquals(IntLists.mutable.with(7, 6), popTwo);

        // peek and peek(count)
        // peek at one element on this.stack
        int peekOne = 0;
        Assertions.assertEquals(5, peekOne);
        // peek at two elements on this.stack
        IntList peekTwo = null;
        Assertions.assertEquals(IntLists.mutable.with(5, 4), peekTwo);
    }

    @Test
    public void select()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Filter the stack inclusively based on the isEven predicate
        MutableIntStack evens = this.stack;
        Assertions.assertEquals(IntStacks.mutable.with(2, 4), evens);
    }

    @Test
    public void reject()
    {
        // Filter the bag exclusively based on the isEven predicate
        MutableIntStack odds = this.stack;
        Assertions.assertEquals(IntStacks.mutable.with(1, 3, 5), odds);
    }

    @Test
    public void collectInt()
    {
        // Create a transformed list multiplying each value by 2
        MutableIntList timesTwo = this.stack.collectInt(each -> each, IntLists.mutable.empty());
        Assertions.assertEquals(IntLists.mutable.with(10, 8, 6, 4, 2), timesTwo);
    }

    @Test
    public void toList()
    {
        // Convert to a MutableIntList
        MutableIntList list = null;
        Assertions.assertEquals(IntInterval.fromTo(5, 1), list);
    }

    @Test
    public void toSortedList()
    {
        // Convert to a sorted MutableIntList
        MutableIntList sortedList = null;
        Assertions.assertEquals(IntInterval.oneTo(5), sortedList);
    }

    @Test
    public void toSet()
    {
        // Convert to a MutableIntSet
        MutableIntSet set = null;
        Assertions.assertEquals(IntInterval.oneTo(5).toSet(), set);
    }

    @Test
    public void toBag()
    {
        // Convert to a MutableIntBag
        MutableIntBag bag = null;
        Assertions.assertEquals(IntInterval.oneTo(5).toBag(), bag);
    }

    @Test
    public void sum()
    {
        // Calculate the sum of this.stack
        long sum = 0L;
        Assertions.assertEquals(15L, sum);
    }

    @Test
    public void averageIfEmpty()
    {
        // Calculate the average of this.stack
        double average = 0.0;
        Assertions.assertEquals(3.0, average, 0.0);
    }

    @Test
    public void medianIfEmpty()
    {
        // Calculate the median of this.stack
        double median = 0.0;
        Assertions.assertEquals(3.0, median, 0.0);
    }

    @Test
    public void minIfEmpty()
    {
        // Calculate the min of this.stack
        int min = 0;
        Assertions.assertEquals(1, min);
    }

    @Test
    public void maxIfEmpty()
    {
        // Calculate the max of this.stack
        int max = 0;
        Assertions.assertEquals(5, max);
    }

    @Test
    public void summaryStatistics()
    {
        // Calculate the summaryStatistics of this.stack
        IntSummaryStatistics stats = new IntSummaryStatistics();
        Assertions.assertEquals(15L, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(5, stats.getMax());
    }
}
