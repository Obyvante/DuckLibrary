package com.duck;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;

public enum DuckProtocolVersion {
    PROTOCOL_1_8_R3("v1_8_R3"),
    PROTOCOL_1_9_R2("v1_9_R2"),
    PROTOCOL_1_10_R1("v1_10_R1"),
    PROTOCOL_1_11_R1("v1_11_R1"),
    PROTOCOL_1_12_R1("v1_12_R1"),
    PROTOCOL_1_13_R2("v1_13_R2"),
    PROTOCOL_1_14_R1("v1_14_R1"),
    PROTOCOL_1_15_R1("v1_15_R1"),
    PROTOCOL_1_16_R3("v1_16_R3"),
    PROTOCOL_1_17_R1("v1_17_R1");

    private final String protocol;

    DuckProtocolVersion(@Nonnull String protocol) {
        Objects.requireNonNull(protocol, "protocol cannot be null!");
        this.protocol = protocol;
    }

    /**
     * Gets protocol bukkit version.
     *
     * @return Protocol bukkit version.
     */
    @Nonnull
    public String getProtocol() {
        return protocol;
    }

    /**
     * Finds match protocol version.
     *
     * @param version Protocol version string.
     * @return Duck protocol version.
     */
    public static DuckProtocolVersion findProtocolVersion(@Nonnull String version) {
        Objects.requireNonNull(version, "version string cannot be null!");
        return Arrays.stream(values())
                .filter(duckProtocolVersion -> duckProtocolVersion.getProtocol().equals(version))
                .findFirst()
                .orElse(null);
    }
}
