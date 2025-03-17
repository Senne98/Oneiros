package com.senne.oneiros.UI.itemCreation.chatUI;

import java.util.HashMap;
import java.util.UUID;

public class ActiveChat {

    // Stores the active chat for each player
    // Layout: ID (+ data)

    private static HashMap<UUID, String> activeChats = new HashMap<>();

    public static void addActiveChat(UUID uuid, String key) {
        activeChats.put(uuid, key);
    }

    public static void removeActiveChat(UUID uuid) {
        activeChats.remove(uuid);
    }

    public static String getActiveChat(UUID uuid) {
        return activeChats.get(uuid);
    }

}
