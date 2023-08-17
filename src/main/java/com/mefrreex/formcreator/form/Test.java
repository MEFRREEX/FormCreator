package com.mefrreex.formcreator.form;

import com.google.gson.Gson;
import com.mefrreex.formcreator.form.action.Action;
import com.mefrreex.formcreator.form.action.Type;
import com.mefrreex.formcreator.form.command.FormCommand;
import com.mefrreex.formcreator.form.element.Button;

public class Test {
    
    public static void main(String[] args) {
        Form form = new Form("Example Form");
        
        FormCommand command = new FormCommand("example", "Example command");
        command.setAliases(new String[]{"example1"});
        command.setPermission("formcreator.example");
        form.setCommand(command);
    
        form.addContent("Content line");

        Button button = new Button("Button");
        button.addAction(new Action(Type.PLAYER_COMMAND, "say Test"));
        form.addButton(button);

        form.addOpenAction(new Action(Type.CONSOLE_COMMAND, "say {player} Form opened!"));
        form.addCloseAction(new Action(Type.CONSOLE_COMMAND, "say {player} Form closed!"));

        System.out.println(new Gson().toJson(form));
    }
}
