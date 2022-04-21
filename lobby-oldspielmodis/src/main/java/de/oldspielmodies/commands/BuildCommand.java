package de.oldspielmodies.commands;

import de.oldspielmodies.data.Data;
import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.manager.ItemManager;
import de.oldspielmodis.spigot.nick.mysql.Nick;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class BuildCommand implements CommandExecutor {
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("build")) {
            if((cs instanceof Player)){
                Player p = (Player) cs;
                if(p.hasPermission("oldspielmodis.build")){
                    if (args.length == 0) {
                        if(!Lobbysystem.build.contains(p)){
                            Lobbysystem.build.add(p);
                            p.sendMessage(Lobbysystem.PREFIX + "You have activated the Build mode.");
                            p.setGameMode(GameMode.CREATIVE);
                            p.getInventory().clear();
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        } else {
                            Lobbysystem.build.remove(p);
                            p.sendMessage(Lobbysystem.PREFIX + "You have deactivated the Build mode.");
                            p.setGameMode(GameMode.SURVIVAL);
                            Data data = new Data();
                            data.DefaultItems(p);
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        }
                    } else if(args.length == 1){
                        Player t = Bukkit.getPlayer(args[0]);
                        if(!Lobbysystem.build.contains(t)){
                            Lobbysystem.build.add(t);
                            p.sendMessage(Lobbysystem.PREFIX + "You have activated the player " + t.getName() + " the BuildMode.");
                            t.sendMessage(Lobbysystem.PREFIX + "You have been activated the BuildMode.");
                            t.setGameMode(GameMode.CREATIVE);
                            t.getInventory().clear();
                            t.playSound(t.getLocation(), Sound.LEVEL_UP, 10, 10);
                        } else {
                            Lobbysystem.build.remove(t);
                            p.sendMessage(Lobbysystem.PREFIX + "You have activated the player " + t.getName() + " the BuildMode.");
                            t.sendMessage(Lobbysystem.PREFIX + "You have been deactivated the BuildMode.");
                            t.setGameMode(GameMode.SURVIVAL);
                            Data data = new Data();
                            data.DefaultItems(t);
                            t.playSound(t.getLocation(), Sound.LEVEL_UP, 10, 10);
                        }
                    }
                } else {
                    p.sendMessage(Lobbysystem.NO_PERMS);
                }
            }
        }
        return false;
    }
}
