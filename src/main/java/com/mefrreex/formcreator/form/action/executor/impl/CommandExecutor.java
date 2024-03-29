package com.mefrreex.formcreator.form.action.executor.impl;

import cn.nukkit.Player;
import cn.nukkit.Server;
import com.mefrreex.formcreator.form.action.executor.Executor;

public class CommandExecutor implements Executor {

    private final boolean isPlayer;

    public CommandExecutor(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    @Override
    public void execute(Player player, String command) {
        if (isPlayer) {
            Server.getInstance().executeCommand(player, command);
        } else {
            Server.getInstance().executeCommand(Server.getInstance().getConsoleSender(), command);
        }
    }   
}