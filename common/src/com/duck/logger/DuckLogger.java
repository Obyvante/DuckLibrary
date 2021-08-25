package com.duck.logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.annotation.Nonnull;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

public final class DuckLogger {

    public static final String PREFIX = "[Duck Library]";

    /**
     * Sends information message to bukkit console.
     * It supports bukkit color codes.
     *
     * @param message Information message.
     */
    public void info(@Nonnull String message) {
        Objects.requireNonNull(message, "message cannot be null!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&f" + PREFIX + " " + message));
    }

    /**
     * Sends warning message to bukkit console.
     * It supports bukkit color codes.
     *
     * @param message Information message.
     */
    public void warn(@Nonnull String message) {
        Objects.requireNonNull(message, "message cannot be null!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e" + PREFIX + " " + message));
    }

    /**
     * Sends error message to bukkit console.
     * It supports bukkit color codes.
     *
     * @param message Information message.
     */
    public void error(@Nonnull String message) {
        Objects.requireNonNull(message, "message cannot be null!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + PREFIX + " " + message));
    }

    /**
     * Sends error message to bukkit console.
     * It supports bukkit color codes.
     *
     * @param message   Information message.
     * @param exception Exception.
     */
    public void error(@Nonnull String message, @Nonnull Exception exception) {
        Objects.requireNonNull(message, "message cannot be null!");
        Objects.requireNonNull(exception, "exception cannot be null!");
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
    public void debug(@Nonnull String message) {
        Objects.requireNonNull(message, "message cannot be null!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + PREFIX + " " + message));
    }

}
