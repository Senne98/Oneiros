package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentIntAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class AttackDamage extends EquipmentIntAttribute {

    public AttackDamage() {
        name = "Attack Damage";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "attackdamage");
        icon = new ItemStack(Material.GOLDEN_SWORD);
        attribute = Attribute.ATTACK_DAMAGE;
        min = -2048;
        max = 2048;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        AttackDamage clone = new AttackDamage();
        clone.setAmount(amount);
        clone.setSlots(new ArrayList<>(slots));
        return clone;
    }
}
