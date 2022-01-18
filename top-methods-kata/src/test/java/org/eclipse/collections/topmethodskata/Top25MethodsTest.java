/*
 * Copyright (c) 2021 The Bank of New York Mellon.
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.factory.Bags;
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
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.factory.primitive.CharBags;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class Top25MethodsTest
{
    private final ImmutableList<String> fruitNames = Fruit.toLowerCaseList();
    private final ImmutableSet<String> onlyBanana = Sets.immutable.with(Fruit.BANANA.toLowerCase());

    @Test
    @Tag("KATA")
    public void with()
    {
        // Fix by replacing the empty list with a List of lowercase fruit names
        MutableList<String> fruit = Lists.mutable.empty();

        var expected = Fruit.toLowerCaseList();

        Assertions.assertEquals(expected, fruit);
    }

    @Test
    @Tag("KATA")
    public void collect()
    {
        // Fix by collecting the names of the fruit to uppercase
        ImmutableList<String> uppercase = this.fruitNames;

        var expectedUppercase = Fruit.ALL.collect(Object::toString);

        Assertions.assertEquals(expectedUppercase, uppercase);
    }

    @Test
    @Tag("KATA")
    public void of()
    {
        // Fix by replacing the empty set with a Set of just a banana
        MutableSet<String> onlyBanana = Sets.mutable.empty();

        var expected = Set.of(Fruit.BANANA.toLowerCase());

        Assertions.assertEquals(expected, onlyBanana);
    }

    @Test
    @Tag("KATA")
    public void select()
    {
        // Select the fruitNames that are contained in the set of onlyBanana
        ImmutableList<String> justBanana = this.fruitNames;

        var expected = List.of(Fruit.BANANA.toLowerCase());

        Assertions.assertEquals(expected, justBanana);
    }

    @Test
    @Tag("KATA")
    public void reject()
    {
        // Reject the fruitNames that are contained in the set of onlyBanana
        ImmutableList<String> notBanana = this.fruitNames;

        var expected = List.of("apple", "apricot", "blueberry", "clementine");

        Assertions.assertEquals(expected, notBanana);
    }

    @Test
    @Tag("KATA")
    public void count()
    {
        // Count the fruitNames that are contained in the set of onlyBanana
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
        // Are any of the fruitNames contained in the set of onlyBanana?
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
        // Are all of the fruitNames contained in the set of onlyBanana?
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
        // Are none of the fruitNames contained in the set of onlyBanana?
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
        // Group the fruitNames by the first character in the name
        ImmutableListMultimap<Character, String> multimap = Multimaps.immutable.list.empty();

        Assertions.assertEquals(List.of("apple", "apricot"), multimap.get('a'));
        Assertions.assertEquals(List.of("banana", "blueberry"), multimap.get('b'));
        Assertions.assertEquals(List.of("clementine"), multimap.get('c'));
    }

    @Test
    @Tag("KATA")
    public void countBy()
    {
        // Group the fruitNames by the first character in the name
        ImmutableBag<Character> firstLetterCounts = Bags.immutable.empty();

        Assertions.assertEquals(2, firstLetterCounts.occurrencesOf('a'));
        Assertions.assertEquals(2, firstLetterCounts.occurrencesOf('b'));
        Assertions.assertEquals(1, firstLetterCounts.occurrencesOf('c'));
    }

    @Test
    @Tag("KATA")
    public void makeString()
    {
        // Make the fruitNames into a String that starts with "(", ends with ")", and is separated by ","
        String fruitString = this.fruitNames.toString();

        Assertions.assertEquals("(apple,apricot,banana,blueberry,clementine)", fruitString);
    }

    @Test
    @Tag("KATA")
    public void toImmutable()
    {
        MutableList<String> mutableFruit =
                Lists.mutable.with("apple", "apricot", "banana", "blueberry", "clementine");

        // Convert the MutableList of fruit names above into an ImmutableList
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
        Assertions.assertEquals(Fruit.ALL.collect(Object::toString), lazyFruit.collect(String::toUpperCase));
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
        // Use injectInto with a String builder to append all of the fruitNames together in a String.
        StringBuilder stringBuilder = null;

        String mixedFruitString = stringBuilder.toString();

        Assertions.assertEquals("appleapricotbananablueberryclementine", mixedFruitString);
    }

    @Test
    @Tag("KATA")
    public void partition()
    {
        // Partition the fruitNames based on the length of a name greater than 6
        PartitionImmutableList<String> partitionFruit = null;

        ImmutableList<String> selected = partitionFruit.getSelected();
        ImmutableList<String> rejected = partitionFruit.getRejected();

        var expectedSelected = Lists.mutable.with("apricot", "blueberry", "clementine");
        var expectedRejected = Lists.mutable.with("apple", "banana");

        Assertions.assertEquals(expectedSelected, selected);
        Assertions.assertEquals(expectedRejected, rejected);
    }

    @Test
    @Tag("KATA")
    public void chunk()
    {
        // Chunk the fruitNames in batches of size 2
        RichIterable<RichIterable<String>> chunkFruit = null;

        Assertions.assertEquals(3, chunkFruit.size());

        var expectedFirst = Lists.mutable.with("apple", "apricot");
        var expectedLast = Lists.mutable.with("clementine");

        Assertions.assertEquals(expectedFirst, chunkFruit.getFirst());
        Assertions.assertEquals(expectedLast, chunkFruit.getLast());
    }

    @Test
    @Tag("KATA")
    public void sumByInt()
    {
        // Sum the length of the fruitNames by the first character of each item
        ImmutableObjectLongMap<Character> sumLengthsByFirstCharacter = null;

        Assertions.assertEquals(12, sumLengthsByFirstCharacter.get('a'));
        Assertions.assertEquals(15, sumLengthsByFirstCharacter.get('b'));
        Assertions.assertEquals(10, sumLengthsByFirstCharacter.get('c'));
    }

    @Test
    @Tag("KATA")
    public void collectInt()
    {
        // Collect the lengths of the fruitNames as int values
        ImmutableIntList lengths = null;

        var expected = IntLists.immutable.with(5, 7, 6, 9, 10);

        Assertions.assertEquals(expected, lengths);
    }

    @Test
    @Tag("KATA")
    public void flatCollectChar()
    {
        // Flat collect all of the char values in the fruitNames into a mutable CharBag
        MutableCharBag charCounts = null;

        Assertions.assertEquals(5, charCounts.occurrencesOf('a'));
        Assertions.assertEquals(3, charCounts.occurrencesOf('b'));
        Assertions.assertEquals(2, charCounts.occurrencesOf('c'));
    }

    @Test
    @Tag("KATA")
    public void asParallel()
    {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // Convert the fruitNames into a ParallelListIterable using the executorService and a batch size of 1
        ParallelListIterable<String> parallelFruit = null;

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
    @Tag("KATA")
    public void distinct()
    {
        MutableList<String> duplicateFruit =
                Lists.mutable.with("apple", "apple", "banana", "banana");

        // Find the distinct list of fruitNames from duplicateFruit
        MutableList<String> distinctFruit = duplicateFruit;

        var expected = Lists.mutable.with("apple", "banana");
        Assertions.assertEquals(expected, distinctFruit);
    }
}
