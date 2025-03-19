package com.senne.oneiros.tools.utils;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.Armor;
import com.senne.oneiros.atributes.Hidetooltip;
import com.senne.oneiros.atributes.MaxHealth;
import com.senne.oneiros.atributes.attributeTypes.Attribute;
import com.senne.oneiros.atributes.attributeTypes.AttributeRegister;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.Item;
import com.senne.oneiros.item.ItemRegister;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;
import org.junit.jupiter.api.*;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;
import org.mockbukkit.mockbukkit.plugin.PluginMock;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

import static com.senne.oneiros.tools.utils.SerializationUtils.deserialize;
import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSerializationUtils {

    @BeforeEach
    public void setUp() {
        MockBukkit.mock();
        MockBukkit.load(Oneiros.class);
    }

    @AfterEach
    public void shutDown() {
        MockBukkit.unmock();
    }

    @Test
    public void testMaterial() {
        Material material = Material.PAPER;
        assertEquals(material, deserialize(serialize(material), Material.class));
    }

    @Test
    public void testInt() {
        int value = 1;
        assertEquals(value, deserialize(serialize(value), Integer.class));
    }

    @Test
    public void testDouble() {
        double value = 1.2;
        assertEquals(value, deserialize(serialize(value), Double.class));

        value = 0.0;
        assertEquals(value, deserialize(serialize(value), Double.class));
    }

    @Test
    public void testComponent() {
        Component component = Component.text("Hello, world!").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD).clickEvent(ClickEvent.runCommand("/say Hi"));
        assertTrue(component.equals(deserialize(serialize(component), Component.class)));
    }

    @Test
    public void testNamespacedKey() {
        NamespacedKey key = new NamespacedKey("oneiros", "test");
        assertTrue(key.equals(deserialize(serialize(key), NamespacedKey.class)));
    }


    @Test
    public void testAttribute() {
        Armor armor = (Armor) AttributeRegister.getAttribute(new NamespacedKey(Oneiros.getPlugin(), "armor"));
        armor.setAmount(EquipmentSlot.CHEST, 1);
        armor.setAmount(EquipmentSlot.BODY, 2);

        assertTrue(armor.equals(deserialize(serialize(armor.copy()), Attribute.class)));

        Hidetooltip attribute2 = new Hidetooltip();
        attribute2.setBool(true);

        assertTrue(attribute2.equals(deserialize(serialize(attribute2), Attribute.class)));
    }

    @Test
    public void testItem() {
        Item item = new Item(Material.PAPER);
        item.setDisplayName(Component.text("Test"));

        MaxHealth maxHealth = (MaxHealth) AttributeRegister.getAttribute(new NamespacedKey(Oneiros.getPlugin(), "maxhealth"));
        maxHealth.setAmount(EquipmentSlot.CHEST, 1);
        item.addAttribute(maxHealth);

        Armor attribute = (Armor) AttributeRegister.getAttribute(new NamespacedKey(Oneiros.getPlugin(), "armor"));
        attribute.setAmount(EquipmentSlot.CHEST, 1);
        attribute.setAmount(EquipmentSlot.HEAD, 20);
        item.addAttribute(attribute);

        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("hi"));
        item.setLore(lore);

        item.setNamespace("oneiros");
        item.setKey("item");

        assertEquals(item, deserialize(serialize(item), Item.class));
    }
}
