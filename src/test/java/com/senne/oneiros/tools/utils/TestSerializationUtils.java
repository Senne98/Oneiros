package com.senne.oneiros.tools.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.junit.jupiter.api.Test;

import static com.senne.oneiros.tools.utils.SerializationUtils.deserialize;
import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSerializationUtils {

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
}
