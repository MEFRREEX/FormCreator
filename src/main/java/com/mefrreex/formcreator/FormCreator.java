package com.mefrreex.formcreator;

import cn.nukkit.plugin.PluginBase;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;
import com.mefrreex.formcreator.command.FormCreatorCommand;
import com.mefrreex.formcreator.form.FormManager;
import com.mefrreex.formcreator.form.action.executor.ExecutorManager;
import com.mefrreex.formcreator.form.adapter.FormAdapterManager;
import com.mefrreex.formcreator.metrics.Metrics;
import com.mefrreex.formcreator.utils.Language;

import java.io.File;
import java.io.IOException;

public class FormCreator extends PluginBase {
    
    private static FormCreator instance;

    private static PlaceholderAPI placeholderApi;

    @Override
    public void onLoad() {
        FormCreator.instance = this;
        this.saveDefaultConfig();
        this.saveResource("forms.yml");
        this.setup();
        this.metrics();
    }

    @Override
    public void onEnable() {
        Language.init(this);
        ExecutorManager.init();
        FormAdapterManager.init(this);
        FormManager.loadAll();
        FormCreatorCommand.register();
        this.loadPlaceholders();
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

    private void loadPlaceholders() {
        if (this.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            placeholderApi = PlaceholderAPI.getInstance();
        }
    }

    public void metrics() {
        int pluginId = 19705;
        Metrics metrics = new Metrics(this, pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("server_movement", () -> String.valueOf(this.getConfig().getBoolean("PowerNukkiX-movement-server"))));
        metrics.addCustomChart(new Metrics.SimplePie("nukkit_version", () -> this.getServer().getNukkitVersion()));
    }

    /** Instance */
    public static FormCreator get() {
        return instance;
    }

    public static PlaceholderAPI getPlaceholderAPI() {
        return placeholderApi;
    }
}
