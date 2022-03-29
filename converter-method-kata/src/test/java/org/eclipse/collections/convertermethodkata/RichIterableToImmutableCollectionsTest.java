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

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
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
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.sorted.ImmutableSortedMap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Convert the types in the following tests to the missing type using APIs on the RichIterable interface.
 */
public class RichIterableToImmutableCollectionsTest
{
    public static final Person MARY_SMITH = new Person("Mary", "Smith", 25);
    public static final Person TED_FIELDS = new Person("Ted", "Fields", 30);
    public static final Person SALLY_GOLD = new Person("Sally", "Gold", 42);
    private final ImmutableList<Person> people =
            Lists.immutable.with(MARY_SMITH, TED_FIELDS, SALLY_GOLD);

    @Test
    @Tag("KATA")
    public void toList()
    {
        Interval interval = Interval.oneTo(5);
        // Convert interval to a ImmutableList<Integer>
        ImmutableList<Integer> list = null;
        Assertions.assertEquals(Lists.mutable.with(1, 2, 3, 4, 5), list);
    }

    @Test
    @Tag("KATA")
    public void toSet()
    {
        MutableList<Integer> list = Lists.mutable.with(1, 2, 2, 3, 3);
        // Convert list to a ImmutableSet<Integer>
        ImmutableSet<Integer> set = null;
        Assertions.assertEquals(Sets.mutable.with(1, 2, 3), set);
    }

    @Test
    @Tag("KATA")
    public void toBag()
    {
        MutableList<Integer> list = Lists.mutable.with(1, 2, 2, 3, 3);
        // Convert list to a ImmutableBag<Integer>
        ImmutableBag<Integer> bag = null;
        Assertions.assertEquals(Bags.mutable.with(1, 2, 2, 3, 3), bag);
    }

    @Test
    @Tag("KATA")
    public void toStack()
    {
        MutableList<Integer> list = Lists.mutable.with(1, 2, 3);
        // Convert list to a ImmutableStack<Integer>
        ImmutableStack<Integer> stack = null;
        Assertions.assertEquals(Stacks.mutable.with(1, 2, 3), stack);
        // Pop 3 elements off the stack
        // TODO: pop(int) on ImmutableStack needs to be correct to return a Pair.
        Verify.assertSize(0, stack.pop(3));
    }

    @Test
    @Tag("KATA")
    public void toMap()
    {
        MutableList<Integer> list = Lists.mutable.with(1, 2, 3);
        // Convert list to a ImmutableMap<String, Integer> where the keys are the String
        // value of the element, and the values are the Integer value
        ImmutableMap<String, Integer> map = null;
        Assertions.assertEquals(Maps.mutable.with("1", 1, "2", 2, "3", 3), map);
    }

    @Test
    @Tag("KATA")
    public void toSortedList()
    {
        MutableList<Integer> list = Lists.mutable.with(5, 3, 1, 4, 2);
        // Convert list to a sorted ImmutableList<Integer>
        ImmutableList<Integer> forward = null;
        // Convert list to a ImmutableList<Integer> sorted in reverse order
        ImmutableList<Integer> reverse = null;
        Assertions.assertEquals(Lists.mutable.with(1, 2, 3, 4, 5), forward);
        Assertions.assertEquals(Lists.mutable.with(5, 4, 3, 2, 1), reverse);
    }

    @Test
    @Tag("KATA")
    public void toSortedListByLastName()
    {
        // Convert this.people to a ImmutableList<Person> sorted by last name
        ImmutableList<Person> sorted = null;
        Assertions.assertEquals(Lists.mutable.with(TED_FIELDS, SALLY_GOLD, MARY_SMITH), sorted);
    }

    @Test
    @Tag("KATA")
    public void toSortedSet()
    {
        MutableList<Integer> list = Lists.mutable.with(5, 3, 1, 4, 2);
        // Convert list to a sorted ImmutableSortedSet<Integer>
        ImmutableSortedSet<Integer> forward = null;
        // Convert list to a ImmutableSortedSet<Integer> sorted in reverse order
        ImmutableSortedSet<Integer> reverse = null;
        Assertions.assertEquals(SortedSets.mutable.with(1, 2, 3, 4, 5), forward);
        Assertions.assertEquals(SortedSets.mutable.with(5, 4, 3, 2, 1), reverse);
    }

    @Test
    @Tag("KATA")
    public void toSortedSetByFirstName()
    {
        // Convert this.people to a ImmutableSortedSet<Person> sorted by first name
        ImmutableSortedSet<Person> sorted = null;
        Assertions.assertEquals(SortedSets.mutable.with(
                Comparator.comparing(Person::getFirstName),
                MARY_SMITH, SALLY_GOLD, TED_FIELDS), sorted);
    }

    @Test
    @Tag("KATA")
    public void toSortedBag()
    {
        MutableList<Integer> list = Lists.mutable.with(5, 3, 1, 4, 2);
        // Convert list to a sorted ImmutableSortedBag<Integer>
        ImmutableSortedBag<Integer> forward = null;
        // Convert list to a ImmutableSortedBag<Integer> sorted in reverse order
        ImmutableSortedBag<Integer> reverse = null;
        Assertions.assertEquals(SortedBags.mutable.with(1, 2, 3, 4, 5), forward);
        Assertions.assertEquals(SortedBags.mutable.with(5, 4, 3, 2, 1), reverse);
    }

    @Test
    @Tag("KATA")
    public void toSortedBagByAge()
    {
        // Convert this.people to a ImmutableSortedBag<Person> sorted by age
        ImmutableSortedBag<Person> sorted = null;
        Assertions.assertEquals(SortedBags.mutable.with(
                Comparator.comparing(Person::getAge),
                MARY_SMITH, TED_FIELDS, SALLY_GOLD), sorted);
    }

    @Test
    @Tag("KATA")
    public void toSortedMap()
    {
        MutableList<Integer> list = Lists.mutable.with(3, 1, 2);
        // Convert list to a ImmutableSortedMap<String, Integer> where the keys are the String
        // value of the Integer and the values are the Integer values
        ImmutableSortedMap<String, Integer> map = null;
        Assertions.assertEquals(SortedMaps.mutable.with("1", 1, "2", 2, "3", 3), map);
    }

    @Test
    @Tag("KATA")
    public void toSortedMapByLastName()
    {
        // Convert this.people to ImmutableSortedMap<String, Person> where the keys are the last name of the
        // person and the values are the person, and the keys are sorted on their uppercase String value
        ImmutableSortedMap<String, Person> map = null;
        Assertions.assertEquals(
                SortedMaps.mutable.with(
                        "FIELDS", TED_FIELDS,
                        "GOLD", SALLY_GOLD,
                        "SMITH", MARY_SMITH),
                map);
    }
}
