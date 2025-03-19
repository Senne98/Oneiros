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

public class ExplosionKnockbackResistance extends EquipmentDoubleAttribute {

    public ExplosionKnockbackResistance() {
        name = "Explosion Knockback Resistance";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "explosionknockbackresistance");
        icon = new ItemStack(Material.TNT);
        attribute = Attribute.EXPLOSION_KNOCKBACK_RESISTANCE;
        min = -1;
        max = 1;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        ExplosionKnockbackResistance clone = new ExplosionKnockbackResistance();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}