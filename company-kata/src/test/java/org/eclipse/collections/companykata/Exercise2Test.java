/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.companykata;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Below are links to APIs that may be helpful during these exercises.
 *
 * <p/>
 * {@link MutableList#anySatisfy(Predicate)}<br>
 * {@link MutableList#anySatisfyWith(Predicate2, Object)}<br>
 * {@link MutableList#allSatisfy(Predicate)}<br>
 * {@link MutableList#allSatisfyWith(Predicate2, Object)}<br>
 * {@link MutableList#count(Predicate)}<br>
 * {@link MutableList#countWith(Predicate2, Object)}<br>
 * {@link MutableList#detect(Predicate)}<br>
 * {@link MutableList#detectWith(Predicate2, Object)}<br>
 * {@link MutableList#partition(Predicate)}<br>
 * {@link MutableList#partitionWith(Predicate2, Object)}<br>
 * {@link MutableList#select(Predicate)}<br>
 * {@link MutableList#selectWith(Predicate2, Object)}<br>
 * {@link MutableList#reject(Predicate)}<br>
 * {@link MutableList#rejectWith(Predicate2, Object)}<br>
 *
 * @see <a href="http://eclipse.github.io/eclipse-collections-kata/company-kata/#/3">Exercise 2 Slides</a>
 */
public class Exercise2Test extends CompanyDomainForKata
{
    /**
     * Set up a {@link Predicate} that tests to see if a {@link Customer}'s city is "London".
     */
    @Test
    @Tag("KATA")
    public void customerFromLondonPredicate()
    {
        Predicate<Customer> predicate = null;

        String predicateClass = predicate.getClass().getSimpleName();
        Assertions.assertTrue(
                "AttributePredicate".equals(predicateClass) || predicateClass.startsWith("Exercise2Test$$Lambda"),
                "Solution should use Predicates.attributeEquals() or a lambda but used " + predicateClass);

        Customer customerFromLondon = new Customer("test customer", "London");

        Assertions.assertTrue(
                predicate.accept(customerFromLondon),
                "predicate should accept Customers where city is London");
    }

    @Test
    @Tag("KATA")
    public void doAnyCustomersLiveInLondon()
    {
        boolean anyCustomersFromLondon = false;

        Assertions.assertTrue(anyCustomersFromLondon);
    }

    @Test
    @Tag("KATA")
    public void doAllCustomersLiveInLondon()
    {
        boolean allCustomersFromLondon = true;

        Assertions.assertFalse(allCustomersFromLondon);
    }

    @Test
    @Tag("KATA")
    public void howManyCustomersLiveInLondon()
    {
        int numberOfCustomerFromLondon = 0;

        Assertions.assertEquals(2, numberOfCustomerFromLondon, "Should be 2 London customers");
    }

    @Test
    @Tag("KATA")
    public void getLondonCustomers()
    {
        MutableList<Customer> customersFromLondon = null;

        var expectedNames = Lists.mutable.with("Fred", "Bill");
        Assertions.assertEquals(expectedNames, customersFromLondon.collect(Customer::getName));
    }

    @Test
    @Tag("KATA")
    public void getCustomersWhoDontLiveInLondon()
    {
        MutableList<Customer> customersNotFromLondon = null;

        var expectedNames = Lists.mutable.with("Mary");
        Assertions.assertEquals(expectedNames, customersNotFromLondon.collect(Customer::getName));
    }

    /**
     * Which customers come from London? Which customers do not come from London? Get a collection of both in a single pass.
     */
    @Test
    @Tag("KATA")
    public void getCustomersWhoDoAndDoNotLiveInLondon()
    {
        PartitionMutableList<Customer> customers = null;

        Assertions.assertEquals(Lists.mutable.with("Fred", "Bill"), customers.getSelected().collect(Customer::getName));
        Assertions.assertEquals(Lists.mutable.with("Mary"), customers.getRejected().collect(Customer::getName));
    }

    /**
     * Implement {@link Company#getCustomerNamed(String)} and get this test to pass.
     */
    @Test
    @Tag("KATA")
    public void findMary()
    {
        Customer mary = this.company.getCustomerNamed("Mary");

        Assertions.assertEquals("Mary", mary.getName(), "customer's name should be Mary");
    }

    /**
     * Implement {@link Company#getCustomerNamed(String)} and get this test to pass.
     */
    @Test
    @Tag("KATA")
    public void findPete()
    {
        Customer pete = this.company.getCustomerNamed("Pete");

        Assertions.assertNull(pete, "Should be null as there is no customer called Pete");
    }
}
