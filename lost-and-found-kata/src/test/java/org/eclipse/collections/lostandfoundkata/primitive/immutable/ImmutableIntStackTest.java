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

import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.block.function.primitive.IntToIntFunction;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.stack.primitive.ImmutableIntStack;
import org.eclipse.collections.api.stack.primitive.MutableIntStack;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntStacks;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * {@link ImmutableIntStack#push(int)} <br>
 * {@link ImmutableIntStack#pop()} <br>
 * {@link ImmutableIntStack#pop(int)} <br>
 * {@link ImmutableIntStack#peek()} <br>
 * {@link ImmutableIntStack#peek(int)} <br>
 * @see IntStacks <br>
 * <p/>
 * Filtering: <br>
 * {@link ImmutableIntStack#select(IntPredicate)} <br>
 * {@link ImmutableIntStack#reject(IntPredicate)} <br>
 * <p/>
 * Transforming: <br>
 * {@link ImmutableIntStack#collectInt(IntToIntFunction, MutableIntCollection)} <br>
 * <p/>
 * Converting: <br>
 * {@link ImmutableIntStack#toList()} <br>
 * {@link ImmutableIntStack#toSortedList()} <br>
 * {@link ImmutableIntStack#toSet()} <br>
 * {@link ImmutableIntStack#toBag()} <br>
 * <p/>
 * Calculating: <br>
 * {@link ImmutableIntStack#sum()} <br>
 * {@link ImmutableIntStack#average()} <br>
 * {@link ImmutableIntStack#averageIfEmpty(double)} <br>
 * {@link ImmutableIntStack#median()} <br>
 * {@link ImmutableIntStack#medianIfEmpty(double)} <br>
 * {@link ImmutableIntStack#min()} <br>
 * {@link ImmutableIntStack#minIfEmpty(int)} <br>
 * {@link ImmutableIntStack#max()} <br>
 * {@link ImmutableIntStack#maxIfEmpty(int)} <br>
 * {@link ImmutableIntStack#summaryStatistics()} <br>
 */
public class ImmutableIntStackTest
{
    private final ImmutableIntStack stack = IntStacks.immutable.with(1, 2, 3, 4, 5);

    @Test
    public void pushPopAndPeek()
    {
        // push 6, 7, 8 onto the this.stack resulting in three new stacks
        ImmutableIntStack stack1 = this.stack;
        ImmutableIntStack stack2 = stack1;
        ImmutableIntStack stack3 = stack2;

        // withAll
        Assertions.assertEquals(IntStacks.immutable.withAll(IntInterval.oneTo(6)), stack1);
        Assertions.assertEquals(IntStacks.immutable.withAll(IntInterval.oneTo(7)), stack2);
        Assertions.assertEquals(IntStacks.immutable.withAll(IntInterval.oneTo(8)), stack3);

        // pop and pop(count)
        // pop one element off of stack3
        ImmutableIntStack popOne = stack3;
        Assertions.assertEquals(stack2, popOne);
        // pop two elements off of stack2
        ImmutableIntStack popTwo = stack2;
        Assertions.assertEquals(this.stack, popTwo);

        // peek and peek(count)
        // peek one element on this.stack
        int peekOne = 0;
        Assertions.assertEquals(5, peekOne);
        // peek two elements on this.stack
        IntList peekTwo = null;
        Assertions.assertEquals(IntLists.mutable.with(5, 4), peekTwo);
    }

    @Test
    public void select()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Filter the stack inclusively based on the isEven predicate
        ImmutableIntStack evens = this.stack;
        Assertions.assertEquals(IntStacks.mutable.with(2, 4), evens);
    }

    @Test
    public void reject()
    {
        // Filter the bag exclusively based on the isEven predicate
        ImmutableIntStack odds = this.stack;
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
