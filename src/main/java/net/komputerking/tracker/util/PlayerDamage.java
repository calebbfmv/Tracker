package net.komputerking.tracker.util;

import net.komputerking.tracker.api.Damage;
import org.bukkit.entity.Player;

public interface PlayerDamage extends Damage {

    public Player getDamager();

}
