package de.oldspielmodis.nick.commands;

import de.oldspielmodis.nick.Nicksystem;
import de.oldspielmodis.nick.mysql.Nickname;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

public class NicknameCommand extends Command {
    public NicknameCommand() {
        super("nickname");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if ((cs instanceof ProxiedPlayer)) {
            if (cs.hasPermission("oldspielmodis.nickname")) {
                Nickname nickname = new Nickname();
                if (args.length == 3 && args[0].equalsIgnoreCase("add")) {
                    String id = args[2];
                    String nick = args[1];
                    if(!nickname.IDExist(id)) {
                        cs.sendMessage(Nicksystem.PREFIX + "You have added the nickname " + nick + ".");
                        nickname.addNickname(id, nick);
                    } else {
                        cs.sendMessage(Nicksystem.PREFIX + "You have updated the nickname " + nick + ".");
                        nickname.updateNickname(id, nick);
                    }
                } else if(args.length == 2 && args[0].equalsIgnoreCase("remove")){
                    String id = args[1];
                    if(!nickname.IDExist(id)) {
                        cs.sendMessage(Nicksystem.PREFIX + "§cThis ID does not exist!");
                    } else {
                        cs.sendMessage(Nicksystem.PREFIX + "You have removed the nickname " + nickname.getNickname(id) + ".");
                        nickname.removeNickname(id);
                    }
                } else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
                    if(!nickname.NicknamesExists()){
                        cs.sendMessage(Nicksystem.PREFIX + "§cThere are no nicknames!");
                    } else {
                        List<String> nicknames = nickname.getNicknames();
                        cs.sendMessage(Nicksystem.PREFIX + "§8§m---------§8[ §eNicknames §8]§8§m---------");
                        cs.sendMessage("");
                        for(String nicks : nicknames){
                            cs.sendMessage("§e" + nicks);
                        }
                        cs.sendMessage("");
                        cs.sendMessage("§8§m------------------------------------");
                    }
                } else {
                    cs.sendMessage(Nicksystem.PREFIX + "§7Please use §e/nickname add §8(§eNickname§8) §8(§eID§8)\n§e/nickname remove §8(§eID§8)\n§e/nickname list");
                }
            } else {
                cs.sendMessage(Nicksystem.NO_PERMS);
            }
        }
    }
}
