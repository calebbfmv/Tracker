package net.komputerking.tracker.trackers;

import net.komputerking.tracker.events.PlayerDamageEvent;
import net.komputerking.tracker.util.MobDamage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Tracker to detect when an entity damages a player.
 */
public class EntityTracker implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onDamage(PlayerDamageEvent event) {
        if (event.getCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event.getCause();
            if (!(e.getDamager() instanceof Player)) {
                event.setTrackerDamage(new EntityDamage(event.getPlayer(), e.getDamager(), event.getDamage()));
            }
        }
    }

    public class EntityDamage implements MobDamage {

        private Player damaged;
        private Entity entity;
        private double damage;

        EntityDamage(Player damaged, Entity entity, double damage) {
            this.damaged = damaged;
            this.damage = damage;
            this.entity = entity;
        }

        @Override
        public String getDescription() {
            return entity.getType().getName();
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
            return damaged.getDisplayName() + ChatColor.GRAY + " was killed by " + ChatColor.GRAY + entity.getType().getName();
        }

        @Override
        public Entity getMob() {
            return entity;
        }
    }

}
