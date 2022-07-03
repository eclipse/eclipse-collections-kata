/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.primitive.immutable;

import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.factory.primitive.CharLists;
import org.eclipse.collections.api.factory.primitive.CharSets;
import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.factory.primitive.IntSets;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableCharSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.string.immutable.CharAdapter;
import org.eclipse.collections.impl.string.immutable.CodePointAdapter;
import org.eclipse.collections.impl.string.immutable.CodePointList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class StringAdaptersTest
{
    private static final String THE_QUICK_BROWN_FOX = "The quick brown fox";

    private final CharAdapter charAdapter = Strings.asChars(THE_QUICK_BROWN_FOX);
    private final CodePointAdapter codePointAdapter = Strings.asCodePoints(THE_QUICK_BROWN_FOX);
    private final CodePointList codePointList = CodePointList.from(THE_QUICK_BROWN_FOX);

    @Test
    @Tag("SOLUTION")
    public void charAdapterToList()
    {
        CharAdapter lowerCase = this.charAdapter.collectChar(Character::toLowerCase);
        MutableCharList list = lowerCase.toList();
        var expectedCharList = CharLists.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedCharList, list.sortThis().distinct());
    }

    @Test
    @Tag("SOLUTION")
    public void charAdapterToSet()
    {
        CharAdapter lowerCase = this.charAdapter.collectChar(Character::toLowerCase);
        MutableCharSet set = lowerCase.toSet();
        var expectedCharSet = CharSets.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedCharSet,set);
    }

    @Test
    @Tag("SOLUTION")
    public void charAdapterToBag()
    {
        CharAdapter lowerCase = this.charAdapter.collectChar(Character::toLowerCase);
        MutableCharBag bag = lowerCase.toBag();
        Assertions.assertEquals(3, bag.occurrencesOf(' '));
    }

    @Test
    @Tag("SOLUTION")
    public void charAdapterReject()
    {
        CharAdapter specialCharsOnly = this.charAdapter.reject(Character::isAlphabetic);
        Assertions.assertEquals("   ", specialCharsOnly.toString());
    }

    @Test
    @Tag("SOLUTION")
    public void charAdapterSelect()
    {
        CharAdapter lettersOnly = this.charAdapter.select(Character::isAlphabetic);
        Assertions.assertEquals("Thequickbrownfox", lettersOnly.toString());
    }

    @Test
    @Tag("SOLUTION")
    public void charAdapterCollectChar()
    {
        CharAdapter upperCase = this.charAdapter.collectChar(Character::toUpperCase);
        Assertions.assertEquals(THE_QUICK_BROWN_FOX.toUpperCase(), upperCase.toString());

        CharAdapter lowerCase = this.charAdapter.collectChar(Character::toLowerCase);
        Assertions.assertEquals(THE_QUICK_BROWN_FOX.toLowerCase(), lowerCase.toString());
    }

    @Test
    @Tag("SOLUTION")
    public void codePointAdapterToList()
    {
        CodePointAdapter lowerCase = this.codePointAdapter.collectInt(Character::toLowerCase);
        MutableIntList list = lowerCase.toList();
        var expectedIntList = IntLists.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntList, list.sortThis().distinct());
    }

    @Test
    @Tag("SOLUTION")
    public void codePointAdapterToSet()
    {
        CodePointAdapter lowerCase = this.codePointAdapter.collectInt(Character::toLowerCase);
        MutableIntSet set = lowerCase.toSet();
        var expectedIntSet = IntSets.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntSet, set);
    }

    @Test
    @Tag("SOLUTION")
    public void codePointAdapterToBag()
    {
        CodePointAdapter lowerCase = this.codePointAdapter.collectInt(Character::toLowerCase);
        MutableIntBag bag = lowerCase.toBag();
        Assertions.assertEquals(3, bag.occurrencesOf(' '));
    }

    @Test
    @Tag("SOLUTION")
    public void codePointAdapterSelect()
    {
        CodePointAdapter lettersOnly = this.codePointAdapter.select(Character::isAlphabetic);
        Assertions.assertEquals("Thequickbrownfox", lettersOnly.toString());
    }

    @Test
    @Tag("SOLUTION")
    public void codePointAdapterReject()
    {
        CodePointAdapter specialIntsOnly = this.codePointAdapter.reject(Character::isAlphabetic);
        Assertions.assertEquals("   ", specialIntsOnly.toString());
    }

    @Test
    @Tag("SOLUTION")
    public void codePointAdapterCollectInt()
    {
        CodePointAdapter upperCase = this.codePointAdapter.collectInt(Character::toUpperCase);
        Assertions.assertEquals("THE QUICK BROWN FOX", upperCase.toString());

        CodePointAdapter lowerCase = this.codePointAdapter.collectInt(Character::toLowerCase);
        Assertions.assertEquals("the quick brown fox", lowerCase.toString());
    }

    @Test
    @Tag("SOLUTION")
    public void codePointListToList()
    {
        CodePointList lowerCase = this.codePointList.collectInt(Character::toLowerCase);
        MutableIntList list = lowerCase.toList();
        var expectedIntList = IntLists.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntList, list.sortThis().distinct());
    }

    @Test
    @Tag("SOLUTION")
    public void codePointListToSet()
    {
        CodePointList lowerCase = this.codePointList.collectInt(Character::toLowerCase);
        MutableIntSet set = lowerCase.toSet();
        var expectedIntSet = IntSets.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntSet, set);
    }

    @Test
    @Tag("SOLUTION")
    public void codePointListToBag()
    {
        CodePointList lowerCase = this.codePointList.collectInt(Character::toLowerCase);
        MutableIntBag bag = lowerCase.toBag();
        Assertions.assertEquals(3, bag.occurrencesOf(' '));
    }

    @Test
    @Tag("SOLUTION")
    public void codePointListSelect()
    {
        CodePointList lettersOnly = this.codePointList.select(Character::isAlphabetic);
        Assertions.assertEquals("Thequickbrownfox", lettersOnly.toString());
    }

    @Test
    @Tag("SOLUTION")
    public void codePointListReject()
    {
        CodePointList specialIntsOnly = this.codePointList.reject(Character::isAlphabetic);
        Assertions.assertEquals("   ", specialIntsOnly.toString());
    }

    @Test
    @Tag("SOLUTION")
    public void codePointListCollectInt()
    {
        CodePointList upperCase = this.codePointList.collectInt(Character::toUpperCase);
        Assertions.assertEquals("THE QUICK BROWN FOX", upperCase.toString());

        CodePointList lowerCase = this.codePointList.collectInt(Character::toLowerCase);
        Assertions.assertEquals("the quick brown fox", lowerCase.toString());
    }
}
