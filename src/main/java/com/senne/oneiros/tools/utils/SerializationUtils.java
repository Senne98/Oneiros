package com.senne.oneiros.tools.utils;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.Attribute;
import com.senne.oneiros.atributes.attributeTypes.AttributeRegister;
import com.senne.oneiros.atributes.attributeTypes.VariableAttribute;
import com.senne.oneiros.item.Item;
import com.senne.oneiros.item.Pack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.senne.oneiros.tools.utils.ByteUtils.merge;

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
            case "EquipmentSlot":
                return clazz.cast(deserializeSlot(bytes));
            case "String":
                return clazz.cast(deserializeString(bytes));
            case "Pack":
                return clazz.cast(deserializePack(bytes));
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

    public static byte[] serialize(int integer) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(integer);
        return bb.array();
    }

    private static int deserializeInt(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        return bb.getInt();
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
            result = merge(result, serialize(attributeBytes.length));
            result = merge(result, attributeBytes);

            byte[] attributeProperties = ((VariableAttribute) attribute).exportVariables();

            result = merge(result, serialize(attributeProperties.length));
            result = merge(result, attributeProperties);

            return result;

        }

        result[0] = 0;

        byte[] attributeBytes = serialize(attribute.getKey());
        result = merge(result, serialize(attributeBytes.length));
        result = merge(result, attributeBytes);

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

    private static NamespacedKey deserializeNamespacedKey(byte[] bytes) {
        return NamespacedKey.fromString(new String(bytes, StandardCharsets.UTF_8));
    }

    public static byte[] serialize(Item item) {

        //version
        byte[] result = serialize(0);

        //cmd
        result = merge(result, serialize(item.getCmd()));

        //material
        byte[] materialBytes = serialize(item.getMaterial());
        result = merge(result, serialize(materialBytes.length));
        result = merge(result, materialBytes);

        //displayName
        byte[] displayNameBytes = serialize(item.getDisplayName());
        result = merge(result, serialize(displayNameBytes.length));
        result = merge(result, displayNameBytes);

        //lore
        byte[] loreBytes = new byte[0];
        for (Component line : item.getLore()) {
            byte[] lineBytes = serialize(line);
            loreBytes = merge(loreBytes, serialize(lineBytes.length));
            loreBytes = merge(loreBytes, lineBytes);
        }
        result = merge(result, serialize(loreBytes.length));
        result = merge(result, loreBytes);

        //attributes
        byte[] attributeListBytes = new byte[0];
        for (Attribute attribute : item.getAttributes()) {
            byte[] attributeBytes = serialize(attribute);
            attributeListBytes = merge(attributeListBytes, serialize(attributeBytes.length));
            attributeListBytes = merge(attributeListBytes, attributeBytes);
        }
        result = merge(result, serialize(attributeListBytes.length));
        result = merge(result, attributeListBytes);

        //actionHandlers: TO DO

        //namespacedKey
        byte[] namespaceBytes = serialize(item.getNamespacedKey());
        result = merge(result, serialize(namespaceBytes.length));
        result = merge(result, namespaceBytes);

        return result;
    }

    private static Item deserializeItem(byte[] bytes) throws IllegalArgumentException {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        byte[] processing;

        //version
        processing = new byte[4];
        buffer.get(processing);
        int version = deserializeInt(processing);

        switch (version) {
            case 0:
                return deserializeItemV0(buffer);
            default:
                Oneiros.getPlugin().getLogger().warning("Unknown item version: " + version);
                throw new IllegalArgumentException("Unknown item version: " + version);
        }

    }

    private static Item deserializeItemV0(ByteBuffer buffer) {
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

    public static byte[] serialize(EquipmentSlot slot) {
        return new byte[]{(byte) slot.ordinal()};
    }

    private static EquipmentSlot deserializeSlot(byte[] bytes) {
        return EquipmentSlot.values()[bytes[0]];
    }

    public static byte[] serialize(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    private static String deserializeString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static byte[] serialize(Pack pack) {
        byte[] result;
        byte[] processing;

        //format version
        result = serialize(0);

        //cmd
        result = merge(result, serialize(pack.getCmd()));

        //name
        processing = serialize(pack.getName());
        result = merge(result, serialize(processing.length));
        result = merge(result, processing);

        //authors
        processing = new byte[0];
        for (int i = 0; i < pack.getAuthors().length; i++) {
            byte[] authorBytes = serialize(pack.getAuthors()[i]);
            processing = merge(processing, serialize(authorBytes.length));
            processing = merge(processing, authorBytes);
        }
        result = merge(result, serialize(processing.length));
        result = merge(result, processing);

        //icon
        processing = serialize(pack.getIcon());
        result = merge(result, serialize(processing.length));
        result = merge(result, processing);

        //items
        processing = new byte[0];
        for (Item item : pack.getItems()) {
            byte[] itemBytes = serialize(item);
            processing = merge(processing, serialize(itemBytes.length));
            processing = merge(processing, itemBytes);
        }
        result = merge(result, serialize(processing.length));
        result = merge(result, processing);

        return result;
    }

    private static Pack deserializePack(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        byte[] processing;

        //format version
        processing = new byte[4];
        buffer.get(processing);
        int version = deserializeInt(processing);

        switch (version) {
            case 0:
                return deserializePackV0(buffer);
            default:
                Oneiros.getPlugin().getLogger().warning("Unknown pack version: " + version);
                throw new IllegalArgumentException("Unknown pack version: " + version);
        }
    }

    private static Pack deserializePackV0(ByteBuffer buffer) {
        byte[] processing;
        int size;

        //cmd
        processing = new byte[4];
        buffer.get(processing);
        int cmd = deserializeInt(processing);

        //name
        processing = new byte[4];
        buffer.get(processing);
        size = deserializeInt(processing);
        processing = new byte[size];
        buffer.get(processing);
        Pack pack = new Pack(deserializeString(processing));

        //apply cmd
        pack.setCmd(cmd);

        //authors
        processing = new byte[4];
        buffer.get(processing);
        size = deserializeInt(processing);
        processing = new byte[size];
        buffer.get(processing);
        ByteBuffer authorBuffer = ByteBuffer.wrap(processing);
        List<String> authors = new ArrayList<>();
        while (authorBuffer.hasRemaining()) {
            processing = new byte[4];
            authorBuffer.get(processing);
            size = deserializeInt(processing);
            processing = new byte[size];
            authorBuffer.get(processing);
            authors.add(deserializeString(processing));
        }
        pack.setAuthors(authors.toArray(new String[0]));

        //icon
        processing = new byte[4];
        buffer.get(processing);
        size = deserializeInt(processing);
        processing = new byte[size];
        buffer.get(processing);
        pack.setIcon(deserializeMaterial(processing));

        //items
        processing = new byte[4];
        buffer.get(processing);
        size = deserializeInt(processing);
        processing = new byte[size];
        buffer.get(processing);
        ByteBuffer itemBuffer = ByteBuffer.wrap(processing);
        List<Item> items = new ArrayList<>();
        while (itemBuffer.hasRemaining()) {
            processing = new byte[4];
            itemBuffer.get(processing);
            size = deserializeInt(processing);
            processing = new byte[size];
            itemBuffer.get(processing);
            items.add(deserializeItem(processing));
        }
        pack.setItems(items);

        return pack;
    }
}
