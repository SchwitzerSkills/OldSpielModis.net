package de.schwitzerskills.spigot.skywars.listeners;

import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.utils.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e){
        e.setMaxPlayers(8);
        if(SkyWars.getInstance().getGameState() == GameState.LOBBY){
            e.setMotd("§aLOBBY");
        } else if(SkyWars.getInstance().getGameState() == GameState.inGAME){
            e.setMotd("§aINGAME");
        } else if(SkyWars.getInstance().getGameState() == GameState.RESTART){
            e.setMotd("§aRESTART");
        }
    }
}
