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

import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.LazyByteIterable;
import org.eclipse.collections.api.LazyCharIterable;
import org.eclipse.collections.api.LazyDoubleIterable;
import org.eclipse.collections.api.LazyFloatIterable;
import org.eclipse.collections.api.LazyIntIterable;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.LazyLongIterable;
import org.eclipse.collections.api.LazyShortIterable;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.block.function.primitive.IntToBooleanFunction;
import org.eclipse.collections.api.block.function.primitive.IntToByteFunction;
import org.eclipse.collections.api.block.function.primitive.IntToCharFunction;
import org.eclipse.collections.api.block.function.primitive.IntToDoubleFunction;
import org.eclipse.collections.api.block.function.primitive.IntToFloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntToIntFunction;
import org.eclipse.collections.api.block.function.primitive.IntToLongFunction;
import org.eclipse.collections.api.block.function.primitive.IntToShortFunction;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.factory.primitive.ByteLists;
import org.eclipse.collections.impl.factory.primitive.CharLists;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.eclipse.collections.impl.factory.primitive.FloatLists;
import org.eclipse.collections.impl.factory.primitive.IntBags;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.factory.primitive.LongLists;
import org.eclipse.collections.impl.factory.primitive.ShortLists;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * {@link LazyIntIterable#toList()} <br>
 * {@link LazyIntIterable#toSet()} <br>
 * {@link LazyIntIterable#toBag()} <br>
 * {@link LazyIntIterable#select(IntPredicate)} <br>
 * {@link LazyIntIterable#reject(IntPredicate)} <br>
 * {@link LazyIntIterable#collectBoolean(IntToBooleanFunction)} <br>
 * {@link LazyIntIterable#collectByte(IntToByteFunction)} <br>
 * {@link LazyIntIterable#collectChar(IntToCharFunction)} <br>
 * {@link LazyIntIterable#collectShort(IntToShortFunction)} <br>
 * {@link LazyIntIterable#collectInt(IntToIntFunction)} <br>
 * {@link LazyIntIterable#collectFloat(IntToFloatFunction)} <br>
 * {@link LazyIntIterable#collectLong(IntToLongFunction)} <br>
 * {@link LazyIntIterable#collectDouble(IntToDoubleFunction)} <br>
 * {@link LazyIntIterable#sum()} <br>
 * {@link LazyIntIterable#max()} <br>
 * {@link LazyIntIterable#maxIfEmpty(int)} <br>
 * {@link LazyIntIterable#min()} <br>
 * {@link LazyIntIterable#minIfEmpty(int)} <br>
 * {@link LazyIntIterable#average()} <br>
 * {@link LazyIntIterable#averageIfEmpty(double)} <br>
 * {@link LazyIntIterable#median()} <br>
 * {@link LazyIntIterable#medianIfEmpty(double)} <br>
 * {@link LazyIntIterable#summaryStatistics()} <br>
 */
public class LazyIntIterableTest
{
    private LazyIntIterable lazyIntIterable = IntInterval.oneTo(5).asLazy();

    @Test
    public void toList()
    {
        // Convert to a MutableIntList
        MutableIntList list = null;
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), list);
    }

    @Test
    public void toSet()
    {
        // Convert to a MutableIntSet
        MutableIntSet set = null;
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), set);
    }

    @Test
    public void toBag()
    {
        // Convert to a MutableIntBag
        MutableIntBag bag = null;
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
    }

    @Test
    public void select()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Filter the lazyIntIterable inclusively based on the isEven predicate
        LazyIntIterable select = null;
        Assertions.assertEquals(IntInterval.evensFromTo(2, 4), select.toList());
    }

    @Test
    public void reject()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Filter the lazyIntIterable exclusively based on the isEven predicate
        LazyIntIterable reject = null;
        Assertions.assertEquals(IntInterval.oddsFromTo(1, 5), reject.toList());
    }

    @Test
    public void collect()
    {
        // Collect to LazyIterable of String the values as Strings
        LazyIterable<String> lazyIterable = null;
        var expectedStringList = Lists.mutable.with("1", "2", "3", "4", "5");
        Assertions.assertEquals(expectedStringList, lazyIterable.toList());
    }

    @Test
    public void collectBoolean()
    {
        // Collect to LazyBooleanIterable evens=true, odds=false
        LazyBooleanIterable lazyBooleanIterable = null;
        var expectedBooleanList = BooleanLists.mutable.with(false, true, false, true, false);
        Assertions.assertEquals(expectedBooleanList, lazyBooleanIterable.toList());
    }

    @Test
    public void collectByte()
    {
        // Collect to LazyByteIterable the values as is
        LazyByteIterable lazyByteIterable = null;
        var expectedByteList = ByteLists.mutable.with((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        Assertions.assertEquals(expectedByteList, lazyByteIterable.toList());
        long byteSum = lazyByteIterable.sum();
        Assertions.assertEquals(15L, byteSum);
    }

    @Test
    public void collectChar()
    {
        // Collect to LazyCharIterable the values as is
        LazyCharIterable lazyCharIterable = null;
        var expectedCharList = CharLists.mutable.with((char) 1, (char) 2, (char) 3, (char) 4, (char) 5);
        Assertions.assertEquals(expectedCharList, lazyCharIterable.toList());
        long charSum = lazyCharIterable.sum();
        Assertions.assertEquals(15L, charSum);
    }

    @Test
    public void collectShort()
    {
        // Collect to LazyShortIterable the values as is
        LazyShortIterable lazyShortIterable = null;
        var expectedShortList = ShortLists.mutable.with((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);
        Assertions.assertEquals(expectedShortList, lazyShortIterable.toList());
        long shortSum = lazyShortIterable.sum();
        Assertions.assertEquals(15L, shortSum);
    }

    @Test
    public void collectFloat()
    {
        // Collect to LazyFloatIterable the values as is
        LazyFloatIterable lazyFloatIterable = null;
        var expectedFloatList = FloatLists.mutable.with(1.0f, 2.0f, 3.0f, 4.0f, 5.0f);
        Assertions.assertEquals(expectedFloatList, lazyFloatIterable.toList());
        double floatSum = lazyFloatIterable.sum();
        Assertions.assertEquals(15.0, floatSum, 0.0);
    }

    @Test
    public void collectLong()
    {
        // Collect to LazyLongIterable the values as is
        LazyLongIterable lazyLongIterable = null;
        var expectedLongList = LongLists.mutable.with(1L, 2L, 3L, 4L, 5L);
        Assertions.assertEquals(expectedLongList, lazyLongIterable.toList());
        long longSum = lazyLongIterable.sum();
        Assertions.assertEquals(15L, longSum);
    }

    @Test
    public void collectDouble()
    {
        // Collect to LazyDoubleIterable the values as is
        LazyDoubleIterable lazyDoubleIterable = null;
        var expectedDoubleList = DoubleLists.mutable.with(1.0, 2.0, 3.0, 4.0, 5.0);
        Assertions.assertEquals(expectedDoubleList, lazyDoubleIterable.toList());
        double doubleSum = lazyDoubleIterable.sum();
        Assertions.assertEquals(15.0, doubleSum, 0.0);
    }

    @Test
    public void sum()
    {
        // Calculate the sum of this.lazyIntIterable
        long sum = 0L;
        Assertions.assertEquals(15, sum);
    }

    @Test
    public void average()
    {
        // Calculate the average of this.lazyIntIterable
        double average = 0.0;
        Assertions.assertEquals(3.0, average, 0.0);
        // Calculate the average of this.lazyIntIterable, 0.0 if empty
        double averageIfEmpty = 0.0;
        Assertions.assertEquals(average, averageIfEmpty, 0.0);
    }

    @Test
    public void median()
    {
        // Calculate the median of this.lazyIntIterable
        double median = this.lazyIntIterable.median();
        Assertions.assertEquals(3.0, median, 0.0);
        // Calculate the median of this.lazyIntIterable, 0.0 if empty
        double medianIfEmpty = 0.0;
        Assertions.assertEquals(median, medianIfEmpty, 0.0);
    }

    @Test
    public void min()
    {
        // Calculate the min of this.lazyIntIterable
        int min = 0;
        Assertions.assertEquals(1, min);
        // Calculate the min of this.lazyIntIterable, 0 if empty
        int minIfEmpty = 0;
        Assertions.assertEquals(min, minIfEmpty);
    }

    @Test
    public void max()
    {
        // Calculate the max of this.lazyIntIterable
        int max = 0;
        Assertions.assertEquals(5, max);
        // Calculate the max of this.lazyIntIterable, 0 if empty
        int maxIfEmpty = 0;
        Assertions.assertEquals(max, maxIfEmpty);
    }

    @Test
    public void summaryStatistics()
    {
        // Calculate the summaryStatistics of this.lazyIntIterable
        IntSummaryStatistics stats = null;
        Assertions.assertEquals(15, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(5, stats.getMax());
        Assertions.assertEquals(3.0, stats.getAverage(), 0.0);
        Assertions.assertEquals(this.lazyIntIterable.size(), stats.getCount());
    }
}
