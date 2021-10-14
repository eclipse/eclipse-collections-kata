/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.datastructures;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bimap.BiMap;
import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.BiMaps;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BiMapTest
{
    /**
     * {@link org.eclipse.collections.api.factory.bimap.MutableBiMapFactory#with(Object, Object, Object, Object, Object, Object)} <br>
     * {@link BiMap#inverse()} <br>
     * @see BiMaps
     */
    @Test
    public void forwardAndInverse()
    {
        // Create a mutable BiMap with key/value pairs: ("1", 1), ("2", 2), ("3", 3)
        BiMap<String, Integer> biMap = null;
        // Create an inverse of the BiMap using inverse()
        BiMap<Integer, String> inverse = null;

        Assertions.assertEquals(1, biMap.get("1"));
        Assertions.assertEquals("1", inverse.get(1));

        Assertions.assertEquals(2, biMap.get("2"));
        Assertions.assertEquals("2", inverse.get(2));

        Assertions.assertEquals(3, biMap.get("3"));
        Assertions.assertEquals("3", inverse.get(3));
    }

    /**
     * {@link org.eclipse.collections.api.factory.bimap.MutableBiMapFactory#with(Object, Object, Object, Object, Object, Object)} <br>
     * {@link BiMap#inverse()} <br>
     * @see BiMaps
     */
    @Test
    public void converterMethods()
    {
        // Create a mutable BiMap with key/value pairs: ("1", 1), ("2", 2), ("3", 3)
        BiMap<String, Integer> biMap = null;
        // Create an inverse of the BiMap using inverse()
        BiMap<Integer, String> inverse = null;

        // Convert the BiMap to an ImmutableBiMap
        ImmutableBiMap<String, Integer> immutableBiMap = null;
        Assertions.assertEquals(biMap, immutableBiMap);
        // Create an inverse of the ImmutableBiMap using inverse()
        ImmutableBiMap<Integer, String> immutableInverse = null;
        Assertions.assertEquals(inverse, immutableInverse);

        // Convert the values in the BiMap to a sorted MutableList
        MutableList<Integer> mutableList = null;
        Assertions.assertEquals(Lists.mutable.with(1, 2, 3), mutableList);
        // Convert the values in the BiMap to a MutableSet
        MutableSet<Integer> mutableSet = null;
        Assertions.assertEquals(Sets.mutable.with(1, 2, 3), mutableSet);
        // Convert the values in the BiMap to a MutableBag
        MutableBag<Integer> mutableBag = null;
        Assertions.assertEquals(Bags.mutable.with(1, 2, 3), mutableBag);

        // Convert the values in the BiMap to a sorted ImmutableList
        ImmutableList<Integer> immutableList = null;
        Assertions.assertEquals(Lists.immutable.with(1, 2, 3), immutableList);
        // Convert the values in the BiMap to an ImmutableSet
        ImmutableSet<Integer> immutableSet = null;
        Assertions.assertEquals(Sets.immutable.with(1, 2, 3), immutableSet);
        // Convert the values in the BiMap to an ImmutableBag
        ImmutableBag<Integer> immutableBag = null;
        Assertions.assertEquals(Bags.immutable.with(1, 2, 3), immutableBag);

        // Convert the values in the inverse BiMap to a sorted MutableList
        MutableList<String> inverseMutableList = null;
        Assertions.assertEquals(Lists.mutable.with("1", "2", "3"), inverseMutableList);
        // Convert the values in the inverse BiMap to a MutableSet
        MutableSet<String> inverseMutableSet = null;
        Assertions.assertEquals(Sets.mutable.with("1", "2", "3"), inverseMutableSet);
        // Convert the values in the inverse BiMap to a MutableBag
        MutableBag<String> inverseMutableBag = null;
        Assertions.assertEquals(Bags.mutable.with("1", "2", "3"), inverseMutableBag);

        // Convert the values in the inverse BiMap to a sorted ImmutableList
        ImmutableList<String> inverseImmutableList = null;
        Assertions.assertEquals(Lists.immutable.with("1", "2", "3"), inverseImmutableList);
        // Convert the values in the inverse BiMap to an ImmutableSet
        ImmutableSet<String> inverseImmutableSet = null;
        Assertions.assertEquals(Sets.immutable.with("1", "2", "3"), inverseImmutableSet);
        // Convert the values in the inverse BiMap to an ImmutableBag
        ImmutableBag<String> inverseImmutableBag = null;
        Assertions.assertEquals(Bags.immutable.with("1", "2", "3"), inverseImmutableBag);
    }
}
