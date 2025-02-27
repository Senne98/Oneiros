package com.senne.oneiros.UI;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.Attribute;
import com.senne.oneiros.atributes.AttributeRegister;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.Item;
import com.senne.oneiros.tools.AttributeUtils;
import com.senne.oneiros.tools.NamespacedKeyDataType;
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
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AttributeUI implements InventoryHolder {

    private Inventory inv;

    public AttributeUI(Player player, int page) {
        this.inv = Bukkit.createInventory(this, 54, "Change Attributes");

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

        Item CustomItem = ActiveItemCreation.getActiveItem(player.getUniqueId());

        item = CustomItem.createItem((byte) 1);
        inv.setItem(13, item);

        // 21 items per page
        int start = (page - 1) * 21;
        int amount = Integer.min(AttributeRegister.getAttributes().size() - start, 21);

        List<Attribute> attributes = AttributeRegister.getAttributes();

        for (int i = start; i < start + amount; i++) {
            item = attributes.get(i).getIcon();
            meta = item.getItemMeta();
            meta.displayName(Component.text(attributes.get(i).getName()).color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));

            if (AttributeUtils.containsInstance(CustomItem.getAttributes(), attributes.get(i))) {
                lore = List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            } else {
                lore = List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            }

            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType(), attributes.get(i).getKey());

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


        if (page > 1) {
            item = new ItemStack(Material.ARROW);
            meta = item.getItemMeta();
            meta.displayName(Component.text("Previous Page").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
            lore = List.of(Component.text("■ Click to go back!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            meta.lore(lore);
            item.setItemMeta(meta);
            inv.setItem(45, item);
        }

        item = new ItemStack(Material.GREEN_CANDLE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Confirm").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to confirm!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(49, item);

        if (AttributeRegister.getAttributes().size() > page * 21) {
            item = new ItemStack(Material.ARROW);
            meta = item.getItemMeta();
            meta.displayName(Component.text("Next Page").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
            lore = List.of(Component.text("■ Click to go forward!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
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
