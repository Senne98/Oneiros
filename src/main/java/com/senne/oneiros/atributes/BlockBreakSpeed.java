package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentDoubleAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class BlockBreakSpeed extends EquipmentDoubleAttribute {

    public BlockBreakSpeed() {
        name = "Block Break Speed";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "blockbreakspeed");
        icon = new ItemStack(Material.GOLDEN_PICKAXE);
        attribute = Attribute.BLOCK_BREAK_SPEED;
        min = -1024;
        max = 1024;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        BlockBreakSpeed clone = new BlockBreakSpeed();
        clone.setAmount(amount);
        clone.setSlots(new ArrayList<>(slots));
        return clone;
    }
}