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
import org.eclipse.collections.api.block.function.primitive.CharToCharFunction;
import org.eclipse.collections.api.block.function.primitive.IntToIntFunction;
import org.eclipse.collections.api.block.predicate.primitive.CharPredicate;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableCharSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.factory.primitive.CharLists;
import org.eclipse.collections.impl.factory.primitive.CharSets;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.string.immutable.CharAdapter;
import org.eclipse.collections.impl.string.immutable.CodePointAdapter;
import org.eclipse.collections.impl.string.immutable.CodePointList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * {@link CharAdapter#collectChar(CharToCharFunction)} <br>
 * {@link CharAdapter#select(CharPredicate)} <br>
 * {@link CharAdapter#reject(CharPredicate)} <br>
 * {@link CharAdapter#toBag()} <br>
 * {@link CharAdapter#toList()} <br>
 * {@link CharAdapter#toSet()} <br>
 *
 * {@link CodePointAdapter#collectInt(IntToIntFunction)} <br>
 * {@link CodePointAdapter#select(IntPredicate)} <br>
 * {@link CodePointAdapter#reject(IntPredicate)} <br>
 * {@link CodePointAdapter#toBag()} <br>
 * {@link CodePointAdapter#toList()} <br>
 * {@link CodePointAdapter#toSet()} <br>
 *
 * {@link CodePointList#collectInt(IntToIntFunction)} <br>
 * {@link CodePointList#select(IntPredicate)} <br>
 * {@link CodePointList#reject(IntPredicate)} <br>
 * {@link CodePointList#toBag()} <br>
 * {@link CodePointList#toList()} <br>
 * {@link CodePointList#toSet()} <br>
 */
public class StringAdaptersTest
{
    private static final String THE_QUICK_BROWN_FOX = "The quick brown fox";

    private final CharAdapter charAdapter = Strings.asChars(THE_QUICK_BROWN_FOX);
    private final CodePointAdapter codePointAdapter = Strings.asCodePoints(THE_QUICK_BROWN_FOX);
    private final CodePointList codePointList = CodePointList.from(THE_QUICK_BROWN_FOX);

    @Test
    public void charAdapterToList()
    {
        // Convert lowercase to a MutableCharList
        MutableCharList list = null;
        var expectedCharList = CharLists.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedCharList, list.sortThis().distinct());
    }

    @Test
    public void charAdapterToSet()
    {
        // Convert lowercase to a MutableCharSet
        MutableCharSet set = null;
        var expectedCharSet = CharSets.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedCharSet, set);
    }

    @Test
    public void charAdapterToBag()
    {
        // Convert lowercase to a MutableCharBag
        MutableCharBag bag = null;
        Assertions.assertEquals(3, bag.occurrencesOf(' '));
    }

    @Test
    public void charAdapterSelect()
    {
        // Filter inclusively all the chars that are alphabetic
        CharAdapter lettersOnly = null;
        Assertions.assertEquals("Thequickbrownfox", lettersOnly.toString());
    }

    @Test
    public void charAdapterReject()
    {
        // Filter exclusively all the chars that are alphabetic
        CharAdapter specialCharsOnly = null;
        Assertions.assertEquals("   ", specialCharsOnly.toString());
    }

    @Test
    public void charAdapterCollectChar()
    {
        // Transform the chars in this.charAdapter to upperCase
        CharAdapter upperCase = null;
        Assertions.assertEquals(THE_QUICK_BROWN_FOX.toUpperCase(), upperCase.toString());

        // Transform the chars in this.charAdapter to lowerCase
        CharAdapter lowerCase = null;
        Assertions.assertEquals(THE_QUICK_BROWN_FOX.toLowerCase(), lowerCase.toString());
    }

    @Test
    public void codePointAdapterToList()
    {
        // Convert lowercase to a MutableIntList
        MutableIntList list = null;
        var expectedIntList = IntLists.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntList, list.sortThis().distinct());
    }

    @Test
    public void codePointAdapterToSet()
    {
        // Convert lowercase to a MutableIntSet
        MutableIntSet set = null;
        var expectedIntSet = IntSets.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntSet, set);
    }

    @Test
    public void codePointAdapterToBag()
    {
        // Convert lowercase to a MutableIntBag
        MutableIntBag bag = null;
        Assertions.assertEquals(3, bag.occurrencesOf(' '));
    }

    @Test
    public void codePointAdapterSelect()
    {
        // Filter inclusively all the codepoints that are alphabetic
        CodePointAdapter lettersOnly = null;
        Assertions.assertEquals("Thequickbrownfox", lettersOnly.toString());
    }

    @Test
    public void codePointAdapterReject()
    {
        // Filter exclusively all the codepoints that are alphabetic
        CodePointAdapter specialIntsOnly = null;
        Assertions.assertEquals("   ", specialIntsOnly.toString());
    }

    @Test
    public void codePointAdapterCollectInt()
    {
        // Transform the codepoints in this.codePointAdapter to upperCase
        CodePointAdapter upperCase = null;
        Assertions.assertEquals("THE QUICK BROWN FOX", upperCase.toString());

        // Transform the codepoints in this.codePointAdapter to lowerCase
        CodePointAdapter lowerCase = null;
        Assertions.assertEquals("the quick brown fox", lowerCase.toString());
    }

    @Test
    public void codePointListToSet()
    {
        // Convert lowercase to a MutableIntSet
        MutableIntSet set = null;
        var expectedIntSet = IntSets.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntSet, set);
    }

    @Test
    public void codePointListToList()
    {
        // Convert lowercase to a MutableIntList
        MutableIntList list = null;
        var expectedIntList = IntLists.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntList, list.sortThis().distinct());
    }

    @Test
    public void codePointListToBag()
    {
        // Convert lowercase to a MutableIntBag
        MutableIntBag bag = null;
        Assertions.assertEquals(3, bag.occurrencesOf(' '));
    }

    @Test
    public void codePointListReject()
    {
        // Filter exclusively all the codepoints that are alphabetic
        CodePointList specialIntsOnly = null;
        Assertions.assertEquals("   ", specialIntsOnly.toString());
    }

    @Test
    public void codePointListSelect()
    {
        // Filter inclusively all the codepoints that are alphabetic
        CodePointList lettersOnly = null;
        Assertions.assertEquals("Thequickbrownfox", lettersOnly.toString());
    }

    @Test
    public void codePointListCollectInt()
    {
        // Transform the codepoints in this.codePointList to upperCase
        CodePointList upperCase = null;
        Assertions.assertEquals("THE QUICK BROWN FOX", upperCase.toString());

        // Transform the codepoints in this.codePointList to lowerCase
        CodePointList lowerCase = null;
        Assertions.assertEquals("the quick brown fox", lowerCase.toString());
    }
}
