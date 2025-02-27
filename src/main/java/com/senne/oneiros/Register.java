package com.senne.oneiros;

import com.senne.oneiros.atributes.equipmentSlotAttributes.armor.Armor;
import com.senne.oneiros.atributes.AttributeRegister;

public class Register {

    public static void registerAttributes() {
        AttributeRegister.registerAttribute(new Armor());
    }
}
