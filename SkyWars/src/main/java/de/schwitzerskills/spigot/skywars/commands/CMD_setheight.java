package de.schwitzerskills.spigot.skywars.commands;

import de.schwitzerskills.spigot.skywars.SkyWars;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CMD_setheight implements CommandExecutor {

    public static File file = new File("plugins/SkyWars/height.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("setheight")){
            if (!(cs instanceof Player)) {
                cs.sendMessage(SkyWars.noCS);
            } else {
                Player p = (Player) cs;
                if(!p.hasPermission("SkyWars.setheight")){
                    p.sendMessage(SkyWars.noPERMS);
                } else {
                    if (args.length == 1) {
                        String mapname = args[0];
                        cfg.set("Height." + mapname, p.getLocation().getY());
                        try {
                            cfg.save(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        p.sendMessage(SkyWars.PREFIX + "§7Du hast die Todeshöhe für die Map §e" + mapname + "§7 gesetzt");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                        return true;
                    }
                    p.sendMessage(SkyWars.PREFIX + "§cNutze: /setheight <Mapname>");
                    return false;
                }
            }
        }
        return false;
    }
}
