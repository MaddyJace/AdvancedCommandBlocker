package com.maddyjace.advancedcommandblocker.ConfigFile;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public enum ConfigFileData {
    INSTANCE();

    private JavaPlugin plugin;

    private FileConfiguration targetFile;

    private ConfigurationSection rulesSection;

    public Plugin placeholderAPI;

    public void initialize(JavaPlugin plugin) {
        this.plugin = plugin;
        File filePath = new File(plugin.getDataFolder(), "config.yml");
        targetFile = YamlConfiguration.loadConfiguration(filePath);
        rulesSection = targetFile.getConfigurationSection("rules");
        placeholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
    }

    public void reload() {
        initialize(plugin);
    }

    public FileConfiguration getTargetFile() {
        return targetFile;
    }

    public ConfigurationSection getRulesSection() {
        return rulesSection;
    }
}
