package com.senne.oneiros.item;

import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemRegister {

    //String is namespace of the item pack
    private static HashMap<String, List<Item>> items = new HashMap<>();

    public static void registerItem(Item item, String pack) {
        if (!items.containsKey(pack)) {
            throw new IllegalArgumentException("Pack not registered");
        }
        items.get(pack).add(item);
    }

    public static Item getItem(NamespacedKey key) {

        List<Item> pack = items.get(key.getNamespace());
        if (pack == null) {
            return null;
        }

        for (Item item : pack) {
            if (item.getNamespacedKey().getKey().equals(key.getKey())) {
                return item;
            }
        }

        return null;
    }

    public static List<Item> getItems() {
        List<Item> itemsMap = new ArrayList<>();

        for (String pack : items.keySet()) {
            itemsMap.addAll(items.get(pack));
        }

        return itemsMap;
    }

    public static List<Item> getPack(String pack) {
        return items.get(pack);
    }

    public static List<String> getPacks() {
        return items.keySet().stream().toList();
    }

    public static void registerPack(String pack) {
        items.put(pack, new ArrayList<>());
    }
}
