package com.senne.oneiros.tools.chatTextAPI;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CallAsyncChatInputEvent implements Listener {

    @EventHandler
    public void onChatInput(AsyncChatEvent e) {
        Player p = e.getPlayer();

        if (!ChatHandler.hasActiveChat(p.getUniqueId()) || ChatHandler.getActiveChat(p.getUniqueId()) == null) return;
        e.setCancelled(true);

        String[] data = ChatHandler.getActiveChat(p.getUniqueId()).split(";", 1);

        AsyncChatInputEvent event = new AsyncChatInputEvent(true, p, NamespacedKey.fromString(data[0]), e.message(), data[1]);
        event.callEvent();
    }
}
