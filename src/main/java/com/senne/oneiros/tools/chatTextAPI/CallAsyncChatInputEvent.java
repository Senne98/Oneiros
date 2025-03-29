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
        UUID uuid = p.getUniqueId();

        if (!ChatHandler.hasActiveChat(p.getUniqueId()) || ChatHandler.getActiveChat(p.getUniqueId()) == null) return;
        e.setCancelled(true);

        ChatHandler.runSend(e.getPlayer(), e.message());

        AsyncChatInputEvent event = new AsyncChatInputEvent(true, p, ChatHandler.getActiveChat(uuid), e.message(), ChatHandler.getData(uuid));
        ChatHandler.removeActiveChat(p.getUniqueId());
        event.callEvent();
    }
}
