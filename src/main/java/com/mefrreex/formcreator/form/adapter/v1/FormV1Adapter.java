package com.mefrreex.formcreator.form.adapter.v1;

import com.mefrreex.formcreator.form.adapter.IButtonAdapter;
import com.mefrreex.formcreator.form.adapter.IFormAdapter;
import com.mefrreex.formcreator.form.adapter.handler.NoneHandler;
import ru.contentforge.formconstructor.form.SimpleForm;
import ru.contentforge.formconstructor.form.element.Button;
import ru.contentforge.formconstructor.form.element.ImageType;

public class FormV1Adapter extends SimpleForm implements IFormAdapter<SimpleForm> {

    public FormV1Adapter(String title) {
        super(title);
    }

    @Override
    public SimpleForm addButton(IButtonAdapter<?> buttonAdapter) {
        Button button = new Button(
            buttonAdapter.getName(),
            ImageType.valueOf(buttonAdapter.getImageType().name()),
            buttonAdapter.getImagePath(),
            (pl, b) -> buttonAdapter.getButtonHandler().handle(pl));
        super.addButton(button);
        return this;
    }

    @Override
    public SimpleForm setCloseHandler(NoneHandler handler) {
        this.setNoneHandler(pl -> handler.handle(pl));
        return this;
    }
}