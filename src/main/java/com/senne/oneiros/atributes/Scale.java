package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class Scale extends EquipmentAmountAttribute {

    public Scale() {
        name = "Scale";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "scale");
        icon = new ItemStack(Material.OAK_STAIRS);
        attribute = Attribute.SCALE;
        min = -16;
        max = 16;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        Scale clone = new Scale();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}