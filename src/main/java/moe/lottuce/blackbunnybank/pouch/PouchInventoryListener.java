package moe.lottuce.blackbunnybank.pouch;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public class PouchInventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (event.getView().title().equals(Component.text("Мешочек").color(TextColor.color(127, 85, 57)))) {

            event.getWhoClicked().sendMessage(Component.text("Тьху."));

            ItemStack clickedItem = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();

            if (clickedItem != null) {

                if (clickedItem.getItemMeta().hasCustomModelData() && clickedItem.getType() == Material.WRITTEN_BOOK) {

                    if (clickedItem.getItemMeta().getCustomModelData() == 3 || clickedItem.getItemMeta().getCustomModelData() == 4) {

                        player.sendMessage(Component.text("Капиц, эта сто, ФЭИКАВЫЕ МАНЕТЫ??!!!1").color(NamedTextColor.RED));

                    }

                } else {

                    switch (clickedItem.getType()) {
                        case NAME_TAG, PUMPKIN_SEEDS, SPECTRAL_ARROW, BREWING_STAND, PIGLIN_BANNER_PATTERN -> {

                            event.setCancelled(true);
                            player.sendMessage(Component.text("Пака сто ни лаботаит т-т").color(NamedTextColor.RED));

                        }
                        default -> event.setCancelled(true);

                    }

                }

            }

        }

    }

    public void onDrag(InventoryDragEvent event) {



    }

}
