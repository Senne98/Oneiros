package com.senne.oneiros.atributes.attributeTypes.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.AttributeUI;
import com.senne.oneiros.atributes.attributeTypes.Attribute;
import com.senne.oneiros.atributes.attributeTypes.EquipmentAmountAttribute;
import com.senne.oneiros.atributes.attributeTypes.UI.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.chatTextAPI.AsyncRunnableSend;
import com.senne.oneiros.tools.chatTextAPI.ChatInputAPI;
import com.senne.oneiros.tools.dataTypes.NamespacedKeyDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.senne.oneiros.tools.utils.DoubleUtils.isDouble;
import static com.senne.oneiros.tools.utils.InventoryUtils.openInvSync;
import static java.lang.Double.parseDouble;

public class EquipmentSlotsUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof EquipmentSlotsUI)) return;
        e.setCancelled(true);

        if (e.getRawSlot() > 53) return;

        Player player = (Player) e.getWhoClicked();
        int slot = e.getSlot();
        Inventory inv = e.getInventory();

        if (slot == 28) {
            clickedSlot(EquipmentSlot.HAND, player, slot, inv);
            return;
        }

        if (slot == 29) {
            clickedSlot(EquipmentSlot.OFF_HAND, player, slot, inv);
            return;
        }

        if (slot == 30) {
            clickedSlot(EquipmentSlot.FEET, player, slot, inv);
            return;
        }

        if (slot == 31) {
            clickedSlot(EquipmentSlot.LEGS, player, slot, inv);
            return;
        }

        if (slot == 32) {
            clickedSlot(EquipmentSlot.CHEST, player, slot, inv);
            return;
        }

        if (slot == 33) {
            clickedSlot(EquipmentSlot.HEAD, player, slot, inv);
            return;
        }

        if (slot == 34) {
            clickedSlot(EquipmentSlot.BODY, player, slot, inv);
            return;
        }

        if (slot == 49) {
            AttributeUI attributeUI = new AttributeUI(player, 1);
            player.openInventory(attributeUI.getInventory());

        }
    }

    private void refreshExample(Inventory inv, UUID uuid, NamespacedKey key) {
        ItemStack item = ActiveItemCreation.getActiveItem(uuid).createItem((byte) 1);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType(), key);
        item.setItemMeta(meta);
        inv.setItem(13, item);
    }

    private void clickedSlot(EquipmentSlot eqSlot, Player player, int slot, Inventory inv) {
        UUID uuid = player.getUniqueId();
        ItemStack item;
        ItemMeta meta;
        Attribute attribute = ActiveItemCreation.getActiveItem(uuid).getAttribute(inv.getItem(13).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType()));

        if (!(attribute instanceof EquipmentAmountAttribute)) return;

        EquipmentAmountAttribute eqAmAttribute = (EquipmentAmountAttribute) attribute;
        List<EquipmentSlot> slots = eqAmAttribute.getSlots();

        item = inv.getItem(slot);
        meta = item.getItemMeta();

        if (slots.contains(eqSlot)) {
            meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
            eqAmAttribute.removeSlot(eqSlot);
        } else {
            String data = attribute.getKey().asString() + ";" + eqSlot;

            player.closeInventory();
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "equipmentamountattribute"), data,
                    p -> openInvSync(player, new AttributeUI(p, 1).getInventory()),
                    new EquipmentSlotAmountSend(), "Enter the amount in the chat.");
        }

        item.setItemMeta(meta);
        inv.setItem(slot, item);

        refreshExample(inv, uuid, attribute.getKey());
    }

}

class EquipmentSlotAmountSend implements AsyncRunnableSend {

    @Override
    public void run(Player player, NamespacedKey namespacedKey, Component message, String data) {
        String input = PlainTextComponentSerializer.plainText().serialize(message);

        NamespacedKey key = NamespacedKey.fromString(data.split(";", 2)[0]);
        EquipmentSlot slot = EquipmentSlot.valueOf(data.split(";", 2)[1]);
        EquipmentAmountAttribute attribute = (EquipmentAmountAttribute) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(key);

        if (!isDouble(input)) {
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "equipmentdoubleattribute"), data,
                    p -> openInvSync(player, new AttributeUI(p, 1).getInventory()),
                    new EquipmentSlotAmountSend(), "Please enter a number!");
            return;
        }

        double amount = parseDouble(input);
        if (amount < attribute.getMin() || amount > attribute.getMax()) {
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "equipmentdoubleattribute"), data,
                    p -> openInvSync(player, new AttributeUI(p, 1).getInventory()),
                    new EquipmentSlotAmountSend(), "Please enter a number between " + attribute.getMin() + " and " + attribute.getMax() + "!");
            return;
        }

        attribute.setAmount(slot, amount);
        openInvSync(player, new EquipmentSlotsUI(player, attribute.getKey()).getInventory());
    }
}
