package com.senne.oneiros.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Pack {

    private String name;
    private String[] authors = new String[0];
    private Material icon;
    private int cmd;
    private List<Item> items = new ArrayList<>();

    public Pack(String name) {
        this.name = name;
        icon = Material.CHEST;
        cmd = -1;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public void addAuthor(String author) {
        String[] newAuthors = new String[authors.length + 1];
        System.arraycopy(authors, 0, newAuthors, 0, authors.length);
        newAuthors[authors.length] = author;
        authors = newAuthors;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public Material getIcon() {
        return icon;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(String key) {
        items.removeIf(item -> item.getNamespacedKey().getKey().equals(key));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStack generateIcon() {
        ItemStack item = new ItemStack(icon);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name).decoration(TextDecoration.ITALIC, false));

        if (authors.length > 0) {
            List<Component> lore = new ArrayList<>();

            lore.add(Component.text("Authors:").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.GRAY));

            for (String author : authors) {
                lore.add(Component.text(" ● " + author).decoration(TextDecoration.ITALIC, false).color(NamedTextColor.GRAY));
            }

            meta.lore(lore);
        }

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pack)) return false;
        Pack pack = (Pack) o;

        if (getCmd() != pack.getCmd()) return false;
        if (!getName().equals(pack.getName())) return false;
        if (!getIcon().equals(pack.getIcon())) return false;
        return getItems().equals(pack.getItems());
    }
}
