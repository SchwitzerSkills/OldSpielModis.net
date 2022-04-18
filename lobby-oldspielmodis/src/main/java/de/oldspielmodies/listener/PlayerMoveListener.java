package de.oldspielmodies.listener;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        try {
            Player p = e.getPlayer();
            if (p.getInventory().getBoots().getType() == Material.LEATHER_BOOTS) {
                if (p.getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cRed shoes")) {
                    Bukkit.getOnlinePlayers().forEach(all -> {
                        float red = 252;
                        float green = 28;
                        float blue = 3;
                        Packet example = new PacketPlayOutWorldParticles(EnumParticle.SPELL_MOB, true, (float) p.getLocation().getX(), (float) p.getLocation().getY(), (float) p.getLocation().getZ(), red, green, blue, (float) 255, 0, 0);
                        ((CraftPlayer) all).getHandle().playerConnection.sendPacket(example);
                    });
                } else if (p.getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §1Blue shoes")) {
                    Bukkit.getOnlinePlayers().forEach(all -> {
                        float red = 28;
                        float green = 3;
                        float blue = 252;
                        Packet example = new PacketPlayOutWorldParticles(EnumParticle.SPELL_MOB, true, (float) p.getLocation().getX(), (float) p.getLocation().getY(), (float) p.getLocation().getZ(), red, green, blue, (float) 255, 0, 0);
                        ((CraftPlayer) all).getHandle().playerConnection.sendPacket(example);
                    });
                } else if (p.getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eYellow shoes")) {
                    Bukkit.getOnlinePlayers().forEach(all -> {
                        float red = 252;
                        float green = 244;
                        float blue = 3;
                        Packet example = new PacketPlayOutWorldParticles(EnumParticle.SPELL_MOB, true, (float) p.getLocation().getX(), (float) p.getLocation().getY(), (float) p.getLocation().getZ(), red, green, blue, (float) 255, 0, 0);
                        ((CraftPlayer) all).getHandle().playerConnection.sendPacket(example);
                    });
                } else if (p.getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aGreen shoes")) {
                    Bukkit.getOnlinePlayers().forEach(all -> {
                        float red = 3;
                        float green = 252;
                        float blue = 7;
                        Packet example = new PacketPlayOutWorldParticles(EnumParticle.SPELL_MOB, true, (float) p.getLocation().getX(), (float) p.getLocation().getY(), (float) p.getLocation().getZ(), red, green, blue, (float) 255, 0, 0);
                        ((CraftPlayer) all).getHandle().playerConnection.sendPacket(example);
                    });
                }
            }
        } catch (Exception e1){
        }
    }
}
