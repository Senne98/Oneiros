package com.senne.oneiros.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.senne.oneiros.item.Item;
import com.senne.oneiros.item.ItemRegister;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.EntitySelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GiveItemCmd  implements Command {

    @Override
    public int run(CommandContext cmd) {

        CommandSourceStack stack = (CommandSourceStack) cmd.getSource();

        if (!(stack.getSender() instanceof Player)) {
            stack.getSender().sendMessage("Only players can use this command.");
            return 0;
        }

        final EntitySelectorArgumentResolver entitySelectorArgumentResolver = (EntitySelectorArgumentResolver) cmd.getArgument("entity", EntitySelectorArgumentResolver.class);
        final List<Entity> entities;
        try {
            entities = entitySelectorArgumentResolver.resolve((CommandSourceStack) cmd.getSource());
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }

        NamespacedKey namespacedKey = (NamespacedKey) cmd.getArgument("item", NamespacedKey.class);
        if (!ItemRegister.getPacks().contains(namespacedKey.getNamespace()) || !ItemRegister.getPackContent(namespacedKey.getNamespace()).stream().anyMatch(item -> item.getNamespacedKey().equals(namespacedKey))) {
            stack.getSender().sendMessage(Component.text("Item " + namespacedKey.asString() + " does not exist.", NamedTextColor.RED));
            return 0;
        }

        int amount = 1;

        if (cmd.getInput().split(" ").length == 5) {
            amount = (int) cmd.getArgument("amount", Integer.class);
        }


        Item item = ItemRegister.getItem(namespacedKey);
        ItemStack itemStack = item.createItem(amount);

        for (Entity entity : entities) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                player.getInventory().addItem(itemStack);
            }
        }

        return Command.SINGLE_SUCCESS;
    }
}
