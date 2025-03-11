package com.senne.oneiros.UI.itemCreation;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.AttributeRegister;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.Item;
import com.senne.oneiros.item.ItemRegister;
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
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PackSelectUI implements InventoryHolder {

    private Inventory inv;

    public PackSelectUI(Player player, int page) {
        inv = Bukkit.createInventory(this, 54, "Select a Pack");

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

        Item CustomItem = ActiveItemCreation.getActiveItem(player.getUniqueId());

        item = CustomItem.createItem((byte) 1);
        inv.setItem(13, item);

        int start = (page - 1) * 21;
        int amount = Integer.min(ItemRegister.getPacks().size() - start, 21);

        List<String> packs = ItemRegister.getPacks();

        for (int i = start; i < start + amount; i++) {
            item = new ItemStack(Material.CHEST);
            meta = item.getItemMeta();
            meta.displayName(Component.text(packs.get(i)).color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));

            lore = List.of(Component.text("■ Click to add item to pack!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "pack"), PersistentDataType.STRING, packs.get(i));

            int slot = 19 + i - start;

            if (i - start > 7) {
                slot += 2;
            }
            if (i - start > 14) {
                slot += 2;
            }

            meta.lore(lore);
            item.setItemMeta(meta);
            inv.setItem(slot, item);
        }

        if (amount < 21) {
            int slot = 19 + amount;

            if (amount > 7) {
                slot += 2;
            }
            if (amount > 14) {
                slot += 2;
            }

            item = new ItemStack(Material.KNOWLEDGE_BOOK);
            meta = item.getItemMeta();
            meta.displayName(Component.text("Create New Pack").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
            meta.lore(List.of(Component.text("■ Click to create a new pack!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "addPack"), PersistentDataType.BOOLEAN, true);
            item.setItemMeta(meta);

            inv.setItem(slot, item);
        }


        if (page > 1) {
            item = new ItemStack(Material.ARROW);
            meta = item.getItemMeta();
            meta.displayName(Component.text("Previous Page").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
            lore = List.of(Component.text("■ Click to go back!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "page"), PersistentDataType.INTEGER, page - 1);
            meta.lore(lore);
            item.setItemMeta(meta);
            inv.setItem(45, item);
        }

        item = new ItemStack(Material.GREEN_CANDLE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Cancel").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to cancel!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(49, item);

        if (AttributeRegister.getAttributes().size() > page * 21) {
            item = new ItemStack(Material.ARROW);
            meta = item.getItemMeta();
            meta.displayName(Component.text("Next Page").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
            lore = List.of(Component.text("■ Click to go forward!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "page"), PersistentDataType.INTEGER, page + 1);
            meta.lore(lore);
            item.setItemMeta(meta);
            inv.setItem(53, item);
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inv;
    }
}
