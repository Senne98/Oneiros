package com.senne.oneiros.atributes.equipmentSlotAttributes.armor;

import com.senne.oneiros.UI.chatUI.ActiveChat;
import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.IntUtils;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArmorAmountTextUIEvent implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {

        Player player = e.getPlayer();

        if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
            return;
        }


        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).equals("armor")) {
            String message = PlainTextComponentSerializer.plainText().serialize(e.message());
            e.setCancelled(true);

            if (!IntUtils.isInt(message)) {
                player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
                return;
            }

            int amount = Integer.parseInt(message);

            Armor armor = (Armor) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(Armor.key());
            armor.setAmount(amount);


            EquipmentSlotsUI ui = new EquipmentSlotsUI(player, Armor.key());
            player.openInventory(ui.getInventory());
        }
    }

}
