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
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnmodifiableIntListTest
{
    // Make this MutableIntList unmodifiable by calling asUnmodifiable
    private MutableIntList unmodifiableList = IntLists.mutable.with(1, 2, 3, 4, 5);

    /**
     * Change the unmodifiableList initialization code above to get this test to pass.
     * <p>
     * {@link MutableIntList#add(int)} <br>
     */
    @Test
    public void isUnmodifiable()
    {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableList.add(6));
    }

    /**
     * {@link MutableIntList#toList()}
     * {@link MutableIntList#toSet()}
     * {@link MutableIntList#toBag()}
     */
    @Test
    public void converting()
    {
        // Convert this.unmodifiableList to a MutableIntList
        MutableIntList list = null;
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), list);

        // Convert this.unmodifiableList to a MutableIntSet
        MutableIntSet set = null;
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), set);

        // Convert this.unmodifiableList to a MutableIntBag
        MutableIntBag bag = null;
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
    }

    /**
     * Inclusive Filter: {@link MutableIntList#select(IntPredicate)} <br>
     * Exclusive Filter: {@link MutableIntList#reject(IntPredicate)} <br>
     */
    @Test
    public void filtering()
    {
        IntPredicate isEven = each -> each % 2 == 0;
        // Filter the list inclusively based on the isEven predicate
        MutableIntList select = this.unmodifiableList;
        Assertions.assertEquals(IntInterval.evensFromTo(2, 4), select);

        // Filter the list exclusively based on the isEven predicate
        MutableIntList reject = this.unmodifiableList;
        Assertions.assertEquals(IntInterval.oddsFromTo(1, 5), reject);
    }

    /**
     * {@link MutableIntList#collectBoolean(IntToBooleanFunction, MutableBooleanCollection)} <br>
     * {@link MutableIntList#collectByte(IntToByteFunction, MutableByteCollection)} <br>
     * {@link MutableIntList#collectChar(IntToCharFunction, MutableCharCollection)} <br>
     * {@link MutableIntList#collectShort(IntToShortFunction, MutableShortCollection)} <br>
     * {@link MutableIntList#collectFloat(IntToFloatFunction, MutableFloatCollection)} <br>
     * {@link MutableIntList#collectLong(IntToLongFunction, MutableLongCollection)} <br>
     * {@link MutableIntList#collectDouble(IntToDoubleFunction, MutableDoubleCollection)} <br>
     * {@link MutableIntList#collect(IntToObjectFunction)} <br>
     */
    @Test
    public void transforming()
    {
        // Type Transformations using fused methods with target collections
        // Collect to MutableBooleanList evens=true, odds=false
        BooleanList booleanList = null;
        var expectedBooleanList = BooleanLists.mutable.with(false, true, false, true, false);
        Assertions.assertEquals(expectedBooleanList, booleanList);

        // Collect to MutableByteList the values as is
        ByteList byteList = null;
        var expectedByteList = ByteLists.mutable.with((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        Assertions.assertEquals(expectedByteList, byteList);
        long byteSum = byteList.sum();
        Assertions.assertEquals(15L, byteSum);

        // Collect to MutableCharList the values as is
        CharList charList = null;
        var expectedCharList = CharLists.mutable.with((char) 1, (char) 2, (char) 3, (char) 4, (char) 5);
        Assertions.assertEquals(expectedCharList, charList);
        long charSum = charList.sum();
        Assertions.assertEquals(15L, charSum);

        // Collect to MutableShortList the values as is
        ShortList shortList = null;
        var expectedShortList = ShortLists.mutable.with((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);
        Assertions.assertEquals(expectedShortList, shortList);
        long shortSum = shortList.sum();
        Assertions.assertEquals(15L, shortSum);

        // Collect to MutableFloatList the values as is
        FloatList floatList = null;
        var expectedFloatList = FloatLists.mutable.with(1.0f, 2.0f, 3.0f, 4.0f, 5.0f);
        Assertions.assertEquals(expectedFloatList, floatList);
        double floatSum = floatList.sum();
        Assertions.assertEquals(15.0, floatSum, 0.0);

        // Collect to MutableLongList the values as is
        LongList longList = null;
        var expectedLongList = LongLists.mutable.with(1L, 2L, 3L, 4L, 5L);
        Assertions.assertEquals(expectedLongList, longList);
        long longSum = longList.sum();
        Assertions.assertEquals(15L, longSum);

        // Collect to MutableDoubleList the values as is
        DoubleList doubleList = null;
        var expectedDoubleList = DoubleLists.mutable.with(1.0, 2.0, 3.0, 4.0, 5.0);
        Assertions.assertEquals(expectedDoubleList, doubleList.toList());
        double doubleSum = doubleList.sum();
        Assertions.assertEquals(15.0, doubleSum, 0.0);

        // Collect to MutableList of String the values as Strings
        MutableList<String> stringList = null;
        var expectedStringList = Lists.mutable.with("1", "2", "3", "4", "5");
        Assertions.assertEquals(expectedStringList, stringList);
    }

    /**
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
    @Test
    public void calculating()
    {
        // Calculate the sum of this.unmodifiableList
        long sum = 0L;
        // Calculate the max of this.unmodifiableList
        int max = 0;
        // Calculate the max of this.unmodifiableList, and 0 if empty
        int maxIfEmpty = 0;
        // Calculate the min of this.unmodifiableList
        int min = 0;
        // Calculate the min of this.unmodifiableList, and 0 if empty
        int minIfEmpty = 0;
        // Calculate the average of this.unmodifiableList
        double average = 0.0;
        // Calculate the average of this.unmodifiableList, and 0.0 if empty
        double averageIfEmpty = 0.0;
        // Calculate the median of this.unmodifiableList
        double median = 0.0;
        // Calculate the median of this.unmodifiableList, and 0.0 if empty
        double medianIfEmpty = 0.0;
        // Calculate the summaryStatistics of this.unmodifiableList
        IntSummaryStatistics stats = new IntSummaryStatistics();

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
