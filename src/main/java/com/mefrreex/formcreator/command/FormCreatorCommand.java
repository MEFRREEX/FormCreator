package com.mefrreex.formcreator.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import com.mefrreex.formcreator.command.subcommand.HelpSubCommand;
import com.mefrreex.formcreator.command.subcommand.InfoSubCommand;
import com.mefrreex.formcreator.command.subcommand.OpenSubCommand;

public class FormCreatorCommand extends BaseCommand {

    public FormCreatorCommand() {
        super("formcreator", "FormCreator");
        this.setPermission("formcreator");
        this.registerSubCommand(new OpenSubCommand(this));
        this.registerSubCommand(new InfoSubCommand(this));
        this.registerSubCommand(new HelpSubCommand(this));
    }

    @Override
    public boolean executeDefault(CommandSender sender, String label) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (sender instanceof Player) {
            this.sendUsage(sender, "/%s help".formatted(label));
        }
        return true;
    }
    
    public static void register() {
        FormCreatorCommand command = new FormCreatorCommand();
        Server.getInstance().getCommandMap().register("FormCreator", command);
    }
}
