package de.schwitzerskills.spigot.skywars.utils;

import de.schwitzerskills.spigot.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ChestManager implements Listener {

    public HashMap<Location, Inventory> chest = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if((e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                (e.getClickedBlock().getType() == Material.CHEST)) {
            e.setCancelled(true);
            if (SkyWars.getInstance().nonspec.contains(p)) {
                if (chest.containsKey(e.getClickedBlock().getLocation())) {
                    p.openInventory(chest.get(e.getClickedBlock().getLocation()));
                } else {
                    Random rnd = new Random();
                    int n = 4;
                    n = rnd.nextInt(11);
                    Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST);
                    ArrayList<ItemStack> items = new ArrayList<ItemStack>();

                    Random randomitems = new Random();
                    int rnditems = randomitems.nextInt(30);
                    if (rnditems == 0) {
                        rnditems = randomitems.nextInt(30);
                    }

                    Random randomblöcke = new Random();
                    int rndblöcke = randomitems.nextInt(60);
                    if (rndblöcke == 0) {
                        rndblöcke = randomitems.nextInt(60);
                    }

                    // ---- Schwerte ----
                    items.add(new ItemStack(Material.WOOD_SWORD));
                    items.add(new ItemStack(Material.WOOD_SWORD));
                    items.add(new ItemStack(Material.WOOD_SWORD));
                    items.add(new ItemStack(Material.WOOD_SWORD));
                    items.add(new ItemStack(Material.WOOD_SWORD));
                    items.add(new ItemStack(Material.WOOD_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    ;
                    items.add(new ItemStack(Material.IRON_SWORD));
                    items.add(new ItemStack(Material.IRON_SWORD));
                    items.add(new ItemStack(Material.IRON_SWORD));
                    items.add(new ItemStack(Material.IRON_SWORD));
                    items.add(new ItemStack(Material.IRON_SWORD));
                    items.add(new ItemStack(Material.DIAMOND_SWORD));
                    items.add(new ItemStack(Material.DIAMOND_SWORD));
                    items.add(new ItemStack(Material.DIAMOND_SWORD));
                    // ---- Äxte ----
                    items.add(new ItemStack(Material.WOOD_AXE));
                    ;
                    items.add(new ItemStack(Material.WOOD_AXE));
                    items.add(new ItemStack(Material.STONE_AXE));
                    items.add(new ItemStack(Material.IRON_AXE));
                    items.add(new ItemStack(Material.DIAMOND_AXE));
                    items.add(new ItemStack(Material.WOOD_AXE));
                    items.add(new ItemStack(Material.WOOD_AXE));
                    items.add(new ItemStack(Material.STONE_AXE));
                    items.add(new ItemStack(Material.IRON_AXE));
                    items.add(new ItemStack(Material.IRON_AXE));
                    items.add(new ItemStack(Material.DIAMOND_AXE));
                    // ---- Essen ----
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.COOKED_BEEF, rnditems));
                    items.add(new ItemStack(Material.COOKED_BEEF, rnditems));
                    items.add(new ItemStack(Material.COOKED_BEEF, rnditems));
                    items.add(new ItemStack(Material.COOKED_BEEF, rnditems));
                    items.add(new ItemStack(Material.COOKED_BEEF, rnditems));
                    items.add(new ItemStack(Material.GOLDEN_APPLE, rnditems));
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.COOKED_BEEF, rnditems));
                    items.add(new ItemStack(Material.COOKED_BEEF, rnditems));
                    items.add(new ItemStack(Material.COOKED_CHICKEN, rnditems));
                    items.add(new ItemStack(Material.COOKED_CHICKEN, rnditems));
                    items.add(new ItemStack(Material.COOKED_CHICKEN, rnditems));
                    items.add(new ItemStack(Material.COOKED_CHICKEN, rnditems));
                    items.add(new ItemStack(Material.COOKED_CHICKEN, rnditems));
                    items.add(new ItemStack(Material.GOLDEN_APPLE, 1));
                    // ---- Materialien ----
                    items.add(new ItemStack(Material.STICK, 3));
                    items.add(new ItemStack(Material.STICK, 1));
                    items.add(new ItemStack(Material.IRON_INGOT, rnditems));
                    items.add(new ItemStack(Material.DIAMOND, 4));
                    items.add(new ItemStack(Material.STICK, 1));
                    items.add(new ItemStack(Material.STICK, 1));
                    items.add(new ItemStack(Material.IRON_INGOT, rnditems));
                    items.add(new ItemStack(Material.DIAMOND, rnditems));
                    // ---- Rüstung ----
                    items.add(new ItemStack(Material.LEATHER_BOOTS, 1));
                    items.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.LEATHER_HELMET, 1));
                    items.add(new ItemStack(Material.LEATHER_HELMET, 1));
                    items.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));
                    items.add(new ItemStack(Material.LEATHER_BOOTS, 1));
                    items.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.LEATHER_HELMET, 1));
                    items.add(new ItemStack(Material.LEATHER_HELMET, 1));
                    items.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));
                    items.add(new ItemStack(Material.DIAMOND_HELMET, 1));
                    items.add(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                    items.add(new ItemStack(Material.DIAMOND_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_HELMET, 1));
                    items.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.IRON_LEGGINGS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_HELMET, 1));
                    items.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.IRON_LEGGINGS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    // ---- Extras ----
                    items.add(new ItemStack(Material.LAVA_BUCKET, 1));
                    items.add(new ItemStack(Material.LAVA_BUCKET, 1));
                    items.add(new ItemStack(Material.LAVA_BUCKET, 1));
                    items.add(new ItemStack(Material.FLINT_AND_STEEL, 1));
                    items.add(new ItemStack(Material.FLINT_AND_STEEL, 1));
                    items.add(new ItemStack(Material.BOW, 1));
                    items.add(new ItemStack(Material.BOW, 1));
                    items.add(new ItemStack(Material.BOW, 1));
                    items.add(new ItemStack(Material.BOW, 1));
                    items.add(new ItemStack(Material.BOW, 1));
                    items.add(new ItemStack(Material.ARROW, 10));
                    items.add(new ItemStack(Material.ARROW, 15));
                    items.add(new ItemStack(Material.ARROW, 1));
                    items.add(new ItemStack(Material.ARROW, 5));
                    // ---- Blöcke ----
                    items.add(new ItemStack(Material.GRASS, rndblöcke));
                    items.add(new ItemStack(Material.GRASS, rndblöcke));
                    items.add(new ItemStack(Material.GRASS, rndblöcke));
                    items.add(new ItemStack(Material.GRASS, rndblöcke));
                    items.add(new ItemStack(Material.GRASS, rndblöcke));
                    items.add(new ItemStack(Material.GRASS, rndblöcke));
                    items.add(new ItemStack(Material.GRASS, rndblöcke));
                    items.add(new ItemStack(Material.GRASS, rndblöcke));
                    items.add(new ItemStack(Material.STONE, rndblöcke));
                    items.add(new ItemStack(Material.STONE, rndblöcke));
                    items.add(new ItemStack(Material.STONE, rndblöcke));
                    items.add(new ItemStack(Material.STONE, rndblöcke));
                    items.add(new ItemStack(Material.STONE, rndblöcke));
                    items.add(new ItemStack(Material.STONE, rndblöcke));
                    items.add(new ItemStack(Material.COBBLESTONE, rndblöcke));
                    items.add(new ItemStack(Material.COBBLESTONE, rndblöcke));
                    items.add(new ItemStack(Material.COBBLESTONE, rndblöcke));
                    items.add(new ItemStack(Material.COBBLESTONE, rndblöcke));
                    items.add(new ItemStack(Material.COBBLESTONE, rndblöcke));
                    items.add(new ItemStack(Material.COBBLESTONE, rndblöcke));
                    items.add(new ItemStack(Material.COBBLESTONE, rndblöcke));
                    items.add(new ItemStack(Material.COBBLESTONE, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    // ---- Schwerte ----
                    items.add(new ItemStack(Material.WOOD_SWORD));
                    items.add(new ItemStack(Material.WOOD_SWORD));
                    items.add(new ItemStack(Material.WOOD_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    ;
                    items.add(new ItemStack(Material.IRON_SWORD));
                    items.add(new ItemStack(Material.DIAMOND_SWORD));
                    // ---- Äxte ----
                    items.add(new ItemStack(Material.WOOD_AXE));
                    ;
                    items.add(new ItemStack(Material.WOOD_AXE));
                    items.add(new ItemStack(Material.STONE_AXE));
                    items.add(new ItemStack(Material.IRON_AXE));
                    items.add(new ItemStack(Material.DIAMOND_AXE));
                    items.add(new ItemStack(Material.WOOD_AXE));
                    items.add(new ItemStack(Material.WOOD_AXE));
                    items.add(new ItemStack(Material.STONE_AXE));
                    items.add(new ItemStack(Material.IRON_AXE));
                    items.add(new ItemStack(Material.IRON_AXE));
                    items.add(new ItemStack(Material.DIAMOND_AXE));
                    // ---- Essen ----
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.COOKED_BEEF, rnditems));
                    items.add(new ItemStack(Material.GOLDEN_APPLE, rnditems));
                    items.add(new ItemStack(Material.APPLE, rnditems));
                    items.add(new ItemStack(Material.COOKED_BEEF, rnditems));
                    items.add(new ItemStack(Material.COOKED_CHICKEN, rnditems));
                    items.add(new ItemStack(Material.GOLDEN_APPLE, 1));
                    // ---- Materialien ----
                    items.add(new ItemStack(Material.STICK, 3));
                    items.add(new ItemStack(Material.STICK, 1));
                    items.add(new ItemStack(Material.IRON_INGOT, rnditems));
                    items.add(new ItemStack(Material.DIAMOND, 4));
                    items.add(new ItemStack(Material.STICK, 1));
                    items.add(new ItemStack(Material.STICK, 1));
                    items.add(new ItemStack(Material.IRON_INGOT, rnditems));
                    items.add(new ItemStack(Material.DIAMOND, rnditems));
                    // ---- Rüstung ----
                    items.add(new ItemStack(Material.LEATHER_BOOTS, 1));
                    items.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.LEATHER_HELMET, 1));
                    items.add(new ItemStack(Material.LEATHER_HELMET, 1));
                    items.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));
                    items.add(new ItemStack(Material.LEATHER_BOOTS, 1));
                    items.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.LEATHER_HELMET, 1));
                    items.add(new ItemStack(Material.LEATHER_HELMET, 1));
                    items.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));
                    items.add(new ItemStack(Material.DIAMOND_HELMET, 1));
                    items.add(new ItemStack(Material.DIAMOND_HELMET, 1));
                    items.add(new ItemStack(Material.DIAMOND_HELMET, 1));
                    items.add(new ItemStack(Material.DIAMOND_HELMET, 1));
                    items.add(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                    items.add(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                    items.add(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                    items.add(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                    items.add(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.DIAMOND_BOOTS, 1));
                    items.add(new ItemStack(Material.DIAMOND_BOOTS, 1));
                    items.add(new ItemStack(Material.DIAMOND_BOOTS, 1));
                    items.add(new ItemStack(Material.DIAMOND_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_HELMET, 1));
                    items.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.IRON_HELMET, 1));
                    items.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.IRON_HELMET, 1));
                    items.add(new ItemStack(Material.IRON_HELMET, 1));
                    items.add(new ItemStack(Material.IRON_LEGGINGS, 1));
                    items.add(new ItemStack(Material.IRON_LEGGINGS, 1));
                    items.add(new ItemStack(Material.IRON_LEGGINGS, 1));
                    items.add(new ItemStack(Material.IRON_LEGGINGS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_HELMET, 1));
                    items.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
                    items.add(new ItemStack(Material.IRON_LEGGINGS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    items.add(new ItemStack(Material.IRON_BOOTS, 1));
                    // ---- Extras ----
                    items.add(new ItemStack(Material.LAVA_BUCKET, 1));
                    items.add(new ItemStack(Material.LAVA_BUCKET, 1));
                    items.add(new ItemStack(Material.LAVA_BUCKET, 1));
                    items.add(new ItemStack(Material.FLINT_AND_STEEL, 1));
                    items.add(new ItemStack(Material.FLINT_AND_STEEL, 1));
                    items.add(new ItemStack(Material.BOW, 1));
                    items.add(new ItemStack(Material.BOW, 1));
                    items.add(new ItemStack(Material.BOW, 1));
                    items.add(new ItemStack(Material.BOW, 1));
                    items.add(new ItemStack(Material.BOW, 1));
                    items.add(new ItemStack(Material.ARROW, 10));
                    items.add(new ItemStack(Material.ARROW, 15));
                    items.add(new ItemStack(Material.ARROW, 1));
                    items.add(new ItemStack(Material.ARROW, 5));
                    // ---- Blöcke ----
                    items.add(new ItemStack(Material.GRASS, rndblöcke));
                    items.add(new ItemStack(Material.GRASS, rndblöcke));
                    items.add(new ItemStack(Material.GRASS, rndblöcke));
                    items.add(new ItemStack(Material.STONE, rndblöcke));
                    items.add(new ItemStack(Material.STONE, rndblöcke));
                    items.add(new ItemStack(Material.COBBLESTONE, rndblöcke));
                    items.add(new ItemStack(Material.COBBLESTONE, rndblöcke));
                    items.add(new ItemStack(Material.COBBLESTONE, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.BRICK, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.TNT, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    items.add(new ItemStack(Material.WOOL, rndblöcke));
                    if (inv.getSize() != 0) {
                        while (n != 0) {
                            n--;
                            Random rnd2 = new Random();
                            Random rnd3 = new Random();
                            int n3 = rnd3.nextInt(27);
                            int n2 = rnd2.nextInt(items.size());
                            inv.setItem(n3, items.get(n2));
                        }
                    } else {
                        while (n != 0) {
                            n--;
                            Random rnd2 = new Random();
                            Random rnd3 = new Random();
                            int n3 = rnd3.nextInt(27);
                            int n2 = rnd2.nextInt(items.size());
                            inv.setItem(n3, items.get(n2));
                        }
                    }
                    chest.put(e.getClickedBlock().getLocation(), inv);
                    p.openInventory(inv);
                    return;
                }
                return;
            }
        }
    }
}
