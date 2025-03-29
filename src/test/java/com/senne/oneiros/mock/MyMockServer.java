package com.senne.oneiros.mock;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;

public class MyMockServer extends ServerMock {

    @Override
    public void sendMessage(final @NotNull Component message) {
        getLogger().info(message.toString());
    }
}
