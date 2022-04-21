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
        Nickname nickname = new Nickname();
        Nick nick = new Nick();
        Bukkit.getOnlinePlayers().forEach(all -> {
            if(nick.isNicked(all.getUniqueId().toString())) {
                if (nick.isRegistered(all.getUniqueId().toString())) {
                        if (!nickname.IDExist(nick.getID(all.getUniqueId().toString()))) {
                            if(!nickname.NicknamesExists(all.getName())) {
                                nickname.addNickname(nick.getID(all.getUniqueId().toString()), all.getName());
                            }
                            nick.updateNicked(all.getUniqueId().toString(), "-");
                            nick.updateID(all.getUniqueId().toString(), "-");

                            IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(all.getUniqueId());
                            if (permissionPlayer.hasPermissionGroup("NickedAdmin")) {
                                permissionPlayer.removePermissionGroup("NickedAdmin");
                                permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Admin", -1));
                                permissionPlayer.update();
                            } else if (permissionPlayer.hasPermissionGroup("NickedBuilder")) {
                                permissionPlayer.removePermissionGroup("NickedBuilder");
                                permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Builder", -1));
                                permissionPlayer.update();
                            } else if (permissionPlayer.hasPermissionGroup("NickedContent")) {
                                permissionPlayer.removePermissionGroup("NickedContent");
                                permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Content", -1));
                                permissionPlayer.update();
                            } else if (permissionPlayer.hasPermissionGroup("NickedDeveloper")) {
                                permissionPlayer.removePermissionGroup("NickedDeveloper");
                                permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Developer", -1));
                                permissionPlayer.update();
                            } else if (permissionPlayer.hasPermissionGroup("NickedModerator")) {
                                permissionPlayer.removePermissionGroup("NickedModerator");
                                permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Moderator", -1));
                                permissionPlayer.update();
                            } else if (permissionPlayer.hasPermissionGroup("NickedSupporter")) {
                                permissionPlayer.removePermissionGroup("NickedSupporter");
                                permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Supporter", -1));
                                permissionPlayer.update();
                            }
                        }
                    }
                }
        });
    }
}
