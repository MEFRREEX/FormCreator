package com.mefrreex.formcreator.form.command;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class FormCommand {
    
    @SerializedName("enable") 
    private boolean enable = true;

    @SerializedName("name") 
    private String name;
    
    @SerializedName("description") 
    private String description = "";
    
    @SerializedName("aliases") 
    private String[] aliases = new String[]{};

    @SerializedName("permission") 
    private String permission;

    public FormCommand(String name) {
        this(name, name);
    }

    public FormCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
