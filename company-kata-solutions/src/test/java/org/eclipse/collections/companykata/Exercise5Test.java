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

import java.util.List;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.primitive.DoublePredicates;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.Assert;
import org.junit.Test;

/**
 * Below are links to APIs that may be helpful during these exercises.
 *
 * {@link MutableList#collect(Function)}
 * {@link MutableList#collectDouble(DoubleFunction)}
 * {@link MutableList#select(Predicate)}
 *
 * @see <a href="http://eclipse.github.io/eclipse-collections-kata/company-kata/#/15">Exercise 5 Slides</a>
 */
public class Exercise5Test extends CompanyDomainForKata
{
    /**
     * Same exercise but don't use static utility - refactor the type of orders and {@link Customer#getOrders()}
     * instead.
     * Get the order values that are greater than 1.5.
     */
    @Test
    public void filterOrderValues()
    {
        MutableList<Order> orders = this.company.getMostRecentCustomer().getOrders();
        MutableList<Double> orderValues = orders.collect(Order::getValue);
        MutableList<Double> filtered = orderValues.select(Predicates.greaterThan(1.5));

        var expectedValues = Lists.mutable.with(372.5, 1.75);
        Assert.assertEquals(expectedValues, filtered);
        Verify.assertInstanceOf(MutableList.class, this.company.getMostRecentCustomer().getOrders());
        this.company.getMostRecentCustomer().getOrders().add(null);
        Verify.assertContains("Don't return a copy from Customer.getOrders(). The field should be a MutableList.", null, this.company.getMostRecentCustomer().getOrders());
    }

    /**
     * Same as above exercise, but use primitive doubles instead of boxed Doubles.
     */
    @Test
    public void filterOrderValuesUsingPrimitives()
    {
        MutableList<Order> orders = this.company.getMostRecentCustomer().getOrders();
        MutableDoubleList orderValues = orders.collectDouble(Order::getValue);
        MutableDoubleList filtered = orderValues.select(DoublePredicates.greaterThan(1.5));

        var expectedValues = DoubleLists.mutable.with(372.5, 1.75);
        Assert.assertEquals(expectedValues, filtered);
    }

    /**
     * Same exercise but don't use static utility - refactor the type of orders and {@link Customer#getOrders()}
     * instead.
     * Get the actual orders (not their double values) where those orders have a value greater than 2.0.
     */
    @Test
    public void filterOrders()
    {
        MutableList<Order> orders = this.company.getMostRecentCustomer().getOrders();
        MutableList<Order> filtered = orders.select(order -> order.getValue() > 2.0);

        var expectedValues = Lists.mutable.with(Iterate.getFirst(this.company.getMostRecentCustomer().getOrders()));
        Assert.assertEquals(expectedValues, filtered);
        Verify.assertInstanceOf(MutableList.class, this.company.getMostRecentCustomer().getOrders());
        this.company.getMostRecentCustomer().getOrders().add(null);
        Verify.assertContains("Don't return a copy from Customer.getOrders(). The field should be a MutableList.", null, this.company.getMostRecentCustomer().getOrders());
    }
}
