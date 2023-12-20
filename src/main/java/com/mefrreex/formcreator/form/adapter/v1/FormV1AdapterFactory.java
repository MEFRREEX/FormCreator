package com.mefrreex.formcreator.form.adapter.v1;

import com.mefrreex.formcreator.form.adapter.IFormAdapterFactory;
import com.mefrreex.formcreator.form.adapter.ImageTypeAdapter;
import com.mefrreex.formcreator.form.adapter.handler.ButtonHandler;

public class FormV1AdapterFactory implements IFormAdapterFactory {

    @Override
    public FormV1Adapter createForm(String title) {
        return new FormV1Adapter(title);
    }

    @Override
    public ButtonV1Adapter createButton(String name, ImageTypeAdapter imageType, String imagePath, ButtonHandler handler) {
        return new ButtonV1Adapter(name, imageType, imagePath, handler);
    }
}