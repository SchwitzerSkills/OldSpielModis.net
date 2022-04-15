package de.oldspielmodies.commands;

import de.oldspielmodies.data.Data;
import de.oldspielmodies.lobby.Lobbysystem;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("build")) {
            if((cs instanceof Player)){
                Player p = (Player) cs;
                if(p.hasPermission("oldspielmodis.build")){
                    if (args.length == 0) {
                        if(!Lobbysystem.build.contains(p)){
                            Lobbysystem.build.add(p);
                            p.sendMessage(Lobbysystem.PREFIX + "You have activated the Build mode");
                            p.setGameMode(GameMode.CREATIVE);
                            p.getInventory().clear();
                        } else {
                            Lobbysystem.build.remove(p);
                            p.sendMessage(Lobbysystem.PREFIX + "You have deactivated the Build mode");
                            p.setGameMode(GameMode.SURVIVAL);
                            p.getInventory().clear();
                            new Data().onPlayerdefaultJoinSettings(p);
                        }
                    } else if(args.length == 1){
                        Player t = Bukkit.getPlayer(args[0]);
                        if(!Lobbysystem.build.contains(t)){
                            Lobbysystem.build.add(t);
                            p.sendMessage(Lobbysystem.PREFIX + "You have activated the player " + t.getName() + " the BuildMode");
                            t.sendMessage(Lobbysystem.PREFIX + "You have been activated the BuildMode");
                            t.setGameMode(GameMode.CREATIVE);
                            t.getInventory().clear();
                        } else {
                            Lobbysystem.build.remove(t);
                            p.sendMessage(Lobbysystem.PREFIX + "You have activated the player " + t.getName() + " the BuildMode");
                            t.sendMessage(Lobbysystem.PREFIX + "You have been deactivated the BuildMode");
                            t.setGameMode(GameMode.SURVIVAL);
                            t.getInventory().clear();
                            new Data().onPlayerdefaultJoinSettings(p);
                        }
                    }
                } else {
                    p.sendMessage(Lobbysystem.NO_PERMS);
                }
            }
        }
        return false;
    }
}
