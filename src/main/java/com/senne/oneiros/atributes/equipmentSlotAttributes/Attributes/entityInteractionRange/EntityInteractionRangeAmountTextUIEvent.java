package com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.entityInteractionRange;

import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.DoubleUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class EntityInteractionRangeAmountTextUIEvent {

    public static void onChat(Player player, String message) {

        if (!DoubleUtils.isDouble(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (Double.parseDouble(message) < -64 || Double.parseDouble(message) > 64) {
            player.sendMessage(Component.text("Please enter a number between -64 and 64!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        double amount = Double.parseDouble(message);

        EntityInteractionRange entityInteractionRange = (EntityInteractionRange) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(EntityInteractionRange.key());
        entityInteractionRange.setAmount(amount);

        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, EntityInteractionRange.key());
        player.openInventory(ui.getInventory());
    }

}
