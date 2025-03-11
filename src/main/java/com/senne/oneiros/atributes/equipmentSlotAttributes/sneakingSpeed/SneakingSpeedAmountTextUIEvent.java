package com.senne.oneiros.atributes.equipmentSlotAttributes.sneakingSpeed;

import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.DoubleUtils;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;

public class SneakingSpeedAmountTextUIEvent {

    public static void onChat(Player player, String message) {

        if (!DoubleUtils.isDouble(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (Double.parseDouble(message) < -1 || Double.parseDouble(message) > 1) {
            player.sendMessage(Component.text("Please enter a number between -1 and 1!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        double amount = Double.parseDouble(message);

        SneakingSpeed sneakingSpeed = (SneakingSpeed) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(SneakingSpeed.key());
        sneakingSpeed.setAmount(amount);

        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, SneakingSpeed.key());
        player.openInventory(ui.getInventory());
    }

}
