package de.oldspielmodies.commands;

import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.manager.LocationManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.persistence.Lob;

public class SetWarpsCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player))return true;

        final Player player = (Player)sender;

        if(player.hasPermission("oldspielmodis.admin")){

            if(args.length == 0){
                player.sendMessage(Lobbysystem.PREFIX + "Pleace use §e/warp §8(§eSpawn§8)");
                return true;
            }

            if(args[0].equalsIgnoreCase("spawn")){
                new LocationManager().saveLocation(player, "spawn");
                player.sendMessage(Lobbysystem.PREFIX + "§eSpawn§7 has been created!");
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 2, 2);
            }else  player.sendMessage("Pleace use §e/warp §8(§eSpawn§8)");


        }else player.sendMessage(Lobbysystem.NO_PERMS);


        return false;
    }
}
