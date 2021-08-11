package com.duck;

import com.duck.chain.DuckChain;
import com.duck.logger.DuckLogger;
import com.duck.message.DuckMessageHandler;
import com.duck.scheduler.DuckScheduler;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;

public class DuckLibrary {

    //Java plugin
    private static JavaPlugin instance;

    //Protocol
    private static DuckProtocolVersion protocolVersion;

    //Logger
    private static final DuckLogger logger = new DuckLogger();

    //Commons
    private static DuckMessageHandler messageHandler;

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
    public static void setInstance(JavaPlugin instance) {
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

    public static void setProtocolVersion(DuckProtocolVersion protocolVersion) {
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
    public static void setMessageHandler(DuckMessageHandler messageHandler) {
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
    public static int runSyncScheduler(Runnable runnable) {
        return new DuckScheduler(DuckScheduler.Type.SYNC).run(runnable);
    }

    /**
     * Runs Duck Scheduler builder as synchronous with delay.
     *
     * @return Scheduler bukkit task id.
     */
    public static int runDelayedSyncScheduler(Runnable runnable, int delay, TimeUnit delayType) {
        return new DuckScheduler(DuckScheduler.Type.SYNC).after(delay, delayType).run(runnable);
    }

    /**
     * Runs Duck Scheduler builder as synchronous for every declared time.
     *
     * @return Scheduler bukkit task id.
     */
    public static int runRepeatingSyncScheduler(Runnable runnable, int repeatingDelay, TimeUnit repeatingDelayType) {
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
    public static int runAsyncScheduler(Runnable runnable) {
        return new DuckScheduler(DuckScheduler.Type.ASYNC).run(runnable);
    }

    /**
     * Runs Duck Scheduler builder as asynchronous with delay.
     *
     * @return Scheduler bukkit task id.
     */
    public static int runDelayedAsyncScheduler(Runnable runnable, int delay, TimeUnit delayType) {
        return new DuckScheduler(DuckScheduler.Type.ASYNC).after(delay, delayType).run(runnable);
    }

    /**
     * Runs Duck Scheduler builder as asynchronous for every declared time.
     *
     * @return Scheduler bukkit task id.
     */
    public static int runRepeatingAsyncScheduler(Runnable runnable, int repeatingDelay, TimeUnit repeatingDelayType) {
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
    public static DuckChain<Void> createChain() {
        return new DuckChain<>();
    }

    /**
     * Creates new Duck Chain for declared value.
     *
     * @param value Value.
     * @param <T>   Value type.
     * @return Duck Chain
     */
    @Nonnull
    public static <T> DuckChain<T> createChain(T value) {
        return new DuckChain<>();
    }
}
