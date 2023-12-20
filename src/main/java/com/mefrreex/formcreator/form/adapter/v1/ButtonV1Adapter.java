package com.mefrreex.formcreator.form.adapter.v1;

import com.mefrreex.formcreator.form.adapter.IButtonAdapter;
import com.mefrreex.formcreator.form.adapter.ImageTypeAdapter;
import com.mefrreex.formcreator.form.adapter.handler.ButtonHandler;
import ru.contentforge.formconstructor.form.element.Button;
import lombok.Setter;

@Setter
public class ButtonV1Adapter extends Button implements IButtonAdapter<Button> {

    private final ImageTypeAdapter imageType;
    private final String imagePath;
    private final ButtonHandler handler;
    
    public ButtonV1Adapter(String name, ImageTypeAdapter imageType, String imagePath, ButtonHandler handler) {
        super(name);
        this.imageType = imageType;
        this.imagePath = imagePath;
        this.handler = handler;
    }

    @Override
    public ButtonHandler getButtonHandler() {
        return handler;
    }

    @Override
    public ImageTypeAdapter getImageType() {
        return imageType;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }
}