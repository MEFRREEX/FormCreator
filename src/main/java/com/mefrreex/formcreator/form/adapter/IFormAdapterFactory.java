package com.mefrreex.formcreator.form.adapter;

import com.mefrreex.formcreator.form.adapter.handler.ButtonHandler;

public interface IFormAdapterFactory {
    
    IFormAdapter<?> createForm(String title);
    
    IButtonAdapter<?> createButton(String name, ImageTypeAdapter imageType, String imagePath, ButtonHandler handler);
}
