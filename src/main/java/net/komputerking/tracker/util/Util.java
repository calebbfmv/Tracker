package net.komputerking.tracker.util;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Util {

    public static String getPlural(String name) {
        return (name.endsWith("s") ? "'" : "'s");
    }

    public static String getEntityName(Entity e) {
    	return nameEntity(e.getType());
    }
    
    private static String nameEntity(EntityType e){
    	String name = e.name();
    	return splitUnderScore(name);
    }
    
    public static String getItemName(ItemStack item){
    	StringBuilder builder = new StringBuilder();
    	if(item.hasItemMeta()){
    		if(item.getItemMeta().hasDisplayName()){
    			builder.append(item.getItemMeta().getDisplayName());
    			return builder.toString();
    		}
    	}
    	String name = item.getType().name();
    	builder.append(splitUnderScore(name));
    	return builder.toString();
    }
    
    private static String splitUnderScore(String name){
    	StringBuilder builder = new StringBuilder();
    	if(name.contains("_")){
    		String[] str = name.split("_");
    		for(int i = 0; i < str.length; i++){
    			String s = str[i];
    			builder.append(s.substring(0,1).toUpperCase());
    			builder.append(s.substring(1).toLowerCase());
    			if((i+1) != str.length){
    				builder.append(" ");
    			}
    		}
    	} else {
    		builder.append(name.substring(0, 1).toUpperCase());
    		builder.append(name.substring(1).toLowerCase());
    	}
    	return builder.toString();
    }
}
