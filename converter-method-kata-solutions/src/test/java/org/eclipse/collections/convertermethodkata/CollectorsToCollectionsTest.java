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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.factory.SortedBags;
import org.eclipse.collections.api.factory.SortedMaps;
import org.eclipse.collections.api.factory.SortedSets;
import org.eclipse.collections.api.factory.Stacks;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.impl.collector.Collectors2;
import org.eclipse.collections.impl.list.Interval;
import org.junit.Assert;
import org.junit.Test;

/**
 * Convert the JDK types in the following tests to the missing EC type using APIs on the Collectors2 utility class.
 */
public class CollectorsToCollectionsTest
{
    public static final Person MARY_SMITH = new Person("Mary", "Smith", 25);
    public static final Person TED_FIELDS = new Person("Ted", "Fields", 30);
    public static final Person SALLY_GOLD = new Person("Sally", "Gold", 42);
    private final List<Person> people = List.of(MARY_SMITH, TED_FIELDS, SALLY_GOLD);

    @Test
    public void toList()
    {
        Stream<Integer> interval = IntStream.rangeClosed(1, 5).boxed();
        // Convert interval to a MutableList<Integer> using Collectors2
        MutableList<Integer> list = interval.collect(Collectors2.toList());
        Assert.assertEquals(Lists.mutable.with(1, 2, 3, 4, 5), list);
    }

    @Test
    public void toSet()
    {
        List<Integer> list = List.of(1, 2, 2, 3, 3);
        // Convert list to a MutableSet<Integer> using Collectors2
        MutableSet<Integer> set = list.stream().collect(Collectors2.toSet());
        Assert.assertEquals(Sets.mutable.with(1, 2, 3), set);
    }

    @Test
    public void toBag()
    {
        List<Integer> list = List.of(1, 2, 2, 3, 3);
        // Convert list to a MutableBag<Integer> using Collectors2
        MutableBag<Integer> bag = list.stream().collect(Collectors2.toBag());
        Assert.assertEquals(Bags.mutable.with(1, 2, 2, 3, 3), bag);
    }

    @Test
    public void toStack()
    {
        List<Integer> list = List.of(1, 2, 3);
        // Convert list to a MutableStack<Integer> using Collectors2
        MutableStack<Integer> stack = list.stream().collect(Collectors2.toStack());
        Assert.assertEquals(Stacks.mutable.with(1, 2, 3), stack);
    }

    @Test
    public void toMap()
    {
        List<Integer> list = List.of(1, 2, 3);
        // Convert list to a MutableMap<String, Integer> where the keys are the String value of the element, and the
        // values are the Integer value using Collectors2
        MutableMap<String, Integer> map = list.stream().collect(Collectors2.toMap(String::valueOf, i -> i));
        Assert.assertEquals(Maps.mutable.with("1", 1, "2", 2, "3", 3), map);
    }

    @Test
    public void toSortedList()
    {
        List<Integer> list = List.of(5, 3, 1, 4, 2);
        // Convert list to a sorted MutableList<Integer> using Collectors2
        MutableList<Integer> forward = list.stream().collect(Collectors2.toSortedList());
        // Convert list to a MutableList<Integer> sorted in reverse order using Collectors2
        MutableList<Integer> reverse = list.stream().collect(Collectors2.toSortedList(Comparator.reverseOrder()));
        Assert.assertEquals(Lists.mutable.with(1, 2, 3, 4, 5), forward);
        Assert.assertEquals(Lists.mutable.with(5, 4, 3, 2, 1), reverse);
    }

    @Test
    public void toSortedListByLastName()
    {
        // Convert this.people to a MutableList<Person> sorted by last name using Collectors2
        MutableList<Person> sorted = this.people.stream().collect(Collectors2.toSortedListBy(Person::getLastName));
        Assert.assertEquals(Lists.mutable.with(TED_FIELDS, SALLY_GOLD, MARY_SMITH), sorted);
    }

    @Test
    public void toSortedSet()
    {
        List<Integer> list = List.of(5, 3, 1, 4, 2);
        // Convert list to a sorted MutableSortedSet<Integer> using Collectors2
        MutableSortedSet<Integer> forward = list.stream().collect(Collectors2.toSortedSet());
        // Convert list to a MutableSortedSet<Integer> sorted in reverse order using Collectors2
        MutableSortedSet<Integer> reverse = list.stream().collect(Collectors2.toSortedSet(Comparator.reverseOrder()));
        Assert.assertEquals(SortedSets.mutable.with(1, 2, 3, 4, 5), forward);
        Assert.assertEquals(SortedSets.mutable.with(5, 4, 3, 2, 1), reverse);
    }

    @Test
    public void toSortedSetByFirstName()
    {
        // Convert this.people to a MutableSortedSet<Person> sorted by first name using Collectors2
        MutableSortedSet<Person> sorted = this.people.stream().collect(Collectors2.toSortedSetBy(Person::getFirstName));
        Assert.assertEquals(SortedSets.mutable.with(
                Comparator.comparing(Person::getFirstName),
                MARY_SMITH, SALLY_GOLD, TED_FIELDS), sorted);
    }

    @Test
    public void toSortedBag()
    {
        List<Integer> list = List.of(5, 3, 1, 4, 2);
        // Convert list to a sorted MutableSortedBag<Integer> using Collectors2
        MutableSortedBag<Integer> forward = list.stream().collect(Collectors2.toSortedBag());
        // Convert list to a MutableSortedBag<Integer> sorted in reverse order using Collectors2
        MutableSortedBag<Integer> reverse = list.stream().collect(Collectors2.toSortedBag(Comparator.reverseOrder()));
        Assert.assertEquals(SortedBags.mutable.with(1, 2, 3, 4, 5), forward);
        Assert.assertEquals(SortedBags.mutable.with(5, 4, 3, 2, 1), reverse);
    }

    @Test
    public void toSortedBagByAge()
    {
        // Convert this.people to a MutableSortedBag<Person> sorted by age using Collectors2
        MutableSortedBag<Person> sorted = this.people.stream().collect(Collectors2.toSortedBagBy(Person::getAge));
        Assert.assertEquals(SortedBags.mutable.with(
                Comparator.comparing(Person::getAge),
                MARY_SMITH, TED_FIELDS, SALLY_GOLD), sorted);
    }

    @Test
    public void toStringTest()
    {
        List<Integer> list = List.of(1, 2, 3);
        // Convert the list to a String
        String toString = list.toString();
        // Convert the list to a String with "[", "," "]" as separators using makeString on Collectors2
        String makeString = list.stream().collect(Collectors2.makeString("[", ", ", "]"));
        Assert.assertEquals("[1, 2, 3]", toString);
        Assert.assertEquals("[1, 2, 3]", makeString);
    }
}
