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

import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.factory.primitive.CharBags;
import org.eclipse.collections.impl.string.immutable.CharAdapter;

public record WordleEC(String string)
{
    public WordleEC(String string)
    {
        this.string = string.toLowerCase();
    }

    /**
     * TODO - Implement the guess method using Eclipse Collections so tests in WordleECTest pass.
     * <p/>
     * Hint: Look at Strings.asChars(), CharAdapter, CharBags, MutableCharBag
     * <p/>
     * Rules:
     * <p/>
     * <p><ul>
     * <li>If a letter in the guess String doesn’t match a letter in the hidden word,
     * then replace the character in the output with “.”
     * <li>If a letter in the guess String matches a letter in the hidden word and the letter is in the same position,
     * then replace the character with an uppercase letter.
     * <li>If a letter in the guess String matches a letter in the hidden word but the letter is in a different position,
     * then replace the character with a lowercase letter.
     * <li>If a letter matches, but appears more times in the guess String than in the hidden word,
     * then replace the additional characters in the output with a “.”.
     * </ul></p>
     */
    public String guess(String guess)
    {
        // TODO - Replace null with the code needed to satisfy the rules above.
        CharAdapter hiddenChars = Strings.asChars(this.string);
        CharAdapter guessChars = Strings.asChars(guess.toLowerCase());
        MutableCharBag hiddenBag = hiddenChars.toBag();
        MutableCharBag guessBag = guessChars.toBag();

        char[] resChars = new char[guess.length()];

        //If all letters and their positions match ,return guess with uppercase letters
        if (guessChars.equals(hiddenChars)) {
            return guess.toUpperCase();
        }

        for (int i = 0; i < guess.length(); i++) {
            //Delete all char which is not contained by guessBag in hiddenBag
            if (!guessBag.contains(hiddenChars.charAt(i))) {
                hiddenBag.removeOccurrences(hiddenChars.charAt(i),hiddenBag.occurrencesOf(hiddenChars.charAt(i)));
            }
            //Delete all char which is not contained by hiddenBag in guessBag
            if (!hiddenBag.contains(guessChars.charAt(i))) {
                guessBag.removeOccurrences(guessChars.charAt(i),guessBag.occurrencesOf(guessChars.charAt(i)));
            }
            //Check the letter in guess
            if (guessChars.charAt(i) == hiddenChars.charAt(i)) {
                //Meet : " If a letter in the guess String matches a letter in the hidden word and the letter is in the
                // same position, then replace the character with an uppercase letter."
                resChars[i] = (char)(guessChars.charAt(i) - 'a' + 'A');
                guessBag.remove(guessChars.charAt(i));
                hiddenBag.remove(hiddenChars.charAt(i));
            } else {
                resChars[i] = '.';
            }
        }

        int i = 0;
        while(!hiddenBag.isEmpty() && !guessBag.isEmpty()) {
            char curChar = guessChars.charAt(i);
            if (resChars[i] == '.' && guessBag.contains(curChar)) {
                resChars[i] = curChar;
                guessBag.remove(curChar);
                hiddenBag.remove(curChar);
            }
            i++;
        }

        return String.valueOf(resChars);
    }
}
