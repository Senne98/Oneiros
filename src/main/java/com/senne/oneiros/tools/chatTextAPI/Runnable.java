package com.senne.oneiros.tools.chatTextAPI;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface Runnable {
    void run(Player player);
}
