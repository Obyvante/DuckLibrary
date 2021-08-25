package com.duck.cooldown;

import com.duck.DuckLibrary;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

/**
 * Pretty basic cooldown map integration with extended HashMap.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public final class CooldownMap<K, V> extends HashMap<K, V> {

    private boolean status = true;
    private final int bukkitTaskId;

    /**
     * Creates hash map with declared duration and time unit.
     *
     * @param duration Duration.
     * @param unit     Time unit.
     */
    public CooldownMap(int duration, @Nonnull TimeUnit unit) {
        Objects.requireNonNull(unit, "time unit cannot be null!");
        //Creates bukkit task with delay of duration.
        this.bukkitTaskId = DuckLibrary.runDelayedSyncScheduler(() -> {
            //Change the status of the map
            this.status = false;
            //Clears the map
            this.clear();
        }, duration, unit);
    }

    @Override
    public V put(K key, V value) {
        if (this.hasExpired())
            return null;
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (this.hasExpired())
            return;
        super.putAll(m);
    }

    @Override
    public V remove(Object key) {
        if (this.hasExpired())
            return null;
        return super.remove(key);
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        if (this.hasExpired())
            return null;
        return super.getOrDefault(key, defaultValue);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (this.hasExpired())
            return null;
        return super.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        if (this.hasExpired())
            return false;
        return super.remove(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        if (this.hasExpired())
            return false;
        return super.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        if (this.hasExpired())
            return null;
        return super.replace(key, value);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        if (this.hasExpired())
            return null;
        return super.merge(key, value, remappingFunction);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        if (this.hasExpired())
            return;
        super.replaceAll(function);
    }

    /**
     * Gets if the cooldown map has expired or not.
     *
     * @return If the cooldown map has expired or not.
     */
    public boolean hasExpired() {
        return !this.status;
    }

    /**
     * Cancels cooldown map.
     */
    public void cancel() {
        //If the cooldown map has already expired, we do not want to touch it again.
        if (!this.status)
            return;
        //Sets status false if developer wants to check if it is expired or not.
        this.status = false;
        //Cancels existed bukkit task.
        if (this.bukkitTaskId != 0)
            Bukkit.getScheduler().cancelTask(this.bukkitTaskId);
    }
}
