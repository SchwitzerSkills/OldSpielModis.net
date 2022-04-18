package de.oldspielmodies.items;

import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.manager.ItemManager;
import de.oldspielmodis.spigot.nick.mysql.Nick;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;

public class Nicktool implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Nick nick = new Nick();
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eNick-Tool §8(§cDisabled§8) §8┃ §7Rightclick")) {
                if(e.getItem().getType() == Material.NAME_TAG){
                    if(!nick.isNicked(p.getUniqueId().toString())){
                        p.sendMessage(Lobbysystem.PREFIX + "You have activated the Nick tool.");
                        nick.updateIsNicked(p.getUniqueId().toString(), "true");
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        p.getInventory().setItem(5, new ItemManager(Material.NAME_TAG).addEnchant(Enchantment.THORNS, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setDisplayName("§8» §eNick-Tool §8(§aEnabled§8) §8┃ §7Rightclick").toItemStack());
                        p.updateInventory();
                    }
                }
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eNick-Tool §8(§aEnabled§8) §8┃ §7Rightclick")) {
                if(nick.isNicked(p.getUniqueId().toString())) {
                    p.sendMessage(Lobbysystem.PREFIX + "You have deactivated the Nick tool.");
                    nick.updateIsNicked(p.getUniqueId().toString(), "false");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                    p.getInventory().setItem(5, new ItemManager(Material.NAME_TAG).setDisplayName("§8» §eNick-Tool §8(§cDisabled§8) §8┃ §7Rightclick").toItemStack());
                    p.updateInventory();
                }
            }
        }
    }
}
