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

public class AttackSpeed extends EquipmentIntAttribute {

    public AttackSpeed() {
        name = "Attack Speed";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "attackspeed");
        icon = new ItemStack(Material.NETHERITE_SWORD);
        attribute = Attribute.ATTACK_SPEED;
        min = -1024;
        max = 1024;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        AttackDamage clone = new AttackDamage();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}
