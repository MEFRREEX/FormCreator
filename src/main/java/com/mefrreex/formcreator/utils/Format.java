package com.mefrreex.formcreator.utils;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import com.mefrreex.formcreator.FormCreator;

public class Format {
    
    public static String format(String string, Player player) {
        String formatted = string.replace("%player%", player.getName());

        if (FormCreator.getPlaceholderAPI() != null) {
            formatted = FormCreator.getPlaceholderAPI().translateString(formatted, player);
        }
        
        return TextFormat.colorize(formatted);
    }
}
