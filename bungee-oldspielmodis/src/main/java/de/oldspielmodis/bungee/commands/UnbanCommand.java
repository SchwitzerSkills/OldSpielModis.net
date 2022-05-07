package de.oldspielmodis.bungee.commands;

import de.oldspielmodis.bungee.Bungee;
import de.oldspielmodis.bungee.mysql.Ban;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.IOfflineCloudPlayer;
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class UnbanCommand extends Command {
    public UnbanCommand() {
        super("unban");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if ((cs instanceof ProxiedPlayer)) {
            if (cs.hasPermission("oldspielmodis.unban")) {
                if (args.length == 1) {
                    Ban ban = new Ban();
                        ICommunicationPromise<IOfflineCloudPlayer> promise = CloudAPI.getInstance().getCloudPlayerManager().getOfflineCloudPlayer(args[0]);
                        IOfflineCloudPlayer cloudPlayer = promise.getBlockingOrNull();
                        if(cloudPlayer != null) {
                            if (!ban.isBanned(cloudPlayer.getUniqueId().toString())) {
                                cs.sendMessage(Bungee.PREFIX + "§cThe player is not banned!");
                            } else {
                                ban.removeBan(cloudPlayer.getUniqueId().toString());
                                cs.sendMessage(Bungee.PREFIX + "You have unbanned the player " + cloudPlayer.getName() + ".");
                            }
                        } else {
                            cs.sendMessage(Bungee.PREFIX + "§cThe player has never been on the server!");
                        }
                } else {
                    cs.sendMessage(Bungee.PREFIX + "§7Please use §e/unban §8(§ePlayer§8)");
                }
            } else{
                cs.sendMessage(Bungee.NO_PERMS);
            }
        }
    }
}
