package de.oldspielmodies.items;

import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.manager.CircleManager;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class Grapplinghook implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent e){
        Player p = e.getPlayer();
        FishHook hook = e.getHook();
        if(e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY || e.getState() == PlayerFishEvent.State.CAUGHT_FISH){
            p.sendMessage(Lobbysystem.PREFIX + "§cFishing is prohibited!");
            e.setCancelled(true);
        }
        if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eGrappling hook §8┃ §7Rightclick")) {
            if(p.getItemInHand().getType() == Material.FISHING_ROD){
                if (hook.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
                    Location loc = p.getLocation();
                    Location hookloc = hook.getLocation();

                    Vector v = p.getVelocity();
                    double distance = loc.distance(hookloc);

                    CircleManager circleManager = new CircleManager();
                    circleManager.drawCircle(hookloc, (float) 1, EnumParticle.VILLAGER_HAPPY, false);

                    v.setX((distance) * (hookloc.getX() - loc.getX()) / distance);
                    v.setY((distance) * (loc.getY() - loc.getY()) / distance - -0.02D * distance);
                    v.setZ((distance) * (hookloc.getZ() - loc.getZ()) / distance);

                    p.setVelocity(v);
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 10);
                    Bukkit.getScheduler().runTaskLaterAsynchronously(Lobbysystem.getInstance(), () -> {
                        p.getItemInHand().setDurability((short) 0);
                        p.updateInventory();
                    }, 0);
                }
            }
        }
    }
}
