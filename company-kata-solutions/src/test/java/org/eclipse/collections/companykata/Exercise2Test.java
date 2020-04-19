/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.companykata;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

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
    public void customerFromLondonPredicate()
    {
        Predicate<Customer> predicate = null;

        String predicateClass = predicate.getClass().getSimpleName();
        Assert.assertTrue(
                "Solution should use Predicates.attributeEquals() or a lambda but used " + predicateClass,
                "AttributePredicate".equals(predicateClass) || predicateClass.startsWith("Exercise2Test$$Lambda"));

        Customer customerFromLondon = new Customer("test customer", "London");

        Assert.assertTrue(
                "predicate should accept Customers where city is London",
                predicate.accept(customerFromLondon));
    }

    @Test
    public void doAnyCustomersLiveInLondon()
    {
        boolean anyCustomersFromLondon = false;

        Assert.assertTrue(anyCustomersFromLondon);
    }

    @Test
    public void doAllCustomersLiveInLondon()
    {
        boolean allCustomersFromLondon = true;

        Assert.assertFalse(allCustomersFromLondon);
    }

    @Test
    public void howManyCustomersLiveInLondon()
    {
        int numberOfCustomerFromLondon = 0;

        Assert.assertEquals("Should be 2 London customers", 2, numberOfCustomerFromLondon);
    }

    @Test
    public void getLondonCustomers()
    {
        MutableList<Customer> customersFromLondon = null;

        Verify.assertSize("Should be 2 London customers", 2, customersFromLondon);
    }

    @Test
    public void getCustomersWhoDontLiveInLondon()
    {
        MutableList<Customer> customersNotFromLondon = null;

        Verify.assertSize("customers not from London", 1, customersNotFromLondon);
    }

    /**
     * Which customers come from London? Which customers do not come from London? Get a collection of both in a single pass.
     */
    @Test
    public void getCustomersWhoDoAndDoNotLiveInLondon()
    {
        PartitionMutableList<Customer> customers = null;

        Verify.assertSize("Should be 2 London customers", 2, customers.getSelected());
        Verify.assertSize("customers not from London", 1, customers.getRejected());
    }

    /**
     * Implement {@link Company#getCustomerNamed(String)} and get this test to pass.
     */
    @Test
    public void findMary()
    {
        Customer mary = this.company.getCustomerNamed("Mary");

        Assert.assertEquals("customer's name should be Mary", "Mary", mary.getName());
    }

    /**
     * Implement {@link Company#getCustomerNamed(String)} and get this test to pass.
     */
    @Test
    public void findPete()
    {
        Customer pete = this.company.getCustomerNamed("Pete");

        Assert.assertNull("Should be null as there is no customer called Pete", pete);
    }
}
