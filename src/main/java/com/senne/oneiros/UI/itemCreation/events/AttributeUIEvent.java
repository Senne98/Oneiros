package com.senne.oneiros.UI.itemCreation.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.AttributeUI;
import com.senne.oneiros.UI.itemCreation.inventories.ItemCreationUI;
import com.senne.oneiros.atributes.attributeTypes.Attribute;
import com.senne.oneiros.atributes.attributeTypes.AttributeRegister;
import com.senne.oneiros.atributes.attributeTypes.VariableAttribute;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.Item;
import com.senne.oneiros.tools.utils.AttributeUtils;
import com.senne.oneiros.tools.dataTypes.NamespacedKeyDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class AttributeUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof AttributeUI)) return;

        e.setCancelled(true);

        if (e.getRawSlot() > 53) return;

        int slot = e.getSlot();
        Player player = (Player) e.getWhoClicked();

        if (slot == 49) {
            ItemCreationUI ui = new ItemCreationUI(player);
            player.openInventory(ui.getInventory());
        }

        if (e.getInventory().getItem(slot).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Oneiros.getPlugin(), "page"))) {
            int page = e.getInventory().getItem(slot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "page"), PersistentDataType.INTEGER);

            AttributeUI ui = new AttributeUI(player, page);
            player.openInventory(ui.getInventory());
            return;
        }

        if (!e.getInventory().getItem(slot).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Oneiros.getPlugin(), "attribute"))) return;

        NamespacedKey attributeKey = e.getInventory().getItem(slot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType());

        Attribute attribute = AttributeRegister.getAttribute(attributeKey);

        if (attribute == null) return;

        Item item = ActiveItemCreation.getActiveItem(player.getUniqueId());

        if (AttributeUtils.containsInstance(item.getAttributes(), attribute)) {
            item.removeAttribute(attribute);
            e.getInventory().setItem(13, item.createItem(1));

            ItemStack icon = attribute.getIcon();
            ItemMeta meta = icon.getItemMeta();
            meta.displayName(Component.text(attribute.getName()).color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));

            List<Component> lore = List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            meta.lore(lore);

            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType(), attribute.getKey());

            icon.setItemMeta(meta);
            e.getInventory().setItem(slot, icon);

            return;
        }

        if (attribute instanceof VariableAttribute) {
            player.closeInventory();

            VariableAttribute varAttribute = (VariableAttribute) attribute;
            item.addAttribute(varAttribute);
            varAttribute.variableConfigUI(player);

            return;
        }

        item.addAttribute(attribute);

        e.getInventory().setItem(13, item.createItem(1));

        ItemStack icon = attribute.getIcon();
        ItemMeta meta = icon.getItemMeta();
        meta.displayName(Component.text(attribute.getName()).color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));

        List<Component> lore = List.of(Component.text("■ Click to remove!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);

        meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType(), attribute.getKey());

        icon.setItemMeta(meta);
        e.getInventory().setItem(slot, icon);

    }

}
