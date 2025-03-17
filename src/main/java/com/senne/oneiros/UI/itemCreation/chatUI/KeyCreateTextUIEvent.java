package com.senne.oneiros.UI.itemCreation.chatUI;

import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.ItemRegister;
import com.senne.oneiros.tools.utils.ItemUtils;
import com.senne.oneiros.tools.utils.StringUtils;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class KeyCreateTextUIEvent implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {

        Player player = e.getPlayer();

        if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
            return;
        }


        if (ActiveChat.getActiveChat(player.getUniqueId()).equals("key")) {
            String key = PlainTextComponentSerializer.plainText().serialize(e.message()).toLowerCase();
            e.setCancelled(true);

            if (!StringUtils.containsOnlyLetters(key)) {
                player.sendMessage(Component.text("Please enter a key with only letters!").decoration(TextDecoration.ITALIC, false));
                return;
            }

            if (ItemUtils.containsNamespacedKey(ItemRegister.getPack(ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace()), new NamespacedKey(ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace(), key))) {
                player.sendMessage(Component.text("A key with that name already exists!").decoration(TextDecoration.ITALIC, false));
                return;
            }

            ActiveItemCreation.getActiveItem(player.getUniqueId()).setKey(key);
            ActiveChat.removeActiveChat(player.getUniqueId());

            ItemRegister.registerItem(ActiveItemCreation.getActiveItem(player.getUniqueId()), ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace());

            player.sendMessage(Component.text("Item " + ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespacedKey().asString() + " created!").decoration(TextDecoration.ITALIC, false));
            ActiveItemCreation.removeActiveItem(player.getUniqueId());
        }
    }
}