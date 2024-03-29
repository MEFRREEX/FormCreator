package com.mefrreex.formcreator.form.action.executor;

import cn.nukkit.Player;
import com.mefrreex.formcreator.form.FormManager;

public class OpenExecutor implements Executor {

    @Override
    public void execute(Player player, String name) {
        if (!FormManager.exists(name)) {
            throw new RuntimeException("Form with name " + name + " not found");
        }
        FormManager.get(name).send(player);
    }
}
