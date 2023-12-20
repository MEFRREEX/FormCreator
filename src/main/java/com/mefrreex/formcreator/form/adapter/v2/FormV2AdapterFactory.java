package com.mefrreex.formcreator.form.adapter.v2;

import com.mefrreex.formcreator.form.adapter.IFormAdapterFactory;
import com.mefrreex.formcreator.form.adapter.ImageTypeAdapter;
import com.mefrreex.formcreator.form.adapter.handler.ButtonHandler;

public class FormV2AdapterFactory implements IFormAdapterFactory {

    @Override
    public FormV2Adapter createForm(String title) {
        return new FormV2Adapter(title);
    }

    @Override
    public ButtonV2Adapter createButton(String name, ImageTypeAdapter imageType, String imagePath, ButtonHandler handler) {
        return new ButtonV2Adapter(name, imageType, imagePath, handler);
    }
}