# 🚫AdvancedCommandBlocker Plugin Overview
**AdvancedCommandBlocker** is designed to intercept players from using specific or certain commands with Tab completion. It features simple configuration and powerful functionality. It can precisely block static commands (e.g.: /help) and dynamic commands (e.g.:
/tp `<player>`). The plugin supports regular expression rules in various scenarios. The plugin was originally developed because I found that the classic command-blocking plugins on the market couldn't meet the needs of my server, so I developed this plugin. If you're looking for a similar plugin, feel free to check it out.

> English [README.md](./README.md)

> 中文简体 [CN-README.md](./zh-cn_README.md)
---
## 📦 Plugin Features
- Supports applying different rules for each player group or permission.
- Blocks specific commands via exact match, prefix, or regular expression.
- Supports custom (supports: PlaceholderAPI) messages after blocking, per player group.
- Automatically reloads the configuration file when the YML file changes, no need to restart the server or manually input reload commands.
---

# ⚙️ AdvancedCommandBlocker Configuration Guide
> [config.yml](src/main/resources/config.yml)

### `✅rules:` # Intercept player commands
- `fallSubCommands:` # You can customize the group name
    - `type: ` `BLACKLIST`&`WHITELIST`
    - `permission:`         # Permission
    - `blockMessage: Hello` # Prompt message
    - `commands:` # Available parameters `*`,`<s:t>`,`<n:t>`
        - "/help*"                              # Corresponds to blocking `/help ALL`
        - "/tp `<s:t>` `<s:f>`"                 # Corresponds to blocking `/tp Player Steve`
        - "/tp `<s:t>` `<n:t>` `<n:t>` `<n:f>`" # Corresponds to blocking `/tp Player 0 0 0` command

- `tab:` # You can customize the group name
    - `type: ` `HIDE_TAB_BLACKLIST`&`HIDE_TAB_WHITELIST`
    - `permission:`         # Permission
    - `closeChat: false` # Whether to force close chat
    - `blockMessage: Hello` # Prompt message
    - `commands:` # Available parameters `*`,`<s:t>`,`<n:t>`
        - "/tp `<n:t>` `<n:t>` `<n:f>`"
        - "/help*"
---

### Config.yml
```yaml
# Commands for the AdvancedCommandBlocker plugin
# /acb reload – Reload the configuration!
# /advancedcommandblocker reload - Reload the configuration!

rules:
  allSubCommands:
    # BLACKLIST mode: block the listed commands
    # WHITELIST mode: only allow the listed commands
    # Warning: You cannot configure both BLACKLIST and WHITELIST at the same time; although allowed by YAML, the plugin will conflict!
    type: BLACKLIST
    # Permission that bypasses this rule; players with this permission will not be blocked
    permission: "cab.allSubCommands"
    # Message sent to the player when a command is blocked; supports PlaceholderAPI placeholders
    blockMessage: "&l&8| &cError &8» &cYou do not have permission to use this command."
    commands:
      # - "/help*"
      # The asterisk (*) blocks all commands starting with /help.
      - "/help*"

  commands:
    # BLACKLIST mode: block the listed commands
    # WHITELIST mode: only allow the listed commands
    # Warning: You cannot configure both BLACKLIST and WHITELIST at the same time; although allowed by YAML, the plugin will conflict!
    type: BLACKLIST
    # Permission that bypasses this rule; players with this permission will not be blocked
    permission: "cab.command"
    # Message sent to the player when a command is blocked; supports PlaceholderAPI placeholders
    blockMessage: "&l&8| &cError &8» &cYou do not have permission to use Bukkit commands."
    commands:
      # - "/bukkit:?"
      # Blocks the exact /bukkit:? command.
      - "/bukkit:?"
      - "/plugins"

  tp:
    # BLACKLIST mode: block the listed commands
    # WHITELIST mode: only allow the listed commands
    # Warning: You cannot configure both BLACKLIST and WHITELIST at the same time; although allowed by YAML, the plugin will conflict!
    type: BLACKLIST
    # Permission that bypasses this rule; players with this permission will not be blocked
    permission: "acb.tpCommand"
    # Message sent to the player when a command is blocked; supports PlaceholderAPI placeholders
    blockMessage: "&l&8| &cError &8» &cYou do not have permission to use the tp command."
    commands:
      # - "/tp <s:t> <s:f>"
      # <s:t> is a true-text placeholder, <s:f> is a false-text placeholder
      # Explanation: A player trying to use /tp player01 player02 will be blocked, but /tp player01 will not be.

      # - "/tp <s:t> <n:t> <n:t> <n:f>"
      # <n:t> is a true-number placeholder, <n:f> is a false-number placeholder
      # The following numeric placeholders are supported: "123", "-45.6", "~", "~0", "~1.23", "1e10", "1.8e308"
      # Explanation: A player trying to use /tp player01 0 0 10 will be blocked, but /tp player01 0 0 will not be.
      #              Players cannot use /tp player01 player02 or /tp player01 0 0 10, but can use /tp 0 0 10 unless you add more specific rules.

      # Use placeholders to indicate which argument position to start blocking (arguments are space-separated; e.g. /tp player01 player02 has three arguments).
      - "/tp <s:t> <s:f>"
      - "/tp <s:t> <n:t> <n:t> <n:f>"

  tab:
    # HIDE_TAB_BLACKLIST: hide tab completions for listed commands (but do not block execution)
    # HIDE_TAB_WHITELIST: only show tab completions for listed commands (but do not block execution)
    # Warning: You cannot configure both HIDE_TAB_BLACKLIST and HIDE_TAB_WHITELIST at the same time; although allowed by YAML, the plugin will conflict!
    type: HIDE_TAB_BLACKLIST
    # Force-close the player's chat input when they press Tab: true = close chat and block Tab; false = do not close but block Tab
    closeChat: false
    # Permission that bypasses this rule; players with this permission will not be blocked when pressing Tab
    permission: "acb.tabCommand"
    # In tab mode, the <s:t>, <s:f>, <n:t>, <n:f> and * placeholders work the same way as in command mode.
    commands:
      - "/tp <n:t> <n:t> <n:f>"
      - "/help*"
```

