package de.oldspielmodis.commands;

import de.oldspielmodis.proxy.Proxy;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCommand extends Command {
    public PingCommand() {
       super("ping");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {


        final ProxiedPlayer player = (ProxiedPlayer)commandSender;

        if(strings.length == 0){

            player.sendMessage(Proxy.PREFIX + "Your Ping: §e" + player.getPing() + "§8.");

        }else player.sendMessage(Proxy.PREFIX + "Pleace use §e/ping§8.");

    }
}
