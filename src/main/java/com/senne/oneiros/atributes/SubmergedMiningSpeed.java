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

public class SubmergedMiningSpeed extends EquipmentDoubleAttribute {

    public SubmergedMiningSpeed() {
        name = "Submerged Mining Speed";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "submergedminingspeed");
        icon = new ItemStack(Material.FIRE_CORAL_BLOCK);
        attribute = Attribute.SUBMERGED_MINING_SPEED;
        min = -20;
        max = 20;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        SubmergedMiningSpeed clone = new SubmergedMiningSpeed();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}