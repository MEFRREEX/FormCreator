package com.mefrreex.formcreator.form.element;

import com.mefrreex.formcreator.form.action.Action;
import com.mefrreex.formcreator.form.adapter.ImageTypeAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
public class Button {
    
    @SerializedName("name") private String name;
    
    @SerializedName("image") private String image;
    @SerializedName("imageType") private ImageTypeAdapter imageType;

    @SerializedName("actions") 
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
