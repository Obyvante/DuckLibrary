package com.duck.message;

import com.duck.message.DuckMessageHandler;
import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class DuckMessageHandler1_10_R1 implements DuckMessageHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendTitle(String title, String subtitle, int show_up_duration, int stay_duration, int show_out_duration) {
        Bukkit.getOnlinePlayers().forEach(player -> this.sendTitle(player, title, subtitle, show_up_duration, stay_duration, show_out_duration));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendTitle(Player player, String title, String subtitle, int show_up_duration, int stay_duration, int show_out_duration) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', title) + "\"}")));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', subtitle) + "\"}")));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutTitle(show_up_duration, stay_duration, show_out_duration));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendActionBar(String message) {
        Bukkit.getOnlinePlayers().forEach(player -> this.sendActionBar(player, message));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendActionBar(Player player, String message) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}"), (byte) 2));
    }
}
