package com.senne.oneiros.atributes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.chatUI.ActiveChat;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.armor.ArmorAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.armorToughness.ArmorToughnessAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.attackDamage.AttackDamageAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.attackKnockback.AttackKnockbackAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.burningTime.BurningTimeAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.entityInteractionRange.EntityInteractionRangeAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.gravity.GravityAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.maxHealth.HealthAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.movementEfficiency.MovementEfficiencyAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.movementSpeed.MovementSpeedAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.oxygenBonus.OxygenBonusAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.scale.ScaleAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.submergedMiningSpeed.SubmergedMiningSpeedAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.sweepingDamageRatio.SweepingDamageRatioAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.Attributes.waterMovementEfficiency.WaterMovementEfficiencyAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.attackSpeed.AttackSpeedAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.blockBreakSpeed.BlockBreakSpeedAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.blockInteractionRange.BlockInteractionRangeAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.explosionKnockbackResistance.ExplosionKnockbackResistanceAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.fallDamageMultiplier.FallDamageMultiplierAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.flyingSpeed.FlyingSpeedAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.jumpStrength.JumpStrengthAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.knockbackResistance.KnockbackResistanceAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.luck.LuckTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.maxAbsorption.MaxAbsorptionTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.miningEfficiency.MiningEfficiencyAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.safeFallDistance.SafeFallDistanceTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.sneakingSpeed.SneakingSpeedAmountTextUIEvent;
import com.senne.oneiros.atributes.equipmentSlotAttributes.stepHeight.StepHeightAmountTextUIEvent;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class TextUIListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncChatEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        if (ActiveChat.getActiveChat(uuid) == null) {
            return;
        }

        if (ActiveChat.getActiveChat(uuid).endsWith("_armor")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                ArmorAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(uuid).endsWith("_armortoughness")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                ArmorToughnessAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_attackdamage")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                AttackDamageAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_attackknockback")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                AttackKnockbackAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_attackspeed")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                AttackSpeedAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_blockbreakspeed")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                BlockBreakSpeedAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_blockinteractionrange")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                BlockInteractionRangeAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_burningtime")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                BurningTimeAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_entityinteractionrange")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                EntityInteractionRangeAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_explosionknockbackresistance")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                ExplosionKnockbackResistanceAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_falldamagemultiplier")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                FallDamageMultiplierAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_flyingspeed")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                FlyingSpeedAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_gravity")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                GravityAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_jumpstrength")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                JumpStrengthAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_knockbackresistance")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                KnockbackResistanceAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_luck")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                LuckTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_maxabsorption")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                MaxAbsorptionTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_maxhealth")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                HealthAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_miningefficiency")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                MiningEfficiencyAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_movementefficiency")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                MovementEfficiencyAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_movementspeed")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                MovementSpeedAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_oxygenbonus")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                OxygenBonusAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_safefalldistance")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                SafeFallDistanceTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_scale")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                ScaleAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_sneakingspeed")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                SneakingSpeedAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_stepheight")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                StepHeightAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_submergedminingspeed")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                SubmergedMiningSpeedAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_sweepingdamageratio")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                SweepingDamageRatioAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }

        if (ActiveChat.getActiveChat(e.getPlayer().getUniqueId()).endsWith("_watermovementefficiency")) {
            e.setCancelled(true);
            Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
                WaterMovementEfficiencyAmountTextUIEvent.onChat(e.getPlayer(), PlainTextComponentSerializer.plainText().serialize(e.message()));
                return null;
            });
            return;
        }
    }
}
