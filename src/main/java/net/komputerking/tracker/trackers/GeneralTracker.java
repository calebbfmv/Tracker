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
 * Tracker for damage that doesn't require extra info. (e.g: Void damage)
 */
public class GeneralTracker implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onDamage(PlayerDamageEvent event) {
        if (event.getCause().getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
            event.setTrackerDamage(new VoidDamage(event.getPlayer(), event.getDamage()));
        }
    }

    public class VoidDamage implements Damage {

        private Player damaged;
        private double damage;

        VoidDamage(Player damaged, double damage) {
            this.damaged = damaged;
            this.damage = damage;
        }

        @Override
        public String getDescription() {
            return "Void";
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
            return damaged.getDisplayName() + ChatColor.GRAY + " fell into the void";
        }

    }

}
