package com.mefrreex.formcreator;

import cn.nukkit.plugin.PluginBase;
import com.mefrreex.formcreator.command.FormCreatorCommand;
import com.mefrreex.formcreator.form.FormManager;
import com.mefrreex.formcreator.form.action.executor.ExecutorManager;
import com.mefrreex.formcreator.utils.Language;

import java.io.File;
import java.io.IOException;

public class FormCreator extends PluginBase {
    
    private static FormCreator instance;

    @Override
    public void onLoad() {
        FormCreator.instance = this;
        this.saveDefaultConfig();
        this.saveResource("forms.yml");
        this.setup();
    }

    @Override
    public void onEnable() {
        Language.init(this);
        ExecutorManager.init();
        FormManager.loadAll();
        FormCreatorCommand.register();
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

    /** Instance */
    public static FormCreator get() {
        return instance;
    }
}
