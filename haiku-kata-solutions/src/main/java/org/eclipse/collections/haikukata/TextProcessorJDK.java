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
import java.util.function.Function;
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
        return this.getHaiku().chars();
    }

    public List<Map.Entry<Character, Long>> topLetters()
    {
        // TODO: Map the alphabetic chars from this.getHaikuAsChars() to lowercase into a Map
        // TODO: of Character objects to their counts
        // Hint: Look at IntStream's filter, map, mapToObject, collect methods
        // Hint: Also look at Collectors.groupingBy, Collectors.counting
        Map<Character, Long> chars = this.getHaikuAsChars().filter(Character::isLetter)
                .mapToObj(i -> Character.toLowerCase((char) i)).collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        // TODO: Sort the entries in the Map by their values in reverseOrder
        // TODO: Take the top three entries and convert them to a List
        // Hint: Look at entrySet, stream, sorted, Map.Entry.comparingByValue, Comparator.reverseOrder()
        // Hint: On Stream look at limit and toList.


        return chars.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3).collect(Collectors.toList());
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
        Map<Character, Long> chars = this.getHaikuAsChars()
                .filter(Character::isLetter)               // Filter alphabetic characters
                .mapToObj(ch -> (char) ch)                // Convert to characters
                .map(Character::toLowerCase)               // Convert to lowercase
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        Map<Character, Long> duplicates = chars.entrySet().stream()
                .filter(entry -> 1L < entry.getValue())    // Filter duplicates
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Set<Character> unique = chars.entrySet().stream()
                .filter(entry -> 2L > entry.getValue())    // Filter unique
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        // Returns a specialized "triple" type implemented as a Java Record (see CharCountsDuplicatesUnique above)
        return new CharCountsDuplicatesUnique(chars, duplicates, unique);
    }

    /**
     * A Record implementation to act as a strongly typed "Pair" for topVowelAndConsonant
     */
    public record TopVowelAndConsonant(char vowel, char consonant) {};

    public TopVowelAndConsonant topVowelAndConsonant() {
        // Find all of the alphabetic letters from the haiku, convert them to lowercase,
        // and count the Character values in a Map.
        Map<Character, Long> charCounts = this.getHaikuAsChars()
                .filter(Character::isAlphabetic) // Filter non-alphabetic characters first
                .mapToObj(ch -> (char) ch)
                .map(Character::toLowerCase)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        // Create a List of the top 26 occurrences in Map.Entry<Character, Long> instances sorted in reverse order by count.
        List<Map.Entry<Character, Long>> entries = charCounts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        // Find the top vowel.        
        char topVowel = entries.stream()
                .filter(entry -> isVowel(entry.getKey()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No vowels found in haiku"))
                .getKey();
       
        // Find the top consonant.        
        char topConsonant = entries.stream()
                .filter(entry -> !isVowel(entry.getKey()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No consonants found in haiku"))
                .getKey();

        // Return a specialized "pair" instance named TopVowelAndConsonant implemented via a Java Record
        return new TopVowelAndConsonant(topVowel, topConsonant);
    }

    public boolean isVowel(char character) {
        return switch (character) {
            case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> true;
            default -> false;
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
