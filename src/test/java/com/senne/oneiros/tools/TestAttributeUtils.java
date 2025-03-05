package com.senne.oneiros.tools;

import com.senne.oneiros.atributes.Attribute;
import com.senne.oneiros.atributes.equipmentSlotAttributes.attackDamage.AttackDamage;
import com.senne.oneiros.atributes.equipmentSlotAttributes.attackSpeed.AttackSpeed;
import com.senne.oneiros.atributes.equipmentSlotAttributes.knockbackResistance.KnockbackResistance;
import com.senne.oneiros.atributes.equipmentSlotAttributes.maxHealth.Health;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestAttributeUtils {

    private List<Attribute> attributes = new ArrayList<>();

    @BeforeEach
    public void load() {
        attributes.add(new AttackSpeed());
        attributes.add(new KnockbackResistance());
        attributes.add(new AttackDamage());
        attributes.add(new AttackSpeed());
    }

    @Test
    public void testContainsInstance() {
        Assertions.assertTrue(AttributeUtils.containsInstance(attributes, new AttackSpeed()));
        Assertions.assertTrue(AttributeUtils.containsInstance(attributes, new AttackDamage()));
        Assertions.assertFalse(AttributeUtils.containsInstance(attributes, new Health()));
    }

    @Test
    public void testremoveEveryInstance() {
        AttributeUtils.removeEveryInstance(attributes, new AttackDamage());
        Assertions.assertFalse(AttributeUtils.containsInstance(attributes, new AttackDamage()));
        AttributeUtils.removeEveryInstance(attributes, new AttackSpeed());
        Assertions.assertFalse(AttributeUtils.containsInstance(attributes, new AttackSpeed()));
        AttributeUtils.removeEveryInstance(attributes, new Health());
        Assertions.assertFalse(AttributeUtils.containsInstance(attributes, new Health()));
    }


}
