package com.senne.oneiros.tools.utils;

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
}
