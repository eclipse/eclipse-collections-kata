/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.convertermethodkata;

import org.eclipse.collections.api.bag.primitive.ImmutableIntBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.ImmutableIntSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.factory.primitive.IntBags;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Convert the types in the following tests to the missing type using APIs on the IntIterable interface.
 */
public class IntIterableToCollectionsTest
{
    @Test
    @Tag("SOLUTION")
    public void toList()
    {
        IntInterval interval = IntInterval.oneTo(5);
        // Convert interval to a MutableIntList
        MutableIntList list = interval.toList();
        // Convert list to an ImmutableIntList
        ImmutableIntList immutableIntList = list.toImmutable();
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), list);
        Assertions.assertEquals(list, immutableIntList);
    }

    @Test
    @Tag("SOLUTION")
    public void toSet()
    {
        MutableIntList list = IntLists.mutable.with(1, 2, 2, 3, 3);
        // Convert list to a MutableIntSet
        MutableIntSet set = list.toSet();
        // Convert set to an ImmutableIntSet
        ImmutableIntSet immutableIntSet = set.toImmutable();
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3), set);
        Assertions.assertEquals(set, immutableIntSet);
    }

    @Test
    @Tag("SOLUTION")
    public void toBag()
    {
        MutableIntList list = IntLists.mutable.with(1, 2, 2, 3, 3);
        // Convert list to a MutableIntBag
        MutableIntBag bag = list.toBag();
        // Convert bag to an ImmutableIntBag
        ImmutableIntBag immutableIntBag = bag.toImmutable();
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 2, 3, 3), bag);
        Assertions.assertEquals(bag, immutableIntBag);
    }

    @Test
    @Tag("SOLUTION")
    public void toSortedList()
    {
        MutableIntList list = IntLists.mutable.with(5, 3, 1, 4, 2);
        // Convert list to a sorted MutableIntList
        MutableIntList sorted = list.toSortedList();
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), sorted);
        // Sort the sorted list in reverse order
        MutableIntList forward = sorted.sortThis();
        MutableIntList reversed = sorted.sortThisBy(i -> -i);
        Assertions.assertEquals(IntLists.mutable.with(5, 4, 3, 2, 1), reversed);
        Assertions.assertSame(sorted, forward);
    }

    @Test
    @Tag("SOLUTION")
    public void toArray()
    {
        MutableIntList list = IntLists.mutable.with(1, 2, 3);
        int[] array = list.toArray(new int[3]);
        Assertions.assertArrayEquals(new int[]{1, 2, 3}, array);
    }

    @Test
    @Tag("SOLUTION")
    public void toStringTest()
    {
        MutableIntList list = IntLists.mutable.with(1, 2, 3);
        String toString = list.toString();
        String makeString = list.makeString("[", ", ", "]");
        Assertions.assertEquals("[1, 2, 3]", toString);
        Assertions.assertEquals("[1, 2, 3]", makeString);
    }
}
