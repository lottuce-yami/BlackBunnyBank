package moe.lottuce.blackbunnybank.pouch;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class PouchRecipe {

    public static ShapedRecipe pouchRecipe(Plugin plugin) {

        ItemStack pouch = new ItemStack(Material.BROWN_DYE);

        ItemMeta pouchMeta = pouch.getItemMeta();

        pouchMeta.displayName(Component.text("Мешочек").color(TextColor.color(127, 85, 57)).decoration(TextDecoration.ITALIC, false));
        pouchMeta.setCustomModelData(1);

        List<Component> pouchLore = new ArrayList<>();
        pouchLore.add(Component.text(""));
        pouchLore.add(Component.text("Волшебный мешочек, в котором вы храните монетки."));
        pouchLore.add(Component.text("Но как же он работает?..."));
        pouchLore.add(Component.text(""));
        pouchMeta.lore(pouchLore);

        pouch.setItemMeta(pouchMeta);

        NamespacedKey key = new NamespacedKey(plugin, "pouch");
        ShapedRecipe recipe = new ShapedRecipe(key, pouch);

        recipe.shape(" H ", "LEL", "HCH");
        recipe.setIngredient('C', Material.CHORUS_FRUIT);
        recipe.setIngredient('H', Material.RABBIT_HIDE);
        recipe.setIngredient('E', Material.ENDER_EYE);
        recipe.setIngredient('L', Material.LEATHER);

        return recipe;

    }

}
