package com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.submergedMiningSpeed;

import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.DoubleUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class SubmergedMiningSpeedAmountTextUIEvent {

    public static void onChat(Player player, String message) {

        if (!DoubleUtils.isDouble(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (Double.parseDouble(message) < -20 || Double.parseDouble(message) > 20) {
            player.sendMessage(Component.text("Please enter a number between -20 and 20!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        double amount = Double.parseDouble(message);

        SubmergedMiningSpeed submergedMiningSpeed = (SubmergedMiningSpeed) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(SubmergedMiningSpeed.key());
        submergedMiningSpeed.setAmount(amount);

        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, SubmergedMiningSpeed.key());
        player.openInventory(ui.getInventory());
    }

}
