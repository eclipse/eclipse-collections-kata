/*
 * Copyright (c) 2023 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.haikukata;

public class HaikuCollection
{
    private final String text = """
            Breaking Through                  Pavement                  Wakin' with Bacon        Homeward Found
            ----------------                  --------                  -----------------        --------------
            The wall disappears               Beautiful pavement!       Wakin' with Bacon        House is where I am
            As soon as you break through the  Imperfect path before me  On a Saturday morning    Home is where I want to be
            Intimidation                      Thank you for the ride    Life’s little pleasures  Both may be the same

            Winter Slip and Slide              Simple Nothings                With Deepest Regrets
            ---------------------              ---------------                --------------------
            Run up the ladder                  A simple flower                With deepest regrets
            Swoosh down the slide in the snow  Petals shine vibrant and pure  That which you have yet to write
            Winter slip and slide              Stares into the void           At death, won't be wrote

            Caffeinated Coding Rituals  Finding Solace               Curious Cat                Eleven
            --------------------------  --------------               -----------                ------
            I arrange my desk,          Floating marshmallows        I see something move       This is how many
            refactor some ugly code,    Cocoa brewed hot underneath  What it is, I am not sure  Haiku I write before I
            and drink my coffee.        Comfort in a cup             Should I pounce or not?    Write a new tech blog.
            """;

    public String getText()
    {
        return this.text;
    }
}
