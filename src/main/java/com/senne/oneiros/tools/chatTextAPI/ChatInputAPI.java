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

    public static void cancelInput(UUID uuid) {
        ChatHandler.runCancel(uuid);
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, String data, AsyncRunnableCancel onCancel, AsyncRunnableSend onSend) {
        ChatHandler.addActiveChat(p.getUniqueId(), namespacedKey, data, onCancel, onSend);
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, String data, AsyncRunnableCancel onCancel, AsyncRunnableSend onSend, String message, String cancelMessage) {
        ChatHandler.addActiveChat(p.getUniqueId(), namespacedKey, data, onCancel, onSend);
        p.sendMessage(Component.text(message).decoration(TextDecoration.ITALIC, false));
        p.sendMessage(Component.text("[Cancel]")
                .hoverEvent(HoverEvent.showText(Component.text(cancelMessage).color(NamedTextColor.RED)))
                .decoration(TextDecoration.ITALIC, false)
                .color(NamedTextColor.RED)
                .clickEvent(ClickEvent.runCommand("/chatinputcancel")));
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, AsyncRunnableCancel onCancel, AsyncRunnableSend onSend, String message, String cancelMessage) {
        newInput(p, namespacedKey, "", onCancel, onSend, message, cancelMessage);
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, String data, AsyncRunnableCancel onCancel, AsyncRunnableSend onSend, String message) {
        newInput(p, namespacedKey, data, onCancel, onSend, message, "Cancel input!");
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, AsyncRunnableCancel onCancel, AsyncRunnableSend onSend, String message) {
        newInput(p, namespacedKey, "", onCancel, onSend, message, "Cancel input!");
    }

    public static void newInput(Player p, NamespacedKey namespacedKey, AsyncRunnableCancel onCancel, AsyncRunnableSend onSend) {
        ChatHandler.addActiveChat(p.getUniqueId(), namespacedKey, "", onCancel, onSend);
        p.sendMessage(Component.text("[Cancel]")
                .hoverEvent(HoverEvent.showText(Component.text("Cancel input!").color(NamedTextColor.RED)))
                .decoration(TextDecoration.ITALIC, false)
                .color(NamedTextColor.RED)
                .clickEvent(ClickEvent.runCommand("/chatinputcancel")));
    }
}
