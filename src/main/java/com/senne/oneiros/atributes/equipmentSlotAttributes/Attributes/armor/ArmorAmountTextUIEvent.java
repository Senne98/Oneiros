package com.senne.oneiros.atributes.equipmentSlotAttributes.armor;

import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.IntUtils;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;

public class ArmorAmountTextUIEvent {

    public static void onChat(Player player, String message) {

        if (!IntUtils.isInt(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (Integer.parseInt(message) < -30 || Integer.parseInt(message) > 30) {
            player.sendMessage(Component.text("Please enter a number between -30 and 30!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        int amount = Integer.parseInt(message);

        Armor armor = (Armor) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(Armor.key());
        armor.setAmount(amount);

        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, Armor.key());
        player.openInventory(ui.getInventory());
    }

}
