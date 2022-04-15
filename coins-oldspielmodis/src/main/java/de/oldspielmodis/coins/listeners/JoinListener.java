package de.oldspielmodis.coins.listeners;

import de.oldspielmodis.coins.mysql.Coins;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Coins coin = new Coins();
        if(!coin.isUserExists(p.getUniqueId())){
            p.sendMessage("dsds");
            coin.addUser(p.getUniqueId());
            p.sendMessage("dsds");
        }
    }
}
