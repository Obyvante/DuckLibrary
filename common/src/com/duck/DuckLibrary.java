package com.duck;

import com.duck.logger.DuckLogger;
import com.duck.message.DuckMessageHandler;

public class DuckLibrary {

    //Protocol
    private static DuckProtocolVersion protocolVersion;

    //Logger
    private static final DuckLogger logger = new DuckLogger();

    //Commons
    private static DuckMessageHandler messageHandler;

    //Initialize
    private static boolean initialized;

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
}
