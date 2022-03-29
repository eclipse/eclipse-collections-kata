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

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Below are links to APIs that may be helpful during these exercises.
 *
 * {@link MutableList#collect(Function)}
 * {@link MutableList#collectDouble(DoubleFunction)}
 * {@link MutableList#toSortedList()}
 * {@link MutableDoubleList#toSortedList()}
 * {@link MutableList#max()}
 * {@link MutableDoubleList#max()}
 * {@link MutableList#maxBy(Function)}
 * {@link RichIterable#makeString(String)}
 * {@link MutableList#select(Predicate)}
 * {@link MutableList#selectWith(Predicate2, Object)}
 * {@link RichIterable#each(Procedure)}
 *
 * @see <a href="http://eclipse.github.io/eclipse-collections-kata/company-kata/#/17">Exercise 6 Slides</a>
 */
public class Exercise6Test extends CompanyDomainForKata
{
    /**
     * Get a list of the customers' total order values, sorted. Check out the implementation of {@link
     * Customer#getTotalOrderValue()} and {@link Order#getValue()} .
     */
    @Test
    @Tag("KATA")
    public void sortedTotalOrderValue()
    {
        MutableList<Double> sortedTotalValues = null;

        // Don't forget the handy utility methods getFirst() and getLast()...
        Assertions.assertEquals(Double.valueOf(857.0), sortedTotalValues.getLast(), "Highest total order value");
        Assertions.assertEquals(Double.valueOf(71.0), sortedTotalValues.getFirst(), "Lowest total order value");
    }

    /**
     * Get a list of the customers' total order values, sorted. Use primitive doubles instead of boxed Doubles.
     */
    @Test
    @Tag("KATA")
    public void sortedTotalOrderValueUsingPrimitives()
    {
        MutableDoubleList sortedTotalValues = null;

        // Don't forget the handy utility methods getFirst() and getLast()...
        Assertions.assertEquals(857.0, sortedTotalValues.getLast(), 0.0, "Highest total order value");
        Assertions.assertEquals(71.0, sortedTotalValues.getFirst(), 0.0, "Lowest total order value");
    }

    /**
     * Find the max total order value across all customers.
     */
    @Test
    @Tag("KATA")
    public void maximumTotalOrderValue()
    {
        Double maximumTotalOrderValue = null;

        Assertions.assertEquals(Double.valueOf(857.0), maximumTotalOrderValue, "max value");
    }

    /**
     * Find the max total order value across all customers, but use primitive double instead of boxed Double.
     */
    @Test
    @Tag("KATA")
    public void maximumTotalOrderValueUsingPrimitives()
    {
        double maximumTotalOrderValue = 0.0;

        Assertions.assertEquals(857.0, maximumTotalOrderValue, 0.0, "max value");
    }

    /**
     * Find the customer with the highest total order value.
     */
    @Test
    @Tag("KATA")
    public void customerWithMaxTotalOrderValue()
    {
        Customer customerWithMaxTotalOrderValue = null;

        Assertions.assertEquals(this.company.getCustomerNamed("Mary"), customerWithMaxTotalOrderValue);
    }

    /**
     * Create some code to get the company's supplier names as a tilde delimited string.
     */
    @Test
    @Tag("KATA")
    public void supplierNamesAsTildeDelimitedString()
    {
        String tildeSeparatedNames = null;

        Assertions.assertEquals(
                "Shedtastic~Splendid Crocks~Annoying Pets~Gnomes 'R' Us~Furniture Hamlet~SFD~Doxins",
                tildeSeparatedNames,
                "tilde separated names");
    }

    /**
     * Deliver all orders going to customers from London.
     * <p/>
     * Hint: Use {@link RichIterable#each(Procedure)}.
     *
     * @see Order#deliver()
     */
    @Test
    @Tag("KATA")
    public void deliverOrdersToLondon()
    {
        Verify.assertAllSatisfy(this.company.getCustomerNamed("Fred").getOrders(), Order::isDelivered);
        Verify.assertNoneSatisfy(this.company.getCustomerNamed("Mary").getOrders(), Order::isDelivered);
        Verify.assertAllSatisfy(this.company.getCustomerNamed("Bill").getOrders(), Order::isDelivered);
    }
}
