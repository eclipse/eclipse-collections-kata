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

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.utility.ListIterate;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Customers have a name, city and a list of {@link Order}s
 */
public class Customer
{
    public static final Function<Customer, String> TO_NAME = customer -> {
        Assert.fail("Replace with the implementation of the Function.");
        return null;
    };

    public static final Function<Customer, String> TO_CITY = null;

    public static final Function<Customer, Double> TO_TOTAL_ORDER_VALUE = Customer::getTotalOrderValue;

    private final String name;
    private final String city;

    private final List<Order> orders = new ArrayList<>();

    public Customer(String name, String city)
    {
        this.name = name;
        this.city = city;
    }

    public String getCity()
    {
        return this.city;
    }

    public String getName()
    {
        return this.name;
    }

    public List<Order> getOrders()
    {
        return this.orders;
    }

    public void addOrder(Order anOrder)
    {
        this.orders.add(anOrder);
    }

    public double getTotalOrderValue()
    {
        MutableList<Double> orderValues = ListIterate.collect(this.orders, Order::getValue);
        return orderValues.injectInto(0.0, AddFunction.DOUBLE_TO_DOUBLE);
    }
}
