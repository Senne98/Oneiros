package com.senne.oneiros.tools;

import com.senne.oneiros.atributes.Attribute;

import java.util.List;

public class AttributeUtils {

    public static boolean containsInstance(List<Attribute> attributes, Attribute attribute) {
        for (Attribute a : attributes) {
            if (a.getClass().equals(attribute.getClass())) {
                return true;
            }
        }
        return false;
    }

    public static void removeInstance(List<Attribute> attributes, Attribute attribute) {
        for (Attribute a : attributes) {
            if (a.getClass().equals(attribute.getClass())) {
                attributes.remove(a);
                return;
            }
        }
    }
}
