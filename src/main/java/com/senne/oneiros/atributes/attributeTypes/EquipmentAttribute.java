package com.senne.oneiros.atributes.attributeTypes;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public abstract class EquipmentAttribute implements VariableAttribute {

    protected NamespacedKey namespacedKey;
    protected ItemStack icon;
    protected String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public NamespacedKey getKey() {
        return namespacedKey;
    }

    @Override
    public ItemStack getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object j) {
        if (!(j instanceof EquipmentAttribute)) return false;
        EquipmentAttribute attribute = (EquipmentAttribute) j;
        if (!attribute.name.equals(this.name)) return false;
        if (!attribute.icon.equals(this.icon)) return false;

        return attribute.namespacedKey.equals(this.namespacedKey);
    }
}
