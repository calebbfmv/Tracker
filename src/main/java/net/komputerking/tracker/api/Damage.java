package net.komputerking.tracker.api;

import org.bukkit.entity.Player;

/**
 * Object that contains information about damage that has occurred to a player.
 */
public interface Damage {

    /**
     * Gets a short description about the damage. (e.g: "Fell 45 blocks")
     * @return A description of how the damage occured
     */
    public String getDescription();

    /**
     * Gets the player that has been damaged.
     * @return The damaged player
     */
    public Player getDamaged();

    /**
     * Gets the amount of damage that was applied.
     * @return The damage applied
     */
    public double getDamage();

}
