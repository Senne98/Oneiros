package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentDoubleAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class Gravity extends EquipmentDoubleAttribute {

    public Gravity() {
        name = "Gravity";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "gravity");
        icon = new ItemStack(Material.CHAINMAIL_BOOTS);
        attribute = Attribute.GRAVITY;
        min = -1;
        max = 1;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        Gravity clone = new Gravity();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}