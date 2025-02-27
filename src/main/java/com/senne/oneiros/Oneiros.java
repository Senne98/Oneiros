package com.senne.oneiros;

import com.senne.oneiros.UI.*;
import com.senne.oneiros.UI.chatUI.*;
import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentSlotsUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.armor.ArmorAmountTextUIEvent;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

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
            commands.register("createitem", "Open item creation ui.", new CreateCommand());
            commands.register("cancel", "Cancel the current action.", new CancelCommand());
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
