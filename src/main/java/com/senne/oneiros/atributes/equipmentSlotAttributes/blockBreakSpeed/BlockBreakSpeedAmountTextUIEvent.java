package com.senne.oneiros.atributes.equipmentSlotAttributes.blockBreakSpeed;

import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.IntUtils;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;

public class BlockBreakSpeedAmountTextUIEvent {

    public static void onChat(Player player, String message) {

        if (!IntUtils.isInt(message)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        if (Integer.parseInt(message) < -1024 || Integer.parseInt(message) > 1024) {
            player.sendMessage(Component.text("Please enter a number between -1024 and 1024!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        int amount = Integer.parseInt(message);

        BlockBreakSpeed blockBreakSpeed = (BlockBreakSpeed) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(BlockBreakSpeed.key());
        blockBreakSpeed.setAmount(amount);

        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, BlockBreakSpeed.key());
        player.openInventory(ui.getInventory());
    }

}
