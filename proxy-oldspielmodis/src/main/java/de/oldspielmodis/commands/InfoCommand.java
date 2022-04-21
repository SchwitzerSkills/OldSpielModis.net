package de.oldspielmodis.commands;

import de.oldspielmodis.proxy.Proxy;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Command;

public class InfoCommand extends Command {
    public InfoCommand() {
        super("info");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {

            final ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if(player.hasPermission("oldspielmodis.admin")){

                if(args.length == 0){
                    player.sendMessage(Proxy.PREFIX + "Pleace use §e/info §8(§ePlayer§8)");
                    return;
                }else if(args.length == 1){
                    final ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

                   if(target != null){

                       player.sendMessage("§8§m--------§e§lInfo§8§m--------");
                       player.sendMessage("§4");
                       player.sendMessage("§8➥ §7Name §8┃ §e" + target.getName());
                       player.sendMessage("§8➥ §7Server §8┃ §e" + target.getServer().getInfo().getName());
                       player.sendMessage("§8➥ §7Ban §8┃ §cfalse");
                       player.sendMessage("§8➥ §7Mute §8┃ §cfalse");
                       player.sendMessage("§3");
                       player.sendMessage("§8§M--------------------§7");


                   }else player.sendMessage(Proxy.PREFIX + "This player is not online.");
                }else  player.sendMessage(Proxy.PREFIX + "Pleace use §e/info §8(§ePlayer§8)");
            }else player.sendMessage(Proxy.NO_PERMS);

    }
}
