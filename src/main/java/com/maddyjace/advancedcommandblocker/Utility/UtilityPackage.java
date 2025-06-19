package com.maddyjace.advancedcommandblocker.Utility;

import org.bukkit.entity.Player;

import java.util.List;

public class UtilityPackage {

    public static boolean getRulesCommands(List<String> rulesCommands, String pluginCommands, String type) {
        for(String command : rulesCommands) {
            if(matchesStrictPrefix(command, pluginCommands)) {
                return true;
            } else if(commandsParsing(command, pluginCommands, type)) {
                return true;
            } else if(command.equalsIgnoreCase(pluginCommands))
                return true;
        }
        return false;
    }


    public static boolean commandsParsing(String c, String u, String type) {
        String[] configCommand = splitByWhitespace(c.toLowerCase());
        String[] userCommand = splitByWhitespace(u.toLowerCase());
        if(type.equalsIgnoreCase("blacklist")) {
            return coreProcessingLogic(configCommand, userCommand, userCommand.length >= configCommand.length);
        } else if(type.equalsIgnoreCase("whitelist")) {
            return coreProcessingLogic(configCommand, userCommand, userCommand.length <= configCommand.length);
        } else if(type.equalsIgnoreCase("HIDE_TAB_BLACKLIST")) {
            return coreProcessingLogic(configCommand, userCommand, userCommand.length >= configCommand.length);
        } else if(type.equalsIgnoreCase("HIDE_TAB_WHITELIST")) {
            return coreProcessingLogic(configCommand, userCommand, userCommand.length <= configCommand.length);
        }
        return false;
    }

    private static boolean coreProcessingLogic(String[] configCommand, String[] userCommand, boolean b) {
        if(b && userCommand.length >= configCommand.length && userCommand[0].equals(configCommand[0])) {
            for (int i = 0; i < configCommand.length ; i++) {
                if(UtilityPackage.isNumber(userCommand[i])) {
                    if(configCommand[i].equals("<n:f>")) {
                        return true;
                    }
                } else if(configCommand[i].equals("<s:f>")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] splitByWhitespace(String input) {
        if (input == null) return new String[0];
        return input.trim().split("\\s+");
    }

    public static boolean isNumber(String str) {
        if (str == null || str.trim().isEmpty()) return false;

        str = str.trim();

        if (str.startsWith("~")) {
            if (str.equals("~")) return true;
            try {
                Double.parseDouble(str.substring(1));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean matchesStrictPrefix(String pattern, String input) {
        if (pattern == null || input == null) return false;

        int starIndex = pattern.indexOf('*');
        if (starIndex == -1) return false;

        String prefix = pattern.substring(0, starIndex);

        return input.startsWith(prefix) && input.length() >= prefix.length();
    }

    public static boolean hasPermission(Player player, String permission) {
        if (player == null || permission == null) return false;
        return player.hasPermission(permission);

    }

}
