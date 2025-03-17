package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentDoubleAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class Luck extends EquipmentDoubleAttribute {

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
        clone.setAmount(amount);
        clone.setSlots(new ArrayList<>(slots));
        return clone;
    }
}