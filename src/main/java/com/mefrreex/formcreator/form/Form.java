package com.mefrreex.formcreator.form;

import com.mefrreex.formcreator.form.action.Action;
import com.mefrreex.formcreator.form.command.ExecutableCommand;
import com.mefrreex.formcreator.form.command.FormCommand;
import com.mefrreex.formcreator.form.element.Button;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandMap;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.contentforge.formconstructor.form.SimpleForm;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
public class Form {
    
    @SerializedName("command") private FormCommand command;

    @SerializedName("title") private String title = "";
    @SerializedName("content") private List<String> content = new ArrayList<>();
    @SerializedName("buttons") private List<Button> buttons = new ArrayList<>();

    @SerializedName("openActions") private List<Action> openActions = new ArrayList<>();
    @SerializedName("closeActions") private List<Action> closeActions = new ArrayList<>();

    public Form() {
        this("");
    }

    public Form(String title) {
        this.title = title; 
    }

    public void init() {
        if (command != null && command.isEnable()) {
            this.registerCommand();
        }
    }

    private void registerCommand() {
        CommandMap map = Server.getInstance().getCommandMap();
        map.register("FormCreator", new ExecutableCommand(command, this));
    }


    public Form addContent(String line) {
        content.add(line);
        return this;
    }

    public Form addButton(Button button) {
        buttons.add(button);
        return this;
    }

    public Form addOpenAction(Action action) {
        openActions.add(action);
        return this;
    }

    public Form addCloseAction(Action action) {
        closeActions.add(action);
        return this;
    }

    public SimpleForm buildForm(Player player) {
        SimpleForm form = new SimpleForm(title);
        
        openActions.forEach(action -> {
            action.execute(player);
        });

        for (String line : content) {
            form.addContent(line);
        }

        for (Button button : buttons) {
            form.addButton(button.getName(), (pl, b) -> {
                button.getActions().forEach(action -> {
                    action.execute(pl);
                });
            });
        }

        form.setNoneHandler(pl -> {
            closeActions.forEach(action -> {
                action.execute(player);
            });
        });

        return form;
    }

    public void send(Player player) {
        this.buildForm(player).send(player);
    }
}
