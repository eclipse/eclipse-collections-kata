/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.datastructures;

import java.util.Arrays;
import java.util.List;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.factory.map.strategy.MutableHashingStrategyMapFactory;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.Pool;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.factory.HashingStrategyBags;
import org.eclipse.collections.impl.factory.HashingStrategyMaps;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashingStrategyTest
{
    /**
     * {@link UnifiedSetWithHashingStrategy#newSet(HashingStrategy)} <br>
     * {@link HashingStrategies#fromFunction(Function)} <br>
     */
    @Test
    public void caseInsensitiveSet()
    {
        // Create a UnifiedSetWithHashingStrategy with a HashingStrategy based on String.toLowerCase()
        UnifiedSetWithHashingStrategy<String> caseInsensitiveSet = null;

        Assertions.assertTrue(caseInsensitiveSet.add("fox"));
        Assertions.assertTrue(caseInsensitiveSet.add("dog"));
        Assertions.assertTrue(caseInsensitiveSet.add("cat"));

        Assertions.assertFalse(caseInsensitiveSet.add("fOx"));
        Assertions.assertFalse(caseInsensitiveSet.add("DoG"));
        Assertions.assertFalse(caseInsensitiveSet.add("CAT"));

        Assertions.assertEquals("fox", caseInsensitiveSet.get("FOX"));
        Assertions.assertEquals("dog", caseInsensitiveSet.get("DOG"));
        Assertions.assertEquals("cat", caseInsensitiveSet.get("CAT"));

        Assertions.assertEquals("fox", caseInsensitiveSet.get("FoX"));
        Assertions.assertEquals("dog", caseInsensitiveSet.get("dOg"));
        Assertions.assertEquals("cat", caseInsensitiveSet.get("cat"));
    }

    /**
     * {@link UnifiedSetWithHashingStrategy#newSet(HashingStrategy)} <br>
     * {@link HashingStrategies#fromFunction(Function)} <br>
     *
     * @see Pool
     */
    @Test
    public void caseInsensitivePool()
    {
        // Create a Pool of String with a HashingStrategy based on String.toLowerCase()
        // Hint: UnifiedSetWithHashingStrategy implements Pool
        Pool<String> caseInsensitivePool = null;

        Assertions.assertEquals("fox", caseInsensitivePool.put("fox"));
        Assertions.assertEquals("dog", caseInsensitivePool.put("dog"));
        Assertions.assertEquals("cat", caseInsensitivePool.put("cat"));

        Assertions.assertEquals("fox", caseInsensitivePool.put("fOx"));
        Assertions.assertEquals("dog", caseInsensitivePool.put("DoG"));
        Assertions.assertEquals("cat", caseInsensitivePool.put("CAT"));

        Assertions.assertEquals("fox", caseInsensitivePool.get("FOX"));
        Assertions.assertEquals("dog", caseInsensitivePool.get("DOG"));
        Assertions.assertEquals("cat", caseInsensitivePool.get("CAT"));

        Assertions.assertEquals("fox", caseInsensitivePool.get("FoX"));
        Assertions.assertEquals("dog", caseInsensitivePool.get("dOg"));
        Assertions.assertEquals("cat", caseInsensitivePool.get("cat"));
    }

    /**
     * {@link MutableHashingStrategyMapFactory#with(HashingStrategy)} <br>
     * {@link HashingStrategies#fromFunction(Function)} <br>
     *
     * @see HashingStrategyMaps
     */
    @Test
    public void caseInsensitiveMap()
    {
        // Create a UnifiedMapWithHashingStrategy with a HashingStrategy based on String.toLowerCase()
        // Hint: Use the HashingStrategyMaps factory class
        MutableMap<String, String> caseInsensitiveMap = null;
        caseInsensitiveMap.withKeyValue("one", "1")
                .withKeyValue("Two", "2")
                .withKeyValue("THREE", "3");

        Assertions.assertEquals("1", caseInsensitiveMap.get("ONE"));
        Assertions.assertEquals("2", caseInsensitiveMap.get("tWO"));
        Assertions.assertEquals("3", caseInsensitiveMap.get("three"));
    }

    /**
     * {@link org.eclipse.collections.api.factory.bag.strategy.MutableHashingStrategyBagFactory#with(HashingStrategy)} <br>
     * {@link HashingStrategies#fromFunction(Function)} <br>
     *
     * @see HashingStrategyBags
     */
    @Test
    public void caseInsensitiveBag()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        List<String> list = Arrays.asList(words.split(" "));
        // Create a HashBagWithHashingStrategy with a HashingStrategy based on String.toLowerCase()
        // Hint: use the HashingStrategyBags factory class
        MutableBag<String> caseInsensitiveBag = null;
        caseInsensitiveBag.withAll(list);

        Assertions.assertEquals(1, caseInsensitiveBag.occurrencesOf("ONE"));
        Assertions.assertEquals(2, caseInsensitiveBag.occurrencesOf("two"));
        Assertions.assertEquals(3, caseInsensitiveBag.occurrencesOf("THREE"));
        Assertions.assertEquals(4, caseInsensitiveBag.occurrencesOf("four"));
    }
}
