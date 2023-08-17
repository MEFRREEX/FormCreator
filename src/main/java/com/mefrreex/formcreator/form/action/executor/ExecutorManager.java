package com.mefrreex.formcreator.form.action.executor;

import com.mefrreex.formcreator.form.action.Type;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ExecutorManager {
    
    @Getter
    private static final Map<String, Executor> executors = new HashMap<>();

    public static void init() {
        register(Type.PLAYER_COMMAND, new CommandExecutor(true));
        register(Type.CONSOLE_COMMAND, new CommandExecutor(false));
        register(Type.MESSAGE, new MessageExecutor(true));
        register(Type.PLAYER_MESSAGE, new MessageExecutor(false));
        register(Type.OPEN, new OpenExecutor());
    }

    public static void register(String type, Executor executor) {
        executors.put(type, executor);
    }

    public static Executor getExecutor(String type) {
        return executors.get(type);
    }
}
