package net.komputerking.tracker.events;

import net.komputerking.tracker.api.Damage;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageEvent extends Event implements Cancellable {

    private static HandlerList handlerList = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    private EntityDamageEvent cause;
    private Damage trackerDamage;
    private boolean cancelled = false;

    public PlayerDamageEvent(EntityDamageEvent cause) {
        this.cause = cause;
    }

    public EntityDamageEvent getCause() {
        return cause;
    }

    public Player getPlayer() {
        return (Player) cause.getEntity();
    }

    public double getDamage() {
        return cause.getDamage();
    }

    public Damage getTrackerDamage() {
        return trackerDamage;
    }

    public void setTrackerDamage(Damage trackerDamage) {
        this.trackerDamage = trackerDamage;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }
}
