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

import org.junit.Before;

public abstract class CompanyDomainForKata
{
    protected final Company company = new Company("Bloggs Shed Supplies");

    @Before
    public void setUp()
    {
        Order.resetNextOrderNumber();
        this.setUpCustomersAndOrders();
        this.setUpSuppliers();
    }

    private void setUpSuppliers()
    {
        this.company.addSupplier(new Supplier("Shedtastic", new String[]{"shed", "big shed", "huge shed"}));
        this.company.addSupplier(new Supplier("Splendid Crocks", new String[]{"cup", "saucer", "bowl"}));
        this.company.addSupplier(new Supplier("Annoying Pets", new String[]{"dog", "cat", "goldfish"}));
        this.company.addSupplier(new Supplier("Gnomes 'R' Us", new String[]{"gnome"}));
        this.company.addSupplier(new Supplier("Furniture Hamlet", new String[]{"table", "sofa", "chair"}));
        this.company.addSupplier(new Supplier("SFD", new String[]{"sofa", "chair"}));
        this.company.addSupplier(new Supplier("Doxins", new String[]{"kettle", "plasma screen", "sandwich toaster"}));
    }

    private void setUpCustomersAndOrders()
    {
        Order fredOrder = new Order();
        fredOrder.addLineItem(new LineItem("shed", 50.0));

        /**
         * TODO 7: Refactor Order and its API so this repetition is not necessary.
         */
        // TODO 7: Add 3 cups at 1.5 each to the order
        fredOrder.addLineItem(new LineItem("cup", 1.5));
        fredOrder.addLineItem(new LineItem("cup", 1.5));
        fredOrder.addLineItem(new LineItem("cup", 1.5));

        // TODO 7: Add 3 saucers at 1.0 each to the order
        fredOrder.addLineItem(new LineItem("saucer", 1.0));
        fredOrder.addLineItem(new LineItem("saucer", 1.0));
        fredOrder.addLineItem(new LineItem("saucer", 1.0));

        fredOrder.addLineItem(new LineItem("chair", 12.50));
        fredOrder.addLineItem(new LineItem("table", 1.0));

        Customer fred = new Customer("Fred", "London");
        fred.addOrder(fredOrder);
        this.company.addCustomer(fred);

        Order maryOrder = new Order();
        maryOrder.addLineItem(new LineItem("cat", 150.0));
        maryOrder.addLineItem(new LineItem("big shed", 500.0));

        // TODO 7: Add 4 cups at 1.50 each to the order
        maryOrder.addLineItem(new LineItem("cup", 1.5));
        maryOrder.addLineItem(new LineItem("cup", 1.5));
        maryOrder.addLineItem(new LineItem("cup", 1.5));
        maryOrder.addLineItem(new LineItem("cup", 1.5));

        // TODO 7: Add 4 saucers at 1.50 each to the order
        maryOrder.addLineItem(new LineItem("saucer", 1.5));
        maryOrder.addLineItem(new LineItem("saucer", 1.5));
        maryOrder.addLineItem(new LineItem("saucer", 1.5));
        maryOrder.addLineItem(new LineItem("saucer", 1.5));

        maryOrder.addLineItem(new LineItem("sofa", 120.0));
        maryOrder.addLineItem(new LineItem("dog", 75.0));

        Customer mary = new Customer("Mary", "Liphook");
        mary.addOrder(maryOrder);
        this.company.addCustomer(mary);

        Order billOrder1 = new Order();
        billOrder1.addLineItem(new LineItem("shed", 50.0));

        // TODO 7: Add 43 gnomes at 7.50 each to the order
        for (int i = 0; i < 43; i++)
        {
            billOrder1.addLineItem(new LineItem("gnome", 7.50));
        }

        Order billOrder2 = new Order();
        billOrder2.addLineItem(new LineItem("bowl", 1.25));
        billOrder2.addLineItem(new LineItem("goldfish", 0.50));

        Order billOrder3 = new Order();
        billOrder3.addLineItem(new LineItem("table", 1.0));

        Customer bill = new Customer("Bill", "London");
        bill.addOrder(billOrder1);
        bill.addOrder(billOrder2);
        bill.addOrder(billOrder3);

        this.company.addCustomer(bill);
    }
}
