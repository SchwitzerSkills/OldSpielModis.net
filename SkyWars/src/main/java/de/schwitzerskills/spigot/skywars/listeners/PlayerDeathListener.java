package de.schwitzerskills.spigot.skywars.listeners;

import de.schwitzerskills.spigot.coinsystem.mysql.Coins;
import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.utils.ItemManager;
import de.schwitzerskills.spigot.skywars.utils.ScoreboardBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.util.Vector;

import java.util.Random;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        Player t = e.getEntity().getKiller();
        try{
            e.setDeathMessage(null);
            SkyWars.getInstance().nonspec.remove(p);
             if((e.getEntity() != null)){
                 if(t == null){
                     Bukkit.broadcastMessage(SkyWars.PREFIX + "§c" + p.getName() + "§7 ist gestorben");
                     p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 10);
                 } else if(t != null){
                     t.playSound(t.getLocation(), Sound.LEVEL_UP, 10, 10);
                     Random rand = new Random();
                     int maxNumber = 30;

                     int randomNumber = rand.nextInt(maxNumber) + 5;
                     Bukkit.broadcastMessage(SkyWars.PREFIX + "§c" + p.getName() + "§7 wurde von §e" + t.getName() + "§7 getötet");
                     t.sendMessage(SkyWars.PREFIX + "§e+" + randomNumber + " Coins");
                     p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 10);
                     new Coins().addCoins(t.getUniqueId().toString(), randomNumber);
                     SkyWars.getInstance().kills.merge(t, 1, Integer::sum);
                 } else {
                     Bukkit.broadcastMessage(SkyWars.PREFIX + "§c" + p.getName() + "§7 ist gestorben");
                     p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 10);
                 }

                 Bukkit.getOnlinePlayers().forEach(all -> {
                     new ScoreboardBuilder().updatescoreboard2(all);
                 });
             }
             if(SkyWars.getInstance().nonspec.size() >= 2) {
                 Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Es verbleiben §c" + SkyWars.getInstance().nonspec.size() + "§7 Spieler...");
             } else {
                 Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Es verbleibt §c" + SkyWars.getInstance().nonspec.size() + "§7 Spieler...");
             }

            if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID){
                if (p.getHealth() <= 0.5) {
                    p.setHealth(20);
                    p.teleport(SkyWars.getInstance().lm.getLocation("Spectator"));
                    p.setVelocity(new Vector(0, 0.5, 0));
                    new ItemManager().SpectatorItems(p);
                }
            } else {
                if (p.getHealth() <= 0.5) {
                    p.setHealth(20);
                    p.setVelocity(new Vector(0, 0.5, 0));
                    new ItemManager().SpectatorItems(p);
                }
            }

             p.getNearbyEntities(0, 0, 0).forEach(all -> {
                 if(SkyWars.getInstance().spec.contains(all)) {
                     PlayerInteractListener.task.remove(all);
                     new ItemManager().SpectatorItems((Player) all);
                     if (!PlayerInteractListener.task.contains(all)) {
                         all.getServer().getScheduler().cancelTask(PlayerInteractListener.count);
                     }
                 }
             });
            SkyWars.getInstance().ende();
        } catch (Exception e1){
        }
    }
}
