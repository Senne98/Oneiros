package com.senne.oneiros.mock;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.plugin.PluginMock;

public class MyPluginMock extends PluginMock {

    private @NotNull LifecycleEventManager<Plugin> lifecycleEventManager;

    public MyPluginMock() {
        this.lifecycleEventManager = new MyLifecycleEventManagerMock<>();
    }
}
