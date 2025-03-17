package com.senne.oneiros.tools.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestByteUtils {

    @Test
    public void testMerge() {
        byte[] first = new byte[] {1, 2, 3};
        byte[] second = new byte[] {4, 5, 6};

        Byte[] a = new Byte[] {1, 2, 5};
        Byte[] b = new Byte[] {4, 5, 7};

        assertArrayEquals(new byte[] {1, 2, 3, 4, 5, 6}, ByteUtils.merge(first, second));
        assertArrayEquals(new Byte[] {1, 2, 5, 4, 5, 7}, ByteUtils.merge(a, b));
    }

}
