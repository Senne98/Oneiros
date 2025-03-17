package com.senne.oneiros;

import com.senne.oneiros.atributes.*;
import com.senne.oneiros.atributes.EnchantmentGlint;
import com.senne.oneiros.atributes.Hidetooltip;
import com.senne.oneiros.atributes.AttackKnockback;
import com.senne.oneiros.atributes.BlockBreakSpeed;
import com.senne.oneiros.atributes.BlockInteractionRange;
import com.senne.oneiros.atributes.BurningTime;
import com.senne.oneiros.atributes.EntityInteractionRange;
import com.senne.oneiros.atributes.ExplosionKnockbackResistance;
import com.senne.oneiros.atributes.FlyingSpeed;
import com.senne.oneiros.atributes.Gravity;
import com.senne.oneiros.atributes.JumpStrength;
import com.senne.oneiros.atributes.KnockbackResistance;
import com.senne.oneiros.atributes.Luck;
import com.senne.oneiros.atributes.MovementEfficiency;
import com.senne.oneiros.atributes.MovementSpeed;
import com.senne.oneiros.atributes.OxygenBonus;
import com.senne.oneiros.atributes.Scale;
import com.senne.oneiros.atributes.SneakingSpeed;
import com.senne.oneiros.atributes.SubmergedMiningSpeed;
import com.senne.oneiros.atributes.SweepingDamageRatio;
import com.senne.oneiros.atributes.WaterMovementEfficiency;
import com.senne.oneiros.atributes.FallDamageMultiplier;
import com.senne.oneiros.atributes.MiningEfficiency;
import com.senne.oneiros.atributes.StepHeight;
import com.senne.oneiros.atributes.attributeTypes.AttributeRegister;

public class Register {

    public static void registerAttributes() {
        AttributeRegister.registerAttribute(new Armor());
        AttributeRegister.registerAttribute(new ArmorToughness());
        AttributeRegister.registerAttribute(new AttackDamage());
        AttributeRegister.registerAttribute(new AttackKnockback());
        AttributeRegister.registerAttribute(new AttackSpeed());
        AttributeRegister.registerAttribute(new BlockBreakSpeed());
        AttributeRegister.registerAttribute(new BlockInteractionRange());
        AttributeRegister.registerAttribute(new BurningTime());
        AttributeRegister.registerAttribute(new EnchantmentGlint());
        AttributeRegister.registerAttribute(new EntityInteractionRange());
        AttributeRegister.registerAttribute(new ExplosionKnockbackResistance());
        AttributeRegister.registerAttribute(new FallDamageMultiplier());
        AttributeRegister.registerAttribute(new FlyingSpeed());
        AttributeRegister.registerAttribute(new Gravity());
        AttributeRegister.registerAttribute(new Hidetooltip());
        AttributeRegister.registerAttribute(new JumpStrength());
        AttributeRegister.registerAttribute(new KnockbackResistance());
        AttributeRegister.registerAttribute(new Luck());
        AttributeRegister.registerAttribute(new MaxAbsorption());
        AttributeRegister.registerAttribute(new MaxHealth());
        AttributeRegister.registerAttribute(new MiningEfficiency());
        AttributeRegister.registerAttribute(new MovementEfficiency());
        AttributeRegister.registerAttribute(new MovementSpeed());
        AttributeRegister.registerAttribute(new OxygenBonus());
        AttributeRegister.registerAttribute(new SafeFallDistance());
        AttributeRegister.registerAttribute(new Scale());
        AttributeRegister.registerAttribute(new SneakingSpeed());
        AttributeRegister.registerAttribute(new StepHeight());
        AttributeRegister.registerAttribute(new SubmergedMiningSpeed());
        AttributeRegister.registerAttribute(new SweepingDamageRatio());
        AttributeRegister.registerAttribute(new WaterMovementEfficiency());
    }
}
