package de.schwitzerskills.spigot.skywars.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title {

    public static void sendTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle){
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;

        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
        connection.sendPacket(packetPlayOutTitle);
        if(subtitle != null){
            IChatBaseComponent TitleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            PacketPlayOutTitle packetPlayOutTitle1 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, TitleSub);
            connection.sendPacket(packetPlayOutTitle1);
        }
        if(title != null){
            IChatBaseComponent Title = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            PacketPlayOutTitle packetPlayOutTitle1 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, Title);
            connection.sendPacket(packetPlayOutTitle1);
        }
    }

    public static void resetTitle(Player p){
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;

        IChatBaseComponent resetTitle = IChatBaseComponent.ChatSerializer.a("");
        PacketPlayOutTitle packetPlayOutTitle1 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, resetTitle);
        connection.sendPacket(packetPlayOutTitle1);
    }
}
