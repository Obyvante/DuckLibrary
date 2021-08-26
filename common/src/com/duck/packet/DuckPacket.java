package com.duck.packet;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class DuckPacket {

    private static final HashMap<UUID, DuckPacketHandler> packetHandlers = new HashMap<>();

    /**
     * Gets Duck Packet Handler by bukkit player.
     *
     * @param player Bukkit player.
     * @return Duck Packet Handler for bukkit player.
     */
    @Nullable
    public DuckPacketHandler getDuckPacketHandler(@Nonnull Player player) {
        Objects.requireNonNull(player, "player cannot be null!");
        return packetHandlers.get(player.getUniqueId());
    }

    /**
     * Gets Duck Packet object by bukkit player.
     *
     * @param id Bukkit player unique id.
     * @return Duck Packet Handler for bukkit player.
     */
    @Nullable
    public DuckPacketHandler getDuckPacketHandler(@Nonnull UUID id) {
        Objects.requireNonNull(id, "id cannot be null!");
        return packetHandlers.get(id);
    }

    /**
     * Adds Duck Packet Handler to the list.
     *
     * @param duckPacketHandler Duck Packet Handler.
     */
    public void addDuckPacketHandler(@Nonnull DuckPacketHandler duckPacketHandler) {
        Objects.requireNonNull(duckPacketHandler, "duck packet handler cannot be null!");
        DuckPacketHandler duck_packet = packetHandlers.get(duckPacketHandler.getId());
        if (duck_packet != null)
            duck_packet.delete();
        packetHandlers.put(duckPacketHandler.getId(), duckPacketHandler);
    }

    /**
     * Removes Duck Packet Handler from the list.
     *
     * @param duckPacketHandler Duck Packet Handler.
     */
    public void removeDuckPacketHandler(@Nonnull DuckPacketHandler duckPacketHandler) {
        Objects.requireNonNull(duckPacketHandler, "duck packet handler cannot be null!");
        DuckPacketHandler duck_packet = packetHandlers.get(duckPacketHandler.getId());
        if (duck_packet == null)
            return;
        duck_packet.delete();
    }

    /**
     * Removes Duck Packet Handler from the list.
     *
     * @param player Bukkit player.
     */
    public void removeDuckPacketHandler(@Nonnull Player player) {
        Objects.requireNonNull(player, "player cannot be null!");
        DuckPacketHandler duck_packet = packetHandlers.get(player.getUniqueId());
        if (duck_packet == null)
            return;
        duck_packet.delete();
    }

    /**
     * Removes Duck Packet Handler from the list.
     *
     * @param id Bukkit player unique id.
     */
    public void removeDuckPacketHandler(@Nonnull UUID id) {
        Objects.requireNonNull(id, "id cannot be null!");
        DuckPacketHandler duck_packet = packetHandlers.get(id);
        if (duck_packet == null)
            return;
        duck_packet.delete();
    }

    /**
     * Created Duck Packet Handler for bukkit player.
     *
     * @param player Bukkit player.
     * @return Duck Packet Handler.
     */
    public abstract DuckPacketHandler createHandler(Player player);

    /**
     * Sends packet to bukkit player.
     *
     * @param packet  Packet.
     * @param players Bukkit player list.
     */
    public abstract void send(@Nonnull Object packet, @Nonnull Player... players);

    /**
     * Sends packet to bukkit player.
     *
     * @param packet  Packet.
     * @param players UUIDs.
     */
    public abstract void send(@Nonnull Object packet, @Nonnull UUID... players);

    /**
     * Sends packet to bukkit player.
     *
     * @param packet  Packet.
     * @param players Bukkit player list.
     */
    public abstract void send(@Nonnull Object packet, @Nonnull List<Player> players);

    /**
     * Sends packet to location.
     *
     * @param packet   Packet.
     * @param location Location.
     * @param radius   Radius.
     */
    public abstract void send(@Nonnull Object packet, @Nonnull Location location, int radius);

}
