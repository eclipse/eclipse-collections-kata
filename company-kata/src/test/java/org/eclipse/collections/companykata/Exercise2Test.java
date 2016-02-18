/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.companykata;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Exercise2Test extends CompanyDomainForKata
{
    /**
     * Set up a {@link Predicate} that tests to see if a {@link Customer}'s city is "London"
     */
    private static final Predicate<Customer> CUSTOMER_FROM_LONDON = null;

    @Test
    public void customerFromLondonPredicate()
    {
        String predicateClass = CUSTOMER_FROM_LONDON.getClass().getSimpleName();
        Assert.assertTrue(
                "Solution should use Predicates.attributeEquals() or a lambda but used " + predicateClass,
                "AttributePredicate".equals(predicateClass) || predicateClass.startsWith("Exercise2Test$$Lambda"));

        Customer customerFromLondon = new Customer("test customer", "London");

        Assert.assertEquals(
                "Implement Customer.TO_CITY",
                "London",
                Customer.TO_CITY.valueOf(customerFromLondon));

        Assert.assertTrue(
                "CUSTOMER_FROM_LONDON should accept Customers where city is London",
                CUSTOMER_FROM_LONDON.accept(customerFromLondon));
    }

    /**
     * Do any customers come from London? Use the Predicate {@link #CUSTOMER_FROM_LONDON}.
     */
    @Test
    public void doAnyCustomersLiveInLondon()
    {
        boolean anyCustomersFromLondon = false;
        Assert.assertTrue(anyCustomersFromLondon);
    }

    /**
     * Do all customers come from London? Use the Predicate {@link #CUSTOMER_FROM_LONDON}.
     */
    @Test
    public void doAllCustomersLiveInLondon()
    {
        boolean allCustomersFromLondon = true;
        Assert.assertFalse(allCustomersFromLondon);
    }

    /**
     * How many customers come from London? Use the Predicate {@link #CUSTOMER_FROM_LONDON}.
     */
    @Test
    public void howManyCustomersLiveInLondon()
    {
        int numberOfCustomerFromLondon = 0;
        Assert.assertEquals("Should be 2 London customers", 2, numberOfCustomerFromLondon);
    }

    /**
     * Which customers come from London? Get a collection of those which do. Use the Predicate {@link
     * #CUSTOMER_FROM_LONDON}.
     */
    @Test
    public void getLondonCustomers()
    {
        MutableList<Customer> customersFromLondon = null;
        Verify.assertSize("Should be 2 London customers", 2, customersFromLondon);
    }

    /**
     * Which customers do not come from London? Get a collection of those which don't. Use the Predicate {@link
     * #CUSTOMER_FROM_LONDON}.
     */
    @Test
    public void getCustomersWhoDontLiveInLondon()
    {
        MutableList<Customer> customersNotFromLondon = null;
        Verify.assertSize("customers not from London", 1, customersNotFromLondon);
    }

    /**
     * Which customers come from London? Which customers do not come from London? Get a collection of each in a single pass.
     * Use the Predicate {@link #CUSTOMER_FROM_LONDON}.
     */
    @Test
    public void getCustomersWhoDoAndDoNotLiveInLondon()
    {
        MutableList<Customer> customersFromLondon = null;
        MutableList<Customer> customersNotFromLondon = null;
        Verify.assertSize("Should be 2 London customers", 2, customersFromLondon);
        Verify.assertSize("customers not from London", 1, customersNotFromLondon);
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
        Assert.assertNull(
                "Should be null as there is no customer called Pete",
                pete);
    }
}
