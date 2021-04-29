package moe.lottuce.blackbunnybank.pouch;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public interface Pouch {

    static void openPouch(Player player) {

        Inventory pouch = Bukkit.getServer().createInventory(null, 36, Component.text("Мешочек").color(TextColor.color(127, 85, 57)));

        ItemStack nameItem = new ItemStack(Material.NAME_TAG);
        ItemMeta nameMeta = nameItem.getItemMeta();
        nameMeta.displayName(Component.text("Переименовать мешочек").color(TextColor.color(127, 85, 57)).decoration(TextDecoration.ITALIC, false));
        nameItem.setItemMeta(nameMeta);

        ItemStack exchangeItem = new ItemStack(Material.PUMPKIN_SEEDS);
        ItemMeta exchangeMeta = exchangeItem.getItemMeta();
        exchangeMeta.displayName(Component.text("Разменять монеты").color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false));
        exchangeItem.setItemMeta(exchangeMeta);

        ItemStack sendItem = new ItemStack(Material.SPECTRAL_ARROW);
        ItemMeta sendMeta = sendItem.getItemMeta();
        sendMeta.displayName(Component.text("Сделать перевод").color(NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.ITALIC, false));
        sendMeta.addEnchant(Enchantment.LUCK, 1, false);
        sendMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sendItem.setItemMeta(sendMeta);

        ItemStack tradeItem = new ItemStack(Material.BREWING_STAND);
        ItemMeta tradeMeta = tradeItem.getItemMeta();
        tradeMeta.displayName(Component.text("Совершить сделку").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        tradeItem.setItemMeta(tradeMeta);

        ItemStack contractItem = new ItemStack(Material.PIGLIN_BANNER_PATTERN);
        ItemMeta contractMeta = contractItem.getItemMeta();
        contractMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        contractMeta.displayName(Component.text("Подписать договор").color(NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false));
        contractItem.setItemMeta(contractMeta);


        ItemStack backgroundItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta backgroundMeta = backgroundItem.getItemMeta();
        backgroundMeta.displayName(Component.text(""));
        backgroundItem.setItemMeta(backgroundMeta);

        pouch.setItem(27, backgroundItem);
        pouch.setItem(28, backgroundItem);
        pouch.setItem(29, nameItem);
        pouch.setItem(30, exchangeItem);
        pouch.setItem(31, sendItem);
        pouch.setItem(32, tradeItem);
        pouch.setItem(33, contractItem);
        pouch.setItem(34, backgroundItem);
        pouch.setItem(35, backgroundItem);

        player.openInventory(pouch);

    }

    static ItemStack getPouchItem() {

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

        return pouch;

    }

    static ShapedRecipe pouchRecipe(Plugin plugin) {

        ItemStack pouch = getPouchItem();

        NamespacedKey key = new NamespacedKey(plugin, "pouch");
        ShapedRecipe recipe = new ShapedRecipe(key, pouch);

        recipe.shape(" H ", "LEL", "HCH");
        recipe.setIngredient('C', Material.CHORUS_FRUIT);
        recipe.setIngredient('H', Material.RABBIT_HIDE);
        recipe.setIngredient('E', Material.ENDER_EYE);
        recipe.setIngredient('L', Material.LEATHER);

        return recipe;

    }

    static void registerPouch(Player player) {



    }

}
