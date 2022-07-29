package de.schwitzerskills.spigot.skywars.utils;

import de.schwitzerskills.spigot.skywars.SkyWars;
import de.schwitzerskills.spigot.skywars.mysql.Kits;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemManager {

    public void Items(Player p){
        p.getInventory().setItem(0, new ItemBuilder(Material.CHEST).setName("§8➤ §e§lWähle dein Kit §8[§7Rechtsklick§8]").toItemStack());
        p.getInventory().setItem(8, new ItemBuilder(Material.FIREBALL).setName("§8➤ §e§lSpiel verlassen §8[§7Rechtsklick§8]").toItemStack());
        if(new Kits().whichKit(p.getUniqueId().toString(), "Standard")) {
            p.getInventory().setItem(7, new ItemBuilder(Material.IRON_PICKAXE).setName("§8➤ §7Dein Kit: §e§lStandard").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        } else if(new Kits().whichKit(p.getUniqueId().toString(), "Enderman")){
            p.getInventory().setItem(7, new ItemBuilder(Material.ENDER_PEARL).setName("§8➤ §7Dein Kit: §e§lEnderman").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        } else if(new Kits().whichKit(p.getUniqueId().toString(), "Enterhaken")){
            p.getInventory().setItem(7, new ItemBuilder(Material.FISHING_ROD).setName("§8➤ §7Dein Kit: §e§lEnterhaken").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        } else if(new Kits().whichKit(p.getUniqueId().toString(), "Baumeister")){
            p.getInventory().setItem(7, new ItemBuilder(Material.BRICK).setName("§8➤ §7Dein Kit: §e§lBaumeister").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        }
    }

    public void SpectatorItems(Player p){
        SkyWars.getInstance().spec.add(p);
        SkyWars.getInstance().nonspec.remove(p);

        Title.sendTitle(p, 20, 30, 20, "§cDu bist gestorben", "");
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.setFoodLevel(20);
        p.setLevel(0);
        p.setExp(0);
        p.setAllowFlight(true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), () -> {
            p.getInventory().setItem(8, new ItemBuilder(Material.FIREBALL).setName("§8➤ §e§lSpiel verlassen §8[§7Rechtsklick§8]").toItemStack());
            p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setName("§8➤ §e§lSpieler beobachten §8[§7Rechtsklick§8]").toItemStack());
        }, 0);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 255));
        Bukkit.getOnlinePlayers().forEach(all -> {
            all.hidePlayer(p);
        });
    }
}
