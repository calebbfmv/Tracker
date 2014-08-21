package net.komputerking.tracker;

import net.komputerking.tracker.api.Damage;
import net.komputerking.tracker.events.PlayerDamageEvent;
import net.komputerking.tracker.trackers.EntityTracker;
import net.komputerking.tracker.trackers.OwnedMobTracker;
import net.komputerking.tracker.trackers.PVPTracker;
import net.komputerking.tracker.util.PlayerDamage;
import net.komputerking.tracker.util.UnknownDamage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;

public class Tracker extends JavaPlugin implements Listener {

    private static Tracker instance;
    private static Logger logger;
    private static RecordHandler recordHandler;

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();

        recordHandler = new RecordHandler();

        registerListener(this);
        registerListener(new PVPTracker());
        registerListener(new EntityTracker());
        registerListener(new OwnedMobTracker());
    }

    public static Logger logger() {
        return logger;
    }

    public static Tracker get() {
        return instance;
    }

    public static RecordHandler getRecordHandler() {
        return recordHandler;
    }

    public void registerListener(Listener l) {
        Bukkit.getPluginManager().registerEvents(l, this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            PlayerDamageEvent e = new PlayerDamageEvent(event);
            e.setTrackerDamage(new UnknownDamage((Player) event.getEntity(), event.getDamage()));
            Bukkit.getPluginManager().callEvent(e);
            if (e.isCancelled()) {
                event.setCancelled(true);
            } else {
                Bukkit.broadcastMessage(ChatColor.GOLD + e.getTrackerDamage().getDamaged().getName() + ": " + ChatColor.RED + e.getTrackerDamage().getDescription() + ChatColor.RED + " - " + ChatColor.YELLOW + e.getTrackerDamage().getDamage() + "dmg");
                recordHandler.addDamage(e.getTrackerDamage());
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        List<Damage> record = recordHandler.getRecord(event.getEntity());
        if (record != null && !record.isEmpty()) {
            Damage deathCause = record.get(record.size()-1);
            event.setDeathMessage(deathCause.getDeathMessage());
        }
    }

}
