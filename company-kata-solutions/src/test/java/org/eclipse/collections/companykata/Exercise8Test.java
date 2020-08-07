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

import java.util.Comparator;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.ObjectDoubleMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.SortedBags;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * Below are links to APIs that may be helpful during these exercises.
 *
 * {@link RichIterable#aggregateBy(Function, Function0, Function2)}
 * {@link RichIterable#sumByDouble(Function, DoubleFunction)}
 * {@link RichIterable#flatCollect(Function)}
 * {@link MutableList#select(Predicate)}
 * {@link MutableList#anySatisfy(Predicate)}
 * {@link MutableList#toMap(Function, Function)}
 * {@link RichIterable#asLazy()}
 * {@link MutableList#groupBy(Function)}
 * {@link MutableList#collectDouble(DoubleFunction, MutableDoubleCollection)}
 * {@link MutableDoubleList#max()}
 *
 * @see <a href="http://eclipse.github.io/eclipse-collections-kata/company-kata/#/23">Exercise 8 Slides</a>
 */
public class Exercise8Test extends CompanyDomainForKata
{
    /**
     * Extra credit. Aggregate the total order values by city.
     *
     * @see RichIterable#aggregateBy(Function, Function0, Function2)
     */
    @Test
    public void totalOrderValuesByCity()
    {
        Function0<Double> zeroValueFactory = () -> 0.0;
        Function2<Double, Customer, Double> aggregator =
                (result, customer) -> result + customer.getTotalOrderValue();
        MutableMap<String, Double> map = this.company
                .getCustomers()
                .aggregateBy(Customer::getCity, zeroValueFactory, aggregator);

        Verify.assertSize(2, map);
        Assert.assertEquals(Double.valueOf(446.25), map.get("London"));
        Assert.assertEquals(Double.valueOf(857.0), map.get("Liphook"));
    }

    /**
     * Solve the same problem as above using a more specialized API.
     *
     * @see RichIterable#sumByDouble(Function, DoubleFunction)
     */
    @Test
    public void totalOrderValuesByCityUsingPrimitiveValues()
    {
        ObjectDoubleMap<String> map = this.company
                .getCustomers()
                .sumByDouble(Customer::getCity, Customer::getTotalOrderValue);

        Verify.assertSize(2, map);
        Assert.assertEquals(446.25, map.get("London"), 0.0);
        Assert.assertEquals(857.0, map.get("Liphook"), 0.0);
    }

    /**
     * Extra credit. Aggregate the total order values by item.
     * Hint: Look at {@link RichIterable#aggregateBy(Function, Function0, Function2)} and remember
     * how to use {@link RichIterable#flatCollect(Function)} to get an iterable of all items.
     */
    @Test
    public void totalOrderValuesByItem()
    {
        Function0<Double> zeroValueFactory = () -> 0.0;
        Function2<Double, LineItem, Double> aggregator =
                (result, lineItem) -> result + lineItem.getValue();
        MutableMap<String, Double> map = this.company
                .getOrders()
                .flatCollect(Order::getLineItems)
                .aggregateBy(LineItem::getName, zeroValueFactory, aggregator);

        Verify.assertSize(12, map);
        Assert.assertEquals(Double.valueOf(100.0), map.get("shed"));
        Assert.assertEquals(Double.valueOf(10.5), map.get("cup"));
    }

    /**
     * Solve the same problem as above using a more specialized API.
     *
     * @see RichIterable#sumByDouble(Function, DoubleFunction)
     */
    @Test
    public void totalOrderValuesByItemUsingPrimitiveValues()
    {
        ObjectDoubleMap<String> map = this.company
                .getOrders()
                .flatCollect(Order::getLineItems)
                .sumByDouble(LineItem::getName, LineItem::getValue);

        Verify.assertSize(12, map);
        Assert.assertEquals(100.0, map.get("shed"), 0.0);
        Assert.assertEquals(10.5, map.get("cup"), 0.0);
    }

    /**
     * Extra credit. Find all customers' line item values greater than 7.5 and sort them by highest to lowest price.
     */
    @Test
    public void sortedOrders()
    {
        MutableSortedBag<Double> orderedPrices = this.company
                .getOrders()
                .asLazy()
                .flatCollect(Order::getLineItems)
                .select(lineItem -> 7.5 < lineItem.getValue())
                .collect(LineItem::getValue)
                .toSortedBag(Comparator.reverseOrder());

        var expectedPrices = SortedBags.mutable.with(
                Comparator.reverseOrder(), 500.0, 150.0, 120.0, 75.0, 50.0, 50.0, 12.5);
        Verify.assertSortedBagsEqual(expectedPrices, orderedPrices);
    }

    /**
     * Extra credit. Figure out which customers ordered saucers (in any of their orders).
     */
    @Test
    public void whoOrderedSaucers()
    {
        MutableList<Customer> customersWithSaucers = this.company
                .getCustomers()
                .selectWith(Customer::orderedItemNamed, "saucer");

        Verify.assertSize("customers with saucers", 2, customersWithSaucers);
    }

    /**
     * Extra credit. Look into the {@link MutableList#toMap(Function, Function)} method.
     */
    @Test
    public void ordersByCustomerUsingAsMap()
    {
        MutableMap<String, MutableList<Order>> customerNameToOrders = this.company
                .getCustomers()
                .toMap(Customer::getName, Customer::getOrders);

        Assert.assertNotNull("customer name to orders", customerNameToOrders);
        Verify.assertSize("customer names", 3, customerNameToOrders);

        MutableList<Order> ordersForBill = customerNameToOrders.get("Bill");
        Verify.assertSize("Bill orders", 3, ordersForBill);
    }

    /**
     * Extra credit. Create a multimap where the values are customers and the key is the price of
     * the most expensive item that the customer ordered.
     */
    @Test
    public void mostExpensiveItem()
    {
        Multimap<Double, Customer> multimap = this.company
                .getCustomers()
                .groupBy(customer -> customer
                        .getOrders()
                        .asLazy()
                        .flatCollect(Order::getLineItems)
                        .collectDouble(LineItem::getValue)
                        .max());

        Verify.assertSize(3, multimap);
        Verify.assertSize(2, multimap.keysView());

        var expectedCustomers = Lists.mutable.with("Fred", "Bill")
                .collect(this.company::getCustomerNamed);
        Assert.assertEquals(expectedCustomers, multimap.get(50.0));
    }
}
