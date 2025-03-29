package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class Luck extends EquipmentAmountAttribute {

    public Luck() {
        name = "Luck";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "luck");
        icon = new ItemStack(Material.EMERALD);
        attribute = Attribute.LUCK;
        min = -2048;
        max = 2048;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        Luck clone = new Luck();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}