package com.duck;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DuckMessageHandler1_12_R1 implements DuckMessageHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendTitle(String title, String subtitle, int show_up_duration, int stay_duration, int show_out_duration) {
        Bukkit.getOnlinePlayers().forEach(player ->
                this.sendTitle(player, title, subtitle, show_up_duration, stay_duration, show_out_duration));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendTitle(Player player, String title, String subtitle, int show_up_duration, int stay_duration, int show_out_duration) {
        player.sendTitle(
                ChatColor.translateAlternateColorCodes('&', title),
                ChatColor.translateAlternateColorCodes('&', subtitle),
                show_up_duration,
                stay_duration,
                show_out_duration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendActionBar(String message) {
        Bukkit.getOnlinePlayers().forEach(player ->
                this.sendActionBar(player, message));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
    }
}
