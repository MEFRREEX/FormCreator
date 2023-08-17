package com.mefrreex.formcreator.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.mefrreex.formcreator.form.FormManager;

public class FormCreatorCommand extends Command {

    public FormCreatorCommand() {
        super("formcreator", "FormCreator");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args[0].equals("open")) {
            FormManager.get(args[1]).send((Player) sender);
        }
        return true;
    }
    
}
