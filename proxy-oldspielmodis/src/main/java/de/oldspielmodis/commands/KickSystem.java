package de.oldspielmodis.commands;

import de.oldspielmodis.proxy.Proxy;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class KickSystem extends Command {
    public KickSystem() {
        super("kick");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        final ProxiedPlayer player = (ProxiedPlayer)commandSender;

        if( strings[0].equalsIgnoreCase("reasons")){
            player.sendMessage("§8§m---------------\n\n§8» §7Chatverhalten §8┃ §e1\n§8» §7Verwarnung §8┃ §e2\n§8» §7Melde TS³ §8┃ §e3\n§8» §7Bugusing §8┃ §e4\n\n§8§m---------------§7");
            return;
        }else if(strings.length == 0){
            player.sendMessage(Proxy.PREFIX + "Pleace use §e/kick §8(§eplayer§8) §8(§ereason§8)");
            return;
        }else  player.sendMessage(Proxy.PREFIX + "Pleace use §e/kick §8(§eplayer§8) §8(§ereason§8)");




        if(player.hasPermission("oldspielmodis.team")){

            final ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);

           if(target != null){

               if(strings[1].equalsIgnoreCase("1")){
                   player.sendMessage(Proxy.PREFIX + "You have kick the player " + target.getName() + " form this server. \nReason: §eUnangemessenes Chatverhalten");
                   target.disconnect("§8§m---------------\n\n §8» §eOldSpielModis.de \n\n §7Your kicked from Server!\n §7reason: §eUnangemessenes Chatverhalten\n\n§8§m---------------§7");
               }else  if(strings[1].equalsIgnoreCase("2")){
                   player.sendMessage(Proxy.PREFIX + "You have kick the player " + target.getName() + " form this server. \nReason: §eVerwarnung ");
                   target.disconnect("§8§m---------------\n\n §8» §eOldSpielModis.de \n\n §7Your kicked from Server!\n §7reason: §eVerwarnung\n\n§8§m---------------§7");
               }else if(strings[1].equalsIgnoreCase("3")){
                   player.sendMessage(Proxy.PREFIX + "You have kick the player " + target.getName() + " form this server. \nReason: §eAuf dem Ts Melden");
                   target.disconnect("§8§m---------------\n\n §8» §eOldSpielModis.de \n\n §7Your kicked from Server!\n §7reason: §eBitte auf dem ts melden §e\n\n§8§m---------------§7");
               }else if(strings[1].equalsIgnoreCase("4")){
                   player.sendMessage(Proxy.PREFIX + "You have kick the player " + target.getName() + " form this server. \nReason: §eBugusing");
                   target.disconnect("§8§m---------------\n\n §8» §eOldSpielModis.de \n\n §7Your kicked from Server!\n §7reason: §eBugusing\n\n§8§m---------------§7");
               }else player.sendMessage(Proxy.PREFIX + "Pleace use §e/kick §8(§eplayer§8) §8(§ereason§8)");

           }else player.sendMessage(Proxy.PREFIX + "This player is not online.");


        }else player.sendMessage(Proxy.NO_PERMS);

    }
}
