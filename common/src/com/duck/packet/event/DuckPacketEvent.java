package com.duck.packet.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;
import java.util.Objects;

public final class DuckPacketEvent extends Event implements Cancellable {

    /**
     * Packet type.
     */
    public enum Type {
        READ,
        WRITE
    }

    private final Player player;
    private final Object packet;
    private final Type type;
    private boolean cancelled;

    public DuckPacketEvent(@Nonnull Player player, @Nonnull Object packet, @Nonnull Type type) {
        //Objects null control
        Objects.requireNonNull(player, "player cannot be null!");
        Objects.requireNonNull(packet, "packet cannot be null!");
        Objects.requireNonNull(type, "type cannot be null");
        //Root construction
        this.player = player;
        this.packet = packet;
        this.type = type;
    }

    /**
     * Gets bukkit player.
     *
     * @return Bukkit player.
     */
    @Nonnull
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets bukkit packet.
     *
     * @return Bukkit packet.
     */
    @Nonnull
    public <T> T getPacket() {
        return (T) packet;
    }

    /**
     * Gets packet type.
     *
     * @return Packet type.
     */
    @Nonnull
    public Type getType() {
        return type;
    }

    /**
     * Gets if event is cancelled or not.
     *
     * @return If event is cancelled or not.
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets event cancel status.
     *
     * @param cancelled Event cancel status.
     */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /*
        HANDLER LIST
        */
    private static final HandlerList handlerList = new HandlerList();

    /**
     * Gets bukkit event handler list.
     *
     * @return Bukkit event handler list.
     */
    @Nonnull
    public HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Gets bukkit event handler list.
     *
     * @return Bukkit event handler list.
     */
    @Nonnull
    static public HandlerList getHandlerList() {
        return handlerList;
    }
}
