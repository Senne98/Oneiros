package com.senne.oneiros.UI.itemCreation;

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

import java.util.List;

public class CreationUI implements InventoryHolder {

    private Inventory inv;

    public CreationUI(Player player) {
        inv = Bukkit.createInventory(this, 54, "Create your item");

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

        if (ActiveItemCreation.hasActiveItem(player.getUniqueId())) {
            item = ActiveItemCreation.getActiveItem(player.getUniqueId()).createItem((byte) 1);

            inv.setItem(13, item);

        } else {
            item = new ItemStack(Material.ENDER_EYE);
            meta = item.getItemMeta();
            meta.displayName(Component.text("Item").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
            lore = List.of(Component.text("■ Click an item in your inventory!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            meta.lore(lore);
            item.setItemMeta(meta);

            inv.setItem(13, item);
        }

        item = new ItemStack(Material.PAPER);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Name").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of((Component) Component.text("■ Click to change!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);

        inv.setItem(28, item);

        item = new ItemStack(Material.WRITABLE_BOOK);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Lore").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of((Component) Component.text("■ Click to change!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);

        inv.setItem(29, item);

        item = new ItemStack(Material.RED_DYE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Custom Model Data").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of((Component) Component.text("■ Click to change!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);

        inv.setItem(30, item);

        item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Attributes").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of((Component) Component.text("■ Click to change!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);

        inv.setItem(32, item);

        item = new ItemStack(Material.FIRE_CHARGE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Actions").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of((Component) Component.text("■ Click to change!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);

        inv.setItem(34, item);

        item = new ItemStack(Material.BARRIER);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Cancel").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to cancel!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);

        inv.setItem(47, item);

        item = new ItemStack(Material.GREEN_CANDLE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Confirm").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to confirm!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(51, item);
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
