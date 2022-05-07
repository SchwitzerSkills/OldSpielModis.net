package de.oldspielmodis.bungee.commands;

import de.oldspielmodis.bungee.Bungee;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MuteCommand extends Command {
    public MuteCommand() {
        super("mute");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if ((cs instanceof ProxiedPlayer)) {
            if (!cs.hasPermission("oldspielmodis.mute")) {
                cs.sendMessage(Bungee.NO_PERMS);
            } else {
            }
        }
    }
}
