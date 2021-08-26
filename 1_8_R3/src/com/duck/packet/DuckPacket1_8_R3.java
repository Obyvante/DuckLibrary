package com.duck.packet;

import com.duck.DuckLibrary;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DuckPacket1_8_R3 extends DuckPacket {

    /**
     * Main construction.
     * Mostly for register listener.
     */
    public DuckPacket1_8_R3() {
        //Register join listener.
        DuckLibrary.registerEvent(PlayerJoinEvent.class).consume(event -> DuckLibrary.getPacket().createHandler(event.getPlayer()));
        //Register quit listener.
        DuckLibrary.registerEvent(PlayerQuitEvent.class).consume(event -> DuckLibrary.getPacket().removeDuckPacketHandler(event.getPlayer()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DuckPacketHandler createHandler(@Nonnull Player player) {
        //Objects null check.
        Objects.requireNonNull(player, "player cannot be null!");
        //Created new Duck Packet Handler.
        DuckPacketHandler duck_packet_handler = new DuckPacketHandler1_8_R3(player);
        //Adds new created duck packet handler to the list.
        this.addDuckPacketHandler(duck_packet_handler);
        //Return new created duck packet handler.
        return duck_packet_handler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(@Nonnull Object packet, @Nonnull Player... players) {
        //Objects null check.
        Objects.requireNonNull(packet, "packet cannot be null!");
        Objects.requireNonNull(players, "players cannot be null!");

        //Check if object is packet or not.
        if (!(packet instanceof Packet))
            throw new NullPointerException(packet.getClass() + " is not a bukkit packet!");

        //Sends packet.
        Arrays.stream(players)
                .filter(player -> player != null && player.isOnline())
                .forEach(player -> ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet<?>) packet));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(@Nonnull Object packet, @Nonnull UUID... players) {
        //Objects null check.
        Objects.requireNonNull(packet, "packet cannot be null!");
        Objects.requireNonNull(players, "players cannot be null!");

        //Check if object is packet or not.
        if (!(packet instanceof Packet))
            throw new NullPointerException(packet.getClass() + " is not a bukkit packet!");

        //Sends packet.
        Arrays.stream(players)
                .map(Bukkit::getPlayer)
                .filter(player -> player != null && player.isOnline())
                .forEach(player -> ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet<?>) packet));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(@Nonnull Object packet, @Nonnull List<Player> players) {
        //Objects null check.
        Objects.requireNonNull(packet, "packet cannot be null!");
        Objects.requireNonNull(players, "players cannot be null!");

        //Check if object is packet or not.
        if (!(packet instanceof Packet))
            throw new NullPointerException(packet.getClass() + " is not a bukkit packet!");

        //Sends packet.
        players.stream()
                .filter(player -> player != null && player.isOnline())
                .forEach(player -> ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet<?>) packet));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(@Nonnull Object packet, @Nonnull Location location, int radius) {
        Objects.requireNonNull(packet, "packet cannot be null!");
        Objects.requireNonNull(location, "location cannot be null!");
        Objects.requireNonNull(location.getWorld(), "location world cannot be null!");

        //Check if object is packet or not.
        if (!(packet instanceof Packet))
            throw new NullPointerException(packet.getClass() + " is not a bukkit packet!");

        //Sends packet.
        location.getWorld().getNearbyEntities(location, radius, radius, radius).stream()
                .filter(entity -> entity instanceof Player)
                .forEach(player -> ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet<?>) packet));
    }
}
