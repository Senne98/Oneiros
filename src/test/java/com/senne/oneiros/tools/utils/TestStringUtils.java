package com.senne.oneiros.tools.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestStringUtils {

    @Test
    public void testContainsOnlyLetters() {
        Assertions.assertTrue(StringUtils.containsOnlyLetters("abc"));
        Assertions.assertTrue(StringUtils.containsOnlyLetters("HJLHhdsjlhgfsq"));
        Assertions.assertFalse(StringUtils.containsOnlyLetters("123"));
        Assertions.assertFalse(StringUtils.containsOnlyLetters("abc&*-"));
    }
}
