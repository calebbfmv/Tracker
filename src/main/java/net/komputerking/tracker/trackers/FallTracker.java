package net.komputerking.tracker.trackers;

import net.komputerking.tracker.api.Damage;
import net.komputerking.tracker.events.PlayerDamageEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Tracker to detect when a player takes fall damage.
 */
public class FallTracker implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onDamage(PlayerDamageEvent event) {
        if (event.getCause().getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            event.setTrackerDamage(new FallDamage(event.getPlayer(), event.getDamage(), (int) Math.floor(event.getPlayer().getFallDistance())));
        }
    }

    public class FallDamage implements Damage {

        private Player damaged;
        private double damage;
        private int distance;

        FallDamage(Player damaged, double damage, int distance) {
            this.damaged = damaged;
            this.damage = damage;
            this.distance = distance;
        }

        @Override
        public String getDescription() {
            return "Fall (" + distance + " block" + (distance == 1 ? "" : "s") + ")";
        }

        @Override
        public Player getDamaged() {
            return damaged;
        }

        @Override
        public double getDamage() {
            return damage;
        }

        @Override
        public String getDeathMessage() {
            return damaged.getDisplayName() + ChatColor.GRAY + " fell from " + distance + " block" + (distance == 1 ? "" : "s");
        }

    }

}
