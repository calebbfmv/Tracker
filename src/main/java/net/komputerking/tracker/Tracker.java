package net.komputerking.tracker;

import net.komputerking.tracker.api.Damage;
import net.komputerking.tracker.events.PlayerDamageEvent;
import net.komputerking.tracker.trackers.PVPTracker;
import net.komputerking.tracker.util.UnknownDamage;
import org.bukkit.Bukkit;
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

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            PlayerDamageEvent e = new PlayerDamageEvent(event);
            e.setTrackerDamage(new UnknownDamage((Player) event.getEntity(), event.getDamage()));
            Bukkit.getPluginManager().callEvent(e);
            if (e.isCancelled()) {
                event.setCancelled(true);
            } else {
                recordHandler.addDamage(e.getTrackerDamage());
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        List<Damage> record = recordHandler.getRecord(event.getEntity());
        if (record == null) {
            Bukkit.broadcastMessage("record is empty");
        } else {
            Damage deathCause = record.get(record.size()-1);
            if (deathCause instanceof UnknownDamage) {
                Bukkit.broadcastMessage("we don't know how they died");
            } else if (deathCause instanceof PVPTracker.PVPDamage) {
                PVPTracker.PVPDamage d = (PVPTracker.PVPDamage) deathCause;
                Bukkit.broadcastMessage("they were killed by " + d.getDamager().getName() + " and the last hit took " + d.getDamage() + " health");
            }
        }
    }

}
