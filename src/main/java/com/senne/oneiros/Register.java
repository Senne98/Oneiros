package com.senne.oneiros;

import com.senne.oneiros.atributes.AttributeRegister;
import com.senne.oneiros.atributes.booleanAttibutes.attributes.EnchantmentGlint;
import com.senne.oneiros.atributes.booleanAttibutes.attributes.Hidetooltip;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.armor.Armor;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.attackDamage.AttackDamage;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.attackKnockback.AttackKnockback;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.burningTime.BurningTime;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.entityInteractionRange.EntityInteractionRange;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.gravity.Gravity;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.maxHealth.Health;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.movementEfficiency.MovementEfficiency;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.movementSpeed.MovementSpeed;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.oxygenBonus.OxygenBonus;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.scale.Scale;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.submergedMiningSpeed.SubmergedMiningSpeed;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.sweepingDamageRatio.SweepingDamageRatio;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.waterMovementEfficiency.WaterMovementEfficiency;
import com.senne.oneiros.atributes.equipmentSlotAttributes.armorToughness.ArmorToughness;
import com.senne.oneiros.atributes.equipmentSlotAttributes.attackSpeed.AttackSpeed;
import com.senne.oneiros.atributes.equipmentSlotAttributes.blockBreakSpeed.BlockBreakSpeed;
import com.senne.oneiros.atributes.equipmentSlotAttributes.blockInteractionRange.BlockInteractionRange;
import com.senne.oneiros.atributes.equipmentSlotAttributes.explosionKnockbackResistance.ExplosionKnockbackResistance;
import com.senne.oneiros.atributes.equipmentSlotAttributes.fallDamageMultiplier.FallDamageMultiplier;
import com.senne.oneiros.atributes.equipmentSlotAttributes.flyingSpeed.FlyingSpeed;
import com.senne.oneiros.atributes.equipmentSlotAttributes.jumpStrength.JumpStrength;
import com.senne.oneiros.atributes.equipmentSlotAttributes.knockbackResistance.KnockbackResistance;
import com.senne.oneiros.atributes.equipmentSlotAttributes.luck.Luck;
import com.senne.oneiros.atributes.equipmentSlotAttributes.maxAbsorption.MaxAbsorption;
import com.senne.oneiros.atributes.equipmentSlotAttributes.miningEfficiency.MiningEfficiency;
import com.senne.oneiros.atributes.equipmentSlotAttributes.safeFallDistance.SafeFallDistance;
import com.senne.oneiros.atributes.equipmentSlotAttributes.sneakingSpeed.SneakingSpeed;
import com.senne.oneiros.atributes.equipmentSlotAttributes.stepHeight.StepHeight;

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
        AttributeRegister.registerAttribute(new Health());
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
