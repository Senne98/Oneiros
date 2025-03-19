package com.senne.oneiros.item;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.action.ActionHandler;
import com.senne.oneiros.atributes.attributeTypes.Attribute;
import com.senne.oneiros.tools.utils.AttributeUtils;
import com.senne.oneiros.tools.dataTypes.NamespacedKeyDataType;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private int cmd;
    private Material material;
    private Component displayName;
    private List<Component> lore = new ArrayList<>();
    private List<Attribute> attributes = new ArrayList<>();
    private List<ActionHandler> actionHandlers = new ArrayList<>();
    private String namespace = "";
    private String key = "";

    public Item(Material material) {
        this.material = material;
    }

    public ItemStack createItem(int amount) {
        ItemStack item = new ItemStack(material, amount);

        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(cmd);
        meta.displayName(displayName);
        meta.lore(lore);

        if (namespace != "" && key != "") {
            meta.getPersistentDataContainer().set(new NamespacedKey(Oneiros.getPlugin(), "customitem"), new NamespacedKeyDataType(), getNamespacedKey());
        }

        item.setItemMeta(meta);

        // Apply attributes
        for (int i = 0; i < attributes.size(); i++) {
            item = attributes.get(i).applyAttribute(item);
        }
        
        return item;
    }

    //unit test to be added
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

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public void removeAttribute(Attribute attribute) {
        AttributeUtils.removeEveryInstance(attributes, attribute);
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
        if (lore == null) {
            this.lore = new ArrayList<>();
            return;
        }
        this.lore = lore;
    }

    public NamespacedKey getNamespacedKey() {
        return new NamespacedKey(namespace, key);
    }

    public void setNamespacedKey(NamespacedKey key) {
        this.namespace = key.getNamespace();
        this.key = key.getKey();
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

    public static Item deserialize(byte[] data) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (cmd != item.cmd) return false;
        if (material != item.material) return false;
        if ((displayName == null && item.displayName == null) || !displayName.equals(item.displayName)) return false;
        if (!lore.equals(item.lore)) return false;
        if (!attributes.equals(item.attributes)) return false;
        if (!actionHandlers.equals(item.actionHandlers)) return false;
        if ((namespace == null && item.namespace == null) || !namespace.equals(item.namespace)) return false;
        return (key == null && item.key == null) || key.equals(item.key);
    }
}
