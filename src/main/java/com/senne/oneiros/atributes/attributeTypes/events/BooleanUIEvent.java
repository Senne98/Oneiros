package com.senne.oneiros.atributes.attributeTypes.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.AttributeUI;
import com.senne.oneiros.atributes.attributeTypes.BooleanAttribute;
import com.senne.oneiros.atributes.attributeTypes.UI.BooleanUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.dataTypes.NamespacedKeyDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

public class BooleanUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof BooleanUI)) return;
        e.setCancelled(true);

        if (e.getRawSlot() > 53) return;

        int slot = e.getSlot();
        Inventory inv = e.getInventory();
        ItemStack item;
        PersistentDataContainer container;
        Player player = (Player) e.getWhoClicked();

        if (slot == 29) {
            item = inv.getItem(13);
            container = item.getItemMeta().getPersistentDataContainer();
            NamespacedKey key = container.get(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType());
            BooleanAttribute attribute = (BooleanAttribute) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(key);

            if (attribute != null) {
                attribute.setBool(true);

                AttributeUI ui = new AttributeUI(player, 1);
                player.openInventory(ui.getInventory());

                return;
            }

        }

        if (slot == 33) {
            item = inv.getItem(13);
            container = item.getItemMeta().getPersistentDataContainer();
            NamespacedKey key = container.get(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType());
            BooleanAttribute attribute = (BooleanAttribute) ActiveItemCreation.getActiveItem(player.getUniqueId()).getAttribute(key);

            if (attribute != null) {
                attribute.setBool(false);

                AttributeUI ui = new AttributeUI(player, 1);
                player.openInventory(ui.getInventory());

                return;
            }
        }

        AttributeUI ui = new AttributeUI(player, 1);
        player.openInventory(ui.getInventory());
    }
}
