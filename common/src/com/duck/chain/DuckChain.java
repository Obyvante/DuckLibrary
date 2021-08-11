package com.duck.chain;

import com.duck.DuckLibrary;
import com.duck.scheduler.DuckScheduler;
import org.bukkit.Bukkit;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class DuckChain<T> {

    private final Queue<DuckScheduler> schedulerQueue = new LinkedList<>();

    private boolean done;
    private boolean running;

    public DuckChain<T> thenSync(Runnable runnable) {
        this.schedulerQueue.add(DuckLibrary.createSyncScheduler().setCachedRunnable(runnable));
        return this;
    }

    public DuckChain<T> thenDelayedSync(Runnable runnable, int delay, TimeUnit delayType) {
        this.schedulerQueue.add(DuckLibrary.createSyncScheduler().after(delay, delayType).setCachedRunnable(runnable));
        return this;
    }

    public <R> DuckChain<R> applyValue(R value) {
        return new DuckChain<>();
    }

    public boolean isDone() {
        return done;
    }

    public boolean isRunning() {
        return running;
    }

    public DuckChain<T> start() {
        if (this.running || this.done)
            return this;

        DuckLibrary.createAsyncScheduler()
                .run(() -> {
                    this.running = true;
                    int currentTask = -100;
                    while (this.schedulerQueue.size() > 0) {
                        if (Bukkit.getScheduler().isCurrentlyRunning(currentTask) || Bukkit.getScheduler().isQueued(currentTask))
                            continue;

                        if (this.schedulerQueue.peek() == null)
                            return;

                        DuckScheduler scheduler = this.schedulerQueue.poll();

                        currentTask = scheduler.run(scheduler.getCachedRunnable());
                    }

                    this.done = true;
                    this.running = false;
                });
        return this;
    }

}
