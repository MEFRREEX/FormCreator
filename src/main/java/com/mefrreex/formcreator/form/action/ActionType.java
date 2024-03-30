package com.mefrreex.formcreator.form.action;

public interface ActionType {

    String PLAYER_COMMAND = "PLAYER_COMMAND";

    String CONSOLE_COMMAND = "CONSOLE_COMMAND";

    String OPERATOR_COMMAND = "OPERATOR_COMMAND";

    String MESSAGE = "MESSAGE";

    String PLAYER_MESSAGE = "PLAYER_MESSAGE";

    @Deprecated
    String OPEN = "OPEN";

    String OPEN_FORM = "OPEN_FORM";

    String OPEN_CHEST = "OPEN_CHEST";
}
