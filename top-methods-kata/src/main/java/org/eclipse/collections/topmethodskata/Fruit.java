/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.topmethodskata;

import java.awt.Color;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

public enum Fruit
{
    APPLE("🍎", Color.RED),
    PEACH("🍑", Color.ORANGE),
    BANANA("🍌", Color.YELLOW),
    CHERRY("🍒", Color.RED),
    ORANGE("🍊", Color.ORANGE);

    public static ImmutableList<Fruit> ALL = Lists.immutable.with(Fruit.values());
    private String emoji;
    private Color color;

    Fruit(String emoji, Color color)
    {
        this.emoji = emoji;
        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }

    @Override
    public String toString()
    {
        return this.emoji;
    }

    public static ImmutableList<String> toLowerCaseList()
    {
        return ALL.collect(Fruit::toLowerCase);
    }

    public static ImmutableList<String> toEmojiList()
    {
        return ALL.collect(Fruit::toString);
    }

    public String toUpperCase()
    {
        return this.name().toUpperCase();
    }

    public String toLowerCase()
    {
        return this.name().toLowerCase();
    }
}
