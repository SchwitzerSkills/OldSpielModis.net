package de.schwitzerskills.spigot.skywars.utils;

import de.schwitzerskills.spigot.skywars.SkyWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class BuildListener implements Listener {

    @EventHandler
    public void onBuild(BlockPlaceEvent e){
        Player p = e.getPlayer();
        try {
            if (SkyWars.getInstance().getGameState() == GameState.LOBBY || SkyWars.getInstance().getGameState() == GameState.RESTART ||
            SkyWars.getInstance().spec.contains(p)) {
                e.setCancelled(true);
            }
        } catch (Exception e1){
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        try {
            if (SkyWars.getInstance().getGameState() == GameState.LOBBY || SkyWars.getInstance().getGameState() == GameState.RESTART ||
                    SkyWars.getInstance().spec.contains(p)) {
                e.setCancelled(true);
            }
        } catch (Exception e1){
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        Player p = (Player) e.getEntity();
        try {
            if((e.getEntity() instanceof Player)) {
                if (SkyWars.getInstance().getGameState() == GameState.LOBBY || SkyWars.getInstance().getGameState() == GameState.RESTART ||
                        SkyWars.getInstance().spec.contains(p)) {
                    e.setCancelled(true);
                }
            }
        } catch (Exception e1){
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        try {
            if (SkyWars.getInstance().getGameState() == GameState.LOBBY || SkyWars.getInstance().getGameState() == GameState.RESTART ||
                    SkyWars.getInstance().spec.contains(p)) {
                e.setCancelled(true);
            }
        } catch (Exception e1){
        }
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e){
        Player p = e.getPlayer();
        try{
            if (SkyWars.getInstance().getGameState() == GameState.LOBBY || SkyWars.getInstance().getGameState() == GameState.RESTART ||
                    SkyWars.getInstance().spec.contains(p)) {
                e.setCancelled(true);
            }
        } catch (Exception e1){
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        try{
            if (SkyWars.getInstance().getGameState() == GameState.LOBBY || SkyWars.getInstance().getGameState() == GameState.RESTART) {
                e.setCancelled(true);
            }
        } catch (Exception e1){
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e){
        try{
            Player k = (Player) e.getDamager();
            if ((e.getEntity() instanceof Player) && (e.getDamager() instanceof Player)) {
                if(SkyWars.getInstance().spec.contains(k)){
                    e.setCancelled(true);
                }
            }
        } catch (Exception e1){
        }
    }
}
