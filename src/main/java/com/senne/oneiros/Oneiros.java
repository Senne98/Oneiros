package com.senne.oneiros;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.senne.oneiros.UI.*;
import com.senne.oneiros.UI.chatUI.*;
import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.armor.ArmorAmountTextUIEvent;
import com.senne.oneiros.commands.CreateItemCmd;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public final class Oneiros extends JavaPlugin {

    static Plugin plugin;

    @Override
    public void onEnable() {

        // start init
        getServer().sendMessage(Component.text("[Oneiros] Starting initialization ..."));

        // Plugin startup logic
        plugin = this;

        // Registering the commands
        getServer().sendMessage(Component.text("[Oneiros] Loading commands ..."));

        LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            //OneirosCommand.register(commands.getDispatcher());
            /*commands.register(new LiteralCommandNode<>("cmd", new OneirosCommand(), (Predicate<Object>) o -> true,
                    new ArgumentCommandNode<>("createitem", StringArgumentType.string(), null, (Predicate<Object>) o -> true, null, null, false,
                            new SuggestionProvider<Object>() {
                                @Override
                                public CompletableFuture<Suggestions> getSuggestions(CommandContext<Object> context, SuggestionsBuilder builder)  {
                                    builder.suggest("createitem");
                                    return builder.buildFuture();
                                }
                             }), null, true));*/
            commands.getDispatcher().register(Commands.literal("cmd").then(new LiteralCommandNode<CommandSourceStack>("createitem", new CreateItemCmd(), (Predicate<CommandSourceStack>) o -> true, null, null, true).createBuilder()));
            //commands.getDispatcher().register(Commands.literal("cmd").then(new LiteralCommandNode<CommandSourceStack>("createitem2", new OneirosCommand(), (Predicate<CommandSourceStack>) o -> true, null, null, true).createBuilder()));
            //commands.register("createitem", "Open item creation ui.", new CreateCommand());
            commands.register("oneiroscancel", "Cancel the current action.", new CancelCommand());
        });

        // Registering the events
        getServer().sendMessage(Component.text("[Oneiros] Loading events ..."));

        getServer().getPluginManager().registerEvents(new CreationUIEvent(), this);
        getServer().getPluginManager().registerEvents(new NameTextUIEvent(), this);
        getServer().getPluginManager().registerEvents(new LoreUIEvent(), this);
        getServer().getPluginManager().registerEvents(new LoreTextUIEvent(), this);
        getServer().getPluginManager().registerEvents(new CmdTextUIEvent(), this);
        getServer().getPluginManager().registerEvents(new AttributeUIEvent(), this);
        getServer().getPluginManager().registerEvents(new EquipmentSlotsUIEvent(), this);
        getServer().getPluginManager().registerEvents(new ArmorAmountTextUIEvent(), this);
        getServer().getPluginManager().registerEvents(new PackSelectUIEvent(), this);
        getServer().getPluginManager().registerEvents(new PackCreateUIEvent(), this);

        // Registering Attributes
        getServer().sendMessage(Component.text("[Oneiros] Registering attributes ..."));
        Register.registerAttributes();

        // end init
        getServer().sendMessage(Component.text("[Oneiros] Initialization finished"));
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
