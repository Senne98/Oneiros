package com.senne.oneiros.mock;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import io.papermc.paper.plugin.lifecycle.event.handler.LifecycleEventHandler;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEventType;
import org.mockbukkit.mockbukkit.plugin.lifecycle.event.LifecycleEventManagerMock;

public class MyLifecycleEventManagerMock<O extends LifecycleEventOwner> extends LifecycleEventManagerMock {

    public void registerEventHandler(final LifecycleEventType eventType, final LifecycleEventHandler eventHandler) {
        this.registerEventHandler(eventType.newHandler(eventHandler));
    }

    }
