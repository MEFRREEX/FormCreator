package com.mefrreex.formcreator.utils;

import java.util.Map;

public class Utils {
    
    public static String replace(String string, Map<String, String> placeholders) {
        for (var entry : placeholders.entrySet()) {
            string = string.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return string;
    }
}
