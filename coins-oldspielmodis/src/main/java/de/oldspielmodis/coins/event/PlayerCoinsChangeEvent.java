package de.oldspielmodis.coins.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerCoinsChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private String uuid;
    private int coins;
    public PlayerCoinsChangeEvent(String uuid, int coins){
        this.uuid = uuid;
        this.coins = coins;
    }

    public String getUuid() {
        return this.uuid;
    }

    public int getCoins() {
        return this.coins;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

}
