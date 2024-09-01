package org.me.pyke.pyfreeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class PyFreeze extends JavaPlugin implements Listener {

    private final Set<String> frozenPlayers = new HashSet<>();
    public FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        config = this.getConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("pyfreeze")).setExecutor(new PyFreezeCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean isPlayerFrozen(String playerName) {
        return frozenPlayers.contains(playerName);
    }

    public void freezePlayer(Player player, String link) {
        frozenPlayers.add(player.getName());
        player.setWalkSpeed(0);
        player.setInvulnerable(true);
        String freezeMessage = config.getString("freeze-message", "&cYou have been frozen!");
        String titleFreezeMessage = config.getString("title-message", "&fFrozen");
        String subtitleFreezeMessage = config.getString("subtitle-message", "&fYou have been frozen by an admin!");

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', freezeMessage));
        player.sendTitle(
                ChatColor.translateAlternateColorCodes('&', titleFreezeMessage),
                ChatColor.translateAlternateColorCodes('&', subtitleFreezeMessage),
                10, 70, 20 // fadeIn, stay, fadeOut time in ticks
        );

        // Particule
        String particleType = config.getString("particle-type", "SNOWFLAKE").toUpperCase();
        int particleInterval = config.getInt("particle-interval", 10); // în ticks
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!isPlayerFrozen(player.getName())) {
                    this.cancel();
                    return;
                }
                player.getWorld().spawnParticle(Particle.valueOf(particleType), player.getLocation(), 30, 0.5, 0.5, 0.5, 0.01);
            }
        }.runTaskTimer(this, 0, particleInterval);

        if (link != null) {
            int interval = config.getInt("link-message-interval", 10) * 20; // Convert seconds to ticks
            Bukkit.getScheduler().runTaskTimer(this, () -> {
                if (isPlayerFrozen(player.getName())) {
                    String linkMessage = config.getString("link-message", "&eVisit this link: {link}");
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', linkMessage.replace("{link}", link)));
                }
            }, 0, interval);
        }
    }

    public void unfreezePlayer(Player player) {
        frozenPlayers.remove(player.getName());
        player.setWalkSpeed(0.2f); // Reset to normal speed
        player.setInvulnerable(false);
        String unfreezeMessage = config.getString("unfreeze-message", "&aYou have been unfrozen!");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', unfreezeMessage));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (isPlayerFrozen(player.getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (isPlayerFrozen(player.getName())) {
            String command = event.getMessage().split(" ")[0].substring(1).toLowerCase(); // Îndepărtează '/' și transformă în lowercase
            if (!isCommandAllowed(command)) {
                event.setCancelled(true);
                String commandMessage = config.getString("command-blocked-message", "&cYou cannot use this command while frozen!");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandMessage));
            }
        }
    }

    private boolean isCommandAllowed(String command) {
        for (String allowedCommand : config.getStringList("commands-whitelist")) {
            if (command.startsWith(allowedCommand.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (isPlayerFrozen(player.getName())) {
            unfreezePlayer(player);
            String quitMessage = config.getString("quit-message", "&e{player} has disconnected while frozen!");
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', quitMessage.replace("{player}", player.getName())));
        }
    }
}
