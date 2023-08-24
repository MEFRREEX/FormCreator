package com.mefrreex.formcreator.event;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import com.mefrreex.formcreator.form.Form;
import lombok.Getter;

@Getter
public abstract class FormEvent extends Event implements Cancellable {
    
    private final Form form;

    public FormEvent(Form form) {
        this.form = form;
    }
}
