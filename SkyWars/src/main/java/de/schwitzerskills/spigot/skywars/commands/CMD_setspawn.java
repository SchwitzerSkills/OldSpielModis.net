package de.schwitzerskills.spigot.skywars.commands;

import de.schwitzerskills.spigot.skywars.SkyWars;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_setspawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("setspawn")){
            if (!(cs instanceof Player)) {
                cs.sendMessage(SkyWars.noCS);
            } else {
                Player p = (Player) cs;
                if(!p.hasPermission("SkyWars.setspawn")){
                    p.sendMessage(SkyWars.noPERMS);
                } else {
                    if (args.length == 1 && args[0].equalsIgnoreCase("Spawn")) {
                        SkyWars.getInstance().lm.setLocation("Spawn", p.getLocation());
                        p.sendMessage(SkyWars.PREFIX + "§7Du hast den Spawn gesetzt");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                        return true;
                    } else if (args.length == 1 && args[0].equalsIgnoreCase("Spectator")) {
                        SkyWars.getInstance().lm.setLocation("Spectator", p.getLocation());
                        p.sendMessage(SkyWars.PREFIX + "§7Du hast den Spawn für den Spectator gesetzt");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                        return true;
                    }
                    p.sendMessage(SkyWars.PREFIX + "§cNutze: /setspawn Spawn");
                    p.sendMessage(SkyWars.PREFIX + "§cNutze: /setspawn Spectator");
                    return true;
                }
            }
        }
        return false;
    }
}
