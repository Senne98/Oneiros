package com.senne.oneiros.UI.itemCreation.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.ItemCreationUI;
import com.senne.oneiros.UI.itemCreation.inventories.PackSelectUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.chatTextAPI.ChatInputAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PackSelectUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof PackSelectUI)) {
            return;
        }

        e.setCancelled(true);

        if (e.getClickedInventory() == null) return;
        if (e.getRawSlot() > 53 || e.getRawSlot() < 0) return;

        int slot = e.getSlot();
        ItemStack item = e.getInventory().getItem(slot);
        PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
        Player player = (Player) e.getWhoClicked();

        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "addPack"))) {
            player.closeInventory();
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "packnamefirst"), p -> {
                PackSelectUI packSelectUI = new PackSelectUI(p, 1);
                p.openInventory(packSelectUI.getInventory());
            }, "Enter the name of the pack in the chat.");

            return;
        }

        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "pack"))) {
            String pack = data.get(new NamespacedKey(Oneiros.getPlugin(), "pack"), PersistentDataType.STRING);
            ActiveItemCreation.getActiveItem(player.getUniqueId()).setNamespace(pack);

            player.closeInventory();
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "itemkey"), p -> {
                ItemCreationUI ui = new ItemCreationUI(p);
                p.openInventory(ui.getInventory());
            }, "Enter the key of the item in the chat.");

            return;
        }

        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "page"))) {
            int page = data.get(new NamespacedKey(Oneiros.getPlugin(), "page"), PersistentDataType.INTEGER);

            PackSelectUI ui = new PackSelectUI(player, page);
            player.openInventory(ui.getInventory());
            return;
        }

        if (slot == 49) {
            ItemCreationUI ui = new ItemCreationUI(player);
            player.openInventory(ui.getInventory());
            return;
        }
    }
}
