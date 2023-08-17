package com.mefrreex.formcreator.form;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.mefrreex.formcreator.FormCreator;

import cn.nukkit.utils.Config;

public class FormManager {
    
    private static final Map<String, Form> forms = new HashMap<>();

    private static final Gson gson = new Gson();

    public static File getFormsPath() {
        return new File(FormCreator.get().getDataFolder() + "/forms");
    }

    public static Form get(String name) {
        return forms.get(name);
    }

    public static boolean exists(String name) {
        return forms.containsKey(name);
    }

    public static void load(String name, File file) {
        try {
            Form form = gson.fromJson(new FileReader(file), Form.class);
            form.init();
            forms.put(name, form);
        } catch(JsonSyntaxException | JsonIOException | FileNotFoundException e) {
            throw new RuntimeException("Failed to load the form " + file.getName(), e);
        }
    }

    public static void loadAll() {
        Config config = new Config(FormCreator.get().getDataFolder() + "/forms.yml");
        for (String name : config.getSection("forms").getKeys(false)) {
            String path = config.getString("forms." + name);
            File file = new File(getFormsPath() + "/" + path);
            load(name, file);
        }
    }
}
