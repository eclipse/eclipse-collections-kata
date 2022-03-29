/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.primitive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.GraphLayout;

public class BoxingTest
{
    /**
     * {@link Boolean#valueOf(boolean)} <br>
     * {@link GraphLayout#parseInstance(Object...)} <br>
     * {@link GraphLayout#totalSize()} <br>
     */
    @Test
    public void booleans()
    {
        boolean booleanValue = true;                            // primitive - ~1 bit - not precisely defined
        Boolean booleanNew = new Boolean(booleanValue);         // Deprecated in Java 9
        Boolean booleanAuto = booleanValue;                     // Autoboxing - 16 bytes
        Boolean booleanValueOf = Boolean.valueOf(booleanValue); // Boxing w/ caching

        Assertions.assertNotSame(booleanNew, booleanAuto);
        Assertions.assertSame(booleanAuto, booleanValueOf);     // Autoboxing uses valueof

        Assertions.assertEquals(0L, GraphLayout.parseInstance(booleanAuto).totalSize());
    }

    /**
     * {@link Byte#valueOf(byte)} <br>
     * {@link GraphLayout#parseInstance(Object...)} <br>
     * {@link GraphLayout#totalSize()} <br>
     */
    @Test
    public void bytes()
    {
        byte byteValue = (byte) 0;                              // primitive - 1 byte
        Byte byteNew = new Byte(byteValue);                     // Deprecated in Java 9
        Byte byteAuto = byteValue;                              // Autoboxing - 16 bytes
        Byte byteValueOf = Byte.valueOf(byteValue);             // Boxing w/ caching

        Assertions.assertNotSame(byteNew, byteAuto);
        Assertions.assertSame(byteAuto, byteValueOf);           // Autoboxing uses valueof
        Assertions.assertEquals(0L, GraphLayout.parseInstance(byteAuto).totalSize());
    }

    /**
     * {@link Character#valueOf(char)} <br>
     * {@link GraphLayout#parseInstance(Object...)} <br>
     * {@link GraphLayout#totalSize()} <br>
     */
    @Test
    public void chars()
    {
        char charValue = 'a';                                   // primitive - 2 bytes
        Character characterNew = new Character(charValue);      // Deprecated in Java 9
        Character characterAuto = charValue;                    // Autoboxing - 16 bytes
        Character characterValueOf = Character.valueOf(charValue);  // Boxing w/ caching

        Assertions.assertNotSame(characterNew, characterAuto);
        Assertions.assertSame(characterAuto, characterValueOf); // Autoboxing uses valueof
        Assertions.assertEquals(0L, GraphLayout.parseInstance(characterAuto).totalSize());
    }

    /**
     * {@link Short#valueOf(short)} <br>
     * {@link GraphLayout#parseInstance(Object...)} <br>
     * {@link GraphLayout#totalSize()} <br>
     */
    @Test
    public void shorts()
    {
        short shortValue = (short) 0;                           // primitive - 2 bytes
        Short shortNew = new Short(shortValue);                 // Deprecated in Java 9
        Short shortAuto = shortValue;                           // Autoboxing - 16 bytes
        Short shortValueOf = Short.valueOf(shortValue);         // Boxing w/ caching

        Assertions.assertNotSame(shortNew, shortAuto);
        Assertions.assertSame(shortAuto, shortValueOf);         // Autoboxing uses valueof
        Assertions.assertEquals(0L, GraphLayout.parseInstance(shortAuto).totalSize());
    }

    /**
     * {@link Integer#valueOf(int)} <br>
     * {@link GraphLayout#parseInstance(Object...)} <br>
     * {@link GraphLayout#totalSize()} <br>
     */
    @Test
    public void ints()
    {
        int intValue = 0;                                       // primitive - 4 bytes
        Integer integerNew = new Integer(intValue);             // Deprecated in Java 9
        Integer integerAuto = intValue;                         // Autoboxing - 16 bytes
        Integer integerValueOf = Integer.valueOf(intValue);     // Boxing w/ caching

        Assertions.assertNotSame(integerNew, integerAuto);
        Assertions.assertSame(integerAuto, integerValueOf);     // Autoboxing uses valueof
        Assertions.assertEquals(0L, GraphLayout.parseInstance(integerAuto).totalSize());
    }

    /**
     * {@link Float#valueOf(float)} <br>
     * {@link GraphLayout#parseInstance(Object...)} <br>
     * {@link GraphLayout#totalSize()} <br>
     */
    @Test
    public void floats()
    {
        float floatValue = 0.0f;                                // primitive - 4 bytes
        Float floatNew = new Float(floatValue);                 // Deprecated in Java 9
        Float floatAuto = floatValue;                           // Autoboxing - 16 bytes
        Float floatValueOf = Float.valueOf(floatValue);         // Boxing NO caching

        Assertions.assertNotSame(floatNew, floatAuto);
        Assertions.assertNotSame(floatAuto, floatValueOf);      // No cache for floats
        Assertions.assertEquals(0L, GraphLayout.parseInstance(floatAuto).totalSize());
    }

    /**
     * {@link Long#valueOf(long)} <br>
     * {@link GraphLayout#parseInstance(Object...)} <br>
     * {@link GraphLayout#totalSize()} <br>
     */
    @Test
    public void longs()
    {
        long longValue = 0L;                                    // primitive - 8 bytes
        Long longNew = new Long(longValue);                     // Deprecated in Java 9
        Long longAuto = longValue;                              // Autoboxing - 24 bytes
        Long longValueOf = Long.valueOf(longValue);             // Boxing w/ caching

        Assertions.assertNotSame(longNew, longAuto);
        Assertions.assertSame(longAuto, longValueOf);           // Autoboxing uses valueof
        Assertions.assertEquals(0L, GraphLayout.parseInstance(longAuto).totalSize());
    }

    /**
     * {@link Double#valueOf(double)} <br>
     * {@link GraphLayout#parseInstance(Object...)} <br>
     * {@link GraphLayout#totalSize()} <br>
     */
    @Test
    public void doubles()
    {
        double doubleValue = 0.0;                               // primitive - 8 bytes
        Double doubleNew = new Double(0.0);                     // Deprecated in Java 9
        Double doubleAuto = doubleValue;                        // Autoboxing - 24 bytes
        Double doubleValueOf = Double.valueOf(doubleValue);     // Boxing NO caching

        Assertions.assertNotSame(doubleNew, doubleAuto);
        Assertions.assertNotSame(doubleAuto, doubleValueOf);    // No cache for doubles
        Assertions.assertEquals(0L, GraphLayout.parseInstance(doubleAuto).totalSize());
    }

    /**
     * {@link GraphLayout#parseInstance(Object...)} <br>
     * {@link GraphLayout#totalSize()} <br>
     * @see MinMaxPrimitivesPlain
     * @see MinMaxPrimitivesBoxed
     */
    @Test
    public void primitivesAndBoxes()
    {
        Assertions.assertEquals(0L, GraphLayout.parseInstance(new MinMaxPrimitivesPlain()).totalSize());
        Assertions.assertEquals(0L, GraphLayout.parseInstance(new MinMaxPrimitivesBoxed()).totalSize());
    }
}
