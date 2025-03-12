package com.senne.oneiros.atributes.booleanAttibutes;

import com.senne.oneiros.atributes.VariableAttribute;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BooleanAttribute implements VariableAttribute {

    private boolean bool;

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    @Override
    public Byte[] exportVariables() {
        return new Byte[0];
    }

    @Override
    public void importVariables(Byte[] variables) {

    }

    @Override
    public void variableConfigUI(Player player) {

    }

    @Override
    public ItemStack applyAttribute(ItemStack item) {
        return null;
    }

    @Override
    public ItemStack getIcon() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public NamespacedKey getKey() {
        return null;
    }
}
