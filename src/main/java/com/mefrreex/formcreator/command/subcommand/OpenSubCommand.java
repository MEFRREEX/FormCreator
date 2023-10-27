package com.mefrreex.formcreator.command.subcommand;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import com.mefrreex.formcreator.command.BaseCommand;
import com.mefrreex.formcreator.command.BaseSubCommand;
import com.mefrreex.formcreator.form.Form;
import com.mefrreex.formcreator.form.FormManager;
import com.mefrreex.formcreator.utils.Language;

public class OpenSubCommand extends BaseSubCommand {

    public OpenSubCommand(BaseCommand command) {
        super("open", "Open form", command);
        this.setPermission("formcreator.open");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }

        if (args.length < 2) {
            this.sendUsage(sender, "/%s %s <form> <player>".formatted(commandLabel, label));
            return false;
        }

        Form form = FormManager.get(args[0]);
        if (form == null) {
            sender.sendMessage(Language.get("command-form-not-found", args[0]));
            return false;
        }

        Player player = Server.getInstance().getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage(Language.get("command-player-not-found", args[1]));
            return false; 
        }

        form.send(player);
        sender.sendMessage(Language.get("command-open-opened", args[0], player.getName()));
        return true;
    } 
}