package com.senne.oneiros.UI.chatUI;

import com.senne.oneiros.UI.CreationUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.ItemRegister;
import com.senne.oneiros.tools.StringUtils;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class KeyCreateUIEvent implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {

        Player player = e.getPlayer();

        if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
            return;
        }


        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).equals("key")) {
            String key = PlainTextComponentSerializer.plainText().serialize(e.message());
            e.setCancelled(true);

            if (StringUtils.containsOnlyLetters(key)) {
                player.sendMessage(Component.text("Please enter a key with only letters!").decoration(TextDecoration.ITALIC, false));
                return;
            }

            if (ItemRegister.getPack(ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace()).containsKey(new NamespacedKey(ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace(), key))) {
                player.sendMessage(Component.text("A key with that name already exists!").decoration(TextDecoration.ITALIC, false));
                return;
            }

            ActiveItemCreation.getActiveItem(player.getUniqueId()).setKey(key);
            ActiveChat.removeActiveChat(player.getUniqueId());

            ItemRegister.registerItem(ActiveItemCreation.getActiveItem(player.getUniqueId()), ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace());
            ActiveItemCreation.removeActiveItem(player.getUniqueId());
        }
    }
}