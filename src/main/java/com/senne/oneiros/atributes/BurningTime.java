package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentDoubleAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class BurningTime extends EquipmentDoubleAttribute {

    public BurningTime() {
        name = "Burning Time";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "burningtime");
        icon = new ItemStack(Material.FIRE_CHARGE);
        attribute = Attribute.BURNING_TIME;
        min = -1024;
        max = 1024;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        BurningTime clone = new BurningTime();
        clone.setAmount(amount);
        clone.setSlots(new ArrayList<>(slots));
        return clone;
    }
}
