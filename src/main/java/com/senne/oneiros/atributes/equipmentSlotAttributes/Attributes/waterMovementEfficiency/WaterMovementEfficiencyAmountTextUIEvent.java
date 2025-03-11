package com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.waterMovementEfficiency;

import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.DoubleUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class WaterMovementEfficiencyAmountTextUIEvent {

    public static void onChat(Player player, String message) {

        if (!DoubleUtils.isDouble(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (Double.parseDouble(message) < -1024 || Double.parseDouble(message) > 1024) {
            player.sendMessage(Component.text("Please enter a number between -1024 and 1024!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        double amount = Double.parseDouble(message);

        WaterMovementEfficiency waterMovementEfficiency = (WaterMovementEfficiency) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(WaterMovementEfficiency.key());
        waterMovementEfficiency.setAmount(amount);

        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, WaterMovementEfficiency.key());
        player.openInventory(ui.getInventory());
    }

}
