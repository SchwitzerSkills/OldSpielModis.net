package de.oldspielmodies.listener;

import de.oldspielmodies.data.Data;
import de.oldspielmodies.manager.LocationManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerConnetionListener implements Listener {


    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event){
        event.setJoinMessage(null);

        new Data().onPlayerdefaultJoinSettings(event.getPlayer());

        if(!event.getPlayer().hasPlayedBefore()){
            event.getPlayer().teleport(new LocationManager().getLocation("spawn"));
        }

    }


    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event){
        event.setQuitMessage(null);

    }

}
