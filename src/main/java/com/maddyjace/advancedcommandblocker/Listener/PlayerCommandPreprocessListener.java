package com.maddyjace.advancedcommandblocker.Listener;

import com.maddyjace.advancedcommandblocker.ConfigFile.ConfigFileData;
import com.maddyjace.advancedcommandblocker.Utility.PlaceholderAPI;
import com.maddyjace.advancedcommandblocker.Utility.UtilityPackage;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class PlayerCommandPreprocessListener implements Listener {

    public Plugin plugin;

    public PlayerCommandPreprocessListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onCommand(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage().toLowerCase();

        ConfigurationSection rulesSection = ConfigFileData.INSTANCE.getRulesSection();
        for (String key : rulesSection.getKeys(false)) {
            ConfigurationSection rule = rulesSection.getConfigurationSection(key);

            String type = rule.getString("type").toLowerCase();
            String permission = rule.getString("permission");
            String blockMessage = rule.getString("blockMessage");
            List<String> commands = rule.getStringList("commands");

            // 判定是否拥有权限（有权限就跳过）
            if (!UtilityPackage.hasPermission(player, permission)) {
                // 判定处理
                if (type.equals("blacklist")) {
                    if (UtilityPackage.getRulesCommands(commands, message, type)) {
                        event.setCancelled(true); // 取消事件

                        // 向玩家发送拦截的提示信息
                        if(PlaceholderAPI.INSTANCE.isPlaceholderAPILoaded() && blockMessage != null) {
                            event.getPlayer().sendMessage(PlaceholderAPI.parsePlaceholders(player, blockMessage));
                        } else if (blockMessage != null) {
                            event.getPlayer().sendMessage(blockMessage.replace("&", "§"));
                        }

                    }
                } else if (type.equals("whitelist")) {
                    if (!UtilityPackage.getRulesCommands(commands, message, type)) {
                        event.setCancelled(true); // 取消事件

                        // 向玩家发送拦截的提示信息
                        if(PlaceholderAPI.INSTANCE.isPlaceholderAPILoaded() && blockMessage != null) {
                            event.getPlayer().sendMessage(PlaceholderAPI.parsePlaceholders(player, blockMessage));
                        } else if (blockMessage != null) {
                            event.getPlayer().sendMessage(blockMessage.replace("&", "§"));
                        }

                    }
                }

            }
        }

    }

}
