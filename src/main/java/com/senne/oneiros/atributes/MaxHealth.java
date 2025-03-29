package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class MaxHealth extends EquipmentAmountAttribute {

    public MaxHealth() {
        name = "Max Health";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "maxhealth");
        icon = new ItemStack(Material.GOLDEN_APPLE);
        attribute = Attribute.MAX_HEALTH;
        min = -1024;
        max = 1024;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        MaxHealth clone = new MaxHealth();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}