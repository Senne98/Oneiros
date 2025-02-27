package com.senne.oneiros.UI;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

public class CreateCommand implements BasicCommand {

    @Override
    public void execute(CommandSourceStack stack, String[] args) {
        if (!(stack.getSender() instanceof Player)) {
            stack.getSender().sendMessage("Only players can use this command.");
            return;
        }

        if (args.length == 0) {

            Player player = (Player) stack.getSender();

            CreationUI ui = new CreationUI(player);
            player.openInventory(ui.getInventory());
        }
    }
}
