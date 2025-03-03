package com.senne.oneiros.atributes.equipmentSlotAttributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.AttributeUI;
import com.senne.oneiros.atributes.Attribute;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.NamespacedKeyDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class EquipmentSlotsUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof EquipmentSlotsUI)) return;
        e.setCancelled(true);

        if (e.getRawSlot() > 53) return;

        int slot = e.getSlot();
        Inventory inv = e.getInventory();
        ItemStack item;
        ItemMeta meta;


        if (slot == 28) {
            item = inv.getItem(28);
            meta = item.getItemMeta();

            if (meta.getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "hand"), PersistentDataType.BOOLEAN)) {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "hand"), PersistentDataType.BOOLEAN, false);
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            } else {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "hand"), PersistentDataType.BOOLEAN, true);
                meta.lore(List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            }

            item.setItemMeta(meta);
            inv.setItem(28, item);

            return;
        }

        if (slot == 29) {
            item = inv.getItem(29);
            meta = item.getItemMeta();

            if (meta.getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "offHand"), PersistentDataType.BOOLEAN)) {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "offHand"), PersistentDataType.BOOLEAN, false);
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            } else {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "offHand"), PersistentDataType.BOOLEAN, true);
                meta.lore(List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            }

            item.setItemMeta(meta);
            inv.setItem(29, item);

            return;
        }

        if (slot == 30) {
            item = inv.getItem(30);
            meta = item.getItemMeta();

            if (meta.getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "feet"), PersistentDataType.BOOLEAN)) {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "feet"), PersistentDataType.BOOLEAN, false);
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            } else {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "feet"), PersistentDataType.BOOLEAN, true);
                meta.lore(List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            }

            item.setItemMeta(meta);
            inv.setItem(30, item);

            return;
        }

        if (slot == 31) {
            item = inv.getItem(31);
            meta = item.getItemMeta();

            if (meta.getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "legs"), PersistentDataType.BOOLEAN)) {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "legs"), PersistentDataType.BOOLEAN, false);
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            } else {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "legs"), PersistentDataType.BOOLEAN, true);
                meta.lore(List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            }

            item.setItemMeta(meta);
            inv.setItem(31, item);

            return;
        }

        if (slot == 32) {
            item = inv.getItem(32);
            meta = item.getItemMeta();

            if (meta.getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "chest"), PersistentDataType.BOOLEAN)) {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "chest"), PersistentDataType.BOOLEAN, false);
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            } else {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "chest"), PersistentDataType.BOOLEAN, true);
                meta.lore(List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            }

            item.setItemMeta(meta);
            inv.setItem(32, item);

            return;
        }

        if (slot == 33) {
            item = inv.getItem(33);
            meta = item.getItemMeta();

            if (meta.getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "head"), PersistentDataType.BOOLEAN)) {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "head"), PersistentDataType.BOOLEAN, false);
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            } else {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "head"), PersistentDataType.BOOLEAN, true);
                meta.lore(List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            }

            item.setItemMeta(meta);
            inv.setItem(33, item);

            return;
        }

        if (slot == 34) {
            item = inv.getItem(34);
            meta = item.getItemMeta();
            List<Component> lore;

            if (meta.getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "body"), PersistentDataType.BOOLEAN)) {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "body"), PersistentDataType.BOOLEAN, false);
                lore = new ArrayList<>();
                lore.add(Component.text("Only for horses, dogs, ...").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
                lore.add(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            } else {
                meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "body"), PersistentDataType.BOOLEAN, true);
                lore = new ArrayList<>();
                lore.add(Component.text("Only for horses, dogs, ...").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
                lore.add(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));            }

            meta.lore(lore);
            item.setItemMeta(meta);
            inv.setItem(34, item);

            return;
        }

        if (slot == 49) {
            Player player = (Player) e.getWhoClicked();
            NamespacedKey attributeKey = e.getInventory().getItem(13).getItemMeta()
                    .getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType());
            Attribute attribute = ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(attributeKey);

            if(!(attribute instanceof EquipmentAttribute)) return;

            EquipmentAttribute equipmentAttribute = (EquipmentAttribute) attribute;

            if (inv.getItem(28).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "hand"), PersistentDataType.BOOLEAN)) {
                equipmentAttribute.addSlot(EquipmentSlot.HAND);
            }
            if (inv.getItem(29).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "offHand"), PersistentDataType.BOOLEAN)) {
                equipmentAttribute.addSlot(EquipmentSlot.OFF_HAND);
            }
            if (inv.getItem(30).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "feet"), PersistentDataType.BOOLEAN)) {
                equipmentAttribute.addSlot(EquipmentSlot.FEET);
            }
            if (inv.getItem(31).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "legs"), PersistentDataType.BOOLEAN)) {
                equipmentAttribute.addSlot(EquipmentSlot.LEGS);
            }
            if (inv.getItem(32).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "chest"), PersistentDataType.BOOLEAN)) {
                equipmentAttribute.addSlot(EquipmentSlot.CHEST);
            }
            if (inv.getItem(33).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "head"), PersistentDataType.BOOLEAN)) {
                equipmentAttribute.addSlot(EquipmentSlot.HEAD);
            }
            if (inv.getItem(34).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "body"), PersistentDataType.BOOLEAN)) {
                equipmentAttribute.addSlot(EquipmentSlot.BODY);
            }

            player.closeInventory();

            AttributeUI attributeUI = new AttributeUI(player, 1);
            player.openInventory(attributeUI.getInventory());

        }
    }

}
