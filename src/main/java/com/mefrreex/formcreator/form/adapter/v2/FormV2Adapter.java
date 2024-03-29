package com.mefrreex.formcreator.form.adapter.v2;

import com.formconstructor.form.SimpleForm;
import com.formconstructor.form.element.simple.Button;
import com.formconstructor.form.element.simple.ImageType;
import com.mefrreex.formcreator.form.adapter.IButtonAdapter;
import com.mefrreex.formcreator.form.adapter.IFormAdapter;
import com.mefrreex.formcreator.form.adapter.handler.CloseHandler;

public class FormV2Adapter extends SimpleForm implements IFormAdapter<SimpleForm> {

    public FormV2Adapter(String title) {
        super(title);
    }

    @Override
    public SimpleForm addButton(IButtonAdapter<?> buttonAdapter) {
        ImageType imageType = ImageType.valueOf(buttonAdapter.getImageType().name());
        this.addButton(new Button(buttonAdapter.getName())
            .setImage(imageType, buttonAdapter.getImagePath())
            .onClick((pl, b) -> buttonAdapter.getButtonHandler().handle(pl)));
        return this;
    }

    @Override
    public SimpleForm setCloseHandler(CloseHandler handler) {
        this.setNoneHandler(pl -> handler.handle(pl));
        return this;
    }
}