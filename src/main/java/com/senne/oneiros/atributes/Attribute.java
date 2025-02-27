package com.senne.oneiros.atributes;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public interface Attribute extends Serializable {
    ItemStack applyAttribute(ItemStack item);
    ItemStack getIcon();
    String getName();
    NamespacedKey getKey();
}
