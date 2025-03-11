package com.senne.oneiros.tools.dataTypes;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.nio.ByteBuffer;

public class NamespacedKeyDataType implements PersistentDataType<byte[], NamespacedKey> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<NamespacedKey> getComplexType() {
        return NamespacedKey.class;
    }

    @Override
    public byte[] toPrimitive(NamespacedKey complex, PersistentDataAdapterContext context) {
        ByteBuffer bb = ByteBuffer.allocate(complex.getNamespace().getBytes().length + complex.getKey().getBytes().length + 2);
        bb.put((byte) complex.getNamespace().getBytes().length);
        bb.put(complex.getNamespace().getBytes());
        bb.put((byte) complex.getKey().getBytes().length);
        bb.put(complex.getKey().getBytes());
        return bb.array();
    }

    @Override
    public NamespacedKey fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        ByteBuffer bb = ByteBuffer.wrap(primitive);
        byte length = bb.get();
        byte[] namespace = new byte[length];
        for (int i = 0; i < length; i++) {
            namespace[i] = bb.get();
        }
        length = bb.get();
        byte[] key = new byte[length];
        for (int i = 0; i < length; i++) {
            key[i] = bb.get();
        }
        return new NamespacedKey(new String(namespace), new String(key));
    }
}
