package de.oldspielmodis.bungee.commands;

import de.oldspielmodis.bungee.Bungee;
import de.oldspielmodis.bungee.mysql.Ban;
import de.oldspielmodis.bungee.mysql.BanReason;
import de.oldspielmodis.bungee.utils.BanType;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.IOfflineCloudPlayer;
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BanCommand extends Command {
    public BanCommand() {
        super("ban");
    }

    int id;

    @Override
    public void execute(CommandSender cs, String[] args) {
        if ((cs instanceof ProxiedPlayer)) {
            if (!cs.hasPermission("oldspielmodis.ban")) {
                cs.sendMessage(Bungee.NO_PERMS);
            } else {
                BanReason banReason = new BanReason();
                if(args.length >= 4){
                    try {
                        int id = Integer.parseInt(args[0]);
                        String reason = "";
                        String time = args[2];
                        String duration = args[1];
                        for (int i = 3; i < args.length; i++) {
                            reason = reason + args[i] + " ";
                        }
                        if (!banReason.existsID(id)) {
                            if(time.contains("mo")) {
                                cs.sendMessage(Bungee.PREFIX + "You have added the BanReason " + reason + "for " + duration + " month(s).");
                                Bungee.getInstance().setBanType(BanType.MONTH);
                            } else if(time.contains("d")){
                                cs.sendMessage(Bungee.PREFIX + "You have added the BanReason " + reason + "for " + duration + " day(s).");
                                Bungee.getInstance().setBanType(BanType.DAY);
                            } else if(time.contains("h")){
                                cs.sendMessage(Bungee.PREFIX + "You have added the BanReason " + reason + "for " + duration + " hour(s).");
                                Bungee.getInstance().setBanType(BanType.HOUR);
                            } else if(time.contains("mi")){
                                cs.sendMessage(Bungee.PREFIX + "You have added the BanReason " + reason + "for " + duration + " minute(s).");
                                Bungee.getInstance().setBanType(BanType.MINUTE);
                            }
                            banReason.addReason(id, reason, duration, Bungee.getInstance().getBanType());
                        } else {
                            cs.sendMessage(Bungee.PREFIX + "This ID already exists.");
                            if(time.contains("mo")){
                                cs.sendMessage(Bungee.PREFIX + "You have updated the BanReason. The new BanReason is " + reason + "for " + duration + " month(s).");
                                Bungee.getInstance().setBanType(BanType.MONTH);
                            } else if(time.contains("d")){
                                cs.sendMessage(Bungee.PREFIX + "You have updated the BanReason. The new BanReason is " + reason + "for " + duration + " day(s).");
                                Bungee.getInstance().setBanType(BanType.DAY);
                            } else if(time.contains("h")){
                                cs.sendMessage(Bungee.PREFIX + "You have updated the BanReason. The new BanReason is " + reason + "for " + duration + " hour(s).");
                                Bungee.getInstance().setBanType(BanType.HOUR);
                            } else if(time.contains("mi")){
                                cs.sendMessage(Bungee.PREFIX + "You have updated the BanReason. The new BanReason is " + reason + "for " + duration + " minute(s).");
                                Bungee.getInstance().setBanType(BanType.MINUTE);
                            }
                            banReason.updateReason(id, reason, duration, Bungee.getInstance().getBanType());
                        }
                    } catch (NumberFormatException e) {
                        cs.sendMessage(Bungee.PREFIX + "§cThe ID must be a number!");
                    }
                } else if (args.length == 1 && args[0].equalsIgnoreCase("reasons")) {
                    List<String> list = banReason.getReasons();

                    if(banReason.exists()) {
                        for (String reasons : list) {
                            cs.sendMessage(Bungee.PREFIX + "§8» §e" + reasons + "§8(§e" + banReason.getDurations(reasons) + " " + banReason.getTypes(reasons) + "§8) §8┃ §e" + banReason.getIDS(reasons));
                        }
                    } else {
                        cs.sendMessage(Bungee.PREFIX + "§cThere are no ban reasons!");
                    }
                } else if(args.length == 2 && !args[0].equalsIgnoreCase("remove")){
                    ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);
                    try {
                        id = Integer.parseInt(args[1]);
                        Ban ban = new Ban();
                        if (target != null) {
                            if (!ban.isBanned(target.getUniqueId().toString())) {
                                if (!banReason.existsID(id)) {
                                    cs.sendMessage(Bungee.PREFIX + "§cThis ID does not exist!");
                                } else {
                                    if(banReason.getType(id, BanType.MONTH)) {
                                        Date date = new Date();
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                        int dur = Integer.parseInt(banReason.getDuration(id));
                                        date.setDate(date.getDate() + 30 * dur);
                                        ban.addBan(target.getUniqueId().toString(), banReason.getReason(id), simpleDateFormat.format(date), banReason.getDuration(id), banReason.getType(id));
                                    } else if(banReason.getType(id, BanType.DAY)) {
                                        Date date = new Date();
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                        int dur = Integer.parseInt(banReason.getDuration(id));
                                        date.setDate(date.getDate() + dur);
                                        ban.addBan(target.getUniqueId().toString(), banReason.getReason(id), simpleDateFormat.format(date), banReason.getDuration(id), banReason.getType(id));
                                    } else if(banReason.getType(id, BanType.HOUR)) {
                                        Date date = new Date();
                                        Date time = new Date();
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                                        SimpleDateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
                                        int dur = Integer.parseInt(banReason.getDuration(id));
                                        date.setHours(date.getHours() + dur);
                                        ban.addBan(target.getUniqueId().toString(), banReason.getReason(id), simpleDateFormat.format(date), timeFormat.format(time), banReason.getType(id));
                                    } else if(banReason.getType(id, BanType.MINUTE)) {
                                        Date date = new Date();
                                        Date time = new Date();
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                                        SimpleDateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
                                        int dur = Integer.parseInt(banReason.getDuration(id));
                                        date.setMinutes(date.getMinutes() + dur);
                                        ban.addBan(target.getUniqueId().toString(), banReason.getReason(id), simpleDateFormat.format(date), timeFormat.format(time), banReason.getType(id));
                                    }
                                        cs.sendMessage(Bungee.PREFIX + "§7You have banned the player " + target.getName() + " for the reason " + banReason.getReason(id) + "for " + banReason.getDuration(id) + " days.");
                                        target.disconnect("§8§m---------------\n§a\n§8§m §8» §eOldSpielModis.de\n§a\n§7Your banned from Server!\nreason: §e" + banReason.getReason(id) + "\n§7time: §e" + ban.getDurationFromBan(target.getUniqueId().toString()) + "\n§a\n§8§m---------------");
                                    }
                            } else {
                                cs.sendMessage(Bungee.PREFIX + "§cThe player is already banned!");
                            }
                        } else {
                            ICommunicationPromise<IOfflineCloudPlayer> promise = CloudAPI.getInstance().getCloudPlayerManager().getOfflineCloudPlayer(args[0]);
                            IOfflineCloudPlayer cloudPlayer = promise.getBlockingOrNull();
                            if (cloudPlayer != null) {
                                if (!ban.isBanned(cloudPlayer.getUniqueId().toString())) {
                                    if (!banReason.existsID(id)) {
                                        cs.sendMessage(Bungee.PREFIX + "§cThis ID does not exist!");
                                    } else {
                                        Date date = new Date();
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                        int dur = Integer.parseInt(banReason.getDuration(id));
                                        date.setDate(date.getDate() + dur);
                                        ban.addBan(cloudPlayer.getUniqueId().toString(), banReason.getReason(id), simpleDateFormat.format(date), banReason.getDuration(id), banReason.getType(id));
                                        cs.sendMessage(Bungee.PREFIX + "§7You have banned the player " + args[0] + " for the reason " + banReason.getReason(id) + "for " + banReason.getDuration(id) + banReason.getType(id) + ".");
                                    }
                                } else {
                                    cs.sendMessage(Bungee.PREFIX + "§cThe player is already banned!");
                                }
                            } else {
                                cs.sendMessage(Bungee.PREFIX + "§cThe player has never been on the server!");
                            }
                        }
                    } catch (NumberFormatException e){
                        cs.sendMessage(Bungee.PREFIX + "§cThe ID must be a number!");
                    }
                } else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
                    try {
                        id = Integer.parseInt(args[1]);
                        if (!banReason.existsID(id)) {
                            cs.sendMessage(Bungee.PREFIX + "§cThis ID does not exist!");
                        } else {
                            cs.sendMessage(Bungee.PREFIX + "§7You have removed the ID " + id + " with the ban reason " + banReason.getReason(id) + ".");
                            banReason.removeReason(id);
                        }
                    } catch (NumberFormatException e){
                        cs.sendMessage(Bungee.PREFIX + "§cThe ID must be a number!");
                    }
                } else if(args.length == 0) {
                    cs.sendMessage(Bungee.PREFIX + "§7Please use §e/ban §8(§ePlayer§8) §8(§eID§8)\n§e/ban reasons\n/ban §8(§eID§8) §8(§eDuration§8) §8(§emi§8, §eh§8, §ed§8, §emo§8) §8(§eReason§8)");
                }
            }
        }
    }
}
