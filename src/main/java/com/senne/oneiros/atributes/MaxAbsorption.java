package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentIntAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class MaxAbsorption extends EquipmentIntAttribute {

    public MaxAbsorption() {
        name = "Max Absorption";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "maxabsorption");
        icon = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
        attribute = Attribute.MAX_ABSORPTION;
        min = -2048;
        max = 2048;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        MaxAbsorption clone = new MaxAbsorption();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}