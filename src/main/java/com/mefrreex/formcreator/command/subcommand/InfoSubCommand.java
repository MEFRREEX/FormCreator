package com.mefrreex.formcreator.command.subcommand;

import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginDescription;
import com.mefrreex.formcreator.FormCreator;
import com.mefrreex.formcreator.command.BaseCommand;
import com.mefrreex.formcreator.command.BaseSubCommand;

public class InfoSubCommand extends BaseSubCommand {

    public InfoSubCommand(BaseCommand command) {
        super("info", "Info", command);
        this.setPermission("formcreator.info");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }

        PluginDescription description = FormCreator.get().getDescription();

        StringBuilder builder = new StringBuilder();
        builder.append("§l§a" + description.getName() + "§r");
        builder.append("\nPlugin version: " + description.getVersion());
        builder.append("\nAuthor: MEFRREEX §7[Discord: mefrreex]§r");
        builder.append("\nLink: §ahttps://github.com/MEFRREEX/FormCreator");

        sender.sendMessage(builder.toString());
        return true;
    } 
}