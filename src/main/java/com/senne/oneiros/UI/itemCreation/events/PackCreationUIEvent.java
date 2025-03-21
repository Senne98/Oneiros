package com.senne.oneiros.UI.itemCreation.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.ItemCreationUI;
import com.senne.oneiros.UI.itemCreation.inventories.AuthorsUI;
import com.senne.oneiros.UI.itemCreation.inventories.PackCreationUI;
import com.senne.oneiros.UI.itemCreation.inventories.PackSelectUI;
import com.senne.oneiros.item.*;
import com.senne.oneiros.tools.chatTextAPI.ChatInputAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PackCreationUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof PackCreationUI)) return;

        if (e.getClickedInventory() == null) return;

        if (e.getRawSlot() < 54) {
            handleTop(e);
        } else {
            handleBottom(e);
        }
    }

    public void handleTop(InventoryClickEvent e) {
        e.setCancelled(true);
        Player player = (Player) e.getWhoClicked();
        int slot = e.getRawSlot();

        if (slot == 29) {
            if (!ActiveItemCreation.hasActiveItem(player.getUniqueId())) {
                return;
            }

            player.closeInventory();
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "packname"), p -> {
                PackCreationUI ui = new PackCreationUI(p);
                p.openInventory(ui.getInventory());
            }, "Enter the name of the pack in the chat.");

            return;
        }

        if (slot == 31) {
            if (!ActiveItemCreation.hasActiveItem(player.getUniqueId())) {
                return;
            }

            player.closeInventory();
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "packcmd"), p -> {
                PackCreationUI ui = new PackCreationUI(p);
                p.openInventory(ui.getInventory());
            }, "Enter the custom model data of the pack in the chat.");

            return;
        }

        if (slot == 33) {
               AuthorsUI ui = new AuthorsUI(player);
               player.openInventory(ui.getInventory());
               return;
        }

        if (slot == 47) {
               ActivePackCreation.removeActivePack(player.getUniqueId());
               PackSelectUI ui = new PackSelectUI(player, 1);
               player.openInventory(ui.getInventory());
               return;
        }

        if (slot == 51) {
            ItemRegister.registerPack(ActivePackCreation.getActivePack(player.getUniqueId()));
            ActivePackCreation.removeActivePack(player.getUniqueId());
            PackSelectUI ui = new PackSelectUI(player, 1);
            player.openInventory(ui.getInventory());
            return;
        }
    }

    public void handleBottom(InventoryClickEvent e) {
        if (e.getClickedInventory() instanceof ItemCreationUI) return;
        e.setCancelled(true);

        ItemStack item = e.getClickedInventory().getItem(e.getSlot());
        if (item == null) return;

        Player player = (Player) e.getWhoClicked();
        UUID uuid = player.getUniqueId();
        Pack pack = ActivePackCreation.getActivePack(uuid);
        pack.setIcon(item.getType());

        ItemStack icon = pack.generateIcon();
        ItemMeta meta = icon.getItemMeta();
        List<Component> lore = item.lore();
        if (lore == null) lore = new ArrayList<>();
        lore.add(Component.text("■ Click item in your inventory!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        icon.setItemMeta(meta);
        e.getInventory().setItem(13, icon);
    }
}