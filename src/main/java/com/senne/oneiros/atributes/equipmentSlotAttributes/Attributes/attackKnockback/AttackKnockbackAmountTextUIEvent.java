package com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.attackKnockback;

import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.DoubleUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class AttackKnockbackAmountTextUIEvent {

    public static void onChat(Player player, String message) {

        if (!DoubleUtils.isDouble(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (Double.parseDouble(message) < -5 || Double.parseDouble(message) > 5) {
            player.sendMessage(Component.text("Please enter a number between -5 and 5!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        double amount = Double.parseDouble(message);

        AttackKnockback attackKnockback = (AttackKnockback) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(AttackKnockback.key());
        attackKnockback.setAmount(amount);


        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, AttackKnockback.key());
        player.openInventory(ui.getInventory());
    }

}
