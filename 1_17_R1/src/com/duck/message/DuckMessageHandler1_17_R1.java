package com.duck.message;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Objects;

public final class DuckMessageHandler1_17_R1 implements DuckMessageHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendTitle(@Nonnull String title, @Nonnull String subtitle, int show_up_duration, int stay_duration, int show_out_duration) {
        //Objects null check.
        Objects.requireNonNull(title, "title cannot be null!");

        Bukkit.getOnlinePlayers().forEach(player ->
                this.sendTitle(player, title, subtitle, show_up_duration, stay_duration, show_out_duration));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendTitle(@Nonnull Player player, @Nonnull String title, @Nonnull String subtitle, int show_up_duration, int stay_duration, int show_out_duration) {
        //Objects null check.
        Objects.requireNonNull(player, "player cannot be null!");
        Objects.requireNonNull(title, "title cannot be null!");
        Objects.requireNonNull(subtitle, "subtitle cannot be null!");

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
    public void sendActionBar(@Nonnull String message) {
        //Objects null check.
        Objects.requireNonNull(message, "message cannot be null!");

        Bukkit.getOnlinePlayers().forEach(player ->
                this.sendActionBar(player, message));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendActionBar(@Nonnull Player player, @Nonnull String message) {
        //Objects null check.
        Objects.requireNonNull(player, "player cannot be null!");
        Objects.requireNonNull(message, "message cannot be null!");

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
    }
}
