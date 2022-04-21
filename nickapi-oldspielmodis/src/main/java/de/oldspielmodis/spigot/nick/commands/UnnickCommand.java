package de.oldspielmodis.spigot.nick.commands;

import de.oldspielmodis.spigot.nick.Nickapi;
import de.oldspielmodis.spigot.nick.mysql.Nick;
import de.oldspielmodis.spigot.nick.utils.NickUtils;
import de.oldspielmodis.spigot.nick.mysql.Nickname;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import eu.thesimplecloud.module.permission.player.PlayerPermissionGroupInfo;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnnickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("unnick")) {
            if ((cs instanceof Player)) {
                if (cs.hasPermission("oldspielmodis.unnick")) {
                    if (args.length == 0) {
                        Nick nick = new Nick();
                        Nickname nickname = new Nickname();
                        NickUtils nickUtils = new NickUtils();
                        if (nick.isNicked(((Player) cs).getUniqueId().toString())) {
                            if (nick.isRegistered(((Player) cs).getUniqueId().toString())) {
                                if (!nick.getNick(((Player) cs).getUniqueId().toString(), "-")) {
                                    nickname.addNickname(nick.getID(((Player) cs).getUniqueId().toString()), cs.getName());
                                    nick.updateNicked(((Player) cs).getUniqueId().toString(), "-");
                                    nick.updateID(((Player) cs).getUniqueId().toString(), "-");
                                    cs.sendMessage(Nickapi.PREFIX + "You have unicked.");
                                    IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(((Player) cs).getUniqueId());
                                    if (permissionPlayer.hasPermissionGroup("NickedAdmin")) {
                                        permissionPlayer.removePermissionGroup("NickedAdmin");
                                        permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Admin", -1));
                                        nickUtils.nick(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()), "§4Admin §8❘ §4");
                                        nickUtils.skin(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()));
                                        permissionPlayer.update();
                                    } else if (permissionPlayer.hasPermissionGroup("NickedBuilder")) {
                                        permissionPlayer.removePermissionGroup("NickedBuilder");
                                        permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Builder", -1));
                                        nickUtils.nick(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()), "§2Builder §8❘ §2");
                                        nickUtils.skin(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()));
                                        permissionPlayer.update();
                                    } else if (permissionPlayer.hasPermissionGroup("NickedContent")) {
                                        permissionPlayer.removePermissionGroup("NickedContent");
                                        permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Content", -1));
                                        nickUtils.nick(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()), "§eContent §8❘ §e");
                                        nickUtils.skin(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()));
                                        permissionPlayer.update();
                                    } else if (permissionPlayer.hasPermissionGroup("NickedDeveloper")) {
                                        permissionPlayer.removePermissionGroup("NickedDeveloper");
                                        permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Developer", -1));
                                        nickUtils.nick(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()), "§bDev §8❘ §b");
                                        nickUtils.skin(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()));
                                        permissionPlayer.update();
                                    } else if (permissionPlayer.hasPermissionGroup("NickedModerator")) {
                                        permissionPlayer.removePermissionGroup("NickedModerator");
                                        permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Moderator", -1));
                                        nickUtils.nick(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()), "§cMod §8❘ §c");
                                        nickUtils.skin(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()));
                                        permissionPlayer.update();
                                    } else if (permissionPlayer.hasPermissionGroup("NickedSupporter")) {
                                        permissionPlayer.removePermissionGroup("NickedSupporter");
                                        permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Supporter", -1));
                                        nickUtils.nick(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()), "§9Sup §8❘ §9");
                                        nickUtils.skin(((Player) cs).getPlayer(), nick.getName(((Player) cs).getUniqueId().toString()));
                                        permissionPlayer.update();
                                    }

                                }
                            } else {
                                cs.sendMessage(Nickapi.PREFIX + "§cYou are not nicked!");
                            }
                        }
                    }
                } else {
                    cs.sendMessage(Nickapi.NO_PERMS);
                }
            }
        }
        return false;
    }
}
