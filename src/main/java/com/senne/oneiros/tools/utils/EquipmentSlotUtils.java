package com.senne.oneiros.tools.utils;

import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class EquipmentSlotUtils {

    public static byte listToByte(List<EquipmentSlot> slots) {
        if (slots == null) return 0;
        if (slots.size() == 0) return 0;

        byte slotsByte = 0;

        if (slots.contains(EquipmentSlot.HAND)) {
            slotsByte = (byte) (slotsByte | 0b00000001);
        }
        if (slots.contains(EquipmentSlot.OFF_HAND)) {
            slotsByte = (byte) (slotsByte | 0b00000010);
        }
        if (slots.contains(EquipmentSlot.FEET)) {
            slotsByte = (byte) (slotsByte | 0b00000100);
        }
        if (slots.contains(EquipmentSlot.LEGS)) {
            slotsByte = (byte) (slotsByte | 0b00001000);
        }
        if (slots.contains(EquipmentSlot.CHEST)) {
            slotsByte = (byte) (slotsByte | 0b00010000);
        }
        if (slots.contains(EquipmentSlot.HEAD)) {
            slotsByte = (byte) (slotsByte | 0b00100000);
        }
        if (slots.contains(EquipmentSlot.BODY)) {
            slotsByte = (byte) (slotsByte | 0b01000000);
        }

        return slotsByte;
    }

    public static List<EquipmentSlot> listFromByte(byte slotsByte) {
        List<EquipmentSlot> slots = new ArrayList<>();
        if (slotsByte == 0) return slots;

        if ((slotsByte & 0b00000001) == 0b00000001) {
            slots.add(EquipmentSlot.HAND);
        }
        if ((slotsByte & 0b00000010) == 0b00000010) {
            slots.add(EquipmentSlot.OFF_HAND);
        }
        if ((slotsByte & 0b00000100) == 0b00000100) {
            slots.add(EquipmentSlot.FEET);
        }
        if ((slotsByte & 0b00001000) == 0b00001000) {
            slots.add(EquipmentSlot.LEGS);
        }
        if ((slotsByte & 0b00010000) == 0b00010000) {
            slots.add(EquipmentSlot.CHEST);
        }
        if ((slotsByte & 0b00100000) == 0b00100000) {
            slots.add(EquipmentSlot.HEAD);
        }
        if ((slotsByte & 0b01000000) == 0b01000000) {
            slots.add(EquipmentSlot.BODY);
        }

        return slots;
    }
}
