package com.mefrreex.formcreator.form.action.executor.impl;

import cn.nukkit.Player;
import com.mefrreex.chestcreator.chest.ChestManager;
import com.mefrreex.formcreator.form.action.executor.Executor;

public class OpenChestExecutor implements Executor {

    @Override
    public void execute(Player player, String name) {
        try {
            if (!ChestManager.exists(name)) {
                throw new RuntimeException("Chest with name " + name + " not found");
            }
            ChestManager.get(name).send(player);
        } catch (NoClassDefFoundError e) {
            throw new RuntimeException("ChestCreator plugin not found", e);
        }
    }
}
