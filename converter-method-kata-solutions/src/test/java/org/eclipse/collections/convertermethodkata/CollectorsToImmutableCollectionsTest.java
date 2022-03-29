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

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.factory.SortedBags;
import org.eclipse.collections.api.factory.SortedSets;
import org.eclipse.collections.api.factory.Stacks;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.impl.collector.Collectors2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Convert the JDK types in the following tests to the missing EC type using APIs on the Collectors2 utility class.
 */
public class CollectorsToImmutableCollectionsTest
{
    @Test
    @Tag("SOLUTION")
    public void toImmutableList()
    {
        Stream<Integer> interval = IntStream.rangeClosed(1, 5).boxed();
        // Convert interval to a ImmutableList<Integer> using Collectors2
        ImmutableList<Integer> list = interval.collect(Collectors2.toImmutableList());
        Assertions.assertEquals(Lists.mutable.with(1, 2, 3, 4, 5), list);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableSet()
    {
        List<Integer> list = List.of(1, 2, 2, 3, 3);
        // Convert list to a ImmutableSet<Integer> using Collectors2
        ImmutableSet<Integer> set = list.stream().collect(Collectors2.toImmutableSet());
        Assertions.assertEquals(Sets.mutable.with(1, 2, 3), set);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableBag()
    {
        List<Integer> list = List.of(1, 2, 2, 3, 3);
        // Convert list to a ImmutableBag<Integer> using Collectors2
        ImmutableBag<Integer> bag = list.stream().collect(Collectors2.toImmutableBag());
        Assertions.assertEquals(Bags.mutable.with(1, 2, 2, 3, 3), bag);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableStack()
    {
        List<Integer> list = List.of(1, 2, 3);
        // Convert list to a ImmutableStack<Integer> using Collectors2
        ImmutableStack<Integer> stack = list.stream().collect(Collectors2.toImmutableStack());
        Assertions.assertEquals(Stacks.mutable.with(1, 2, 3), stack);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableMap()
    {
        List<Integer> list = List.of(1, 2, 3);
        // Convert list to a ImmutableMap<String, Integer> where the keys are the String value of the element, and the
        // values are the Integer value using Collectors2
        ImmutableMap<String, Integer> map = list.stream().collect(Collectors2.toImmutableMap(String::valueOf, i -> i));
        Assertions.assertEquals(Maps.mutable.with("1", 1, "2", 2, "3", 3), map);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableSortedList()
    {
        List<Integer> list = List.of(5, 3, 1, 4, 2);
        // Convert list to a sorted ImmutableList<Integer> using Collectors2
        ImmutableList<Integer> forward = list.stream().collect(Collectors2.toImmutableSortedList());
        // Convert list to a ImmutableList<Integer> sorted in reverse order using Collectors2
        ImmutableList<Integer> reverse = list.stream().collect(Collectors2.toImmutableSortedList(Comparator.reverseOrder()));
        Assertions.assertEquals(Lists.mutable.with(1, 2, 3, 4, 5), forward);
        Assertions.assertEquals(Lists.mutable.with(5, 4, 3, 2, 1), reverse);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableSortedSet()
    {
        List<Integer> list = List.of(5, 3, 1, 4, 2);
        // Convert list to a sorted ImmutableSortedSet<Integer> using Collectors2
        ImmutableSortedSet<Integer> forward = list.stream().collect(Collectors2.toImmutableSortedSet());
        // Convert list to a ImmutableSortedSet<Integer> sorted in reverse order using Collectors2
        ImmutableSortedSet<Integer> reverse = list.stream().collect(Collectors2.toImmutableSortedSet(Comparator.reverseOrder()));
        Assertions.assertEquals(SortedSets.mutable.with(1, 2, 3, 4, 5), forward);
        Assertions.assertEquals(SortedSets.mutable.with(5, 4, 3, 2, 1), reverse);
    }

    @Test
    @Tag("SOLUTION")
    public void toImmutableSortedBag()
    {
        List<Integer> list = List.of(5, 3, 1, 4, 2);
        // Convert list to a sorted ImmutableSortedBag<Integer> using Collectors2
        ImmutableSortedBag<Integer> forward = list.stream().collect(Collectors2.toImmutableSortedBag());
        // Convert list to a ImmutableSortedBag<Integer> sorted in reverse order using Collectors2
        ImmutableSortedBag<Integer> reverse = list.stream().collect(Collectors2.toImmutableSortedBag(Comparator.reverseOrder()));
        Assertions.assertEquals(SortedBags.mutable.with(1, 2, 3, 4, 5), forward);
        Assertions.assertEquals(SortedBags.mutable.with(5, 4, 3, 2, 1), reverse);
    }
}
