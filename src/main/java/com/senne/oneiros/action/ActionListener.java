package com.senne.oneiros.action;

import com.senne.oneiros.action.actions.Action;
import com.senne.oneiros.item.ItemRegister;

import java.util.Arrays;
import java.util.List;

public class ActionListener {

    public static boolean runAction(Action action) {
        ItemRegister.getItems().forEach((item) -> {
            List<ActionHandler> handlers = item.getActionHandlers();

            handlers.forEach(handler -> {
                Class handlerClass = handler.getClass();
                Arrays.stream(handlerClass.getMethods()).toList().forEach(method -> {
                    if (method.isAnnotationPresent(com.senne.oneiros.action.Action.class)) {
                        if (method.getParameterCount() == 1 && method.getParameterTypes()[0].equals(action.getClass())) {
                            try {
                                method.invoke(handler, action);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                });

            });
        });

        if (action instanceof Cancellable) {
            return ((Cancellable) action).isCancelled();
        }
        return true;
    }
}
