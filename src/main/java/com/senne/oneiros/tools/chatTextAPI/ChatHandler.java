package com.senne.oneiros.tools.chatTextAPI;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class ChatHandler {

    // Stores the active chat for each player
    // Layout: ID (+ data)

    private static HashMap<UUID, ChatData> activeChats = new HashMap<>();

    public static void addActiveChat(UUID uuid, NamespacedKey key, String data, Runnable onCancel) {
        activeChats.put(uuid, new ChatData(key, data, onCancel));
    }

    public static void removeActiveChat(UUID uuid) {
        activeChats.remove(uuid);
    }

    public static void runCancel(UUID uuid) {
        activeChats.get(uuid).runOnCancel(uuid);
    }

    public static NamespacedKey getActiveChat(UUID uuid) {
        return activeChats.get(uuid).getKey();
    }

    public static String getData(UUID uuid) {
        return activeChats.get(uuid).getData();
    }

    public static boolean hasActiveChat(UUID uuid) {
        return activeChats.containsKey(uuid);
    }

    public static void buildCommand(Plugin plugin) {
        LifecycleEventManager<Plugin> manager = plugin.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();

            LiteralArgumentBuilder<CommandSourceStack> cancel = Commands.literal("chatinputcancel")
                    .requires(source -> {
                        if (!(source.getSender() instanceof Player)) return false;
                        return hasActiveChat(((Player) source.getSender()).getUniqueId());
                    })
                    .executes(context -> {
                        UUID uuid = ((Player) context.getSource().getSender()).getUniqueId();
                        ChatHandler.runCancel(uuid);
                        ChatHandler.removeActiveChat(uuid);
                        return Command.SINGLE_SUCCESS;
                    });

            commands.getDispatcher().register(cancel);
        });
    }
}

class ChatData {
    private NamespacedKey key;
    private String data;
    private Runnable onCancel;

    public ChatData(NamespacedKey key, String data, Runnable onCancel) {
        this.key = key;
        this.data = data;
        this.onCancel = onCancel;
    }

    public NamespacedKey getKey() {
        return key;
    }

    public String getData() {
        return data;
    }

    public void runOnCancel(UUID uuid) {
        if (onCancel != null) onCancel.run(Bukkit.getPlayer(uuid));
    }
}
