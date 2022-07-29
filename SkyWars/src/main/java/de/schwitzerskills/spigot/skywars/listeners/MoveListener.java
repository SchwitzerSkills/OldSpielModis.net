package de.schwitzerskills.spigot.skywars.listeners;

import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.commands.CMD_setheight;
import de.schwitzerskills.spigot.skywars.mysql.MapSystem;
import de.schwitzerskills.spigot.skywars.utils.Countdown;
import de.schwitzerskills.spigot.skywars.utils.GameState;
import de.schwitzerskills.spigot.skywars.utils.ItemManager;
import de.schwitzerskills.spigot.skywars.utils.ScoreboardBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class MoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Player nearest = null;
        try {
            if (SkyWars.getInstance().getGameState() == GameState.inGAME) {
                    if (SkyWars.getInstance().nonspec.contains(p)) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if(players.getLocation().distanceSquared(p.getLocation()) < 4){
                                if(p != players){
                                    if(SkyWars.getInstance().spec.contains(players)) {
                                        if(!PlayerInteractListener.task.contains(players)) {
                                            players.getLocation().distanceSquared(p.getLocation());
                                        nearest = players;
                                        players.setCompassTarget(nearest.getLocation());
                                        players.setVelocity(new Vector(1, 2, 1));
                                        players.playSound(players.getLocation(), Sound.IRONGOLEM_DEATH, 10, 10);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(CMD_setheight.cfg.contains("Height." + new MapSystem().getMap(1)) && CMD_setheight.cfg.contains("Height." + new MapSystem().getMap(2)) && CMD_setheight.cfg.contains("Height." + new MapSystem().getMap(3))) {
                if(!Countdown.schutzzeitbo) {
                    if (CMD_setheight.cfg.getDouble("Height." + new MapSystem().getMap(1)) > p.getLocation().getY() && p.getWorld().getName().equalsIgnoreCase(new MapSystem().getMap(1))) {
                        if(SkyWars.getInstance().nonspec.contains(p)) {
                            p.setHealth(20);
                            p.setVelocity(new Vector(0, 0.5, 0));
                            new ItemManager().SpectatorItems(p);
                            p.teleport(SkyWars.getInstance().lm.getLocation("Spectator"));
                            Bukkit.broadcastMessage(SkyWars.PREFIX + "§c" + p.getName() + "§7 ist gestorben");
                            Bukkit.getOnlinePlayers().forEach(all -> {
                                new ScoreboardBuilder().updatescoreboard2(all);
                            });
                            if (SkyWars.getInstance().nonspec.size() >= 2) {
                                Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Es verbleiben §c" + SkyWars.getInstance().nonspec.size() + "§7 Spieler...");
                            } else {
                                Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Es verbleibt §c" + SkyWars.getInstance().nonspec.size() + "§7 Spieler...");
                            }
                            SkyWars.getInstance().ende();
                        } else {
                            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), () -> {
                                p.teleport(SkyWars.getInstance().lm.getLocation("Spectator"));
                            }, 5);
                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 10);
                        }
                    } else if (CMD_setheight.cfg.getDouble("Height." + new MapSystem().getMap(2)) > p.getLocation().getY() && p.getWorld().getName().equalsIgnoreCase(new MapSystem().getMap(2))) {
                        if(SkyWars.getInstance().nonspec.contains(p)) {
                            p.setHealth(20);
                            p.setVelocity(new Vector(0, 0.5, 0));
                            new ItemManager().SpectatorItems(p);
                            p.teleport(SkyWars.getInstance().lm.getLocation("Spectator"));
                            Bukkit.broadcastMessage(SkyWars.PREFIX + "§c" + p.getName() + "§7 ist gestorben");
                            Bukkit.getOnlinePlayers().forEach(all -> {
                                new ScoreboardBuilder().updatescoreboard2(all);
                            });
                            if (SkyWars.getInstance().nonspec.size() >= 2) {
                                Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Es verbleiben §c" + SkyWars.getInstance().nonspec.size() + "§7 Spieler...");
                            } else {
                                Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Es verbleibt §c" + SkyWars.getInstance().nonspec.size() + "§7 Spieler...");
                            }
                            SkyWars.getInstance().ende();
                        } else {
                            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), () -> {
                                p.teleport(SkyWars.getInstance().lm.getLocation("Spectator"));
                            }, 5);
                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 10);
                        }
                    } else if (CMD_setheight.cfg.getDouble("Height." + new MapSystem().getMap(3)) > p.getLocation().getY() && p.getWorld().getName().equalsIgnoreCase(new MapSystem().getMap(3))) {
                        if(SkyWars.getInstance().nonspec.contains(p)) {
                            p.setHealth(20);
                            p.setVelocity(new Vector(0, 0.5, 0));
                            new ItemManager().SpectatorItems(p);
                            p.teleport(SkyWars.getInstance().lm.getLocation("Spectator"));
                            Bukkit.broadcastMessage(SkyWars.PREFIX + "§c" + p.getName() + "§7 ist gestorben");
                            Bukkit.getOnlinePlayers().forEach(all -> {
                                new ScoreboardBuilder().updatescoreboard2(all);
                            });
                            if (SkyWars.getInstance().nonspec.size() >= 2) {
                                Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Es verbleiben §c" + SkyWars.getInstance().nonspec.size() + "§7 Spieler...");
                            } else {
                                Bukkit.broadcastMessage(SkyWars.PREFIX + "§7Es verbleibt §c" + SkyWars.getInstance().nonspec.size() + "§7 Spieler...");
                            }
                            SkyWars.getInstance().ende();
                        } else {
                            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), () -> {
                                p.teleport(SkyWars.getInstance().lm.getLocation("Spectator"));
                            }, 5);
                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 10);
                        }
                    }
                }
            }
        } catch (Exception e1){
        }
    }
}
