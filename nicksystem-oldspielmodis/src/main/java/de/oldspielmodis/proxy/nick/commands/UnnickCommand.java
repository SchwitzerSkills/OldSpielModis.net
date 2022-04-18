package de.oldspielmodis.proxy.nick.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class UnnickCommand extends Command {
    public UnnickCommand() {
        super("unnick");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if ((cs instanceof ProxiedPlayer)) {

        }
    }
}
