package com.mefrreex.formcreator.form;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandMap;
import com.mefrreex.formcreator.event.FormSendEvent;
import com.mefrreex.formcreator.form.action.Action;
import com.mefrreex.formcreator.form.command.ExecutableCommand;
import com.mefrreex.formcreator.form.command.FormCommand;
import com.mefrreex.formcreator.form.element.Button;
import ru.contentforge.formconstructor.form.SimpleForm;
import ru.contentforge.formconstructor.form.element.ImageType;
import ru.contentforge.formconstructor.form.handler.SimpleFormHandler;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
public class Form {
    
    @SerializedName("command") private FormCommand command;
    private transient ExecutableCommand executableCommand;

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

    /**
     * Initialize the form
     */
    public void init() {
        if (command != null && command.isEnable()) {
            this.registerCommand();
        }
    }

    private void registerCommand() {
        CommandMap map = Server.getInstance().getCommandMap();
        this.executableCommand = new ExecutableCommand(command, this);
        map.register("FormCreator", executableCommand);
    }


    /**
     * Add form content
     */
    public Form addContent(String line) {
        content.add(line);
        return this;
    }

    /**
     * Add form button
     * @param button Button
     */
    public Form addButton(Button button) {
        buttons.add(button);
        return this;
    }

    /**
     * Add an action to open the form
     */
    public Form addOpenAction(Action action) {
        openActions.add(action);
        return this;
    }

    /**
     * Add an action to close the form
     */
    public Form addCloseAction(Action action) {
        closeActions.add(action);
        return this;
    }

    /**
     * Create a form in FormConstructor
     * @param player Player
     * @return SimpleForm
     */
    public SimpleForm build(Player player) {
        SimpleForm form = new SimpleForm(title);
        
        openActions.forEach(action -> {
            action.execute(player);
        });

        for (String line : content) {
            form.addContent(line);
        }

        for (Button button : buttons) {
            SimpleFormHandler handler  = (pl, b) -> {
                button.getActions().forEach(action -> {
                    action.execute(pl);
                });
            };
            
            ImageType imageType = button.getImageType();
            String image = button.getImage();

            if (imageType != null && image != null) {
                form.addButton(button.getName(), imageType, image, handler);
            } else {
                form.addButton(button.getName(), handler);
            }
        }

        form.setNoneHandler(pl -> {
            closeActions.forEach(action -> {
                action.execute(player);
            });
        });

        return form;
    }

    /**
     * Send form to player
     * @param player Player 
     */
    public void send(Player player) {
        FormSendEvent event = new FormSendEvent(this, player);
        Server.getInstance().getPluginManager().callEvent(event);
            
        if (event.isCancelled()) {
            return;
        }

        this.build(player).send(player);
    }
}
