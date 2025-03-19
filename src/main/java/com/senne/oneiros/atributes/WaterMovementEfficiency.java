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

public class WaterMovementEfficiency extends EquipmentDoubleAttribute {

    public WaterMovementEfficiency() {
        name = "Water Movement Efficiency";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "watermovementefficiency");
        icon = new ItemStack(Material.HEART_OF_THE_SEA);
        attribute = Attribute.WATER_MOVEMENT_EFFICIENCY;
        min = -1;
        max = 1;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        WaterMovementEfficiency clone = new WaterMovementEfficiency();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}