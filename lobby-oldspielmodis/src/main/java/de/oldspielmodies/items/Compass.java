package de.oldspielmodies.items;

import de.oldspielmodies.manager.ItemManager;
import de.oldspielmodies.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Compass implements Listener {

    @EventHandler
    public void onCompass(final PlayerInteractEvent event){

        if(event.getItem() == null)return;
        if(event.getItem().getItemMeta() == null)return;
        if(event.getItem().getItemMeta().getDisplayName() == null)return;

        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eCompass §8┃ §7Rightclick")){

            if(event.getItem().getType() == Material.COMPASS){

                if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){

                                final Inventory inventory = Bukkit.createInventory(null, 9*5, "§8» §eCompass");

                                inventory.setItem(13, new ItemManager(Material.MAGMA_CREAM).setDisplayName("§8» §eSpawn").toItemStack());
                                inventory.setItem(30, new ItemManager(Material.GRASS).setDisplayName("§8» §eSkyWars").toItemStack());
                                inventory.setItem(32, new ItemManager(Material.BED).setDisplayName("§8» §eBedWars").toItemStack());

                                event.getPlayer().openInventory(inventory);
                }

            }
        }

    }
    @EventHandler
    public void onClick(final InventoryClickEvent event){

        final Player player = (Player)event.getWhoClicked();

        if(event.getCurrentItem() == null)return;
        if(event.getCurrentItem().getItemMeta() == null)return;
        if(event.getCurrentItem().getItemMeta().getDisplayName() == null)return;

        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eSpawn")){

            player.closeInventory();
            player.teleport(new LocationManager().getLocation("spawn"));
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 2, 2);

        }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eSkyWars")){

            player.closeInventory();
            player.teleport(new LocationManager().getLocation("skywars"));
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 2, 2);

        }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eBedWars")){

            player.closeInventory();
            player.teleport(new LocationManager().getLocation("bedwars"));
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 2, 2);

        }else return;


    }


}
