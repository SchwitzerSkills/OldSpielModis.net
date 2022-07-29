package de.schwitzerskills.spigot.skywars.listeners;

import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.commands.CMD_forcemap;
import de.schwitzerskills.spigot.skywars.commands.CMD_start;
import de.schwitzerskills.spigot.skywars.mysql.Kits;
import de.schwitzerskills.spigot.skywars.mysql.Players;
import de.schwitzerskills.spigot.skywars.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(SkyWars.getInstance().getGameState() == GameState.LOBBY){
            for(int i = 0; i < 10000; i++) {
                p.sendMessage("");
            }
            SkyWars.getInstance().maxplayers--;
            p.sendMessage(SkyWars.PREFIX + "§c" + SkyWars.getInstance().maxplayers);
            p.setGameMode(GameMode.SURVIVAL);
            p.setMaxHealth(20);
            p.setHealth(20);
            p.setFoodLevel(20);
            p.setExp(0);
            p.setLevel(0);
            p.getInventory().clear();
            SkyWars.getInstance().spec.remove(p);
            SkyWars.getInstance().nonspec.add(p);
            p.getInventory().setArmorContents(null);
            new ItemManager().Items(p);
            e.setJoinMessage(SkyWars.PREFIX + "§a§o" + p.getName() + "§7 hat das Spiel §abetreten");
            Bukkit.getOnlinePlayers().forEach(all -> {
                all.playSound(all.getLocation(), Sound.NOTE_PLING, 10, 10);
                all.removePotionEffect(PotionEffectType.INVISIBILITY);
                all.showPlayer(all);
            });

            new ScoreboardBuilder().scoreboard(p);
            new ScoreboardBuilder().setPrefix(p);

            Bukkit.getOnlinePlayers().forEach(all -> {
                new ScoreboardBuilder().updatescoreboard(all);
                new ScoreboardBuilder().updatePrefix(all);
            });


            if(!new Players().isExists(p.getUniqueId().toString())){
                new Players().addPlayer(p.getUniqueId().toString());
                new Kits().addKit(p.getUniqueId().toString(), "Standard");
            }

            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), () -> {
                p.teleport(SkyWars.getInstance().lm.getLocation("Spawn"));
            }, 5);
            SkyWars.getInstance().kills.put(p, 0);

            if(SkyWars.getInstance().maxplayers == 0){
                Countdown.started = true;
                new Countdown().start();
            }
        } else if(SkyWars.getInstance().getGameState() == GameState.inGAME){
            if(!Countdown.notlaunch) {
                e.setJoinMessage(null);
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), () -> {
                    p.teleport(SkyWars.getInstance().lm.getLocation("Spectator"));
                }, 5);
                new ItemManager().SpectatorItems(p);
                Title.resetTitle(p);
                new ScoreboardBuilder().scoreboard2(p);
                new ScoreboardBuilder().setPrefix(p);
                Bukkit.getOnlinePlayers().forEach(all -> {
                    new ScoreboardBuilder().updatescoreboard2(all);
                    new ScoreboardBuilder().updatePrefix(all);
                });
            } else {
                e.setJoinMessage(null);
                p.kickPlayer("");
            }
        } else if(SkyWars.getInstance().getGameState() == GameState.RESTART){
            e.setJoinMessage(null);
            p.kickPlayer(SkyWars.PREFIX + "§cDas Spiel ist schon zuende!");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(SkyWars.getInstance().getGameState() == GameState.LOBBY){
            SkyWars.getInstance().maxplayers++;
            e.setQuitMessage(SkyWars.PREFIX + "§c§o" + p.getName() + "§7 hat das Spiel §cverlassen");
            SkyWars.getInstance().nonspec.remove(p);
            SkyWars.getInstance().spec.remove(p);
            Bukkit.getOnlinePlayers().forEach(all -> {
                all.playSound(all.getLocation(), Sound.NOTE_BASS, 10, 10);
            });
            if(Countdown.started) {
                if (SkyWars.getInstance().maxplayers >= 1) {
                    Bukkit.getScheduler().cancelTask(Countdown.countT);
                    Bukkit.getOnlinePlayers().forEach(all -> {
                        all.playSound(all.getLocation(), Sound.GLASS, 10, 10);
                    });
                    Bukkit.broadcastMessage(SkyWars.PREFIX + "§cDer Countdown wurde abgebrochen, weil die maximalen Spieler nicht erreicht wurden!");
                    Countdown.count = 61;
                    SkyWars.getInstance().warten();
                    CMD_start.isStarted = false;
                    Countdown.started = false;
                    CMD_forcemap.isForce = false;
                }
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), () -> {
                Bukkit.getOnlinePlayers().forEach(all -> {
                    new ScoreboardBuilder().updatescoreboard(all);
                });
            }, 0);
        } else if(SkyWars.getInstance().getGameState() == GameState.inGAME){
            if(SkyWars.getInstance().nonspec.contains(p)) {
                SkyWars.getInstance().nonspec.remove(p);
                SkyWars.getInstance().spec.remove(p);
                e.setQuitMessage(SkyWars.PREFIX + "§c§o" + p.getName() + "§7 hat das Spiel §cverlassen");
                Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Es verbleibt §c" + SkyWars.getInstance().nonspec.size() + "§7 Spieler...");
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), () -> {
                    Bukkit.getOnlinePlayers().forEach(all -> {
                        new ScoreboardBuilder().updatescoreboard(all);
                    });
                }, 0);
                SkyWars.getInstance().ende();
            } else {
                e.setQuitMessage(null);
            }
        } else if(SkyWars.getInstance().getGameState() == GameState.RESTART){
            e.setQuitMessage(SkyWars.PREFIX + "§c§o" + p.getName() + "§7 hat das Spiel §cverlassen");
        }
    }
}
