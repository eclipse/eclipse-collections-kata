/*
 * Copyright (c) 2022 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.petkata;

import org.eclipse.collections.impl.utility.ArrayIterate;

public enum PetType
{
    CAT("🐱"),
    DOG("🐶"),
    HAMSTER("🐹"),
    TURTLE("🐢"),
    BIRD("🐦"),
    SNAKE("🐍");

    private final String emoji;

    PetType(String emoji)
    {
        this.emoji = emoji;
    }

    @Override
    public String toString()
    {
        return this.emoji;
    }

    public static PetType fromEmoji(String searchEmoji)
    {
        // Find the correct PetType based on the String emoji
        return null;
    }
}
