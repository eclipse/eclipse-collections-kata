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

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.collections.impl.block.factory.Functions;

public class TextProcessorJDK
{
    public String getHaiku()
    {
        return new HaikuCollection().getText();
    }

    public IntStream getHaikuAsChars()
    {
        // TODO: Create an IntStream representing the chars from this.getHaiku()
        // Hint: Look at the chars() method on the String class
        return null;
    }

    public List<Map.Entry<Character, Long>> topLetters()
    {
        // TODO: Map the alphabetic chars from this.getHaikuAsChars() to lowercase into a Map
        // TODO: of Character objects to their counts
        // Hint: Look at IntStream's filter, map, mapToObject, collect methods
        // Hint: Also look at Collectors.groupingBy, Collectors.counting
        Map<Character, Long> chars = null;

        // TODO: Sort the entries in the Map by their values in reverseOrder
        // TODO: Take the top three entries and convert them to a List
        // Hint: Look at entrySet, stream, sorted, Map.Entry.comparingByValue, Comparator.reverseOrder()
        // Hint: On Stream look at limit and toList.
        return null;
    }

    public String distinctLetters()
    {
        // TODO: Return all of the distinct alphabetic letters from this.getHaikuAsChars() converted to lowercase as a String
        // Note: The letters should be returned in encounter order from the original Haiku String
        // Hint: Look at IntStream's filter, map, distinct, mapToObject, collect, and toString
        return null;
    }

    /**
     * A Record implementation to act as a strongly typed "Triple" for duplicatesAndUnique
     */
    public record CharCountsDuplicatesUnique(
            Map<Character, Long> chars,
            Map<Character, Long> duplicates,
            Set<Character> unique
    ) {};

    public CharCountsDuplicatesUnique duplicatesAndUnique()
    {
        // TODO: Find all of the alphabetic letters from this.getHaikuAsChars(), convert them to lowercase
        // and count and store them in a Map<Character, Long>.
        // Hint: Look at IntStream's filter, map, mapToObj
        // Hint: Also Look at Stream.collect, Collectors.groupingBy, Collectors.counting
        Map<Character, Long> chars = null;

        // TODO: Find all the Characters with duplicates in the map (value > 1)
        // Hint: Look at entrySet, stream, filter, collect
        // Hint: Also look at Collectors.toMap and using Map.Entry.getKey and Map.Entry.getValue as method references
        Map<Character, Long> duplicates = null;

        // TODO: Find all the unique Characters in the Map (value < 2)
        // Hint: Look at entrySet, stream, filter, map, Map.Entry.getKey, collect, Collectors.toSet
        Set<Character> unique = null;

        // Returns a specialized "triple" type implemented as a Java Record (see CharCountsDuplicatesUnique above)
        return new CharCountsDuplicatesUnique(chars, duplicates, unique);
    }

    /**
     * A Record implementation to act as a strongly typed "Pair" for topVowelAndConsonant
     */
    public record TopVowelAndConsonant(char vowel, char consonant) {};

    public TopVowelAndConsonant topVowelAndConsonant()
    {
        // TODO: Find all of the alphabetic letters from this.getHaikuAsChars(), convert them to lowercase,
        // TODO: count the Character values in a Map and create a List of top 26 occurrences in
        // TODO: Map.Entry<Character, Long> instances sorted in reverse order by count
        // Hint: Look at filter, map, mapToObj, collect, Collectors.groupingBy, Collectors.counting
        // Hint: Also look at entrySet, stream, sorted, Map.Entry.comparingByValue, Comparator.reverseorder, toList
        List<Map.Entry<Character, Long>> entries = null;

        // TODO: Find the top vowel
        // Hint: Use the filter method with findFirst, orElseThrow on Stream with the isVowel method below to find
        // Hint: the top vowel Character value using getKey on Map.Entry.
        char topVowel = 'a';

        // TODO: Find the top consonant
        // Hint: Use the filter method with findFirst, orElseThrow on Stream with the isVowel method below to find
        // Hint: the top consonant Character value using getKey on Map.Entry.
        char topConsonant = 'b';

        // Return a specialized "pair" instance named TopVowelAndConsonant implemented via a Java Record
        return new TopVowelAndConsonant(topVowel, topConsonant);
    }

    public boolean isVowel(char character)
    {
        // TODO: Use a Switch Expression to test for lowercase and uppercase vowels.
        return switch (character)
        {
            case 'a', 'b', 'c' -> false;
            default -> true;
        };
    }

    public Set<String> findWordleWords()
    {
        List<String> words = new Scanner(this.getHaiku()).useDelimiter("[\\s,.!?-]+").tokens().toList();
        // TODO: Filter out the five letter words from the List<String> named words
        // TODO: Exclude contractions, and convert the words to lowercase
        // Hint: Look at filter, map, collect and Collectors.toSet
        Set<String> wordleWords = null;
        return wordleWords;
    }
}
