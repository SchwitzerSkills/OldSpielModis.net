package de.oldspielmodies.listener;

import de.oldspielmodies.data.Data;
import de.oldspielmodies.items.Playerhider;
import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.manager.LocationManager;
import de.oldspielmodies.mysql.News;
import de.oldspielmodies.mysql.Setting;
import de.oldspielmodies.scoreboard.ScoreboardManager;
import de.oldspielmodis.spigot.nick.mysql.Nick;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;


public class PlayerConnetionListener implements Listener {


    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event){
        event.setJoinMessage(null);

        new Data().onPlayerdefaultJoinSettings(event.getPlayer());
        new Data().DefaultItems(event.getPlayer());

        for(int i = 0; i < 200; i++){
            event.getPlayer().sendMessage("");
        }
        News news = new News();
        List<String> list = news.getUpdates();

        event.getPlayer().sendMessage(Lobbysystem.PREFIX + "Welcome to §eOldSpielModis.net\n      §8➥ §7Your §eMinecraft §7Network\n§a \n");
            if (news.isExistsNews()) {
                event.getPlayer().sendMessage("§8§m------§8[ §cNEWS §8(§7" + news.getUpdatesDatum() + "§8) §8]§8§m------\n§a \n");
                for (String list1 : list) {
                    event.getPlayer().sendMessage("§8» §7" + ChatColor.translateAlternateColorCodes('&', list1));
                }
            } else {
                event.getPlayer().sendMessage("§8» §cNEWS §8(§7-§8)\n §8» §7-");
            }
        event.getPlayer().sendMessage("\n§8§m-------------------------------");

        if(!event.getPlayer().hasPlayedBefore()){
            event.getPlayer().teleport(new LocationManager().getLocation("spawn"));
        }

        Playerhider.show.add(event.getPlayer());

        Setting setting = new Setting();
        if (setting.hasSetting(event.getPlayer().getUniqueId().toString(), "spawn")) {
            LocationManager locationManager = new LocationManager();
            event.getPlayer().teleport(locationManager.getLocation("spawn"));
        }

        Bukkit.getOnlinePlayers().forEach(all -> {
            if (Playerhider.hide.contains(all)) {
                all.hidePlayer(event.getPlayer());
            }
            if (Playerhider.members.contains(all)) {
                if (!event.getPlayer().hasPermission("oldspielmodis.hider.members")) {
                    all.hidePlayer(event.getPlayer());
                }
            }
        });

        Nick nick = new Nick();
        if(nick.isNicked(event.getPlayer().getUniqueId().toString())) {
            ScoreboardManager scoreboardManager = new ScoreboardManager();
            scoreboardManager.removeBoard(event.getPlayer());

            new BukkitRunnable() {
                @Override
                public void run() {
                    scoreboardManager.setBoardMain(event.getPlayer());
                }
            }.runTaskLater(Lobbysystem.getInstance(), 4);
        }
    }


    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event){
        event.setQuitMessage(null);

    }

}
