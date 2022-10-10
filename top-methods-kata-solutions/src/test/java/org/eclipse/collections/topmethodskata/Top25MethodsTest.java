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
import java.util.concurrent.Executors;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.primitive.CharBag;
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
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.factory.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class Top25MethodsTest
{
    private final ImmutableList<String> fruitNames = Fruit.toLowerCaseList();
    private final ImmutableList<String> fruitEmoji = Fruit.toEmojiList();
    private final ImmutableSet<String> onlyBanana = Sets.immutable.with("🍌");

    @Test
    @Tag("SOLUTION")
    public void with()
    {
        MutableList<String> fruit =
                Lists.mutable.with("🍎", "🍑", "🍌", "🍒", "🍊");

        var expected = Fruit.toEmojiList();

        Assertions.assertEquals(expected, fruit);
    }

    @Test
    @Tag("SOLUTION")
    public void collect()
    {
        ImmutableList<String> uppercase = this.fruitNames.collect(String::toUpperCase);

        var expectedUppercase = Lists.mutable.with("APPLE", "PEACH", "BANANA", "CHERRY", "ORANGE");

        Assertions.assertEquals(expectedUppercase, uppercase);
    }

    @Test
    @Tag("SOLUTION")
    public void of()
    {
        MutableSet<String> onlyBanana = Sets.mutable.of("🍌");

        var expected = Set.of("🍌");

        Assertions.assertEquals(expected, onlyBanana);
    }

    @Test
    @Tag("SOLUTION")
    public void select()
    {
        ImmutableList<String> justBanana = this.fruitEmoji.select(this.onlyBanana::contains);

        var expected = List.of("🍌");

        Assertions.assertEquals(expected, justBanana);
    }

    @Test
    @Tag("SOLUTION")
    public void reject()
    {
        ImmutableList<String> notBanana = this.fruitEmoji.reject(this.onlyBanana::contains);

        var expected = List.of("🍎", "🍑", "🍒", "🍊");

        Assertions.assertEquals(expected, notBanana);
    }

    @Test
    @Tag("SOLUTION")
    public void count()
    {
        int countBanana = this.fruitEmoji.count(this.onlyBanana::contains);
        int countAll = this.fruitNames.count(this.fruitNames::contains);

        Assertions.assertEquals(1, countBanana);
        Assertions.assertEquals(5, countAll);
    }

    @Test
    @Tag("SOLUTION")
    public void anySatisfy()
    {
        boolean anyBanana = this.fruitEmoji.anySatisfy(this.onlyBanana::contains);
        boolean anyEmpty = this.fruitNames.anySatisfy(String::isEmpty);

        Assertions.assertTrue(anyBanana);
        Assertions.assertFalse(anyEmpty);
    }

    @Test
    @Tag("SOLUTION")
    public void allSatisfy()
    {
        boolean allBanana = this.fruitEmoji.allSatisfy(this.onlyBanana::contains);
        boolean allLowercase =
                this.fruitNames.allSatisfy(string ->
                        Strings.asChars(string).allSatisfy(Character::isLowerCase));

        Assertions.assertFalse(allBanana);
        Assertions.assertTrue(allLowercase);
    }

    @Test
    @Tag("SOLUTION")
    public void noneSatisfy()
    {
        boolean noneBanana = this.fruitEmoji.noneSatisfy(this.onlyBanana::contains);
        boolean noneEmpty = this.fruitNames.noneSatisfy(String::isEmpty);

        Assertions.assertFalse(noneBanana);
        Assertions.assertTrue(noneEmpty);
    }

    @Test
    @Tag("SOLUTION")
    public void groupBy()
    {
        ImmutableListMultimap<Color, Fruit> multimap = Fruit.ALL.groupBy(Fruit::getColor);

        var expected = Multimaps.mutable.list.empty()
                .withKeyMultiValues(Color.RED, Fruit.APPLE, Fruit.CHERRY)
                .withKeyMultiValues(Color.YELLOW, Fruit.BANANA)
                .withKeyMultiValues(Color.ORANGE, Fruit.PEACH, Fruit.ORANGE);

        Assertions.assertEquals(expected, multimap);
    }

    @Test
    @Tag("SOLUTION")
    public void countBy()
    {
        ImmutableBag<Color> colors = Fruit.ALL.countBy(Fruit::getColor);

        var expected = Bags.immutable.withOccurrences(Color.RED, 2, Color.YELLOW, 1, Color.ORANGE, 2);

        Assertions.assertEquals(expected, colors);
    }

    @Test
    @Tag("SOLUTION")
    public void makeString()
    {
        String fruitString = this.fruitNames.makeString("(", ",", ")");
        Assertions.assertEquals("(apple,peach,banana,cherry,orange)", fruitString);

        String fruitEmojis = this.fruitEmoji.makeString("[", ",", "]");
        Assertions.assertEquals("[🍎,🍑,🍌,🍒,🍊]", fruitEmojis);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutable()
    {
        MutableList<String> mutableFruit =
                Lists.mutable.with("🍎", "🍑", "🍌", "🍒", "🍊");

        ImmutableList<String> immutableFruit = mutableFruit.toImmutable();

        Assertions.assertEquals(mutableFruit, immutableFruit);
    }

    @Test
    @Tag("SOLUTION")
    public void asLazy()
    {
        LazyIterable<String> lazyFruit = this.fruitNames.asLazy();

        Assertions.assertEquals(5, lazyFruit.size());
        // Note: LazyIterable does not get exhausted, so you can keep using it.
        Assertions.assertEquals(5, lazyFruit.size());
        var expected = Lists.mutable.with("APPLE", "PEACH", "BANANA", "CHERRY", "ORANGE");
        Assertions.assertEquals(expected, lazyFruit.collect(String::toUpperCase).toList());
    }

    @Test
    @Tag("SOLUTION")
    public void containsBy()
    {
        boolean hasApple = this.fruitNames.containsBy(String::toUpperCase, "APPLE");
        boolean hasTomato = this.fruitNames.containsBy(String::toUpperCase, "TOMATO");

        Assertions.assertTrue(hasApple);
        Assertions.assertFalse(hasTomato);
    }

    @Test
    @Tag("SOLUTION")
    public void detectWith()
    {
        String banana = this.fruitNames.detectWith(String::startsWith, "b");
        String none = this.fruitNames.detectWith(String::startsWith, "d");

        Assertions.assertEquals("banana", banana);
        Assertions.assertNull(none);
    }

    @Test
    @Tag("SOLUTION")
    public void detectWithIfNone()
    {
        String banana =
                this.fruitNames.detectWithIfNone(String::startsWith, "b", () -> "apple");
        String stillBanana =
                this.fruitNames.detectWithIfNone(String::startsWith, "d", () -> "banana");

        Assertions.assertEquals("banana", banana);
        Assertions.assertEquals("banana", stillBanana);
    }

    @Test
    @Tag("SOLUTION")
    public void injectInto()
    {
        StringBuilder stringBuilder =
                this.fruitEmoji.injectInto(new StringBuilder(), StringBuilder::append);

        String mixedFruitString = stringBuilder.toString();

        Assertions.assertEquals("🍎🍑🍌🍒🍊", mixedFruitString);
    }

    @Test
    @Tag("SOLUTION")
    public void partition()
    {
        PartitionImmutableList<String> partitionFruit =
                this.fruitNames.partition(each -> each.contains("n"));

        ImmutableList<String> selected = partitionFruit.getSelected();
        ImmutableList<String> rejected = partitionFruit.getRejected();

        var expectedSelected = Lists.mutable.with("banana", "orange");
        var expectedRejected = Lists.mutable.with("apple", "peach", "cherry");

        Assertions.assertEquals(expectedSelected, selected);
        Assertions.assertEquals(expectedRejected, rejected);
    }

    @Test
    @Tag("SOLUTION")
    public void chunk()
    {
        RichIterable<RichIterable<String>> chunkFruit = this.fruitEmoji.chunk(2);

        Assertions.assertEquals(3, chunkFruit.size());

        var expectedFirst = Lists.mutable.with("🍎", "🍑");
        var expectedLast = Lists.mutable.with("🍊");

        Assertions.assertEquals(expectedFirst, chunkFruit.getFirst());
        Assertions.assertEquals(expectedLast, chunkFruit.getLast());
    }

    @Test
    @Tag("SOLUTION")
    public void sumByInt()
    {
        ImmutableObjectLongMap<Character> sumLengthsByFirstCharacter =
                this.fruitNames.sumByInt(each -> each.charAt(0), String::length);

        var expected = ObjectLongMaps.mutable.<Character>empty()
                .withKeyValue('a', 5)
                .withKeyValue('b', 6)
                .withKeyValue('c', 6)
                .withKeyValue('o', 6)
                .withKeyValue('p', 5);

        Assertions.assertEquals(expected, sumLengthsByFirstCharacter);
    }

    @Test
    @Tag("SOLUTION")
    public void collectInt()
    {
        ImmutableIntList lengths = this.fruitNames.collectInt(String::length);

        var expected = IntLists.immutable.with(5, 5, 6, 6, 6);

        Assertions.assertEquals(expected, lengths);
    }

    @Test
    @Tag("SOLUTION")
    public void flatCollectChar()
    {
        CharBag charCounts = this.fruitNames.flatCollectChar(Strings::asChars, CharBags.mutable.empty());

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
    @Tag("SOLUTION")
    public void asParallel()
    {
        ParallelListIterable<String> parallelFruit =
                this.fruitEmoji.asParallel(Executors.newFixedThreadPool(4), 1);

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
    @Tag("SOLUTION")
    public void distinct()
    {
        MutableList<String> duplicateFruit =
                Lists.mutable.with("🍎", "🍎", "🍌", "🍌");

        MutableList<String> distinctFruit = duplicateFruit.distinct();

        var expected = Lists.mutable.with("🍎", "🍌");
        Assertions.assertEquals(expected, distinctFruit);
    }
}
