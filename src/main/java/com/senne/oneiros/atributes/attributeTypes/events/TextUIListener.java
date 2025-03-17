package com.senne.oneiros.atributes.attributeTypes.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.chatUI.ActiveChat;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class TextUIListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncChatEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        if (ActiveChat.getActiveChat(uuid) == null) {
            return;
        }

        if (ActiveChat.getActiveChat(uuid).startsWith("equipmentIntAttribute:")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                EquipmentIntTextEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(uuid).startsWith("equipmentDoubleAttribute:")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                EquipmentDoubleTextEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }
    }
}
