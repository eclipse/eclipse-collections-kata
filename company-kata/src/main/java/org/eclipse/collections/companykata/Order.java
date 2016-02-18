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
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.collection.mutable.CollectionAdapter;
import org.eclipse.collections.impl.utility.Iterate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Has a number, a {@link Customer}, a {@link List} of {@link LineItem}s, and a boolean that states whether or not the order
 * has been delivered. There is a class variable that contains the next order number.
 */
public class Order
{
    public static final Function<Order, Double> TO_VALUE = Order::getValue;

    public static final Function<Order, Iterable<LineItem>> TO_LINE_ITEMS = Order::getLineItems;

    private static int nextOrderNumber = 1;

    private final int orderNumber;
    private final List<LineItem> lineItems = new ArrayList<>();
    private boolean isDelivered;

    public Order()
    {
        this.orderNumber = nextOrderNumber;
        nextOrderNumber += 1;
    }

    public static void resetNextOrderNumber()
    {
        nextOrderNumber = 1;
    }

    public void deliver()
    {
        this.isDelivered = true;
    }

    public boolean isDelivered()
    {
        return this.isDelivered;
    }

    public void addLineItem(LineItem aLineItem)
    {
        this.lineItems.add(aLineItem);
    }

    public List<LineItem> getLineItems()
    {
        return this.lineItems;
    }

    @Override
    public String toString()
    {
        return "order " + this.orderNumber + " items: " + this.lineItems.size();
    }

    public double getValue()
    {
        Collection<Double> itemValues = Iterate.collect(this.lineItems, LineItem::getValue);

        return CollectionAdapter.adapt(itemValues).injectInto(0.0, AddFunction.DOUBLE_TO_DOUBLE);
    }
}
