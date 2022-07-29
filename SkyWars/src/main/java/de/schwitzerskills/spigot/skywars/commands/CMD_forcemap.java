package de.schwitzerskills.spigot.skywars.commands;

import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.mysql.MapSystem;
import de.schwitzerskills.spigot.skywars.mysql.Maps;
import de.schwitzerskills.spigot.skywars.utils.ScoreboardBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CMD_forcemap implements CommandExecutor {

    public static boolean isForce = false;

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("forcemap")) {
            if (!(cs instanceof Player)) {
                cs.sendMessage(SkyWars.noCS);
            } else {
                Player p = (Player) cs;
                if (!p.hasPermission("SkyWars.forcemap")) {
                    p.sendMessage(SkyWars.noPERMS);
                } else {
                    if(args.length == 1 && args[0].equalsIgnoreCase("list")){
                        for(int i = 1; i < 4; i++) {
                            List<String> map = new MapSystem().getMaps(i);
                            if(new MapSystem().hasMaps()){
                                for(String maps : map){
                                    p.sendMessage(SkyWars.PREFIX + "§c" + maps);
                                }
                            } else {
                                p.sendMessage(SkyWars.PREFIX + "§cEs gibt leider keine Maps!");
                            }
                        }
                        return true;
                    } else if(args.length == 1){
                        String map = args[0];
                        if(!isForce) {
                            if (new MapSystem().hasMap(map)) {
                                if(new MapSystem().getMap(1).equals(map) || new MapSystem().getMap(2).equals(map) || new MapSystem().getMap(3).equals(map)){
                                    Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Ein §5Youtuber §7oder ein §aTeammitglied §7hat die Map auf §c" + map + "§7 gewechselt");
                                    new Maps().switchMap(map);
                                    Bukkit.getOnlinePlayers().forEach(all -> {
                                        all.playSound(all.getLocation(), Sound.GLASS, 10, 10);
                                        new ScoreboardBuilder().updatescoreboard(all);
                                    });
                                } else {
                                    p.sendMessage(SkyWars.PREFIX + "§cBitte beachte deine Groß/Kleinschreibung!");
                                    for(int i = 1; i < 4; i++) {
                                        List<String> map1 = new MapSystem().getMaps(i);
                                        if(new MapSystem().hasMaps()){
                                            for(String maps : map1){
                                                p.sendMessage(SkyWars.PREFIX + "§c" + maps);
                                            }
                                        } else {
                                            p.sendMessage(SkyWars.PREFIX + "§cEs gibt leider keine Maps!");
                                        }
                                    }
                                }
                            } else {
                                p.sendMessage(SkyWars.PREFIX + "§cDiese Map gibt es leider nicht!");
                            }
                        } else {
                            p.sendMessage(SkyWars.PREFIX + "§cDu kannst die Map leider nicht mehr wechseln!");
                        }
                        return true;
                    }
                    p.sendMessage(SkyWars.PREFIX + "§cNutze: /forcemap <Map>");
                    p.sendMessage(SkyWars.PREFIX + "§cNutze: /forcemap list");
                    return false;
                }
            }
        }
        return false;
    }
}
