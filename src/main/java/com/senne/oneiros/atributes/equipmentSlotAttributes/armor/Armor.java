package com.senne.oneiros.atributes.equipmentSlotAttributes.armor;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.chatUI.ActiveChat;
import com.senne.oneiros.atributes.equipmentSlotAttributes.EquipmentAttribute;
import com.senne.oneiros.tools.byteUtils.DoubleToByteArray;
import com.senne.oneiros.tools.byteUtils.EquipentSlotToByte;
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

public class Armor extends EquipmentAttribute {
    private double amount;

    public Armor() {}

    @Override
    public ItemStack applyAttribute(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        for (int i = 0; i < getSlots().size(); i++) {
            meta.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(new NamespacedKey(Oneiros.getPlugin(), "armor_" + getSlots().get(i).name()), amount, AttributeModifier.Operation.ADD_NUMBER, getSlots().get(i).getGroup()));
        }
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.DIAMOND_CHESTPLATE);
    }

    @Override
    public String getName() {
        return "Armor";
    }

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(Oneiros.getPlugin(), "armor");
    }

    public static NamespacedKey key() {
        return new NamespacedKey(Oneiros.getPlugin(), "armor");
    }

    @Override
    public Byte[] exportVariables() {
        // first byte: slots
        // proceeding bytes: amount
        Byte[] bytes = new Byte[9];
        bytes[0] = EquipentSlotToByte.toByte(getSlots());

        byte[] amountBytes = DoubleToByteArray.toByteArray(amount);
        for (int i = 0; i < 8; i++) {
            bytes[i + 1] = amountBytes[i];
        }

        return bytes;

    }

    @Override
    public void importVariables(Byte[] variables) {
        setSlots(EquipentSlotToByte.fromByte(variables[0]));
        byte[] amountBytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            amountBytes[i] = variables[i + 1];
        }
        amount = DoubleToByteArray.convertByteArrayToDouble(amountBytes);
    }

    @Override
    public void variableConfigUI(Player player) {
        player.closeInventory();

        ActiveChat.addActiveChat(player.getUniqueId(), "armor");

        player.sendMessage(Component.text("Enter the amount of armor in the chat.").decoration(TextDecoration.ITALIC, false));
        player.sendMessage(Component.text("[Cancel]")
                .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the armor amount input.").color(NamedTextColor.RED)))
                .decoration(TextDecoration.ITALIC, false)
                .color(NamedTextColor.RED)
                .clickEvent(ClickEvent.runCommand("/cancel armor")));
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
