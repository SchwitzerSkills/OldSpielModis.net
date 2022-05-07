package de.oldspielmodies.lobby;

import de.oldspielmodies.commands.BuildCommand;
import de.oldspielmodies.commands.NewsCommand;
import de.oldspielmodies.commands.SetWarpsCommand;
import de.oldspielmodies.items.*;
import de.oldspielmodies.listener.BuildListener;
import de.oldspielmodies.listener.PlayerCoinsChangeListener;
import de.oldspielmodies.listener.PlayerConnetionListener;
import de.oldspielmodies.listener.PlayerMoveListener;
import de.oldspielmodies.manager.*;
import de.oldspielmodies.mysql.*;
import de.oldspielmodies.scoreboard.ScoreboardManager;
import net.minecraft.server.v1_8_R3.EnumParticle;
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

            Gadget gadget = new Gadget();
            gadget.createTable();

            News news = new News();
            news.createTables();
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
        CircleManager circleManager = new CircleManager();
        LocationManager locationManager = new LocationManager();
        circleManager.drawCircle(locationManager.getLocation("spawn"), (float) 1.4, EnumParticle.SPELL, true);
        getServer().getPluginCommand("setwarp").setExecutor(new SetWarpsCommand());
        getServer().getPluginCommand("build").setExecutor(new BuildCommand());
        getServer().getPluginCommand("news").setExecutor(new NewsCommand());
        pluginManager.registerEvents(new PlayerConnetionListener(), this);
        pluginManager.registerEvents(new Compass(), this);
        pluginManager.registerEvents(new BuildListener(), this);
        pluginManager.registerEvents(new PlayerCoinsChangeListener(), this);
        pluginManager.registerEvents(new Settings(), this);
        pluginManager.registerEvents(new Grapplinghook(), this);
        pluginManager.registerEvents(new PlayerManager(), this);
        pluginManager.registerEvents(new Playerhider(), this);
        pluginManager.registerEvents(new Gadgets(), this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);
        pluginManager.registerEvents(new Nicktool(), this);
        pluginManager.registerEvents(new Lobbyswitcher(), this);
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
