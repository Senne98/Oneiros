package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class EntityInteractionRange extends EquipmentAmountAttribute {

    public EntityInteractionRange() {
        name = "Entity Interaction Range";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "entityinteractionrange");
        icon = new ItemStack(Material.CREEPER_HEAD);
        attribute = Attribute.ENTITY_INTERACTION_RANGE;
        min = -64;
        max = 64;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        EntityInteractionRange clone = new EntityInteractionRange();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}