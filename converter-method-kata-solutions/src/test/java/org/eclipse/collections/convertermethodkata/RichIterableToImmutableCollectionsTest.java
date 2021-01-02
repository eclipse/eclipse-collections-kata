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
import org.junit.Assert;
import org.junit.Test;

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
    public void toList()
    {
        Interval interval = Interval.oneTo(5);
        // Convert interval to a ImmutableList<Integer>
        ImmutableList<Integer> list = interval.toList().toImmutable();
        Assert.assertEquals(Lists.mutable.with(1, 2, 3, 4, 5), list);
    }

    @Test
    public void toSet()
    {
        MutableList<Integer> list = Lists.mutable.with(1, 2, 2, 3, 3);
        // Convert list to a ImmutableSet<Integer>
        ImmutableSet<Integer> set = list.toSet().toImmutable();
        Assert.assertEquals(Sets.mutable.with(1, 2, 3), set);
    }

    @Test
    public void toBag()
    {
        MutableList<Integer> list = Lists.mutable.with(1, 2, 2, 3, 3);
        // Convert list to a ImmutableBag<Integer>
        ImmutableBag<Integer> bag = list.toBag().toImmutable();
        Assert.assertEquals(Bags.mutable.with(1, 2, 2, 3, 3), bag);
    }

    @Test
    public void toStack()
    {
        MutableList<Integer> list = Lists.mutable.with(1, 2, 3);
        // Convert list to a ImmutableStack<Integer>
        ImmutableStack<Integer> stack = list.toStack().toImmutable();
        Assert.assertEquals(Stacks.mutable.with(1, 2, 3), stack);
        // Pop 3 elements off the stack
        // TODO: pop(int) on ImmutableStack needs to be correct to return a Pair.
        Verify.assertSize(0, stack.pop(3));
    }

    @Test
    public void toMap()
    {
        MutableList<Integer> list = Lists.mutable.with(1, 2, 3);
        // Convert list to a ImmutableMap<String, Integer> where the keys are the String
        // value of the element, and the values are the Integer value
        ImmutableMap<String, Integer> map = list.toMap(String::valueOf, i -> i).toImmutable();
        Assert.assertEquals(Maps.mutable.with("1", 1, "2", 2, "3", 3), map);
    }

    @Test
    public void toSortedList()
    {
        MutableList<Integer> list = Lists.mutable.with(5, 3, 1, 4, 2);
        // Convert list to a sorted ImmutableList<Integer>
        ImmutableList<Integer> forward = list.toSortedList().toImmutable();
        // Convert list to a ImmutableList<Integer> sorted in reverse order
        ImmutableList<Integer> reverse = list.toSortedList(Comparator.reverseOrder()).toImmutable();
        Assert.assertEquals(Lists.mutable.with(1, 2, 3, 4, 5), forward);
        Assert.assertEquals(Lists.mutable.with(5, 4, 3, 2, 1), reverse);
    }

    @Test
    public void toSortedListByLastName()
    {
        // Convert this.people to a ImmutableList<Person> sorted by last name
        ImmutableList<Person> sorted = this.people.toSortedListBy(Person::getLastName).toImmutable();
        Assert.assertEquals(Lists.mutable.with(TED_FIELDS, SALLY_GOLD, MARY_SMITH), sorted);
    }

    @Test
    public void toSortedSet()
    {
        MutableList<Integer> list = Lists.mutable.with(5, 3, 1, 4, 2);
        // Convert list to a sorted ImmutableSortedSet<Integer>
        ImmutableSortedSet<Integer> forward = list.toSortedSet().toImmutable();
        // Convert list to a ImmutableSortedSet<Integer> sorted in reverse order
        ImmutableSortedSet<Integer> reverse = list.toSortedSet(Comparator.reverseOrder()).toImmutable();
        Assert.assertEquals(SortedSets.mutable.with(1, 2, 3, 4, 5), forward);
        Assert.assertEquals(SortedSets.mutable.with(5, 4, 3, 2, 1), reverse);
    }

    @Test
    public void toSortedSetByFirstName()
    {
        // Convert this.people to a ImmutableSortedSet<Person> sorted by first name
        ImmutableSortedSet<Person> sorted = this.people.toSortedSetBy(Person::getFirstName).toImmutable();
        Assert.assertEquals(
                SortedSets.mutable.with(
                        Comparator.comparing(Person::getFirstName),
                        MARY_SMITH, SALLY_GOLD, TED_FIELDS),
                sorted);
    }

    @Test
    public void toSortedBag()
    {
        MutableList<Integer> list = Lists.mutable.with(5, 3, 1, 4, 2);
        // Convert list to a sorted ImmutableSortedBag<Integer>
        ImmutableSortedBag<Integer> forward = list.toSortedBag().toImmutable();
        // Convert list to a ImmutableSortedBag<Integer> sorted in reverse order
        ImmutableSortedBag<Integer> reverse = list.toSortedBag(Comparator.reverseOrder()).toImmutable();
        Assert.assertEquals(SortedBags.mutable.with(1, 2, 3, 4, 5), forward);
        Assert.assertEquals(SortedBags.mutable.with(5, 4, 3, 2, 1), reverse);
    }

    @Test
    public void toSortedBagByAge()
    {
        // Convert this.people to a ImmutableSortedBag<Person> sorted by age
        ImmutableSortedBag<Person> sorted = this.people.toSortedBagBy(Person::getAge).toImmutable();
        Assert.assertEquals(SortedBags.mutable.with(
                Comparator.comparing(Person::getAge),
                MARY_SMITH, TED_FIELDS, SALLY_GOLD), sorted);
    }

    @Test
    public void toSortedMap()
    {
        MutableList<Integer> list = Lists.mutable.with(3, 1, 2);
        // Convert list to a ImmutableSortedMap<String, Integer> where the keys are the String
        // value of the Integer and the values are the Integer values
        ImmutableSortedMap<String, Integer> map = list.toSortedMap(String::valueOf, i -> i).toImmutable();
        Assert.assertEquals(SortedMaps.mutable.with("1", 1, "2", 2, "3", 3), map);
    }

    @Test
    public void toSortedMapByLastName()
    {
        // Convert this.people to ImmutableSortedMap<String, Person> where the keys are the last name of the person
        // and the values are the person, and the keys are sorted on their uppercase String value
        ImmutableSortedMap<String, Person> map = this.people.toSortedMapBy(
                String::toUpperCase,
                Person::getLastName,
                person -> person).toImmutable();
        Assert.assertEquals(
                SortedMaps.mutable.with(
                        "FIELDS", TED_FIELDS,
                        "GOLD", SALLY_GOLD,
                        "SMITH", MARY_SMITH),
                map);
    }
}
