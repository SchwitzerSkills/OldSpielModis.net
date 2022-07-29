package de.schwitzerskills.spigot.skywars.utils;

import de.schwitzerskills.spigot.skywars.SkyWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class GameManager implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        Player p = (Player) e.getEntity();
        try {
            if((e.getEntity() instanceof Player)) {
                if (SkyWars.getInstance().getGameState() != GameState.inGAME || SkyWars.getInstance().spec.contains(p)) {
                    e.setCancelled(true);
                }
            }
        } catch (Exception e1){
        }
    }
}
