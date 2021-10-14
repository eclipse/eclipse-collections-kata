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

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.CharBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.primitive.CharToCharFunction;
import org.eclipse.collections.api.block.predicate.primitive.CharPredicate;
import org.eclipse.collections.impl.collector.Collectors2;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.string.immutable.CharAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BagTest
{
    /**
     * {@link org.eclipse.collections.api.factory.bag.MutableBagFactory#with(Object[])} <br>
     * {@link MutableBag#collect(Function)} <br>
     * {@link MutableBag#toBag()} <br>
     * @see Bags
     */
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

    /**
     * {@link org.eclipse.collections.api.factory.bag.MutableBagFactory#with(Object[])} <br>
     * {@link MutableBag#countBy(Function)} <br>
     * @see Bags
     */
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

    /**
     * {@link Arrays#stream(Object[])} <br>
     * {@link Stream#collect(Collector)} <br>
     * {@link Collectors2#countBy(Function)} <br>
     * @see Arrays
     * @see Collectors2
     */
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

    /**
     * {@link Strings#asChars(String)} <br>
     * {@link CharAdapter#select(CharPredicate)} <br>
     * {@link CharAdapter#collectChar(CharToCharFunction)}     <br>
     * {@link CharAdapter#toBag()}     <br>
     * @see Strings
     * @see CharAdapter
     */
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
