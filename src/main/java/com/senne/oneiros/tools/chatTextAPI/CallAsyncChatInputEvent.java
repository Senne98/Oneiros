package com.senne.oneiros.tools.chatTextAPI;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class CallAsyncChatInputEvent implements Listener {

    @EventHandler
    public void onChatInput(AsyncChatEvent e) {

        Player p = e.getPlayer();

        if (!ChatHandler.hasActiveChat(p.getUniqueId()) || ChatHandler.getActiveChat(p.getUniqueId()) == null) return;
        e.setCancelled(true);

        ChatHandler.runSend(e.getPlayer(), e.message());
        ChatHandler.removeActiveChat(p.getUniqueId());
    }
}
