package com.senne.oneiros.atributes;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.List;

public class AttributeRegister {

    private static List<Attribute> attributes = new ArrayList<>();

    public static void registerAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public static List<Attribute> getAttributes() {
        List<Attribute> attributeList = new ArrayList<>();
        for (Attribute attribute : attributes) {
            attributeList.add(SerializationUtils.clone(attribute));
        }
        return attributeList;
    }

    public static Attribute getAttribute(NamespacedKey key) {
        for (Attribute attribute : attributes) {
            if (attribute.getKey().equals(key)) {
                return SerializationUtils.clone(attribute);
            }
        }
        return null;
    }
}
