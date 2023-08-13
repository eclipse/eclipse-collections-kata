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

public record WordleJDK(String string)
{
    public WordleJDK(String string)
    {
        this.string = string.toLowerCase();
    }

    /**
     * TODO - Implement the guess method using Java Streams so tests in WordleJDKTest pass.
     * <p/>
     * Hint: Look at String.chars() and methods on IntStream.
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
        return null;
    }
}
