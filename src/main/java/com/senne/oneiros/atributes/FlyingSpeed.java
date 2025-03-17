package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentDoubleAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class FlyingSpeed extends EquipmentDoubleAttribute {

    public FlyingSpeed() {
        name = "Flying Speed";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "flyingspeed");
        icon = new ItemStack(Material.FEATHER);
        attribute = Attribute.FLYING_SPEED;
        min = -1024;
        max = 1024;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        FlyingSpeed clone = new FlyingSpeed();
        clone.setAmount(amount);
        clone.setSlots(new ArrayList<>(slots));
        return clone;
    }
}