package com.senne.oneiros.UI;

import com.senne.oneiros.UI.itemCreation.AttributeUI;
import com.senne.oneiros.UI.itemCreation.CreationUI;
import com.senne.oneiros.UI.itemCreation.LoreUI;
import com.senne.oneiros.UI.itemCreation.chatUI.ActiveChat;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

public class CancelCommand implements BasicCommand {
    @Override
    public void execute(CommandSourceStack stack, String[] args) {
        if (!(stack.getSender() instanceof Player)) {
            return;
        }

        // If the player is in the name chat, remove them from it
        if (args.length == 1 && args[0].equals("name")) {
            Player player = (Player) stack.getSender();
            if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
                return;
            }

            if (ActiveChat.getActiveChat(player.getUniqueId()).equals("name")) {
                ActiveChat.removeActiveChat(player.getUniqueId());

                CreationUI ui = new CreationUI(player);
                player.openInventory(ui.getInventory());
                return;
            }
        }

        if (args.length == 1 && args[0].equals("lore")) {
            Player player = (Player) stack.getSender();
            if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
                return;
            }

            if (ActiveChat.getActiveChat(player.getUniqueId()).equals("lore")) {
                ActiveChat.removeActiveChat(player.getUniqueId());

                LoreUI ui = new LoreUI(player);
                player.openInventory(ui.getInventory());
                return;
            }
        }

        if (args.length == 1 && args[0].equals("cmd")) {
            Player player = (Player) stack.getSender();
            if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
                return;
            }

            if (ActiveChat.getActiveChat(player.getUniqueId()).equals("cmd")) {
                ActiveChat.removeActiveChat(player.getUniqueId());

                CreationUI ui = new CreationUI(player);
                player.openInventory(ui.getInventory());
                return;
            }
        }

        if (args.length == 1 && args[0].equals("armor")) {
            Player player = (Player) stack.getSender();
            if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
                return;
            }

            if (ActiveChat.getActiveChat(player.getUniqueId()).equals("armor")) {
                ActiveChat.removeActiveChat(player.getUniqueId());

                AttributeUI ui = new AttributeUI(player, 1);
                player.openInventory(ui.getInventory());
                return;
            }
        }

        if (args.length == 1 && args[0].equals("pack")) {
            Player player = (Player) stack.getSender();
            if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
                return;
            }

            if (ActiveChat.getActiveChat(player.getUniqueId()).equals("pack")) {
                ActiveChat.removeActiveChat(player.getUniqueId());

                CreationUI ui = new CreationUI(player);
                player.openInventory(ui.getInventory());
                return;
            }
        }

        if (args.length == 1 && args[0].equals("key")) {
            Player player = (Player) stack.getSender();
            if (ActiveChat.getActiveChat(player.getUniqueId()) == null) {
                return;
            }

            if (ActiveChat.getActiveChat(player.getUniqueId()).equals("key")) {
                ActiveChat.removeActiveChat(player.getUniqueId());

                CreationUI ui = new CreationUI(player);
                player.openInventory(ui.getInventory());
                return;
            }
        }
    }
}
