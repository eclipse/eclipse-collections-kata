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
import org.eclipse.collections.api.factory.primitive.IntBags;
import org.eclipse.collections.api.factory.primitive.IntIntMaps;
import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.map.primitive.ImmutableIntIntMap;
import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class MutableIntIntMapTest
{
    private MutableIntIntMap map;

    @BeforeEach
    void setUp()
    {
        this.map = IntIntMaps.mutable.empty();
    }

    @Test
    @Tag("SOLUTION")
    public void mutable()
    {
        // put / get
        this.map.put(1, 1);
        Assertions.assertEquals(1, this.map.get(1));
        this.map.put(2, 2);
        Assertions.assertEquals(2, this.map.get(2));

        // withKeyValue
        MutableIntIntMap map1 = this.map.withKeyValue(3, 3).withKeyValue(4, 4).withKeyValue(5, 5);
        Assertions.assertSame(this.map, map1);
        Assertions.assertEquals(3, this.map.get(3));
        Assertions.assertEquals(4, this.map.get(4));
        Assertions.assertEquals(5, this.map.get(5));

        // withoutKey
        MutableIntIntMap map2 = this.map.withoutKey(4).withoutKey(5);
        Assertions.assertSame(this.map, map2);

        // flipUniqueValues
        MutableIntIntMap flipped = this.map.flipUniqueValues();
        Assertions.assertEquals(this.map, flipped);
        Assertions.assertNotSame(this.map, flipped);

        // getIfAbsent
        Assertions.assertEquals(3, this.map.getIfAbsent(3, 4));
        Assertions.assertEquals(4, this.map.getIfAbsent(5, 4));

        // getIfAbsentPut
        Assertions.assertEquals(3, this.map.getIfAbsentPut(3, 4));
        Assertions.assertEquals(4, this.map.getIfAbsentPut(4, 4));
        Assertions.assertEquals(4, this.map.get(4));

        // getIfAbsentPut using lambda
        Assertions.assertEquals(3, this.map.getIfAbsentPut(3, () -> 4));
        Assertions.assertEquals(5, this.map.getIfAbsentPut(5, () -> 5));
        Assertions.assertEquals(4, this.map.get(4));

        // putAll
        this.map.putAll(IntIntMaps.mutable.empty().withKeyValue(6, 6));
        Assertions.assertEquals(6, this.map.get(6));

        // putPair
        this.map.putPair(PrimitiveTuples.pair(7, 7));
        Assertions.assertEquals(7, this.map.get(7));

        // withoutAllKeys
        MutableIntIntMap withoutAllKeys = this.map.withoutAllKeys(IntLists.immutable.with(4, 5, 6, 7));
        Assertions.assertSame(this.map, withoutAllKeys);
        Assertions.assertEquals(3, this.map.size());

        // Math on values
        long sum = this.map.sum();
        Assertions.assertEquals(6L, sum);
        double average = this.map.averageIfEmpty(0.0);
        Assertions.assertEquals(2.0, average, 0.0);
        double median = this.map.medianIfEmpty(0.0);
        Assertions.assertEquals(2.0, median, 0.0);
        int min = this.map.minIfEmpty(0);
        Assertions.assertEquals(1, min);
        int max = this.map.maxIfEmpty(0);
        Assertions.assertEquals(3, max);
        IntSummaryStatistics stats = this.map.summaryStatistics();
        Assertions.assertEquals(stats.getSum(), sum);
        Assertions.assertEquals(stats.getMin(), min);
        Assertions.assertEquals(stats.getMax(), max);

        // forEachKeyValue with addToValue
        this.map.forEachKeyValue(this.map::addToValue);
        MutableIntIntMap expected = IntIntMaps.mutable.empty()
                .withKeyValue(1, 2)
                .withKeyValue(2, 4)
                .withKeyValue(3, 6);
        Assertions.assertEquals(expected, this.map);

        // Filter values inclusively
        MutableIntBag select = this.map.select(each -> each % 2 == 0);
        Assertions.assertEquals(IntBags.mutable.with(2, 4, 6), select);

        // Filter values inclusively
        MutableIntBag reject = this.map.reject(each -> each % 2 == 0);
        Assertions.assertEquals(IntBags.mutable.empty(), reject);

        // toImmutable
        ImmutableIntIntMap immutableMap = this.map.toImmutable();
        Assertions.assertEquals(this.map, immutableMap);

        // Converter methods on values
        MutableIntList valueList = this.map.toList().sortThis();
        IntInterval expectedList = IntInterval.evensFromTo(2, 6);
        Assertions.assertEquals(expectedList, valueList);
        MutableIntList valuesSortedList = this.map.toSortedList();
        Assertions.assertEquals(expectedList, valuesSortedList);
        MutableIntBag valuesBag = this.map.toBag();
        Assertions.assertEquals(expectedList.toBag(), valuesBag);
        MutableIntSet valuesSet = this.map.toSet();
        Assertions.assertEquals(expectedList.toSet(), valuesSet);
    }
}
