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

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.map.primitive.ImmutableObjectLongMap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.factory.primitive.CharBags;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class Top25MethodsTest
{
    private final ImmutableList<String> fruitNames = Fruit.toLowerCaseList();
    private final ImmutableSet<String> onlyBanana = Sets.immutable.with(Fruit.BANANA.toLowerCase());

    @Test
    @Tag("SOLUTION")
    public void with()
    {
        MutableList<String> fruit =
                Lists.mutable.with("apple", "apricot", "banana", "blueberry", "clementine");

        var expected = Fruit.toLowerCaseList();

        Assertions.assertEquals(expected, fruit);
    }

    @Test
    @Tag("SOLUTION")
    public void collect()
    {
        ImmutableList<String> uppercase = this.fruitNames.collect(String::toUpperCase);

        var expectedUppercase = Fruit.ALL.collect(Object::toString);

        Assertions.assertEquals(expectedUppercase, uppercase);
    }

    @Test
    @Tag("SOLUTION")
    public void of()
    {
        MutableSet<String> onlyBanana = Sets.mutable.of("banana");

        var expected = Set.of(Fruit.BANANA.toLowerCase());

        Assertions.assertEquals(expected, onlyBanana);
    }

    @Test
    @Tag("SOLUTION")
    public void select()
    {
        ImmutableList<String> justBanana = this.fruitNames.select(this.onlyBanana::contains);

        var expected = List.of(Fruit.BANANA.toLowerCase());

        Assertions.assertEquals(expected, justBanana);
    }

    @Test
    @Tag("SOLUTION")
    public void reject()
    {
        ImmutableList<String> notBanana = this.fruitNames.reject(this.onlyBanana::contains);

        var expected = List.of("apple", "apricot", "blueberry", "clementine");

        Assertions.assertEquals(expected, notBanana);
    }

    @Test
    @Tag("SOLUTION")
    public void count()
    {
        int countBanana = this.fruitNames.count(this.onlyBanana::contains);
        int countAll = this.fruitNames.count(this.fruitNames::contains);

        Assertions.assertEquals(1, countBanana);
        Assertions.assertEquals(5, countAll);
    }

    @Test
    @Tag("SOLUTION")
    public void anySatisfy()
    {
        boolean anyBanana = this.fruitNames.anySatisfy(this.onlyBanana::contains);
        boolean anyEmpty = this.fruitNames.anySatisfy(String::isEmpty);

        Assertions.assertTrue(anyBanana);
        Assertions.assertFalse(anyEmpty);
    }

    @Test
    @Tag("SOLUTION")
    public void allSatisfy()
    {
        boolean allBanana = this.fruitNames.allSatisfy(this.onlyBanana::contains);
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
        boolean noneBanana = this.fruitNames.noneSatisfy(this.onlyBanana::contains);
        boolean noneEmpty = this.fruitNames.noneSatisfy(String::isEmpty);

        Assertions.assertFalse(noneBanana);
        Assertions.assertTrue(noneEmpty);
    }

    @Test
    @Tag("SOLUTION")
    public void groupBy()
    {
        ImmutableListMultimap<Character, String> multimap =
                this.fruitNames.groupBy(each -> each.charAt(0));

        Assertions.assertEquals(List.of("apple", "apricot"), multimap.get('a'));
        Assertions.assertEquals(List.of("banana", "blueberry"), multimap.get('b'));
        Assertions.assertEquals(List.of("clementine"), multimap.get('c'));
    }

    @Test
    @Tag("SOLUTION")
    public void countBy()
    {
        ImmutableBag<Character> firstLetterCounts =
                this.fruitNames.countBy(each -> each.charAt(0));

        Assertions.assertEquals(2, firstLetterCounts.occurrencesOf('a'));
        Assertions.assertEquals(2, firstLetterCounts.occurrencesOf('b'));
        Assertions.assertEquals(1, firstLetterCounts.occurrencesOf('c'));
    }

    @Test
    @Tag("SOLUTION")
    public void makeString()
    {
        String fruitString = this.fruitNames.makeString("(", ",", ")");

        Assertions.assertEquals("(apple,apricot,banana,blueberry,clementine)", fruitString);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutable()
    {
        MutableList<String> mutableFruit =
                Lists.mutable.with("apple", "apricot", "banana", "blueberry", "clementine");

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
        Assertions.assertEquals(Fruit.ALL.collect(Object::toString), lazyFruit.collect(String::toUpperCase).toList());
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
                this.fruitNames.injectInto(new StringBuilder(), StringBuilder::append);

        String mixedFruitString = stringBuilder.toString();

        Assertions.assertEquals("appleapricotbananablueberryclementine", mixedFruitString);
    }

    @Test
    @Tag("SOLUTION")
    public void partition()
    {
        PartitionImmutableList<String> partitionFruit =
                this.fruitNames.partition(each -> each.length() > 6);

        ImmutableList<String> selected = partitionFruit.getSelected();
        ImmutableList<String> rejected = partitionFruit.getRejected();

        var expectedSelected = Lists.mutable.with("apricot", "blueberry", "clementine");
        var expectedRejected = Lists.mutable.with("apple", "banana");

        Assertions.assertEquals(expectedSelected, selected);
        Assertions.assertEquals(expectedRejected, rejected);
    }

    @Test
    @Tag("SOLUTION")
    public void chunk()
    {
        RichIterable<RichIterable<String>> chunkFruit = this.fruitNames.chunk(2);

        Assertions.assertEquals(3, chunkFruit.size());

        var expectedFirst = Lists.mutable.with("apple", "apricot");
        var expectedLast = Lists.mutable.with("clementine");

        Assertions.assertEquals(expectedFirst, chunkFruit.getFirst());
        Assertions.assertEquals(expectedLast, chunkFruit.getLast());
    }

    @Test
    @Tag("SOLUTION")
    public void sumByInt()
    {
        ImmutableObjectLongMap<Character> sumLengthsByFirstCharacter =
                this.fruitNames.sumByInt(each -> each.charAt(0), String::length);

        Assertions.assertEquals(12, sumLengthsByFirstCharacter.get('a'));
        Assertions.assertEquals(15, sumLengthsByFirstCharacter.get('b'));
        Assertions.assertEquals(10, sumLengthsByFirstCharacter.get('c'));
    }

    @Test
    @Tag("SOLUTION")
    public void collectInt()
    {
        ImmutableIntList lengths = this.fruitNames.collectInt(String::length);

        var expected = IntLists.immutable.with(5, 7, 6, 9, 10);

        Assertions.assertEquals(expected, lengths);
    }

    @Test
    @Tag("SOLUTION")
    public void flatCollectChar()
    {
        MutableCharBag charCounts =
                this.fruitNames.flatCollectChar(Strings::asChars, CharBags.mutable.empty());

        Assertions.assertEquals(5, charCounts.occurrencesOf('a'));
        Assertions.assertEquals(3, charCounts.occurrencesOf('b'));
        Assertions.assertEquals(2, charCounts.occurrencesOf('c'));
    }

    @Test
    @Tag("SOLUTION")
    public void asParallel()
    {
        ParallelListIterable<String> parallelFruit =
                this.fruitNames.asParallel(Executors.newFixedThreadPool(4), 1);

        ParallelListIterable<String> parallelSelect =
                parallelFruit.select(this.onlyBanana::contains);

        MutableList<String> onlyBananaList = parallelSelect.toList();
        MutableSet<String> onlyBananaSet = parallelSelect.toSet();

        var expectedList = Lists.mutable.with("banana");
        var expectedSet = Sets.mutable.with("banana");

        Assertions.assertEquals(expectedList, onlyBananaList);
        Assertions.assertEquals(expectedSet, onlyBananaSet);
    }

    @Test
    @Tag("SOLUTION")
    public void distinct()
    {
        MutableList<String> duplicateFruit =
                Lists.mutable.with("apple", "apple", "banana", "banana");

        MutableList<String> distinctFruit = duplicateFruit.distinct();

        var expected = Lists.mutable.with("apple", "banana");
        Assertions.assertEquals(expected, distinctFruit);
    }
}
