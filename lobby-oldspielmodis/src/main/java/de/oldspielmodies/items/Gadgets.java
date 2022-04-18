package de.oldspielmodies.items;

import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.mysql.Gadget;
import de.oldspielmodies.manager.ItemManager;
import de.oldspielmodis.coins.mysql.Coins;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class Gadgets implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eGadgets §8┃ §7Rightclick")) {
                if(e.getItem().getType() == Material.CHEST){
                    Inventory inv = Bukkit.createInventory(p, 9*4, "§8» §eGadgets");

                    Gadget gadget = new Gadget();
                    if(!gadget.hasGadget(p.getUniqueId().toString(), "redshoes")) {
                        inv.setItem(10, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.RED).setDisplayName("§8» §cRed shoes").setLore("§e1000 Coins").toItemStack());
                    } else {
                        if(!gadget.hasGadgetSelected(p.getUniqueId().toString(), "redshoes")) {
                            inv.setItem(10, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.RED).setDisplayName("§8» §cRed shoes").setLore("§cNot selected").toItemStack());
                        } else {
                            inv.setItem(10, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.RED).setDisplayName("§8» §cRed shoes").setLore("§aSelected").toItemStack());
                        }
                    }
                    if(!gadget.hasGadget(p.getUniqueId().toString(), "blueshoes")) {
                        inv.setItem(12, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.BLUE).setDisplayName("§8» §1Blue shoes").setLore("§e2000 Coins").toItemStack());
                    } else {
                        if(!gadget.hasGadgetSelected(p.getUniqueId().toString(), "blueshoes")) {
                            inv.setItem(12, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.BLUE).setDisplayName("§8» §1Blue shoes").setLore("§cNot selected").toItemStack());
                        } else {
                            inv.setItem(12, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.BLUE).setDisplayName("§8» §1Blue shoes").setLore("§aSelected").toItemStack());
                        }
                    }

                    if(!gadget.hasGadget(p.getUniqueId().toString(), "yellowshoes")) {
                        inv.setItem(14, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.YELLOW).setDisplayName("§8» §eYellow shoes").setLore("§e3000 Coins").toItemStack());
                    } else {
                        if(!gadget.hasGadgetSelected(p.getUniqueId().toString(), "yellowshoes")) {
                            inv.setItem(14, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.YELLOW).setDisplayName("§8» §eYellow shoes").setLore("§cNot selected").toItemStack());
                        } else {
                            inv.setItem(14, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.YELLOW).setDisplayName("§8» §eYellow shoes").setLore("§aSelected").toItemStack());
                        }
                    }

                    if(!gadget.hasGadget(p.getUniqueId().toString(), "greenshoes")) {
                        inv.setItem(16, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.GREEN).setDisplayName("§8» §aGreen shoes").setLore("§e4000 Coins").toItemStack());
                    } else {
                        if(!gadget.hasGadgetSelected(p.getUniqueId().toString(), "greenshoes")) {
                            inv.setItem(16, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.GREEN).setDisplayName("§8» §aGreen shoes").setLore("§cNot selected").toItemStack());
                        } else {
                            inv.setItem(16, new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.GREEN).setDisplayName("§8» §aGreen shoes").setLore("§aSelected").toItemStack());
                        }
                    }

                    inv.setItem(31, new ItemManager(Material.BARRIER).setDisplayName("§8» §cRemove gadget").toItemStack());
                    p.openInventory(inv);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        try {
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cRed shoes")) {
                if (e.getCurrentItem().getType() == Material.LEATHER_BOOTS) {
                    Gadget gadget = new Gadget();
                    if (e.getCurrentItem().getItemMeta().getLore().contains("§e1000 Coins")) {
                        Coins coins = new Coins();
                        if (coins.getCoins(p.getUniqueId().toString()) < 1000) {
                            p.closeInventory();
                            p.sendMessage(Lobbysystem.PREFIX + "§cYou don't have enough coins!");
                        } else if(coins.getCoins(p.getUniqueId().toString()) >= 1000) {
                            p.closeInventory();
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                            gadget.addGadget(p.getUniqueId().toString(), "redshoes", false);
                            coins.removeCoins(p.getUniqueId().toString(), 1000);
                            p.sendMessage(Lobbysystem.PREFIX + "You bought the Red shoes gadget.");
                        }
                    } else if (e.getCurrentItem().getItemMeta().getLore().contains("§cNot selected")) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        gadget.updateSelectedGadgetAll(p.getUniqueId().toString(), "false");
                        gadget.updateSelectedGadget(p.getUniqueId().toString(), "redshoes", "true");
                        p.sendMessage(Lobbysystem.PREFIX + "You have selected the Red shoes gadget.");
                        p.getInventory().setBoots(new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.RED).setDisplayName(e.getCurrentItem().getItemMeta().getDisplayName()).toItemStack());
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §1Blue shoes")) {
                if (e.getCurrentItem().getType() == Material.LEATHER_BOOTS) {
                    Gadget gadget = new Gadget();
                    if (e.getCurrentItem().getItemMeta().getLore().contains("§e2000 Coins")) {
                        Coins coins = new Coins();
                        if (coins.getCoins(p.getUniqueId().toString()) < 2000) {
                            p.closeInventory();
                            p.sendMessage(Lobbysystem.PREFIX + "§cYou don't have enough coins!");
                        } else {
                            p.closeInventory();
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                            gadget.addGadget(p.getUniqueId().toString(), "blueshoes", false);
                            coins.removeCoins(p.getUniqueId().toString(), 2000);
                            p.sendMessage(Lobbysystem.PREFIX + "You bought the Blue shoes gadget.");
                        }
                    } else if (e.getCurrentItem().getItemMeta().getLore().contains("§cNot selected")) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        gadget.updateSelectedGadgetAll(p.getUniqueId().toString(), "false");
                        gadget.updateSelectedGadget(p.getUniqueId().toString(), "blueshoes", "true");
                        p.sendMessage(Lobbysystem.PREFIX + "You have selected the Blue shoes gadget.");
                        p.getInventory().setBoots(new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.BLUE).setDisplayName(e.getCurrentItem().getItemMeta().getDisplayName()).toItemStack());
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eYellow shoes")) {
                if (e.getCurrentItem().getType() == Material.LEATHER_BOOTS) {
                    Gadget gadget = new Gadget();
                    if (e.getCurrentItem().getItemMeta().getLore().contains("§e3000 Coins")) {
                        Coins coins = new Coins();
                        if (coins.getCoins(p.getUniqueId().toString()) < 3000) {
                            p.closeInventory();
                            p.sendMessage(Lobbysystem.PREFIX + "§cYou don't have enough coins!");
                        } else {
                            p.closeInventory();
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                            gadget.addGadget(p.getUniqueId().toString(), "yellowshoes", false);
                            coins.removeCoins(p.getUniqueId().toString(), 3000);
                            p.sendMessage(Lobbysystem.PREFIX + "You bought the Yellow shoes gadget.");
                        }
                    } else if (e.getCurrentItem().getItemMeta().getLore().contains("§cNot selected")) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        gadget.updateSelectedGadgetAll(p.getUniqueId().toString(), "false");
                        gadget.updateSelectedGadget(p.getUniqueId().toString(), "yellowshoes", "true");
                        p.sendMessage(Lobbysystem.PREFIX + "You have selected the Yellow shoes gadget.");
                        p.getInventory().setBoots(new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.YELLOW).setDisplayName(e.getCurrentItem().getItemMeta().getDisplayName()).toItemStack());

                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aGreen shoes")) {
                if (e.getCurrentItem().getType() == Material.LEATHER_BOOTS) {
                    Gadget gadget = new Gadget();
                    if (e.getCurrentItem().getItemMeta().getLore().contains("§e4000 Coins")) {
                        Coins coins = new Coins();
                        if (coins.getCoins(p.getUniqueId().toString()) < 4000) {
                            p.closeInventory();
                            p.sendMessage(Lobbysystem.PREFIX + "§cYou don't have enough coins!");
                        } else {
                            p.closeInventory();
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                            gadget.addGadget(p.getUniqueId().toString(), "greenshoes", false);
                            coins.removeCoins(p.getUniqueId().toString(), 4000);
                            p.sendMessage(Lobbysystem.PREFIX + "You bought the Green shoes gadget.");
                        }
                    } else if (e.getCurrentItem().getItemMeta().getLore().contains("§cNot selected")) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        gadget.updateSelectedGadgetAll(p.getUniqueId().toString(), "false");
                        gadget.updateSelectedGadget(p.getUniqueId().toString(), "greenshoes", "true");
                        p.sendMessage(Lobbysystem.PREFIX + "You have selected the Green shoes gadget.");
                        p.getInventory().setBoots(new ItemManager(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.GREEN).setDisplayName(e.getCurrentItem().getItemMeta().getDisplayName()).toItemStack());
                    }
                }
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cRemove gadget")){
                if(e.getCurrentItem().getType() == Material.BARRIER){
                    Gadget gadget = new Gadget();
                    if (gadget.hasGadgetSelectedAll(p.getUniqueId().toString())) {
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                        gadget.updateSelectedGadgetAll(p.getUniqueId().toString(), "false");
                        p.sendMessage(Lobbysystem.PREFIX + "You have removed your gadgets.");
                        p.getInventory().setArmorContents(null);
                    } else {
                        p.closeInventory();
                        p.sendMessage(Lobbysystem.PREFIX + "§cNo gadgets are selected!");
                    }
                }
            }
        } catch (Exception e1){
        }
    }
}
