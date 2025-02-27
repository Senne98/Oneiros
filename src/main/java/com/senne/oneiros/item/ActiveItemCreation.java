package com.senne.oneiros.item;

import java.util.HashMap;
import java.util.UUID;

public class ActiveItemCreation {

    private static HashMap<UUID, Item> activeItems = new HashMap<>();

    public static void addActiveItem(UUID uuid, Item item) {
        activeItems.put(uuid, item);
    }

    public static void removeActiveItem(UUID uuid) {
        activeItems.remove(uuid);
    }

    public static Item getActiveItem(UUID uuid) {
        return activeItems.get(uuid);
    }

    public static boolean hasActiveItem(UUID uuid) {
        return activeItems.containsKey(uuid);
    }

}
