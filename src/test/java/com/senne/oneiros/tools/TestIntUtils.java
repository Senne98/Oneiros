package com.senne.oneiros.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestIntUtils {

    @Test
    public void testIsInt() {
        Assertions.assertTrue(IntUtils.isInt("1"));
        Assertions.assertTrue(IntUtils.isInt("-12"));
        Assertions.assertTrue(IntUtils.isInt("0"));
        Assertions.assertFalse(IntUtils.isInt("12.0"));
        Assertions.assertFalse(IntUtils.isInt("-2.0"));
        Assertions.assertFalse(IntUtils.isInt("12.0a"));
    }
}
