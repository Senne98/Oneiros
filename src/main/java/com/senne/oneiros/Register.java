package com.senne.oneiros;

import com.senne.oneiros.atributes.AttributeRegister;
import com.senne.oneiros.atributes.equipmentSlotAttributes.armor.Armor;

public class Register {

    public static void registerAttributes() {
        AttributeRegister.registerAttribute(new Armor());
    }
}
