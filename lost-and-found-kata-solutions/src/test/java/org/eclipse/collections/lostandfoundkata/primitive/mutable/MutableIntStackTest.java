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

import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.factory.primitive.IntStacks;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.stack.primitive.MutableIntStack;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class MutableIntStackTest
{
    private MutableIntStack stack;

    @BeforeEach
    void setUp()
    {
        this.stack = IntStacks.mutable.with(1, 2, 3, 4, 5);
    }

    @Test
    @Tag("SOLUTION")
    public void pushPopAndPeek()
    {
        // push
        this.stack.push(6);
        this.stack.push(7);
        this.stack.push(8);

        // withAll
        Assertions.assertEquals(IntStacks.mutable.withAll(IntInterval.oneTo(8)), this.stack);

        // pop and pop(count)
        Assertions.assertEquals(8, this.stack.pop());
        Assertions.assertEquals(IntLists.mutable.with(7, 6), this.stack.pop(2));

        // peek and peek(count)
        Assertions.assertEquals(5, this.stack.peek());
        Assertions.assertEquals(IntLists.mutable.with(5, 4), this.stack.peek(2));
    }

    @Test
    @Tag("SOLUTION")
    public void select()
    {
        // Filter the stack inclusively
        MutableIntStack evens = this.stack.select(each -> each % 2 == 0);
        Assertions.assertEquals(IntStacks.mutable.with(2, 4), evens);
    }

    @Test
    @Tag("SOLUTION")
    public void reject()
    {
        // Filter the stack exclusively
        MutableIntStack odds = this.stack.reject(each -> each % 2 == 0);
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
