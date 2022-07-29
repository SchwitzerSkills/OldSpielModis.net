package de.schwitzerskills.spigot.skywars;

import de.schwitzerskills.spigot.coinsystem.mysql.Coins;
import de.schwitzerskills.spigot.skywars.commands.*;
import de.schwitzerskills.spigot.skywars.listeners.*;
import de.schwitzerskills.spigot.skywars.mysql.*;
import de.schwitzerskills.spigot.skywars.utils.*;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SkyWars extends JavaPlugin {

    public static String PREFIX = "§cSkyWars §8: §7";
    public static String noPERMS = PREFIX + "§cDu hast keine Berechtigung!";
    public static String noCS = "§cDu musst ein Spieler sein!";

    public static SkyWars instance;
    private GameState gameState;

    public File file = new File("plugins/SkyWars/mysql.yml");
    public YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public int maxplayers = 2; // 8

    public ArrayList<Player> spec = new ArrayList<>();
    public ArrayList<Player> nonspec = new ArrayList<>();

    private MySQL mySQL;
    public LocationManager lm;

    public HashMap<Player, Integer> kills = new HashMap<>();

    @Override
    public void onEnable() {
        createFile();
        instance = this;
        this.mySQL = MySQL.newBuilder()
                .withUser(cfg.getString("MySQL.username"))
                .withPassword(cfg.getString("MySQL.password"))
                .withDatabase(cfg.getString("MySQL.database"))
                .withUrl(cfg.getString("MySQL.host"))
                .withPort(3306)
                .create();
        new Kits().createTable();
        new Players().createTable();
        new KitSystem().createTable();
        new MapSystem().createTable();
        new Maps().createTable();
        setGameState(GameState.LOBBY);
        settings();
        lm = new LocationManager();
        lm.saveCfg();
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§aDu hast das Plugin aktiviert");
        registerListeners();
        registerCommands();
        warten();
        getRandomMap();
    }

    public void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinQuitListener(), this);
        pm.registerEvents(new GameManager(), this);
        pm.registerEvents(new CommandListener(), this);
        pm.registerEvents(new BuildListener(), this);
        pm.registerEvents(new ServerPingListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new ClickListener(), this);
        pm.registerEvents(new EnterhakenListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new MoveListener(), this);
        pm.registerEvents(new SchutzzeitListener(), this);
        pm.registerEvents(new ChestManager(), this);
        pm.registerEvents(new ChatListener(), this);
    }

    public void registerCommands(){
        getCommand("start").setExecutor(new CMD_start());
        getCommand("map").setExecutor(new CMD_map());
        getCommand("forcemap").setExecutor(new CMD_forcemap());
        getCommand("set").setExecutor(new CMD_set());
        getCommand("setspawn").setExecutor(new CMD_setspawn());
        getCommand("setheight").setExecutor(new CMD_setheight());
    }

    public void settings(){
        for(World world : Bukkit.getWorlds()){
            world.setDifficulty(Difficulty.PEACEFUL);
            world.setWeatherDuration(0);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, () -> {
                world.setTime(1000);
            }, 0, 0);
            for(Entity entity : world.getEntities()){
                if(!(entity instanceof Player)){
                    entity.remove();
                }
            }
        }
    }

    public int count;

    public void warten(){
        count = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(all -> {
                new Actionbar(SkyWars.PREFIX + "§7Warten auf §c" + SkyWars.getInstance().maxplayers + "§7 Spieler...").send(all);
            });
        }, 1, 1);
    }

    public void ende(){
        if (SkyWars.getInstance().nonspec.size() == 1) {
            Bukkit.getScheduler().cancelAllTasks();
            SkyWars.getInstance().setGameState(GameState.RESTART);
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), () -> {
                new EndeCountdown().start();
            }, 10);
            Bukkit.getOnlinePlayers().forEach(all -> {
                new ScoreboardBuilder().scoreboard3(all);
                Bukkit.getScheduler().cancelTask(ScoreboardBuilder.count);
                Title.sendTitle(all, 20, 40, 20, "§a" + SkyWars.getInstance().nonspec.iterator().next().getName(), "§7hat das Spiel gewonnen");
                if(SkyWars.getInstance().spec.contains(all)) {
                    SkyWars.getInstance().nonspec.forEach(all1 -> {
                        all1.showPlayer(all);
                        new Coins().addCoins(all1.getUniqueId().toString(), 40);
                        all1.sendMessage(SkyWars.PREFIX + "§e+40 Coins");
                        all1.showPlayer(all1);
                    });
                    all.showPlayer(all);
                    SkyWars.getInstance().spec.remove(all);
                }
                all.setAllowFlight(false);
                all.removePotionEffect(PotionEffectType.INVISIBILITY);
                all.playSound(all.getLocation(), Sound.LEVEL_UP, 10, 10);
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), () -> {
                    all.getInventory().setArmorContents(null);
                    all.getInventory().clear();
                    new ScoreboardBuilder().setPrefix(all);
                    new ScoreboardBuilder().updatePrefix(all);
                    all.setHealth(20);
                    all.setFoodLevel(20);
                    all.teleport(SkyWars.getInstance().lm.getLocation("Spawn"));
                    all.getInventory().setItem(4, new ItemBuilder(Material.FIREBALL).setName("§8➤ §e§lSpiel verlassen §8[§7Rechtsklick§8]").toItemStack());
                }, 5);
            });
        }
    }

    public void getRandomMap(){
        Random rand = new Random();
        int maxNumber = 3;

        int randomNumber = rand.nextInt(maxNumber) + 1;

        if(!new Maps().isMap()) {
            new Maps().setMap(new MapSystem().getMap(randomNumber));
        } else {
            new Maps().switchMap(new MapSystem().getMap(randomNumber));
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§cDu hast das Plugin deaktiviert");
    }

    public static SkyWars getInstance() {
        return instance;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gamestate){
        this.gameState = gamestate;
    }

    public MySQL getMySQL() {
        return mySQL;
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
}
