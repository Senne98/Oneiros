package com.senne.oneiros.item;

import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.List;

public class ItemRegister {

    //String is namespace of the item pack
    private static HashMap<String, HashMap<NamespacedKey, Item>> items = new HashMap<>();

    public static void registerItem(Item item, String pack) {
        if (!items.containsKey(pack)) {
            throw new IllegalArgumentException("Pack not registered");
        }
        items.get(pack).put(item.getNamespacedKey(), item);
    }

    public static Item getItem(NamespacedKey key) {
        return items.get(key.getNamespace()).get(key);
    }

    public static HashMap<NamespacedKey, Item> getItems() {
        HashMap<NamespacedKey, Item> itemsMap = new HashMap<>();

        for (String pack : items.keySet()) {
            itemsMap.putAll(items.get(pack));
        }

        return itemsMap;
    }

    public static HashMap<NamespacedKey, Item> getPack(String pack) {
        return items.get(pack);
    }

    public static List<String> getPacks() {
        return (List<String>) items.keySet();
    }

    public static void registerPack(String pack) {
        items.put(pack, new HashMap<>());
    }
}
