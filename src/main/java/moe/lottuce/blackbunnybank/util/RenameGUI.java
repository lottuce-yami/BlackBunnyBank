package moe.lottuce.blackbunnybank.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class RenameGUI {

    static void openGUI(Player player) {

        Inventory anvil = Bukkit.createInventory(null, InventoryType.ANVIL);
        player.openInventory(anvil);

    }

}
