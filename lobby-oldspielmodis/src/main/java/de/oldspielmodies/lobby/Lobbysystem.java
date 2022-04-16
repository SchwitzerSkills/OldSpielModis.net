package de.oldspielmodies.lobby;

import de.oldspielmodies.commands.BuildCommand;
import de.oldspielmodies.commands.SetWarpsCommand;
import de.oldspielmodies.items.CompassInteract;
import de.oldspielmodies.items.SettingsInteract;
import de.oldspielmodies.listener.BuildListener;
import de.oldspielmodies.listener.PlayerCoinsChangeListener;
import de.oldspielmodies.listener.PlayerConnetionListener;
import de.oldspielmodies.manager.ActionbarManager;
import de.oldspielmodies.manager.AnimalManager;
import de.oldspielmodies.manager.MySQL;
import de.oldspielmodies.mysql.Setting;
import de.oldspielmodies.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class Lobbysystem extends JavaPlugin {

    private static Lobbysystem instance;

    public File file = new File("plugins/Lobby/mysql.yml");
    public YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    public static ArrayList<Player> build = new ArrayList<Player>();
    public static final String PREFIX = "§8× §eOldSpielModis §8┃ §7",
                                NO_PERMS = PREFIX + "§cYou don't have the permission to use this command!";

    private MySQL mySQL;

    @Override
    public void onEnable() {

        if(instance == null){
            instance = this;
            createFile();
            this.mySQL = MySQL.newBuilder()
                    .withUser(cfg.getString("MySQL.username"))
                    .withPassword(cfg.getString("MySQL.password"))
                    .withDatabase(cfg.getString("MySQL.database"))
                    .withUrl(cfg.getString("MySQL.host"))
                    .withPort(3306)
                    .create();
            init(Bukkit.getPluginManager());
            Bukkit.getConsoleSender().sendMessage(PREFIX + "Das Plugin wurde erfolgreich aktiviert!");

            Setting setting = new Setting();
            setting.createTable();

        }


    }

    @Override
    public void onDisable() {

    }

    private void init(final PluginManager pluginManager){

        AnimalManager.removeAnimals();
        ScoreboardManager scoreboardManager = new ScoreboardManager();
        scoreboardManager.startAnimation();
        ActionbarManager actionbarManager = new ActionbarManager();
        actionbarManager.createFile();
        actionbarManager.sendActionbar();
        getServer().getPluginCommand("setwarp").setExecutor(new SetWarpsCommand());
        getServer().getPluginCommand("build").setExecutor(new BuildCommand());
        pluginManager.registerEvents(new PlayerConnetionListener(), this);
        pluginManager.registerEvents(new CompassInteract(), this);
        pluginManager.registerEvents(new BuildListener(), this);
        pluginManager.registerEvents(new PlayerCoinsChangeListener(), this);
        pluginManager.registerEvents(new SettingsInteract(), this);

    }


    public static Lobbysystem getInstance() {
        return instance;
    }

    public void createFile(){
        try{
            if(!file.exists()){
                cfg.set("MySQL.username", "username");
                cfg.set("MySQL.password", "password");
                cfg.set("MySQL.database", "database");
                cfg.set("MySQL.host", "localhost");
                cfg.save(file);
            }
        } catch (Exception e){
        }
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
