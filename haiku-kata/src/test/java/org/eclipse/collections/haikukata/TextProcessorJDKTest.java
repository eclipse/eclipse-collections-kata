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

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TextProcessorJDKTest
{
    @Test
    @Tag("KATA")
    public void topLetters()
    {
        List<Map.Entry<Character, Long>> top3 = new TextProcessorJDK().topLetters();

        Assertions.assertEquals(new AbstractMap.SimpleEntry<>('e', 94L), top3.get(0));
        Assertions.assertEquals(new AbstractMap.SimpleEntry<>('t', 65L), top3.get(1));
        Assertions.assertEquals(new AbstractMap.SimpleEntry<>('i', 62L), top3.get(2));
    }

    @Test
    @Tag("KATA")
    public void distinctLetters()
    {
        String distinctLetters = new TextProcessorJDK().distinctLetters();

        Assertions.assertEquals("breakingthoupvmwcdflsy", distinctLetters);
    }

    @Test
    @Tag("KATA")
    public void duplicatesAndUnique()
    {
        TextProcessorJDK.CharCountsDuplicatesUnique cdu = new TextProcessorJDK().duplicatesAndUnique();

        Assertions.assertEquals(cdu.chars(), cdu.duplicates());
        Assertions.assertEquals(Set.of(), cdu.unique());
    }

    @Test
    @Tag("KATA")
    public void topVowelAndConsonant()
    {
        TextProcessorJDK.TopVowelAndConsonant vowelAndConsonant = new TextProcessorJDK().topVowelAndConsonant();

        Assertions.assertEquals('e', vowelAndConsonant.vowel());
        Assertions.assertEquals('t', vowelAndConsonant.consonant());
    }

    @Test
    @Tag("KATA")
    public void findWordleWords()
    {
        Set<String> wordleWords = new TextProcessorJDK().findWordleWords();
        Set<String> expected =
                Set.of("haiku", "death", "wrote", "bacon", "shine", "house", "where", "thank", "break",
                        "which", "cocoa", "drink", "write", "slide", "found");
        Assertions.assertEquals(expected, wordleWords);
    }
}
