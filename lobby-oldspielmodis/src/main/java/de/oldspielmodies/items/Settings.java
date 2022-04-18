package de.oldspielmodies.items;

import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.manager.ItemManager;
import de.oldspielmodies.mysql.Setting;
import de.oldspielmodies.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;

public class Settings implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eSettings §8┃ §7Rightclick")){
                if(e.getItem().getType() == Material.REDSTONE_COMPARATOR){
                    Inventory inv = Bukkit.createInventory(p, 9*3, "§8» §eSettings");

                    Setting setting = new Setting();
                    if(!setting.hasSetting(p.getUniqueId().toString(), "scoreboard")) {
                        inv.setItem(11, new ItemManager(Material.COMMAND).setDisplayName("§8» §eScoreboard §8┃ §7Rightclick").setLore("§aEnabled").toItemStack());
                    } else {
                        inv.setItem(11, new ItemManager(Material.COMMAND).setDisplayName("§8» §eScoreboard §8┃ §7Rightclick").setLore("§cDisabled").toItemStack());
                    }
                    if(setting.hasSetting(p.getUniqueId().toString(), "spawn")) {
                        inv.setItem(13, new ItemManager(Material.GLOWSTONE).setDisplayName("§8» §eJoin at the spawn §8┃ §7Rightclick").setLore("§aEnabled").toItemStack());
                    } else {
                        inv.setItem(13, new ItemManager(Material.GLOWSTONE).setDisplayName("§8» §eJoin at the spawn §8┃ §7Rightclick").setLore("§cDisabled").toItemStack());
                    }

                    if(setting.hasSetting(p.getUniqueId().toString(), "inv")) {
                        inv.setItem(15, new ItemManager(Material.NOTE_BLOCK).setDisplayName("§8» §eScrollsound §8┃ §7Rightclick").setLore("§aEnabled").toItemStack());
                    } else {
                        inv.setItem(15, new ItemManager(Material.NOTE_BLOCK).setDisplayName("§8» §eScrollsound §8┃ §7Rightclick").setLore("§cDisabled").toItemStack());
                    }

                    p.openInventory(inv);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        try {
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eScoreboard §8┃ §7Rightclick")) {
                if (e.getCurrentItem().getType() == Material.COMMAND) {
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                    ScoreboardManager scoreboardManager = new ScoreboardManager();
                    Setting setting = new Setting();
                    if (!setting.hasSetting(p.getUniqueId().toString(), "scoreboard")) {
                        setting.setSetting(p.getUniqueId().toString(), "scoreboard");
                        p.sendMessage(Lobbysystem.PREFIX + "You have disabled the scoreboard.");
                        scoreboardManager.removeBoard(p);
                    } else {
                        setting.removeSetting(p.getUniqueId().toString(), "scoreboard");
                        p.sendMessage(Lobbysystem.PREFIX + "You have activated the scoreboard.");
                        scoreboardManager.setBoardMain(p);
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eJoin at the spawn §8┃ §7Rightclick")) {
                if (e.getCurrentItem().getType() == Material.GLOWSTONE) {
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                    Setting setting = new Setting();
                    if (!setting.hasSetting(p.getUniqueId().toString(), "spawn")) {
                        setting.setSetting(p.getUniqueId().toString(), "spawn");
                        p.sendMessage(Lobbysystem.PREFIX + "You have activated join at the spawn.");
                    } else {
                        setting.removeSetting(p.getUniqueId().toString(), "spawn");
                        p.sendMessage(Lobbysystem.PREFIX + "You have disabled join at the spawn.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eScrollsound §8┃ §7Rightclick")) {
                if (e.getCurrentItem().getType() == Material.NOTE_BLOCK) {
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                    Setting setting = new Setting();
                    if (!setting.hasSetting(p.getUniqueId().toString(), "inv")) {
                        setting.setSetting(p.getUniqueId().toString(), "inv");
                        p.sendMessage(Lobbysystem.PREFIX + "You have activated the scrollsound.");
                    } else {
                        setting.removeSetting(p.getUniqueId().toString(), "inv");
                        p.sendMessage(Lobbysystem.PREFIX + "You have disabled the scrollsound.");
                    }
                }
            }
        } catch (Exception e1){
        }
    }

    @EventHandler
    public void onScroll(PlayerItemHeldEvent e){
        Player p = e.getPlayer();
        Setting setting = new Setting();
        if (setting.hasSetting(p.getUniqueId().toString(), "inv")) {
            p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
        }
    }
}
