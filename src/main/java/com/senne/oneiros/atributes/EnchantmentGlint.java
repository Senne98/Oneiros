package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.BooleanAttribute;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EnchantmentGlint extends BooleanAttribute {

    public EnchantmentGlint() {
        bool = false;
        name = "Enchantment Glint";
        namespacedKey = new NamespacedKey(Oneiros.getPlugin(), "enchantmentglint");
        icon = new ItemStack(Material.ENCHANTED_BOOK);
    }

    @Override
    public ItemStack applyAttribute(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.setEnchantmentGlintOverride(isBool());
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public com.senne.oneiros.atributes.attributeTypes.Attribute copy() {
        EnchantmentGlint clone = new EnchantmentGlint();
        clone.setBool(bool);
        return clone;
    }
}
