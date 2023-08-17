package com.mefrreex.formcreator.form.action.executor;

import cn.nukkit.Player;

public class MessageExecutor implements Executor {

    private final boolean isToPlayer;

    public MessageExecutor(boolean isPlayer) {
        this.isToPlayer = isPlayer;
    }

    @Override
    public void execute(Player player, String message) {
        if (isToPlayer) {
            player.sendMessage(message);
        } else {
            player.chat(message);
        }
    }
    
}
