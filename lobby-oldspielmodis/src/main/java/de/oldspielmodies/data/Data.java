package de.oldspielmodies.data;

import de.oldspielmodies.manager.ItemManager;
import de.oldspielmodies.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Data {


    public void onPlayerdefaultJoinSettings(final Player player){
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setLevel(2022);

        player.getInventory().setItem(4, new ItemManager(Material.COMPASS).setDisplayName("§8» §eCompass §8┃ §7Rightclick").toItemStack());
        new ScoreboardManager().setBoard(player);


    }

}
