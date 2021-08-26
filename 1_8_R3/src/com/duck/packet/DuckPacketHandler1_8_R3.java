package com.duck.packet;

import com.duck.DuckLibrary;
import com.duck.packet.event.DuckPacketEvent;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class DuckPacketHandler1_8_R3 implements DuckPacketHandler {

    private final UUID id;
    private final ChannelPipeline pipeline;

    public DuckPacketHandler1_8_R3(@Nonnull Player player) {
        Objects.requireNonNull(player, "player cannot be null!");
        this.id = player.getUniqueId();

        //Add new pipeline named "duck_packet_handler" after "packet_handler".
        this.pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline().addBefore("packet_handler", "duck_packet_handler", new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
                AtomicBoolean status = new AtomicBoolean(false);
                DuckLibrary.runSyncScheduler(() -> {
                    //Creates Duck Packet event.
                    DuckPacketEvent packet_event = new DuckPacketEvent(player, msg, DuckPacketEvent.Type.READ);
                    //Calls event
                    Bukkit.getPluginManager().callEvent(packet_event);
                    //Sets event status
                    status.set(packet_event.isCancelled());
                });
                //Checks if it is cancelled
                if (status.get())
                    return;
                //Passes to default Minecraft packet reader/decoder.
                //If you block it, it will not read default one.
                super.channelRead(channelHandlerContext, msg);
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise) throws Exception {
                AtomicBoolean status = new AtomicBoolean(false);
                DuckLibrary.runSyncScheduler(() -> {
                    //Creates Duck Packet event.
                    DuckPacketEvent packet_event = new DuckPacketEvent(player, o, DuckPacketEvent.Type.WRITE);
                    //Calls event
                    Bukkit.getPluginManager().callEvent(packet_event);
                    //Sets event status
                    status.set(packet_event.isCancelled());
                });
                //Checks if it is cancelled
                if (status.get())
                    return;
                //Passes to default Minecraft packet writer.
                //If you block it, it will not fire/call default one.
                super.write(channelHandlerContext, o, channelPromise);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete() {
        //If the pipeline is not initialized, we don't want to use it, right?
        if (this.pipeline != null && this.pipeline.get("duck_packet_handler") != null)
            //Removes "duck_packet_handler" pipeline from the player's pipeline.
            this.pipeline.remove("duck_packet_handler");
    }
}
