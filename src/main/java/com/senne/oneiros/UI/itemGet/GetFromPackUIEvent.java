package com.senne.oneiros.UI.itemGet;

import com.senne.oneiros.Oneiros;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class GetFromPackUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof GetFromPackUI)) {
            return;
        }

        e.setCancelled(true);
        Player player = (Player) e.getWhoClicked();

        if (e.getRawSlot() > 53) {
            return;
        }

        PersistentDataContainer data = e.getInventory().getItem(e.getSlot()).getItemMeta().getPersistentDataContainer();

        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "page"))) {
            int page = data.get(new NamespacedKey(Oneiros.getPlugin(), "page"), PersistentDataType.INTEGER);
            GetFromPackUI ui = new GetFromPackUI(page);
            player.openInventory(ui.getInventory());
            return;
        }
        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "pack"))) {
            String pack = data.get(new NamespacedKey(Oneiros.getPlugin(), "pack"), PersistentDataType.STRING);
            GetItemUI ui = new GetItemUI(1, pack);
            player.openInventory(ui.getInventory());
        }
    }
}
