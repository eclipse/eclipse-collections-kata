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
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.primitive.BooleanLists;
import org.eclipse.collections.api.factory.primitive.ByteLists;
import org.eclipse.collections.api.factory.primitive.CharLists;
import org.eclipse.collections.api.factory.primitive.DoubleLists;
import org.eclipse.collections.api.factory.primitive.FloatLists;
import org.eclipse.collections.api.factory.primitive.IntBags;
import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.factory.primitive.IntSets;
import org.eclipse.collections.api.factory.primitive.LongLists;
import org.eclipse.collections.api.factory.primitive.ShortLists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.BooleanList;
import org.eclipse.collections.api.list.primitive.ByteList;
import org.eclipse.collections.api.list.primitive.CharList;
import org.eclipse.collections.api.list.primitive.DoubleList;
import org.eclipse.collections.api.list.primitive.FloatList;
import org.eclipse.collections.api.list.primitive.LongList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.ShortList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SynchronizedIntListTest
{
    private MutableIntList synchList;

    @BeforeEach
    void setUp()
    {
        this.synchList = IntLists.mutable.with(1, 2, 3, 4, 5).asSynchronized();
    }

    @Test
    @Tag("SOLUTION")
    public void isThreadSafe()
    {
        this.synchList.clear();
        this.assertThreadSafe(this.synchList::add, this.synchList::size);
    }

    private void assertThreadSafe(IntConsumer consumer, IntSupplier supplier)
    {
        IntInterval.oneTo(1000).primitiveParallelStream().forEach(consumer);
        Assertions.assertEquals(1000, supplier.getAsInt());
    }

    @Test
    @Tag("SOLUTION")
    public void toList()
    {
        MutableIntList list = this.synchList.toList();
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), list);
    }

    @Test
    @Tag("SOLUTION")
    public void toSet()
    {
        MutableIntSet set = this.synchList.toSet();
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), set);
    }

    @Test
    @Tag("SOLUTION")
    public void toBag()
    {
        MutableIntBag bag = this.synchList.toBag();
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
    }

    @Test
    @Tag("SOLUTION")
    public void select()
    {
        // Inclusive
        MutableIntList select = this.synchList.select(each -> each % 2 == 0);
        Assertions.assertEquals(IntInterval.evensFromTo(2, 4), select);
    }

    @Test
    @Tag("SOLUTION")
    public void reject()
    {
        // Exclusive
        MutableIntList reject = this.synchList.reject(each -> each % 2 == 0);  // Exclusive
        Assertions.assertEquals(IntInterval.oddsFromTo(1, 5), reject);
    }

    @Test
    @Tag("SOLUTION")
    public void collectBoolean()
    {
        BooleanList booleanList = this.synchList.collectBoolean(each -> each % 2 == 0, BooleanLists.mutable.empty());
        var expectedBooleanList = BooleanLists.mutable.with(false, true, false, true, false);
        Assertions.assertEquals(expectedBooleanList, booleanList);
    }

    @Test
    @Tag("SOLUTION")
    public void collectByte()
    {
        ByteList byteList = this.synchList.collectByte(each -> (byte) each, ByteLists.mutable.empty());
        var expectedByteList = ByteLists.mutable.with((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        Assertions.assertEquals(expectedByteList, byteList);
        long byteSum = byteList.sum();
        Assertions.assertEquals(15L, byteSum);
    }

    @Test
    @Tag("SOLUTION")
    public void collectChar()
    {
        CharList charList = this.synchList.collectChar(each -> (char) each, CharLists.mutable.empty());
        var expectedCharList = CharLists.mutable.with((char) 1, (char) 2, (char) 3, (char) 4, (char) 5);
        Assertions.assertEquals(expectedCharList, charList);
        long charSum = charList.sum();
        Assertions.assertEquals(15L, charSum);
    }

    @Test
    @Tag("SOLUTION")
    public void collectShort()
    {
        ShortList shortList = this.synchList.collectShort(each -> (short) each, ShortLists.mutable.empty());
        var expectedShortList = ShortLists.mutable.with((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);
        Assertions.assertEquals(expectedShortList, shortList);
        long shortSum = shortList.sum();
        Assertions.assertEquals(15L, shortSum);
    }

    @Test
    @Tag("SOLUTION")
    public void collectFloat()
    {
        FloatList floatList = this.synchList.collectFloat(each -> (float) each, FloatLists.mutable.empty());
        var expectedFloatList = FloatLists.mutable.with(1.0f, 2.0f, 3.0f, 4.0f, 5.0f);
        Assertions.assertEquals(expectedFloatList, floatList);
        double floatSum = floatList.sum();
        Assertions.assertEquals(15.0, floatSum, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void collectLong()
    {
        LongList longList = this.synchList.collectLong(each -> (long) each, LongLists.mutable.empty());
        var expectedLongList = LongLists.mutable.with(1L, 2L, 3L, 4L, 5L);
        Assertions.assertEquals(expectedLongList, longList);
        long longSum = longList.sum();
        Assertions.assertEquals(15L, longSum);
    }

    @Test
    @Tag("SOLUTION")
    public void collectDouble()
    {
        DoubleList doubleList = this.synchList.collectDouble(each -> (double) each, DoubleLists.mutable.empty());
        var expectedDoubleList = DoubleLists.mutable.with(1.0, 2.0, 3.0, 4.0, 5.0);
        Assertions.assertEquals(expectedDoubleList, doubleList);
        double doubleSum = doubleList.sum();
        Assertions.assertEquals(15.0, doubleSum, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void collect()
    {
        MutableList<String> stringList = this.synchList.collect(Integer::toString);
        var expectedStringList = Lists.mutable.with("1", "2", "3", "4", "5");
        Assertions.assertEquals(expectedStringList, stringList);
    }

    @Test
    @Tag("SOLUTION")
    public void sum()
    {
        long sum = this.synchList.sum();
        Assertions.assertEquals(15, sum);
    }

    @Test
    @Tag("SOLUTION")
    public void max()
    {
        int max = this.synchList.max();
        Assertions.assertEquals(5, max);
    }

    @Test
    @Tag("SOLUTION")
    public void maxIfEmpty()
    {
        int maxIfEmpty = this.synchList.maxIfEmpty(0);
        Assertions.assertEquals(5, maxIfEmpty);
    }

    @Test
    @Tag("SOLUTION")
    public void min()
    {
        int min = this.synchList.min();
        Assertions.assertEquals(1, min);
    }

    @Test
    @Tag("SOLUTION")
    public void minIfEmpty()
    {
        int minIfEmpty = this.synchList.minIfEmpty(0);
        Assertions.assertEquals(1, minIfEmpty);
    }

    @Test
    @Tag("SOLUTION")
    public void average()
    {
        double average = this.synchList.average();
        Assertions.assertEquals(3.0, average, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void averageIfEmpty()
    {
        double averageIfEmpty = this.synchList.averageIfEmpty(0.0);
        Assertions.assertEquals(3.0, averageIfEmpty, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void median()
    {
        double median = this.synchList.median();
        Assertions.assertEquals(3.0, median, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void medianIfEmpty()
    {
        double medianIfEmpty = this.synchList.medianIfEmpty(0.0);
        Assertions.assertEquals(3.0, medianIfEmpty, 0.0);
    }

    @Test
    @Tag("SOLUTION")
    public void summaryStatistics()
    {
        IntSummaryStatistics stats = this.synchList.summaryStatistics();
        Assertions.assertEquals(15L, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(5, stats.getMax());
        Assertions.assertEquals(3.0, stats.getAverage(), 0.0);
        Assertions.assertEquals(this.synchList.size(), stats.getCount());
    }
}
