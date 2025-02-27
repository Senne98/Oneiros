package com.senne.oneiros.UI.chatUI;

import com.senne.oneiros.UI.LoreUI;
import com.senne.oneiros.item.ActiveItemCreation;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class LoreTextUIEvent implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {

        Player player = e.getPlayer();

        if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
            return;
        }


        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).equals("lore")) {
            String message = PlainTextComponentSerializer.plainText().serialize(e.message());

            List<Component> lore = ActiveItemCreation.getActiveItem(player.getUniqueId()).getLore();
            lore.add(Component.text().decoration(TextDecoration.ITALIC, false).color(NamedTextColor.WHITE).append(MiniMessage.miniMessage().deserialize(message).asComponent()).asComponent());
            ActiveItemCreation.getActiveItem(player.getUniqueId()).setLore(lore);

            ActiveChat.removeActiveChat(player.getUniqueId());

            e.setCancelled(true);

            LoreUI ui = new LoreUI(player);
            player.openInventory(ui.getInventory());
        }
    }
}