package com.senne.oneiros.atributes.equipmentSlotAttributes.knockbackResistance;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.chatUI.ActiveChat;
import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentAttribute;
import com.senne.oneiros.tools.DoubleUtils;
import com.senne.oneiros.tools.EquipmentSlotUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KnockbackResistance extends EquipmentAttribute {
    private double amount;

    public KnockbackResistance() {}

    @Override
    public ItemStack applyAttribute(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        for (int i = 0; i < getSlots().size(); i++) {
            meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, new AttributeModifier(new NamespacedKey(Oneiros.getPlugin(), "knockbackresistance_" + getSlots().get(i).name()), amount, AttributeModifier.Operation.ADD_NUMBER, getSlots().get(i).getGroup()));
        }
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.NETHERITE_CHESTPLATE);
    }

    @Override
    public String getName() {
        return "Knockback Resistance";
    }

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(Oneiros.getPlugin(), "knockbackresistance");
    }

    public static NamespacedKey key() {
        return new NamespacedKey(Oneiros.getPlugin(), "knockbackresistance");
    }

    @Override
    public Byte[] exportVariables() {
        // first byte: slots
        // proceeding bytes: amount
        Byte[] bytes = new Byte[9];
        bytes[0] = EquipmentSlotUtils.listToByte(getSlots());

        byte[] amountBytes = DoubleUtils.toByteArray(amount);
        for (int i = 0; i < 8; i++) {
            bytes[i + 1] = amountBytes[i];
        }

        return bytes;

    }

    @Override
    public void importVariables(Byte[] variables) {
        setSlots(EquipmentSlotUtils.listFromByte(variables[0]));
        byte[] amountBytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            amountBytes[i] = variables[i + 1];
        }
        amount = DoubleUtils.fromByteArray(amountBytes);
    }

    @Override
    public void variableConfigUI(Player player) {
        player.closeInventory();

        ActiveChat.addActiveChat(player.getUniqueId(), "defaultattribute_knockbackresistance");

        player.sendMessage(Component.text("Enter the amount of knockback resistance in the chat.").decoration(TextDecoration.ITALIC, false));
        player.sendMessage(Component.text("[Cancel]")
                .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the knockback resistance amount input.").color(NamedTextColor.RED)))
                .decoration(TextDecoration.ITALIC, false)
                .color(NamedTextColor.RED)
                .clickEvent(ClickEvent.runCommand("/oneiroscancel defaultattribute")));
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
