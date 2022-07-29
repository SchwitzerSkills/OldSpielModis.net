package de.schwitzerskills.spigot.skywars.utils;

import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.commands.CMD_forcemap;
import de.schwitzerskills.spigot.skywars.commands.CMD_set;
import de.schwitzerskills.spigot.skywars.mysql.Kits;
import de.schwitzerskills.spigot.skywars.mysql.MapSystem;
import de.schwitzerskills.spigot.skywars.mysql.Maps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class Countdown {

    public static int count = 61;
    public static int countT;

    public static boolean started = false;

    public void start(){
        Bukkit.getScheduler().cancelTask(SkyWars.getInstance().count);
        countT = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.getInstance(), () -> {
            count--;
            Bukkit.getOnlinePlayers().forEach(all -> {
                new Actionbar(SkyWars.PREFIX + "§7Das Spiel startet in §c" + count + " §7Sekunden...").send(all);
            });
            if (count == 60 || count == 50 || count == 40 || count == 30 || count == 20 || count == 10 || count == 5 || count == 3 || count == 2) {
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
                CMD_forcemap.isForce = true;
                Bukkit.getOnlinePlayers().forEach(all -> {
                    Title.sendTitle(all, 20, 40, 20, "§8» §4SkyWars §8«", "§7Map: §c" + new Maps().getMap());
                });
                Bukkit.broadcastMessage(SkyWars.PREFIX + "§cTeaming §7mit anderen Spielern ist verboten und wird mit einem §4Ban §7bestraft");
            }
            if(count == 1){
                Bukkit.getOnlinePlayers().forEach(all -> {
                    new Actionbar(SkyWars.PREFIX + "§7Das Spiel startet in §ceine §7Sekunden...").send(all);
                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10, 10);
                });
            } else if(count == 0) {
                Bukkit.getScheduler().cancelTask(countT);
                SkyWars.getInstance().setGameState(GameState.inGAME);
                Bukkit.getOnlinePlayers().forEach(all -> {
                    Title.resetTitle(all);
                    new Actionbar(SkyWars.PREFIX + "§cDas Spiel startet!").send(all);
                    all.playSound(all.getLocation(), Sound.NOTE_PLING, 10, 10);
                    all.getInventory().clear();
                    if (new Kits().whichKit(all.getUniqueId().toString(), "Standard")) {
                        all.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).toItemStack());
                        all.getInventory().setItem(1, new ItemBuilder(Material.IRON_PICKAXE).toItemStack());
                        all.getInventory().setItem(2, new ItemBuilder(Material.IRON_AXE).toItemStack());
                    } else if (new Kits().whichKit(all.getUniqueId().toString(), "Enderman")) {
                        all.getInventory().setItem(0, new ItemBuilder(Material.ENDER_PEARL).setName("§8➤ §e§lEnderperle").toItemStack());
                    } else if (new Kits().whichKit(all.getUniqueId().toString(), "Enterhaken")) {
                        all.getInventory().setItem(0, new ItemBuilder(Material.FISHING_ROD).setName("§8➤ §e§lEnterhaken").toItemStack());
                    } else if (new Kits().whichKit(all.getUniqueId().toString(), "Baumeister")) {
                        all.getInventory().setItem(0, new ItemBuilder(Material.BRICK, 64).toItemStack());
                    }
                });
                mapTeleport();
                stop();
                Bukkit.getOnlinePlayers().forEach(all -> {
                    new ScoreboardBuilder().scoreboard2(all);
                    new ScoreboardBuilder().setPrefix(all);
                    new ScoreboardBuilder().updatePrefix(all);
                });
            }
        }, 0, 20);
    }

    public void mapTeleport(){
        int count = 1;
        for(Player alive : SkyWars.getInstance().nonspec){
            alive.teleport(SkyWars.getInstance().lm.getSpawns(count, new Maps().getMap()));
            count++;
        }
    }

    int countstop = 6;
    int counttstop;
    public int stopt;

    public static boolean notlaunch = false;


    public void stop(){
        counttstop = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.getInstance(), () -> {
            stopt = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.getInstance(), this::mapTeleport, 0, 5);
            countstop--;
            notlaunch = true;
            Bukkit.getOnlinePlayers().forEach(all -> {
                new Actionbar(SkyWars.PREFIX + "§7Die Runde startet in §c" + countstop + " §7Sekunden...").send(all);
            });
            if (countstop == 10 || countstop == 5 || countstop == 3 || countstop == 2) {
                Bukkit.getOnlinePlayers().forEach(all -> {
                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10, 10);
                });
            }
            if (countstop == 2 || countstop == 3 || countstop == 1) {
                Bukkit.getOnlinePlayers().forEach(all -> {
                    Title.sendTitle(all, 20, 50, 20, "§c" + countstop, "");
                });
            }
            if(countstop == 1){
                Bukkit.getOnlinePlayers().forEach(all -> {
                    new Actionbar(SkyWars.PREFIX + "§7Die Runde startet in §ceine §7Sekunden...").send(all);
                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10, 10);
                });
            } else if (countstop == 0) {
                Bukkit.getScheduler().cancelAllTasks();
                notlaunch = false;
                Bukkit.getOnlinePlayers().forEach(all -> {
                    Title.resetTitle(all);
                    new Actionbar(SkyWars.PREFIX + "§cDie Runde startet!").send(all);
                    all.playSound(all.getLocation(), Sound.NOTE_PLING, 10, 10);
                });
                new ScoreboardBuilder().startAnimation();
                schutzzeit();
            }
        }, 0, 20);
    }

    public int schutzzeit = 21;
    public int tschutzzeit;
    public static boolean schutzzeitbo = false;

    public void schutzzeit(){
        tschutzzeit = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.getInstance(), () -> {
            schutzzeit--;
            schutzzeitbo = true;
            Bukkit.getOnlinePlayers().forEach(all -> {
                new Actionbar(SkyWars.PREFIX + "§7Die Schutzzeit endet in §c" + schutzzeit + " §7Sekunden...").send(all);
            });
            if (schutzzeit == 10 || schutzzeit == 5 || schutzzeit == 3 || schutzzeit == 2) {
                Bukkit.getOnlinePlayers().forEach(all -> {
                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10, 10);
                });
            }
            if (schutzzeit == 2 || schutzzeit == 3 || schutzzeit == 1) {
                Bukkit.getOnlinePlayers().forEach(all -> {
                    Title.sendTitle(all, 20, 50, 20, "§c" + schutzzeit, "");
                });
            }
            if(schutzzeit == 1){
                Bukkit.getOnlinePlayers().forEach(all -> {
                    new Actionbar(SkyWars.PREFIX + "§7Die Schutzzeit endet in §ceine §7Sekunden...").send(all);
                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10, 10);
                });
            } else if (schutzzeit == 0) {
                Bukkit.getScheduler().cancelTask(tschutzzeit);
                Bukkit.getOnlinePlayers().forEach(all -> {
                    new Actionbar(SkyWars.PREFIX + "§cDie Schutzzeit ist geendet!").send(all);
                    all.playSound(all.getLocation(), Sound.NOTE_PLING, 10, 10);
                    Title.resetTitle(all);
                });
                schutzzeitbo = false;
            }
        }, 0, 20);
    }
}
