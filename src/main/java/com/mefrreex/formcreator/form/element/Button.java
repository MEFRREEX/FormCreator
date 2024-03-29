package com.mefrreex.formcreator.form.element;

import com.mefrreex.formcreator.form.action.Action;
import com.mefrreex.formcreator.form.adapter.ImageTypeAdapter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter 
@Setter
@ToString
public class Button {
    
    private String name;
    
    private String image;
    private ImageTypeAdapter imageType;

    private List<Action> actions = new ArrayList<>();

    public Button(String name) {
        this.name = name;
    }

    /**
     * Add button action
     */
    public Button addAction(Action action) {
        actions.add(action);
        return this;
    }
}
