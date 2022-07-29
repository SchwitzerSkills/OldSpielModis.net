package de.schwitzerskills.spigot.skywars.listeners;

import de.schwitzerskills.spigot.coinsystem.mysql.Coins;
import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.mysql.KitSystem;
import de.schwitzerskills.spigot.skywars.mysql.Kits;
import de.schwitzerskills.spigot.skywars.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PlayerInteractListener implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        try{
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §e§lWähle dein Kit §8[§7Rechtsklick§8]")) {
                if (e.getItem().getType() == Material.CHEST) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        Inventory inv = Bukkit.createInventory(null, 9, "§8➤ §7Deine Coins: §e" + new Coins().getCoins(p.getUniqueId().toString()));

                        ArrayList<String> desc = new ArrayList<>();

                        if (!new Kits().whichKit(p.getUniqueId().toString(), "Standard")) {
                            desc.clear();
                            desc.add("§8§m              ");
                            desc.add("§7Starte mit:");
                            desc.add("§8- §7Eisenschwert");
                            desc.add("§8- §7Eisenspitzhacke");
                            desc.add("§8- §7Eisenaxt");
                            desc.add("§a");
                            desc.add("§8- §7Starte mit dem Standard Kit");
                            desc.add("§8§m              ");
                            desc.add("§cDeaktiviert");
                            inv.setItem(0, new ItemBuilder(Material.IRON_PICKAXE).setName("§8➤ §e§lStandard").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(desc).toItemStack());
                        } else {
                            desc.clear();
                            desc.add("§8§m              ");
                            desc.add("§7Starte mit:");
                            desc.add("§8- §7Eisenschwert");
                            desc.add("§8- §7Eisenspitzhacke");
                            desc.add("§8- §7Eisenaxt");
                            desc.add("§a");
                            desc.add("§8- §7Starte mit dem Standard Kit");
                            desc.add("§8§m              ");
                            desc.add("§aAktiviert");
                            inv.setItem(0, new ItemBuilder(Material.IRON_PICKAXE).setName("§8➤ §e§lStandard").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(desc).toItemStack());
                        }

                        if (!new Kits().whichKit(p.getUniqueId().toString(), "Enderman")) {
                            desc.clear();
                            desc.add("§8§m              ");
                            desc.add("§7Starte mit:");
                            desc.add("§8- §7Enterperle");
                            desc.add("§a");
                            desc.add("§8- §7Teleportiere dich direkt in die Mitte");
                            desc.add("§8§m              ");
                            if(!p.hasPermission("Kits.*")) {
                                if (!new KitSystem().hasKit(p.getUniqueId().toString(), "Enderman")) {
                                    desc.add("§e8.000 Coins");
                                } else {
                                    desc.add("§cDeaktiviert");
                                }
                            } else {
                                desc.add("§cDeaktiviert");
                            }
                            inv.setItem(1, new ItemBuilder(Material.ENDER_PEARL).setName("§8➤ §e§lEnderman").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(desc).toItemStack());
                        } else {
                            desc.clear();
                            desc.add("§8§m              ");
                            desc.add("§7Starte mit:");
                            desc.add("§8- §7Enterperle");
                            desc.add("§a");
                            desc.add("§8- §7Teleportiere dich direkt in die Mitte");
                            desc.add("§8§m              ");
                            desc.add("§aAktiviert");
                            inv.setItem(1, new ItemBuilder(Material.ENDER_PEARL).setName("§8➤ §e§lEnderman").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(desc).toItemStack());
                        }

                        if (!new Kits().whichKit(p.getUniqueId().toString(), "Enterhaken")) {
                            desc.clear();
                            desc.add("§8§m              ");
                            desc.add("§7Starte mit:");
                            desc.add("§8- §7Enterhaken");
                            desc.add("§a");
                            desc.add("§8- §7Ziehe dich zu anderen Spielern");
                            desc.add("§8§m              ");
                            if(!p.hasPermission("Kits.*")) {
                                if (!new KitSystem().hasKit(p.getUniqueId().toString(), "Enterhaken")) {
                                    desc.add("§e5.000 Coins");
                                } else {
                                    desc.add("§cDeaktiviert");
                                }
                            } else {
                                desc.add("§cDeaktiviert");
                            }
                            inv.setItem(2, new ItemBuilder(Material.FISHING_ROD).setName("§8➤ §e§lEnterhaken").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(desc).toItemStack());
                        } else {
                            desc.clear();
                            desc.add("§8§m              ");
                            desc.add("§7Starte mit:");
                            desc.add("§8- §7Enterhaken");
                            desc.add("§a");
                            desc.add("§8- §7Ziehe dich zu anderen Spielern");
                            desc.add("§8§m              ");
                            desc.add("§aAktiviert");
                            inv.setItem(2, new ItemBuilder(Material.FISHING_ROD).setName("§8➤ §e§lEnterhaken").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(desc).toItemStack());
                        }

                        if (!new Kits().whichKit(p.getUniqueId().toString(), "Baumeister")) {
                            desc.clear();
                            desc.add("§8§m              ");
                            desc.add("§7Starte mit:");
                            desc.add("§8- §764x Ziegelsteine");
                            desc.add("§a");
                            desc.add("§8- §7Baue dich direkt weg");
                            desc.add("§8§m              ");
                            if(!p.hasPermission("Kits.*")) {
                                if (!new KitSystem().hasKit(p.getUniqueId().toString(), "Baumeister")) {
                                    desc.add("§e2.500 Coins");
                                } else {
                                    desc.add("§cDeaktiviert");
                                }
                            } else {
                                desc.add("§cDeaktiviert");
                            }
                            inv.setItem(3, new ItemBuilder(Material.BRICK).setName("§8➤ §e§lBaumeister").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(desc).toItemStack());
                        } else {
                            desc.clear();
                            desc.add("§8§m              ");
                            desc.add("§7Starte mit:");
                            desc.add("§8- §764x Ziegelsteine");
                            desc.add("§a");
                            desc.add("§8- §7Baue dich direkt weg");
                            desc.add("§8§m              ");
                            desc.add("§aAktiviert");
                            inv.setItem(3, new ItemBuilder(Material.BRICK).setName("§8➤ §e§lBaumeister").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(desc).toItemStack());
                        }

                        p.openInventory(inv);
                    }
                }
            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §7Dein Kit: §e§lEnderman")){
                if(e.getItem().getType() == Material.ENDER_PEARL){
                    e.setCancelled(true);
                    new ItemManager().Items(p);
                }
            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §7Dein Kit: §e§lEnterhaken")){
                if(e.getItem().getType() == Material.FISHING_ROD){
                    e.setCancelled(true);
                }
            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §e§lSpiel verlassen §8[§7Rechtsklick§8]")){
                if(e.getItem().getType() == Material.FIREBALL){
                    if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                        p.kickPlayer(SkyWars.PREFIX + "§cDu hast das Spiel verlassen!");
                    }
                }
            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §e§lVerlassen §8[§7Rechtsklick§8]")) {
                if (e.getItem().getType() == Material.INK_SACK) {
                    if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                        p.getInventory().clear();
                        p.getInventory().setItem(8, new ItemBuilder(Material.FIREBALL).setName("§8➤ §e§lSpiel verlassen §8[§7Rechtsklick§8]").toItemStack());
                        p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setName("§8➤ §e§lSpieler beobachten §8[§7Rechtsklick§8]").toItemStack());
                        task.remove(p);
                        if (!task.contains(p)) {
                            p.getNearbyEntities(0, 0, 0).forEach(t -> {
                                p.showPlayer((Player) t);
                            });
                            p.getServer().getScheduler().cancelTask(count);
                        }
                    }
                }
            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §e§lSpieler beobachten §8[§7Rechtsklick§8]")){
                if(e.getItem().getType() == Material.COMPASS){
                    if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                        Inventory inv = Bukkit.createInventory(null, 9*5, "§8➤ §e§lSpieler beobachten");
                        if(SkyWars.getInstance().nonspec.size() >= 1) {
                            for (int i = 0; i < SkyWars.instance.nonspec.size(); i++) {
                                int finalI = i;
                                SkyWars.getInstance().nonspec.forEach(all -> {
                                    inv.setItem(finalI, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§8➤ §e§l" + all.getName()).setSkullOwner(all.getName()).toItemStack());
                                });
                            }
                            p.openInventory(inv);
                        } else {
                            p.sendMessage(SkyWars.PREFIX + "§cEs gibt keine Spieler mehr zu beobachten!");
                        }

                    }
                }
            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §e§lEnderperle")){
                if(e.getItem().getType() == Material.ENDER_PEARL){
                    if(Countdown.notlaunch) {
                        e.setCancelled(true);
                        p.getInventory().setItem(0, new ItemBuilder(Material.ENDER_PEARL).setName("§8➤ §e§lEnderperle").toItemStack());
                    }
                }
            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➤ §e§lEnterhaken")){
                if(e.getItem().getType() == Material.FISHING_ROD){
                    if(Countdown.notlaunch) {
                        e.setCancelled(true);
                    }
                }
            }
        } catch (Exception e1){
        }
    }

    public static int count;
    public static ArrayList<Player> task = new ArrayList<>();

    @EventHandler
    public void onInteractPlayer(PlayerInteractAtEntityEvent e){
        Player p = e.getPlayer();
        Player t = (Player) e.getRightClicked();
        try{
            if((e.getRightClicked() instanceof Player)) {
                if (SkyWars.getInstance().spec.contains(p)) {
                    Title.sendTitle(p, 20, 40, 20, "§cDrücke Farbstoff", "§7zum verlassen");
                    p.getInventory().clear();
                    task.add(p);
                    p.getInventory().setItem(4, new ItemBuilder(Material.INK_SACK, 1, (byte) 1).setName("§8➤ §e§lVerlassen §8[§7Rechtsklick§8]").toItemStack());
                    p.hidePlayer(t);
                    if(task.contains(p)) {
                        count = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.getInstance(), () -> {
                            p.teleport(t);
                        }, 1, 1);
                    }
                }
            }
        } catch (Exception e1){
        }
    }
}
