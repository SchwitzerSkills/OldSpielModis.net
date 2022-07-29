package de.schwitzerskills.spigot.skywars.utils;

import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.mysql.MapSystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LocationManager {

    public File file = new File("plugins/SkyWars/locs.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void setLocation(String name, Location loc){

        cfg.set(name + ".world", loc.getWorld().getName());
        cfg.set(name + ".x", loc.getX());
        cfg.set(name + ".y", loc.getY());
        cfg.set(name + ".z", loc.getZ());

        cfg.set(name + ".yaw", loc.getYaw());
        cfg.set(name + ".pitch", loc.getPitch());
        saveCfg();
    }

    public Location getLocation(String name){
        Location loc = null;
        try {
            World w = Bukkit.getWorld(cfg.getString(name + ".world"));
            double x = cfg.getDouble(name + ".x");
            double y = cfg.getDouble(name + ".y");
            double z = cfg.getDouble(name + ".z");
            loc = new Location(w, x, y, z);
            loc.setYaw(cfg.getInt(name + ".yaw"));
            loc.setPitch(cfg.getInt(name + ".pitch"));
        } catch (Exception e){
            Bukkit.broadcastMessage(SkyWars.PREFIX + "§cKeine Locations gesetzt!");
        }
        return loc;
    }

    public void setSpawn(int num, Location loc, String map){
        String name = "Spawns";
        cfg.set(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".world", loc.getWorld().getName());
        cfg.set(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".x", loc.getX());
        cfg.set(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".y", loc.getY());
        cfg.set(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".z", loc.getZ());
        cfg.set(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".yaw", loc.getYaw());
        cfg.set(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".pitch", loc.getPitch());
        saveCfg();
    }

    public Location getSpawns(int num, String map){
        String name = "Spawns";
        World w = Bukkit.getWorld(cfg.getString(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".world"));
        double x = cfg.getDouble(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".x");
        double y = cfg.getDouble(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".y");
        double z = cfg.getDouble(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".z");
        Location loc = new Location(w, x, y, z);
        loc.setYaw(cfg.getInt(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".yaw"));
        loc.setPitch(cfg.getInt(name + "." + new MapSystem().getIds(map) + "." + map + "." + num + ".pitch"));
        return loc;
    }

    public void saveCfg(){
        try{
            cfg.save(file);
        } catch (Exception e){
        }
    }
}
