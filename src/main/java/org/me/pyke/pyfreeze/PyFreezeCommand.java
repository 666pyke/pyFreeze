package org.me.pyke.pyfreeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PyFreezeCommand implements CommandExecutor {

    private final PyFreeze plugin;

    public PyFreezeCommand(PyFreeze plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("usage-message", "Usage: /pyfreeze <player> [link] or /pyfreeze unfreeze <player> or /pyfreeze reload")));
            return false;
        }

        String subcommand = args[0].toLowerCase();

        if (subcommand.equals("reload")) {
            if (sender.hasPermission("pyfreeze.reload")) {
                plugin.reloadConfig(); // Reîncarcă config.yml
                plugin.config = plugin.getConfig(); // Actualizează referința la config
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("reload-message", "PyFreeze config reloaded!")));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("no-permission-message", "You don't have permission to reload the plugin.")));
            }
            return true;
        }

        if (subcommand.equals("unfreeze")) {
            if (args.length < 2) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("usage-unfreeze-message", "Usage: /pyfreeze unfreeze <player>")));
                return false;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-found-message", "Player not found.")));
                return false;
            }
            if (plugin.isPlayerFrozen(target.getName())) {
                plugin.unfreezePlayer(target);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("unfreeze-success-message", "Player {player} has been unfrozen.").replace("{player}", target.getName())));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("not-frozen-message", "Player {player} is not frozen.").replace("{player}", target.getName())));
            }
        } else {
            Player target = Bukkit.getPlayerExact(subcommand); // Tratarea subcomenzii ca numele unui jucător
            if (target == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-found-message", "Player not found.")));
                return false;
            }
            String link = args.length > 1 ? args[1] : null;
            plugin.freezePlayer(target, link);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("freeze-success-message", "Player {player} has been frozen.").replace("{player}", target.getName())));
        }

        return true;
    }

}
