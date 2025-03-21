package com.senne.oneiros.UI.itemCreation.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.AttributeUI;
import com.senne.oneiros.UI.itemCreation.inventories.ItemCreationUI;
import com.senne.oneiros.UI.itemCreation.inventories.PackSelectUI;
import com.senne.oneiros.UI.itemCreation.inventories.LoreUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.Item;
import com.senne.oneiros.tools.chatTextAPI.ChatInputAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemCreationUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getRawSlot() < 54) {
            handleTop(e);
        } else {
            handleBottom(e);
        }
    }

    public void handleTop(InventoryClickEvent e) {

        if (!(e.getInventory().getHolder() instanceof ItemCreationUI)) {
            return;
        }

        if (e.getClickedInventory() == null) {
            return;
        }

        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();

        int slot = e.getRawSlot();

        if (slot == 28) {
            if (!ActiveItemCreation.hasActiveItem(player.getUniqueId())) {
                return;
            }

            player.closeInventory();
            player.sendMessage(Component.text("Enter the name of the item in the chat.").decoration(TextDecoration.ITALIC, false));
            player.sendMessage(Component.text("This can be done with MiniMessage.").decoration(TextDecoration.ITALIC, false));
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "itemname"), p -> {
                ItemCreationUI ui = new ItemCreationUI(p);
                p.openInventory(ui.getInventory());
            });

            return;
        }

        if (slot == 29) {
               LoreUI ui = new LoreUI(player);
               player.openInventory(ui.getInventory());
               return;
        }

        if (slot == 30) {
            if (!ActiveItemCreation.hasActiveItem(player.getUniqueId())) {
                return;
            }

            player.closeInventory();
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "itemcmd"), p -> {
                ItemCreationUI ui = new ItemCreationUI(p);
                p.openInventory(ui.getInventory());
            }, "Enter the custom model data of the item in the chat.");

            return;
        }

        if (slot == 32) {
            if (!ActiveItemCreation.hasActiveItem(player.getUniqueId())) {
                return;
            }

            AttributeUI ui = new AttributeUI(player, 1);
            player.openInventory(ui.getInventory());
        }

        if (slot == 47) {
               ActiveItemCreation.removeActiveItem(player.getUniqueId());
               player.closeInventory();
               return;
        }

        if (slot == 51) {
            if (!ActiveItemCreation.hasActiveItem(player.getUniqueId())) {
                return;
            }

            PackSelectUI ui = new PackSelectUI(player, 1);
            player.openInventory(ui.getInventory());
            return;
        }
    }

    public void handleBottom(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof ItemCreationUI)) {
            return;
        }

        if (e.getClickedInventory() == null) {
            return;
        }

        if (e.getClickedInventory() instanceof ItemCreationUI) {
            return;
        }

        e.setCancelled(true);

        ItemStack item = e.getClickedInventory().getItem(e.getSlot());

        if (item == null) {
            return;
        }

        if (ActiveItemCreation.hasActiveItem(e.getWhoClicked().getUniqueId())) {
            ActiveItemCreation.getActiveItem(e.getWhoClicked().getUniqueId()).setMaterial(item.getType());
            return;
        }

        Item customItem = new Item(item.getType());
        ActiveItemCreation.addActiveItem(e.getWhoClicked().getUniqueId(), customItem);

        ItemCreationUI ui = new ItemCreationUI((Player) e.getWhoClicked());
        e.getWhoClicked().openInventory(ui.getInventory());
    }
}