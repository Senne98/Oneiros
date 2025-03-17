package com.senne.oneiros.tools.utils;

import com.senne.oneiros.item.Item;
import org.bukkit.NamespacedKey;

import java.util.List;

public class ItemUtils {

    public static boolean containsKey(List<Item> items, String key) {
        for (Item item : items) {
            if (item.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsNamespacedKey(List<Item> items, NamespacedKey key) {
        for (Item item : items) {
            if (item.getNamespacedKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

}
