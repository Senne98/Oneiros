package com.senne.oneiros.atributes.attributeTypes;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.chatUI.ActiveChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static com.senne.oneiros.tools.utils.SerializationUtils.deserialize;
import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public abstract class EquipmentDoubleAttribute extends EquipmentAttribute {

    protected double amount;

    protected double min;
    protected double max;
    protected Attribute attribute;


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public ItemStack applyAttribute(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        for (int i = 0; i < getSlots().size(); i++) {
            meta.addAttributeModifier(attribute, new AttributeModifier(new NamespacedKey(Oneiros.getPlugin(),  name.toLowerCase().replace(" ", "_") + "_" + getSlots().get(i).name()), amount, AttributeModifier.Operation.ADD_NUMBER, getSlots().get(i).getGroup()));
        }
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void importVariables(byte[] variables) {
        if (variables.length != 5) throw new IllegalArgumentException("Invalid variable length");
        super.importVariables(Arrays.copyOfRange(variables, 4, 5));
        amount = deserialize(Arrays.copyOfRange(variables, 0, 4), Double.class);
    }

    @Override
    public byte[] exportVariables() {
        byte[] amountBytes = serialize(amount);
        byte[] slotsBytes = super.exportVariables();

        amountBytes = Arrays.copyOf(amountBytes, amountBytes.length + 1);
        amountBytes[amountBytes.length - 1] = slotsBytes[0];
        return amountBytes;
    }

    @Override
    public void variableConfigUI(Player player) {
        player.closeInventory();

        ActiveChat.addActiveChat(player.getUniqueId(), "equipmentDoubleAttribute:" + namespacedKey.asString());

        player.sendMessage(Component.text("Enter the amount of " + name.toLowerCase() + " in the chat.").decoration(TextDecoration.ITALIC, false));
        player.sendMessage(Component.text("[Cancel]")
                .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the " + name.toLowerCase() + " amount input.").color(NamedTextColor.RED)))
                .decoration(TextDecoration.ITALIC, false)
                .color(NamedTextColor.RED)
                .clickEvent(ClickEvent.runCommand("/oneiroscancel equipmentDoubleAttribute")));

    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
