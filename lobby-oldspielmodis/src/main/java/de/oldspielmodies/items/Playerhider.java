package de.oldspielmodies.items;

import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.HashMap;

public class Playerhider implements Listener {

    public static ArrayList<Player> hide = new ArrayList<>();
    public static ArrayList<Player> members = new ArrayList<>();
    public static ArrayList<Player> show = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §ePlayer hider §8┃ §7Rightclick")){
                if (e.getItem().getType() == Material.BLAZE_ROD) {
                    Inventory inv = Bukkit.createInventory(p, InventoryType.BREWING, "§8» §ePlayer hider");

                    inv.setItem(0, new ItemManager(Material.INK_SACK, 1, (byte) 10).setDisplayName("§8» §aShow all players").toItemStack());
                    inv.setItem(1, new ItemManager(Material.INK_SACK, 1, (byte) 5).setDisplayName("§8» §5Show team members only").toItemStack());
                    inv.setItem(2, new ItemManager(Material.INK_SACK, 1, (byte) 1).setDisplayName("§8» §cShow no players").toItemStack());

                    if(!members.contains(p)) {
                        if (show.contains(p)) {
                            inv.setItem(3, new ItemManager(Material.BLAZE_ROD).addEnchant(Enchantment.THORNS, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setDisplayName("§8» §aShow all players").toItemStack());
                        }
                        if(hide.contains(p)){
                            inv.setItem(3, new ItemManager(Material.BLAZE_ROD).addEnchant(Enchantment.THORNS, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setDisplayName("§8» §cShow no players").toItemStack());
                        }
                    } else {
                        inv.setItem(3, new ItemManager(Material.BLAZE_ROD).addEnchant(Enchantment.THORNS, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setDisplayName("§8» §5Show team members only").toItemStack());
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
            if (e.getCurrentItem().getType() == Material.INK_SACK) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aShow all players")) {
                    if (show.contains(p)) {
                        p.closeInventory();
                        p.sendMessage(Lobbysystem.PREFIX + "§cYou are already shown all players!");
                    } else {
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        p.closeInventory();
                        members.remove(p);
                        hide.remove(p);
                        show.add(p);
                        Bukkit.getOnlinePlayers().forEach(p::showPlayer);
                        p.sendMessage(Lobbysystem.PREFIX + "All players are now displayed.");
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cShow no players")) {
                    if (hide.contains(p)) {
                        p.closeInventory();
                        p.sendMessage(Lobbysystem.PREFIX + "§cYou already have all the players hidden!");
                    } else {
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        p.closeInventory();
                        members.remove(p);
                        show.remove(p);
                        hide.add(p);
                        Bukkit.getOnlinePlayers().forEach(p::hidePlayer);
                        p.sendMessage(Lobbysystem.PREFIX + "You will now have all the players hidden.");
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §5Show team members only")) {
                    if (members.contains(p)) {
                        p.closeInventory();
                        p.sendMessage(Lobbysystem.PREFIX + "§cYou are already shown only all team members!");
                    } else {
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        p.closeInventory();
                        hide.remove(p);
                        show.remove(p);
                        members.add(p);
                        Bukkit.getOnlinePlayers().forEach(all -> {
                            if (!all.hasPermission("oldspielmodis.hider.members")) {
                                p.hidePlayer(all);
                            }
                        });
                        p.sendMessage(Lobbysystem.PREFIX + "You are now only shown team members.");
                    }
                }
            }
        } catch (Exception e1){
        }
    }
}
