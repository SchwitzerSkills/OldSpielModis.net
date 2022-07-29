package de.schwitzerskills.spigot.skywars.listeners;

import de.schwitzerskills.spigot.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        if(e.getMessage().equalsIgnoreCase("/rl") || e.getMessage().equalsIgnoreCase("/reload")){
            e.setCancelled(true);
            Bukkit.getOnlinePlayers().forEach(all -> {
                all.kickPlayer(SkyWars.PREFIX + "§cDer Server startet jetzt neu!");
            });
            Bukkit.reload();
        }
    }
}
