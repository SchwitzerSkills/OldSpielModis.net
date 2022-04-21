package de.oldspielmodis.spigot.nick;

import de.oldspielmodis.spigot.nick.commands.UnnickCommand;
import de.oldspielmodis.spigot.nick.listeners.JoinListener;
import de.oldspielmodis.spigot.nick.mysql.MySQL;
import de.oldspielmodis.spigot.nick.mysql.Nick;
import de.oldspielmodis.spigot.nick.mysql.Nickname;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import eu.thesimplecloud.module.permission.player.PlayerPermissionGroupInfo;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Nickapi extends JavaPlugin {

    public static final String PREFIX = "§8× §eOldSpielModis §8┃ §7",
            NO_PERMS = PREFIX + "§cYou don't have the permission to use this command!";

    public File file = new File("plugins/Nickapi/mysql.yml");
    public YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    private MySQL mySQL;

    public static Nickapi instance;

    @Override
    public void onEnable() {
        instance = this;
        createFile();
        this.mySQL = MySQL.newBuilder()
                .withUser(cfg.getString("MySQL.username"))
                .withPassword(cfg.getString("MySQL.password"))
                .withDatabase(cfg.getString("MySQL.database"))
                .withUrl(cfg.getString("MySQL.host"))
                .withPort(3306)
                .create();
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§aNickAPI aktiviert");

        registerListeners();
        registerCommands();

        Nick nick = new Nick();
        nick.createTable();

        Nickname nickname = new Nickname();
        nickname.createTable();
    }

    @Override
    public void onDisable() {
        init();
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§cNickAPI deaktiviert");
    }

    public void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
    }

    public void registerCommands(){
        getCommand("unnick").setExecutor(new UnnickCommand());
    }

    public void createFile(){
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

    public static Nickapi getInstance() {
        return instance;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public void init(){
        Bukkit.getOnlinePlayers().forEach(all -> {
          JoinListener joinListener = new JoinListener();
          joinListener.nicked(all);
        });
    }
}
