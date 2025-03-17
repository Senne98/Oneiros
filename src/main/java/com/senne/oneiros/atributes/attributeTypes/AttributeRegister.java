package com.senne.oneiros.atributes.attributeTypes;

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
            attributeList.add(attribute.copy());
        }
        return attributeList;
    }

    public static Attribute getAttribute(NamespacedKey key) {
        for (Attribute attribute : attributes) {
            if (attribute.getKey().equals(key)) {
                return attribute.copy();
            }
        }
        return null;
    }
}
