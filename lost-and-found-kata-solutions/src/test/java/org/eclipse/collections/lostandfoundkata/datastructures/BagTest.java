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
import java.util.stream.Stream;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.CharBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.impl.collector.Collectors2;
import org.eclipse.collections.impl.factory.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class BagTest
{
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
