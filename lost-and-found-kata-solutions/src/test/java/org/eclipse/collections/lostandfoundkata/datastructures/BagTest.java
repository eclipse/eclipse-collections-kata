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
import java.util.stream.Stream;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.CharBag;
import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.bag.sorted.SortedBag;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.primitive.IntPredicates;
import org.eclipse.collections.impl.collector.Collectors2;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.SortedBags;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class BagTest
{
    @Test
    @Tag("SOLUTION")
    public void addOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.empty();
        bag.addOccurrences("1", 1);
        bag.addOccurrences("2", 2);
        bag.addOccurrences("3", 3);
        bag.addOccurrences("4", 4);
        Assertions.assertEquals(1, bag.occurrencesOf("1"));
        Assertions.assertEquals(2, bag.occurrencesOf("2"));
        Assertions.assertEquals(3, bag.occurrencesOf("3"));
        Assertions.assertEquals(4, bag.occurrencesOf("4"));
    }

    @Test
    @Tag("SOLUTION")
    public void removeOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "1", "1", "2", "2", "2", "2", "3", "3", "3", "3");
        bag.removeOccurrences("1", 2);
        bag.removeOccurrences("2", 2);
        bag.removeOccurrences("3", 1);
        bag.removeOccurrences("4", 1);
        Assertions.assertEquals(1, bag.occurrencesOf("1"));
        Assertions.assertEquals(2, bag.occurrencesOf("2"));
        Assertions.assertEquals(3, bag.occurrencesOf("3"));
    }

    @Test
    @Tag("SOLUTION")
    public void forEachWithOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.withOccurrences("1", 1, "2", 2, "3", 3, "4", 4);
        MutableSet<ObjectIntPair<String>> set = Sets.mutable.empty();
        bag.forEachWithOccurrences((each, occurrences) -> set.add(PrimitiveTuples.pair(each, occurrences)));
        MutableSet<ObjectIntPair<String>>  expected = Sets.mutable.with(
                PrimitiveTuples.pair("1", 1),
                PrimitiveTuples.pair("2", 2),
                PrimitiveTuples.pair("3", 3),
                PrimitiveTuples.pair("4", 4));
        Assertions.assertEquals(expected, set);
    }

    @Test
    @Tag("SOLUTION")
    public void selectByOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        MutableBag<String> selected = bag.selectByOccurrences(IntPredicates.greaterThan(2));
        MutableBag<String> expected = Bags.mutable.withOccurrences("3", 3, "4", 4);
        Assertions.assertEquals(expected, selected);
    }

    @Test
    @Tag("SOLUTION")
    public void collectWithOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        MutableBag<ObjectIntPair<String>> result = bag.collectWithOccurrences(PrimitiveTuples::pair);
        MutableBag<ObjectIntPair<String>> expected = Bags.mutable.with(
                PrimitiveTuples.pair("1", 1),
                PrimitiveTuples.pair("2", 2),
                PrimitiveTuples.pair("3", 3),
                PrimitiveTuples.pair("4", 4));
        Assertions.assertEquals(expected, result);
    }

    @Test
    @Tag("SOLUTION")
    public void anySatisfyWithOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        Assertions.assertTrue(bag.anySatisfyWithOccurrences((each, occurrences) -> "1".equals(each) && 1 == occurrences));
        Assertions.assertTrue(bag.anySatisfyWithOccurrences((each, occurrences) -> "2".equals(each) && 2 == occurrences));
        Assertions.assertTrue(bag.anySatisfyWithOccurrences((each, occurrences) -> "3".equals(each) && 3 == occurrences));
        Assertions.assertTrue(bag.anySatisfyWithOccurrences((each, occurrences) -> "4".equals(each) && 4 == occurrences));
    }

    @Test
    @Tag("SOLUTION")
    public void allSatisfyWithOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        Assertions.assertTrue(bag.allSatisfyWithOccurrences((each, occurrences) -> "1234".contains(each) && occurrences > 0));
    }

    @Test
    @Tag("SOLUTION")
    public void noneSatisfyWithOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        Assertions.assertTrue(bag.noneSatisfyWithOccurrences((each, occurrences) -> "5678".contains(each) && occurrences > 1));
    }

    @Test
    @Tag("SOLUTION")
    public void detectWithOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        String string = bag.detectWithOccurrences((each, occurrences) -> occurrences > 3);
        Assertions.assertEquals("4", string);
    }

    @Test
    @Tag("SOLUTION")
    public void toMapOfItemWithCount()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        MutableMap<String, Integer> result = bag.toMapOfItemToCount();
        MutableMap<String, Integer> expected = Maps.mutable.with("1", 1, "2", 2, "3", 3, "4", 4);
        Assertions.assertEquals(expected, result);
    }

    @Test
    @Tag("SOLUTION")
    public void setOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "1", "2", "3", "4");
        bag.setOccurrences("1", 1);
        bag.setOccurrences("2", 2);
        bag.setOccurrences("3", 3);
        bag.setOccurrences("4", 4);
        MutableBag<String> expected = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        Assertions.assertEquals(expected, bag);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutable()
    {
        Bag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        Bag<String> immutable = bag.toImmutable();
        Assertions.assertEquals(bag, immutable);
        Assertions.assertInstanceOf(ImmutableBag.class, immutable);
    }

    @Test
    @Tag("SOLUTION")
    public void toSortedBag()
    {
        Bag<String> bag = Bags.mutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        SortedBag<String> sorted = bag.toSortedBag();
        SortedBag<String> expected = SortedBags.mutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        List<String> list = List.of("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        Assertions.assertEquals(bag, sorted);
        Assertions.assertEquals(expected, sorted);
        Assertions.assertEquals(list, sorted.toList());
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableSortedBag()
    {
        Bag<String> bag = Bags.mutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        ImmutableSortedBag<String> sorted = bag.toImmutableSortedBag();
        ImmutableSortedBag<String> expected = SortedBags.immutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        List<String> list = List.of("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        Assertions.assertEquals(bag, sorted);
        Assertions.assertEquals(expected, sorted);
        Assertions.assertEquals(list, sorted.toList());
    }

    @Test
    @Tag("SOLUTION")
    public void sizeDistinct()
    {
        Bag<String> bag = Bags.mutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        Assertions.assertEquals(4, bag.sizeDistinct());
    }

    @Test
    @Tag("SOLUTION")
    public void groupBy()
    {
        Bag<String> bag = Bags.mutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        BagMultimap<String, String> multimap = bag.groupBy(Functions.identity());
        MutableBagMultimap<String, String> expected = Multimaps.mutable.bag.empty();
        expected.putOccurrences("4", "4", 4);
        expected.putOccurrences("3", "3", 3);
        expected.putOccurrences("2", "2", 2);
        expected.putOccurrences("1", "1", 1);
        Assertions.assertEquals(expected, multimap);
    }

    @Test
    @Tag("SOLUTION")
    void wordCounterCollectToBag()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        MutableBag<String> bag = Bags.mutable.with(words.split(" "));
        Bag<String> lowerCaseWords = bag.asLazy()
                .collect(String::toLowerCase)
                .toBag();

        Assertions.assertEquals(1, lowerCaseWords.occurrencesOf("one"));
        Assertions.assertEquals(2, lowerCaseWords.occurrencesOf("two"));
        Assertions.assertEquals(3, lowerCaseWords.occurrencesOf("three"));
        Assertions.assertEquals(4, lowerCaseWords.occurrencesOf("four"));
    }

    @Test
    @Tag("SOLUTION")
    void wordCounterCountBy()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        MutableBag<String> bag = Bags.mutable.with(words.split(" "));
        Bag<String> lowerCaseWords = bag.countBy(String::toLowerCase);

        Assertions.assertEquals(1, lowerCaseWords.occurrencesOf("one"));
        Assertions.assertEquals(2, lowerCaseWords.occurrencesOf("two"));
        Assertions.assertEquals(3, lowerCaseWords.occurrencesOf("three"));
        Assertions.assertEquals(4, lowerCaseWords.occurrencesOf("four"));
    }

    @Test
    @Tag("SOLUTION")
    void wordCounterCollectors2CountBy()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        Stream<String> stream = Arrays.stream(words.split(" "));
        Bag<String> lowerCaseWords =
                stream.collect(Collectors2.countBy(String::toLowerCase));

        Assertions.assertEquals(1, lowerCaseWords.occurrencesOf("one"));
        Assertions.assertEquals(2, lowerCaseWords.occurrencesOf("two"));
        Assertions.assertEquals(3, lowerCaseWords.occurrencesOf("three"));
        Assertions.assertEquals(4, lowerCaseWords.occurrencesOf("four"));
    }

    @Test
    void characterCounter()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        CharBag lowerCaseLetters = Strings.asChars(words)
                .select(Character::isAlphabetic)
                .collectChar(Character::toLowerCase)
                .toBag();

        Assertions.assertEquals(7, lowerCaseLetters.occurrencesOf('o'));
        Assertions.assertEquals(1, lowerCaseLetters.occurrencesOf('n'));
        Assertions.assertEquals(7, lowerCaseLetters.occurrencesOf('e'));
        Assertions.assertEquals(5, lowerCaseLetters.occurrencesOf('t'));
        Assertions.assertEquals(2, lowerCaseLetters.occurrencesOf('w'));
        Assertions.assertEquals(3, lowerCaseLetters.occurrencesOf('h'));
        Assertions.assertEquals(7, lowerCaseLetters.occurrencesOf('r'));
        Assertions.assertEquals(4, lowerCaseLetters.occurrencesOf('f'));
        Assertions.assertEquals(4, lowerCaseLetters.occurrencesOf('u'));
    }
}
