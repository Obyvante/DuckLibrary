package com.duck.event;

import com.duck.DuckLibrary;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.EventExecutor;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public final class DuckEvent<T extends Event> implements Listener, EventExecutor {

    /**
     * Event enum filters.
     */
    public enum Filter {
        IGNORE_CANCELLED, //Events; Cancellable...

        IGNORE_DISALLOWED_LOGIN, //Events; PlayerLoginEvent
        IGNORE_DISALLOWED_PRE_LOGIN, //Events; AsyncPlayerPreLoginEvent

        IGNORE_FILTERS_FOR_LIMIT
    }

    //Root event
    private final Class<T> eventClass;
    private EventPriority priority = EventPriority.NORMAL;

    //Filters
    private final Collection<Filter> filters = new ArrayList<>();
    private final Collection<Function<T, Boolean>> functionFilters = new ArrayList<>();

    //Limit
    private int usageLimit;
    private int usage;

    //Expire
    private int expire;
    private TimeUnit expireUnit;
    private int expireTaskId = -1;

    //Consumer
    private Consumer<? super T> consumer;

    //Checks
    private boolean unregistered;

    public DuckEvent(@Nonnull Class<T> eventClass) {
        Objects.requireNonNull(eventClass, "event class cannot be null!");
        this.eventClass = eventClass;
    }

    public DuckEvent(@Nonnull Class<T> eventClass, @Nonnull EventPriority priority) {
        Objects.requireNonNull(eventClass, "event class cannot be null!");
        Objects.requireNonNull(priority, "priority cannot be null!");
        this.eventClass = eventClass;
        this.priority = priority;
    }

    /**
     * Sets bukkit event priority.
     *
     * @param priority Event priority.
     * @return Builder.
     */
    @Nonnull
    public DuckEvent<T> priority(@Nonnull EventPriority priority) {
        Objects.requireNonNull(priority, "priority cannot be null!");
        this.priority = priority;
        return this;
    }

    /**
     * Adds new event filter to the list.
     *
     * @param filter Event filter.
     * @return Builder.
     */
    @Nonnull
    public DuckEvent<T> filter(@Nonnull Filter... filter) {
        Objects.requireNonNull(filter, "filter cannot be null!");
        for (Filter loop_filter : filter) {
            if (this.filters.contains(loop_filter))
                continue;
            this.filters.add(loop_filter);
        }
        return this;
    }

    /**
     * Adds new functional event filter to the list.
     *
     * @param function_filter Functional event filter.
     * @return Builder.
     */
    @Nonnull
    public DuckEvent<T> filter(@Nonnull Function<T, Boolean> function_filter) {
        Objects.requireNonNull(function_filter, "function filter cannot be null!");
        if (this.functionFilters.contains(function_filter))
            return this;
        this.functionFilters.add(function_filter);
        return this;
    }

    /**
     * Sets usage limit.
     *
     * @param usageLimit Usage limit.
     * @return Builder.
     */
    @Nonnull
    public DuckEvent<T> limit(int usageLimit) {
        this.usageLimit = usageLimit;
        return this;
    }

    /**
     * Sets expire duration and time unit.
     *
     * @param expire     Expire duration.
     * @param expireUnit Expire time unit.
     * @return Builder.
     */
    @Nonnull
    public DuckEvent<T> expire(int expire, @Nonnull TimeUnit expireUnit) {
        Objects.requireNonNull(expireUnit, "expire unit cannot be null!");
        this.expire = expire;
        this.expireUnit = expireUnit;
        return this;
    }

    /**
     * Events consume action.
     *
     * @param consumer Root function.
     */
    public void consume(@Nonnull Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer, "consumer cannot be null!");
        this.consumer = consumer;
        //Register
        this.register();
    }

    /**
     * Registers Duck Event as a bukkit listener and event executor.
     */
    private void register() {
        //Register bukkit listener and executor.
        Bukkit.getPluginManager().registerEvent(this.eventClass, this, this.priority, this, DuckLibrary.getInstance(), false);

        //Expire handler
        if (this.expireUnit != null)
            this.expireTaskId = DuckLibrary.runDelayedSyncScheduler(this::unregister, this.expire, this.expireUnit);
    }

    /**
     * Registers Duck Event as a bukkit listener and event executor.
     */
    private void unregister() {
        //If it is already unregistered, we do not want to use it, right?
        if (this.unregistered)
            return;
        //Register bukkit listener and executor.
        HandlerList.unregisterAll(this);
        //Sets check
        this.unregistered = true;
        //Task control
        if (this.expireTaskId != 1)
            Bukkit.getScheduler().cancelTask(this.expireTaskId);
    }

    /**
     * Bukkit event executor execute override.
     *
     * @param listener Event listener.
     * @param event    Bukkit event.
     */
    @Override
    public void execute(@Nonnull Listener listener, @Nonnull Event event) {
        //Objects control if somewhat executes as a null
        Objects.requireNonNull(listener, "listener cannot be null! (Bukkit error)");
        Objects.requireNonNull(event, "event cannot be null! (Bukkit error)");
        //If it is already unregistered, we do not want to use it, right?
        if (this.unregistered)
            return;
        if (this.filters.contains(Filter.IGNORE_FILTERS_FOR_LIMIT) && this.usageLimit != 0 && this.usage++ >= this.usageLimit) {
            this.unregister();
            return;
        }
        //Checks cancelled.
        if (this.filters.contains(Filter.IGNORE_CANCELLED)
                && event instanceof Cancellable
                && ((Cancellable) event).isCancelled())
            return;
        //Checks disallowed login.
        if (this.filters.contains(Filter.IGNORE_DISALLOWED_LOGIN)
                && event instanceof PlayerLoginEvent
                && ((PlayerLoginEvent) event).getResult() != PlayerLoginEvent.Result.ALLOWED)
            return;
        //Checks disallowed pre login.
        if (this.filters.contains(Filter.IGNORE_DISALLOWED_PRE_LOGIN)
                && event instanceof AsyncPlayerPreLoginEvent
                && ((AsyncPlayerPreLoginEvent) event).getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED)
            return;

        //Checks functional filters.
        boolean should_continue = true;
        for (Function<T, Boolean> function_filter : this.functionFilters) {
            if (function_filter.apply((T) event))
                continue;
            should_continue = false;
            break;
        }
        //If any of the functional filters false, no need to continue.
        if (!should_continue)
            return;

        //Consume event.
        this.consumer.accept((T) event);

        //Usage limit check.
        if (!this.filters.contains(Filter.IGNORE_FILTERS_FOR_LIMIT) && this.usageLimit != 0 && this.usage++ >= this.usageLimit)
            this.unregister();
    }
}
