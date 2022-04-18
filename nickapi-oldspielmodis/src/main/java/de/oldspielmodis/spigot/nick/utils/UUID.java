package de.oldspielmodis.spigot.nick.utils;

import org.bukkit.Bukkit;

public class UUID {

    public String getUUID(String name){
        return Bukkit.getOfflinePlayer(name).getUniqueId().toString().replace("-", "");
    }
}
