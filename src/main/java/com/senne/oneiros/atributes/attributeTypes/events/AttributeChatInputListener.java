package com.senne.oneiros.atributes.attributeTypes.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.AttributeUI;
import com.senne.oneiros.atributes.attributeTypes.EquipmentDoubleAttribute;
import com.senne.oneiros.atributes.attributeTypes.EquipmentIntAttribute;
import com.senne.oneiros.atributes.attributeTypes.UI.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.chatTextAPI.AsyncChatInputEvent;
import com.senne.oneiros.tools.chatTextAPI.ChatInputAPI;
import com.senne.oneiros.tools.utils.IntUtils;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;

import static com.senne.oneiros.tools.utils.DoubleUtils.isDouble;
import static com.senne.oneiros.tools.utils.InventoryUtils.openInvSync;
import static java.lang.Double.parseDouble;

public class AttributeChatInputListener implements Listener {

    @EventHandler
    public void onChat(AsyncChatInputEvent e) {
        NamespacedKey key = e.namespacedKey();

        if (!key.getNamespace().equals("oneiros")) return;

        Player player = e.getPlayer();
        String input = PlainTextComponentSerializer.plainText().serialize(e.input());
        String data = e.data();

        if (key.getKey().equals("equipmentdoubleattribute")) {
            equipmentDoubleAttribute(input, player, data);
            return;
        }

        if (key.getKey().equals("equipmentintattribute")) {
            equipmentIntAttribute(input, player, data);
            return;
        }
    }

    public static void equipmentDoubleAttribute(String input, Player player, String data) {
        NamespacedKey key = NamespacedKey.fromString(data.split(";", 2)[0]);
        EquipmentSlot slot = EquipmentSlot.valueOf(data.split(";", 2)[1]);
        EquipmentDoubleAttribute attribute = (EquipmentDoubleAttribute) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(key);

        if (!isDouble(input)) {
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "equipmentdoubleattribute"), data, p -> {
                AttributeUI attributeUI = new AttributeUI(p, 1);
                p.openInventory(attributeUI.getInventory());
            }, "Please enter a number!");
            return;
        }

        double amount = parseDouble(input);
        if (amount < attribute.getMin() || amount > attribute.getMax()) {
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "equipmentdoubleattribute"), data, p -> {
                AttributeUI attributeUI = new AttributeUI(p, 1);
                p.openInventory(attributeUI.getInventory());
            }, "Please enter a number between " + attribute.getMin() + " and " + attribute.getMax() + "!");
            return;
        }

        attribute.setAmount(slot, amount);
        openInvSync(player, new EquipmentSlotsUI(player, attribute.getKey()).getInventory());
    }

    public static void equipmentIntAttribute(String input, Player player, String data) {
        NamespacedKey key = NamespacedKey.fromString(data.split(";", 2)[0]);
        EquipmentSlot slot = EquipmentSlot.valueOf(data.split(";", 2)[1]);

        EquipmentIntAttribute attribute = (EquipmentIntAttribute) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(key);

        if (!IntUtils.isInt(input)) {
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "equipmentintattribute"), data, p -> {
                AttributeUI attributeUI = new AttributeUI(p, 1);
                p.openInventory(attributeUI.getInventory());
            }, "Please enter a number!");
            return;
        }

        int amount = Integer.parseInt(input);
        if (amount < attribute.getMin() || amount > attribute.getMax()) {
            player.closeInventory();
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "equipmentintattribute"), data, p -> {
                AttributeUI attributeUI = new AttributeUI(p, 1);
                p.openInventory(attributeUI.getInventory());
            }, "Please enter a number between " + attribute.getMin() + " and " + attribute.getMax() + "!");
            return;
        }

        attribute.setAmount(slot, amount);
        openInvSync(player, new EquipmentSlotsUI(player, attribute.getKey()).getInventory());
    }
}
