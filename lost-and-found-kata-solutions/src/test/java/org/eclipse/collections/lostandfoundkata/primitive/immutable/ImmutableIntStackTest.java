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

import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.factory.primitive.IntStacks;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.stack.primitive.ImmutableIntStack;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ImmutableIntStackTest
{
    private final ImmutableIntStack stack = IntStacks.immutable.with(1, 2, 3, 4, 5);

    @Test
    @Tag("SOLUTION")
    public void pushPopAndPeek()
    {
        // push
        ImmutableIntStack stack1 = this.stack.push(6);
        ImmutableIntStack stack2 = stack1.push(7);
        ImmutableIntStack stack3 = stack2.push(8);

        // withAll
        Assertions.assertEquals(IntStacks.immutable.withAll(IntInterval.oneTo(6)), stack1);
        Assertions.assertEquals(IntStacks.immutable.withAll(IntInterval.oneTo(7)), stack2);
        Assertions.assertEquals(IntStacks.immutable.withAll(IntInterval.oneTo(8)), stack3);

        // pop and pop(count)
        Assertions.assertEquals(stack2, stack3.pop());
        Assertions.assertEquals(this.stack, stack2.pop(2));

        // peek and peek(count)
        Assertions.assertEquals(5, this.stack.peek());
        Assertions.assertEquals(IntLists.mutable.with(5, 4), this.stack.peek(2));
    }

    @Test
    @Tag("SOLUTION")
    public void select()
    {
        // Filter the stack inclusively
        ImmutableIntStack evens = this.stack.select(each -> each % 2 == 0);
        Assertions.assertEquals(IntStacks.mutable.with(2, 4), evens);
    }

    @Test
    @Tag("SOLUTION")
    public void reject()
    {
        // Filter the stack exclusively
        ImmutableIntStack odds = this.stack.reject(each -> each % 2 == 0);
        Assertions.assertEquals(IntStacks.mutable.with(1, 3, 5), odds);
    }

    @Test
    @Tag("SOLUTION")
    public void collectInt()
    {
        // Created a transformed list multiplying each value by 2
        MutableIntList timesTwo = this.stack.collectInt(each -> each * 2, IntLists.mutable.empty());
        Assertions.assertEquals(IntLists.mutable.with(10, 8, 6, 4, 2), timesTwo);
    }

    @Test
    @Tag("SOLUTION")
    public void toList()
    {
        // Converter methods
        Assertions.assertEquals(IntInterval.fromTo(5, 1), this.stack.toList());
    }

    @Test
    @Tag("SOLUTION")
    public void toSortedList()
    {
        Assertions.assertEquals(IntInterval.oneTo(5), this.stack.toSortedList());
    }

    @Test
    @Tag("SOLUTION")
    public void toSet()
    {
        Assertions.assertEquals(IntInterval.oneTo(5).toSet(), this.stack.toSet());
    }

    @Test
    @Tag("SOLUTION")
    public void toBag()
    {
        Assertions.assertEquals(IntInterval.oneTo(5).toBag(), this.stack.toBag());
    }

    @Test
    @Tag("SOLUTION")
    public void sum()
    {
        // Math
        long sum = this.stack.sum();
        Assertions.assertEquals(15L, sum);
    }

    @Test
    @Tag("SOLUTION")
    public void averageIfEmpty()
    {
        double average = this.stack.averageIfEmpty(0.0);
        Assertions.assertEquals(3.0, average, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void medianIfEmpty()
    {
        double median = this.stack.medianIfEmpty(0.0);
        Assertions.assertEquals(3.0, median, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void minIfEmpty()
    {
        int min = this.stack.minIfEmpty(0);
        Assertions.assertEquals(1, min);
    }

    @Test
    @Tag("SOLUTION")
    public void maxIfEmpty()
    {
        int max = this.stack.maxIfEmpty(0);
        Assertions.assertEquals(5, max);
    }

    @Test
    @Tag("SOLUTION")
    public void summaryStatistics()
    {
        IntSummaryStatistics stats = this.stack.summaryStatistics();
        Assertions.assertEquals(15L, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(5, stats.getMax());
    }
}
