package de.oldspielmodies.manager;

import de.oldspielmodies.lobby.Lobbysystem;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CircleManager {

    public void drawCircle(Location location, float radius){
        Bukkit.getScheduler().runTaskTimer(Lobbysystem.getInstance(), new Runnable() {

            @Override
            public void run() {
                for(double t = 0; t<50; t+=0.5) {
                    float x = radius*(float)Math.sin(t);
                    float z = radius*(float)Math.cos(t);
                    PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.SPELL, true, (float) location.getX() + x, (float) location.getY(), (float) location.getZ() + z, 0, 0, 0, 0, 1);
                    for(Player online : Bukkit.getOnlinePlayers()){
                        ((CraftPlayer)online).getHandle().playerConnection.sendPacket(packet);
                    }
                }
            }
        }, 20, 20);
    }
}
