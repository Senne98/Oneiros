package com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.attackDamage;

import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.IntUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class AttackDamageAmountTextUIEvent {

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

        AttackDamage attackDamage = (AttackDamage) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(AttackDamage.key());
        attackDamage.setAmount(amount);


        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, AttackDamage.key());
        player.openInventory(ui.getInventory());
    }

}
