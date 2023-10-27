package com.mefrreex.formcreator.form;

import cn.nukkit.Server;
import cn.nukkit.utils.Config;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.mefrreex.formcreator.FormCreator;
import com.mefrreex.formcreator.event.FormLoadEvent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class FormManager {
    
    private static final Map<String, Form> forms = new HashMap<>();

    private static final Gson gson = new Gson();

    /**
     * Get forms folder
     * @return File
     */
    public static File getFormsFolder() {
        return new File(FormCreator.get().getDataFolder() + "/forms");
    }

    /**
     * Get form by name
     * @param name Form name
     * @return Form
     */
    public static Form get(String name) {
        return forms.get(name);
    }

    /**
     * Check the existence of the form
     * @param name Form name
     */
    public static boolean exists(String name) {
        return forms.containsKey(name);
    }

    /**
     * Load form
     * @param name File
     * @param file Form name
     */
    public synchronized static void load(String name, File file) {
        try {
            Form form = gson.fromJson(new FileReader(file, Charset.forName("UTF-8")), Form.class);

            FormLoadEvent event = new FormLoadEvent(form);
            Server.getInstance().getPluginManager().callEvent(event);
            
            if (event.isCancelled()) {
                return;
            }

            form.init();
            forms.put(name, form);
        } catch(IOException | JsonSyntaxException | JsonIOException e) {
            throw new RuntimeException("Failed to load the form " + file.getName(), e);
        }
    }

    /**
     * Load all forms
     */
    public static void loadAll() {
        Config config = new Config(FormCreator.get().getDataFolder() + "/forms.yml");
        for (String name : config.getSection("forms").getKeys(false)) {
            String path = config.getString("forms." + name);
            File file = new File(getFormsFolder() + "/" + path);
            load(name, file);
        }
    }
}
