package moe.lottuce.blackbunnybank;

import moe.lottuce.blackbunnybank.pouch.PouchUseListener;
import moe.lottuce.blackbunnybank.pouch.PouchInventoryListener;
import moe.lottuce.blackbunnybank.pouch.PouchRecipe;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BlackBunnyBank extends JavaPlugin {

    Connection connection = null;
    Statement stmt = null;

    public void onEnable() {

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./plugins/BlackBunnyBank/blackbunnybank;AUTO_SERVER=TRUE");
            stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pouches (id IDENTITY NOT NULL PRIMARY KEY, owner UUID, trusted ARRAY, pouch OTHER)");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new PouchInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PouchUseListener(), this);
        Bukkit.addRecipe(PouchRecipe.pouchRecipe(this));
        getLogger().info("Enabled!");

    }

    public void onDisable() {
        getLogger().info("Disabled!");
    }

}
