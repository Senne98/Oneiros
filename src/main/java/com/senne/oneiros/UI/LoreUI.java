package com.senne.oneiros.UI;

import com.senne.oneiros.item.ActiveItemCreation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoreUI implements InventoryHolder {

    private Inventory inv;

    public LoreUI(Player player) {
        this.inv = Bukkit.createInventory(this, 54, "Edit Lore");

        ItemStack item;
        ItemMeta meta;
        List<Component> lore;

        for (int i = 0; i < 54; i++) {
            ItemStack neutral = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

            meta = neutral.getItemMeta();
            meta.setDisplayName(" ");
            neutral.setItemMeta(meta);

            inv.setItem(i, neutral);
        }

        item = ActiveItemCreation.getActiveItem(player.getUniqueId()).createItem((byte) 1);
        inv.setItem(13, item);

        item = new ItemStack(Material.RED_DYE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Remove last line").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to remove line!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(29, item);

        item = new ItemStack(Material.GREEN_DYE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Add line").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to add line!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(33, item);

        item = new ItemStack(Material.GREEN_CANDLE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Confirm").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to confirm!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(49, item);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inv;
    }
}
