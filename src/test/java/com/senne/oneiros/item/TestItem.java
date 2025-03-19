package com.senne.oneiros.item;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.AttackDamage;
import com.senne.oneiros.atributes.MaxHealth;
import com.senne.oneiros.mock.MyMockServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestItem {

    Item item;

    @BeforeEach
    public void setUp() {
        item = new Item(Material.DIAMOND);
        MockBukkit.mock(new MyMockServer());
        MockBukkit.load(Oneiros.class, new Object[0]);
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    public void testSetMaterial() {
        item.setMaterial(Material.IRON_INGOT);
        assertEquals(Material.IRON_INGOT, item.getMaterial());
    }

    @Test
    public void testSetName() {
        Component name = Component.text("Test").color(NamedTextColor.GOLD);
        item.setDisplayName(name);
        assertEquals(name, item.getDisplayName());
    }

    @Test
    public void testSetCmd() {
        item.setCmd(101);
        assertEquals(101, item.getCmd());
    }

    @Test
    public void testAddAttribute() {
        MaxHealth health = new MaxHealth();
        AttackDamage attackDamage = new AttackDamage();

        item.addAttribute(health);
        item.addAttribute(attackDamage);
        assertEquals(2, item.getAttributes().size());
        assertTrue(item.getAttributes().contains(health));
        assertTrue(item.getAttributes().contains(attackDamage));
        assertEquals(health, item.getAttribute(health.getKey()));
        assertEquals(attackDamage, item.getAttribute(attackDamage.getKey()));

        item.removeAttribute(health);
        assertEquals(1, item.getAttributes().size());
        assertFalse(item.getAttributes().contains(health));
        assertTrue(item.getAttributes().contains(attackDamage));
        assertNull(item.getAttribute(health.getKey()));
        assertNotNull(item.getAttribute(attackDamage.getKey()));
    }

    @Test
    public void testSetLores() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("Test1").color(NamedTextColor.GOLD));
        lore.add(Component.text("Test2").color(NamedTextColor.GOLD));
        item.setLore(lore);
        assertEquals(2, item.getLore().size());
        assertTrue(item.getLore().contains(lore.get(0)));
        assertTrue(item.getLore().contains(lore.get(1)));
    }

    @Test
    public void testSetNamespacedKey() {
        item.setKey("key");
        item.setNamespace("test");
        assertEquals("test", item.getNamespacedKey().getNamespace());
        assertEquals("key", item.getNamespacedKey().getKey());
        assertEquals(new NamespacedKey("test", "key"), item.getNamespacedKey());
    }

    /*@Test
    public void testSerialization() {
        item.setCmd(101);
        item.setDisplayName(Component.text("Test").color(NamedTextColor.GOLD));
        item.setLore(List.of(Component.text("lore").color(NamedTextColor.GRAY)));
        item.addAttribute(new MovementSpeed());
        item.setKey("key");
        item.setNamespace("test");

        byte[] serialized = item.serialize();
        Item deserialized = Item.deserialize(serialized);

        assertEquals(item.getMaterial(), deserialized.getMaterial());
        assertEquals(item.getCmd(), deserialized.getCmd());
        assertEquals(item.getDisplayName(), deserialized.getDisplayName());
        assertEquals(item.getLore(), deserialized.getLore());
        assertEquals(item.getAttributes().get(0).getKey(), deserialized.getAttributes().get(0).getKey());
        assertEquals(item.getActionHandlers(), deserialized.getActionHandlers());
        assertEquals(item.getNamespacedKey(), deserialized.getNamespacedKey());
    }*/

}
