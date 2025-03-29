package com.senne.oneiros.tools.chatTextAPI;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

@FunctionalInterface
public interface AsyncRunnableSend {
    void run(Player player, NamespacedKey namespacedKey, Component input, String data);
}
