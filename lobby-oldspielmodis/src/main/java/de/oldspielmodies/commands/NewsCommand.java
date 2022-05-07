package de.oldspielmodies.commands;

import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.mysql.News;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("news")) {
            if(cs instanceof Player){
                Player p = (Player) cs;
                if (p.hasPermission("oldspielmodis.news")) {
                    News news = new News();
                    if(args.length >= 3){
                        int id = Integer.parseInt(args[0]);
                        String nachricht = "";
                        for(int i = 2; i < args.length; i++){
                            nachricht = nachricht + args[i] + " ";
                        }
                        Date date = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                        if(!news.isExistsNews(id)){
                            p.sendMessage(Lobbysystem.PREFIX + "You have created new news.");
                            news.addNews(id, nachricht, simpleDateFormat.format(date));
                        } else {
                            p.sendMessage(Lobbysystem.PREFIX + "You updated the old news because the ID already existed.");
                            p.sendMessage(Lobbysystem.PREFIX + "You have brought the news up to date.");
                            news.updateNews(id, nachricht, simpleDateFormat.format(date));
                        }
                    } else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
                        int id = Integer.parseInt(args[1]);
                        if (news.isExistsNewsID(id)) {
                            p.sendMessage(Lobbysystem.PREFIX + "You have removed the news of the ID " + id + ".");
                            news.removeNews(id);
                        } else {
                            p.sendMessage(Lobbysystem.PREFIX + "§cThere are no news!");
                        }
                    } else if (args.length == 0){
                        p.sendMessage(Lobbysystem.PREFIX + "Please use §e/news §8(§eID§8) §8(§eDate MM/dd/yyyy§8) §8(§eMessage§8)\n§e/news remove §8(§eID§8)\n§e/news list");
                    } else if(args.length == 1 && args[0].equalsIgnoreCase("list")){
                        List<String> list = news.getUpdates();

                        if(news.isExistsNews()) {
                            for (String updates : list) {
                                p.sendMessage(Lobbysystem.PREFIX + "§8» §7" + ChatColor.translateAlternateColorCodes('&', updates) + "§8(§e" + news.getUpdatesDatum() + "§8) §8┃ §e" + news.getUpdatesID(updates));
                            }
                        } else {
                            p.sendMessage(Lobbysystem.PREFIX + "§cThere are no news!");
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
