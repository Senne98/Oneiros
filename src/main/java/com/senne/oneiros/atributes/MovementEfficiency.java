package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class MovementEfficiency extends EquipmentAmountAttribute {

    public MovementEfficiency() {
        name = "Movement Efficiency";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "movementefficiency");
        icon = new ItemStack(Material.IRON_BOOTS);
        attribute = Attribute.MOVEMENT_EFFICIENCY;
        min = -1;
        max = 1;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        MovementEfficiency clone = new MovementEfficiency();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}