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

public class BlockInteractionRange extends EquipmentDoubleAttribute {

    public BlockInteractionRange() {
        name = "Block Interaction Range";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "blockinteractionrange");
        icon = new ItemStack(Material.GRASS_BLOCK);
        attribute = Attribute.BLOCK_INTERACTION_RANGE;
        min = -64;
        max = 64;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        BlockInteractionRange clone = new BlockInteractionRange();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}