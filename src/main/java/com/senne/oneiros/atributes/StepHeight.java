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

public class StepHeight extends EquipmentDoubleAttribute {

    public StepHeight() {
        name = "Step Height";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "stepheight");
        icon = new ItemStack(Material.MUSIC_DISC_PIGSTEP);
        attribute = Attribute.STEP_HEIGHT;
        min = -10;
        max = 10;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        StepHeight clone = new StepHeight();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}