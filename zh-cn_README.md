# 🚫AdvancedCommandBlocker Plugin Overview
**AdvancedCommandBlocker** 专门用于拦截玩家使用特定或特定命令的Tab补全插件，配置简单且功能强大， 可以精准的拦截静态命令(如: /help)和非静态命令(如: 
/tp `<player>`)，插件支持各种场景下的正则表达式规则。插件开发起初是因为我在用市面上的老牌命令拦截插件时发现他们无法我服务器的需求，所以我开发了此插件，如果您在寻找类似的插件不妨看看。

> English [README.md](./README.md)

> 中文简体 [CN-README.md](./zh-cn_README.md)
---
## 📦 插件功能
- 支持为每个玩家组或权限应用不同的规则。
- 通过精确匹配、前缀或正则表达式阻止特定命令。
- 支持每个玩家组的拦截成功后 自定义(支持: PlaceholderAPI) 提示信息。
- 当YML文件发生更改时自动重载配置文件，无需重启服务器或手动输入命令重载。
---

# ⚙️ AdvancedCommandBlocker 配置指南
> [config.yml](src/main/resources/zh_cn-config.yml)

### `✅rules:` # 拦截玩家命令
- `fallSubCommands:` # 组的名称您可以自定义
  - `type: ` `BLACKLIST`&`WHITELIST`
  - `permission:`         # 权限
  - `blockMessage: Hello` # 提示信息
  - `commands:` # 可用参数 `*`,`<s:t>`,`<n:t>`
    - "/help*"                              # 对应拦截 `/help ALL`
    - "/tp `<s:t>` `<s:f>`"                 # 对应拦截 `/tp Player Steve`
    - "/tp `<s:t>` `<n:t>` `<n:t>` `<n:f>`" # 对应拦截 `/tp Player 0 0 0` 命令

- `tab:` # 组的名称您可以自定义
    - `type: ` `HIDE_TAB_BLACKLIST`&`HIDE_TAB_WHITELIST`
    - `permission:`         # 权限
    - `closeChat: false` # 是否强制关闭聊天
    - `blockMessage: Hello` # 提示信息
    - `commands:` # 可用参数 `*`,`<s:t>`,`<n:t>`
        - "/tp `<n:t>` `<n:t>` `<n:f>`"
        - "/help*"
---

### Config.yml
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

