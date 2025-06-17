package com.maddyjace.advancedcommandblocker;

import com.maddyjace.advancedcommandblocker.Commands.Commands;
import com.maddyjace.advancedcommandblocker.ConfigFile.ConfigFileData;
import com.maddyjace.advancedcommandblocker.Listener.PlayerCommandPreprocessListener;
import com.maddyjace.advancedcommandblocker.Listener.TabCompleteListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedCommandBlocker extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();
        saveResource("zh_cn-config.yml", false);

        ConfigFileData.INSTANCE.initialize(this);

        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(this), this);
        getServer().getPluginManager().registerEvents(new TabCompleteListener(), this);

        Commands commandHandler = new Commands();
        this.getCommand("advancedcommandblocker").setExecutor(commandHandler);
        this.getCommand("advancedcommandblocker").setTabCompleter(commandHandler);

        Bukkit.getConsoleSender().sendMessage("§b§l");
        Bukkit.getConsoleSender().sendMessage("§b§l");

        Bukkit.getConsoleSender().sendMessage("§b§l    /\\         | |          / ____|                     |  _ \\  | |        ");
        Bukkit.getConsoleSender().sendMessage("§b§l   /  \\      __| | __   __ | |        ___    _ __ ___   | |_) | | |   ___  ");
        Bukkit.getConsoleSender().sendMessage("§b§l  / /\\ \\    / _` | \\ \\ / / | |       / _ \\  | '_ ` _ \\  |  _ <  | |  / _ \\ ");
        Bukkit.getConsoleSender().sendMessage("§b§l / ____ \\  | (_| |  \\ V /  | |____  | (_) | | | | | | | | |_) | | | | (_) |");
        Bukkit.getConsoleSender().sendMessage("§b§l/_/    \\_\\  \\__,_|   \\_/    \\_____|  \\___/  |_| |_| |_| |____/  |_|  \\___/ ");

        Bukkit.getConsoleSender().sendMessage("§f");
        Bukkit.getConsoleSender().sendMessage("§7Author: §aMaddyJace     §7Email: §edixiaomai@qq.com     §7CN-QQ: §e2743063754     §7Version: §e1.0");
        Bukkit.getConsoleSender().sendMessage("§f-------------------------------------------------------------------------------------");
        Bukkit.getConsoleSender().sendMessage("§b§l");
        Bukkit.getConsoleSender().sendMessage("§b§l");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
