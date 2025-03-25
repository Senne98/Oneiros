package com.senne.oneiros;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.senne.oneiros.UI.itemCreation.events.*;
import com.senne.oneiros.UI.itemCreation.inventories.LoreUIEvent;
import com.senne.oneiros.atributes.attributeTypes.Attribute;
import com.senne.oneiros.atributes.attributeTypes.AttributeRegister;
import com.senne.oneiros.atributes.attributeTypes.events.AttributeChatInputListener;
import com.senne.oneiros.atributes.attributeTypes.events.BooleanUIEvent;
import com.senne.oneiros.atributes.attributeTypes.events.EquipmentSlotsUIEvent;
import com.senne.oneiros.UI.itemGet.events.GetFromPackUIEvent;
import com.senne.oneiros.UI.itemGet.events.GetItemUIEvent;
import com.senne.oneiros.commands.CreateItemCmd;
import com.senne.oneiros.commands.GetItemCmd;
import com.senne.oneiros.commands.GiveItemCmd;
import com.senne.oneiros.item.ItemRegister;
import com.senne.oneiros.item.Pack;
import com.senne.oneiros.tools.argumentTypes.ItemKeyArgumentType;
import com.senne.oneiros.tools.chatTextAPI.ChatHandler;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.EntitySelectorArgumentResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

import static com.senne.oneiros.storage.Data.load;
import static com.senne.oneiros.storage.Data.save;

public class Oneiros extends JavaPlugin {

    private static Plugin plugin;
    private static Logger logger;
    private static boolean mocked;

    @Override
    public void onEnable() {

        // start init
        logger = getLogger();
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

        // Loading packs
        logger.info("[Oneiros] Loading packs ...");
        load();

        // end init
        logger.info("[Oneiros] Initialization finished");
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info(getDataFolder().getAbsolutePath());

        logger.info("[Oneiros] Saving packs ...");
        ItemRegister.getPacks().forEach(s -> {
            Pack pack = ItemRegister.getPack(s);
            if (!pack.isSaved()) {
                try {
                    save(s);
                } catch (IOException e) {
                    logger.warning("[Oneiros] Failed to save pack " + s);
                    logger.throwing("Oneiros", "onDisable", e);
                }
            }
        });

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
            oneiros.then(Commands.literal("give").requires(source -> source.getSender().hasPermission("oneiros.oneiros.get") || source.getSender().isOp())
                    .then(Commands.argument("entity", ArgumentTypes.entities())
                            .then(Commands.argument("item", new ItemKeyArgumentType())
                                    .then(Commands.argument("amount", IntegerArgumentType.integer(1)).executes(new GiveItemCmd())))));

            commands.getDispatcher().register(oneiros);

            oneiros = Commands.literal("oneiros");
            oneiros.then(Commands.literal("create").requires(source -> source.getSender().hasPermission("oneiros.oneiros.create") || source.getSender().isOp()).executes(new CreateItemCmd()));
            oneiros.then(Commands.literal("get").requires(source -> source.getSender().hasPermission("oneiros.oneiros.get") || source.getSender().isOp()).executes(new GetItemCmd()));
            oneiros.then(Commands.literal("give").requires(source -> source.getSender().hasPermission("oneiros.oneiros.get") || source.getSender().isOp())
                    .then(Commands.argument("entity", ArgumentTypes.entities())
                            .then(Commands.argument("item", new ItemKeyArgumentType())
                                    .executes(new GiveItemCmd()))));

            commands.getDispatcher().register(oneiros);
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
        getServer().getPluginManager().registerEvents(new BooleanUIEvent(), this);
        getServer().getPluginManager().registerEvents(new ItemCreationChatInputListener(), this);
        getServer().getPluginManager().registerEvents(new AuthorsUIEvent(), this);
        getServer().getPluginManager().registerEvents(new PackCreationUIEvent(), this);
        getServer().getPluginManager().registerEvents(new AttributeChatInputListener(), this);
    }

    public static boolean isMocked() {
        return mocked;
    }
}
