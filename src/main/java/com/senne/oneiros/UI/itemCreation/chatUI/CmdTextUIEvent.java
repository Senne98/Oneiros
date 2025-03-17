package com.senne.oneiros.UI.itemCreation.chatUI;

import com.senne.oneiros.UI.itemCreation.CreationUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.utils.IntUtils;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CmdTextUIEvent implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {

        Player player = e.getPlayer();

        if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
            return;
        }


        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).equals("cmd")) {
            String message = PlainTextComponentSerializer.plainText().serialize(e.message());
            e.setCancelled(true);

            if (!IntUtils.isInt(message)) {
                player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
                return;
            }

            if (message.length() > 7) {
                player.sendMessage(Component.text("Please enter a number between 0 and 9999999!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
                return;
            }

            int cmd = Integer.parseInt(message);

            if (cmd < 0) {
                player.sendMessage(Component.text("Please enter a number between 0 and 9999999!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
                return;
            }

            ActiveItemCreation.getActiveItem(player.getUniqueId()).setCmd(cmd);

            CreationUI ui = new CreationUI(player);
            player.openInventory(ui.getInventory());
        }
    }
}
