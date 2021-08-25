package com.duck.packet;

import java.util.UUID;

public interface DuckPacketHandler {

    /**
     * Gets Duck Packet Handler player unique id.
     *
     * @return Duck Packet Handler player unique id.
     */
    UUID getId();

    /**
     * Deletes Duck Packet Handler.
     */
    void delete();

}
