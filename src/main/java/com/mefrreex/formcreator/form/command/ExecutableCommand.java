package com.mefrreex.formcreator.form.command;

import com.mefrreex.formcreator.form.Form;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class ExecutableCommand extends Command {

    private final Form form;

    public ExecutableCommand(FormCommand command, Form form) {
        super(command.getName(), command.getDescription());
        this.setAliases(command.getAliases());
        this.setPermission(command.getPermission());
        this.form = form;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (sender instanceof Player player) {
            form.send(player);
        }
        return true;
    }
    
}
