package com.senne.oneiros.tools.utils;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.Attribute;
import com.senne.oneiros.atributes.attributeTypes.AttributeRegister;
import com.senne.oneiros.atributes.attributeTypes.VariableAttribute;
import com.senne.oneiros.item.Item;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
            case "Attribute":
                return clazz.cast(deserializeAttribute(bytes));
            case "Item":
                return clazz.cast(deserializeItem(bytes));
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

    //second byte: attribute type: 1 for VariableAttribute, 0 for Attribute
    //third bytes: length of key
    //fourth bytes: key
    //ONLY FOR VARIABLE ATTRIBUTE
    //fifth bytes: length of attribute
    //sixth bytes: attribute
    public static byte[] serialize(Attribute attribute) {
        byte[] result = new byte[1];

        if (attribute instanceof VariableAttribute) {
            result[0] = 1;

            byte[] attributeBytes = serialize(attribute.getKey());
            result = ByteUtils.merge(result, serialize(attributeBytes.length));
            result = ByteUtils.merge(result, attributeBytes);

            byte[] attributeProperties = ((VariableAttribute) attribute).exportVariables();

            result = ByteUtils.merge(result, serialize(attributeProperties.length));
            result = ByteUtils.merge(result, attributeProperties);

            return result;

        }

        result[0] = 0;

        byte[] attributeBytes = serialize(attribute.getKey());
        result = ByteUtils.merge(result, serialize(attributeBytes.length));
        result = ByteUtils.merge(result, attributeBytes);

        return result;
    }

    private static Attribute deserializeAttribute(byte[] bytes) {
        byte[] processing;
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        //type
        byte type = buffer.get();

        //namespacedKey
        processing = new byte[4];
        buffer.get(processing);
        int length = deserializeInt(processing);
        processing = new byte[length];
        buffer.get(processing);
        NamespacedKey key = deserializeNamespacedKey(processing);

        if (!AttributeRegister.contains(key)) {
            Oneiros.getPlugin().getLogger().warning("Unknown attribute key: " + key);
            return null;
        }

        Attribute attribute = AttributeRegister.getAttribute(key);

        if (type == 0) {
            return attribute;
        }

        //variable attribute
        processing = new byte[4];
        buffer.get(processing);
        length = deserializeInt(processing);
        processing = new byte[length];
        VariableAttribute varAttribute = (VariableAttribute) attribute;
        buffer.get(processing);
        varAttribute.importVariables(processing);

        return varAttribute;
    }

    public static byte[] serialize(NamespacedKey key) {
        return key.toString().getBytes(StandardCharsets.UTF_8);
    }

    public static NamespacedKey deserializeNamespacedKey(byte[] bytes) {
        return NamespacedKey.fromString(new String(bytes, StandardCharsets.UTF_8));
    }

    public static byte[] serialize(Item item) {
        byte[] result = new byte[0];

        //cmd
        result = ByteUtils.merge(result, serialize(item.getCmd()));

        //material
        byte[] materialBytes = serialize(item.getMaterial());
        result = ByteUtils.merge(result, serialize(materialBytes.length));
        result = ByteUtils.merge(result, materialBytes);

        //displayName
        byte[] displayNameBytes = serialize(item.getDisplayName());
        result = ByteUtils.merge(result, serialize(displayNameBytes.length));
        result = ByteUtils.merge(result, displayNameBytes);

        //lore
        byte[] loreBytes = new byte[0];
        for (Component line : item.getLore()) {
            byte[] lineBytes = serialize(line);
            loreBytes = ByteUtils.merge(loreBytes, serialize(lineBytes.length));
            loreBytes = ByteUtils.merge(loreBytes, lineBytes);
        }
        result = ByteUtils.merge(result, serialize(loreBytes.length));
        result = ByteUtils.merge(result, loreBytes);

        //attributes
        byte[] attributeListBytes = new byte[0];
        for (Attribute attribute : item.getAttributes()) {
            byte[] attributeBytes = serialize(attribute);
            attributeListBytes = ByteUtils.merge(attributeListBytes, serialize(attributeBytes.length));
            attributeListBytes = ByteUtils.merge(attributeListBytes, attributeBytes);
        }
        result = ByteUtils.merge(result, serialize(attributeListBytes.length));
        result = ByteUtils.merge(result, attributeListBytes);

        //actionHandlers: TO DO

        //namespacedKey
        byte[] namespaceBytes = serialize(item.getNamespacedKey());
        result = ByteUtils.merge(result, serialize(namespaceBytes.length));
        result = ByteUtils.merge(result, namespaceBytes);

        return result;
    }

    public static Item deserializeItem(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        byte[] processing;

        //cmd
        processing = new byte[4];
        buffer.get(processing);
        int cmd = deserializeInt(processing);

        //material
        processing = new byte[4];
        buffer.get(processing);
        int amount = deserializeInt(processing);
        processing = new byte[amount];
        buffer.get(processing);
        Item item = new Item(deserializeMaterial(processing));

        item.setCmd(cmd);

        //displayName
        processing = new byte[4];
        buffer.get(processing);
        amount = deserializeInt(processing);
        processing = new byte[amount];
        buffer.get(processing);
        item.setDisplayName(deserializeComponent(processing));

        //lore
        processing = new byte[4];
        buffer.get(processing);
        amount = deserializeInt(processing);
        processing = new byte[amount];
        buffer.get(processing);
        ByteBuffer loreBuffer = ByteBuffer.wrap(processing);

        List<Component> lore = new ArrayList<>();
        while (loreBuffer.remaining() > 0) {
            processing = new byte[4];
            loreBuffer.get(processing);
            amount = deserializeInt(processing);
            processing = new byte[amount];
            loreBuffer.get(processing);
            lore.add(deserializeComponent(processing));
        }
        item.setLore(lore);

        //attributes
        processing = new byte[4];
        buffer.get(processing);
        amount = deserializeInt(processing);
        processing = new byte[amount];
        buffer.get(processing);
        ByteBuffer attributeBuffer = ByteBuffer.wrap(processing);

        while (attributeBuffer.remaining() > 0) {
            processing = new byte[4];
            attributeBuffer.get(processing);
            amount = deserializeInt(processing);
            processing = new byte[amount];
            attributeBuffer.get(processing);
            item.addAttribute(deserializeAttribute(processing));
        }

        //actionHandlers: TO DO

        //namespacedKey
        processing = new byte[4];
        buffer.get(processing);
        amount = deserializeInt(processing);
        processing = new byte[amount];
        buffer.get(processing);
        item.setNamespacedKey(deserializeNamespacedKey(processing));

        return item;
    }
}
