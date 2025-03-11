package com.senne.oneiros.atributes.equipmentSlotAttributes.maxAbsorption;

import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.IntUtils;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;

public class MaxAbsorptionTextUIEvent {

    public static void onChat(Player player, String message) {

        if (!IntUtils.isInt(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (Integer.parseInt(message) < -2048 || Integer.parseInt(message) > 2048) {
            player.sendMessage(Component.text("Please enter a number between -2048 and 2048!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        int amount = Integer.parseInt(message);

        MaxAbsorption maxAbsorption = (MaxAbsorption) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(MaxAbsorption.key());
        maxAbsorption.setAmount(amount);

        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, MaxAbsorption.key());
        player.openInventory(ui.getInventory());
    }

}
