package com.mefrreex.formcreator.form.action.executor.impl;

import cn.nukkit.Player;
import com.mefrreex.formcreator.form.FormManager;
import com.mefrreex.formcreator.form.action.executor.Executor;

public class OpenFormExecutor implements Executor {

    @Override
    public void execute(Player player, String name) {
        if (!FormManager.exists(name)) {
            throw new RuntimeException("Form with name " + name + " not found");
        }
        FormManager.get(name).send(player);
    }
}
