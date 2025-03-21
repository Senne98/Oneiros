package com.senne.oneiros.atributes.attributeTypes.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.AttributeUI;
import com.senne.oneiros.atributes.attributeTypes.ActiveChat;
import com.senne.oneiros.atributes.attributeTypes.Attribute;
import com.senne.oneiros.atributes.attributeTypes.EquipmentDoubleAttribute;
import com.senne.oneiros.atributes.attributeTypes.EquipmentIntAttribute;
import com.senne.oneiros.atributes.attributeTypes.UI.EquipmentSlotsUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.tools.dataTypes.NamespacedKeyDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EquipmentSlotsUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof EquipmentSlotsUI)) return;
        e.setCancelled(true);

        if (e.getRawSlot() > 53) return;

        Player player = (Player) e.getWhoClicked();
        UUID uuid = player.getUniqueId();
        int slot = e.getSlot();
        Inventory inv = e.getInventory();
        ItemStack item;
        ItemMeta meta;
        Attribute attribute = ActiveItemCreation.getActiveItem(uuid).getAttribute(inv.getItem(13).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType()));
        List<EquipmentSlot> slots = new ArrayList<>();

        boolean doubleAttribute = attribute instanceof EquipmentDoubleAttribute;

        if (attribute instanceof EquipmentDoubleAttribute) slots = ((EquipmentDoubleAttribute) attribute).getSlots();
        if (attribute instanceof EquipmentIntAttribute) slots = ((EquipmentIntAttribute) attribute).getSlots();

        if (slot == 28) {
            item = inv.getItem(28);
            meta = item.getItemMeta();

            if (slots.contains(EquipmentSlot.HAND)) {
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
                if (doubleAttribute) {
                    ((EquipmentDoubleAttribute) attribute).removeSlot(EquipmentSlot.HAND);
                } else {
                    ((EquipmentIntAttribute) attribute).removeSlot(EquipmentSlot.HAND);
                }
            } else {
                player.closeInventory();

                player.sendMessage(Component.text("Enter the amount in the chat.").decoration(TextDecoration.ITALIC, false));
                player.sendMessage(Component.text("[Cancel]")
                        .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the amount input.").color(NamedTextColor.RED)))
                        .decoration(TextDecoration.ITALIC, false)
                        .color(NamedTextColor.RED)
                        .clickEvent(ClickEvent.runCommand("/oneiroscancel defaultattribute")));

                if (doubleAttribute) {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentDoubleAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.HAND);
                } else {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentIntAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.HAND);
                }
            }

            item.setItemMeta(meta);
            inv.setItem(28, item);

            refreshExample(inv, uuid, attribute.getKey());

            return;
        }

        if (slot == 29) {
            item = inv.getItem(29);
            meta = item.getItemMeta();

            if (slots.contains(EquipmentSlot.OFF_HAND)) {
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
                if (doubleAttribute) {
                    ((EquipmentDoubleAttribute) attribute).removeSlot(EquipmentSlot.OFF_HAND);
                } else {
                    ((EquipmentIntAttribute) attribute).removeSlot(EquipmentSlot.OFF_HAND);
                }
            } else {
                player.closeInventory();

                player.sendMessage(Component.text("Enter the amount in the chat.").decoration(TextDecoration.ITALIC, false));
                player.sendMessage(Component.text("[Cancel]")
                        .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the amount input.").color(NamedTextColor.RED)))
                        .decoration(TextDecoration.ITALIC, false)
                        .color(NamedTextColor.RED)
                        .clickEvent(ClickEvent.runCommand("/oneiroscancel defaultattribute")));

                if (doubleAttribute) {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentDoubleAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.OFF_HAND);
                } else {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentIntAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.OFF_HAND);
                }
            }

            item.setItemMeta(meta);
            inv.setItem(29, item);

            refreshExample(inv, uuid, attribute.getKey());

            return;
        }

        if (slot == 30) {
            item = inv.getItem(30);
            meta = item.getItemMeta();

            if (slots.contains(EquipmentSlot.FEET)) {
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
                if (doubleAttribute) {
                    ((EquipmentDoubleAttribute) attribute).removeSlot(EquipmentSlot.FEET);
                } else {
                    ((EquipmentIntAttribute) attribute).removeSlot(EquipmentSlot.FEET);
                }
            } else {
                player.closeInventory();

                player.sendMessage(Component.text("Enter the amount in the chat.").decoration(TextDecoration.ITALIC, false));
                player.sendMessage(Component.text("[Cancel]")
                        .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the amount input.").color(NamedTextColor.RED)))
                        .decoration(TextDecoration.ITALIC, false)
                        .color(NamedTextColor.RED)
                        .clickEvent(ClickEvent.runCommand("/oneiroscancel defaultattribute")));

                if (doubleAttribute) {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentDoubleAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.FEET);
                } else {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentIntAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.FEET);
                }
            }

            item.setItemMeta(meta);
            inv.setItem(30, item);

            refreshExample(inv, uuid, attribute.getKey());

            return;
        }

        if (slot == 31) {
            item = inv.getItem(31);
            meta = item.getItemMeta();

            if (slots.contains(EquipmentSlot.LEGS)) {
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
                if (doubleAttribute) {
                    ((EquipmentDoubleAttribute) attribute).removeSlot(EquipmentSlot.LEGS);
                } else {
                    ((EquipmentIntAttribute) attribute).removeSlot(EquipmentSlot.LEGS);
                }
            } else {
                player.closeInventory();

                player.sendMessage(Component.text("Enter the amount in the chat.").decoration(TextDecoration.ITALIC, false));
                player.sendMessage(Component.text("[Cancel]")
                        .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the amount input.").color(NamedTextColor.RED)))
                        .decoration(TextDecoration.ITALIC, false)
                        .color(NamedTextColor.RED)
                        .clickEvent(ClickEvent.runCommand("/oneiroscancel defaultattribute")));

                if (doubleAttribute) {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentDoubleAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.LEGS);
                } else {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentIntAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.LEGS);
                }
            }

            item.setItemMeta(meta);
            inv.setItem(31, item);

            refreshExample(inv, uuid, attribute.getKey());

            return;
        }

        if (slot == 32) {
            item = inv.getItem(32);
            meta = item.getItemMeta();

            if (slots.contains(EquipmentSlot.CHEST)) {
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
                if (doubleAttribute) {
                    ((EquipmentDoubleAttribute) attribute).removeSlot(EquipmentSlot.CHEST);
                } else {
                    ((EquipmentIntAttribute) attribute).removeSlot(EquipmentSlot.CHEST);
                }
            } else {
                player.closeInventory();

                player.sendMessage(Component.text("Enter the amount in the chat.").decoration(TextDecoration.ITALIC, false));
                player.sendMessage(Component.text("[Cancel]")
                        .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the amount input.").color(NamedTextColor.RED)))
                        .decoration(TextDecoration.ITALIC, false)
                        .color(NamedTextColor.RED)
                        .clickEvent(ClickEvent.runCommand("/oneiroscancel defaultattribute")));

                if (doubleAttribute) {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentDoubleAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.CHEST);
                } else {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentIntAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.CHEST);
                }
            }

            item.setItemMeta(meta);
            inv.setItem(32, item);

            refreshExample(inv, uuid, attribute.getKey());

            return;
        }

        if (slot == 33) {
            item = inv.getItem(33);
            meta = item.getItemMeta();

            if (slots.contains(EquipmentSlot.HEAD)) {
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
                if (doubleAttribute) {
                    ((EquipmentDoubleAttribute) attribute).removeSlot(EquipmentSlot.HEAD);
                } else {
                    ((EquipmentIntAttribute) attribute).removeSlot(EquipmentSlot.HEAD);
                }
            } else {
                player.closeInventory();

                player.sendMessage(Component.text("Enter the amount in the chat.").decoration(TextDecoration.ITALIC, false));
                player.sendMessage(Component.text("[Cancel]")
                        .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the amount input.").color(NamedTextColor.RED)))
                        .decoration(TextDecoration.ITALIC, false)
                        .color(NamedTextColor.RED)
                        .clickEvent(ClickEvent.runCommand("/oneiroscancel defaultattribute")));

                if (doubleAttribute) {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentDoubleAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.HEAD);
                } else {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentIntAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.HEAD);
                }
            }

            item.setItemMeta(meta);
            inv.setItem(33, item);

            refreshExample(inv, uuid, attribute.getKey());

            return;
        }

        if (slot == 34) {
            item = inv.getItem(34);
            meta = item.getItemMeta();

            if (slots.contains(EquipmentSlot.BODY)) {
                meta.lore(List.of(Component.text("■ Click to add!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)));
                if (doubleAttribute) {
                    ((EquipmentDoubleAttribute) attribute).removeSlot(EquipmentSlot.BODY);
                } else {
                    ((EquipmentIntAttribute) attribute).removeSlot(EquipmentSlot.BODY);
                }
            } else {
                player.closeInventory();

                player.sendMessage(Component.text("Enter the amount in the chat.").decoration(TextDecoration.ITALIC, false));
                player.sendMessage(Component.text("[Cancel]")
                        .hoverEvent(HoverEvent.showText(Component.text("Click to cancel the amount input.").color(NamedTextColor.RED)))
                        .decoration(TextDecoration.ITALIC, false)
                        .color(NamedTextColor.RED)
                        .clickEvent(ClickEvent.runCommand("/oneiroscancel defaultattribute")));

                if (doubleAttribute) {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentDoubleAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.BODY);
                } else {
                    ActiveChat.addActiveChat(player.getUniqueId(), "equipmentIntAttribute:" + attribute.getKey().asString() + ";" + EquipmentSlot.BODY);
                }
            }

            item.setItemMeta(meta);
            inv.setItem(34, item);

            refreshExample(inv, uuid, attribute.getKey());

            return;
        }

        if (slot == 49) {
            player.closeInventory();

            AttributeUI attributeUI = new AttributeUI(player, 1);
            player.openInventory(attributeUI.getInventory());

        }
    }

    private void refreshExample(Inventory inv, UUID uuid, NamespacedKey key) {
        ItemStack item = ActiveItemCreation.getActiveItem(uuid).createItem((byte) 1);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "attribute"), new NamespacedKeyDataType(), key);
        item.setItemMeta(meta);
        inv.setItem(13, item);
    }

}
