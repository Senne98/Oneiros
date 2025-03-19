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

public class JumpStrength extends EquipmentDoubleAttribute {

    public JumpStrength() {
        name = "Jump Strength";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "jumpstrength");
        icon = new ItemStack(Material.GOLDEN_BOOTS);
        attribute = Attribute.JUMP_STRENGTH;
        min = -32;
        max = 32;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        JumpStrength clone = new JumpStrength();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}