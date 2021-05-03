package moe.lottuce.blackbunnybank;

import moe.lottuce.blackbunnybank.pouch.Pouch;
import moe.lottuce.blackbunnybank.pouch.PouchListener;
import moe.lottuce.blackbunnybank.util.BookListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;

public class BlackBunnyBank extends JavaPlugin {

    public static YamlConfiguration lang = new YamlConfiguration();
    public final String LANG_CODE = getConfig().getString("language");
    private final File LANG_FILE = new File(getDataFolder() + "/languages/", LANG_CODE + ".yml");

    private Connection connection = null;
    private Statement stmt = null;


    @Override
    public void onEnable() {

        saveDefaultConfig();
        loadLanguage();
        database(true);

        getServer().getPluginManager().registerEvents(new PouchListener(), this);
        getServer().getPluginManager().registerEvents(new BookListener(), this);
        Bukkit.addRecipe(Pouch.recipe(this));

        getLogger().info("Enabled!");

    }

    @Override
    public void onDisable() {

        database(false);

        getLogger().info("Disabled!");

    }

    private void loadLanguage() {

        try {
            if (!LANG_FILE.exists()) {

                if (!LANG_FILE.getParentFile().exists()) {

                    boolean dirCreated = LANG_FILE.getParentFile().mkdirs();
                    if (!dirCreated) getLogger().log(Level.SEVERE,"Could not create a languages directory!");

                }

                InputStream langResource = BlackBunnyBank.class.getResourceAsStream("/languages/" + LANG_CODE + ".yml");
                OutputStream out = new FileOutputStream(LANG_FILE);
                Objects.requireNonNull(langResource).transferTo(out);

            }
            lang.load(LANG_FILE);

        } catch (IOException | InvalidConfigurationException e) {

            e.printStackTrace();

        }

    }

    private void database(boolean enable) {

        if (enable) {
            try {

                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:./plugins/BlackBunnyBank/blackbunnybank;AUTO_SERVER=TRUE");
                stmt = connection.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pouches (id IDENTITY NOT NULL PRIMARY KEY, owner UUID, trusted ARRAY, pouchName VARCHAR, pouch OTHER)");
            } catch (ClassNotFoundException | SQLException e) {

                getLogger().log(Level.SEVERE, "Could not load the database! Disabling the plugin.");
                e.printStackTrace();
                this.setEnabled(true);

            }
        } else {
            if (connection != null && stmt != null) {
                try {

                    stmt.close();
                    connection.close();

                } catch(SQLException e) {

                    getLogger().log(Level.SEVERE, "Could not correctly unload the database! This may produce errors.");
                    e.printStackTrace();

                }
            }
        }

    }

}
