package de.oldspielmodis.coins.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class PlayerCoinsChangeEvent extends Event {
    private final int coins;

    private final UUID uuid;

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public PlayerCoinsChangeEvent(UUID uuid, int coins) {
        this.coins = coins;
        this.uuid = uuid;
    }

    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public int getCoins() {
        return coins;
    }

    public UUID getUuid() {
        return uuid;
    }
}
