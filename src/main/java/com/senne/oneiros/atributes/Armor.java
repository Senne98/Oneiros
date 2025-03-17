package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentIntAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;
import static org.apache.commons.lang3.SerializationUtils.deserialize;

public class Armor extends EquipmentIntAttribute {

    public Armor() {
        name = "Armor";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "armor");
        icon = new ItemStack(Material.DIAMOND_CHESTPLATE);
        attribute = Attribute.ARMOR;
        min = -30;
        max = 30;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        Armor clone = new Armor();
        clone.setAmount(amount);
        clone.setSlots(new ArrayList<>(slots));
        return clone;
    }
}
