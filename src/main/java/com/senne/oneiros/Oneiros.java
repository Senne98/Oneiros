package com.senne.oneiros;

import com.mojang.brigadier.tree.LiteralCommandNode;
import com.senne.oneiros.atributes.booleanAttibutes.BooleanUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUIEvent;
import com.senne.oneiros.commands.CancelCommand;
import com.senne.oneiros.UI.itemCreation.AttributeUIEvent;
import com.senne.oneiros.UI.itemCreation.CreationUIEvent;
import com.senne.oneiros.UI.itemCreation.LoreUIEvent;
import com.senne.oneiros.UI.itemCreation.PackSelectUIEvent;
import com.senne.oneiros.UI.itemCreation.chatUI.*;
import com.senne.oneiros.UI.itemGet.GetFromPackUIEvent;
import com.senne.oneiros.UI.itemGet.GetItemUIEvent;
import com.senne.oneiros.atributes.TextUIListener;
import com.senne.oneiros.commands.CreateItemCmd;
import com.senne.oneiros.commands.GetItemCmd;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Predicate;
import java.util.logging.Logger;

public class Oneiros extends JavaPlugin {

    static Plugin plugin;
    Logger logger;

    @Override
    public void onEnable() {

        // start init
        logger = getServer().getLogger();
        logger.info("[Oneiros] Starting Oneiros plugin...");

        // Plugin startup logic
        plugin = this;

        // Registering the commands
        logger.info("[Oneiros] Loading commands ...");

        LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.getDispatcher().register(Commands.literal("oneiros").then(new LiteralCommandNode<CommandSourceStack>("create", new CreateItemCmd(), (Predicate<CommandSourceStack>) o -> o.getSender().hasPermission("oneiros.oneiros.create") || o.getSender().isOp(), null, null, true).createBuilder()));
            commands.getDispatcher().register(Commands.literal("oneiros").then(new LiteralCommandNode<CommandSourceStack>("get", new GetItemCmd(), (Predicate<CommandSourceStack>) o -> o.getSender().hasPermission("oneiros.oneiros.get") || o.getSender().isOp(), null, null, true).createBuilder()));
            commands.register("oneiroscancel", "Cancel the current action.", new CancelCommand());
        });

        // Registering the events
        logger.info("[Oneiros] Loading events ...");

        getServer().getPluginManager().registerEvents(new CreationUIEvent(), this);
        getServer().getPluginManager().registerEvents(new NameTextUIEvent(), this);
        getServer().getPluginManager().registerEvents(new LoreUIEvent(), this);
        getServer().getPluginManager().registerEvents(new LoreTextUIEvent(), this);
        getServer().getPluginManager().registerEvents(new CmdTextUIEvent(), this);
        getServer().getPluginManager().registerEvents(new AttributeUIEvent(), this);
        getServer().getPluginManager().registerEvents(new EquipmentSlotsUIEvent(), this);
        getServer().getPluginManager().registerEvents(new PackSelectUIEvent(), this);
        getServer().getPluginManager().registerEvents(new PackCreateTextUIEvent(), this);
        getServer().getPluginManager().registerEvents(new GetFromPackUIEvent(), this);
        getServer().getPluginManager().registerEvents(new GetItemUIEvent(), this);
        getServer().getPluginManager().registerEvents(new KeyCreateTextUIEvent(), this);
        getServer().getPluginManager().registerEvents(new TextUIListener(), this);
        getServer().getPluginManager().registerEvents(new BooleanUIEvent(), this);

        // Registering Attributes
        logger.info("[Oneiros] Registering attributes ...");
        Register.registerAttributes();

        // end init
        logger.info("[Oneiros] Initialization finished");
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
