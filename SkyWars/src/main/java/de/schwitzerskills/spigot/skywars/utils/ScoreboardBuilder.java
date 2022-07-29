package de.schwitzerskills.spigot.skywars.utils;

import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.mysql.Kits;
import de.schwitzerskills.spigot.skywars.mysql.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class ScoreboardBuilder {

    public Team map;
    public Team kit;
    public Team player;

    public void scoreboard(Player p){
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = sb.registerNewObjective("aaa", "bbb");

        obj.setDisplayName("§8» §c§lSkyWars §8«");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        map = sb.registerNewTeam("map");
        kit = sb.registerNewTeam("kit");
        player = sb.registerNewTeam("player");

        obj.getScore("§a").setScore(11);
        obj.getScore("§f§lMap:").setScore(10);

        map.setSuffix("§e" + new Maps().getMap());
        map.addEntry("§d");
        obj.getScore("§d").setScore(9);

        obj.getScore("§b").setScore(8);
        obj.getScore("§f§lDein Kit:").setScore(7);

        kit.setSuffix("§e" + new Kits().getKit(p.getUniqueId().toString()));
        kit.addEntry("§f");
        obj.getScore("§f").setScore(6);

        obj.getScore("§c").setScore(2);
        obj.getScore("§f§lSpieler:").setScore(1);

        player.setSuffix("§e" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
        player.addEntry("§g");
        obj.getScore("§g").setScore(0);

        p.setScoreboard(sb);
    }

    public void updatescoreboard(Player p){
        Scoreboard sb = p.getScoreboard();
        Objective obj = sb.getObjective("aaa");

        map = sb.getTeam("map");
        kit = sb.getTeam("kit");
        player = sb.getTeam("player");

        map.setSuffix("§e" + new Maps().getMap());
        map.addEntry("§d");
        obj.getScore("§d").setScore(9);

        kit.setSuffix("§e" + new Kits().getKit(p.getUniqueId().toString()));
        kit.addEntry("§f");
        obj.getScore("§f").setScore(6);

        player.setSuffix("§e" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
        player.addEntry("§g");
        obj.getScore("§g").setScore(0);
    }

    public int countm;
    public int counts;

    public Team kills;

    public void scoreboard2(Player p){
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = sb.registerNewObjective("ccc", "ddd");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        if(counts <= 9 && countm <= 9) {
            obj.setDisplayName("§8» §c§lSkyWars §8| §70" + countm + ":0" + counts + " §8«");
        } else if(counts <= 9) {
            obj.setDisplayName("§8» §c§lSkyWars §8| §7" + countm + ":0" + counts + " §8«");
        } else if(countm <= 9){
            obj.setDisplayName("§8» §c§lSkyWars §8| §70" + countm + ":" + counts + " §8«");
        } else {
            obj.setDisplayName("§8» §c§lSkyWars §8| §7" + countm + ":" + counts + " §8«");
        }

        player = sb.registerNewTeam("player");
        kills = sb.registerNewTeam("kills");

        obj.getScore("§a").setScore(14);
        obj.getScore("§f§lMap:").setScore(13);
        obj.getScore("§e" + new Maps().getMap()).setScore(12);

        obj.getScore("§b").setScore(11);
        obj.getScore("§f§lDein Kit:").setScore(10);
        if(SkyWars.getInstance().nonspec.contains(p)) {
            obj.getScore("§e" + new Kits().getKit(p.getUniqueId().toString())).setScore(9);
        } else if(SkyWars.getInstance().spec.contains(p)){
            obj.getScore("§e-").setScore(9);
        }

        obj.getScore("§c").setScore(8);
        obj.getScore("§f§lVerbleibende Spieler:").setScore(7);

        player.setSuffix("§e" + SkyWars.getInstance().nonspec.size());
        player.addEntry("§g");
        obj.getScore("§g").setScore(6);

        obj.getScore("§h").setScore(5);
        obj.getScore("§f§lKills:").setScore(4);

        if(SkyWars.getInstance().nonspec.contains(p)) {
            kills.setSuffix("§e" + SkyWars.getInstance().kills.get(p));
        } else if(SkyWars.getInstance().spec.contains(p)){
            kills.setSuffix("§e-");
        }
        kills.addEntry("§i");
        obj.getScore("§i").setScore(3);


        obj.getScore("§n").setScore(2);
        obj.getScore("§cTeams verboten").setScore(1);

        p.setScoreboard(sb);
    }

    public static int count;

    public void startAnimation(){
        counts = 0;
        countm = 0;
        count = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(all -> {

                    if(all.getScoreboard() == null)

                        scoreboard2(all);

                    if(counts <= 9 && countm <= 9) {
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§8» §c§lSkyWars §8| §70" + countm + ":0" + counts + " §8«");
                    } else if(counts <= 9) {
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§8» §c§lSkyWars §8| §7" + countm + ":0" + counts + " §8«");
                    } else if(countm <= 9){
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§8» §c§lSkyWars §8| §70" + countm + ":" + counts + " §8«");
                    } else {
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§8» §c§lSkyWars §8| §7" + countm + ":" + counts + " §8«");
                    }
                });

                counts++;

                if(counts == 60){
                    countm++;
                    counts = 0;
                }

            }
        }, 0, 20);
    }

    public void updatescoreboard2(Player p){
        Scoreboard sb = p.getScoreboard();
        Objective obj = sb.getObjective("ccc");

        player = sb.getTeam("player");
        kills = sb.getTeam("kills");

        player.setSuffix("§e" + SkyWars.getInstance().nonspec.size());
        player.addEntry("§g");
        obj.getScore("§g").setScore(6);

        if(SkyWars.getInstance().nonspec.contains(p)) {
            kills.setSuffix("§e" + SkyWars.getInstance().kills.get(p));
        } else if(SkyWars.getInstance().spec.contains(p)){
            kills.setSuffix("§e-");
        }
        obj.getScore("§i").setScore(3);

    }

    public void scoreboard3(Player p){
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = sb.registerNewObjective("eee", "fff");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§8» §c§lSkyWars §8«");

        obj.getScore("§a").setScore(4);
        obj.getScore("§f§lGewinner:").setScore(3);
        obj.getScore("§e" + SkyWars.getInstance().nonspec.iterator().next().getName()).setScore(2);
        obj.getScore("§c").setScore(1);

        p.setScoreboard(sb);
    }

    public Team spieler;

    public void setPrefix(Player p){
        Scoreboard sb = p.getScoreboard();

        spieler = sb.registerNewTeam("0001spieler");

        spieler.setPrefix("§e");

        Bukkit.getOnlinePlayers().forEach(all -> {
            spieler.addPlayer(all);
        });
    }

    public void updatePrefix(Player p){
        Scoreboard sb = p.getScoreboard();

        spieler = sb.getTeam("0001spieler");

        if (spieler == null) {
            setPrefix(p);
            return;
        }

        Bukkit.getOnlinePlayers().forEach(all -> {
            spieler.addPlayer(all);
        });
    }

    public Team team;

    public void setPrefix2(Player p, int t){
        Scoreboard sb = p.getScoreboard();

        team = sb.registerNewTeam("000" + t + "team");

        team.setPrefix("§eT" + t + " §8| §e");

        Bukkit.getOnlinePlayers().forEach(all -> {
            team.addPlayer(all);
        });
    }

    public void updatePrefix2(Player p, int t){
        Scoreboard sb = p.getScoreboard();

        team = sb.getTeam("000" + t + "team");

        if (team == null) {
            setPrefix2(p, 1);
            return;
        }

        Bukkit.getOnlinePlayers().forEach(all -> {
            team.addPlayer(all);
        });
    }
}
