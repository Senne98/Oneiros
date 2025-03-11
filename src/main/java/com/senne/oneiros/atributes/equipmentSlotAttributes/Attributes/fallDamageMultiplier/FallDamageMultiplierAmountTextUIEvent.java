package com.senne.oneiros.atributes.equipmentSlotAttributes.fallDamageMultiplier;

import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.DoubleUtils;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;

public class FallDamageMultiplierAmountTextUIEvent {

    public static void onChat(Player player, String message) {

        if (!DoubleUtils.isDouble(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (Double.parseDouble(message) < -100 || Double.parseDouble(message) > 100) {
            player.sendMessage(Component.text("Please enter a number between -100 and 100!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        double amount = Double.parseDouble(message);

        FallDamageMultiplier fallDamageMultiplier = (FallDamageMultiplier) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(FallDamageMultiplier.key());
        fallDamageMultiplier.setAmount(amount);

        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, FallDamageMultiplier.key());
        player.openInventory(ui.getInventory());
    }

}
