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
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.CharSet;
import org.eclipse.collections.api.tuple.Triple;
import org.eclipse.collections.api.tuple.primitive.CharCharPair;
import org.eclipse.collections.api.tuple.primitive.CharIntPair;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.string.immutable.CharAdapter;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.eclipse.collections.impl.utility.StringIterate;

public class TextProcessorEC
{
    private String getHaiku()
    {
        return new HaikuCollection().getText();
    }

    public CharAdapter getHaikuAsCharAdapter()
    {
        // TODO: Wrap this.getHaiku() in a CharAdapter
        // Hint: Look a the Strings class.
        return null;
    }

    public ListIterable<CharIntPair> topLetters()
    {
        // TODO: Collect the alphabetic chars from this.getHaikuAsCharAdapter() to lowercase into a CharBag
        // Hint: Look at select, collectChar and toBag
        CharBag chars = null;

        // TODO: Return the top three occurrences of the letters in the CharBag
        return null;
    }

    public String distinctLetters()
    {
        // TODO: Return all of the distinct alphabetic letters from this.getHaikuAsCharAdapter() converted to lowercase as a String
        // Note: The letters should be returned in encounter order from the original Haiku String
        // Hint: Look at select, collectChar, distinct and toString
        return null;
    }

    public Triple<CharBag, CharBag, CharSet> duplicatesAndUnique()
    {
        // TODO: Find all of the alphabetic letters from this.getHaikuAsCharAdapter(), convert them to lowercase
        // and store them in a MutableCharBag.
        // Hint: Look at select, collectChar, and toBag
        MutableCharBag chars = null;

        // TODO: Find all the chars with duplicates
        // Hint: Find a method on MutableCharBag that returns duplicates
        CharBag duplicates = null;

        // TODO: Find all the unique chars
        // Hint: Find a method on MutableCharBag that returns unique chars
        CharSet unique = null;

        return Tuples.triple(chars, duplicates, unique);
    }

    public CharCharPair topVowelAndConsonant()
    {
        // TODO: Find all of the alphabetic letters from this.getHaikuAsCharAdapter(), convert them to lowercase,
        // TODO: put them in a bag and then get the top 26 occurrences
        // Hint: Look at select, collectChar, toBag, and topOccurrences
        // Bonus: See if the same solution will work using asLazy
        MutableList<CharIntPair> charIntPairs = null;

        // TODO: Find the top vowel
        // Hint: Use the detect method on MutableList with the isVowel method below to find the top vowel char value
        char topVowel = 'a';
        // TODO: Find the top consonant
        // Hint: Use the detect method on MutableList with the isVowel method below to find the top consonant char value
        char topConsonant = 'b';

        return PrimitiveTuples.pair(topVowel, topConsonant);
    }

    public boolean isVowel(char character)
    {
        return Strings.asChars("aeiouAEIOU").contains(character);
    }

    public MutableSet<String> findWordleWords()
    {
        MutableList<String> words = Lists.mutable.empty();
        StringIterate.forEachToken(this.getHaiku(), " ,.-!?\t\n\r\f", words::add);
        // TODO: Filter out the five letter words from the MutableList<String> named words
        // TODO: Exclude contractions, and convert the words to lowercase
        // Hint: Look at reject, select, collect and toSet
        MutableSet<String> wordleWords = null;

        return wordleWords;
    }
}
