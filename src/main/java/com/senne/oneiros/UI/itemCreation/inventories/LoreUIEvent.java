package com.senne.oneiros.UI.itemCreation.inventories;

import com.senne.oneiros.Oneiros;
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

import java.util.List;

public class LoreUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof LoreUI)) return;

        e.setCancelled(true);

        if (e.getClickedInventory() == null) return;

        if (e.getRawSlot() > 53) return;

        int slot = e.getSlot();

        Player player = (Player) e.getWhoClicked();
        Item item = ActiveItemCreation.getActiveItem(player.getUniqueId());

        if (slot == 29) {
            List<Component> lore = item.getLore();

            if (lore == null) return;
            if (lore.size() == 0) return;

            lore.remove(lore.size() - 1);
            item.setLore(lore);

            e.getInventory().setItem(13, item.createItem(1));
            return;
        }

        if (slot == 33) {
            if (!ActiveItemCreation.hasActiveItem(player.getUniqueId())) {
                return;
            }

            player.closeInventory();
            player.sendMessage(Component.text("Enter the next line of lore in the chat.").decoration(TextDecoration.ITALIC, false));
            player.sendMessage(Component.text("This can be done with MiniMessage.").decoration(TextDecoration.ITALIC, false));
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "itemlore"), p -> {
                LoreUI ui = new LoreUI(p);
                p.openInventory(ui.getInventory());
            });

            return;
        }

        if (slot == 49) {
                player.closeInventory();
                ItemCreationUI ui = new ItemCreationUI(player);
                player.openInventory(ui.getInventory());
        }
    }
}
