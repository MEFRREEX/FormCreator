package com.mefrreex.formcreator.form.adapter;

import cn.nukkit.plugin.Plugin;
import com.mefrreex.formcreator.FormCreator;
import com.mefrreex.formcreator.form.adapter.v1.FormV1AdapterFactory;
import com.mefrreex.formcreator.form.adapter.v2.FormV2AdapterFactory;
import com.mefrreex.formcreator.utils.VersionUtils;
import com.mefrreex.formcreator.utils.VersionUtils.VersionEntry;
import lombok.Getter;
import lombok.Setter;

public class FormAdapterManager {
    
    @Getter @Setter
    private static IFormAdapterFactory factory;

    public static void init(FormCreator main) {
        Plugin formConstructor = main.getServer().getPluginManager().getPlugin("FormConstructor");

        if (formConstructor == null) {
            throw new RuntimeException("Plugin FormConstructor not found");
        }

        VersionEntry version = VersionUtils.fromString(formConstructor.getDescription().getVersion());
        FormAdapterManager.setFactory(
            switch(version.major()) {
                case 1 -> new FormV1AdapterFactory();
                case 2 -> new FormV2AdapterFactory();
                default -> throw new RuntimeException("Your server is using an unsupported version of the FormConstructor plugin");
            }
        );
    }
}
