package com.senne.oneiros.tools.dataTypes;

import org.bukkit.NamespacedKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestNamespacedKeyDataType {

    @Test
    public void testDataType() {
        NamespacedKey key = new NamespacedKey("minecraft", "test");
        NamespacedKeyDataType dataType = new NamespacedKeyDataType();
        byte[] primitive = dataType.toPrimitive(key, null);
        Assertions.assertEquals(key, dataType.fromPrimitive(primitive, null));
    }
}
