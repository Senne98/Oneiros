package com.senne.oneiros.tools.chatTextAPI;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface AsyncRunnableCancel {
    void run(Player player);
}
