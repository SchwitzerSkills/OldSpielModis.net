package de.schwitzerskills.spigot.skywars.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Actionbar {

    private String message;

    public Actionbar(String msg){
        this.message = msg;
    }

    public void send(Player p){

        IChatBaseComponent ichat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"\"}").a(message);
        PacketPlayOutChat chat = new PacketPlayOutChat(ichat, (byte) 2);

        CraftPlayer cp = (CraftPlayer) p;
        cp.getHandle().playerConnection.sendPacket(chat);
    }
}
