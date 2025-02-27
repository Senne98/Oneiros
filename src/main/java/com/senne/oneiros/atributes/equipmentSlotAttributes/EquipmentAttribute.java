package com.senne.oneiros.atributes.equipmentSlotAttributes;

import com.senne.oneiros.atributes.VariableAttribute;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EquipmentAttribute implements VariableAttribute {

    private List<EquipmentSlot> slots = new ArrayList<>();

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
}
