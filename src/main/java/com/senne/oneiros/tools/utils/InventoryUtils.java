package com.senne.oneiros.tools.utils;

import com.senne.oneiros.Oneiros;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryUtils {

    public static void openInvSync(Player player, Inventory inv) {
        Bukkit.getScheduler().callSyncMethod(Oneiros.getPlugin(), () -> {
            player.openInventory(inv);
            return null;
        });
    }

}
