package com.mefrreex.formcreator.form;

import cn.nukkit.Player;
import cn.nukkit.Server;
import com.mefrreex.formcreator.event.FormSendEvent;
import com.mefrreex.formcreator.form.action.Action;
import com.mefrreex.formcreator.form.adapter.FormAdapterManager;
import com.mefrreex.formcreator.form.adapter.IFormAdapter;
import com.mefrreex.formcreator.form.adapter.IFormAdapterFactory;
import com.mefrreex.formcreator.form.adapter.ImageTypeAdapter;
import com.mefrreex.formcreator.form.adapter.handler.ButtonHandler;
import com.mefrreex.formcreator.form.command.FormCommandExecutor;
import com.mefrreex.formcreator.form.command.FormCommand;
import com.mefrreex.formcreator.form.element.Button;
import com.mefrreex.formcreator.utils.Format;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter 
@Setter
@ToString
public class Form {
    
    private FormCommand command;
    private transient FormCommandExecutor executableCommand;

    private String title = "";
    private List<String> content = new ArrayList<>();
    private List<Button> buttons = new ArrayList<>();

    private List<Action> openActions = new ArrayList<>();
    private List<Action> closeActions = new ArrayList<>();

    private static final IFormAdapterFactory formFactory = FormAdapterManager.getFactory();

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
        if (this.command != null && command.isEnable()) {
            this.executableCommand = new FormCommandExecutor(command, this);
            Server.getInstance().getCommandMap().register("FormCreator", executableCommand);
        }
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
    public IFormAdapter<?> build(Player player) {
        IFormAdapter<?> form = formFactory.createForm(Format.format(title, player));

        for (String line : content) {
            form.addContent(Format.format(line, player));
        }

        for (Button button : buttons) {
            ButtonHandler handler  = (pl) -> {
                button.getActions().forEach(action -> {
                    action.execute(pl);
                });
            };
            
            ImageTypeAdapter imageType = button.getImageType();
            String imagePath = button.getImage();

            String name = Format.format(button.getName(), player);
            if (imageType != null && imagePath != null) {
                form.addButton(formFactory.createButton(name, imageType, imagePath, handler));
            } else {
                form.addButton(formFactory.createButton(name, ImageTypeAdapter.PATH, "", handler));
            }
        }

        form.setCloseHandler(pl -> {
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

        openActions.forEach(action -> {
            action.execute(player);
        });

        this.build(player).send(player);
    }
}
