package de.oldspielmodies.items;

import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.manager.ItemManager;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.api.service.ICloudService;
import eu.thesimplecloud.api.servicegroup.ICloudServiceGroup;
import eu.thesimplecloud.api.servicegroup.grouptype.ICloudServerGroup;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Lobbyswitcher implements Listener {

    int i1;

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eLobby-switcher §8┃ §7Rightclick")) {
                if(e.getItem().getType() == Material.NETHER_STAR){
                    Inventory inv = Bukkit.createInventory(p, 9*4, "§8» §eLobby-switcher");

                    i1 = 1;
                    for(int i = 0; i < CloudAPI.getInstance().getCloudServiceGroupManager().getServiceGroupByName("Lobby").getRegisteredServiceCount(); i++){
                        ICloudService iCloudServerGroup = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-" + i1);
                        if(iCloudServerGroup.isOnline()) {
                            inv.setItem(i, new ItemManager(Material.NETHER_STAR).setDisplayName("§8» §eLobby-" + i1 + " §8(§aOnline§8)").toItemStack());
                        } else {
                            inv.setItem(i, new ItemManager(Material.NETHER_STAR).setDisplayName("§8» §eLobby-" + i1 + " §8(§cOffline§8)").toItemStack());
                        }
                        i1++;
                    }

                    p.openInventory(inv);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        int slot = e.getRawSlot();
        slot = slot+1;
        if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eLobby-" + slot + " §8(§aOnline§8)")){
            if(e.getCurrentItem().getType() == Material.NETHER_STAR){
                ICloudPlayer player = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(p.getName());
                ICloudService iCloudServerGroup = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-" + slot);
                player.connect(iCloudServerGroup);
            }
        } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eLobby-" + slot + " §8(§cOffline§8)")){
            p.closeInventory();
            p.sendMessage(Lobbysystem.PREFIX + "§cSorry, this lobby is offline!");
        }
    }
}
