/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.topmethodskata;

import java.awt.Color;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.factory.primitive.CharBags;
import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.factory.primitive.ObjectLongMaps;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.map.primitive.ImmutableObjectLongMap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class Top25MethodsTest
{
    private final ImmutableList<String> fruitNames = Fruit.toLowerCaseList();
    private final ImmutableList<String> fruitEmoji = Fruit.toEmojiList();
    private final ImmutableSet<String> onlyBanana = Sets.immutable.with("🍌");

    @Test
    @Tag("KATA")
    public void with()
    {
        // Fix by replacing the empty list with a List of fruit emoji - 🍎, 🍑, 🍌, 🍒, 🍊
        MutableList<String> fruit = Lists.mutable.empty();

        var expected = Fruit.toEmojiList();

        Assertions.assertEquals(expected, fruit);
    }

    @Test
    @Tag("KATA")
    public void collect()
    {
        // Fix by collecting the names of the fruit to uppercase
        ImmutableList<String> uppercase = this.fruitNames;

        var expectedUppercase = Lists.mutable.with("APPLE", "PEACH", "BANANA", "CHERRY", "ORANGE");

        Assertions.assertEquals(expectedUppercase, uppercase);
    }

    @Test
    @Tag("KATA")
    public void of()
    {
        // Fix by replacing the empty set with a Set of just a banana
        MutableSet<String> onlyBanana = Sets.mutable.empty();

        var expected = Set.of("🍌");

        Assertions.assertEquals(expected, onlyBanana);
    }

    @Test
    @Tag("KATA")
    public void select()
    {
        // Select the fruit emoji that are contained in the set of onlyBanana
        ImmutableList<String> justBanana = this.fruitEmoji;

        var expected = List.of("🍌");

        Assertions.assertEquals(expected, justBanana);
    }

    @Test
    @Tag("KATA")
    public void reject()
    {
        // Reject the fruit emoji that are contained in the set of onlyBanana
        ImmutableList<String> notBanana = this.fruitNames;

        var expected = List.of("🍎", "🍑", "🍒", "🍊");

        Assertions.assertEquals(expected, notBanana);
    }

    @Test
    @Tag("KATA")
    public void count()
    {
        // Count the fruitEmoji that are contained in the set of onlyBanana
        int countBanana = 0;
        // Count the fruitNames that are contained in the list of fruitNames
        int countAll = 0;

        Assertions.assertEquals(1, countBanana);
        Assertions.assertEquals(5, countAll);
    }

    @Test
    @Tag("KATA")
    public void anySatisfy()
    {
        // Are any of the fruitEmoji contained in the set of onlyBanana?
        boolean anyBanana = false;
        // Are any of the fruitNames an empty String?
        boolean anyEmpty = true;

        Assertions.assertTrue(anyBanana);
        Assertions.assertFalse(anyEmpty);
    }

    @Test
    @Tag("KATA")
    public void allSatisfy()
    {
        // Are all of the fruitEmoji contained in the set of onlyBanana?
        boolean allBanana = true;
        // Are all of the fruitNames lowercase?
        boolean allLowercase = false;

        Assertions.assertFalse(allBanana);
        Assertions.assertTrue(allLowercase);
    }

    @Test
    @Tag("KATA")
    public void noneSatisfy()
    {
        // Are none of the fruitEmoji contained in the set of onlyBanana?
        boolean noneBanana = true;
        // Are none of the fruitNames empty Strings?
        boolean noneEmpty = false;

        Assertions.assertFalse(noneBanana);
        Assertions.assertTrue(noneEmpty);
    }

    @Test
    @Tag("KATA")
    public void groupBy()
    {
        // Group the fruit in Fruit.ALL by their color
        ImmutableListMultimap<Color, Fruit> multimap = Multimaps.immutable.list.empty();

        var expected = Multimaps.mutable.list.empty()
                .withKeyMultiValues(Color.RED, Fruit.APPLE, Fruit.CHERRY)
                .withKeyMultiValues(Color.YELLOW, Fruit.BANANA)
                .withKeyMultiValues(Color.ORANGE, Fruit.PEACH, Fruit.ORANGE);

        Assertions.assertEquals(expected, multimap);
    }

    @Test
    @Tag("KATA")
    public void countBy()
    {
        // Count the fruit in Fruit.ALL by their color
        ImmutableBag<Color> colors = Bags.immutable.empty();

        var expected = Bags.immutable.withOccurrences(Color.RED, 2, Color.YELLOW, 1, Color.ORANGE, 2);

        Assertions.assertEquals(expected, colors);
    }

    @Test
    @Tag("KATA")
    public void makeString()
    {
        // Make the fruitNames into a String that starts with "(", ends with ")", and is separated by ","
        String fruitString = this.fruitNames.toString();
        Assertions.assertEquals("(apple,peach,banana,cherry,orange)", fruitString);

        // Make the fruitEmoji into a String that starts with "[", ends with "]", and is separated by ","
        String fruitEmojis = this.fruitEmoji.toString();
        Assertions.assertEquals("[🍎,🍑,🍌,🍒,🍊]", fruitEmojis);
    }

    @Test
    @Tag("KATA")
    public void toImmutable()
    {
        MutableList<String> mutableFruit =
                Lists.mutable.with("🍎", "🍑", "🍌", "🍒", "🍊");

        // Convert the MutableList of fruit emoji above into an ImmutableList
        ImmutableList<String> immutableFruit = Lists.immutable.empty();

        Assertions.assertEquals(mutableFruit, immutableFruit);
    }

    @Test
    @Tag("KATA")
    public void asLazy()
    {
        // Convert this.fruitNames into a LazyIterable
        LazyIterable<String> lazyFruit = LazyIterate.adapt(List.of());

        Assertions.assertEquals(5, lazyFruit.size());
        // Note: LazyIterable does not get exhausted, so you can keep using it.
        Assertions.assertEquals(5, lazyFruit.size());
        // Is an ImmutableList equal to a LazyIterable? If not, how can you convert the LazyIterable to a List?
        var expected = Lists.mutable.with("APPLE", "PEACH", "BANANA", "CHERRY", "ORANGE");
        Assertions.assertEquals(expected, lazyFruit.collect(String::toUpperCase));
    }

    @Test
    @Tag("KATA")
    public void containsBy()
    {
        // Does this.fruitNames contain APPLE if you compare each element By uppercase?
        boolean hasApple = this.fruitNames.contains("APPLE");
        // Does this.fruitNames contain TOMATO if you compare each element By uppercase?
        boolean hasTomato = this.fruitNames.contains("TOMATO");

        Assertions.assertTrue(hasApple);
        Assertions.assertFalse(hasTomato);
    }

    @Test
    @Tag("KATA")
    public void detectWith()
    {
        // Do any of the this.fruitNames start with the letter b? Which one is first?
        String banana = this.fruitNames.selectWith(String::startsWith, "").getFirst();
        // Do any of the this.fruitNames start with the letter d?
        String none = this.fruitNames.selectWith(String::startsWith, "").getFirst();

        Assertions.assertEquals("banana", banana);
        Assertions.assertNull(none);
    }

    @Test
    @Tag("KATA")
    public void detectWithIfNone()
    {
        // Do any of the this.fruitNames start with the letter b? Which one is first? Return "apple" if none match.
        String banana = "";
        // Do any of the this.fruitNames start with the letter d? Which one is first? Return "banana" if none match.
        String stillBanana = "";

        Assertions.assertEquals("banana", banana);
        Assertions.assertEquals("banana", stillBanana);
    }

    @Test
    @Tag("KATA")
    public void injectInto()
    {
        // Use injectInto with a String builder to append all of the fruitEmoji together in a String.
        StringBuilder stringBuilder = null;

        String mixedFruitString = stringBuilder.toString();

        Assertions.assertEquals("🍎🍑🍌🍒🍊", mixedFruitString);
    }

    @Test
    @Tag("KATA")
    public void partition()
    {
        // Partition the fruitNames based on any fruit containing the letter "n"
        PartitionImmutableList<String> partitionFruit = null;

        ImmutableList<String> selected = partitionFruit.getSelected();
        ImmutableList<String> rejected = partitionFruit.getRejected();

        var expectedSelected = Lists.mutable.with("banana", "orange");
        var expectedRejected = Lists.mutable.with("apple", "peach", "cherry");

        Assertions.assertEquals(expectedSelected, selected);
        Assertions.assertEquals(expectedRejected, rejected);
    }

    @Test
    @Tag("KATA")
    public void chunk()
    {
        // Chunk the fruitEmoji in batches of size 2
        RichIterable<RichIterable<String>> chunkFruit = null;

        Assertions.assertEquals(3, chunkFruit.size());

        var expectedFirst = Lists.mutable.with("🍎", "🍑");
        var expectedLast = Lists.mutable.with("🍊");

        Assertions.assertEquals(expectedFirst, chunkFruit.getFirst());
        Assertions.assertEquals(expectedLast, chunkFruit.getLast());
    }

    @Test
    @Tag("KATA")
    public void sumByInt()
    {
        // Sum the length of the fruitNames by the first character of each item
        ImmutableObjectLongMap<Character> sumLengthsByFirstCharacter = null;

        var expected = ObjectLongMaps.mutable.<Character>empty()
                .withKeyValue('a', 5)
                .withKeyValue('b', 6)
                .withKeyValue('c', 6)
                .withKeyValue('o', 6)
                .withKeyValue('p', 5);

        Assertions.assertEquals(expected, sumLengthsByFirstCharacter);
    }

    @Test
    @Tag("KATA")
    public void collectInt()
    {
        // Collect the lengths of the fruitNames as int values
        ImmutableIntList lengths = null;

        var expected = IntLists.immutable.with(5, 5, 6, 6, 6);

        Assertions.assertEquals(expected, lengths);
    }

    @Test
    @Tag("KATA")
    public void flatCollectChar()
    {
        // Flat collect all of the char values in the fruitNames into a mutable CharBag
        MutableCharBag charCounts = null;

        var expected = CharBags.mutable.empty();
        expected.addOccurrences('a', 6);
        expected.addOccurrences('b', 1);
        expected.addOccurrences('c', 2);
        expected.addOccurrences('e', 4);
        expected.addOccurrences('g', 1);
        expected.addOccurrences('h', 2);
        expected.addOccurrences('l', 1);
        expected.addOccurrences('n', 3);
        expected.addOccurrences('o', 1);
        expected.addOccurrences('p', 3);
        expected.addOccurrences('r', 3);
        expected.addOccurrences('y', 1);
        Assertions.assertEquals(expected, charCounts);
    }

    @Test
    @Tag("KATA")
    public void asParallel()
    {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // Convert the fruitEmoji into a ParallelListIterable using the executorService and a batch size of 1
        ParallelListIterable<String> parallelFruit = null;

        ParallelListIterable<String> parallelSelect =
                parallelFruit.select(this.onlyBanana::contains);

        MutableList<String> onlyBananaList = parallelSelect.toList();
        MutableSet<String> onlyBananaSet = parallelSelect.toSet();

        var expectedList = Lists.mutable.with("🍌");
        var expectedSet = Sets.mutable.with("🍌");

        Assertions.assertEquals(expectedList, onlyBananaList);
        Assertions.assertEquals(expectedSet, onlyBananaSet);
    }

    @Test
    @Tag("KATA")
    public void distinct()
    {
        MutableList<String> duplicateFruit =
                Lists.mutable.with("🍎", "🍎", "🍌", "🍌");

        // Find the distinct list of fruit emoji from duplicateFruit
        MutableList<String> distinctFruit = duplicateFruit;

        var expected = Lists.mutable.with("🍎", "🍌");
        Assertions.assertEquals(expected, distinctFruit);
    }
}
