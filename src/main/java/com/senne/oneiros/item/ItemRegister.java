package com.senne.oneiros.item;

import com.senne.oneiros.Oneiros;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Warning;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.senne.oneiros.storage.Data.save;

public class ItemRegister {

    //String is namespace of the item pack
    private static HashMap<String, Pack> packs = new HashMap<>();

    public static void registerItem(Item item, String pack) {
        if (!packs.containsKey(pack)) {
            throw new IllegalArgumentException("Pack not registered");
        }
        packs.get(pack).addItem(item);

        try {
            save(pack);
            packs.get(pack).setSaved(true);
        } catch (IOException e) {
            packs.get(pack).setSaved(true);
            Oneiros.getPlugin().getLogger().warning("[Oneiros] Failed to save pack " + pack);
        }
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

    public static List<Item> getPackContent(String pack) {
        return packs.get(pack).getItems();
    }

    public static Pack getPack(String pack) {
        return packs.get(pack);
    }

    public static List<String> getPacks() {
        return packs.keySet().stream().toList();
    }

    public static ItemStack getPackIcon(String pack) {
        return new ItemStack(packs.get(pack).generateIcon());
    }

    public static void registerPack(Pack pack) {
        packs.put(pack.getName(), pack);

        try {
            save(pack.getName());
            packs.get(pack.getName()).setSaved(true);
        } catch (IOException e) {
            packs.get(pack.getName()).setSaved(true);
            Oneiros.getPlugin().getLogger().warning("[Oneiros] Failed to save pack " + pack.getName());
        }
    }

    @Warning(reason = "This method should only be used for loading packs from file")
    public static void registerPackNoSave(Pack pack) {
        packs.put(pack.getName(), pack);
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
