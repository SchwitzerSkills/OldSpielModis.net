package de.oldspielmodis.proxy.nick;

import de.oldspielmodis.proxy.nick.commands.NicknameCommand;
import de.oldspielmodis.proxy.nick.listeners.JoinListener;
import de.oldspielmodis.proxy.nick.mysql.MySQL;
import de.oldspielmodis.proxy.nick.mysql.Nick;
import de.oldspielmodis.proxy.nick.mysql.Nickname;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;

public class Nicksystem extends Plugin {

    public static final String PREFIX = "§8× §eProxy §8┃ §7",
            NO_PERMS = PREFIX + "§cYou don't have the permission to use this command!";

    private MySQL mySQL;
    public static Nicksystem instance;

    public static File file = new File("plugins/Nicksystem/mysql.yml");

    @Override
    public void onEnable() {
        instance = this;
        createFile();
        ///--------------------------
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
        ///--------------------------

        BungeeCord.getInstance().getConsole().sendMessage(PREFIX + "§aNicksystem aktiviert");
        Nick nick = new Nick();
        nick.createTable();
        Nickname nickname = new Nickname();
        nickname.createTable();

        BungeeCord.getInstance().getPluginManager().registerListener(this, new JoinListener());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new NicknameCommand());
    }

    @Override
    public void onDisable() {
        BungeeCord.getInstance().getConsole().sendMessage(PREFIX + "§cNicksystem deaktiviert");
    }

    public void createFile(){
        try {
            if (!getDataFolder().exists()) {
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

    public static Nicksystem getInstance() {
        return instance;
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
