package de.oldspielmodis.proxy.nick.listeners;

import de.oldspielmodis.proxy.nick.mysql.Nick;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e){
        ProxiedPlayer p = e.getPlayer();
        if (p.hasPermission("oldspielmodis.youtuber")) {
            Nick nick = new Nick();
            if(!nick.isRegistered(p.getUniqueId().toString())){
                nick.register(p.getUniqueId().toString(), "-", "-", false);
            }
        }
    }
}
