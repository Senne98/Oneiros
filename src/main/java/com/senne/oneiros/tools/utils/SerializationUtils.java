package com.senne.oneiros.tools.utils;

import com.senne.oneiros.atributes.attributeTypes.Attribute;
import com.senne.oneiros.atributes.attributeTypes.VariableAttribute;
import com.senne.oneiros.item.Item;
import com.senne.oneiros.tools.ByteWriter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.nio.charset.StandardCharsets;

public class SerializationUtils {

    public static <T> T deserialize(byte[] bytes, Class<T> clazz) {
        switch (clazz.getSimpleName()) {
            case "Material":
                return clazz.cast(deserializeMaterial(bytes));
            case "Integer":
                return clazz.cast(deserializeInt(bytes));
            case "Double":
                return clazz.cast(deserializeDouble(bytes));
            case "Component":
                return clazz.cast(deserializeComponent(bytes));
            case "NamespacedKey":
                return clazz.cast(deserializeNamespacedKey(bytes));
            default:
                throw new IllegalArgumentException("Unsupported type: " + clazz.getName());
        }
    }

    public static byte[] serialize(Material material) {
        return material.toString().getBytes(StandardCharsets.UTF_8);
    }

    private static Material deserializeMaterial(byte[] bytes) {
        return Material.getMaterial(new String(bytes, StandardCharsets.UTF_8));
    }

    public static byte[] serialize(int num) {
        byte[] numBytes = new byte[4];
        numBytes[0] = (byte) (num >> 24);
        numBytes[1] = (byte) (num >> 16);
        numBytes[2] = (byte) (num >> 8);
        numBytes[3] = (byte) num;
        return numBytes;
    }

    private static int deserializeInt(byte[] bytes) {
        return (bytes[0] << 24) + (bytes[1] << 16) + (bytes[2] << 8) + bytes[3];
    }

    public static byte[] serialize(double num) {
        long longBits = Double.doubleToRawLongBits(num);
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((longBits >> (8 * i)) & 0xFF);
        }
        return bytes;
    }

    private static double deserializeDouble(byte[] bytes) {
        long longBits = 0;
        for (int i = 0; i < 8; i++) {
            longBits |= ((long) bytes[i] & 0xFF) << (8 * i);
        }
        return Double.longBitsToDouble(longBits);
    }

    public static byte[] serialize(Component component) {
        GsonComponentSerializer serializer = GsonComponentSerializer.gson();
        return serializer.serialize(component).getBytes(StandardCharsets.UTF_8);
    }

    private static Component deserializeComponent(byte[] bytes) {
        GsonComponentSerializer serializer = GsonComponentSerializer.gson();
        return serializer.deserialize(new String(bytes, StandardCharsets.UTF_8));
    }

    //first bytes: length of attribute
    //second byte: attribute type: 0 for VariableAttribute, 1 for Attribute
    //third bytes: length of key
    //fourth bytes: key
    //ONLY FOR VARIABLE ATTRIBUTE
    //fifth bytes: length of attribute
    //sixth bytes: attribute
    public static byte[] serialize(Attribute attribute) {
        ByteWriter attributeWriter = new ByteWriter();

        if (!(attribute instanceof VariableAttribute)) {
            attributeWriter.add((byte) 1);

            byte[] attributeBytes = serialize(attribute.getKey());
            attributeWriter.addAll(serialize(attributeBytes.length));
            attributeWriter.addAll(attributeBytes);

            byte[] attributeProperties = ((VariableAttribute) attribute).exportVariables();

            attributeWriter.addAll(serialize(attributeProperties.length));
            attributeWriter.addAll(attributeProperties);

            return attributeWriter.toByteArray();

        }

        attributeWriter.add((byte) 0);

        byte[] attributeBytes = serialize(attribute.getKey());
        attributeWriter.addAll(serialize(attributeBytes.length));
        attributeWriter.addAll(attributeBytes);

        return attributeWriter.toByteArray();
    }

    public static byte[] serialize(NamespacedKey key) {
        return key.toString().getBytes(StandardCharsets.UTF_8);
    }

    public static NamespacedKey deserializeNamespacedKey(byte[] bytes) {
        return NamespacedKey.fromString(new String(bytes, StandardCharsets.UTF_8));
    }

    public static byte[] serialize(Item item) {
        ByteWriter writer = new ByteWriter();

        //cmd
        writer.addAll(serialize(item.getCmd()));

        //material
        byte[] materialBytes = serialize(item.getMaterial());
        writer.addAll(serialize(materialBytes.length));
        writer.addAll(materialBytes);

        //displayName
        byte[] displayNameBytes = serialize(item.getDisplayName());
        writer.addAll(serialize(displayNameBytes.length));
        writer.addAll(displayNameBytes);

        //lore
        ByteWriter loreWriter = new ByteWriter();
        for (Component lore : item.getLore()) {
            byte[] loreBytes = serialize(lore);
            loreWriter.addAll(serialize(loreBytes.length));
            loreWriter.addAll(loreBytes);
        }
        byte[] loreBytes = loreWriter.toByteArray();
        writer.addAll(serialize(loreBytes.length));
        writer.addAll(loreBytes);

        //attributes
        ByteWriter attributeListWriter = new ByteWriter();

        byte[] attributeBytes;

        for (Attribute attribute : item.getAttributes()) {
            attributeBytes = serialize(attribute);
            attributeListWriter.addAll(serialize(attributeBytes.length));
            attributeListWriter.addAll(attributeBytes);
        }

        byte[] attributeListBytes = attributeListWriter.toByteArray();
        writer.addAll(serialize(attributeListBytes.length));
        writer.addAll(attributeListBytes);

        //actionHandlers: TO DO

        //namespacedKey
        byte[] namespaceBytes = serialize(item.getNamespacedKey());
        writer.addAll(serialize(namespaceBytes.length));
        writer.addAll(namespaceBytes);

        return writer.toByteArray();
    }
}
