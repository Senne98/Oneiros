package com.senne.oneiros.UI.itemCreation.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.*;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.ActivePackCreation;
import com.senne.oneiros.item.ItemRegister;
import com.senne.oneiros.item.Pack;
import com.senne.oneiros.tools.chatTextAPI.AsyncChatInputEvent;
import com.senne.oneiros.tools.chatTextAPI.AsyncRunnableSend;
import com.senne.oneiros.tools.chatTextAPI.ChatInputAPI;
import com.senne.oneiros.tools.utils.IntUtils;
import com.senne.oneiros.tools.utils.ItemUtils;
import com.senne.oneiros.tools.utils.StringUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.UUID;

import static com.senne.oneiros.tools.utils.InventoryUtils.openInvSync;

public class ItemCreationChatInputListener implements Listener {

    @EventHandler
    public void onChat(AsyncChatInputEvent e) {
        /*NamespacedKey key = e.namespacedKey();

        if (!key.getNamespace().equals("oneiros")) return;

        Player player = e.getPlayer();
        String input = PlainTextComponentSerializer.plainText().serialize(e.input());

        if (key.getKey().equals("packname")) {
            packName(input, player);
            return;
        }
        if (key.getKey().equals("packcmd")) {
            packCMD(input, player);
            return;
        }
        if (key.getKey().equals("packauthor")) {
            packAuthor(input, player);
            return;
        }
        if (key.getKey().equals("packnamefirst")) {
            packNameFirst(input, player);
            return;
        }
        if (key.getKey().equals("itemkey")) {
            itemKey(input, player);
            return;
        }
        if (key.getKey().equals("itemname")) {
            itemName(input, player);
            return;
        }
        if (key.getKey().equals("itemcmd")) {
            itemCMD(input, player);
            return;
        }
        if (key.getKey().equals("itemlore")) {
            itemLore(input, player);
            return;
        }*/


    }

    /*private static void packName(String input, Player player) {
        UUID uuid = player.getUniqueId();
        if (StringUtils.containsOnlyLetters(input) && !input.isEmpty()) ActivePackCreation.getActivePack(uuid).setName(input);
        openInvSync(player, new PackCreationUI(player).getInventory());
    }

    private static void packCMD(String input, Player player) {
        /*if (!IntUtils.isInt(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) > 9999999) {

            ChatInputAPI.newInput(player, new NamespacedKey("oneiros", "packcmd"),
                    player1 -> openInvSync(player1, new PackCreationUI(player1).getInventory()),
                    new AsyncRunnableSend() {
                        @Override
                        public void run(Player player, NamespacedKey namespacedKey, Component input, String data) {
                            this();
                        }
                    }, "Please enter a number between 0 and 9999999.");


            /*ChatInputAPI.newInput(player, new NamespacedKey("oneiros", "packcmd"), player1 -> {
                PackCreationUI ui1 = new PackCreationUI(player1);
                player1.openInventory(ui1.getInventory());
            }, "Please enter a number between 0 and 9999999.");*/
        /*}

        ActivePackCreation.getActivePack(player.getUniqueId()).setCmd(Integer.parseInt(input));
        openInvSync(player, new PackCreationUI(player).getInventory());*/
    /*}

    private static void packAuthor(String input, Player player) {
        ActivePackCreation.getActivePack(player.getUniqueId()).addAuthor(input);
        openInvSync(player, new AuthorsUI(player).getInventory());
    }

    private static void packNameFirst(String input, Player player) {
        if (StringUtils.containsOnlyLetters(input) && !input.isEmpty()) {
            ActivePackCreation.addActivePack(player.getUniqueId(), new Pack(input));
            openInvSync(player, new PackCreationUI(player).getInventory());
            return;
        }
        openInvSync(player, new PackSelectUI(player, 1).getInventory());
    }

    private static void itemKey(String input, Player player) {
        if (!StringUtils.containsOnlyLetters(input)) {
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "itemkey"), p -> {
                ItemCreationUI ui = new ItemCreationUI(p);
                p.openInventory(ui.getInventory());
            }, "Please enter a key with only letters!");
            return;
        }

        if (ItemUtils.containsNamespacedKey(ItemRegister.getPackContent(ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace()), new NamespacedKey(ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace(), input))) {
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "itemkey"), p -> {
                ItemCreationUI ui = new ItemCreationUI(p);
                p.openInventory(ui.getInventory());
            }, "A key with that name already exists!");
            return;
        }

        ActiveItemCreation.getActiveItem(player.getUniqueId()).setKey(input);
        ItemRegister.registerItem(ActiveItemCreation.getActiveItem(player.getUniqueId()), ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace());

        player.sendMessage(Component.text("Item ")
                .append(Component.text(ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespacedKey().asString()).decoration(TextDecoration.BOLD, true).color(NamedTextColor.YELLOW)
                        .append(Component.text(" created!").decoration(TextDecoration.BOLD, false).color(NamedTextColor.WHITE))));
        ActiveItemCreation.removeActiveItem(player.getUniqueId());

    }

    private static void itemName(String input, Player player) {
        ActiveItemCreation.getActiveItem(player.getUniqueId())
                .setDisplayName(Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .append(MiniMessage.miniMessage().deserialize(input).asComponent())
                        .asComponent()
                );

        openInvSync(player, new ItemCreationUI(player).getInventory());
    }

    private static void itemCMD(String input, Player player) {
        if (!IntUtils.isInt(input)) {
            player.sendMessage(Component.text("Please enter a number!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        int cmd = Integer.parseInt(input);
        if (cmd > 9999999 || cmd < 0) {
            player.sendMessage(Component.text("Please enter a number between 0 and 9999999!").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.RED));
            return;
        }

        openInvSync(player, new ItemCreationUI(player).getInventory());
    }

    private static void itemLore(String input, Player player) {
        List<Component> lore = ActiveItemCreation.getActiveItem(player.getUniqueId()).getLore();
        lore.add(Component.text().decoration(TextDecoration.ITALIC, false).color(NamedTextColor.GRAY).append(MiniMessage.miniMessage().deserialize(input).asComponent()).asComponent());
        ActiveItemCreation.getActiveItem(player.getUniqueId()).setLore(lore);
        openInvSync(player, new LoreUI(player).getInventory());
    }*/
}
