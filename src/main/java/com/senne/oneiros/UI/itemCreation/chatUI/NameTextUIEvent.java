package com.senne.oneiros.UI.itemCreation.chatUI;

import com.senne.oneiros.UI.itemCreation.CreationUI;
import com.senne.oneiros.item.ActiveItemCreation;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NameTextUIEvent implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {

        Player player = e.getPlayer();

        if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
            return;
        }


        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).equals("name")) {
            String message = PlainTextComponentSerializer.plainText().serialize(e.message());
            ActiveItemCreation.getActiveItem(player.getUniqueId()).setDisplayName(Component.text().decoration(TextDecoration.ITALIC, false).append(MiniMessage.miniMessage().deserialize(message).asComponent()).asComponent());
            ActiveChat.removeActiveChat(player.getUniqueId());

            e.setCancelled(true);

            CreationUI ui = new CreationUI(player);
            player.openInventory(ui.getInventory());
        }
    }
}
