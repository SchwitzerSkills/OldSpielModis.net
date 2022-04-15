package de.oldspielmodies.lobby;

import de.oldspielmodies.commands.BuildCommand;
import de.oldspielmodies.commands.SetWarpsCommand;
import de.oldspielmodies.items.CompassInteract;
import de.oldspielmodies.listener.BuildListener;
import de.oldspielmodies.listener.PlayerConnetionListener;
import de.oldspielmodies.manager.AnimalManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Lobbysystem extends JavaPlugin {

    private static Lobbysystem instance;

    public static ArrayList<Player> build = new ArrayList<Player>();
    public static final String PREFIX = "§8× §eOldSpielModis §8┃ §7",
                                NO_PERMS = PREFIX + "§cYou don't have the permission to use this command!";

    @Override
    public void onEnable() {

        if(instance == null){
            init(Bukkit.getPluginManager());
            Bukkit.getConsoleSender().sendMessage(PREFIX + "Das Plugin wurde erfolgreich aktiviert!");
        }


    }

    @Override
    public void onDisable() {

    }

    private void init(final PluginManager pluginManager){

        getServer().getPluginCommand("setwarp").setExecutor(new SetWarpsCommand());
        getServer().getPluginCommand("build").setExecutor(new BuildCommand());
        pluginManager.registerEvents(new PlayerConnetionListener(), this);
        pluginManager.registerEvents(new CompassInteract(), this);
        pluginManager.registerEvents(new BuildListener(), this);

    }


    public static Lobbysystem getInstance() {
        return instance;
    }
}
