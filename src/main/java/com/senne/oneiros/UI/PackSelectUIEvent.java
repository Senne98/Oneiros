package com.senne.oneiros.UI;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.chatUI.ActiveChat;
import com.senne.oneiros.item.ActiveItemCreation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class PackSelectUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof PackSelectUI)) {
            return;
        }

        e.setCancelled(true);

        if (e.getRawSlot() > 53) {
            return;
        }

        int slot = e.getSlot();
        ItemStack item = e.getInventory().getItem(slot);
        PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
        Player player = (Player) e.getWhoClicked();

        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "addPack"))) {
            player.closeInventory();

            ActiveChat.addActiveChat(player.getUniqueId(), "pack");

            player.sendMessage(Component.text("Enter the name of the pack in the chat.").decoration(TextDecoration.ITALIC, false));
            player.sendMessage(Component.text("[Cancel]")
                    .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the name input.").color(NamedTextColor.RED)))
                    .decoration(TextDecoration.ITALIC, false)
                    .color(NamedTextColor.RED)
                    .clickEvent(ClickEvent.runCommand("/oneiroscancel pack")));

            return;
        }

        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "pack"))) {
            player.closeInventory();

            String pack = data.get(new NamespacedKey(Oneiros.getPlugin(), "key"), PersistentDataType.STRING);
            ActiveItemCreation.getActiveItem(player.getUniqueId()).setNamespace(pack);

            player.sendMessage(Component.text("Enter the key of the item in the chat.").decoration(TextDecoration.ITALIC, false));
            player.sendMessage(Component.text("[Cancel]")
                    .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the key input.").color(NamedTextColor.RED)))
                    .decoration(TextDecoration.ITALIC, false)
                    .color(NamedTextColor.RED)
                    .clickEvent(ClickEvent.runCommand("/oneiroscancel key")));



            return;
        }

        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "page"))) {
            int page = data.get(new NamespacedKey(Oneiros.getPlugin(), "page"), PersistentDataType.INTEGER);

            PackSelectUI ui = new PackSelectUI(player, page);
            player.closeInventory();
            player.openInventory(ui.getInventory());
            return;
        }

        if (slot == 49) {
            CreationUI ui = new CreationUI(player);

            player.closeInventory();
            player.openInventory(ui.getInventory());
            return;
        }
    }
}
