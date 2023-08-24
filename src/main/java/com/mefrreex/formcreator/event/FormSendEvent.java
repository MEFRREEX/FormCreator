package com.mefrreex.formcreator.event;

import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import lombok.Getter;

import com.mefrreex.formcreator.form.Form;

@Getter
public class FormSendEvent extends FormEvent {

    private final Player player;

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    public FormSendEvent(Form form, Player player) {
        super(form);
        this.player = player;
    }
}
