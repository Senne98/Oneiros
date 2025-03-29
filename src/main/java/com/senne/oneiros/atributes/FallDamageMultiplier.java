package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class FallDamageMultiplier extends EquipmentAmountAttribute {

    public FallDamageMultiplier() {
        name = "Fall Damage Multiplier";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "falldamagemultiplier");
        icon = new ItemStack(Material.LEATHER_BOOTS);
        attribute = Attribute.FALL_DAMAGE_MULTIPLIER;
        min = -100;
        max = 100;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        FallDamageMultiplier clone = new FallDamageMultiplier();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}