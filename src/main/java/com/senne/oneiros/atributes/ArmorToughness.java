package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class ArmorToughness extends EquipmentAmountAttribute {

    public ArmorToughness() {
        name = "Armor Toughness";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "armortoughness");
        icon = new ItemStack(Material.NETHERITE_CHESTPLATE);
        attribute = Attribute.ARMOR_TOUGHNESS;
        min = -20;
        max = 20;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        ArmorToughness clone = new ArmorToughness();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}
