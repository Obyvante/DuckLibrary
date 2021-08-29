package com.duck.scheduler;

import com.duck.DuckLibrary;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class DuckScheduler {

    /**
     * Scheduler types.
     */
    public enum Type {
        SYNC,
        ASYNC
    }

    private final Type type;

    private int delay;
    private TimeUnit delayType;

    private int repeatingDelay;
    private TimeUnit repeatingDelayType;

    private Runnable cachedRunnable;

    private int bukkitTaskId = -1;

    public DuckScheduler(@Nonnull Type type) {
        //Objects null control
        Objects.requireNonNull(type, "type cannot be null!");
        //Root construction
        this.type = type;
    }

    public DuckScheduler(@Nonnull Type type, int delay, int repeatingDelay, @Nonnull TimeUnit repeatingDelayType) {
        //Objects null control
        Objects.requireNonNull(type, "type cannot be null!");
        Objects.requireNonNull(repeatingDelayType, "repeating delay type cannot be null!");
        //Root construction
        this.type = type;
        this.delay = delay;
        this.repeatingDelay = repeatingDelay;
        this.repeatingDelayType = repeatingDelayType;
    }

    /**
     * Runs scheduler after declared time.
     *
     * @param delay     Scheduler after delay.
     * @param delayType Scheduler after delay type.
     * @return Duck Scheduler builder.
     */
    @Nonnull
    public DuckScheduler after(int delay, @Nonnull TimeUnit delayType) {
        Objects.requireNonNull(delayType, "delay type cannot be null!");
        this.delay = delay;
        this.delayType = delayType;
        return this;
    }

    /**
     * Runs scheduler every declared time.
     *
     * @param repeatingDelay     Scheduler repeating time.
     * @param repeatingDelayType Scheduler repeating time type.
     * @return Duck Scheduler builder.
     */
    @Nonnull
    public DuckScheduler every(int repeatingDelay, @Nonnull TimeUnit repeatingDelayType) {
        Objects.requireNonNull(repeatingDelayType, "repeating delay type cannot be null!");
        this.repeatingDelay = repeatingDelay;
        this.repeatingDelayType = repeatingDelayType;
        return this;
    }

    /**
     * Gets cached runnable.
     *
     * @return Runnable.
     */
    public Runnable getCachedRunnable() {
        return cachedRunnable;
    }

    /**
     * Sets cached runnable
     *
     * @param cachedRunnable Runnable.
     * @return Duck scheduler builder.
     */
    @Nonnull
    public DuckScheduler setCachedRunnable(@Nonnull Runnable cachedRunnable) {
        Objects.requireNonNull(cachedRunnable, "cached runnable cannot be null!");
        this.cachedRunnable = cachedRunnable;
        return this;
    }

    /**
     * Runs cached Duck Scheduler.
     *
     * @return Bukkit task id.
     */
    public int runCached() {
        return this.run(this.cachedRunnable);
    }

    /**
     * If there is an ongoing task, it will stop it.
     */
    public void stop() {
        if (this.bukkitTaskId == -1)
            return;
        Bukkit.getScheduler().cancelTask(this.bukkitTaskId);
    }

    /**
     * Runs configured Duck Scheduler.
     *
     * @param runnable Runnable.
     * @return Bukkit task id.
     */
    public int run(@Nonnull Runnable runnable) throws IllegalArgumentException {
        Objects.requireNonNull(runnable, "runnable cannot be null!");
        long delay = this.delayType == null ? 0 : Math.max(this.delayType.toMillis(this.delay) / 50, 0);
        long repeating_delay = this.repeatingDelayType == null ? 0 : Math.max(this.repeatingDelayType.toMillis(this.repeatingDelay) / 50, 0);

        int task_id;
        if (this.type == Type.SYNC) {
            if (repeating_delay != 0)
                task_id = Bukkit.getScheduler().scheduleSyncRepeatingTask(DuckLibrary.getInstance(), runnable, delay, repeating_delay);
            else if (delay != 0)
                task_id = Bukkit.getScheduler().scheduleSyncDelayedTask(DuckLibrary.getInstance(), runnable, delay);
            else
                task_id = Bukkit.getScheduler().runTask(DuckLibrary.getInstance(), runnable).getTaskId();
        } else {
            if (repeating_delay != 0)
                task_id = Bukkit.getScheduler().runTaskTimerAsynchronously(DuckLibrary.getInstance(), runnable, delay, repeating_delay).getTaskId();
            else if (delay != 0)
                task_id = Bukkit.getScheduler().runTaskLaterAsynchronously(DuckLibrary.getInstance(), runnable, delay).getTaskId();
            else
                task_id = Bukkit.getScheduler().runTaskAsynchronously(DuckLibrary.getInstance(), runnable).getTaskId();
        }
        this.bukkitTaskId = task_id;
        return task_id;
    }
}
