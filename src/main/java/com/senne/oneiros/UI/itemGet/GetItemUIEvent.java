package com.senne.oneiros.UI.itemGet;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.item.ItemRegister;
import com.senne.oneiros.tools.NamespacedKeyDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class GetItemUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof GetItemUI)) {
            return;
        }

        Player player = (Player) e.getWhoClicked();

        if (e.getRawSlot() > 53) {

            if (e.getClick().isShiftClick()) {
                e.setCancelled(true);
            }

            return;
        }
        e.setCancelled(true);

        PersistentDataContainer data = e.getInventory().getItem(e.getSlot()).getItemMeta().getPersistentDataContainer();

        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "page"))) {
            int page = data.get(new NamespacedKey(Oneiros.getPlugin(), "page"), PersistentDataType.INTEGER);
            String pack = data.get(new NamespacedKey(Oneiros.getPlugin(), "pack"), PersistentDataType.STRING);
            GetItemUI ui = new GetItemUI(page, pack);
            player.openInventory(ui.getInventory());
            return;
        }
        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "customitem"))) {
            NamespacedKey key = data.get(new NamespacedKey(Oneiros.getPlugin(), "customitem"), new NamespacedKeyDataType());

            ClickType clickType = e.getClick();

            if (!player.getItemOnCursor().isEmpty()) {

                if (!player.getItemOnCursor().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Oneiros.getPlugin(), "customitem"))) {
                    player.setItemOnCursor(ItemStack.empty());

                    return;
                }

                NamespacedKey cursorKey = player.getItemOnCursor().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "customitem"), new NamespacedKeyDataType());

                if (!cursorKey.asString().equals(key.asString())) {
                    player.setItemOnCursor(ItemStack.empty());

                    return;
                }
            }

            if (clickType.isShiftClick() || clickType == ClickType.MIDDLE) {
                player.setItemOnCursor(ItemRegister.getItem(key).createItem(64));
                return;
            }

            if (clickType == ClickType.NUMBER_KEY) {
                int numberKey = e.getHotbarButton();
                player.getInventory().setItem(numberKey, ItemRegister.getItem(key).createItem(64));
                return;
            }

            if (clickType != ClickType.LEFT && clickType != ClickType.RIGHT) {
                return;
            }

            int cursorAmount = player.getItemOnCursor().getAmount();

            player.setItemOnCursor(ItemRegister.getItem(key).createItem(cursorAmount + 1));

            return;
        }
        player.setItemOnCursor(ItemStack.empty());
    }

}
