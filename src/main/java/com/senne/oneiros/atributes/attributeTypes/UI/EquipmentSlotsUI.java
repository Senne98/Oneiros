package com.senne.oneiros.atributes.attributeTypes.UI;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.Attribute;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.dataTypes.NamespacedKeyDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EquipmentSlotsUI implements InventoryHolder {

    private Inventory inv;

    public EquipmentSlotsUI(Player player, NamespacedKey attributeKey) {
        inv = Bukkit.createInventory(this, 54, "Edit Attribute");

        List<EquipmentSlot> keys = new ArrayList<>();
        if (ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(attributeKey) != null) {
            Attribute attribute = ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(attributeKey);
            if (attribute instanceof EquipmentAmountAttribute eqAmAttribute) {
                keys = eqAmAttribute.getSlots();
            }
        }

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

        item = new ItemStack(Material.CARROT_ON_A_STICK);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Hand").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        if (keys.contains(EquipmentSlot.HAND)) {
            lore = List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        } else {
            lore = List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        }
        //meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "hand"), PersistentDataType.BOOLEAN, false);
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(28, item);

        item = new ItemStack(Material.WARPED_FUNGUS_ON_A_STICK);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Off hand").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        if (keys.contains(EquipmentSlot.OFF_HAND)) {
            lore = List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        } else {
            lore = List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        }
        //meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "offHand"), PersistentDataType.BOOLEAN, false);
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(29, item);

        item = new ItemStack(Material.IRON_BOOTS);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Feet").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        if (keys.contains(EquipmentSlot.FEET)) {
            lore = List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        } else {
            lore = List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        }
        //meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "feet"), PersistentDataType.BOOLEAN, false);
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(30, item);

        item = new ItemStack(Material.IRON_LEGGINGS);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Legs").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        if (keys.contains(EquipmentSlot.LEGS)) {
            lore = List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        } else {
            lore = List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        }
        //meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "legs"), PersistentDataType.BOOLEAN, false);
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(31, item);

        item = new ItemStack(Material.IRON_CHESTPLATE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Chest").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        if (keys.contains(EquipmentSlot.CHEST)) {
            lore = List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        } else {
            lore = List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        }
        //meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "chest"), PersistentDataType.BOOLEAN, false);
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(32, item);

        item = new ItemStack(Material.IRON_HELMET);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Head").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        if (keys.contains(EquipmentSlot.HEAD)) {
            lore = List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        } else {
            lore = List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        }
        //meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "head"), PersistentDataType.BOOLEAN, false);
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(33, item);

        item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Body").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore = new ArrayList<>();
        lore.add(Component.text("Only for horses, dogs, ...").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        if (keys.contains(EquipmentSlot.BODY)) {
            lore = List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        } else {
            lore = List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        }
        //meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "body"), PersistentDataType.BOOLEAN, false);
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(34, item);

        item = new ItemStack(Material.GREEN_CANDLE);
        meta = item.getItemMeta();
        meta.displayName(Component.text("Confirm").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        lore = List.of(Component.text("■ Click to confirm!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        inv.setItem(49, item);
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
