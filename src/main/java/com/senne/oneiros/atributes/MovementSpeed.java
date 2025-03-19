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

public class MovementSpeed extends EquipmentDoubleAttribute {

    public MovementSpeed() {
        name = "Movement Speed";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "movementspeed");
        icon = new ItemStack(Material.GOLDEN_BOOTS);
        attribute = Attribute.MOVEMENT_SPEED;
        min = -1024;
        max = 1024;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        MovementSpeed clone = new MovementSpeed();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}