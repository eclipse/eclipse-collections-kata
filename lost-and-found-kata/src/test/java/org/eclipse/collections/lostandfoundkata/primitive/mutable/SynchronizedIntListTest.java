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
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.block.function.primitive.IntToBooleanFunction;
import org.eclipse.collections.api.block.function.primitive.IntToByteFunction;
import org.eclipse.collections.api.block.function.primitive.IntToCharFunction;
import org.eclipse.collections.api.block.function.primitive.IntToDoubleFunction;
import org.eclipse.collections.api.block.function.primitive.IntToFloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntToLongFunction;
import org.eclipse.collections.api.block.function.primitive.IntToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.IntToShortFunction;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableByteCollection;
import org.eclipse.collections.api.collection.primitive.MutableCharCollection;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.MutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.MutableLongCollection;
import org.eclipse.collections.api.collection.primitive.MutableShortCollection;
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
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * {@link MutableIntList#asSynchronized()} <br>
 * <p/>
 * {@link MutableIntList#add(int)} <br>
 * {@link MutableIntList#size()} <br>
 * <p/>
 * {@link IntInterval#oneTo(int)} <br>
 * {@link IntInterval#primitiveParallelStream()} <br>
 * {@link java.util.stream.IntStream#forEach(IntConsumer)} <br>
 * {@link IntSupplier#getAsInt()} <br>
 * <p/>
 * Converting: <br>
 * {@link MutableIntList#toList()} <br>
 * {@link MutableIntList#toSet()} <br>
 * {@link MutableIntList#toBag()} <br>
 * <p/>
 * Filtering: <br>
 * {@link MutableIntList#select(IntPredicate)} <br>
 * {@link MutableIntList#reject(IntPredicate)} <br>
 * <p/>
 * Converting: <br>
 * {@link MutableIntList#collectBoolean(IntToBooleanFunction, MutableBooleanCollection)} <br>
 * {@link MutableIntList#collectByte(IntToByteFunction, MutableByteCollection)} <br>
 * {@link MutableIntList#collectChar(IntToCharFunction, MutableCharCollection)} <br>
 * {@link MutableIntList#collectShort(IntToShortFunction, MutableShortCollection)} <br>
 * {@link MutableIntList#collectFloat(IntToFloatFunction, MutableFloatCollection)} <br>
 * {@link MutableIntList#collectLong(IntToLongFunction, MutableLongCollection)} <br>
 * {@link MutableIntList#collectDouble(IntToDoubleFunction, MutableDoubleCollection)} <br>
 * {@link MutableIntList#collect(IntToObjectFunction)} <br>
 * <p/>
 * Calculating: <br>
 * {@link MutableIntList#sum()} <br>
 * {@link MutableIntList#max()} <br>
 * {@link MutableIntList#maxIfEmpty(int)} <br>
 * {@link MutableIntList#min()} <br>
 * {@link MutableIntList#minIfEmpty(int)} <br>
 * {@link MutableIntList#average()} <br>
 * {@link MutableIntList#averageIfEmpty(double)} <br>
 * {@link MutableIntList#median()} <br>
 * {@link MutableIntList#medianIfEmpty(double)} <br>
 * {@link MutableIntList#summaryStatistics()} <br>
 */
public class SynchronizedIntListTest
{
    private MutableIntList synchList;

    @BeforeEach
    void setUp()
    {
        // Make this IntList thread-safe by calling asSynchronized
        this.synchList = IntLists.mutable.with(1, 2, 3, 4, 5);
    }

    /**
     * Change the synchList initialization code in the setUp method to get this test to pass.
     */
    @Test
    public void isThreadSafe()
    {
        this.synchList.clear();
        this.assertThreadSafe(this.synchList::add, this.synchList::size);
    }

    public void assertThreadSafe(IntConsumer consumer, IntSupplier supplier)
    {
        IntInterval.oneTo(1000).primitiveParallelStream().forEach(consumer);
        Assertions.assertEquals(1000, supplier.getAsInt());
    }

    @Test
    public void toList()
    {
        // Convert this.synchList to a MutableIntList
        MutableIntList list = null;
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), list);
    }

    @Test
    public void toSet()
    {
        // Convert this.synchList to a MutableIntSet
        MutableIntSet set = null;
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), set);
    }

    @Test
    public void toBag()
    {
        // Convert this.synchList to a MutableIntBag
        MutableIntBag bag = null;
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
    }

    @Test
    public void select()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Filter the list inclusively based on the isEven predicate
        MutableIntList select = this.synchList;
        Assertions.assertEquals(IntInterval.evensFromTo(2, 4), select);
    }

    @Test
    public void reject()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Filter the list exclusively based on the isEven predicate
        MutableIntList reject = this.synchList;
        Assertions.assertEquals(IntInterval.oddsFromTo(1, 5), reject);
    }

    @Test
    public void collectBoolean()
    {
        // Collect to MutableBooleanList evens=true, odds=false
        BooleanList booleanList = null;
        var expectedBooleanList = BooleanLists.mutable.with(false, true, false, true, false);
        Assertions.assertEquals(expectedBooleanList, booleanList);
    }

    @Test
    public void collectByte()
    {
        // Collect to MutableByteList the values as is
        ByteList byteList = null;
        var expectedByteList = ByteLists.mutable.with((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        Assertions.assertEquals(expectedByteList, byteList);
        long byteSum = byteList.sum();
        Assertions.assertEquals(15L, byteSum);
    }

    @Test
    public void collectChar()
    {
        // Collect to MutableCharList the values as is
        CharList charList = null;
        var expectedCharList = CharLists.mutable.with((char) 1, (char) 2, (char) 3, (char) 4, (char) 5);
        Assertions.assertEquals(expectedCharList, charList);
        long charSum = charList.sum();
        Assertions.assertEquals(15L, charSum);
    }

    @Test
    public void collectShort()
    {
        // Collect to MutableShortList the values as is
        ShortList shortList = null;
        var expectedShortList = ShortLists.mutable.with((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);
        Assertions.assertEquals(expectedShortList, shortList);
        long shortSum = shortList.sum();
        Assertions.assertEquals(15L, shortSum);
    }

    @Test
    public void collectFloat()
    {
        // Collect to MutableFloatList the values as is
        FloatList floatList = null;
        var expectedFloatList = FloatLists.mutable.with(1.0f, 2.0f, 3.0f, 4.0f, 5.0f);
        Assertions.assertEquals(expectedFloatList, floatList);
        double floatSum = floatList.sum();
        Assertions.assertEquals(15.0, floatSum, 0.0);
    }

    @Test
    public void collectLong()
    {
        // Collect to MutableLongList the values as is
        LongList longList = null;
        var expectedLongList = LongLists.mutable.with(1L, 2L, 3L, 4L, 5L);
        Assertions.assertEquals(expectedLongList, longList);
        long longSum = longList.sum();
        Assertions.assertEquals(15L, longSum);
    }

    @Test
    public void collectDouble()
    {
        // Collect to MutableDoubleList the values as is
        DoubleList doubleList = null;
        var expectedDoubleList = DoubleLists.mutable.with(1.0, 2.0, 3.0, 4.0, 5.0);
        Assertions.assertEquals(expectedDoubleList, doubleList);
        double doubleSum = doubleList.sum();
        Assertions.assertEquals(15.0, doubleSum, 0.0);
    }

    @Test
    public void collect()
    {
        // Collect to MutableList of String the values as Strings
        MutableList<String> stringList = null;
        var expectedStringList = Lists.mutable.with("1", "2", "3", "4", "5");
        Assertions.assertEquals(expectedStringList, stringList);
    }

    @Test
    public void sum()
    {
        // Calculate the sum of this.syncList
        long sum = 0L;
        Assertions.assertEquals(15, sum);
    }

    @Test
    public void max()
    {
        // Calculate the max of this.syncList
        int max = 0;
        Assertions.assertEquals(5, max);
    }

    @Test
    public void maxIfEmpty()
    {
        // Calculate the max of this.syncList, and 0 if empty
        int maxIfEmpty = 0;
        Assertions.assertEquals(5, maxIfEmpty);
    }

    @Test
    public void min()
    {
        // Calculate the min of this.syncList
        int min = 0;
        Assertions.assertEquals(1, min);
    }

    @Test
    public void minIfEmpty()
    {
        // Calculate the min of this.syncList, and 0 if empty
        int minIfEmpty = 0;
        Assertions.assertEquals(1, minIfEmpty);
    }

    @Test
    public void average()
    {
        // Calculate the average of this.syncList
        double average = 0.0;
        Assertions.assertEquals(3.0, average, 0.0);
    }

    @Test
    public void averageIfEmpty()
    {
        // Calculate the average of this.syncList, and 0.0 if empty
        double averageIfEmpty = 0.0;
        Assertions.assertEquals(3.0, averageIfEmpty, 0.0);
    }

    @Test
    public void median()
    {
        // Calculate the median of this.syncList
        double median = 0.0;
        Assertions.assertEquals(3.0, median, 0.0);
    }

    @Test
    public void medianIfEmpty()
    {
        // Calculate the median of this.syncList, and 0.0 if empty
        double medianIfEmpty = 0.0;
        Assertions.assertEquals(3.0, medianIfEmpty, 0.0);
    }

    @Test
    public void summaryStatistics()
    {
        // Calculate the summaryStatistics of this.syncList
        IntSummaryStatistics stats = new IntSummaryStatistics();
        Assertions.assertEquals(15L, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(5, stats.getMax());
        Assertions.assertEquals(3.0, stats.getAverage(), 0.0);
        Assertions.assertEquals(this.synchList.size(), stats.getCount());
    }
}
