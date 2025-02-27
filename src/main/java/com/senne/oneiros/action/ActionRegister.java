package com.senne.oneiros.action;

import com.senne.oneiros.action.actions.Action;

import java.util.ArrayList;
import java.util.List;

public class ActionRegister {

    static List<Action> RegisteredActions = new ArrayList<>();

    public static void registerAction(Action action) {
        RegisteredActions.add(action);
    }
}
