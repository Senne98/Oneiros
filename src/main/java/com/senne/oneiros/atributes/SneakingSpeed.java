package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class SneakingSpeed extends EquipmentAmountAttribute {

    public SneakingSpeed() {
        name = "Snaking Speed";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "sneakingspeed");
        icon = new ItemStack(Material.IRON_LEGGINGS);
        attribute = Attribute.SNEAKING_SPEED;
        min = -1;
        max = 1;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        SneakingSpeed clone = new SneakingSpeed();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}