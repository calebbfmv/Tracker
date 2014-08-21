package net.komputerking.tracker.util;

import org.bukkit.entity.Entity;

public class Util {

    public static String getPlural(String name) {
        return (name.endsWith("s") ? "'" : "'s");
    }

    public static String getEntityName(Entity e) {
        switch (e.getType()) {
            case ENDER_DRAGON:
                return "Dragon";
            default:
                return e.getType().getName();
        }
    }

}
