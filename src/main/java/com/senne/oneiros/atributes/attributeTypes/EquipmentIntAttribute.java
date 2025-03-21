package com.senne.oneiros.atributes.attributeTypes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.atributes.attributeTypes.UI.EquipmentSlotsUI;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;

import static com.senne.oneiros.tools.utils.ByteUtils.merge;
import static com.senne.oneiros.tools.utils.SerializationUtils.deserialize;
import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public abstract class EquipmentIntAttribute extends EquipmentAttribute {

    protected HashMap<EquipmentSlot, Integer> slots = new HashMap<>();

    protected int min;
    protected int max;
    protected Attribute attribute;


    public int getAmount(EquipmentSlot slot) {
        return slots.get(slot);
    }

    public void setAmount(EquipmentSlot slot, int amount) {
        slots.put(slot, amount);
    }

    public List<EquipmentSlot> getSlots() {
        return slots.keySet().stream().toList();
    }

    public void removeSlot(EquipmentSlot slot) {
        slots.remove(slot);
    }

    public void setSlots(HashMap<EquipmentSlot, Integer> slots) {
        if (slots == null) throw new IllegalArgumentException("slots cannot be null");
        if (slots.keySet().stream().anyMatch(s -> s == null)) throw new IllegalArgumentException("slots cannot contain null values");

        this.slots = slots;
    }

    @Override
    public ItemStack applyAttribute(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        for (EquipmentSlot slot : slots.keySet()) {
            meta.addAttributeModifier(attribute,
                    new AttributeModifier(new NamespacedKey(Oneiros.getPlugin(),
                            name.toLowerCase().replace(" ", "_") + "_" + slot.name()),
                            slots.get(slot),
                            AttributeModifier.Operation.ADD_NUMBER,
                            slot.getGroup()
                    )
            );
        }
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void importVariables(byte[] variables) {
        ByteBuffer buffer = ByteBuffer.wrap(variables);
        while (buffer.hasRemaining()) {
            EquipmentSlot slot = EquipmentSlot.values()[buffer.get()];
            byte[] processing = new byte[4];
            buffer.get(processing);
            int amount = deserialize(processing, Integer.class);
            slots.put(slot, amount);
        }
    }

    @Override
    public byte[] exportVariables() {
        byte[] result = new byte[0];
        for (EquipmentSlot slot : slots.keySet()) {
            result = merge(result, serialize(slot));
            result = merge(result, serialize(slots.get(slot)));
        }
        return result;
    }

    @Override
    public void variableConfigUI(Player player) {
        EquipmentSlotsUI ui = new EquipmentSlotsUI(player, namespacedKey);
        player.openInventory(ui.getInventory());
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public boolean equals(Object j) {
        if (!(j instanceof EquipmentIntAttribute)) return false;
        EquipmentIntAttribute attribute = (EquipmentIntAttribute) j;
        if (!slots.equals(attribute.slots)) return false;
        if (min != attribute.min) return false;
        if (max != attribute.max) return false;

        return super.equals(j);
    }

}
