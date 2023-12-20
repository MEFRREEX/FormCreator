package com.mefrreex.formcreator.form.adapter;

import com.mefrreex.formcreator.form.adapter.handler.NoneHandler;

import cn.nukkit.Player;

public interface IFormAdapter<T> {
    
    T setTitle(String title);
    T addContent(String addition);
    T addButton(IButtonAdapter<?> button);
    T setCloseHandler(NoneHandler handler);
    void send(Player player);
}