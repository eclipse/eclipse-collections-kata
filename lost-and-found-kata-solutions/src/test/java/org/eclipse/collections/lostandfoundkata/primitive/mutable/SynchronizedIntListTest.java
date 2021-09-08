/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.primitive.mutable;

import java.util.IntSummaryStatistics;

import org.eclipse.collections.api.bag.primitive.MutableIntBag;
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
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SynchronizedIntListTest
{
    private MutableIntList synchList = IntLists.mutable.with(1, 2, 3, 4, 5).asSynchronized();

    @Test
    @Tag("SOLUTION")
    public void converting()
    {
        MutableIntList list = this.synchList.toList();
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), list);

        MutableIntSet set = this.synchList.toSet();
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), set);

        MutableIntBag bag = this.synchList.toBag();
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
    }

    @Test
    @Tag("SOLUTION")
    public void filtering()
    {
        // Inclusive
        MutableIntList select = this.synchList.select(each -> each % 2 == 0);
        Assertions.assertEquals(IntInterval.evensFromTo(2, 4), select);

        // Exclusive
        MutableIntList reject = this.synchList.reject(each -> each % 2 == 0);  // Exclusive
        Assertions.assertEquals(IntInterval.oddsFromTo(1, 5), reject);
    }

    @Test
    @Tag("SOLUTION")
    public void transforming()
    {
        // Type Transformations using fused methods with target collections
        BooleanList booleanList = this.synchList.collectBoolean(each -> each % 2 == 0, BooleanLists.mutable.empty());
        var expectedBooleanList = BooleanLists.mutable.with(false, true, false, true, false);
        Assertions.assertEquals(expectedBooleanList, booleanList);

        ByteList byteList = this.synchList.collectByte(each -> (byte) each, ByteLists.mutable.empty());
        var expectedByteList = ByteLists.mutable.with((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        Assertions.assertEquals(expectedByteList, byteList);
        long byteSum = byteList.sum();
        Assertions.assertEquals(15L, byteSum);

        CharList charList = this.synchList.collectChar(each -> (char) each, CharLists.mutable.empty());
        var expectedCharList = CharLists.mutable.with((char) 1, (char) 2, (char) 3, (char) 4, (char) 5);
        Assertions.assertEquals(expectedCharList, charList);
        long charSum = charList.sum();
        Assertions.assertEquals(15L, charSum);

        ShortList shortList = this.synchList.collectShort(each -> (short) each, ShortLists.mutable.empty());
        var expectedShortList = ShortLists.mutable.with((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);
        Assertions.assertEquals(expectedShortList, shortList);
        long shortSum = shortList.sum();
        Assertions.assertEquals(15L, shortSum);

        FloatList floatList = this.synchList.collectFloat(each -> (float) each, FloatLists.mutable.empty());
        var expectedFloatList = FloatLists.mutable.with(1.0f, 2.0f, 3.0f, 4.0f, 5.0f);
        Assertions.assertEquals(expectedFloatList, floatList);
        double floatSum = floatList.sum();
        Assertions.assertEquals(15.0, floatSum, 0.0);

        LongList longList = this.synchList.collectLong(each -> (long) each, LongLists.mutable.empty());
        var expectedLongList = LongLists.mutable.with(1L, 2L, 3L, 4L, 5L);
        Assertions.assertEquals(expectedLongList, longList);
        long longSum = longList.sum();
        Assertions.assertEquals(15L, longSum);

        DoubleList doubleList = this.synchList.collectDouble(each -> (double) each, DoubleLists.mutable.empty());
        var expectedDoubleList = DoubleLists.mutable.with(1.0, 2.0, 3.0, 4.0, 5.0);
        Assertions.assertEquals(expectedDoubleList, doubleList.toList());
        double doubleSum = doubleList.sum();
        Assertions.assertEquals(15.0, doubleSum, 0.0);

        MutableList<String> stringList = this.synchList.collect(Integer::toString);
        var expectedStringList = Lists.mutable.with("1", "2", "3", "4", "5");
        Assertions.assertEquals(expectedStringList, stringList);
    }

    @Test
    @Tag("SOLUTION")
    public void calculating()
    {
        long sum = this.synchList.sum();
        int max = this.synchList.max();
        int maxIfEmpty = this.synchList.maxIfEmpty(0);
        int min = this.synchList.min();
        int minIfEmpty = this.synchList.minIfEmpty(0);
        double average = this.synchList.average();
        double averageIfEmpty = this.synchList.averageIfEmpty(0.0);
        double median = this.synchList.median();
        double medianIfEmpty = this.synchList.medianIfEmpty(0.0);
        IntSummaryStatistics stats = this.synchList.summaryStatistics();

        Assertions.assertEquals(15, sum);
        Assertions.assertEquals(1, min);
        Assertions.assertEquals(min, minIfEmpty);
        Assertions.assertEquals(5, max);
        Assertions.assertEquals(max, maxIfEmpty);
        Assertions.assertEquals(3.0, average, 0.0);
        Assertions.assertEquals(average, averageIfEmpty, 0.0);
        Assertions.assertEquals(3.0, median, 0.0);
        Assertions.assertEquals(median, medianIfEmpty, 0.0);
        Assertions.assertEquals(sum, stats.getSum());
        Assertions.assertEquals(min, stats.getMin());
        Assertions.assertEquals(max, stats.getMax());
        Assertions.assertEquals(average, stats.getAverage(), 0.0);
        Assertions.assertEquals(this.synchList.size(), stats.getCount());
    }
}
