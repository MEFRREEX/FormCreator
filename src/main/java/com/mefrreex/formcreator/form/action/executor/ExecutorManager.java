package com.mefrreex.formcreator.form.action.executor;

import com.mefrreex.formcreator.form.action.ActionType;
import com.mefrreex.formcreator.form.action.executor.impl.CommandExecutor;
import com.mefrreex.formcreator.form.action.executor.impl.MessageExecutor;
import com.mefrreex.formcreator.form.action.executor.impl.OpenChestExecutor;
import com.mefrreex.formcreator.form.action.executor.impl.OpenFormExecutor;
import com.mefrreex.formcreator.form.action.executor.impl.CommandExecutor.CommandExecuteType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ExecutorManager {
    
    @Getter
    private static final Map<String, Executor> executors = new HashMap<>();

    @SuppressWarnings("deprecation")
    public static void init() {
        register(ActionType.PLAYER_COMMAND, new CommandExecutor(CommandExecuteType.PLAYER));
        register(ActionType.CONSOLE_COMMAND, new CommandExecutor(CommandExecuteType.CONSOLE));
        register(ActionType.OPERATOR_COMMAND, new CommandExecutor(CommandExecuteType.OPERATOR));
        register(ActionType.MESSAGE, new MessageExecutor(true));
        register(ActionType.PLAYER_MESSAGE, new MessageExecutor(false));
        register(ActionType.OPEN, new OpenFormExecutor(true)); // register executor for backward compatibility
        register(ActionType.OPEN_FORM, new OpenFormExecutor(false));
        register(ActionType.OPEN_CHEST, new OpenChestExecutor());
    }

    public static void register(String type, Executor executor) {
        executors.put(type, executor);
    }

    public static Executor getExecutor(String type) {
        return executors.get(type);
    }
}
