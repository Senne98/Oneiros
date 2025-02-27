package com.senne.oneiros.UI;

import com.senne.oneiros.UI.chatUI.ActiveChat;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.Item;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class CreationUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getRawSlot() < 54) {
            handleTop(e);
        } else {
            handleBottom(e);
        }
    }

    public void handleTop(InventoryClickEvent e) {

        if (!(e.getInventory().getHolder() instanceof CreationUI)) {
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

            ActiveChat.addActiveChat(player.getUniqueId(), "name");

            player.sendMessage(Component.text("Enter the name of the item in the chat.").decoration(TextDecoration.ITALIC, false));
            player.sendMessage(Component.text("This can be done with MiniMessage.").decoration(TextDecoration.ITALIC, false));
            player.sendMessage(Component.text("[Cancel]")
                    .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the name input.").color(NamedTextColor.RED)))
                    .decoration(TextDecoration.ITALIC, false)
                    .color(NamedTextColor.RED)
                    .clickEvent(ClickEvent.runCommand("/oneiroscancel name")));

            return;
        }

        if (slot == 29) {
               LoreUI ui = new LoreUI(player);
               player.closeInventory();
               player.openInventory(ui.getInventory());
               return;
        }

        if (slot == 30) {
            if (!ActiveItemCreation.hasActiveItem(player.getUniqueId())) {
                return;
            }

            player.closeInventory();

            ActiveChat.addActiveChat(player.getUniqueId(), "cmd");

            player.sendMessage(Component.text("Enter the custom model data of the item in the chat.").decoration(TextDecoration.ITALIC, false));
            player.sendMessage(Component.text("[Cancel]")
                    .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the custom model data input.").color(NamedTextColor.RED)))
                    .decoration(TextDecoration.ITALIC, false)
                    .color(NamedTextColor.RED)
                    .clickEvent(ClickEvent.runCommand("/oneiroscancel cmd")));

            return;
        }

        if (slot == 32) {
            if (!ActiveItemCreation.hasActiveItem(player.getUniqueId())) {
                return;
            }

            player.closeInventory();

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
            player.closeInventory();
            player.openInventory(ui.getInventory());
            return;
        }
    }

    public void handleBottom(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof CreationUI)) {
            return;
        }

        if (e.getClickedInventory() == null) {
            return;
        }

        if (e.getClickedInventory() instanceof CreationUI) {
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

        e.getWhoClicked().closeInventory();

        CreationUI ui = new CreationUI((Player) e.getWhoClicked());
        e.getWhoClicked().openInventory(ui.getInventory());
    }
}