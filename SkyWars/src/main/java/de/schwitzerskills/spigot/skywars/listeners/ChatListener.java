package de.schwitzerskills.spigot.skywars.listeners;

import de.schwitzerskills.spigot.skywars.SkyWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        e.setFormat("§e" + p.getName() + "§8: §7" + e.getMessage());
        if(SkyWars.getInstance().spec.contains(p)){
            e.setCancelled(true);
            p.sendMessage(SkyWars.PREFIX + "§cDu darfst als Spectator nichts schreiben!");
        }
    }
}
