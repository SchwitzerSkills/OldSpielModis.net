package de.oldspielmodies.manager;

import de.oldspielmodies.lobby.Lobbysystem;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.File;

public class ActionbarManager {

    public static File file = new File("plugins/Lobby/Actionbar.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void createFile(){
        try{
            if(!file.exists()){
                cfg.set("Actionbar.1", "&7Warum stinkt &cEndrew&7?'");
                cfg.set("Gesamt", "1");
                cfg.save(file);
            }
        } catch (Exception e){
        }
    }

    int count = 1;

    public void sendActionbar(){
        int gesamt = Integer.parseInt(cfg.getString("Gesamt"));
        gesamt = gesamt+1;
        int finalGesamt = gesamt;
        Bukkit.getScheduler().runTaskTimer(Lobbysystem.getInstance(), () -> {
            String nachricht = cfg.getString("Actionbar." + count);
            nachricht = nachricht.replace("&", "§");
            String finalNachricht = nachricht;
            Bukkit.getOnlinePlayers().forEach(current -> {
                Actionbar(current, finalNachricht);
            });
            count++;

            if (count == finalGesamt) {
                count = 1;
            }
        }, 40, 40);
    }

    public void Actionbar(Player player, String message){
        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(iChatBaseComponent, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
    }
}
