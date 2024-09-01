# PyFreeze - Minecraft Freeze Plugin
![Version](https://img.shields.io/badge/version-1.0-brightgreen)
![Minecraft](https://img.shields.io/badge/Minecraft-1.20.x-blue)

Easily freeze players and take control during screenshares!

## 👀 Overview
PyFreeze is a Minecraft plugin designed to help administrators easily freeze players during screenshares or other administrative activities. When a player is frozen, they cannot move, open menus, or execute commands (unless allowed by the admin). This plugin is particularly useful in monitoring and controlling player activities to ensure a fair gameplay environment.

## 🚀 Features
- 🟢 **Player Freeze Command:** Instantly freeze players to prevent them from moving or performing any actions.
- 🟢 **Customizable Notifications:** Send customizable messages to frozen players, including titles and chat notifications.
- 🟢 **Automatic Unfreeze on Disconnect:** If a player disconnects while frozen, they will be automatically unfrozen upon reconnect, and a notification will be sent to the server.
- 🟢 **Link Notifications:** Periodically send a customizable message with a link to frozen players.
- 🟢 **Command Whitelist:** Allows frozen players to execute specific commands as defined by the admin.
- 🟢 **Particle Effects:** Display customizable particle effects around frozen players for a visual indication.

## ⚙️ Configuration

```yaml
plugin-enabled: true

freeze-settings:
  send-link: true
  link-message-interval: 10
  particle-type: "SNOWFLAKE"
  particle-interval: 20
  works-in-creative: false

messages:
  freeze-message: "&cYou have been frozen!"
  unfreeze-message: "&aYou have been unfrozen!"
  quit-message: "&e{player} has disconnected while frozen!"
  title-message: "&cYou have been frozen by an admin!"
  subtitle-message: "&7You cannot move."
  command-blocked-message: "&cYou cannot use this command while frozen!"
  link-message: "&ePlease check this link: {link}"
  reload-message: "&aPyFreeze config has been reloaded!"
  usage-message: "&cUsage: /pyfreeze <player> [link] or /pyfreeze unfreeze <player> or /pyfreeze reload"
  no-permission-message: "&cYou don't have permission to reload the plugin."

commands-whitelist:
  - msg
  - help
  - tell
```

## 🛠 How It Works
When you freeze a player with the `/pyfreeze` command, the player will be completely immobilized, unable to perform any actions. You can specify a link that will be sent to them periodically, and particles will appear around them, indicating their frozen state. If their inventory is full during the freeze, they will receive a notification, and any drops will fall to the ground.

## 🤝 Support & Feedback
If you encounter any issues or have suggestions for future features, feel free to reach out via Discord (666pyke) or leave a comment on the plugin page.

