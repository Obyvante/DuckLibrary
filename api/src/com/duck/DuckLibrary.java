package com.duck;

import com.duck.logger.DuckLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DuckLibrary extends JavaPlugin {

    //Instance
    private static DuckLibrary instance;

    //Protocol
    private static DuckProtocolVersion protocolVersion;

    //Logger
    private static final DuckLogger duckLogger = new DuckLogger();

    //Commons
    private static DuckMessageHandler messageHandler;

    //Initialize
    private static boolean initialized;

    @Override
    public void onEnable() {
        instance = this;

        long ms = System.currentTimeMillis();

        try {
            this.initialize();
        } catch (Exception exception) {
            duckLogger.error("Couldn't fetch bukkit protocol version!", exception);
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        //Test register
        Bukkit.getPluginManager().registerEvents(new DuckLibraryTest(), this);

        initialized = true;

        duckLogger.info("Successfully initialized in " + (System.currentTimeMillis() - ms) + " ms!");
    }

    private void initialize() throws Exception {
        protocolVersion = DuckProtocolVersion.findProtocolVersion(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]);

        if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_8_R3) {
            messageHandler = new DuckMessageHandler1_8_R3();
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_9_R2) {
            messageHandler = new DuckMessageHandler1_9_R2();
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_10_R1) {
            messageHandler = new DuckMessageHandler1_10_R1();
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_11_R1) {
            messageHandler = new DuckMessageHandler1_11_R1();
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_12_R1) {
            messageHandler = new DuckMessageHandler1_12_R1();
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_13_R2) {
            messageHandler = new DuckMessageHandler1_13_R2();
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_14_R1) {
            messageHandler = new DuckMessageHandler1_14_R1();
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_15_R1) {
            messageHandler = new DuckMessageHandler1_15_R1();
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_16_R3) {
            messageHandler = new DuckMessageHandler1_16_R3();
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_17_R1) {
            messageHandler = new DuckMessageHandler1_17_R1();
        }

        duckLogger.info("Bukkit protocol version " + protocolVersion.getProtocol() + " is initialized.");
    }

    /**
     * Gets Duck Library instance.
     *
     * @return Duck Library.
     */
    public static DuckLibrary getInstance() {
        if (!initialized)
            duckLogger.error("Tried to fetch data from Duck Library during initializing.");
        return instance;
    }

    /**
     * Gets duck protocol version.
     *
     * @return Duck protocol version.
     */
    public static DuckProtocolVersion getProtocolVersion() {
        if (!initialized)
            duckLogger.error("Tried to fetch data from Duck Library during initializing.");
        return protocolVersion;
    }

    /**
     * Gets duck message handler.
     *
     * @return Duck message handler.
     */
    public static DuckMessageHandler getMessageHandler() {
        if (!initialized)
            duckLogger.error("Tried to fetch data from Duck Library during initializing.");
        return messageHandler;
    }

    /**
     * Gets duck logger.
     *
     * @return Duck logger.
     */
    public static DuckLogger getDuckLogger() {
        return duckLogger;
    }

    /**
     * Gets if Duck Library is ready to use or not.
     *
     * @return If Duck Library is ready to use or not.
     */
    public static boolean isReady() {
        return initialized;
    }
}
