package com.senne.oneiros.item;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.action.ActionHandler;
import com.senne.oneiros.atributes.Attribute;
import com.senne.oneiros.tools.AttributeUtils;
import com.senne.oneiros.tools.NamespacedKeyDataType;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {

    private int cmd;
    private Material material;
    private Component displayName;
    private List<Component> lore = new ArrayList<>();
    private List<Attribute> attributes = new ArrayList<>();
    private List<ActionHandler> actionHandlers = new ArrayList<>();
    private String namespace;
    private String key;

    public Item(Material material) {
        this.material = material;
    }

    public ItemStack createItem(int amount) {
        ItemStack item = new ItemStack(material, amount);

        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(cmd);
        meta.displayName(displayName);
        meta.lore(lore);

        if (namespace != null && key != null) {
            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "customItem"), new NamespacedKeyDataType(), getNamespacedKey());
        }

        item.setItemMeta(meta);

        // Apply attributes
        for (int i = 0; i < attributes.size(); i++) {
            item = attributes.get(i).applyAttribute(item);
        }
        
        return item;
    }

    public List<ActionHandler> getActionHandlers() {
        return actionHandlers;
    }

    public void addActionHandler(ActionHandler actionHandler) {
        actionHandlers.add(actionHandler);
    }

    public void setActionHandlers(List<ActionHandler> actionHandlers) {
        this.actionHandlers = actionHandlers;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public void removeAttribute(Attribute attribute) {
        AttributeUtils.removeInstance(attributes, attribute);
    }

    public Attribute getAttribute(NamespacedKey key) {
        for (Attribute attribute : attributes) {
            if (attribute.getKey().equals(key)) {
                return attribute;
            }
        }
        return null;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Component getDisplayName() {
        return displayName;
    }

    public void setDisplayName(Component displayName) {
        this.displayName = displayName;
    }

    public List<Component> getLore() {
        return lore;
    }

    public void setLore(List<Component> lore) {
        this.lore = lore;
    }

    public NamespacedKey getNamespacedKey() {
        return new NamespacedKey(namespace, key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public byte[] serialize() {
        return SerializationUtils.serialize(this);
    }

    public static Item deserialize(byte[] data) {
        return SerializationUtils.deserialize(data);
    }
}
