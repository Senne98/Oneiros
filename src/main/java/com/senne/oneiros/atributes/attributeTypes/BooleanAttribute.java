package com.senne.oneiros.atributes.attributeTypes;

import com.senne.oneiros.atributes.attributeTypes.UI.BooleanUI;
import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class BooleanAttribute implements VariableAttribute {

    protected NamespacedKey namespacedKey;
    protected ItemStack icon;
    protected String name;

    protected boolean bool;

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    @Override
    public byte[] exportVariables() {
        byte[] bytes = new byte[1];
        bytes[0] = (byte) (bool ? 1 : 0);
        return bytes;
    }

    @Override
    public void importVariables(byte[] variables) {
        bool = variables[0] == 1;
    }

    @Override
    public void variableConfigUI(Player player) {
        BooleanUI ui = new BooleanUI(player, namespacedKey);
        player.closeInventory();
        player.openInventory(ui.getInventory());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public NamespacedKey getKey() {
        return namespacedKey;
    }

    @Override
    public ItemStack getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object j) {
        if (!(j instanceof BooleanAttribute)) return false;
        BooleanAttribute attribute = (BooleanAttribute) j;
        if (!attribute.getKey().equals(namespacedKey)) return false;
        if (!attribute.getIcon().equals(icon)) return false;
        if (!attribute.getName().equals(name)) return false;

        return bool == attribute.isBool();
    }
}
