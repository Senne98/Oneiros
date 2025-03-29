package com.senne.oneiros.UI.itemCreation.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.AttributeUI;
import com.senne.oneiros.UI.itemCreation.inventories.ItemCreationUI;
import com.senne.oneiros.UI.itemCreation.inventories.PackSelectUI;
import com.senne.oneiros.UI.itemCreation.inventories.LoreUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.Item;
import com.senne.oneiros.tools.chatTextAPI.AsyncRunnableSend;
import com.senne.oneiros.tools.chatTextAPI.ChatInputAPI;
import com.senne.oneiros.tools.utils.IntUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static com.senne.oneiros.tools.utils.InventoryUtils.openInvSync;

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
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "itemname"),
                    p -> openInvSync(p, new ItemCreationUI(p).getInventory()),
                    (player1, namespacedKey, message, data) -> {
                        String input = PlainTextComponentSerializer.plainText().serialize(message);
                        ActiveItemCreation.getActiveItem(player1.getUniqueId())
                                .setDisplayName(Component.text()
                                        .decoration(TextDecoration.ITALIC, false)
                                        .append(MiniMessage.miniMessage().deserialize(input).asComponent())
                                        .asComponent()
                                );
                        openInvSync(player1, new ItemCreationUI(player1).getInventory());
                    }
            );

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
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "itemcmd"),
                    p -> openInvSync(p, new ItemCreationUI(p).getInventory()),
                     new ItemCMDSend(), "Enter the custom model data of the item in the chat.");

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

class ItemCMDSend implements AsyncRunnableSend {
    @Override
    public void run(Player player, NamespacedKey namespacedKey, Component message, String data) {
        String input = PlainTextComponentSerializer.plainText().serialize(message);
        if (!IntUtils.isInt(input)) {
            ChatInputAPI.newInput(player, namespacedKey, data,
                    p -> openInvSync(p, new ItemCreationUI(p).getInventory()),
                    new ItemCMDSend(), "Please enter a number!");
            return;
        }

        int cmd = Integer.parseInt(input);
        if (cmd > 9999999 || cmd < 0) {
            ChatInputAPI.newInput(player, namespacedKey, data,
                    p -> openInvSync(p, new ItemCreationUI(p).getInventory()),
                    new ItemCMDSend(), "Please enter a number between 0 and 9999999!");
            return;
        }

        openInvSync(player, new ItemCreationUI(player).getInventory());
    }
}