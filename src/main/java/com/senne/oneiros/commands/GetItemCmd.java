package com.senne.oneiros.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.senne.oneiros.UI.itemGet.GetFromPackUI;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

public class GetItemCmd implements Command {

    @Override
    public int run(CommandContext cmd) {

        CommandSourceStack stack = (CommandSourceStack) cmd.getSource();

        if (!(stack.getSender() instanceof Player)) {
            stack.getSender().sendMessage("Only players can use this command.");
            return 0;
        }

        Player player = (Player) stack.getSender();

        GetFromPackUI ui = new GetFromPackUI(1);
        player.openInventory(ui.getInventory());

        return 0;
    }
}
