package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class SweepingDamageRatio extends EquipmentAmountAttribute {

    public SweepingDamageRatio() {
        name = "Sweeping Damage Ratio";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "sweepingdamageratio");
        icon = new ItemStack(Material.WOODEN_SWORD);
        attribute = Attribute.SWEEPING_DAMAGE_RATIO;
        min = -1;
        max = 1;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        SweepingDamageRatio clone = new SweepingDamageRatio();
        clone.setSlots(new HashMap<>(slots));
        return clone;
    }
}