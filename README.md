# PyFreeze - Minecraft Freeze Plugin
![Version](https://img.shields.io/badge/version-1.0-brightgreen)
![Minecraft](https://img.shields.io/badge/Minecraft-1.20.x-blue)

Easily freeze players and take control during screenshares!

## 👀 Overview
PyFreeze is a Minecraft plugin designed to help administrators easily freeze players during screenshares or other administrative activities. When a player is frozen, they cannot move, open menus, or execute commands (unless allowed by the admin). This plugin is particularly useful in monitoring and controlling player activities to ensure a fair gameplay environment.
![ezgif-2-72da90029e](https://github.com/user-attachments/assets/5215e8fe-ff5c-42cc-870c-d6a7871a0260)


## 🚀 Features
- 🟢 **Player Freeze Command:** Instantly freeze players to prevent them from moving or performing any actions.
- 🟢 **Customizable Notifications:** Send customizable messages to frozen players, including titles and chat notifications.
- 🟢 **Automatic Unfreeze on Disconnect:** If a player disconnects while frozen, they will be automatically unfrozen upon reconnect, and a notification will be sent to the server.
- 🟢 **Link Notifications:** Periodically send a customizable message with a link to frozen players.
- 🟢 **Command Whitelist:** Allows frozen players to execute specific commands as defined by the admin.
- 🟢 **Particle Effects:** Display customizable particle effects around frozen players for a visual indication.

## ⚙️ Configuration

```yaml
# pyFreeze 1.0 - 666pyke

# Basic Messages
usage-message: "§8「§cFreeze§8」§7» &fUsage: &c/pyfreeze <player> [link]"
reload-message: "§8「§creeze§8」§7» &aPyFreeze config reloaded!"
no-permission-message: "§8「§cFreeze§8」§7» &fYou don't have permission to reload the plugin."
usage-unfreeze-message: "§8「§cFreeze§8」§7» &fUsage: /pyfreeze unfreeze <player>"
player-not-found-message: "§8「§cFreeze§8」§7» &fPlayer not found."
unfreeze-success-message: "§8「§cFreeze§8」§7» &fPlayer &c{player}&f has been unfrozen."
not-frozen-message: "§8「§cFreeze§8」§7» &fPlayer &c{player}&f is not frozen."
freeze-success-message: "§8「§cFreeze§8」§7» &fPlayer &c{player}&f has been frozen."

# Messages to the frozen player.
freeze-message: "§8「§cFreeze§8」§7» &fYou have been frozen by an admin!"
title-message: "&b❄ &b&lFROZEN! &b❄"
subtitle-message: "&fYou have been frozen by an admin!"
unfreeze-message: "§8「§cFreeze§8」§7» &fYou have been unfrozen and can now move again."
command-blocked-message: "§8「§cFreeze§8」§7» &fYou cannot use this command while frozen!"

# Quit when frozen
quit-message: "§8「§cFreeze§8」§7» &c{player}&f has disconnected while frozen!"

# The message sent periodically to the frozen player if a link was specified.
# {link} will be replaced with the link provided in the command /pyfreeze <player> <link>

link-message: "§8「§cFreeze§8」§7» &fPlease check this link: &4&n{link}"

# The time interval (in seconds) at which the link message will be sent to the frozen player.
link-message-interval: 10

# The list of allowed commands that frozen players can use.
# Each command should be added without the / character at the beginning.
commands-whitelist:
  - msg
  - help
  - tell

# Particle settings
particle-type: "SNOWFLAKE"
particle-interval: 60
```

## 🛠 How It Works
When you freeze a player with the `/pyfreeze` command, the player will be completely immobilized, unable to perform any actions. You can specify a link that will be sent to them periodically, and particles will appear around them, indicating their frozen state. If their inventory is full during the freeze, they will receive a notification, and any drops will fall to the ground.

## 🤝 Support & Feedback
If you encounter any issues or have suggestions for future features, feel free to reach out via Discord (666pyke) or leave a comment on the plugin page.

