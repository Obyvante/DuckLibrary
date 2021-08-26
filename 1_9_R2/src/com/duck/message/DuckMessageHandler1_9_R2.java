package com.duck.message;

import com.duck.DuckLibrary;
import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import net.minecraft.server.v1_9_R2.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Objects;

public final class DuckMessageHandler1_9_R2 implements DuckMessageHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendTitle(@Nonnull String title, @Nonnull String subtitle, int show_up_duration, int stay_duration, int show_out_duration) {
        //Objects null check.
        Objects.requireNonNull(title, "title cannot be null!");

        Bukkit.getOnlinePlayers().forEach(player -> this.sendTitle(player, title, subtitle, show_up_duration, stay_duration, show_out_duration));
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

        DuckLibrary.getPacket().send(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', title) + "\"}")), player);
        DuckLibrary.getPacket().send(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', subtitle) + "\"}")), player);
        DuckLibrary.getPacket().send(new PacketPlayOutTitle(show_up_duration, stay_duration, show_out_duration), player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendActionBar(@Nonnull String message) {
        //Objects null check.
        Objects.requireNonNull(message, "message cannot be null!");

        Bukkit.getOnlinePlayers().forEach(player -> this.sendActionBar(player, message));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendActionBar(@Nonnull Player player, @Nonnull String message) {
        //Objects null check.
        Objects.requireNonNull(player, "player cannot be null!");
        Objects.requireNonNull(message, "message cannot be null!");

        DuckLibrary.getPacket().send(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}"), (byte) 2), player);
    }
}
