package com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.armorToughness;

import com.senne.oneiros.atributes.equipmentSlotAttributes.armorToughness.ArmorToughness;
import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.IntUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class ArmorToughnessAmountTextUIEvent {

    public static void onChat(Player player, String message) {

        if (!IntUtils.isInt(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (Integer.parseInt(message) < -20 || Integer.parseInt(message) > 20) {
            player.sendMessage(Component.text("Please enter a number between -20 and 20!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        int amount = Integer.parseInt(message);

        ArmorToughness armorToughness = (ArmorToughness) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(ArmorToughness.key());
        armorToughness.setAmount(amount);


        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, ArmorToughness.key());
        player.openInventory(ui.getInventory());
    }

}
