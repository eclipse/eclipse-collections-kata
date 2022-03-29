/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.primitive;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.list.MultiReaderList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.stack.primitive.MutableIntStack;
import org.eclipse.collections.api.tuple.Triple;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.IntIntMaps;
import org.eclipse.collections.impl.factory.primitive.IntStacks;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.StringIterate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.GraphLayout;

public class CollectionMemoryTest
{
    private static MultiReaderList<Triple<String, Long, Long>> RESULTS =
            Lists.multiReader.empty();

    private void recordTriple(String list, Object ecType, Object jdkType)
    {
        long ecPrimitiveSize = GraphLayout.parseInstance(ecType).totalSize();
        long jdkBoxedSize = GraphLayout.parseInstance(jdkType).totalSize();
        Assertions.assertTrue(ecPrimitiveSize < jdkBoxedSize);
        RESULTS.add(Tuples.triple(list, ecPrimitiveSize, jdkBoxedSize));
    }

    @Test
    @Tag("SOLUTION")
    @Order(1)
    public void list()
    {
        MutableIntList bigIntList =
                IntInterval.oneTo(1_000).toList();
        List<Integer> bigIntegerList =
                new ArrayList<>(Interval.oneTo(1_000));

        this.recordTriple("List", bigIntList, bigIntegerList);
    }

    @Test
    @Tag("SOLUTION")
    @Order(2)
    public void set()
    {
        MutableIntSet bigIntSet =
                IntInterval.oneTo(1_000).toSet();
        Set<Integer> bigIntegerSet =
                new HashSet<>(Interval.oneTo(1_000));

        this.recordTriple("Set", bigIntSet, bigIntegerSet);
    }

    @Test
    @Tag("SOLUTION")
    @Order(3)
    public void bag()
    {
        MutableIntBag bigIntBag =
                IntInterval.oneTo(1_000).toBag();
        Map<Integer, Long> bigIntegerMap = Interval.oneTo(1_000)
                .injectInto(
                        new HashMap<Integer, Long>(),
                        (map, value) -> {
                            map.put(value, Long.valueOf(1));
                            return map;
                        });

        this.recordTriple("Bag", bigIntBag, bigIntegerMap);
    }

    @Test
    @Tag("SOLUTION")
    @Order(4)
    public void stack()
    {
        MutableIntStack bigIntStack =
                IntStacks.mutable.withAll(IntInterval.oneTo(1_000));
        Stack<Integer> bigIntegerStack = new Stack<>();
        bigIntegerStack.addAll(Interval.oneTo(1_000));

        this.recordTriple("Stack", bigIntStack, bigIntegerStack);
    }

    @Test
    @Tag("SOLUTION")
    @Order(5)
    public void map()
    {
        MutableIntIntMap bigIntIntMap =
                IntInterval.oneTo(1_000).injectInto(
                        IntIntMaps.mutable.empty(),
                        (map, each) -> map.withKeyValue(each, each - 1));
        Map<Integer, Integer> bigIntegerIntegerMap =
                Interval.oneTo(1_000).injectInto(
                        new HashMap<Integer, Integer>(),
                        (map, value) -> {
                            map.put(value, value - 1);
                            return map;
                        });

        this.recordTriple("Map", bigIntIntMap, bigIntegerIntegerMap);
    }

    @AfterAll
    static void afterAll()
    {
        RESULTS.sortThisByLong(triple -> Math.min(triple.getTwo(), triple.getThree()));
        System.out.println(StringIterate.padOrTrim("Primitive vs. Boxed Collections", 49));
        System.out.println(StringIterate.padOrTrim("Count: 1,000 elements", 49));
        System.out.println(StringIterate.repeat("-", 49));
        System.out.println("                          " + StringIterate.padOrTrim("EC Primitive", 12) + " " + StringIterate.padOrTrim("JDK Boxed", 12));
        RESULTS.each(triple -> System.out.println("Type: " + StringIterate.padOrTrim(triple.getOne(), 5)
                + " Size (bytes): " + StringIterate.padOrTrim(NumberFormat.getInstance().format(triple.getTwo()), 12)
                + " " + StringIterate.padOrTrim(NumberFormat.getInstance().format(triple.getThree()), 12)));
    }
}
