package de.oldspielmodies.lobby;

import de.oldspielmodies.commands.SetWarpsCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobbysystem extends JavaPlugin {

    private static Lobbysystem instance;
    public static final String PREFIX = "§eOldSpielModis §8| §7",
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

    }


    public static Lobbysystem getInstance() {
        return instance;
    }
}
