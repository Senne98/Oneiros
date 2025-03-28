package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class OxygenBonus extends EquipmentAmountAttribute {

    public OxygenBonus() {
        name = "Oxygen Bonus";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "oxygenbonus");
        icon = new ItemStack(Material.TURTLE_HELMET);
        attribute = Attribute.OXYGEN_BONUS;
        min = -1024;
        max = 1024;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        OxygenBonus clone = new OxygenBonus();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}