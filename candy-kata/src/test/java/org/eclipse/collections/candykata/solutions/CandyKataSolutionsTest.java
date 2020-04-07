/*
 * Copyright (c) 2020 BNY Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.candykata.solutions;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.candykata.Candy;
import org.eclipse.collections.candykata.SchoolGroup;
import org.eclipse.collections.impl.factory.Sets;
import org.junit.Assert;
import org.junit.Test;

public class CandyKataSolutionsTest
{
    @Test
    public void topCandy()
    {
        MutableList<Bag<Candy>> bagsOfCandy = this.collectBagsOfCandy();

        // Hint: Flatten the Bags of Candy into a single Bag
        Bag<Candy> bigBagOfCandy =
                bagsOfCandy.flatCollect(bag -> bag).toBag();

        // Hint: Find the top occurrence in the bag and convert that to a set.
        MutableSet<Candy> mostCommon =
                bigBagOfCandy.topOccurrences(1)
                        .collect(ObjectIntPair::getOne)
                        .toSet();

        var expectedSet = Sets.mutable.with(Candy.REESES_PIECES);
        Assert.assertEquals(expectedSet, mostCommon);
    }

    @Test
    public void commonInTop10()
    {
        MutableList<Bag<Candy>> bagsOfCandy = this.collectBagsOfCandy();

        // Hint: Find the top 10 occurrences of Candy in each of the bags and intersect them.
        MutableSet<Candy> commonInTop10 =
                bagsOfCandy.collect(
                        bag -> bag.topOccurrences(10)
                                .collect(ObjectIntPair::getOne)
                                .toSet())
                        .reduce(MutableSet::intersect)
                        .get();

        var expectedSet = Sets.mutable.with(Candy.REESES_PIECES, Candy.CRUNCH);
        Assert.assertEquals(expectedSet, commonInTop10);
    }

    private MutableList<Bag<Candy>> collectBagsOfCandy()
    {
        return SchoolGroup.all().collect(SchoolGroup::trickOrTreat).toList();
    }
}
