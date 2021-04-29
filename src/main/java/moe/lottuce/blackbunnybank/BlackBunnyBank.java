package moe.lottuce.blackbunnybank;

import moe.lottuce.blackbunnybank.pouch.Pouch;
import moe.lottuce.blackbunnybank.pouch.PouchListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BlackBunnyBank extends JavaPlugin {

    private Connection connection = null;
    private Statement stmt = null;

    @Override
    public void onEnable() {

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./plugins/BlackBunnyBank/blackbunnybank;AUTO_SERVER=TRUE");
            stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pouches (id IDENTITY NOT NULL PRIMARY KEY, owner UUID, trusted ARRAY, pouch OTHER)");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new PouchListener(), this);
        Bukkit.addRecipe(Pouch.pouchRecipe(this));
        getLogger().info("Enabled!");

    }

    @Override
    public void onDisable() {
        if (connection != null && stmt != null) {
            try {
                stmt.close();
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        getLogger().info("Disabled!");
    }

}
