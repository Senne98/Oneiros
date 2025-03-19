package com.senne.oneiros.atributes.attributeTypes;

import com.senne.oneiros.tools.utils.EquipmentSlotUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

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
