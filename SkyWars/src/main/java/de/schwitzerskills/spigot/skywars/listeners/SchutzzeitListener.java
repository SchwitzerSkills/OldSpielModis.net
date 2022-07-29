package de.schwitzerskills.spigot.skywars.listeners;

import de.schwitzerskills.spigot.skywars.utils.Countdown;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class SchutzzeitListener implements Listener {

    @EventHandler
    public void onSchutzzeit(EntityDamageEvent e){
        try{
            if(Countdown.schutzzeitbo){
                e.setCancelled(true);
            }
        } catch (Exception e1){
        }
    }
}
