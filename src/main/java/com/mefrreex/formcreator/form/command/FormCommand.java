package com.mefrreex.formcreator.form.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class FormCommand {
    
    private boolean enable = true;

    private String name;
    
    private String description = "";
    
    private String[] aliases = new String[0];

    private String permission;

    public FormCommand(String name) {
        this(name, name);
    }

    public FormCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
