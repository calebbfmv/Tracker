package net.komputerking.tracker;

import net.komputerking.tracker.api.Damage;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Stores information about recent damage a player has took.
 */
public class RecordHandler {

	private Map<UUID, List<Damage>> damage = new HashMap<>();

	public void addDamage(Damage d) {
		if (d.getDamaged() == null) return;
		UUID u = d.getDamaged().getUniqueId();
		if (!damage.containsKey(u)) damage.put(u, new ArrayList<Damage>());
		damage.get(u).add(d);
	}

	public void clearRecord(Player p) {
		damage.remove(p.getUniqueId());
	}

	public List<Damage> getRecord(Player p) {
		return damage.get(p.getUniqueId());
	}

}
