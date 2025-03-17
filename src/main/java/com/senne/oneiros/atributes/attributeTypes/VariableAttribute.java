package com.senne.oneiros.atributes.attributeTypes;

import org.bukkit.entity.Player;

public interface VariableAttribute extends Attribute {
    byte[] exportVariables();
    void importVariables(byte[] variables);
    void variableConfigUI(Player player);
}
