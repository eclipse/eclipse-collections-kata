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

import org.eclipse.collections.api.bag.primitive.ImmutableIntBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.factory.primitive.IntBags;
import org.eclipse.collections.api.factory.primitive.IntIntMaps;
import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.map.primitive.ImmutableIntIntMap;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ImmutableIntIntMapTest
{
    @Test
    @Tag("SOLUTION")
    public void immutable()
    {
        ImmutableIntIntMap map = IntIntMaps.immutable.empty();

        // newWithKeyValue / get
        map = map.newWithKeyValue(1, 1);
        Assertions.assertEquals(1, map.get(1));
        map = map.newWithKeyValue(2, 2);
        Assertions.assertEquals(2, map.get(2));

        // withKeyValue
        ImmutableIntIntMap map1 = map.newWithKeyValue(3, 3).newWithKeyValue(4, 4).newWithKeyValue(5, 5);
        Assertions.assertNotSame(map, map1);
        Assertions.assertEquals(3, map1.get(3));
        Assertions.assertEquals(4, map1.get(4));
        Assertions.assertEquals(5, map1.get(5));

        // withoutKey
        map = map1.newWithoutKey(4).newWithoutKey(5);
        Assertions.assertEquals(1, map.get(1));
        Assertions.assertEquals(2, map.get(2));
        Assertions.assertEquals(3, map.get(3));

        // flipUniqueValues
        ImmutableIntIntMap flipped = map.flipUniqueValues();
        Assertions.assertEquals(map, flipped);
        Assertions.assertNotSame(map, flipped);

        // getIfAbsent
        Assertions.assertEquals(3, map.getIfAbsent(3, 4));
        Assertions.assertEquals(4, map.getIfAbsent(5, 4));

        // withoutAllKeys
        ImmutableIntIntMap withoutAllKeys = map.newWithoutAllKeys(IntLists.immutable.with(1, 2, 3));
        Assertions.assertTrue(withoutAllKeys.isEmpty());

        // Math on values
        long sum = map.sum();
        Assertions.assertEquals(6L, sum);
        double average = map.averageIfEmpty(0.0);
        Assertions.assertEquals(2.0, average, 0.0);
        double median = map.medianIfEmpty(0.0);
        Assertions.assertEquals(2.0, median, 0.0);
        int min = map.minIfEmpty(0);
        Assertions.assertEquals(1, min);
        int max = map.maxIfEmpty(0);
        Assertions.assertEquals(3, max);
        IntSummaryStatistics stats = map.summaryStatistics();
        Assertions.assertEquals(stats.getSum(), sum);
        Assertions.assertEquals(stats.getMin(), min);
        Assertions.assertEquals(stats.getMax(), max);

        // Filter values inclusively
        ImmutableIntBag select = map.select(each -> each % 2 == 0);
        Assertions.assertEquals(IntBags.mutable.with(2), select);

        // Filter values inclusively
        ImmutableIntBag reject = map.reject(each -> each % 2 == 0);
        Assertions.assertEquals(IntBags.mutable.with(1, 3), reject);

        // toImmutable
        ImmutableIntIntMap immutableMap = map.toImmutable();
        Assertions.assertEquals(map, immutableMap);

        // Converter methods on values
        MutableIntList valueList = map.toList().sortThis();
        IntInterval expectedList = IntInterval.oneTo(3);
        Assertions.assertEquals(expectedList, valueList);
        MutableIntList valuesSortedList = map.toSortedList();
        Assertions.assertEquals(expectedList, valuesSortedList);
        MutableIntBag valuesBag = map.toBag();
        Assertions.assertEquals(expectedList.toBag(), valuesBag);
        MutableIntSet valuesSet = map.toSet();
        Assertions.assertEquals(expectedList.toSet(), valuesSet);
    }
}
