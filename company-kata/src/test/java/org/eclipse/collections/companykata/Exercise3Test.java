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

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Exercise3Test extends CompanyDomainForKata
{
    /**
     * Improve {@link Company#getOrders()} without breaking this test.
     */
    @Test
    public void improveGetOrders()
    {
        // Delete this line - it's a reminder
        Assert.fail("Improve getOrders() without breaking this test");
        Verify.assertSize(5, this.company.getOrders());
    }

    /**
     * Get all items that have been ordered by anybody.
     */
    @Test
    public void findItemNames()
    {
        MutableList<LineItem> allOrderedLineItems = null;
        MutableSet<String> actualItemNames = null;

        Verify.assertInstanceOf(MutableSet.class, actualItemNames);
        Verify.assertInstanceOf(String.class, actualItemNames.getFirst());

        MutableSet<String> expectedItemNames = UnifiedSet.newSetWith(
                "shed", "big shed", "bowl", "cat", "cup", "chair", "dog",
                "goldfish", "gnome", "saucer", "sofa", "table");
        Assert.assertEquals(expectedItemNames, actualItemNames);
    }

    @Test
    public void findCustomerNames()
    {
        MutableList<String> names = null;

        MutableList<String> expectedNames = FastList.newListWith("Fred", "Mary", "Bill");
        Assert.assertEquals(expectedNames, names);
    }
}
