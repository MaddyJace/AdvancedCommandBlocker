package com.maddyjace.advancedcommandblocker;

import com.maddyjace.advancedcommandblocker.Commands.Commands;
import com.maddyjace.advancedcommandblocker.ConfigFile.ConfigFileData;
import com.maddyjace.advancedcommandblocker.ConfigFile.FileWatcher;
import com.maddyjace.advancedcommandblocker.Listener.PlayerCommandPreprocessListener;
import com.maddyjace.advancedcommandblocker.Listener.TabCompleteListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AdvancedCommandBlocker extends JavaPlugin {
    private FileWatcher watcher;
    @Override
    public void onEnable() {

        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
            saveResource("zh-cn_config.yml", false);
        }

        // 初始化
        ConfigFileData.INSTANCE.initialize(this);

        // 初始化 FileWatcher 类
        watcher = new FileWatcher(getDataFolder().getAbsolutePath(), ".yml", 100);

        // 开启文件监听
        try {
            watcher.start();
        } catch (Exception e) {
            getLogger().warning("The automatic reload function failed to enable!");
        }

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
        // 关闭文件监听
        try {
            watcher.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
