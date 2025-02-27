package com.senne.oneiros.action;

public interface Cancellable {

    boolean isCancelled();
    void setCancelled(boolean cancelled);
}
