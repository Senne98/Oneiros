package com.senne.oneiros.atributes.attributeTypes.UI;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.dataTypes.NamespacedKeyDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BooleanUI implements InventoryHolder {

    private Inventory inv;

    public BooleanUI(Player player, NamespacedKey attributeKey) {
        inv = Bukkit.createInventory(this, 54, "Armor Slots");

        ItemStack item;
        ItemMeta meta;
        List<Component> lore;

        for (int i = 0; i < 54; i++) {
            ItemStack neutral = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

            meta = neutral.getItemMeta();
            meta.setDisplayName(" ");
            meta.setHideTooltip(true);
            neutral.setItemMeta(meta);

            inv.setItem(i, neutral);
        }

        item = ActiveItemCreation.getActiveItem(player.getUniqueId()).createItem((byte) 1);
        meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType(), attributeKey);
        item.setItemMeta(meta);
        inv.setItem(13, item);

        item = new ItemStack(Material.GREEN_CONCRETE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("True").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to set to true!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(29, item);

        item = new ItemStack(Material.RED_CONCRETE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("False").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to set to false!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(33, item);
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
