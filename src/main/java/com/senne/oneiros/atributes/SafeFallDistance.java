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

public class SafeFallDistance extends EquipmentIntAttribute {

    public SafeFallDistance() {
        name = "Safe Fall Distance";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "safeFallDistance");
        icon = new ItemStack(Material.FEATHER);
        attribute = Attribute.SAFE_FALL_DISTANCE;
        min = -1024;
        max = 1024;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        SafeFallDistance clone = new SafeFallDistance();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}