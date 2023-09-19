/*
 * Copyright (c) 2023 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.wordlekata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordleECTest
{
//    @Test // Uncomment once guess is implemented for WordleEC
//    @Tag("SOLUTION")
    public void matchWordWithGuess()
    {
        Assertions.assertEquals(".....", new WordleEC("aaaaa").guess("bbbbb"));
        Assertions.assertEquals("A....", new WordleEC("aaaaa").guess("abbbb"));
        Assertions.assertEquals(".A...", new WordleEC("aaaaa").guess("babbb"));
        Assertions.assertEquals("..A..", new WordleEC("aaaaa").guess("bbabb"));
        Assertions.assertEquals("...A.", new WordleEC("aaaaa").guess("bbbab"));
        Assertions.assertEquals("....A", new WordleEC("aaaaa").guess("bbbba"));

        Assertions.assertEquals(".a...", new WordleEC("abbbb").guess("caccc"));
        Assertions.assertEquals("..a..", new WordleEC("abbbb").guess("ccacc"));
        Assertions.assertEquals("...a.", new WordleEC("abbbb").guess("cccac"));
        Assertions.assertEquals("....a", new WordleEC("abbbb").guess("cccca"));

        Assertions.assertEquals("A....", new WordleEC("abbbb").guess("accca"));
        Assertions.assertEquals("A....", new WordleEC("abbbb").guess("accaa"));
        Assertions.assertEquals("A..a.", new WordleEC("aabbb").guess("accaa"));
        Assertions.assertEquals("AA...", new WordleEC("aabbb").guess("aacaa"));
        Assertions.assertEquals("...aa", new WordleEC("aabbb").guess("cccaa"));

        Assertions.assertEquals("..A..", new WordleEC("bbabb").guess("aaaaa"));

        Assertions.assertEquals("AAAAA", new WordleEC("aaaaa").guess("aaaaa"));
        Assertions.assertEquals("BRAVO", new WordleEC("bravo").guess("bravo"));
    }
}
