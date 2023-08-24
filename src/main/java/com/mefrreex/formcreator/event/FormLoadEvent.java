package com.mefrreex.formcreator.event;

import cn.nukkit.event.HandlerList;
import com.mefrreex.formcreator.form.Form;

public class FormLoadEvent extends FormEvent {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    public FormLoadEvent(Form form) {
        super(form);
    }
}
