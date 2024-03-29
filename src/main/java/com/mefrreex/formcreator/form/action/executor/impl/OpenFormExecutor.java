package com.mefrreex.formcreator.form.action.executor.impl;

import cn.nukkit.Player;
import com.mefrreex.formcreator.FormCreator;
import com.mefrreex.formcreator.form.FormManager;
import com.mefrreex.formcreator.form.action.executor.Executor;

public class OpenFormExecutor implements Executor {

    private final boolean oldType;

    public OpenFormExecutor(boolean oldType) {
        this.oldType = oldType;
    }

    @Override
    public void execute(Player player, String name) {
        if (oldType) {
            FormCreator.get().getLogger().warning("Uses the deprecated executor type \"OPEN\". Change it to \"OPEN_FORM\"");
        }

        if (!FormManager.exists(name)) {
            throw new RuntimeException("Form with name " + name + " not found");
        }

        FormManager.get(name).send(player);
    }
}
