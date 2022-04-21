package de.oldspielmodies.data;

import de.oldspielmodies.manager.ItemManager;
import de.oldspielmodies.mysql.Setting;
import de.oldspielmodies.scoreboard.ScoreboardManager;
import de.oldspielmodis.spigot.nick.mysql.Nick;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class Data {


    public void onPlayerdefaultJoinSettings(final Player player){
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setLevel(2022);

        Setting setting = new Setting();
        if(!setting.hasSetting(player.getUniqueId().toString(), "scoreboard")) {
            new ScoreboardManager().setBoard(player);
        }


    }

    public void DefaultItems(Player player){
        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemManager(Material.COMPASS).setDisplayName("§8» §eCompass §8┃ §7Rightclick").toItemStack());
        player.getInventory().setItem(1, new ItemManager(Material.BLAZE_ROD).setDisplayName("§8» §ePlayer hider §8┃ §7Rightclick").toItemStack());
        player.getInventory().setItem(7, new ItemManager(Material.REDSTONE_COMPARATOR).setDisplayName("§8» §eSettings §8┃ §7Rightclick").toItemStack());
        player.getInventory().setItem(8, new ItemManager(Material.FISHING_ROD).setUnbreakable(true).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setDisplayName("§8» §eGrappling hook §8┃ §7Rightclick").toItemStack());

        if(!player.hasPermission("oldspielmodis.nicktool")) {
            player.getInventory().setItem(4, new ItemManager(Material.CHEST).setDisplayName("§8» §eGadgets §8┃ §7Rightclick").toItemStack());
        } else {
            player.getInventory().setItem(3, new ItemManager(Material.CHEST).setDisplayName("§8» §eGadgets §8┃ §7Rightclick").toItemStack());
            Nick nick = new Nick();
            if(!nick.isNicked(player.getUniqueId().toString())) {
                player.getInventory().setItem(5, new ItemManager(Material.NAME_TAG).setDisplayName("§8» §eNick-Tool §8(§cDisabled§8) §8┃ §7Rightclick").toItemStack());
            } else {
                player.getInventory().setItem(5, new ItemManager(Material.NAME_TAG).addEnchant(Enchantment.THORNS, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setDisplayName("§8» §eNick-Tool §8(§aEnabled§8) §8┃ §7Rightclick").toItemStack());
            }
        }
    }

}
