package de.oldspielmodies.manager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerManager implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();
        if(!to.getWorld().getBlockAt(to).equals(from.getWorld().getBlockAt(from))) {
            if (p.getLocation().getBlock().getType() == Material.WATER || p.getLocation().getBlock().getType() == Material.WATER_LILY || p.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
                LocationManager locationManager = new LocationManager();
                p.teleport(locationManager.getLocation("spawn"));
            }
        }
    }
}
