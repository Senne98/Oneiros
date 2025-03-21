package com.senne.oneiros.tools.chatTextAPI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ChatInputAPI {

    public static void stopInput(UUID uuid) {
        ChatHandler.removeActiveChat(uuid);
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, String data, Runnable onCancel) {
        ChatHandler.addActiveChat(p.getUniqueId(), namespacedKey, data, onCancel);
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, String data, Runnable onCancel, String message, String cancelMessage) {
        ChatHandler.addActiveChat(p.getUniqueId(), namespacedKey, data, onCancel);
        p.sendMessage(Component.text(message).decoration(TextDecoration.ITALIC, false));
        p.sendMessage(Component.text("[Cancel]")
                .hoverEvent(HoverEvent.showText(Component.text(cancelMessage).color(NamedTextColor.RED)))
                .decoration(TextDecoration.ITALIC, false)
                .color(NamedTextColor.RED)
                .clickEvent(ClickEvent.runCommand("/chatinputcancel")));
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, Runnable onCancel, String message, String cancelMessage) {
        newInput(p, namespacedKey, "", onCancel, message, cancelMessage);
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, String data, Runnable onCancel, String message) {
        newInput(p, namespacedKey, data, onCancel, message, "Cancel input!");
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, Runnable onCancel, String message) {
        newInput(p, namespacedKey, "", onCancel, message, "Cancel input!");
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, Runnable onCancel) {
        ChatHandler.addActiveChat(p.getUniqueId(), namespacedKey, "", onCancel);
        p.sendMessage(Component.text("[Cancel]")
                .hoverEvent(HoverEvent.showText(Component.text("Cancel input!").color(NamedTextColor.RED)))
                .decoration(TextDecoration.ITALIC, false)
                .color(NamedTextColor.RED)
                .clickEvent(ClickEvent.runCommand("/chatinputcancel")));
    }
}
