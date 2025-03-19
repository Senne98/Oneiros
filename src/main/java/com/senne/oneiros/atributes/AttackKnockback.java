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

public class AttackKnockback extends EquipmentDoubleAttribute {

    public AttackKnockback() {
        name = "Attack Knockback";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "attackknockback");
        icon = new ItemStack(Material.STICK);
        attribute = Attribute.ATTACK_KNOCKBACK;
        min = -5;
        max = 5;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        AttackKnockback clone = new AttackKnockback();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}