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

            ActiveChat.addActiveChat(player.getUniqueId(), "lore");

            player.sendMessage(Component.text("Enter the next line of lore in the chat.").decoration(TextDecoration.ITALIC, false));
            player.sendMessage(Component.text("This can be done with MiniMessage.").decoration(TextDecoration.ITALIC, false));
            player.sendMessage(Component.text("[Cancel]")
                    .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the lore input.").color(NamedTextColor.RED)))
                    .decoration(TextDecoration.ITALIC, false)
                    .color(NamedTextColor.RED)
                    .clickEvent(ClickEvent.runCommand("/cancel lore")));
            return;
        }

        if (slot == 49) {
                player.closeInventory();
                CreationUI ui = new CreationUI(player);
                player.openInventory(ui.getInventory());
        }
    }
}
