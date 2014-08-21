package net.komputerking.tracker.util;

import net.komputerking.tracker.api.Damage;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface PlayerDamage extends Damage {

    public Player getDamager();
    public ItemStack getUsedItem();

}
