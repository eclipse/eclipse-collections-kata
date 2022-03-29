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

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.sortedbag.ImmutableSortedBagMultimap;
import org.eclipse.collections.api.multimap.sortedbag.MutableSortedBagMultimap;
import org.eclipse.collections.api.multimap.sortedset.ImmutableSortedSetMultimap;
import org.eclipse.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import org.eclipse.collections.impl.factory.Multimaps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultimapTest
{
    /**
     * @see Multimaps
     */
    @Test
    public void createEmptyListMultimaps()
    {
        // Hint: Use the Multimaps factory to create mutable and immutable Multimap types
        // Create an empty MutableListMultimap
        MutableListMultimap<String, String> mutableListMultimap = null;
        // Create an empty ImmutableListMultimap
        ImmutableListMultimap<String, String> immutableListMultimap = null;
        Assertions.assertTrue(mutableListMultimap.isEmpty());
        Assertions.assertEquals(mutableListMultimap, immutableListMultimap);
    }

    /**
     * @see Multimaps
     */
    @Test
    public void createEmptySetMultimaps()
    {
        // Hint: Use the Multimaps factory to create mutable and immutable Multimap types
        // Create an empty MutableSetMultimap
        MutableSetMultimap<String, String> mutableSetMultimap = null;
        // Create an empty ImmutableSetMultimap
        ImmutableSetMultimap<String, String> immutableSetMultimap = null;
        Assertions.assertTrue(mutableSetMultimap.isEmpty());
        Assertions.assertEquals(mutableSetMultimap, immutableSetMultimap);
    }

    /**
     * @see Multimaps
     */
    @Test
    public void createEmptyBagMultimaps()
    {
        // Hint: Use the Multimaps factory to create mutable and immutable Multimap types
        // Create an empty MutableBagMultimap
        MutableBagMultimap<String, String> mutableBagMultimap = null;
        // Create an empty ImmutableBagMultimap
        ImmutableBagMultimap<String, String> immutableBagMultimap = null;
        Assertions.assertTrue(mutableBagMultimap.isEmpty());
        Assertions.assertEquals(mutableBagMultimap, immutableBagMultimap);
    }

    /**
     * @see Multimaps
     */
    @Test
    public void createEmptySortedSetMultimaps()
    {
        // Hint: Use the Multimaps factory to create mutable and immutable Multimap types
        // Create an empty MutableSortedSetMultimap
        MutableSortedSetMultimap<String, String> mutableSortedSetMultimap = null;
        // Create an empty ImmutableSortedSetMultimap
        ImmutableSortedSetMultimap<String, String> immutableSortedSetMultimap = null;
        Assertions.assertTrue(mutableSortedSetMultimap.isEmpty());
        Assertions.assertEquals(mutableSortedSetMultimap, immutableSortedSetMultimap);
    }

    /**
     * @see Multimaps
     */
    @Test
    public void createEmptySortedBagMultimaps()
    {
        // Hint: Use the Multimaps factory to create mutable and immutable Multimap types
        // Create an empty MutableSortedBagMultimap
        MutableSortedBagMultimap<String, String> mutableSortedBagMultimap = null;
        // Create an empty ImmutableSortedBagMultimap
        ImmutableSortedBagMultimap<String, String> immutableSortedBagMultimap = null;
        Assertions.assertTrue(mutableSortedBagMultimap.isEmpty());
        Assertions.assertEquals(mutableSortedBagMultimap, immutableSortedBagMultimap);
    }

    /**
     * {@link MutableList#groupBy(Function)} <br>
     * {@link String#charAt(int)} <br>
     */
    @Test
    public void groupWordsByFirstCharacter()
    {
        String words = "The quick brown fox jumps over the lazy dog";
        MutableList<String> list = Lists.mutable.with(words.split(" ")).collect(String::toLowerCase);
        // Group the words in list by their first character
        Multimap<Character, String> multimap = null;

        Assertions.assertEquals(Lists.mutable.with("the", "the"), multimap.get('t'));
        Assertions.assertEquals(Lists.mutable.empty(), multimap.get('a'));
    }

    /**
     * {@link MutableList#groupByAndCollect(Function, Function, MutableMultimap)} <br>
     * {@link Character#toLowerCase(char)} <br>
     * {@link String#toLowerCase()} <br>
     */
    @Test
    public void groupAndCollectWordsByLowercaseFirstCharacter()
    {
        String words = "The quick brown fox jumps over the lazy dog";
        MutableList<String> list = Lists.mutable.with(words.split(" "));
        // Group the words by their first letter converted to lowercase, and collect the words as lowercase
        Multimap<Character, String> multimap = null;

        Assertions.assertEquals(Lists.mutable.with("the", "the"), multimap.get('t'));
        Assertions.assertEquals(Lists.mutable.empty(), multimap.get('a'));
    }

    /**
     * {@link MutableList#groupBy(Function)} <br>
     * {@link String#charAt(int)} <br>
     * {@link Multimap#flip()} <br>
     */
    @Test
    public void flip()
    {
        String words = "The quick brown fox jumps over the lazy dog";
        MutableList<String> list = Lists.mutable.with(words.split(" ")).collect(String::toLowerCase);
        // Group the words in list by their first character and flip the Multimap
        Multimap<String, Character> multimap = null;

        Assertions.assertEquals(Bags.mutable.with('t', 't'), multimap.get("the"));
        Assertions.assertEquals(Bags.mutable.empty(), multimap.get("and"));
    }
}
