/*
 * Copyright (c) 2023 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.haikukata;

import org.eclipse.collections.api.bag.primitive.CharBag;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.CharSet;
import org.eclipse.collections.api.tuple.Triple;
import org.eclipse.collections.api.tuple.primitive.CharCharPair;
import org.eclipse.collections.api.tuple.primitive.CharIntPair;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.primitive.CharSets;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TextProcessorECTest
{
    @Test
    @Tag("KATA")
    public void topLetters()
    {
        ListIterable<CharIntPair> top3 = new TextProcessorEC().topLetters();

        Assertions.assertEquals(PrimitiveTuples.pair('e', 94), top3.get(0));
        Assertions.assertEquals(PrimitiveTuples.pair('t', 65), top3.get(1));
        Assertions.assertEquals(PrimitiveTuples.pair('i', 62), top3.get(2));
    }

    @Test
    @Tag("KATA")
    public void distinctLetters()
    {
        String distinctLetters = new TextProcessorEC().distinctLetters();

        Assertions.assertEquals("breakingthoupvmwcdflsy", distinctLetters);
    }

    @Test
    @Tag("KATA")
    public void duplicatesAndUnique()
    {
        Triple<CharBag, CharBag, CharSet> triple = new TextProcessorEC().duplicatesAndUnique();

        Assertions.assertEquals(triple.getOne(), triple.getTwo());
        Assertions.assertEquals(CharSets.immutable.empty(), triple.getThree());
    }

    @Test
    @Tag("KATA")
    public void topVowelAndConsonant()
    {
        CharCharPair vowelAndConsonant = new TextProcessorEC().topVowelAndConsonant();

        Assertions.assertEquals('e', vowelAndConsonant.getOne());
        Assertions.assertEquals('t', vowelAndConsonant.getTwo());
    }

    @Test
    @Tag("KATA")
    public void findWordleWords()
    {
        MutableSet<String> wordleWords = new TextProcessorEC().findWordleWords();
        ImmutableSet<String> expected =
                Sets.immutable.with("haiku", "death", "wrote", "bacon", "shine", "house", "where", "thank", "break",
                        "which", "cocoa", "drink", "write", "slide", "found");
        Assertions.assertEquals(expected, wordleWords);
    }
}
