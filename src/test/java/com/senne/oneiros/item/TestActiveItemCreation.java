package com.senne.oneiros.item;

import org.bukkit.Material;
import org.junit.jupiter.api.*;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;

public class TestActiveItemCreation {

    Item item;
    PlayerMock player;

    ServerMock server;

    @BeforeEach
    public void setUp() {
        // Start the mock server
        server = MockBukkit.mock();

        item = new Item(Material.ACACIA_DOOR);
        player = server.addPlayer();
        ActiveItemCreation.addActiveItem(player.getUniqueId(), item);
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void testGetActiveItem() {
        Assertions.assertEquals(item, ActiveItemCreation.getActiveItem(player.getUniqueId()));
    }

    @Test
    public void testRemoveActiveItem() {
        ActiveItemCreation.removeActiveItem(player.getUniqueId());
        Assertions.assertNull(ActiveItemCreation.getActiveItem(player.getUniqueId()));
    }

    @Test
    public void testHasActiveItem() {
        Assertions.assertTrue(ActiveItemCreation.hasActiveItem(player.getUniqueId()));
        ActiveItemCreation.removeActiveItem(player.getUniqueId());
        Assertions.assertFalse(ActiveItemCreation.hasActiveItem(player.getUniqueId()));
    }
}
