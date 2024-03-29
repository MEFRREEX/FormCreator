package com.mefrreex.formcreator.form.action.executor.impl;

import com.mefrreex.formcreator.form.action.executor.Executor;

import cn.nukkit.Player;
import cn.nukkit.Server;

public class CommandExecutor implements Executor {

    private final boolean isPlayer;

    public CommandExecutor(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    @Override
    @SuppressWarnings("all")
    public void execute(Player player, String command) {
        if (isPlayer) {
            Server.getInstance().dispatchCommand(player, command);
        } else {
            Server.getInstance().dispatchCommand(Server.getInstance().getConsoleSender(), command);
        }
    }   
}