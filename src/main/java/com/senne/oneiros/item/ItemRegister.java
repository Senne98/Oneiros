package com.senne.oneiros.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemRegister {

    //String is namespace of the item pack
    private static HashMap<String, Pack> packs = new HashMap<>();

    public static void registerItem(Item item, String pack) {
        if (!packs.containsKey(pack)) {
            throw new IllegalArgumentException("Pack not registered");
        }
        packs.get(pack).addItem(item);
    }

    public static Item getItem(NamespacedKey key) {

        List<Item> items = packs.get(key.getNamespace()).getItems();
        if (items == null) {
            return null;
        }

        for (Item item : items) {
            if (item.getNamespacedKey().getKey().equals(key.getKey())) {
                return item;
            }
        }

        return null;
    }

    public static List<Item> getItems() {
        List<Item> items = new ArrayList<>();

        for (String pack : packs.keySet()) {
            items.addAll(packs.get(pack).getItems());
        }

        return items;
    }

    public static List<Item> getPack(String pack) {
        return packs.get(pack).getItems();
    }

    public static List<String> getPacks() {
        return packs.keySet().stream().toList();
    }

    public static void registerPack(String pack) {
        packs.put(pack, new Pack(pack));
    }

    public static void removeItem(NamespacedKey namespacedKey) {
        String key = namespacedKey.getKey();
        String namespace = namespacedKey.getNamespace();

        packs.get(namespace).removeItem(key);
    }

    public static void setPackAuthors(String pack, String[] authors) {
        packs.get(pack).setAuthors(authors);
    }

    public static void setPackIcon(String pack, Material icon) {
        packs.get(pack).setIcon(icon);
    }

    public static void setPackCmd(String pack, int cmd) {
        packs.get(pack).setCmd(cmd);
    }
}
