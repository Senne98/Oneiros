package com.senne.oneiros.atributes.attributeTypes;

import com.senne.oneiros.Oneiros;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.List;

public class AttributeRegister {

    private static List<Attribute> attributes = new ArrayList<>();

    public static void registerAttribute(Attribute attribute) {
        attributes.add(attribute.copy());
    }

    public static List<Attribute> getAttributes() {
        List<Attribute> attributeList = new ArrayList<>();
        for (int i = 0; i < attributes.size(); i++) {
            attributeList.add(attributes.get(i).copy());
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

    public static boolean contains(NamespacedKey key) {
        return attributes.stream().anyMatch(attribute -> attribute.getKey().equals(key));
    }
}
