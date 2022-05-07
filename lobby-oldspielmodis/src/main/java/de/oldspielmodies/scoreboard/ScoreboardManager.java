package de.oldspielmodies.scoreboard;

import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.mysql.Setting;
import de.oldspielmodis.coins.mysql.Coins;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.api.service.ICloudService;
import eu.thesimplecloud.api.servicegroup.ICloudServiceGroup;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class ScoreboardManager {

    public static boolean scoreboard = false;

    public static Team coins;
    public static Team server;

    public String[] animation = new String[]{
      "", "§8✗", "§8✗ ", "§8✗ §6§lO", "§8✗ §6§lOl", "§8✗ §6§lOld", "§8✗ §6§lOldS", "§8✗ §6§lOldSp", "§8✗ §6§lOldSpi", "§8✗ §6§lOldSpie", "§8✗ §6§lOldSpiel",
            "§8✗ §6§lOldSpielM", "§8✗ §6§lOldSpielMo", "§8✗ §6§lOldSpielMod", "§8✗ §6§lOldSpielModi", "§8✗ §6§lOldSpielModis", "§8✗ §6§lOldSpielModis.",
            "§8✗ §6§lOldSpielModis.d", "§8✗ §6§lOldSpielModis.de", "§8✗ §6§lOldSpielModis.de ", "§8✗ §6§lOldSpielModis.de §8✗"
    };

    public int animationCount;

    public void setBoard(final Player player) {
            Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = sb.registerNewObjective("aaa", "bbb");


            coins = sb.registerNewTeam("coins");
        server = sb.registerNewTeam("server");

            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName(animation[animationCount]);
            obj.getScore("§8§m------------------").setScore(21);
            obj.getScore("§4").setScore(20);
            obj.getScore(" §8» §7Rank").setScore(19);
            IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());
            assert permissionPlayer != null;
            if (permissionPlayer.hasPermissionGroup("Admin")) {
                obj.getScore("  §8➥ §4Administrator").setScore(18);
            } else if(permissionPlayer.hasPermissionGroup("Moderator")) {
                obj.getScore("  §8➥ §cMod").setScore(18);
            } else if (permissionPlayer.hasPermissionGroup("Developer")) {
                obj.getScore("  §8➥ §bDev").setScore(18);
            } else if (permissionPlayer.hasPermissionGroup("Content")) {
                obj.getScore("  §8➥ §eContent").setScore(18);
            } else if (permissionPlayer.hasPermissionGroup("Supporter")) {
                obj.getScore("  §8➥ §9Sup").setScore(18);
            } else if (permissionPlayer.hasPermissionGroup("Builder")) {
                obj.getScore("  §8➥ §2Builder").setScore(18);
            } else if (permissionPlayer.hasPermissionGroup("Premium")) {
                obj.getScore("  §8➥ §6Premium").setScore(18);
            } else {
                obj.getScore("  §8➥ §7User").setScore(18);
            }
            obj.getScore("§2").setScore(17);
            obj.getScore(" §8» §7Server").setScore(16);
            ICloudPlayer p = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(player.getName());
            server.setSuffix("  §8➥ §e" + p.getConnectedServerName());
            server.addEntry("§i");
            obj.getScore("§i").setScore(15);
            obj.getScore("§8").setScore(14);
            obj.getScore(" §8» §7Coins").setScore(13);
            Coins c = new Coins();
        String coin = String.valueOf(c.getCoins(player.getUniqueId().toString()));
        if(c.getCoins(player.getUniqueId().toString()) >= 1000000000) {
            coins.setSuffix("  §8➥ §e∞");
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        } else if(c.getCoins(player.getUniqueId().toString()) >= 1000000) {
            coin = coin.substring(0, coin.length()-6);
            coins.setSuffix("  §8➥ §e" + coin + "Mio");
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        } else if(c.getCoins(player.getUniqueId().toString()) >= 1000){
            coin = coin.substring(0, coin.length()-3);
            coins.setSuffix("  §8➥ §e" + coin + "K");
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        } else {
            coins.setSuffix("  §8➥ §e" + c.getCoins(player.getUniqueId().toString()));
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        }
            obj.getScore("§5").setScore(11);
            obj.getScore("§8§m------------------§7").setScore(10);


            player.setScoreboard(sb);
            scoreboard = true;
    }

    public void updateBoard(String uuid){
        Scoreboard sb = Bukkit.getPlayer(UUID.fromString(uuid)).getScoreboard();
        Objective obj = sb.getObjective("aaa");

        coins = sb.getTeam("coins");

        Coins c = new Coins();
        String coin = String.valueOf(c.getCoins(Bukkit.getPlayer(UUID.fromString(uuid)).getUniqueId().toString()));
        if(c.getCoins(Bukkit.getPlayer(UUID.fromString(uuid)).getUniqueId().toString()) >= 1000000000) {
            coins.setSuffix("  §8➥ §e∞");
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        } else if(c.getCoins(Bukkit.getPlayer(UUID.fromString(uuid)).getUniqueId().toString()) >= 1000000) {
            coin = coin.substring(0, coin.length()-6);
            coins.setSuffix("  §8➥ §e" + coin + "Mio");
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        } else if(c.getCoins(Bukkit.getPlayer(UUID.fromString(uuid)).getUniqueId().toString()) >= 1000){
            coin = coin.substring(0, coin.length()-3);
            coins.setSuffix("  §8➥ §e" + coin + "K");
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        } else {
            coins.setSuffix("  §8➥ §e" + c.getCoins(Bukkit.getPlayer(UUID.fromString(uuid)).getUniqueId().toString()));
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        }

        ICloudPlayer p = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(Bukkit.getPlayer(UUID.fromString(uuid)).getName());
        server.setSuffix("  §8➥ §e" + p.getConnectedServerName());
        server.addEntry("§i");
        obj.getScore("§i").setScore(15);

    }

    public void removeBoard(Player player){
        Scoreboard sb = player.getScoreboard();
        Objective obj = sb.getObjective("aaa");
        obj.unregister();

    }

    public void setBoardMain(final Player player) {
        Scoreboard sb = player.getScoreboard();
        Objective obj = sb.registerNewObjective("aaa", "bbb");


        coins = sb.getTeam("coins");
        server = sb.getTeam("server");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(animation[animationCount]);
        obj.getScore("§8§m------------------").setScore(21);
        obj.getScore("§4").setScore(20);
        obj.getScore(" §8» §7Rank").setScore(19);
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());
        assert permissionPlayer != null;
        if (permissionPlayer.hasPermissionGroup("Admin")) {
            obj.getScore("  §8➥ §4Administrator").setScore(18);
        } else if(permissionPlayer.hasPermissionGroup("Moderator")) {
            obj.getScore("  §8➥ §cMod").setScore(18);
        } else if (permissionPlayer.hasPermissionGroup("Developer")) {
            obj.getScore("  §8➥ §bDev").setScore(18);
        } else if (permissionPlayer.hasPermissionGroup("Content")) {
            obj.getScore("  §8➥ §eContent").setScore(18);
        } else if (permissionPlayer.hasPermissionGroup("Supporter")) {
            obj.getScore("  §8➥ §9Sup").setScore(18);
        } else if (permissionPlayer.hasPermissionGroup("Builder")) {
            obj.getScore("  §8➥ §2Builder").setScore(18);
        } else if (permissionPlayer.hasPermissionGroup("Premium")) {
            obj.getScore("  §8➥ §6Premium").setScore(18);
        } else {
            obj.getScore("  §8➥ §7User").setScore(18);
        }
        obj.getScore("§2").setScore(17);
        obj.getScore(" §8» §7Server").setScore(16);
        ICloudPlayer p = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(player.getName());
        server.setSuffix("  §8➥ §e" + p.getConnectedServerName());
        server.addEntry("§i");
        obj.getScore("§i").setScore(15);
        obj.getScore("§8").setScore(14);
        obj.getScore(" §8» §7Coins").setScore(13);
        Coins c = new Coins();
        String coin = String.valueOf(c.getCoins(player.getUniqueId().toString()));
        if(c.getCoins(player.getUniqueId().toString()) >= 1000000000) {
            coins.setSuffix("  §8➥ §e∞");
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        } else if(c.getCoins(player.getUniqueId().toString()) >= 1000000) {
            coin = coin.substring(0, coin.length()-6);
            coins.setSuffix("  §8➥ §e" + coin + "Mio");
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        } else if(c.getCoins(player.getUniqueId().toString()) >= 1000){
            coin = coin.substring(0, coin.length()-3);
            coins.setSuffix("  §8➥ §e" + coin + "K");
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        } else {
            coins.setSuffix("  §8➥ §e" + c.getCoins(player.getUniqueId().toString()));
            coins.addEntry("§c");
            obj.getScore("§c").setScore(12);
        }
        obj.getScore("§c").setScore(12);
        obj.getScore("§5").setScore(11);
        obj.getScore("§8§m------------------§7").setScore(10);


        player.setScoreboard(sb);
        scoreboard = true;
    }

    public void startAnimation(){
        animationCount = 0;
        Setting setting = new Setting();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Lobbysystem.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(current -> {

                if(!setting.hasSetting(current.getUniqueId().toString(), "scoreboard")) {
                    if (current.getScoreboard() == null) {
                        setBoard(current);
                    }

                    current.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(animation[animationCount]);
                }
            });

            animationCount++;

            if(animationCount == animation.length)
                animationCount = 0;
        }, 0, 10);
    }

    }


