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
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.CharBag;
import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.bag.sorted.SortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.primitive.CharToCharFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectIntToObjectFunction;
import org.eclipse.collections.api.block.predicate.primitive.CharPredicate;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.predicate.primitive.ObjectIntPredicate;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.factory.bag.MutableBagFactory;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.collector.Collectors2;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.SortedBags;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.string.immutable.CharAdapter;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * {@link MutableBagFactory#empty()} <br>
 * {@link MutableBagFactory#with(Object[])} <br>
 * {@link MutableBagFactory#withOccurrences(Object, int, Object, int)} <br>
 * {@link MutableBagFactory#withOccurrences(Object, int, Object, int, Object, int, Object, int)} <br>
 * {@link Bag#occurrencesOf(Object)} <br>
 * {@link MutableBag#addOccurrences(Object, int)} <br>
 * {@link MutableBag#removeOccurrences(Object, int)} <br>
 * {@link Bag#forEachWithOccurrences(ObjectIntProcedure)} <br>
 * {@link Bag#selectByOccurrences(IntPredicate)} <br>
 * {@link Bag#collectWithOccurrences(ObjectIntToObjectFunction)} <br>
 * {@link Bag#forEachWithOccurrences(ObjectIntProcedure)} <br>
 * {@link Bag#anySatisfyWithOccurrences(ObjectIntPredicate)} <br>
 * {@link Bag#allSatisfyWithOccurrences(ObjectIntPredicate)} <br>
 * {@link Bag#noneSatisfyWithOccurrences(ObjectIntPredicate)} <br>
 * {@link Bag#toMapOfItemToCount()} <br>
 * {@link MutableBag#setOccurrences(Object, int)} <br>
 * {@link Bag#sizeDistinct()} <br>
 * {@link MutableBag#toImmutable()} <br>
 * {@link MutableBag#toSortedBag()} <br>
 * {@link MutableBag#toImmutableSortedBag()} <br>
 * {@link MutableBag#groupBy(Function)} <br>
 * {@link MutableBag#collect(Function)} <br>
 * {@link MutableBag#toBag()} <br>
 * {@link MutableBag#countBy(Function)} <br>
 * {@link Arrays#stream(Object[])} <br>
 * {@link Stream#collect(Collector)} <br>
 * {@link Collectors2#countBy(Function)} <br>
 * {@link Strings#asChars(String)} <br>
 * {@link CharAdapter#select(CharPredicate)} <br>
 * {@link CharAdapter#collectChar(CharToCharFunction)} <br>
 * {@link CharAdapter#toBag()} <br>
 * <p/>
 *
 * @see Bags
 * @see Arrays
 * @see Collectors2
 * @see Strings
 * @see CharAdapter
 */
public class BagTest
{
    @Test
    public void addOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.empty();
        // Add 1 occurrence of "1" to bag
        // Add 2 occurrences of "2" to bag
        // Add 3 occurrences of "3" to bag
        // Add 4 occurrences of "4" to bag
        // Hint: use addOccurrences() method on MutableBag
        Assertions.assertEquals(1, bag.occurrencesOf("1"));
        Assertions.assertEquals(2, bag.occurrencesOf("2"));
        Assertions.assertEquals(3, bag.occurrencesOf("3"));
        Assertions.assertEquals(4, bag.occurrencesOf("4"));
    }

    @Test
    public void removeOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "1", "1", "2", "2", "2", "2", "3", "3", "3", "3");
        // Remove 2 occurrences of "1"
        // Remove 2 occurrences of "2"
        // Remove 1 occurrence of "3"
        // Remove 1 occurrence of "4"
        // Hint: use removeOccurrences method on MutableBag
        Assertions.assertEquals(1, bag.occurrencesOf("1"));
        Assertions.assertEquals(2, bag.occurrencesOf("2"));
        Assertions.assertEquals(3, bag.occurrencesOf("3"));
    }

    @Test
    public void forEachWithOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.withOccurrences("1", 1, "2", 2, "3", 3, "4", 4);
        MutableSet<ObjectIntPair<String>> set = Sets.mutable.empty();
        // Add an ObjectIntPair<String> to set for each unique String in bag
        // Hint: use forEachWithOccurrences() method on Bag
        MutableSet<ObjectIntPair<String>> expected = Sets.mutable.with(
                PrimitiveTuples.pair("1", 1),
                PrimitiveTuples.pair("2", 2),
                PrimitiveTuples.pair("3", 3),
                PrimitiveTuples.pair("4", 4));
        Assertions.assertEquals(expected, set);
    }

    @Test
    public void selectByOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        // Use selectByOccurrences() on bag to include only elements with more than two occurrences
        MutableBag<String> selected = null;
        MutableBag<String> expected = Bags.mutable.withOccurrences("3", 3, "4", 4);
        Assertions.assertEquals(expected, selected);
    }

    @Test
    public void collectWithOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        // use collectWithOccurrences on bag to return a Bag of ObjectIntPair<String>
        // Hint: Use PrimitiveTuples::pair
        MutableBag<ObjectIntPair<String>> result = null;
        MutableBag<ObjectIntPair<String>> expected = Bags.mutable.with(
                PrimitiveTuples.pair("1", 1),
                PrimitiveTuples.pair("2", 2),
                PrimitiveTuples.pair("3", 3),
                PrimitiveTuples.pair("4", 4));
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void anySatisfyWithOccurrences()
    {
        // Create a Bag to match the conditions specified in the calls to anySatisfyWithOccurrences
        MutableBag<String> bag = Bags.mutable.empty();
        Assertions.assertTrue(bag.anySatisfyWithOccurrences((each, occurrences) -> "1".equals(each) && 1 == occurrences));
        Assertions.assertTrue(bag.anySatisfyWithOccurrences((each, occurrences) -> "2".equals(each) && 2 == occurrences));
        Assertions.assertTrue(bag.anySatisfyWithOccurrences((each, occurrences) -> "3".equals(each) && 3 == occurrences));
        Assertions.assertTrue(bag.anySatisfyWithOccurrences((each, occurrences) -> "4".equals(each) && 4 == occurrences));
    }

    @Test
    public void allSatisfyWithOccurrences()
    {
        // Create a Bag to match the condition specified in the calls to allSatisfyWithOccurrences
        MutableBag<String> bag = Bags.mutable.with("0");
        Assertions.assertTrue(bag.allSatisfyWithOccurrences((each, occurrences) -> "1234".contains(each) && occurrences > 0));
    }

    @Test
    public void noneSatisfyWithOccurrences()
    {
        // Create a Bag to match the condition specified in the calls to noneSatisfyWithOccurrences
        MutableBag<String> bag = Bags.mutable.with("5", "5");
        Assertions.assertTrue(bag.noneSatisfyWithOccurrences((each, occurrences) -> "5678".contains(each) && occurrences > 1));
    }

    @Test
    public void detectWithOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        // Find the String with 4 occurrences
        // Hint: Use detectWithOccurrences()
        String string = null;
        Assertions.assertEquals("4", string);
    }

    @Test
    public void toMapOfItemWithCount()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        // Convert the Bag to a Map<String, Integer> where the String is the distinct item and the Integer is the
        // number of occurrences
        // Hint: Use toMapOfItemWithCount()
        MutableMap<String, Integer> result = null;
        MutableMap<String, Integer> expected = Maps.mutable.with("1", 1, "2", 2, "3", 3, "4", 4);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void setOccurrences()
    {
        MutableBag<String> bag = Bags.mutable.with("1", "1", "2", "3", "4");
        // Set the occurrences of "1" to 1
        // Set the occurrences of "2" to 2
        // Set the occurrences of "3" to 3
        // Set the occurrences of "4" to 4
        // Hint: Use setOccurrences()
        MutableBag<String> expected = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        Assertions.assertEquals(expected, bag);
    }

    @Test
    public void toImmutable()
    {
        Bag<String> bag = Bags.mutable.with("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        // Convert the bag to an ImmutableBag
        // Hint: Use an appropriate "to" method
        Bag<String> immutable = null;
        Assertions.assertEquals(bag, immutable);
        Assertions.assertInstanceOf(ImmutableBag.class, immutable);
    }

    @Test
    public void toSortedBag()
    {
        Bag<String> bag = Bags.mutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        // Convert the bag to a SortedBag
        // Hint: Use an appropriate "to" method
        SortedBag<String> sorted = null;
        SortedBag<String> expected = SortedBags.mutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        List<String> list = List.of("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        Assertions.assertEquals(bag, sorted);
        Assertions.assertEquals(expected, sorted);
        Assertions.assertEquals(list, sorted.toList());
    }

    @Test
    public void toImmutableSortedBag()
    {
        Bag<String> bag = Bags.mutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        // Convert the bag to an ImmutableSortedBag
        // Hint: Use an appropriate "to" method
        ImmutableSortedBag<String> sorted = null;
        ImmutableSortedBag<String> expected = SortedBags.immutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        List<String> list = List.of("1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
        Assertions.assertEquals(bag, sorted);
        Assertions.assertEquals(expected, sorted);
        Assertions.assertEquals(list, sorted.toList());
        Assertions.assertInstanceOf(ImmutableSortedBag.class, sorted);
    }

    @Test
    public void sizeDistinct()
    {
        Bag<String> bag = Bags.mutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        // Find the distinct size of the Bag
        // Hint: Use a distinct form of size
        Assertions.assertEquals(4, bag.size());
    }

    @Test
    public void groupBy()
    {
        Bag<String> bag = Bags.mutable.with("4", "4", "4", "4", "3", "3", "3", "1", "2", "2");
        // Group the bag using an Identity Function
        // Hint: Use groupBy() with Functions.identity()
        BagMultimap<String, String> multimap = null;
        MutableBagMultimap<String, String> expected = Multimaps.mutable.bag.empty();
        expected.putOccurrences("4", "4", 4);
        expected.putOccurrences("3", "3", 3);
        expected.putOccurrences("2", "2", 2);
        expected.putOccurrences("1", "1", 1);
        Assertions.assertEquals(expected, multimap);
    }

    @Test
    void wordCounterCollectToBag()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        // Create a MutableBag of String containing the words in the String above
        // Hint: Use Bags.mutable.with which takes an array
        MutableBag<String> bag = null;
        // Convert the bag of words to a lowerCase Bag of String using collect() and toBag()
        // Note: This can be done lazily or eagerly
        Bag<String> lowerCaseWords = null;

        Assertions.assertEquals(1, lowerCaseWords.occurrencesOf("one"));
        Assertions.assertEquals(2, lowerCaseWords.occurrencesOf("two"));
        Assertions.assertEquals(3, lowerCaseWords.occurrencesOf("three"));
        Assertions.assertEquals(4, lowerCaseWords.occurrencesOf("four"));
    }

    @Test
    void wordCounterCountBy()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        // Create a MutableBag of String containing the words in the String above
        // Hint: Use Bags.mutable.with which takes an array
        MutableBag<String> bag = null;
        // Convert the bag of words to a lowerCase Bag of String using countBy()
        Bag<String> lowerCaseWords = null;

        Assertions.assertEquals(1, lowerCaseWords.occurrencesOf("one"));
        Assertions.assertEquals(2, lowerCaseWords.occurrencesOf("two"));
        Assertions.assertEquals(3, lowerCaseWords.occurrencesOf("three"));
        Assertions.assertEquals(4, lowerCaseWords.occurrencesOf("four"));
    }

    @Test
    void wordCounterCollectors2CountBy()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        // Create a Stream of String containing the words in the String above
        // Hint: Use Arrays.stream()
        Stream<String> stream = null;
        // Convert the Stream of workds to a lowerCase Bag of String using Collectors2.countBy()
        // Hint: Use stream.collect()
        Bag<String> lowerCaseWords = null;

        Assertions.assertEquals(1, lowerCaseWords.occurrencesOf("one"));
        Assertions.assertEquals(2, lowerCaseWords.occurrencesOf("two"));
        Assertions.assertEquals(3, lowerCaseWords.occurrencesOf("three"));
        Assertions.assertEquals(4, lowerCaseWords.occurrencesOf("four"));
    }

    @Test
    void characterCounter()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        // Convert the words to a CharBag including only the alphabetic characters
        // Hint: Use Strings.asChars, select, collectChar and toBag
        CharBag charBag = null;

        Assertions.assertEquals(7, charBag.occurrencesOf('o'));
        Assertions.assertEquals(1, charBag.occurrencesOf('n'));
        Assertions.assertEquals(7, charBag.occurrencesOf('e'));
        Assertions.assertEquals(5, charBag.occurrencesOf('t'));
        Assertions.assertEquals(2, charBag.occurrencesOf('w'));
        Assertions.assertEquals(3, charBag.occurrencesOf('h'));
        Assertions.assertEquals(7, charBag.occurrencesOf('r'));
        Assertions.assertEquals(4, charBag.occurrencesOf('f'));
        Assertions.assertEquals(4, charBag.occurrencesOf('u'));
    }
}
