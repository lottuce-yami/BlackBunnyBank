package moe.lottuce.blackbunnybank.pouch;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Pouch {

    public static void openPouch(Player player) {

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

}
