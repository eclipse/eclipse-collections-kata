/*
 * Copyright (c) 2020 The Bank of New York Mellon.
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
import org.junit.Assert;
import org.junit.Test;

/**
 * Convert the types in the following tests to the missing type using APIs on the IntIterable interface.
 */
public class IntIterableToCollectionsTest
{
    @Test
    public void toList()
    {
        IntInterval interval = IntInterval.oneTo(5);
        // Convert interval to a MutableIntList
        MutableIntList list = null;
        // Convert list to an ImmutableIntList
        ImmutableIntList immutableIntList = null;
        Assert.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), list);
        Assert.assertEquals(list, immutableIntList);
    }

    @Test
    public void toSet()
    {
        MutableIntList list = IntLists.mutable.with(1, 2, 2, 3, 3);
        // Convert list to a MutableIntSet
        MutableIntSet set = null;
        // Convert set to an ImmutableIntSet
        ImmutableIntSet immutableIntSet = null;
        Assert.assertEquals(IntSets.mutable.with(1, 2, 3), set);
        Assert.assertEquals(set, immutableIntSet);
    }

    @Test
    public void toBag()
    {
        MutableIntList list = IntLists.mutable.with(1, 2, 2, 3, 3);
        // Convert list to a MutableIntBag
        MutableIntBag bag = null;
        // Convert bag to an ImmutableIntBag
        ImmutableIntBag immutableIntBag = null;
        Assert.assertEquals(IntBags.mutable.with(1, 2, 2, 3, 3), bag);
        Assert.assertEquals(bag, immutableIntBag);
    }

    @Test
    public void toSortedList()
    {
        MutableIntList list = IntLists.mutable.with(5, 3, 1, 4, 2);
        // Convert list to a sorted MutableIntList
        MutableIntList sorted = null;
        Assert.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), sorted);
        // Sort the sorted list in reverse order
        MutableIntList reversed = null;
        Assert.assertEquals(IntLists.mutable.with(5, 4, 3, 2, 1), reversed);
    }

    @Test
    public void toArray()
    {
        MutableIntList list = IntLists.mutable.with(1, 2, 3);
        int[] array = null;
        Assert.assertArrayEquals(new int[]{1, 2, 3}, array);
    }

    @Test
    public void toStringTest()
    {
        MutableIntList list = IntLists.mutable.with(1, 2, 3);
        String toString = null;
        String makeString = null;
        Assert.assertEquals("[1, 2, 3]", toString);
        Assert.assertEquals("[1, 2, 3]", makeString);
    }
}
