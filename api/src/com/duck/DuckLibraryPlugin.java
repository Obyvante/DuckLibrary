package com.duck;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DuckLibraryPlugin extends JavaPlugin {

    //Instance
    private static DuckLibraryPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        long ms = System.currentTimeMillis();

        try {
            this.initialize();
        } catch (Exception exception) {
            DuckLibrary.getLogger().error("Couldn't fetch bukkit protocol version!", exception);
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        //Test register
        Bukkit.getPluginManager().registerEvents(new DuckLibraryTest(), this);

        DuckLibrary.setStatus(true);

        DuckLibrary.getLogger().info("Successfully initialized in " + (System.currentTimeMillis() - ms) + " ms!");
    }

    private void initialize() throws Exception {
        DuckProtocolVersion protocolVersion = DuckProtocolVersion.findProtocolVersion(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]);

        //Sets protocol version.
        DuckLibrary.setProtocolVersion(protocolVersion);

        //Initializes protocol classes.
        if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_8_R3) {
            DuckLibrary.setMessageHandler(new DuckMessageHandler1_8_R3());
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_9_R2) {
            DuckLibrary.setMessageHandler(new DuckMessageHandler1_9_R2());
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_10_R1) {
            DuckLibrary.setMessageHandler(new DuckMessageHandler1_10_R1());
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_11_R1) {
            DuckLibrary.setMessageHandler(new DuckMessageHandler1_11_R1());
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_12_R1) {
            DuckLibrary.setMessageHandler(new DuckMessageHandler1_12_R1());
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_13_R2) {
            DuckLibrary.setMessageHandler(new DuckMessageHandler1_13_R2());
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_14_R1) {
            DuckLibrary.setMessageHandler(new DuckMessageHandler1_14_R1());
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_15_R1) {
            DuckLibrary.setMessageHandler(new DuckMessageHandler1_15_R1());
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_16_R3) {
            DuckLibrary.setMessageHandler(new DuckMessageHandler1_16_R3());
        } else if (protocolVersion == DuckProtocolVersion.PROTOCOL_1_17_R1) {
            DuckLibrary.setMessageHandler(new DuckMessageHandler1_17_R1());
        }

        //Inform about protocol version on console
        DuckLibrary.getLogger().info("Bukkit protocol version " + protocolVersion.getProtocol() + " is initialized.");
    }

    /**
     * Gets Duck Library instance.
     *
     * @return Duck Library.
     */
    public static DuckLibraryPlugin getInstance() {
        if (!DuckLibrary.isReady())
            DuckLibrary.getLogger().error("Tried to fetch data from Duck Library during initializing.");
        return instance;
    }
}
