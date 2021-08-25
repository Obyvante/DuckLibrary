package com.duck.chain;

import com.duck.DuckLibrary;
import com.duck.scheduler.DuckScheduler;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public final class DuckChain {

    private final Queue<DuckScheduler> schedulerQueue = new LinkedList<>();

    private boolean done;
    private boolean running;

    /**
     * Adds synchronized runnable to the queue.
     *
     * @param runnable Runnable
     * @return Builder.
     */
    @Nonnull
    public DuckChain thenSync(@Nonnull Runnable runnable) {
        Objects.requireNonNull(runnable, "runnable cannot be null!");
        this.schedulerQueue.add(DuckLibrary.createSyncScheduler().setCachedRunnable(runnable));
        return this;
    }

    /**
     * Adds delayed synchronized runnable to the queue.
     *
     * @param runnable Runnable
     * @return Builder.
     */
    @Nonnull
    public DuckChain thenDelayedSync(@Nonnull Runnable runnable, int delay, @Nonnull TimeUnit delayType) {
        Objects.requireNonNull(runnable, "runnable cannot be null!");
        Objects.requireNonNull(delayType, "delay type cannot be null!");
        this.schedulerQueue.add(DuckLibrary.createSyncScheduler().after(delay, delayType).setCachedRunnable(runnable));
        return this;
    }

    /**
     * Adds asynchronous runnable to the queue.
     *
     * @param runnable Runnable
     * @return Builder.
     */
    @Nonnull
    public DuckChain thenAsync(@Nonnull Runnable runnable) {
        Objects.requireNonNull(runnable, "runnable cannot be null!");
        this.schedulerQueue.add(DuckLibrary.createAsyncScheduler().setCachedRunnable(runnable));
        return this;
    }

    /**
     * Adds delayed asynchronous runnable to the queue.
     *
     * @param runnable Runnable
     * @return Builder.
     */
    @Nonnull
    public DuckChain thenDelayedAsync(Runnable runnable, int delay, TimeUnit delayType) {
        Objects.requireNonNull(runnable, "runnable cannot be null!");
        Objects.requireNonNull(delayType, "delay type cannot be null!");
        this.schedulerQueue.add(DuckLibrary.createAsyncScheduler().after(delay, delayType).setCachedRunnable(runnable));
        return this;
    }

    /**
     * Gets if chain has finished or not.
     *
     * @return If chain has finished or not.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Gets if chain is running or not.
     *
     * @return If chain is running or not.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Starts chain.
     *
     * @return Builder.
     */
    @Nonnull
    public DuckChain start() {
        if (this.running || this.done)
            return this;

        DuckLibrary.createAsyncScheduler().run(() -> {
            //Local variables
            this.running = true;
            int currentTask = -100;
            //While loop until queue is empty.
            while (this.schedulerQueue.size() > 0) {
                //Checks if last task is running or not.
                if (Bukkit.getScheduler().isCurrentlyRunning(currentTask) || Bukkit.getScheduler().isQueued(currentTask))
                    continue;
                //Peek the next queue object if it is null or not.
                if (this.schedulerQueue.peek() == null)
                    return;

                //Runs cached task.
                currentTask = this.schedulerQueue.poll().runCached();
            }

            this.done = true;
            this.running = false;
        });
        return this;
    }
}
