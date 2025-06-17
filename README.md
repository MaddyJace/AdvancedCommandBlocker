## 🚫 AdvancedCommandBlocker

- Based on Bukkit and Spigot APIs. Supports Minecraft 1.12.2 to 1.21.1+ and future versions.

## ✨ Features
- 🔒 Block specific commands by exact match, prefix, or regex
- 👥 Apply different rules per player group or permission
- 🌐 Supports PlaceholderAPI and custom deny messages
- 🧩 Fully configurable via YAML
- 🔄 Reloadable in-game without restarting the server

## ⚙️ US Example Configuration

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
## ⚙️ CN Example Configuration

```yaml
# AdvancedCommandBlocker插件的命令
# /acb reload 重载！
# /advancedcommandblocker reload - Reload the configuration!

rules:
  allSubCommands:
    # BLACKLIST（黑名单模式：拦截列出的命令）
    # WHITELIST（白名单模式：只允许列出的命令）
    # 警告: 你不能 BLACKLIST 和 WHITELIST 同时配置使用，虽然允许但插件会冲突！
    type: BLACKLIST
    # 当玩家拥有此权限后，插件不会拦截。
    permission: "cab.allSubCommands"
    # 当拦截成功后提示给玩家的信息，支持使用PlaceholderAPI占位符
    blockMessage: "&l&8| &c错误 &8» &c你没有权限使用该命令。"
    commands:
      # - "/help*"
      # *是拦截所有以 /help 开头的命令。
      - "/help*"

  commands:
    # BLACKLIST（黑名单模式：拦截列出的命令）
    # WHITELIST（白名单模式：只允许列出的命令）
    # 警告: 你不能 BLACKLIST 和 WHITELIST 同时配置使用，虽然允许但插件会冲突！
    type: BLACKLIST
    # 当玩家拥有此权限后，插件不会拦截。
    permission: "cab.command"
    # 当拦截成功后提示给玩家的信息，支持使用PlaceholderAPI占位符
    blockMessage: "&l&8| &c错误 &8» &c你没有权限使用bukkit命令。"
    commands:
      # - "/bukkit:?"
      # 当玩家输入 /bukkit:? 使会被拦截。
      - "/bukkit:?"
      - "/plugins"

  tp:
    # BLACKLIST（黑名单模式：拦截列出的命令）
    # WHITELIST（白名单模式：只允许列出的命令）
    # 警告: 你不能 BLACKLIST 和 WHITELIST 同时配置使用，虽然允许但插件会冲突！
    type: BLACKLIST
    # 当玩家拥有此权限后，插件不会拦截。
    permission: "acb.tpCommand"
    # 当拦截成功后提示给玩家的信息，支持使用PlaceholderAPI占位符
    blockMessage: "&l&8| &c错误 &8» &c你没有权限使用tp命令。"
    commands:
      # - "/tp <s:t> <s:f>"
      # <s:t>是true文本占位符，<s:f>是false文本占位符
      # 说明: 当玩家尝试输入 /tp player01 player02 时会被拦截，但输入 /tp player01 不会拦截。

      # - "/tp <s:t> <n:t> <n:t> <n:f>"
      # <n:t>是true数字占位符，<n:f>是false数字占位符
      # 说明: 当玩家尝试输入 /tp player01 0 0 10 时会被拦截，但输入 /tp player01 0 0 不会拦截。
      #      玩家不能使用 /tp player01 player02 和 /tp player01 0 0 10 但可以使用 /tp 0 0 10 除非你更明确的拦截。
      # 数字占位符支持: "123" "-45.6" "~" "~0" "~1.23" "1e10" "1.8e308"

      # 所以你想命令的第几个参数开始拦截(参数就是以空格隔开，如: /tp player01 player02 就是三个参数)，就使用占位符。
      - "/tp <s:t> <s:f>"
      - "/tp <s:t> <n:t> <n:t> <n:f>"

  tab:
    # HIDE_TAB_BLACKLIST（黑名单模式：隐藏列出的命令的补全，但不拦截其执行）
    # HIDE_TAB_WHITELIST（白名单模式：只允列出的命令的补全，但不拦截其执行）
    # 警告: 你不能 HIDE_TAB_BLACKLIST 和 HIDE_TAB_WHITELIST 同时配置使用，虽然允许但插件会冲突！
    type: HIDE_TAB_BLACKLIST
    # 强制关闭玩家的聊天栏，true强制关闭并拦截Tab键，false不关闭并拦截Tab键
    closeChat: false
    # 当玩家拥有此权限时，使用Tab键时不会被拦截。
    permission: "acb.tabCommand"
    commands:
      # commands的 <s:t> <s:f> 和 <n:t> <n:f> 以及 * 占位符在Tab模式下也能使用，不再重复说明。
      - "/tp <n:t> <n:t> <n:f>"
      - "/help*"
```
## License

This project is licensed under the **Creative Commons BY-NC 4.0 License**.  
You are free to use, modify, and share it **for non-commercial purposes only**.

Full license: [https://creativecommons.org/licenses/by-nc/4.0](https://creativecommons.org/licenses/by-nc/4.0/)

