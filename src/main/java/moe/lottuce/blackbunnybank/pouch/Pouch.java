package moe.lottuce.blackbunnybank.pouch;

import moe.lottuce.blackbunnybank.BlackBunnyBank;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftInventoryCustom;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pouch extends CraftInventoryCustom {

    public Pouch(@NotNull String name) {

        super(null, 36, Component.text(Objects.requireNonNull(BlackBunnyBank.lang.getString("pouch")) + "\"" + name + "\"").color(TextColor.color(127, 85, 57)));
        toolbar(this);

    }

    public Pouch() {

        super(null, 36, Component.text(Objects.requireNonNull(BlackBunnyBank.lang.getString("pouch"))).color(TextColor.color(127, 85, 57)));
        toolbar(this);

    }

    private void toolbar(Pouch pouch) {

        ItemStack renameItem = new ItemStack(Material.NAME_TAG);
        ItemMeta renameMeta = renameItem.getItemMeta();
        renameMeta.displayName(Component.text(Objects.requireNonNull(BlackBunnyBank.lang.getString("change-data"))).color(TextColor.color(127, 85, 57)).decoration(TextDecoration.ITALIC, false));
        renameItem.setItemMeta(renameMeta);

        ItemStack exchangeItem = new ItemStack(Material.PUMPKIN_SEEDS);
        ItemMeta exchangeMeta = exchangeItem.getItemMeta();
        exchangeMeta.displayName(Component.text(Objects.requireNonNull(BlackBunnyBank.lang.getString("exchange-coins"))).color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false));
        exchangeItem.setItemMeta(exchangeMeta);

        ItemStack sendItem = new ItemStack(Material.SPECTRAL_ARROW);
        ItemMeta sendMeta = sendItem.getItemMeta();
        sendMeta.displayName(Component.text(Objects.requireNonNull(BlackBunnyBank.lang.getString("send-coins"))).color(NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.ITALIC, false));
        sendMeta.addEnchant(Enchantment.LUCK, 1, false);
        sendMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sendItem.setItemMeta(sendMeta);

        ItemStack tradeItem = new ItemStack(Material.BREWING_STAND);
        ItemMeta tradeMeta = tradeItem.getItemMeta();
        tradeMeta.displayName(Component.text(Objects.requireNonNull(BlackBunnyBank.lang.getString("trade"))).color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        tradeItem.setItemMeta(tradeMeta);

        ItemStack contractItem = new ItemStack(Material.PIGLIN_BANNER_PATTERN);
        ItemMeta contractMeta = contractItem.getItemMeta();
        contractMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        contractMeta.displayName(Component.text(Objects.requireNonNull(BlackBunnyBank.lang.getString("sign-contract"))).color(NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false));
        contractItem.setItemMeta(contractMeta);

        ItemStack backgroundItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta backgroundMeta = backgroundItem.getItemMeta();
        backgroundMeta.displayName(Component.text(""));
        backgroundItem.setItemMeta(backgroundMeta);

        pouch.setItem(27, backgroundItem);
        pouch.setItem(28, backgroundItem);
        pouch.setItem(29, renameItem);
        pouch.setItem(30, exchangeItem);
        pouch.setItem(31, sendItem);
        pouch.setItem(32, tradeItem);
        pouch.setItem(33, contractItem);
        pouch.setItem(34, backgroundItem);
        pouch.setItem(35, backgroundItem);

    }

    public static ItemStack pouchItem() {

        ItemStack pouchItem = new ItemStack(Material.BROWN_DYE);

        ItemMeta pouchMeta = pouchItem.getItemMeta();
        pouchMeta.displayName(Component.text(Objects.requireNonNull(BlackBunnyBank.lang.getString("pouch"))).color(TextColor.color(127, 85, 57)).decoration(TextDecoration.ITALIC, false));
        pouchMeta.setCustomModelData(1);
        List<Component> pouchLore = new ArrayList<>();
        List<String> pouchLoreRaw = BlackBunnyBank.lang.getStringList("pouchLore");

        for (String s : pouchLoreRaw) {

            pouchLore.add(Component.text(s));

        }

        pouchMeta.lore(pouchLore);
        pouchItem.setItemMeta(pouchMeta);

        return pouchItem;

    }

    public static ShapedRecipe recipe(Plugin plugin) {

        ItemStack pouch = pouchItem();

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
