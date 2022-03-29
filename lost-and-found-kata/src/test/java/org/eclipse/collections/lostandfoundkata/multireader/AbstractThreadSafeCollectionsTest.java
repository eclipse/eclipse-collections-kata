/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.multireader;

import java.util.function.Consumer;
import java.util.function.IntSupplier;

import org.eclipse.collections.impl.list.Interval;
import org.junit.jupiter.api.Assertions;

public abstract class AbstractThreadSafeCollectionsTest
{
    protected void assertThreadSafe(Consumer<Integer> consumer, IntSupplier supplier)
    {
        Interval.oneTo(1000).parallelStream().forEach(consumer);
        Assertions.assertEquals(1000, supplier.getAsInt());
    }
}
