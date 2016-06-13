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

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.DoubleList;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Exercise6Test extends CompanyDomainForKata
{
    /**
     * Get a list of the customers' total order values, sorted. Check out the implementation of {@link
     * Customer#getTotalOrderValue()} and {@link Order#getValue()} .
     */
    @Test
    public void sortedTotalOrderValue()
    {
    	DoubleList sortedTotalValues = this.company.getCustomers().collectDouble(Customer::getTotalOrderValue).sortThis();

        // Don't forget the handy utility methods getFirst() and getLast()...
        Assert.assertEquals("Highest total order value", 857.0, sortedTotalValues.getLast(), 0.0);
        Assert.assertEquals("Lowest total order value", 71.0, sortedTotalValues.getFirst(), 0.0);
    }

    /**
     * Find the max total order value across all customers.
     */
    @Test
    public void maximumTotalOrderValue()
    {
        Double maximumTotalOrderValue = this.company.getCustomers().asLazy().collectDouble(Customer::getTotalOrderValue).max();
        Assert.assertEquals("max value", 857.0d, maximumTotalOrderValue, 0.0d);
    }

    /**
     * Find the customer with the highest total order value.
     */
    @Test
    public void customerWithMaxTotalOrderValue()
    {
        Customer customerWithMaxTotalOrderValue = this.company.getCustomers().maxBy(Customer::getTotalOrderValue);
        Assert.assertEquals(this.company.getCustomerNamed("Mary"), customerWithMaxTotalOrderValue);
    }

    /**
     * Create some code to get the company's supplier names as a tilde delimited string.
     */
    @Test
    public void supplierNamesAsTildeDelimitedString()
    {
        String tildeSeparatedNames = this.company.getSuppliers().collect(Supplier::getName).makeString("~");
        Assert.assertEquals(
                "tilde separated names",
                "Shedtastic~Splendid Crocks~Annoying Pets~Gnomes 'R' Us~Furniture Hamlet~SFD~Doxins",
                tildeSeparatedNames);
    }

    /**
     * Deliver all orders going to customers from London.
     * <p/>
     * Hint: Use {@link RichIterable#forEach(Procedure)}. To solve the ambiguity error, use {@link Procedures#cast(Procedure)}}.
     *
     * @see Order#deliver()
     */
    @Test
    public void deliverOrdersToLondon()
    {
    	this.company.getCustomers().asLazy().selectWith(Customer::livesIn, "London").flatCollect(Customer::getOrders).forEach(Procedures.cast(Order::deliver));
        Verify.assertAllSatisfy(this.company.getCustomerNamed("Fred").getOrders(), Order::isDelivered);
        Verify.assertNoneSatisfy(this.company.getCustomerNamed("Mary").getOrders(), Order::isDelivered);
        Verify.assertAllSatisfy(this.company.getCustomerNamed("Bill").getOrders(), Order::isDelivered);
    }
}
