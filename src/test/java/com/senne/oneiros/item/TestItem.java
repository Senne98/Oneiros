package com.senne.oneiros.item;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.attackDamage.AttackDamage;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.maxHealth.Health;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.movementSpeed.MovementSpeed;
import com.senne.oneiros.mock.MyMockServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;

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
        Health health = new Health();
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

    @Test
    public void testSerialization() {
        item.setCmd(101);
        item.setDisplayName(Component.text("Test").color(NamedTextColor.GOLD));
        item.setLore(List.of(Component.text("lore").color(NamedTextColor.GRAY)));
        item.addAttribute(new MovementSpeed());
        item.setKey("key");
        item.setNamespace("test");

        byte[] serialized = SerializationUtils.serialize(item);
        assertEquals(item.getMaterial(), ((Item) SerializationUtils.deserialize(serialized)).getMaterial());
        assertEquals(item.getCmd(), ((Item) SerializationUtils.deserialize(serialized)).getCmd());
        assertEquals(item.getDisplayName(), ((Item) SerializationUtils.deserialize(serialized)).getDisplayName());
        assertEquals(item.getLore(), ((Item) SerializationUtils.deserialize(serialized)).getLore());
        assertEquals(item.getAttributes().get(0).getKey(), ((Item) SerializationUtils.deserialize(serialized)).getAttributes().get(0).getKey());
        assertEquals(item.getActionHandlers(), ((Item) SerializationUtils.deserialize(serialized)).getActionHandlers());
        assertEquals(item.getNamespacedKey(), ((Item) SerializationUtils.deserialize(serialized)).getNamespacedKey());
    }

}
