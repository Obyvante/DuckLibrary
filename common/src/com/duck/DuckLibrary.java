package com.duck;

import com.duck.chain.DuckChain;
import com.duck.event.DuckEvent;
import com.duck.logger.DuckLogger;
import com.duck.message.DuckMessageHandler;
import com.duck.packet.DuckPacket;
import com.duck.scheduler.DuckScheduler;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class DuckLibrary {

    //Java plugin
    private static JavaPlugin instance;

    //Protocol
    private static DuckProtocolVersion protocolVersion;

    //Logger
    private static final DuckLogger logger = new DuckLogger();

    //Commons
    private static DuckMessageHandler messageHandler;

    //Packet
    private static DuckPacket packet;

    //Initialize
    private static boolean initialized;

    /**
     * Gets duck library java plugin.
     *
     * @return Duck library java plugin.
     */
    public static JavaPlugin getInstance() {
        return instance;
    }

    /**
     * Sets duck library java plugin.
     *
     * @param instance Duck library java plugin.
     */
    public static void setInstance(@Nonnull JavaPlugin instance) {
        Objects.requireNonNull(instance, "instance cannot be null!");
        DuckLibrary.instance = instance;
    }

    /**
     * Gets duck protocol version.
     *
     * @return Duck protocol version.
     */
    public static DuckProtocolVersion getProtocolVersion() {
        if (!initialized)
            logger.error("Tried to fetch data from Duck Library during initializing.");
        return protocolVersion;
    }

    public static void setProtocolVersion(@Nonnull DuckProtocolVersion protocolVersion) {
        Objects.requireNonNull(protocolVersion, "protocol version cannot be null!");
        DuckLibrary.protocolVersion = protocolVersion;
    }

    /**
     * Gets duck message handler.
     *
     * @return Duck message handler.
     */
    public static DuckMessageHandler getMessageHandler() {
        if (!initialized)
            logger.error("Tried to fetch data from Duck Library during initializing.");
        return messageHandler;
    }

    /**
     * Sets message handler.
     *
     * @param messageHandler Message handler.
     */
    public static void setMessageHandler(@Nonnull DuckMessageHandler messageHandler) {
        Objects.requireNonNull(messageHandler, "message handler cannot be null!");
        DuckLibrary.messageHandler = messageHandler;
    }

    /**
     * Gets duck logger.
     *
     * @return Duck logger.
     */
    @Nonnull
    public static DuckLogger getLogger() {
        return logger;
    }

    /**
     * Gets if Duck Library is ready to use or not.
     *
     * @return If Duck Library is ready to use or not.
     */
    public static boolean isReady() {
        return initialized;
    }

    /**
     * Sets Duck Library status.
     *
     * @param initialized Duck Library status.
     */
    public static void setStatus(boolean initialized) {
        DuckLibrary.initialized = initialized;
    }


    /*
    SCHEDULERS
     */

    /**
     * Runs Duck Scheduler as a main thread.
     *
     * @param runnable Duck Scheduler builder as synchronous.
     */
    public static void mainThread(@Nonnull Runnable runnable) {
        new DuckScheduler(DuckScheduler.Type.SYNC).run(runnable);
    }

    /*
    SYNC
     */

    /**
     * Creates Duck Scheduler builder as synchronous.
     *
     * @return Duck Scheduler builder as synchronous.
     */
    @Nonnull
    public static DuckScheduler createSyncScheduler() {
        return new DuckScheduler(DuckScheduler.Type.SYNC);
    }

    /**
     * Runs Duck Scheduler builder as synchronous.
     *
     * @return Scheduler bukkit task id.
     */
    public static int runSyncScheduler(@Nonnull Runnable runnable) {
        return new DuckScheduler(DuckScheduler.Type.SYNC).run(runnable);
    }

    /**
     * Runs Duck Scheduler builder as synchronous with delay.
     *
     * @return Scheduler bukkit task id.
     */
    public static int runDelayedSyncScheduler(@Nonnull Runnable runnable, int delay, @Nonnull TimeUnit delayType) {
        return new DuckScheduler(DuckScheduler.Type.SYNC).after(delay, delayType).run(runnable);
    }

    /**
     * Runs Duck Scheduler builder as synchronous for every declared time.
     *
     * @return Scheduler bukkit task id.
     */
    public static int runRepeatingSyncScheduler(@Nonnull Runnable runnable, int repeatingDelay, @Nonnull TimeUnit repeatingDelayType) {
        return new DuckScheduler(DuckScheduler.Type.SYNC).after(repeatingDelay, repeatingDelayType).run(runnable);
    }

    /*
    ASYNC
     */

    /**
     * Creates Duck Scheduler builder as asynchronous.
     *
     * @return Duck Scheduler builder as asynchronous.
     */
    @Nonnull
    public static DuckScheduler createAsyncScheduler() {
        return new DuckScheduler(DuckScheduler.Type.ASYNC);
    }

    /**
     * Runs Duck Scheduler builder as asynchronous.
     *
     * @return Scheduler bukkit task id.
     */
    public static int runAsyncScheduler(@Nonnull Runnable runnable) {
        return new DuckScheduler(DuckScheduler.Type.ASYNC).run(runnable);
    }

    /**
     * Runs Duck Scheduler builder as asynchronous with delay.
     *
     * @return Scheduler bukkit task id.
     */
    public static int runDelayedAsyncScheduler(@Nonnull Runnable runnable, int delay, @Nonnull TimeUnit delayType) {
        return new DuckScheduler(DuckScheduler.Type.ASYNC).after(delay, delayType).run(runnable);
    }

    /**
     * Runs Duck Scheduler builder as asynchronous for every declared time.
     *
     * @return Scheduler bukkit task id.
     */
    public static int runRepeatingAsyncScheduler(@Nonnull Runnable runnable, int repeatingDelay, @Nonnull TimeUnit repeatingDelayType) {
        return new DuckScheduler(DuckScheduler.Type.ASYNC).after(repeatingDelay, repeatingDelayType).run(runnable);
    }


    /*
    CHAIN
     */

    /**
     * Creates new Duck Chain.
     *
     * @return Duck Chain.
     */
    @Nonnull
    public static DuckChain newChain() {
        return new DuckChain();
    }


    /*
    EVENT
     */
    @Nonnull
    public static <T extends Event> DuckEvent<T> registerEvent(@Nonnull Class<T> eventClass) {
        return new DuckEvent<>(eventClass);
    }

    @Nonnull
    public static <T extends Event> DuckEvent<T> registerEvent(@Nonnull Class<T> eventClass, EventPriority priority) {
        return new DuckEvent<>(eventClass, priority);
    }


    /*
    PACKET
     */

    /**
     * Gets Duck Packet.
     *
     * @return Duck Packet.
     */
    public static DuckPacket getPacket() {
        return packet;
    }

    /**
     * Sets Duck Packet.
     *
     * @param packet Duck Packet.
     */
    public static void setPacket(@Nonnull DuckPacket packet) {
        Objects.requireNonNull(packet, "packet cannot be null!");
        DuckLibrary.packet = packet;
    }
}
