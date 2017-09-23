/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.companykata;

import java.util.List;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see <a href="http://eclipse.github.io/eclipse-collections-kata/company-kata/#/19">Exercise 7 Slides</a>
 */
public class Exercise7Test extends CompanyDomainForKata
{
    /**
     * Create a multimap where the keys are the names of cities and the values are the customers from those cities.
     */
    @Test
    public void customersByCity()
    {
        // Notice that the second generic type is Customer, not List<Customer>
        MutableListMultimap<String, Customer> multimap = null;

        Assert.assertEquals(Lists.mutable.with(this.company.getCustomerNamed("Mary")), multimap.get("Liphook"));
        Assert.assertEquals(
                Lists.mutable.with(
                        this.company.getCustomerNamed("Fred"),
                        this.company.getCustomerNamed("Bill")),
                multimap.get("London"));
    }

    @Test
    public void mapOfItemsToSuppliers()
    {
        Assert.fail("Refactor this as part of Exercise 7");
        /**
         * Change itemsToSuppliers to a MutableMultimap<String, Supplier>
         */
        MutableMap<String, List<Supplier>> itemsToSuppliers = Maps.mutable.empty();

        for (Supplier supplier : this.company.getSuppliers())
        {
            for (String itemName : supplier.getItemNames())
            {
                List<Supplier> suppliersForItem;
                if (itemsToSuppliers.containsKey(itemName))
                {
                    suppliersForItem = itemsToSuppliers.get(itemName);
                }
                else
                {
                    suppliersForItem = FastList.newList();
                    itemsToSuppliers.put(itemName, suppliersForItem);
                }

                suppliersForItem.add(supplier);
            }
        }
        Verify.assertIterableSize("should be 2 suppliers for sofa", 2, itemsToSuppliers.get("sofa"));
    }

    @Test
    public void reminder()
    {
        Assert.fail("Refactor setUpCustomersAndOrders() in the super class to not have so much repetition.");
        // Delete this whole method when you're done. It's just a reminder.
    }
}
