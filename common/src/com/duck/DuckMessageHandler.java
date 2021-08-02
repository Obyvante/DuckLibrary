package com.duck;

import org.bukkit.entity.Player;

public interface DuckMessageHandler {

    /**
     * Shows bukkit title to everyone.
     *
     * @param title             Title.
     * @param subtitle          Subtitle.
     * @param show_up_duration  Show up duration.
     * @param stay_duration     Stay duration.
     * @param show_out_duration Show out duration.
     */
    void sendTitle(String title, String subtitle, int show_up_duration, int stay_duration, int show_out_duration);

    /**
     * Shows bukkit title to player.
     *
     * @param player            Player.
     * @param title             Title.
     * @param subtitle          Subtitle.
     * @param show_up_duration  Show up duration.
     * @param stay_duration     Stay duration.
     * @param show_out_duration Show out duration.
     */
    void sendTitle(Player player, String title, String subtitle, int show_up_duration, int stay_duration, int show_out_duration);

    /**
     * Sends bukkit action bar to everyone.
     *
     * @param message Message.
     */
    void sendActionBar(String message);

    /**
     * Sends bukkit action bar to everyone.
     *
     * @param player  Player.
     * @param message Message.
     */
    void sendActionBar(Player player, String message);

}
