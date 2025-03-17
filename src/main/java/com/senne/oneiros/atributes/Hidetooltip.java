package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.BooleanAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Hidetooltip extends BooleanAttribute {

    public Hidetooltip() {
        bool = false;
        name = "Hide Tooltip";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "hidetooltip");
        icon = new ItemStack(Material.FILLED_MAP);
    }

    @Override
    public ItemStack applyAttribute(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.setHideTooltip(isBool());
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        Hidetooltip clone = new Hidetooltip();
        clone.setBool(bool);
        return clone;
    }
}
