package de.schwitzerskills.spigot.skywars.utils;

import de.schwitzerskills.spigot.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

public class EndeCountdown {

    public static int count = 16;
    public static int Tcount;

    public void start(){
        Tcount = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.getInstance(), () -> {
            count--;
            Bukkit.getOnlinePlayers().forEach(all -> {
                new Actionbar(SkyWars.PREFIX + "§7Das Spiel endet in §c" + count + " §7Sekunden...").send(all);
            });
            if (count == 15 || count == 10 || count == 5 || count == 3 || count == 2) {
                Bukkit.getOnlinePlayers().forEach(all -> {
                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10, 10);
                });
            }
            if (count == 2 || count == 3 || count == 1) {
                Bukkit.getOnlinePlayers().forEach(all -> {
                    Title.sendTitle(all, 20, 50, 20, "§c" + count, "");
                });
            }
            if (count == 5) {
                Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Alle Spieler werden jetzt gekickt und der Server wird neugestartet");
            }
            if(count == 1){
                Bukkit.getOnlinePlayers().forEach(all -> {
                    new Actionbar(SkyWars.PREFIX + "§7Das Spiel startet in §ceine §7Sekunden...").send(all);
                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10, 10);
                });
            } else if(count == 0) {
                Bukkit.getScheduler().cancelTask(Tcount);
                Bukkit.getOnlinePlayers().forEach(all -> {
                    all.kickPlayer(SkyWars.PREFIX + "§cDer Server startet jetzt neu!");
                });
                Bukkit.reload();
            }
        }, 0, 20);
    }
}
