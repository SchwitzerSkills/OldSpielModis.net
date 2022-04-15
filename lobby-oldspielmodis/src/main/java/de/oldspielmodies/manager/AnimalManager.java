package de.oldspielmodies.manager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class AnimalManager {

    public static void removeAnimals(){
        for(World w : Bukkit.getWorlds()){
            for(Entity e : w.getEntities()){
                if(e.getType() != EntityType.ARMOR_STAND || e.getType() != EntityType.PLAYER){
                    e.remove();
                }
            }
        }
    }
}
