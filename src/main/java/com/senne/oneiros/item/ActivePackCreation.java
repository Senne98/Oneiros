package com.senne.oneiros.item;

import java.util.HashMap;
import java.util.UUID;

public class ActivePackCreation {

    private static HashMap<UUID, Pack> activePacks = new HashMap<>();

    public static void addActivePack(UUID uuid, Pack pack) {
        activePacks.put(uuid, pack);
    }

    public static void removeActivePack(UUID uuid) {
        activePacks.remove(uuid);
    }

    public static Pack getActivePack(UUID uuid) {
        return activePacks.get(uuid);
    }

    public static boolean hasActivePack(UUID uuid) {
        return activePacks.containsKey(uuid);
    }

}
