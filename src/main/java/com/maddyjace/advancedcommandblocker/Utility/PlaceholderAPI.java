package com.maddyjace.advancedcommandblocker.Utility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public enum PlaceholderAPI {
    INSTANCE(Bukkit.getPluginManager().getPlugin("PlaceholderAPI"));

    private final Plugin placeholderAPI;

    PlaceholderAPI(Plugin placeholderAPI) {
        this.placeholderAPI = placeholderAPI;
    }

    public boolean isPlaceholderAPILoaded() {
        return placeholderAPI != null;
    }

    public static String parsePlaceholders(Player player, String input) {
        String parsed = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, input);

        return parsed.replace("&", "§");
    }

}
