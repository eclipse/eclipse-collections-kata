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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UnmodifiableIntListTest
{
    private MutableIntList unmodifiableList = IntLists.mutable.with(1, 2, 3, 4, 5).asUnmodifiable();

    @Test
    @Tag("SOLUTION")
    public void isUnmodifiable()
    {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.add(6));
    }

    @Test
    @Tag("SOLUTION")
    public void converting()
    {
        // Converter methods
        MutableIntList list = this.unmodifiableList.toList();
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), list);

        MutableIntSet set = this.unmodifiableList.toSet();
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), set);

        MutableIntBag bag = this.unmodifiableList.toBag();
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
    }

    @Test
    @Tag("SOLUTION")
    public void filtering()
    {
        MutableIntList select = this.unmodifiableList.select(each -> each % 2 == 0);  // Inclusive
        Assertions.assertEquals(IntInterval.evensFromTo(2, 4), select);

        MutableIntList reject = this.unmodifiableList.reject(each -> each % 2 == 0);  // Exclusive
        Assertions.assertEquals(IntInterval.oddsFromTo(1, 5), reject);
    }

    @Test
    @Tag("SOLUTION")
    public void transforming()
    {
        // Type Transformations using fused methods with target collections
        BooleanList booleanList = this.unmodifiableList.collectBoolean(each -> each % 2 == 0, BooleanLists.mutable.empty());
        var expectedBooleanList = BooleanLists.mutable.with(false, true, false, true, false);
        Assertions.assertEquals(expectedBooleanList, booleanList);

        ByteList byteList = this.unmodifiableList.collectByte(each -> (byte) each, ByteLists.mutable.empty());
        var expectedByteList = ByteLists.mutable.with((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        Assertions.assertEquals(expectedByteList, byteList);
        long byteSum = byteList.sum();
        Assertions.assertEquals(15L, byteSum);

        CharList charList = this.unmodifiableList.collectChar(each -> (char) each, CharLists.mutable.empty());
        var expectedCharList = CharLists.mutable.with((char) 1, (char) 2, (char) 3, (char) 4, (char) 5);
        Assertions.assertEquals(expectedCharList, charList);
        long charSum = charList.sum();
        Assertions.assertEquals(15L, charSum);

        ShortList shortList = this.unmodifiableList.collectShort(each -> (short) each, ShortLists.mutable.empty());
        var expectedShortList = ShortLists.mutable.with((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);
        Assertions.assertEquals(expectedShortList, shortList);
        long shortSum = shortList.sum();
        Assertions.assertEquals(15L, shortSum);

        FloatList floatList = this.unmodifiableList.collectFloat(each -> (float) each, FloatLists.mutable.empty());
        var expectedFloatList = FloatLists.mutable.with(1.0f, 2.0f, 3.0f, 4.0f, 5.0f);
        Assertions.assertEquals(expectedFloatList, floatList);
        double floatSum = floatList.sum();
        Assertions.assertEquals(15.0, floatSum, 0.0);

        LongList longList = this.unmodifiableList.collectLong(each -> (long) each, LongLists.mutable.empty());
        var expectedLongList = LongLists.mutable.with(1L, 2L, 3L, 4L, 5L);
        Assertions.assertEquals(expectedLongList, longList);
        long longSum = longList.sum();
        Assertions.assertEquals(15L, longSum);

        DoubleList doubleList = this.unmodifiableList.collectDouble(each -> (double) each, DoubleLists.mutable.empty());
        var expectedDoubleList = DoubleLists.mutable.with(1.0, 2.0, 3.0, 4.0, 5.0);
        Assertions.assertEquals(expectedDoubleList, doubleList.toList());
        double doubleSum = doubleList.sum();
        Assertions.assertEquals(15.0, doubleSum, 0.0);

        MutableList<String> stringList = this.unmodifiableList.collect(Integer::toString);
        var expectedStringList = Lists.mutable.with("1", "2", "3", "4", "5");
        Assertions.assertEquals(expectedStringList, stringList);
    }

    @Test
    @Tag("SOLUTION")
    public void calculating()
    {
        long sum = this.unmodifiableList.sum();
        int max = this.unmodifiableList.max();
        int maxIfEmpty = this.unmodifiableList.maxIfEmpty(0);
        int min = this.unmodifiableList.min();
        int minIfEmpty = this.unmodifiableList.minIfEmpty(0);
        double average = this.unmodifiableList.average();
        double averageIfEmpty = this.unmodifiableList.averageIfEmpty(0.0);
        double median = this.unmodifiableList.median();
        double medianIfEmpty = this.unmodifiableList.medianIfEmpty(0.0);
        IntSummaryStatistics stats = this.unmodifiableList.summaryStatistics();

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
        Assertions.assertEquals(this.unmodifiableList.size(), stats.getCount());
    }
}
