package com.maddyjace.advancedcommandblocker.Listener;

import com.maddyjace.advancedcommandblocker.ConfigFile.ConfigFileData;
import com.maddyjace.advancedcommandblocker.Utility.UtilityPackage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.List;

public class TabCompleteListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onTabComplete(TabCompleteEvent event) {
        try {

            Player player = (Player) event.getSender();
            String TabMessage = event.getBuffer().toLowerCase();

            ConfigurationSection rulesSection = ConfigFileData.INSTANCE.getRulesSection();
            for (String key : rulesSection.getKeys(false)) {
                ConfigurationSection rule = rulesSection.getConfigurationSection(key);

                String type = rule.getString("type").toLowerCase();
                String closeChat  = rule.getString("closeChat");
                String permission = rule.getString("permission");
                List<String> commands = rule.getStringList("commands");

                if (!UtilityPackage.hasPermission(player, permission)) {
                    if (type.equals("hide_tab_blacklist")) {
                        if (UtilityPackage.getRulesCommands(commands, TabMessage, type)) {

                            closeChat(event, player, closeChat);

                        }
                    } else if (type.equals("hide_tab_whitelist")) {
                        if (!UtilityPackage.getRulesCommands(commands, TabMessage, type)) {

                            closeChat(event, player, closeChat);

                        }
                    }
                }
            }

        } catch (Exception ignored) { }
    }

    private void closeChat(TabCompleteEvent event, Player player, String closeChat) {
        if(closeChat.equals("true")) {

            Inventory fakeInv = Bukkit.createInventory(null, 9, "AdvancedCommandBlocker-202506171506000-#9!null");

            player.openInventory(fakeInv);

            InventoryView openView = player.getOpenInventory();
            if (fakeInv != null && "AdvancedCommandBlocker-202506171506000-#9!null".equals(openView.getTitle())) {
                player.closeInventory();
            }
        } else if (closeChat.equals("false")) {
            event.setCancelled(true);
        }
    }


}
