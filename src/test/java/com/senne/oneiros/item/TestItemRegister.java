package com.senne.oneiros.item;

import org.bukkit.Material;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestItemRegister {

    Item item1;
    Item item2;
    Item item3;

    @BeforeEach
    public void setUp() {
        ItemRegister.registerPack(new Pack("pack"));
        item1 = new Item(Material.ACACIA_BOAT);
        item1.setNamespace("pack");
        item1.setKey("item");
        ItemRegister.registerItem(item1, "pack");
        item2 = new Item(Material.CHERRY_CHEST_BOAT);
        item2.setNamespace("pack");
        item2.setKey("cherry");
        ItemRegister.registerItem(item2, "pack");
        ItemRegister.registerPack(new Pack("pock"));
        item3 = new Item(Material.ACACIA_BOAT);
        item3.setNamespace("pock");
        item3.setKey("itempock");
        ItemRegister.registerItem(item3, "pock");
    }

    @Test
    public void testRegisterItem() {
        List<String> packs = ItemRegister.getPacks();
        Assertions.assertEquals(2, packs.size());
        Assertions.assertTrue(packs.contains("pack"));
        Assertions.assertTrue(packs.contains("pock"));

        List<Item> items = ItemRegister.getItems();
        Assertions.assertEquals(3, items.size());
        Assertions.assertTrue(items.contains(item1));
        Assertions.assertTrue(items.contains(item2));
        Assertions.assertTrue(items.contains(item3));

        List<Item> pack = ItemRegister.getPackContent("pack");
        Assertions.assertEquals(2, pack.size());
        Assertions.assertTrue(pack.contains(item1));
        Assertions.assertTrue(pack.contains(item2));

        List<Item> pock = ItemRegister.getPackContent("pock");
        Assertions.assertEquals(1, pock.size());
        Assertions.assertTrue(pock.contains(item3));
    }
}
