package de.schwitzerskills.spigot.skywars.commands;

import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.mysql.MapSystem;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CMD_map implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("map")){
            if (!(cs instanceof Player)) {
                cs.sendMessage(SkyWars.noCS);
            } else {
                Player p = (Player) cs;
                if(!p.hasPermission("SkyWars.map")){
                    p.sendMessage(SkyWars.noPERMS);
                } else {
                    if (args.length == 3 && args[0].equalsIgnoreCase("add")) {
                        try {
                            String mapname = args[2];
                            int id = Integer.parseInt(args[1]);
                            if (new MapSystem().hasID(id)) {
                                p.sendMessage(SkyWars.PREFIX + "§cDiese ID gibt es schon!");
                            } else if (new MapSystem().hasMap(mapname)) {
                                p.sendMessage(SkyWars.PREFIX + "§cDieser Mapname gibt es schon!");
                            } else {
                                if(id <= 3) {
                                    new MapSystem().addMap(id, mapname);
                                    p.sendMessage(SkyWars.PREFIX + "§7Du hast die Map §c" + mapname + "§7 hinzugefügt");
                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                                } else {
                                    p.sendMessage(SkyWars.PREFIX + "§cDu kannst nur 3 Maps hinzufügen!");
                                }
                            }
                        } catch (NumberFormatException e){
                            p.sendMessage(SkyWars.PREFIX + "§cDie ID darf keinen Buchstaben beinhalten!");
                        }
                        return true;
                    } else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
                        String mapname = args[1];
                        if (!new MapSystem().hasMap(mapname)) {
                            p.sendMessage(SkyWars.PREFIX + "§cDiesen Mapnamen gibt es nicht!");
                        } else {
                            new MapSystem().removeMap(mapname);
                            p.sendMessage(SkyWars.PREFIX + "§7Du hast die Map §c" + mapname + "§7 entfernt");
                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                        }
                        return true;
                    } else if(args.length == 1 && args[0].equalsIgnoreCase("list")){
                        for(int i = 1; i < 4; i++) {
                            List<String> map = new MapSystem().getMaps(i);
                            if(new MapSystem().hasMaps()){
                                for(String maps : map){
                                    p.sendMessage(SkyWars.PREFIX + "§7" + new MapSystem().getIds(maps) + ": §c" + maps);
                                }
                            } else {
                                p.sendMessage(SkyWars.PREFIX + "§cDu hast leider keine Maps hinzugefügt!");
                            }
                        }

                        return true;
                    }
                    p.sendMessage(SkyWars.PREFIX + "§cNutze: /map add <ID> <Mapname>");
                    p.sendMessage(SkyWars.PREFIX + "§cNutze: /map remove <ID>");
                    p.sendMessage(SkyWars.PREFIX + "§cNutze: /map list");
                    return false;
                }
            }
        }
        return false;
    }
}
