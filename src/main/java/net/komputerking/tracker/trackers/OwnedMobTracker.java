package net.komputerking.tracker.trackers;

import net.komputerking.tracker.events.PlayerDamageEvent;
import net.komputerking.tracker.util.MobDamage;
import net.komputerking.tracker.util.PlayerDamage;
import net.komputerking.tracker.util.Util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Tracker to detect when a tamed mob attacks a player.
 */
public class OwnedMobTracker implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onDamage(PlayerDamageEvent event) {
        if (event.getCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event.getCause();
            if (e.getDamager() instanceof Tameable) {
                Tameable t = (Tameable) e.getDamager();
                if (t.getOwner() != null) {
                    if (t.getOwner() instanceof Player) {
                        Player damaged = event.getPlayer();
                        Player damager = (Player) t.getOwner();
                        Entity entity = e.getDamager();

                        event.setTrackerDamage(new OwnedMobDamage(damaged, damager, entity, event.getDamage()));
                    }
                }
            }
        }
    }

    public class OwnedMobDamage implements PlayerDamage, MobDamage {

        private Player damaged;
        private Player damager;
        private Entity entity;
        private double damage;

        OwnedMobDamage(Player damaged, Player owner, Entity entity, double damage) {
            this.damaged = damaged;
            this.damager = owner;
            this.damage = damage;
            this.entity = entity;
        }

        @Override
        public String getDescription() {
            return Util.getEntityName(entity);
        }

        @Override
        public Player getDamaged() {
            return damaged;
        }

        @Override
        public Player getDamager() {
            return damager;
        }

        @Override
        public double getDamage() {
            return damage;
        }

        @Override
        public String getDeathMessage() {
            return damaged.getDisplayName() + ChatColor.GRAY + " got too close to " + ChatColor.GRAY + damager.getDisplayName() + Util.getPlural(damager.getDisplayName()) + " " + Util.getEntityName(entity);
        }

        @Override
        public Entity getMob() {
            return entity;
        }

		@Override
		public ItemStack getUsedItem() {
			return damager.getItemInHand();
		}
    }

}
