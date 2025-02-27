package com.senne.oneiros.atributes;

import org.bukkit.entity.Player;

public interface VariableAttribute extends Attribute {
    Byte[] exportVariables();
    void importVariables(Byte[] variables);
    void variableConfigUI(Player player);
}
