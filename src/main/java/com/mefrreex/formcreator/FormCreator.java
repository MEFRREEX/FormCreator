package com.mefrreex.formcreator;

import cn.nukkit.plugin.PluginBase;

import java.io.File;
import java.io.IOException;

import com.mefrreex.formcreator.command.FormCreatorCommand;
import com.mefrreex.formcreator.form.FormManager;
import com.mefrreex.formcreator.form.action.executor.ExecutorManager;

public class FormCreator extends PluginBase {
    
    private static FormCreator instance;

    @Override
    public void onLoad() {
        FormCreator.instance = this;
        this.saveResource("forms.yml");
        this.setup();
    }

    @Override
    public void onEnable() {
        this.getServer().getCommandMap().register("FormCreator", new FormCreatorCommand());
        ExecutorManager.init();
        FormManager.loadAll();
    }

    private void setup() {
        File firstStartFile = new File(getDataFolder() + "/.configured");
        if (!firstStartFile.exists()) {
            this.saveResource("forms/example.json");
            try {
                firstStartFile.createNewFile();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public static FormCreator get() {
        return instance;
    }
}
