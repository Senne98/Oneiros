package com.senne.oneiros.UI.itemCreation.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.AuthorsUI;
import com.senne.oneiros.UI.itemCreation.inventories.PackCreationUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.ActivePackCreation;
import com.senne.oneiros.item.Pack;
import com.senne.oneiros.tools.chatTextAPI.ChatInputAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.UUID;

public class AuthorsUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof AuthorsUI)) return;
        e.setCancelled(true);

        if (e.getClickedInventory() == null) return;
        if (e.getRawSlot() > 53) return;

        int slot = e.getSlot();
        Player player = (Player) e.getWhoClicked();
        UUID uuid = player.getUniqueId();
        Pack pack = ActivePackCreation.getActivePack(uuid);

        if (slot == 29) {
            String[] authors = pack.getAuthors();

            if (authors == null) return;
            if (authors.length == 0) return;

            pack.setAuthors(Arrays.copyOf(authors, authors.length - 1));

            e.getInventory().setItem(13, pack.generateIcon());
            return;
        }

        if (slot == 33) {
            if (!ActiveItemCreation.hasActiveItem(player.getUniqueId())) {
                return;
            }

            player.closeInventory();
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "packauthor"), p -> {
                AuthorsUI ui = new AuthorsUI(p);
                p.openInventory(ui.getInventory());
            }, "Enter the next author in the chat.");

            return;
        }

        if (slot == 49) {
                player.closeInventory();
                PackCreationUI ui = new PackCreationUI(player);
                player.openInventory(ui.getInventory());
        }
    }
}
