package de.oldspielmodis.bungee.listeners;

import de.oldspielmodis.bungee.mysql.Ban;
import de.oldspielmodis.bungee.utils.DateToMilliSeconds;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e){
        ProxiedPlayer p = e.getPlayer();
        Ban ban = new Ban();
        long dur = DateToMilliSeconds.getMilliseconds(ban.getDurationFromBan(p.getUniqueId().toString()));
        long dur2 = DateToMilliSeconds.getMilliseconds2(ban.getTimeFromBan(p.getUniqueId().toString()) + " " + ban.getDurationFromBan(p.getUniqueId().toString()));
        if(ban.isBanned(p.getUniqueId().toString())) {

                if (System.currentTimeMillis() < dur || System.currentTimeMillis() < dur2) {
                    p.disconnect("§8§m---------------\n§a\n§8§m §8» §eOldSpielModis.de\n§a\n§7Your banned from Server!\nreason: §e" + ban.getReasonFromBan(p.getUniqueId().toString()) + "\n§7time: §e" + ban.getDurationFromBan(p.getUniqueId().toString()) + "\n§a\n§8§m---------------");
                } else {
                    ban.removeBan(p.getUniqueId().toString());
                }
        }
    }
}
