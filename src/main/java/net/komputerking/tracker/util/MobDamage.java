package net.komputerking.tracker.util;

import net.komputerking.tracker.api.Damage;
import org.bukkit.entity.Entity;

public interface MobDamage extends Damage {

    public Entity getMob();

}
