package de.oldspielmodies.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class GrapplinghookListener implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent e){
        Player p = e.getPlayer();
        Fish fish = e.getHook();
        if((e.getState() == PlayerFishEvent.State.IN_GROUND || e.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY) || (e.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT)))
        && (Bukkit.getWorld(p.getWorld().getName()).getBlockAt(fish.getLocation().getBlockX(), fish.getLocation().getBlockY() -1, fish.getLocation().getBlockZ()).getType() != Material.AIR)){

            Location loc = p.getLocation();
            Location hook = e.getHook().getLocation();

            loc.setY(loc.getY() + 0.5D);
            p.teleport(hook);

            p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 10);

            double g = -0.08D;
            double d = hook.distance(loc);
            double t = d;
            double v_x = (1.0D + 0.07D * t) * (hook.getX() - loc.getX()) / t;
            double v_y = (1.0D + 0.03D * t) * (hook.getY() - loc.getY()) / t - 0.5D * g * t;
            double v_z = (1.0D + 0.07D * t) * (hook.getZ() - loc.getZ()) / t;

            Vector vector = p.getVelocity();
            vector.setX(v_x);
            vector.setY(v_y);
            vector.setZ(v_z);
            p.setVelocity(vector);
        }
    }
}
