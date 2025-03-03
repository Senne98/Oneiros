package com.senne.oneiros.UI.itemCreation.chatUI;

import com.senne.oneiros.UI.itemCreation.PackSelectUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.ItemRegister;
import com.senne.oneiros.tools.StringUtils;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PackCreateTextUIEvent implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {

        Player player = e.getPlayer();

        if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
            return;
        }


        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).equals("pack")) {
            e.setCancelled(true);

            String pack = PlainTextComponentSerializer.plainText().serialize(e.message());
            pack = pack.toLowerCase();
            if (!StringUtils.containsOnlyLetters(pack)) {
                player.sendMessage(Component.text("Please enter a name with only letters!").decoration(TextDecoration.ITALIC, false));
                return;
            }

            if (ItemRegister.getPacks().contains(pack)) {
                player.sendMessage(Component.text("A pack with that name already exists!").decoration(TextDecoration.ITALIC, false));
                return;
            }

            pack = pack.replaceAll(" ", "");

            ActiveItemCreation.getActiveItem(player.getUniqueId()).setNamespace(pack);
            ItemRegister.registerPack(pack);
            ActiveChat.removeActiveChat(player.getUniqueId());

            ActiveItemCreation.getActiveItem(player.getUniqueId()).setNamespace(pack);

            player.sendMessage(Component.text("Enter the key of the item in the chat.").decoration(TextDecoration.ITALIC, false));
            player.sendMessage(Component.text("[Cancel]")
                    .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the key input.").color(NamedTextColor.RED)))
                    .decoration(TextDecoration.ITALIC, false)
                    .color(NamedTextColor.RED)
                    .clickEvent(ClickEvent.runCommand("/oneiroscancel key")));

            PackSelectUI ui = new PackSelectUI(player, 1);
            player.openInventory(ui.getInventory());
        }
    }
}