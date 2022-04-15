package de.oldspielmodies.listener;

import de.oldspielmodies.scoreboard.ScoreboardManager;
import de.oldspielmodis.coins.event.PlayerCoinsChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerCoinsChangeListener implements Listener {

    @EventHandler
    public void onCoinsChange(PlayerCoinsChangeEvent e){
        Player player = Bukkit.getPlayer(e.getUuid());
        new ScoreboardManager().updateBoard(player);
    }
}
