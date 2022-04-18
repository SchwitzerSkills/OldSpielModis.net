package de.oldspielmodies.commands;

import de.oldspielmodies.data.Data;
import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                            p.getInventory().clear();
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                            p.getInventory().setItem(0, new ItemManager(Material.COMPASS).setDisplayName("§8» §eCompass §8┃ §7Rightclick").toItemStack());
                            p.getInventory().setItem(1, new ItemManager(Material.BLAZE_ROD).setDisplayName("§8» §ePlayer hider §8┃ §7Rightclick").toItemStack());
                            p.getInventory().setItem(7, new ItemManager(Material.REDSTONE_COMPARATOR).setDisplayName("§8» §eSettings §8┃ §7Rightclick").toItemStack());
                            p.getInventory().setItem(8, new ItemManager(Material.FISHING_ROD).setDisplayName("§8» §eGrappling hook §8┃ §7Rightclick").toItemStack());

                            if(!p.hasPermission("oldspielmodis.nicktool")) {
                                p.getInventory().setItem(4, new ItemManager(Material.CHEST).setDisplayName("§8» §eGadgets §8┃ §7Rightclick").toItemStack());
                            } else {
                                p.getInventory().setItem(3, new ItemManager(Material.CHEST).setDisplayName("§8» §eGadgets §8┃ §7Rightclick").toItemStack());
                                p.getInventory().setItem(5, new ItemManager(Material.NAME_TAG).setDisplayName("§8» §eNick-Tool §8┃ §7Rightclick").toItemStack());
                            }
                        }
                    } else if(args.length == 1){
                        Player t = Bukkit.getPlayer(args[0]);
                        if(!Lobbysystem.build.contains(t)){
                            Lobbysystem.build.add(t);
                            p.sendMessage(Lobbysystem.PREFIX + "You have activated the player " + t.getName() + " the BuildMode.");
                            t.sendMessage(Lobbysystem.PREFIX + "You have been activated the BuildMode.");
                            t.setGameMode(GameMode.CREATIVE);
                            t.getInventory().clear();
                            t.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        } else {
                            Lobbysystem.build.remove(t);
                            p.sendMessage(Lobbysystem.PREFIX + "You have activated the player " + t.getName() + " the BuildMode.");
                            t.sendMessage(Lobbysystem.PREFIX + "You have been deactivated the BuildMode.");
                            t.setGameMode(GameMode.SURVIVAL);
                            t.getInventory().clear();
                            t.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                            t.getInventory().setItem(0, new ItemManager(Material.COMPASS).setDisplayName("§8» §eCompass §8┃ §7Rightclick").toItemStack());
                            t.getInventory().setItem(1, new ItemManager(Material.BLAZE_ROD).setDisplayName("§8» §ePlayer hider §8┃ §7Rightclick").toItemStack());
                            t.getInventory().setItem(7, new ItemManager(Material.REDSTONE_COMPARATOR).setDisplayName("§8» §eSettings §8┃ §7Rightclick").toItemStack());
                            t.getInventory().setItem(8, new ItemManager(Material.FISHING_ROD).setDisplayName("§8» §eGrappling hook §8┃ §7Rightclick").toItemStack());

                            if(!t.hasPermission("oldspielmodis.nicktool")) {
                                t.getInventory().setItem(4, new ItemManager(Material.CHEST).setDisplayName("§8» §eGadgets §8┃ §7Rightclick").toItemStack());
                            } else {
                                t.getInventory().setItem(3, new ItemManager(Material.CHEST).setDisplayName("§8» §eGadgets §8┃ §7Rightclick").toItemStack());
                                t.getInventory().setItem(5, new ItemManager(Material.NAME_TAG).setDisplayName("§8» §eNick-Tool §8┃ §7Rightclick").toItemStack());
                            }
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
