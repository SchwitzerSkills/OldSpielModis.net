package de.oldspielmodis.coins.commands;

import de.oldspielmodis.coins.Coinssystem;
import de.oldspielmodis.coins.mysql.Coins;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("coins")){
            if (cs instanceof Player) {
                Player p = (Player) cs;
                Coins coins = new Coins();
                if(args.length == 0){
                    p.sendMessage(Coinssystem.PREFIX + "You have §e§l" + coins.getCoins(p.getUniqueId().toString()) + "§7 Coins.");
                } else if(args.length == 3 && args[0].equalsIgnoreCase("add")){
                    if(p.hasPermission("oldspielmodis.coins")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if(target != null) {
                            int amount = Integer.parseInt(args[2]);
                            if (amount <= 0) {
                                p.sendMessage(Coinssystem.PREFIX + "§cThe number of coins must not be 0 or below 0!");
                            } else {
                                coins.addCoins(target.getUniqueId().toString(), amount);
                                p.sendMessage(Coinssystem.PREFIX + "You have added " + amount + " coins to the player " + target.getName() + ".");
                                target.sendMessage(Coinssystem.PREFIX + "You have been added " + amount + " coins.");
                            }
                        } else {
                            p.sendMessage(Coinssystem.PREFIX + "§cThis player is not on the network!");
                        }
                    } else {
                        p.sendMessage(Coinssystem.NO_PERMS);
                    }
                } else if(args.length == 3 && args[0].equalsIgnoreCase("remove")){
                    if(p.hasPermission("oldspielmodis.coins")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if(target != null) {
                            int amount = Integer.parseInt(args[2]);
                            if (amount <= 0) {
                                p.sendMessage(Coinssystem.PREFIX + "§cThe number of coins must not be 0 or below 0!");
                            } else if (coins.getCoins(target.getUniqueId().toString()) <= 0) {
                                p.sendMessage(Coinssystem.PREFIX + "§cThis player " + target.getName() + " has 0 coins in the account!");
                            } else {
                                coins.removeCoins(target.getUniqueId().toString(), amount);
                                p.sendMessage(Coinssystem.PREFIX + "You have deducted " + amount + " coins from the player " + target.getName() + ".");
                                target.sendMessage(Coinssystem.PREFIX + "You have been deducted " + amount + " coins.");
                            }
                        } else {
                            p.sendMessage(Coinssystem.PREFIX + "§cThis player is not on the network!");
                        }
                    } else {
                        p.sendMessage(Coinssystem.NO_PERMS);
                    }
                } else if(args.length == 3 && args[0].equalsIgnoreCase("set")){
                    if(p.hasPermission("oldspielmodis.coins")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if(target != null) {
                            int amount = Integer.parseInt(args[2]);
                            if (amount <= 0) {
                                p.sendMessage(Coinssystem.PREFIX + "§cThe number of coins must not be 0 or below 0!");
                            } else {
                                coins.setCoins(target.getUniqueId().toString(), amount);
                                p.sendMessage(Coinssystem.PREFIX + "You have set the player " + target.getName() + "'s coins to " + amount + ".");
                                target.sendMessage(Coinssystem.PREFIX + "You have been set the coins to " + amount + ".");
                            }
                        } else {
                            p.sendMessage(Coinssystem.PREFIX + "§cThis player is not on the network!");
                        }
                    } else {
                        p.sendMessage(Coinssystem.NO_PERMS);
                    }
                } else {
                    p.sendMessage(Coinssystem.PREFIX + "§7Please use §e/coins\n§e/coins add §8(§eName§8) §8(§eAmount§8)\n§e/coins set §8(§eName§8) §8(§eAmount§8)\n§e/coins remove §8(§eName§8) §8(§eAmount§8)");
                }
            }
        }
        return false;
    }
}
