package net.komputerking.tracker.util;

import net.komputerking.tracker.api.Damage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created when damage has come from an unknown source.
 */
public class UnknownDamage implements Damage {

    private double damage;
    private Player damaged;

    public UnknownDamage(Player damaged, double damage) {
        this.damaged = damaged;
        this.damage = damage;
    }

    @Override
    public String getDescription() {
        return "Unknown";
    }

    @Override
    public Player getDamaged() {
        return null;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public String getDeathMessage() {
        return damaged.getDisplayName() + ChatColor.GRAY + "died";
    }

}
