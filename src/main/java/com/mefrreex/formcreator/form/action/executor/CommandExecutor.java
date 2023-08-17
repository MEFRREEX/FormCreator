package com.mefrreex.formcreator.form.action.executor;

import cn.nukkit.Player;
import cn.nukkit.Server;

public class CommandExecutor implements Executor {

    private final boolean isPlayer;

    public CommandExecutor(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    @Override
    public void execute(Player player, String command) {
        if (isPlayer) {
            Server.getInstance().dispatchCommand(player, command);
        } else {
            Server.getInstance().dispatchCommand(Server.getInstance().getConsoleSender(), command);
        }
    }
    
}
