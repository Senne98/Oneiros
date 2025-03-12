package com.senne.oneiros.atributes.booleanAttibutes.attributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.booleanAttibutes.BooleanAttribute;
import com.senne.oneiros.atributes.booleanAttibutes.BooleanUI;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Hidetooltip extends BooleanAttribute {

    public Hidetooltip() {
        setBool(false);
    }

    @Override
    public ItemStack applyAttribute(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.setHideTooltip(isBool());
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.MAP);
    }

    @Override
    public String getName() {
        return "Hide Tooltip";
    }

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(Oneiros.getPlugin(), "hidetooltip");
    }

    public static NamespacedKey key() {
        return new NamespacedKey(Oneiros.getPlugin(), "hidetooltip");
    }

    @Override
    public Byte[] exportVariables() {
        return new Byte[]{0};
    }

    @Override
    public void importVariables(Byte[] variables) {
    }

    @Override
    public void variableConfigUI(Player player) {
        BooleanUI ui = new BooleanUI(player, getKey());
        player.closeInventory();
        player.openInventory(ui.getInventory());
    }
}
