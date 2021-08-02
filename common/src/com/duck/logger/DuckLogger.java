package com.duck.logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.PrintWriter;
import java.io.StringWriter;

public class DuckLogger {

    public static final String PREFIX = "[Duck Library]";

    /**
     * Sends information message to bukkit console.
     * It supports bukkit color codes.
     *
     * @param message Information message.
     */
    public void info(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&f" + PREFIX + " " + message));
    }

    /**
     * Sends warning message to bukkit console.
     * It supports bukkit color codes.
     *
     * @param message Information message.
     */
    public void warn(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e" + PREFIX + " " + message));
    }

    /**
     * Sends error message to bukkit console.
     * It supports bukkit color codes.
     *
     * @param message Information message.
     */
    public void error(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + PREFIX + " " + message));
    }

    /**
     * Sends error message to bukkit console.
     * It supports bukkit color codes.
     *
     * @param message   Information message.
     * @param exception Exception.
     */
    public void error(String message, Exception exception) {
        StringWriter exception_writer = new StringWriter();
        exception.printStackTrace(new PrintWriter(exception_writer));

        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4" + PREFIX + " " + message + ": &c" + exception_writer));
    }

    /**
     * Sends information message to bukkit console.
     * It supports bukkit color codes.
     *
     * @param message Information message.
     */
    public void debug(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + PREFIX + " " + message));
    }

}
