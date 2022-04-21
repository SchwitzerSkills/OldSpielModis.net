package de.oldspielmodis.proxy;

import de.oldspielmodis.commands.InfoCommand;
import de.oldspielmodis.commands.KickSystem;
import de.oldspielmodis.commands.PingCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.protocol.packet.Kick;

public class Proxy extends Plugin {

    public static final String PREFIX = "§8× §eProxy §8┃ §7",
                                NO_PERMS = PREFIX + "§cYou don't have the permission to use this command!";

    @Override
    public void onEnable() {

        init();
        ProxyServer.getInstance().getConsole().sendMessage(PREFIX + "This plugin is Enabled!");

    }

    @Override
    public void onDisable() {


    }

    private void init(){

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new KickSystem());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PingCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new InfoCommand());

    }

}
