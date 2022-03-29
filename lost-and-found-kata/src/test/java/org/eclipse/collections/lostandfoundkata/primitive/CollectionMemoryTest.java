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

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.ObjectIntToObjectFunction;
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
    private static final int SIZE = 1_000;
    private static final MultiReaderList<Triple<String, Long, Long>> RESULTS = Lists.multiReader.empty();

    /**
     * {@link GraphLayout#parseInstance(Object...)} <br>
     * {@link GraphLayout#totalSize()} <br>
     * {@link Tuples#triple(Object, Object, Object)} <br>
     */
    private void recordTriple(String list, Object ecType, Object jdkType)
    {
        long ecPrimitiveSize = GraphLayout.parseInstance(ecType).totalSize();
        long jdkBoxedSize = GraphLayout.parseInstance(jdkType).totalSize();
        Assertions.assertTrue(ecPrimitiveSize < jdkBoxedSize);
        RESULTS.add(Tuples.triple(list, ecPrimitiveSize, jdkBoxedSize));
    }

    /**
     * {@link IntInterval#oneTo(int)} <br>
     * {@link Interval#oneTo(int)} <br>
     * {@link IntInterval#toList()} <br>
     * @see MutableIntList
     * @see List
     * @see ArrayList
     */
    @Test
    @Order(1)
    public void list()
    {
        // Create an IntInterval from 1 to SIZE
        IntInterval ints = null;
        // Create an Interval from 1 to SIZE
        Interval integers = null;
        MutableIntList bigIntList = ints.toList();
        List<Integer> bigIntegerList = new ArrayList<>(integers);

        this.recordTriple("List", bigIntList, bigIntegerList);
    }

    /**
     * {@link IntInterval#oneTo(int)} <br>
     * {@link Interval#oneTo(int)} <br>
     * {@link IntInterval#toSet()} <br>
     * @see MutableIntSet
     * @see Set
     * @see HashSet
     */
    @Test
    @Order(2)
    public void set()
    {
        // Create an IntInterval from 1 to SIZE
        IntInterval ints = null;
        // Create an Interval from 1 to SIZE
        Interval integers = null;
        MutableIntSet bigIntSet = ints.toSet();
        Set<Integer> bigIntegerSet = new HashSet<>(integers);

        this.recordTriple("Set", bigIntSet, bigIntegerSet);
    }

    /**
     * {@link IntInterval#oneTo(int)} <br>
     * {@link Interval#oneTo(int)} <br>
     * {@link IntInterval#toBag()} <br>
     * {@link Interval#injectInto(Object, Function2)} <br>
     * @see MutableIntBag
     * @see Map
     * @see HashMap
     */
    @Test
    @Order(3)
    public void bag()
    {
        // Create an IntInterval from 1 to SIZE
        IntInterval ints = null;
        // Create an Interval from 1 to SIZE
        Interval integers = null;
        MutableIntBag bigIntBag = ints.toBag();
        Map<Integer, Long> bigIntegerMap = integers
                .injectInto(
                        new HashMap<Integer, Long>(),
                        (map, value) -> {
                            map.put(value, Long.valueOf(1));
                            return map;
                        });

        this.recordTriple("Bag", bigIntBag, bigIntegerMap);
    }

    /**
     * {@link IntInterval#oneTo(int)} <br>
     * {@link Interval#oneTo(int)} <br>
     * {@link org.eclipse.collections.api.factory.stack.primitive.MutableIntStackFactory#withAll(IntIterable)}
     * @see IntStacks
     * @see MutableIntStack
     * @see Stack
     */
    @Test
    @Order(4)
    public void stack()
    {
        // Create an IntInterval from 1 to SIZE
        IntInterval ints = null;
        // Create an Interval from 1 to SIZE
        Interval integers = null;
        MutableIntStack bigIntStack = IntStacks.mutable.withAll(ints);
        Stack<Integer> bigIntegerStack = new Stack<>();
        bigIntegerStack.addAll(integers);

        this.recordTriple("Stack", bigIntStack, bigIntegerStack);
    }

    /**
     * {@link IntInterval#oneTo(int)} <br>
     * {@link Interval#oneTo(int)} <br>
     * {@link IntInterval#injectInto(Object, ObjectIntToObjectFunction)} <br>
     * {@link Interval#injectInto(Object, Function2)} <br>
     * @see MutableIntIntMap
     * @see Map
     * @see HashMap
     */
    @Test
    @Order(5)
    public void map()
    {
        // Create an IntInterval from 1 to SIZE
        IntInterval ints = null;
        // Create an Interval from 1 to SIZE
        Interval integers = null;
        MutableIntIntMap bigIntIntMap =
                ints.injectInto(
                        IntIntMaps.mutable.empty(),
                        (map, each) -> map.withKeyValue(each, each - 1));
        Map<Integer, Integer> bigIntegerIntegerMap =
                integers.injectInto(
                        new HashMap<Integer, Integer>(),
                        (map, value) -> {
                            map.put(value, value - 1);
                            return map;
                        });

        this.recordTriple("Map", bigIntIntMap, bigIntegerIntegerMap);
    }

    /**
     * {@link StringIterate#padOrTrim(String, int)} <br>
     * {@link StringIterate#repeat(char, int)} <br>
     */
    @AfterAll
    static void afterAll()
    {
        RESULTS.sortThisByLong(triple -> Math.min(triple.getTwo(), triple.getThree()));
        System.out.println(StringIterate.padOrTrim("Primitive vs. Boxed Collections", 49));
        System.out.println(StringIterate.padOrTrim("Count: " + SIZE + " elements", 49));
        System.out.println(StringIterate.repeat("-", 49));
        System.out.println("                          " + StringIterate.padOrTrim("EC Primitive", 12) + " " + StringIterate.padOrTrim("JDK Boxed", 12));
        RESULTS.each(triple -> System.out.println("Type: " + StringIterate.padOrTrim(triple.getOne(), 5)
                + " Size (bytes): " + StringIterate.padOrTrim(NumberFormat.getInstance().format(triple.getTwo()), 12)
                + " " + StringIterate.padOrTrim(NumberFormat.getInstance().format(triple.getThree()), 12)));
    }
}
