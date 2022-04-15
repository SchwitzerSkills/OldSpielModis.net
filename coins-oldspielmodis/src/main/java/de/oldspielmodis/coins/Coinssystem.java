package de.oldspielmodis.coins;

import de.oldspielmodis.coins.commands.CoinsCommand;
import de.oldspielmodis.coins.event.PlayerCoinsChangeEvent;
import de.oldspielmodis.coins.listeners.JoinListener;
import de.oldspielmodis.coins.mysql.Coins;
import de.oldspielmodis.coins.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Coinssystem extends JavaPlugin {

    public static String PREFIX = "§8× §eOldSpielModis §8┃ §7", NO_PERMS = PREFIX + "§cYou don't have the permission to use this command!";

    public File file = new File("plugins/Coinssystem/mysql.yml");
    public YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static Coinssystem instance;

    private MySQL mySQL;

    @Override
    public void onEnable() {
        createFiles();
        instance = this;
        this.mySQL = MySQL.newBuilder()
                .withUser(cfg.getString("MySQL.username"))
                .withPassword(cfg.getString("MySQL.password"))
                .withDatabase(cfg.getString("MySQL.database"))
                .withUrl(cfg.getString("MySQL.host"))
                .withPort(3306)
                .create();
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§aCoinSystem aktiviert");

        Coins coin = new Coins();
        coin.createTable();

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    public void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
    }

    public void registerCommands(){
        getCommand("coins").setExecutor(new CoinsCommand());
    }

    public static Coinssystem getInstance() {
        return instance;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public void createFiles(){
        try{
            if(!file.exists()){
                cfg.set("MySQL.username", "root");
                cfg.set("MySQL.password", "password");
                cfg.set("MySQL.database", "database");
                cfg.set("MySQL.host", "localhost");
                cfg.save(file);
            }
        } catch (Exception e){
        }
    }
}
