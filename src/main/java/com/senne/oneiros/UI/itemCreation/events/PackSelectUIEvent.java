package com.senne.oneiros.UI.itemCreation.events;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.UI.itemCreation.inventories.ItemCreationUI;
import com.senne.oneiros.UI.itemCreation.inventories.PackCreationUI;
import com.senne.oneiros.UI.itemCreation.inventories.PackSelectUI;
import com.senne.oneiros.item.ActiveItemCreation;
import com.senne.oneiros.item.ActivePackCreation;
import com.senne.oneiros.item.ItemRegister;
import com.senne.oneiros.item.Pack;
import com.senne.oneiros.tools.chatTextAPI.AsyncRunnableSend;
import com.senne.oneiros.tools.chatTextAPI.ChatInputAPI;
import com.senne.oneiros.tools.utils.ItemUtils;
import com.senne.oneiros.tools.utils.StringUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static com.senne.oneiros.tools.utils.InventoryUtils.openInvSync;

public class PackSelectUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof PackSelectUI)) {
            return;
        }

        e.setCancelled(true);

        if (e.getClickedInventory() == null) return;
        if (e.getRawSlot() > 53 || e.getRawSlot() < 0) return;

        int slot = e.getSlot();
        ItemStack item = e.getInventory().getItem(slot);
        PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
        Player player = (Player) e.getWhoClicked();

        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "addPack"))) {
            player.closeInventory();
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "packnamefirst"),
                    p -> openInvSync(p, new PackSelectUI(p, 1).getInventory()),
                    (player1, namespacedKey, message, data1) -> {
                        String input = PlainTextComponentSerializer.plainText().serialize(message);
                        if (StringUtils.containsOnlyLetters(input) && !input.isEmpty()) {
                            ActivePackCreation.addActivePack(player1.getUniqueId(), new Pack(input));
                            openInvSync(player1, new PackCreationUI(player1).getInventory());
                            return;
                        }
                        openInvSync(player1, new PackSelectUI(player1, 1).getInventory());
                    }, "Enter the name of the pack in the chat.");

            return;
        }

        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "pack"))) {
            String pack = data.get(new NamespacedKey(Oneiros.getPlugin(), "pack"), PersistentDataType.STRING);
            ActiveItemCreation.getActiveItem(player.getUniqueId()).setNamespace(pack);

            player.closeInventory();
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "itemkey"),
                    p -> openInvSync(p, new ItemCreationUI(p).getInventory()),
                    new ItemKeySend(), "Enter the key of the item in the chat.");

            return;
        }

        if (data.has(new NamespacedKey(Oneiros.getPlugin(), "page"))) {
            int page = data.get(new NamespacedKey(Oneiros.getPlugin(), "page"), PersistentDataType.INTEGER);

            PackSelectUI ui = new PackSelectUI(player, page);
            player.openInventory(ui.getInventory());
            return;
        }

        if (slot == 49) {
            ItemCreationUI ui = new ItemCreationUI(player);
            player.openInventory(ui.getInventory());
            return;
        }
    }
}

class ItemKeySend implements AsyncRunnableSend {

    @Override
    public void run(Player player, NamespacedKey namespacedKey, Component message, String data) {
        String input = PlainTextComponentSerializer.plainText().serialize(message);

        if (!StringUtils.containsOnlyLetters(input)) {
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "itemkey"),
                    p -> openInvSync(p, new ItemCreationUI(p).getInventory()),
                    new ItemKeySend(), "Please enter a key with only letters!");
            return;
        }

        if (ItemUtils.containsNamespacedKey(ItemRegister.getPackContent(ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace()), new NamespacedKey(ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace(), input))) {
            ChatInputAPI.newInput(player, new NamespacedKey(Oneiros.getPlugin(), "itemkey"),
                    p -> openInvSync(p, new ItemCreationUI(p).getInventory()),
                    new ItemKeySend(), "A key with that name already exists!");
            return;
        }

        ActiveItemCreation.getActiveItem(player.getUniqueId()).setKey(input);
        ItemRegister.registerItem(ActiveItemCreation.getActiveItem(player.getUniqueId()), ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespace());

        player.sendMessage(Component.text("Item ")
                .append(Component.text(ActiveItemCreation.getActiveItem(player.getUniqueId()).getNamespacedKey().asString()).decoration(TextDecoration.BOLD, true).color(NamedTextColor.YELLOW)
                        .append(Component.text(" created!").decoration(TextDecoration.BOLD, false).color(NamedTextColor.WHITE))));
        ActiveItemCreation.removeActiveItem(player.getUniqueId());
    }
}
