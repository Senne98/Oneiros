package com.senne.oneiros.atributes.attributeTypes.events;

import com.senne.oneiros.UI.itemCreation.chatUI.ActiveChat;
import com.senne.oneiros.atributes.attributeTypes.EquipmentDoubleAttribute;
import com.senne.oneiros.atributes.attributeTypes.UI.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import static com.senne.oneiros.tools.utils.DoubleUtils.isDouble;
import static java.lang.Double.parseDouble;

public class EquipmentDoubleTextEvent {

    public static void onChat(Player player, String message) {

        String activeChatKey = ActiveChat.getActiveChat(player.getUniqueId());
        // equipmentDoubleAttribute:namespacedkey;slot
        NamespacedKey key = NamespacedKey.fromString(activeChatKey.replaceFirst("equipmentDoubleAttribute:", "").split(";")[0]);
        EquipmentSlot slot = EquipmentSlot.valueOf(activeChatKey.replaceFirst("equipmentDoubleAttribute:", "").split(";")[1]);

        EquipmentDoubleAttribute attribute = (EquipmentDoubleAttribute) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(key);

        if (!isDouble(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (parseDouble(message) < attribute.getMin() || parseDouble(message) > attribute.getMax()) {
            player.sendMessage(Component.text("Please enter a number between " + attribute.getMin() + " and " + attribute.getMax() + "!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        double amount = parseDouble(message);

        ActiveChat.removeActiveChat(player.getUniqueId());

        attribute.setAmount(slot, amount);

        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, attribute.getKey());
        player.openInventory(ui.getInventory());
    }
}
