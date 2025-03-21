package com.senne.oneiros.atributes.attributeTypes.events;

import com.senne.oneiros.atributes.attributeTypes.ActiveChat;
import com.senne.oneiros.atributes.attributeTypes.EquipmentIntAttribute;
import com.senne.oneiros.atributes.attributeTypes.UI.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.utils.IntUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

public class EquipmentIntTextEvent {

    public static void onChat(Player player, String message) {

        String activeChatKey = ActiveChat.getActiveChat(player.getUniqueId());
        NamespacedKey key = NamespacedKey.fromString(activeChatKey.replaceFirst("equipmentIntAttribute:", "").split(";")[0]);
        EquipmentSlot slot = EquipmentSlot.valueOf(activeChatKey.replaceFirst("equipmentIntAttribute:", "").split(";")[1]);

        EquipmentIntAttribute attribute = (EquipmentIntAttribute) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(key);

        if (!IntUtils.isInt(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (Integer.parseInt(message) < attribute.getMin() || Integer.parseInt(message) > attribute.getMax()) {
            player.sendMessage(Component.text("Please enter a number between " + attribute.getMin() + " and " + attribute.getMax() + "!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        int amount = Integer.parseInt(message);

        attribute.setAmount(slot, amount);

        ActiveChat.removeActiveChat(player.getUniqueId());

        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, attribute.getKey());
        player.openInventory(ui.getInventory());
    }
}
