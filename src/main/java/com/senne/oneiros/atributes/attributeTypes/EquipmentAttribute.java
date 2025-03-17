package com.senne.oneiros.atributes.attributeTypes;

import com.senne.oneiros.tools.utils.EquipmentSlotUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class EquipmentAttribute implements VariableAttribute {

    protected NamespacedKey namespacedKey;
    protected ItemStack icon;
    protected String name;

    protected List<EquipmentSlot> slots = new ArrayList<>();

    @Override
    public byte[] exportVariables() {
        return new byte[]{EquipmentSlotUtils.listToByte(slots)};
    }

    @Override
    public void importVariables(byte[] variables) {
        if (variables.length != 1) throw new IllegalArgumentException("Invalid variable length");

        setSlots(EquipmentSlotUtils.listFromByte(variables[0]));
    }

    public List<EquipmentSlot> getSlots() {
        return slots;
    }

    public void setSlots(List<EquipmentSlot> slots) {
        this.slots = slots;
    }

    public void addSlot(EquipmentSlot slot) {
        if (slots.contains(slot)) return;
        slots.add(slot);
    }

    public void removeSlot(EquipmentSlot slot) {
        slots.remove(slot);
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
}
