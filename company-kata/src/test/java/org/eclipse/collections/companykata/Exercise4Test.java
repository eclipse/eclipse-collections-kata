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
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.DoubleList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.ListIterate;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class Exercise4Test extends CompanyDomainForKata
{
    /**
     * Solve this without changing the return type of {@link Company#getSuppliers()}. Find the appropriate method on
     * {@link ArrayIterate}.
     */
    @Test
    public void findSupplierNames()
    {
        MutableList<String> supplierNames = this.company.getSuppliers().collect(Supplier::getName);

        MutableList<String> expectedSupplierNames = FastList.newListWith(
                "Shedtastic",
                "Splendid Crocks",
                "Annoying Pets",
                "Gnomes 'R' Us",
                "Furniture Hamlet",
                "SFD",
                "Doxins");
        Assert.assertEquals(expectedSupplierNames, supplierNames);
    }

    /**
     * Create a {@link Predicate} for Suppliers that supply more than 2 items. Find the number of suppliers that
     * satisfy that Predicate.
     */
    @Test
    public void countSuppliersWithMoreThanTwoItems()
    {
        Predicate<Supplier> moreThanTwoItems = supplier -> supplier.getItemNames().length > 2;
        int suppliersWithMoreThanTwoItems = this.company.getSuppliers().count(moreThanTwoItems);
        Assert.assertEquals("suppliers with more than 2 items", 5, suppliersWithMoreThanTwoItems);
    }

    /**
     * Try to solve this without changing the return type of {@link Supplier#getItemNames()}.
     */
    @Test
    public void whoSuppliesSandwichToaster()
    {
        Supplier toasterSupplier = this.company.getSuppliers().detectWith(Supplier::supplies, "sandwich toaster");
        Assert.assertNotNull("toaster supplier", toasterSupplier);
        Assert.assertEquals("Doxins", toasterSupplier.getName());
    }

    @Test
    public void filterOrderValues()
    {
        /**
         * Get the order values that are greater than 1.5.
         */
        DoubleList orderValues = this.company
                .getMostRecentCustomer()
                .getOrders()
                .asLazy()
                .collectDouble(Order::getValue)
                .select(value -> value > 1.5)
                .toSortedList();
        Assert.assertEquals(DoubleArrayList.newListWith(1.75d, 372.5), orderValues);
    }

    @Test
    public void filterOrders()
    {
        MutableList<Order> orders = this.company.getMostRecentCustomer().getOrders();
        /**
         * Get the actual orders (not their double values) where those orders have a value greater than 2.0.
         */
        MutableList<Order> filtered = ListIterate.select(orders, order -> order.getValue() > 2.0);
        Assert.assertEquals(FastList.newListWith(this.company.getMostRecentCustomer().getOrders().getFirst()), filtered);
    }
}
