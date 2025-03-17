package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentIntAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class MiningEfficiency extends EquipmentIntAttribute {

    public MiningEfficiency() {
        name = "Mining Efficiency";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "miningefficiency");
        icon = new ItemStack(Material.NETHERITE_PICKAXE);
        attribute = Attribute.MINING_EFFICIENCY;
        min = -1024;
        max = 1024;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        MiningEfficiency clone = new MiningEfficiency();
        clone.setAmount(amount);
        clone.setSlots(new ArrayList<>(slots));
        return clone;
    }
}