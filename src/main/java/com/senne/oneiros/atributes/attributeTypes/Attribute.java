package com.senne.oneiros.atributes.attributeTypes;

import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public interface Attribute extends Serializable {

    ItemStack applyAttribute(ItemStack item);

    ItemStack getIcon();

    String getName();

    NamespacedKey getKey();

    static NamespacedKey key() {
        throw new NotImplementedException();
    }

    Attribute copy();
}
