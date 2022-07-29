package de.schwitzerskills.spigot.skywars.commands;

import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.utils.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_start implements CommandExecutor {

    public static boolean isStarted = false;

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("start")) {
            if (!(cs instanceof Player)) {
                cs.sendMessage(SkyWars.noCS);
            } else {
                Player p = (Player) cs;
                if (!p.hasPermission("SkyWars.start")) {
                    p.sendMessage(SkyWars.noPERMS);
                } else {
                    if(SkyWars.getInstance().maxplayers <= 0) {
                        if (Countdown.count <= 15) {
                            p.sendMessage(SkyWars.PREFIX + "§cDu kannst die Runde nicht starten, weil der Countdown unter 15 Sekunden schon ist!");
                        } else {
                            if (!isStarted) {
                                Countdown.count = 16;
                                Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Die Runde wurde von einem §5Youtuber §7oder \n§aTeammitglied §7gestartet");
                                isStarted = true;
                                Bukkit.getOnlinePlayers().forEach(all -> {
                                    all.playSound(all.getLocation(), Sound.GLASS, 10, 10);
                                });
                            } else {
                                p.sendMessage(SkyWars.PREFIX + "§cEs wurde schon von einem §5Youtuber §coder \n§aTeammitglied §cgestartet!");
                            }
                        }
                    } else {
                        p.sendMessage(SkyWars.PREFIX + "§cEs wurden nicht die maximalen Spieler erreicht!");
                    }
                }
            }
        }
        return false;
    }
}
