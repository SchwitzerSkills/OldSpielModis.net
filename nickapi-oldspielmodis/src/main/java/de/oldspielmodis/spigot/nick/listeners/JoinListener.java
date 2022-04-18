package de.oldspielmodis.spigot.nick.listeners;

import de.oldspielmodis.spigot.nick.Nickapi;
import de.oldspielmodis.spigot.nick.mysql.Nick;
import de.oldspielmodis.spigot.nick.utils.NickUtils;
import de.oldspielmodis.spigot.nick.utils.Nickname;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.group.IPermissionGroup;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import eu.thesimplecloud.module.permission.player.PlayerPermissionGroupInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Random;

public class JoinListener implements Listener {

    int augenZahl;

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        Nick nick = new Nick();
        Nickname nickname = new Nickname();
        NickUtils nickUtils = new NickUtils();
         if(nick.isNicked(p.getUniqueId().toString())) {
             if(nickname.NicknamesExists()) {
                 Random wuerfel = new Random();
                 for (int i = 0; i < 10; i++) {
                     augenZahl = 1 + wuerfel.nextInt(nickname.getIDS());
                     nickUtils.nick(p, nickname.RandomNickname(String.valueOf(augenZahl)), "§6Premium §8❘ §6");
                     nick.updateNicked(p.getUniqueId().toString(),nickname.RandomNickname(String.valueOf(augenZahl)));
                     nickUtils.skin(p, p.getName());
                     nick.updateID(p.getUniqueId().toString(), String.valueOf(augenZahl));
                 }
                 nickname.removeNickname(String.valueOf(augenZahl));
                 IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(p.getUniqueId());
                 assert permissionPlayer != null;
                 if(permissionPlayer.hasPermissionGroup("Admin")) {
                     permissionPlayer.removePermissionGroup("Admin");
                     permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("NickedAdmin", -1));
                     permissionPlayer.update();
                 } else if(permissionPlayer.hasPermissionGroup("Builder")) {
                     permissionPlayer.removePermissionGroup("Builder");
                     permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("NickedBuilder", -1));
                     permissionPlayer.update();
                 } else if(permissionPlayer.hasPermissionGroup("Content")) {
                     permissionPlayer.removePermissionGroup("Content");
                     permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("NickedContent", -1));
                     permissionPlayer.update();
                 } else if(permissionPlayer.hasPermissionGroup("Developer")) {
                     permissionPlayer.removePermissionGroup("Developer");
                     permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("NickedDeveloper", -1));
                     permissionPlayer.update();
                 } else if(permissionPlayer.hasPermissionGroup("Moderator")) {
                     permissionPlayer.removePermissionGroup("Moderator");
                     permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("NickedModerator", -1));
                     permissionPlayer.update();
                 }
                 p.sendMessage(Nickapi.PREFIX + "You are nicked as " + p.getName());
             } else {
                 p.sendMessage(Nickapi.PREFIX + "§cNo nicknames available!");
             }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        Nickname nickname = new Nickname();
        Nick nick = new Nick();
        if(nick.isNicked(p.getUniqueId().toString())) {
            if (nickname.NicknamesExists()) {
                nickname.addNickname(nick.getID(p.getUniqueId().toString()), p.getName());
                nick.updateNicked(p.getUniqueId().toString(), "-");
                nick.updateID(p.getUniqueId().toString(), "-");
                IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(p.getUniqueId());
                if (permissionPlayer.hasPermissionGroup("NickedAdmin")) {
                    permissionPlayer.removePermissionGroup("NickedAdmin");
                    permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Admin", -1));
                    permissionPlayer.update();
                } else if (permissionPlayer.hasPermissionGroup("NickedBuilder")) {
                    permissionPlayer.removePermissionGroup("NickedBuilder");
                    permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Builder", -1));
                    permissionPlayer.update();
                } else if (permissionPlayer.hasPermissionGroup("NickedContent")) {
                    permissionPlayer.removePermissionGroup("NickedContent");
                    permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Content", -1));
                    permissionPlayer.update();
                } else if (permissionPlayer.hasPermissionGroup("NickedDeveloper")) {
                    permissionPlayer.removePermissionGroup("NickedDeveloper");
                    permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Developer", -1));
                    permissionPlayer.update();
                } else if (permissionPlayer.hasPermissionGroup("NickedModerator")) {
                    permissionPlayer.removePermissionGroup("NickedModerator");
                    permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Moderator", -1));
                    permissionPlayer.update();
                }
            }
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent e){
        Player p = e.getPlayer();
        Nickname nickname = new Nickname();
        Nick nick = new Nick();
        if(nick.isNicked(p.getUniqueId().toString())) {
            if (nickname.NicknamesExists()) {
                nickname.addNickname(nick.getID(p.getUniqueId().toString()), p.getName());
                nick.updateNicked(p.getUniqueId().toString(), "-");
                nick.updateID(p.getUniqueId().toString(), "-");
                IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(p.getUniqueId());
                if (permissionPlayer.hasPermissionGroup("NickedAdmin")) {
                    permissionPlayer.removePermissionGroup("NickedAdmin");
                    permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Admin", -1));
                    permissionPlayer.update();
                } else if (permissionPlayer.hasPermissionGroup("NickedBuilder")) {
                    permissionPlayer.removePermissionGroup("NickedBuilder");
                    permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Builder", -1));
                    permissionPlayer.update();
                } else if (permissionPlayer.hasPermissionGroup("NickedContent")) {
                    permissionPlayer.removePermissionGroup("NickedContent");
                    permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Content", -1));
                    permissionPlayer.update();
                } else if (permissionPlayer.hasPermissionGroup("NickedDeveloper")) {
                    permissionPlayer.removePermissionGroup("NickedDeveloper");
                    permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Developer", -1));
                    permissionPlayer.update();
                } else if (permissionPlayer.hasPermissionGroup("NickedModerator")) {
                    permissionPlayer.removePermissionGroup("NickedModerator");
                    permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Moderator", -1));
                    permissionPlayer.update();
                }
            }
        }
    }
}
