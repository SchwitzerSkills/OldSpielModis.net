package de.schwitzerskills.spigot.skywars.commands;

import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.mysql.MapSystem;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CMD_set implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("set")) {
            if (!(cs instanceof Player)) {
                cs.sendMessage(SkyWars.noCS);
            } else {
                Player p = (Player) cs;
                if (!p.hasPermission("SkyWars.set")) {
                    p.sendMessage(SkyWars.noPERMS);
                } else {
                    if(args.length == 3 && args[0].equalsIgnoreCase("spawn")){
                        int anzahl = Integer.parseInt(args[2]);
                        String map = args[1];
                        if(new MapSystem().hasMap(map)) {
                            if(anzahl <= 8) {
                                SkyWars.getInstance().lm.setSpawn(anzahl, p.getLocation(), map);
                                p.sendMessage(SkyWars.PREFIX + "§7Du hast die Location §c" + anzahl + " §7von der Map §e" + map + "§7 gesetzt");
                                p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                            } else {
                                p.sendMessage(SkyWars.PREFIX + "§cDu kannst du min. 8 Spawns hinzufügen!");
                            }
                        } else {
                            p.sendMessage(SkyWars.PREFIX + "§cDiese Map gibt es nicht!");
                        }
                        return true;
                    }
                    p.sendMessage(SkyWars.PREFIX + "§cNutze: /set spawn <Map> <Anzahl>");
                    return false;
                }
            }
        }
        return false;
    }
}
