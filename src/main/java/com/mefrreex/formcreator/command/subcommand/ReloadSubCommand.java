package com.mefrreex.formcreator.command.subcommand;

import cn.nukkit.command.CommandSender;
import com.mefrreex.formcreator.command.BaseCommand;
import com.mefrreex.formcreator.command.BaseSubCommand;
import com.mefrreex.formcreator.form.FormManager;
import com.mefrreex.formcreator.utils.Language;

public class ReloadSubCommand extends BaseSubCommand {

    public ReloadSubCommand(BaseCommand command) {
        super("reload", "Reload forms", command);
        this.setPermission("formcreator.reload");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }

        try {
            FormManager.getForms().clear();
            FormManager.loadAll();
        } catch(Exception exception) {
            sender.sendMessage(Language.get("command-reload-error"));
            exception.printStackTrace();
            return false;
        }

        sender.sendMessage(Language.get("command-reload-success"));
        return true;
    } 
}