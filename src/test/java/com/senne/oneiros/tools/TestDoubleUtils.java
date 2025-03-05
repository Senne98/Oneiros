package com.senne.oneiros.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDoubleUtils {

    @Test
    public void testIsDouble() {
        Assertions.assertTrue(DoubleUtils.isDouble("1.2"));
        Assertions.assertTrue(DoubleUtils.isDouble("1"));
        Assertions.assertFalse(DoubleUtils.isDouble("12.0a"));
        Assertions.assertFalse(DoubleUtils.isDouble("1°"));
        Assertions.assertFalse(DoubleUtils.isDouble("12%"));
    }

    @Test
    public void testSerialization() {
        double value = 1.2;
        byte[] bytes = DoubleUtils.toByteArray(value);
        double deserializedValue = DoubleUtils.fromByteArray(bytes);
        Assertions.assertEquals(value, deserializedValue);
        value = 0.0;
        bytes = DoubleUtils.toByteArray(value);
        deserializedValue = DoubleUtils.fromByteArray(bytes);
        Assertions.assertEquals(value, deserializedValue);
    }

}
