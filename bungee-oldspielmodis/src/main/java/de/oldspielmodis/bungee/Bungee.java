package de.oldspielmodis.bungee;

import de.oldspielmodis.bungee.commands.BanCommand;
import de.oldspielmodis.bungee.commands.BanInfoCommand;
import de.oldspielmodis.bungee.commands.UnbanCommand;
import de.oldspielmodis.bungee.listeners.JoinListener;
import de.oldspielmodis.bungee.mysql.Ban;
import de.oldspielmodis.bungee.mysql.BanReason;
import de.oldspielmodis.bungee.mysql.MySQL;
import de.oldspielmodis.bungee.utils.BanType;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;

public class Bungee extends Plugin {

    public static final String PREFIX = "§8× §eProxy §8┃ §7",
            NO_PERMS = PREFIX + "§cYou don't have the permission to use this command!";

    public static File file = new File("plugins/Bungee/mysql.yml");

    public static Bungee instance;

    private MySQL mySQL;

    private BanType banType;

    @Override
    public void onEnable() {
        createFile();
        instance = this;
        //-------------------------------------------
        try {
            Configuration config = YamlConfiguration.getProvider(YamlConfiguration.class).load(file);
            this.mySQL = MySQL.newBuilder()
                    .withUser(config.getString("MySQL.username"))
                    .withPassword(config.getString("MySQL.password"))
                    .withDatabase(config.getString("MySQL.database"))
                    .withUrl(config.getString("MySQL.host"))
                    .withPort(3306)
                    .create();
        } catch (Exception e){
        }
        //-------------------------------------------
        BungeeCord.getInstance().getConsole().sendMessage(PREFIX + "§aPlugin aktiviert");
        BanReason banReason = new BanReason();
        banReason.createTable();
        Ban ban = new Ban();
        ban.createTable();

        BungeeCord.getInstance().getPluginManager().registerCommand(this, new BanCommand());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new UnbanCommand());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new BanInfoCommand());
        BungeeCord.getInstance().getPluginManager().registerListener(this, new JoinListener());
    }

    @Override
    public void onDisable() {
        BungeeCord.getInstance().getConsole().sendMessage(PREFIX + "§cPlugin deaktiviert");
    }

    public void createFile(){
        try{
            if(!getDataFolder().exists()){
                getDataFolder().mkdir();
            }
            if(!file.exists()){
                file.createNewFile();
                Configuration config = YamlConfiguration.getProvider(YamlConfiguration.class).load(file);
                config.set("MySQL.username", "root");
                config.set("MySQL.password", "password");
                config.set("MySQL.database", "database");
                config.set("MySQL.host", "localhost");
                YamlConfiguration.getProvider(YamlConfiguration.class).save(config, file);
            }
        } catch (Exception e){
        }
    }

    public static Bungee getInstance() {
        return instance;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public BanType getBanType() {
        return banType;
    }

    public void setBanType(BanType banType){
        this.banType = banType;
    }
}
