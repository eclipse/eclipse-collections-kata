/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.topmethodskata;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

public enum Fruit
{
    APPLE, APRICOT, BANANA, BLUEBERRY, CLEMENTINE;

    public static ImmutableList<Fruit> ALL = Lists.immutable.with(Fruit.values());

    public static ImmutableList<String> toLowerCaseList()
    {
        return ALL.collect(Fruit::toLowerCase);
    }

    public String toLowerCase()
    {
        return this.toString().toLowerCase();
    }
}
