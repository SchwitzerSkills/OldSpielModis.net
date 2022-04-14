package de.oldspielmodies.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {

    public static boolean scoreboard = false;

    public void setBoard(final Player player) {

        final Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective obj = sb.registerNewObjective("aaa", "bbb");


        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§8✗ §e§lOldSpielModis.de §8✗");
        obj.getScore("§8§m----------------").setScore(21);
        obj.getScore("§4").setScore(20);
        obj.getScore(" §8» §7Rang").setScore(19);
        if (player.hasPermission("OldSpielModis.Admin")) {
            obj.getScore("  §8➥ §4Administrator").setScore(18);
        } else
            obj.getScore("  §8➥ §7User").setScore(18);
        obj.getScore("§2").setScore(17);
        obj.getScore(" §8» §7Server").setScore(16);
        obj.getScore("  §8➥ §e§lLobby-1").setScore(15);
        obj.getScore("§8").setScore(14);
        obj.getScore(" §8» §7Coins").setScore(13);
        obj.getScore("  §8➥  §e§l0").setScore(12);
        obj.getScore("§5").setScore(11);
        obj.getScore("§8§m----------------§7").setScore(10);


        player.setScoreboard(sb);
        scoreboard = true;

    }



    }


