package net.komputerking.tracker.trackers;

import net.komputerking.tracker.api.Damage;
import net.komputerking.tracker.events.PlayerDamageEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Tracker for damage that doesn't require extra info. (e.g: Void damage)
 */
public class GeneralTracker implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onDamage(PlayerDamageEvent event) {
        switch (event.getCause().getCause()) {
            case VOID:
                event.setTrackerDamage(new GeneralDamage(event.getPlayer(), event.getDamage(), "Void", "fell into the void"));
                break;
            case SUFFOCATION:
                event.setTrackerDamage(new GeneralDamage(event.getPlayer(), event.getDamage(), "Suffocation", "suffocated"));
                break;
            case DROWNING:
                event.setTrackerDamage(new GeneralDamage(event.getPlayer(), event.getDamage(), "Water", "drowned"));
                break;
            case STARVATION:
                event.setTrackerDamage(new GeneralDamage(event.getPlayer(), event.getDamage(), "Food", "starved to death"));
                break;
            case FIRE_TICK:
                event.setTrackerDamage(new GeneralDamage(event.getPlayer(), event.getDamage(), "Fire", "burned to death"));
                break;
            case LAVA:
                event.setTrackerDamage(new GeneralDamage(event.getPlayer(), event.getDamage(), "Lava", "burned to death"));
                break;
            case LIGHTNING:
                event.setTrackerDamage(new GeneralDamage(event.getPlayer(), event.getDamage(), "Lightning", "was struck by lightning"));
                break;
            case POISON:
                event.setTrackerDamage(new GeneralDamage(event.getPlayer(), event.getDamage(), "Poison", "was poisoned"));
                break;
            case WITHER:
                event.setTrackerDamage(new GeneralDamage(event.getPlayer(), event.getDamage(), "Wither", "withered away"));
                break;
        }
    }

    public class GeneralDamage implements Damage {

        private Player damaged;
        private double damage;
        private String description;
        private String message;

        GeneralDamage(Player damaged, double damage, String description, String message) {
            this.damaged = damaged;
            this.damage = damage;
            this.description = description;
            this.message = message;
        }

        @Override
        public String getDescription() {
            return description;
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
            return damaged.getDisplayName() + ChatColor.GRAY + " " + message;
        }

    }

}
