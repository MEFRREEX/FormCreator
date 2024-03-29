package com.mefrreex.formcreator.form.adapter;

import cn.nukkit.Player;
import com.mefrreex.formcreator.form.adapter.handler.CloseHandler;

public interface IFormAdapter<T> {
    
    T setTitle(String title);

    T addContent(String addition);

    T addButton(IButtonAdapter<?> button);

    T setCloseHandler(CloseHandler handler);

    void send(Player player);
}