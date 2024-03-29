package com.mefrreex.formcreator.form.adapter;

import com.mefrreex.formcreator.form.adapter.handler.ButtonHandler;

public interface IButtonAdapter<T> {
    
    String getName();

    ImageTypeAdapter getImageType();

    String getImagePath();
    
    ButtonHandler getButtonHandler();
}
