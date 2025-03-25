package com.senne.oneiros.UI.itemGet.inventories;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.item.Item;
import com.senne.oneiros.item.ItemRegister;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetItemUI implements InventoryHolder {

    private final Inventory inv;

    public GetItemUI(int page, String pack) {
        inv = Bukkit.createInventory(this, 54, "Select Item");

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

        // 7 * 4 = 28 items per page
        int start = (page - 1) * 28;
        List<Item> items = ItemRegister.getPackContent(pack);
        int amount = Integer.min(items.size() - start, 28);

        for (int i = start; i < start + amount; i++) {
            item = items.get(i).createItem(1);

            int slot = 10 + i - start;

            if (i - start > 7) {
                slot += 2;
            }
            if (i - start > 14) {
                slot += 2;
            }
            if (i - start > 21) {
                slot += 2;
            }

            inv.setItem(slot, item);
        }


        if (page > 1) {
            item = new ItemStack(Material.ARROW);
            meta = item.getItemMeta();
            meta.displayName(Component.text("Previous Page").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
            lore = List.of(Component.text("■ Click to go back!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "page"), PersistentDataType.INTEGER, page - 1);
            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "pack"), PersistentDataType.STRING, pack);
            meta.lore(lore);
            item.setItemMeta(meta);
            inv.setItem(45, item);
        }

        if (items.size() > page * 28) {
            item = new ItemStack(Material.ARROW);
            meta = item.getItemMeta();
            meta.displayName(Component.text("Next Page").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
            lore = List.of(Component.text("■ Click to go forward!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "page"), PersistentDataType.INTEGER, page - 1);
            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "pack"), PersistentDataType.STRING, pack);
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
