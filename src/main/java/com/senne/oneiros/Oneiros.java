package com.senne.oneiros;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.senne.oneiros.UI.itemCreation.events.*;
import com.senne.oneiros.UI.itemCreation.inventories.LoreUIEvent;
import com.senne.oneiros.atributes.attributeTypes.events.BooleanUIEvent;
import com.senne.oneiros.atributes.attributeTypes.events.EquipmentSlotsUIEvent;
import com.senne.oneiros.commands.CancelCommand;
import com.senne.oneiros.UI.itemGet.events.GetFromPackUIEvent;
import com.senne.oneiros.UI.itemGet.events.GetItemUIEvent;
import com.senne.oneiros.atributes.attributeTypes.events.TextUIListener;
import com.senne.oneiros.commands.CreateItemCmd;
import com.senne.oneiros.commands.GetItemCmd;
import com.senne.oneiros.tools.chatTextAPI.ChatHandler;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Oneiros extends JavaPlugin {

    private static Plugin plugin;
    private static Logger logger;
    private static boolean mocked;

    @Override
    public void onEnable() {

        // start init
        logger = getServer().getLogger();
        logger.info("[Oneiros] Starting Oneiros plugin...");

        // Check if the plugin is running in a test environment
        try {
            Class.forName("org.mockbukkit.mockbukkit.MockBukkit");
            logger.info("[Oneiros] Running in test environment");
            mocked = true;
        } catch (ClassNotFoundException e) {
            mocked = false;

        }

        // Plugin startup logic
        plugin = this;

        // Registering the commands
        logger.info("[Oneiros] Loading commands ...");
        if (!mocked) buildCommands();
        ChatHandler.setUp(plugin);

        // Registering the events
        logger.info("[Oneiros] Loading events ...");
        registerEvents();

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

    private void buildCommands() {
        LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();

            LiteralArgumentBuilder<CommandSourceStack> oneiros = Commands.literal("oneiros");
            oneiros.then(Commands.literal("create").requires(source -> source.getSender().hasPermission("oneiros.oneiros.create") || source.getSender().isOp()).executes(new CreateItemCmd()));
            oneiros.then(Commands.literal("get").requires(source -> source.getSender().hasPermission("oneiros.oneiros.get") || source.getSender().isOp()).executes(new GetItemCmd()));

            commands.getDispatcher().register(oneiros);

            commands.register("oneiroscancel", "Cancel the current action.", new CancelCommand());
        });
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new ItemCreationUIEvent(), this);
        getServer().getPluginManager().registerEvents(new LoreUIEvent(), this);
        getServer().getPluginManager().registerEvents(new AttributeUIEvent(), this);
        getServer().getPluginManager().registerEvents(new EquipmentSlotsUIEvent(), this);
        getServer().getPluginManager().registerEvents(new PackSelectUIEvent(), this);
        getServer().getPluginManager().registerEvents(new GetFromPackUIEvent(), this);
        getServer().getPluginManager().registerEvents(new GetItemUIEvent(), this);
        getServer().getPluginManager().registerEvents(new TextUIListener(), this);
        getServer().getPluginManager().registerEvents(new BooleanUIEvent(), this);
        getServer().getPluginManager().registerEvents(new ItemCreationChatInputListener(), this);
        getServer().getPluginManager().registerEvents(new AuthorsUIEvent(), this);
        getServer().getPluginManager().registerEvents(new PackCreationUIEvent(), this);
    }

    public static boolean isMocked() {
        return mocked;
    }
}
