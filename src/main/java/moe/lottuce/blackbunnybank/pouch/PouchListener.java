package moe.lottuce.blackbunnybank.pouch;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PouchListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        ItemStack clickedItem = event.getCurrentItem();

        if (event.getClickedInventory() instanceof Pouch && clickedItem != null) {

            Player player = (Player) event.getWhoClicked();

            // TODO class for handling all the coins types
                if (clickedItem.getItemMeta().hasCustomModelData() && clickedItem.getType() == Material.WRITTEN_BOOK) {
                    if (clickedItem.getItemMeta().getCustomModelData() == 3 || clickedItem.getItemMeta().getCustomModelData() == 4) {

                        player.sendMessage(Component.text("Капиц, эта сто, ФЭИКАВЫЕ МАНЕТЫ??!!!1").color(NamedTextColor.RED)); // bruh

                    }
                } else {
                    switch (clickedItem.getType()) {

                        case NAME_TAG, PUMPKIN_SEEDS, SPECTRAL_ARROW, BREWING_STAND, PIGLIN_BANNER_PATTERN -> {

                            event.setCancelled(true);
                            player.sendMessage(Component.text("Пака сто ни лаботаит т-т").color(NamedTextColor.RED)); // bruh

                        }
                        default -> event.setCancelled(true);

                    }
                }

            }

        }

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {

        ItemStack clickedItem = event.getItem();

        if (clickedItem != null && clickedItem.equals(Pouch.pouchItem())) {

            Action action = event.getAction();
            Player player = event.getPlayer();

            switch (action) {
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> player.openInventory(new Pouch());
            }

        }

    }

}
