package com.mefrreex.formcreator.form.action;

import cn.nukkit.Player;
import com.google.gson.annotations.SerializedName;
import com.mefrreex.formcreator.form.action.executor.Executor;
import com.mefrreex.formcreator.form.action.executor.ExecutorManager;
import com.mefrreex.formcreator.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;


@Getter @Setter
@ToString
public class Action {
    
    @SerializedName("type") private String type;
    @SerializedName("data") private String data;

    public Action(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type.toUpperCase();
    }

    public void execute(Player player) {
        Executor executor = ExecutorManager.getExecutor(this.getType());
        if (executor == null) {
            throw new RuntimeException("No registered Executor found for type " + this.getType());
        }
        String data = Utils.replace(this.data, Map.of("player", player.getName()));
        executor.execute(player, data);
    }
}
