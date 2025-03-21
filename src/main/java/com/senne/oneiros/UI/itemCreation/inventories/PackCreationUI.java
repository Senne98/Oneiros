package com.senne.oneiros.UI.itemCreation.inventories;

import com.senne.oneiros.item.ActivePackCreation;
import com.senne.oneiros.item.Pack;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PackCreationUI  implements InventoryHolder {

    private Inventory inv;

    public PackCreationUI(Player player) {
        inv = Bukkit.createInventory(this, 54, "Create new pack");

        ItemStack item;
        ItemMeta meta;
        List<Component> lore;
        UUID uuid = player.getUniqueId();
        Pack pack = ActivePackCreation.getActivePack(uuid);

        for (int i = 0; i < 54; i++) {
            ItemStack neutral = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

            meta = neutral.getItemMeta();
            meta.setDisplayName(" ");
            meta.setHideTooltip(true);
            neutral.setItemMeta(meta);

            inv.setItem(i, neutral);
        }

        item = pack.generateIcon();
        meta = item.getItemMeta();
        lore = item.lore();
        if (lore == null) lore = new ArrayList<>();
        lore.add(Component.text("■ Click item in your inventory!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);

        inv.setItem(13, item);

        item = new ItemStack(Material.PAPER);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Name").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to change!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);

        inv.setItem(29, item);

        item = new ItemStack(Material.RED_DYE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Custom Model Data").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to change!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);

        inv.setItem(31, item);

        item = new ItemStack(Material.WRITTEN_BOOK);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Authors").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to change!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);

        inv.setItem(33, item);

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
