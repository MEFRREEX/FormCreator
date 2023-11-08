package com.mefrreex.formcreator.form.action;

import cn.nukkit.Player;
import com.google.gson.annotations.SerializedName;
import com.mefrreex.formcreator.form.action.executor.Executor;
import com.mefrreex.formcreator.form.action.executor.ExecutorManager;
import com.mefrreex.formcreator.utils.Format;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class Action {
    
    @SerializedName("type") private String type;
    @SerializedName("value") private String value;

    public Action(String type, String data) {
        this.type = type;
        this.value = data;
    }

    public String getType() {
        return type.toUpperCase();
    }

    public void execute(Player player) {
        Executor executor = ExecutorManager.getExecutor(this.getType());
        if (executor == null) {
            throw new RuntimeException("No registered Executor found for type " + this.getType());
        }
        String data = Format.format(value, player);
        executor.execute(player, data);
    }
}
