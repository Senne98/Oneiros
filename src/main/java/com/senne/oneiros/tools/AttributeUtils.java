package com.senne.oneiros.tools;

import com.senne.oneiros.atributes.Attribute;

import java.util.ArrayList;
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

    public static void removeEveryInstance(List<Attribute> attributes, Attribute attribute) {

        List<Attribute> toRemove = new ArrayList<>();

        for (Attribute a : attributes) {
            if (a.getClass().equals(attribute.getClass())) {
                toRemove.add(a);
            }
        }

        attributes.removeAll(toRemove);
    }
}
