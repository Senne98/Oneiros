package com.senne.oneiros.tools;

import org.bukkit.inventory.EquipmentSlot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEquipmentSlotUtils {

    @Test
    public void listSerialization() {
        List<EquipmentSlot> slots = new ArrayList<>();
        slots.add(EquipmentSlot.OFF_HAND);
        slots.add(EquipmentSlot.FEET);
        slots.add(EquipmentSlot.LEGS);
        slots.add(EquipmentSlot.BODY);
        slots.add(EquipmentSlot.CHEST);
        slots.add(EquipmentSlot.HEAD);
        slots.add(EquipmentSlot.BODY);
        slots.add(EquipmentSlot.HAND);

        byte slotsByte = EquipmentSlotUtils.listToByte(slots);
        Assertions.assertEquals(List.of(
            EquipmentSlot.HAND,
            EquipmentSlot.OFF_HAND,
            EquipmentSlot.FEET,
            EquipmentSlot.LEGS,
            EquipmentSlot.CHEST,
            EquipmentSlot.HEAD,
            EquipmentSlot.BODY),
                EquipmentSlotUtils.listFromByte(slotsByte));
    }
}
