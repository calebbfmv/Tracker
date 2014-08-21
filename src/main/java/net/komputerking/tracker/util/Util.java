package net.komputerking.tracker.util;

public class Util {

    public static String getPlural(String name) {
        return (name.endsWith("s") ? "'" : "'s");
    }

}
