package net.komputerking.tracker.trackers;

import net.komputerking.tracker.api.Damage;
import net.komputerking.tracker.events.PlayerDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Tracker to detect PVP damage.
 */
public class PVPTracker implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onDamage(PlayerDamageEvent event) {
        if (event.getCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event.getCause();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                Player damaged = event.getPlayer();

                event.setTrackerDamage(new PVPDamage(damaged, damager, event.getDamage()));
            }
        }
    }

    public class PVPDamage implements Damage {

        private Player damaged;
        private Player damager;
        private double damage;

        PVPDamage(Player damaged, Player damager, double damage) {
            this.damaged = damaged;
            this.damager = damager;
            this.damage = damage;
        }

        @Override
        public String getDescription() {
            return "PVP";
        }

        @Override
        public Player getDamaged() {
            return damaged;
        }

        public Player getDamager() {
            return damager;
        }

        @Override
        public double getDamage() {
            return damage;
        }
    }

}
