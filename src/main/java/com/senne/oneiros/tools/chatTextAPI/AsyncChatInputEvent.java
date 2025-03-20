package com.senne.oneiros.tools.chatTextAPI;


import io.papermc.paper.chat.ChatRenderer;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class AsyncChatInputEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    private ChatRenderer renderer;
    private Component input;
    private String data;
    private NamespacedKey namespacedKey;

    AsyncChatInputEvent(boolean async, Player player, NamespacedKey namespacedKey, Component input, String data) {
        super(player, async);
        this.input = input;
        this.data = data;
        this.namespacedKey = namespacedKey;
    }

    public final Component input() {
        return this.input;
    }

    public final String data() {
        return this.data;
    }

    public final NamespacedKey namespacedKey() {
        return this.namespacedKey;
    }
}