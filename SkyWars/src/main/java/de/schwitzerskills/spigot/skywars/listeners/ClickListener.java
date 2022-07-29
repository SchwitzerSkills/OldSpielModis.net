package de.schwitzerskills.spigot.skywars.listeners;

import de.schwitzerskills.spigot.coinsystem.mysql.Coins;
import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.mysql.KitSystem;
import de.schwitzerskills.spigot.skywars.mysql.Kits;
import de.schwitzerskills.spigot.skywars.utils.ItemManager;
import de.schwitzerskills.spigot.skywars.utils.ScoreboardBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

public class ClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        try{
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §e§lStandard")){
                if(e.getCurrentItem().getType() == Material.IRON_PICKAXE){
                    if(e.getCurrentItem().getItemMeta().getLore().contains("§aAktiviert")){
                        p.closeInventory();
                        p.sendMessage(SkyWars.PREFIX + "§cDu hast dieses Kit schon ausgewählt!");
                        p.playSound(p.getLocation(), Sound.GLASS, 10, 10);
                    } else if(e.getCurrentItem().getItemMeta().getLore().contains("§cDeaktiviert")) {
                        new Kits().updateKit(p.getUniqueId().toString(), "Standard");
                        new ItemManager().Items(p);
                        p.closeInventory();
                        p.sendMessage(SkyWars.PREFIX + "§7Du hast das Kit §e§lStandard §7ausgewählt");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                        new ScoreboardBuilder().updatescoreboard(p);
                    }
                }
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §e§lEnderman")){
                if(e.getCurrentItem().getType() == Material.ENDER_PEARL){
                    if(e.getCurrentItem().getItemMeta().getLore().contains("§aAktiviert")){
                        p.closeInventory();
                        p.sendMessage(SkyWars.PREFIX + "§cDu hast dieses Kit schon ausgewählt!");
                        p.playSound(p.getLocation(), Sound.GLASS, 10, 10);
                    } else if(e.getCurrentItem().getItemMeta().getLore().contains("§cDeaktiviert")) {
                        new Kits().updateKit(p.getUniqueId().toString(), "Enderman");
                        new ItemManager().Items(p);
                        p.closeInventory();
                        p.sendMessage(SkyWars.PREFIX + "§7Du hast das Kit §e§lEnderman §7ausgewählt");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                        new ScoreboardBuilder().updatescoreboard(p);
                    } else if(e.getCurrentItem().getItemMeta().getLore().contains("§e8.000 Coins")) {
                        if(new Coins().getCoins(p.getUniqueId().toString()) >= 8000) {
                            p.closeInventory();
                            new KitSystem().buyKit(p.getUniqueId().toString(), "Enderman");
                            new Coins().removeCoins(p.getUniqueId().toString(), 8000);
                            new Kits().updateKit(p.getUniqueId().toString(), "Enderman");
                            new ItemManager().Items(p);
                            p.sendMessage(SkyWars.PREFIX + "§7Du hast das Kit §e§lEnderman §7gekauft");
                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                            new ScoreboardBuilder().updatescoreboard(p);
                        } else {
                            p.closeInventory();
                            p.sendMessage(SkyWars.PREFIX + "§cDu hast nicht genügend Coins!");
                            p.playSound(p.getLocation(), Sound.NOTE_BASS, 10, 10);
                        }
                    }
                }
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §e§lEnterhaken")){
                if(e.getCurrentItem().getType() == Material.FISHING_ROD){
                    if(e.getCurrentItem().getItemMeta().getLore().contains("§aAktiviert")){
                        p.closeInventory();
                        p.sendMessage(SkyWars.PREFIX + "§cDu hast dieses Kit schon ausgewählt!");
                        p.playSound(p.getLocation(), Sound.GLASS, 10, 10);
                    } else if(e.getCurrentItem().getItemMeta().getLore().contains("§cDeaktiviert")) {
                        new Kits().updateKit(p.getUniqueId().toString(), "Enterhaken");
                        new ItemManager().Items(p);
                        p.closeInventory();
                        p.sendMessage(SkyWars.PREFIX + "§7Du hast das Kit §e§lEnterhaken §7ausgewählt");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                        new ScoreboardBuilder().updatescoreboard(p);
                    } else if(e.getCurrentItem().getItemMeta().getLore().contains("§e5.000 Coins")) {
                        if(new Coins().getCoins(p.getUniqueId().toString()) >= 5000) {
                            p.closeInventory();
                            new KitSystem().buyKit(p.getUniqueId().toString(), "Enterhaken");
                            new Coins().removeCoins(p.getUniqueId().toString(), 5000);
                            new Kits().updateKit(p.getUniqueId().toString(), "Enterhaken");
                            new ItemManager().Items(p);
                            p.sendMessage(SkyWars.PREFIX + "§7Du hast das Kit §e§lEnterhaken §7gekauft");
                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                            new ScoreboardBuilder().updatescoreboard(p);
                        } else {
                            p.closeInventory();
                            p.sendMessage(SkyWars.PREFIX + "§cDu hast nicht genügend Coins!");
                            p.playSound(p.getLocation(), Sound.NOTE_BASS, 10, 10);
                        }
                    }
                }
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §e§lBaumeister")){
                if(e.getCurrentItem().getType() == Material.BRICK){
                    if(e.getCurrentItem().getItemMeta().getLore().contains("§aAktiviert")){
                        p.closeInventory();
                        p.sendMessage(SkyWars.PREFIX + "§cDu hast dieses Kit schon ausgewählt!");
                        p.playSound(p.getLocation(), Sound.GLASS, 10, 10);
                    } else if(e.getCurrentItem().getItemMeta().getLore().contains("§cDeaktiviert")) {
                        new Kits().updateKit(p.getUniqueId().toString(), "Baumeister");
                        new ItemManager().Items(p);
                        p.closeInventory();
                        p.sendMessage(SkyWars.PREFIX + "§7Du hast das Kit §e§lBaumeister §7ausgewählt");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                        new ScoreboardBuilder().updatescoreboard(p);
                    } else if(e.getCurrentItem().getItemMeta().getLore().contains("§e2.500 Coins")) {
                        if(new Coins().getCoins(p.getUniqueId().toString()) >= 2500) {
                            p.closeInventory();
                            new KitSystem().buyKit(p.getUniqueId().toString(), "Baumeister");
                            new Coins().removeCoins(p.getUniqueId().toString(), 2500);
                            new Kits().updateKit(p.getUniqueId().toString(), "Baumeister");
                            new ItemManager().Items(p);
                            p.sendMessage(SkyWars.PREFIX + "§7Du hast das Kit §e§lBaumeister §7gekauft");
                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 10);
                            new ScoreboardBuilder().updatescoreboard(p);
                        } else {
                            p.closeInventory();
                            p.sendMessage(SkyWars.PREFIX + "§cDu hast nicht genügend Coins!");
                            p.playSound(p.getLocation(), Sound.NOTE_BASS, 10, 10);
                        }
                    }
                }
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(e.getCurrentItem().getItemMeta().getDisplayName())){
                if(e.getCurrentItem().getType() == Material.SKULL_ITEM){
                    SkullMeta skullMeta = (SkullMeta) e.getCurrentItem().getItemMeta();
                    Player t = Bukkit.getPlayer(skullMeta.getOwner());
                    p.teleport(t);
                    p.sendMessage(SkyWars.PREFIX + "§7Du hast dich zu §e§l" + t.getName() + "§7 teleportiert");
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 10);
                }
            }
        } catch (Exception e1){
        }
    }
}
