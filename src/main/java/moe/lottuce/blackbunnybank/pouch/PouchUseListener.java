package moe.lottuce.blackbunnybank.pouch;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class PouchUseListener implements Listener {

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {

        ItemStack item = event.getItem();

        if (item.getItemMeta().hasCustomModelData() && item.getType() == Material.BROWN_DYE) {

            event.setCancelled(true);

        }

    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        ItemStack item = event.getItem();
        if (item != null) {

            if (item.getItemMeta().hasCustomModelData() && item.getType() == Material.BROWN_DYE) {

                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    Pouch.openPouch(event.getPlayer());

                }
            }

        }

    }

}
