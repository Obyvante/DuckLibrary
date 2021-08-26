package com.duck.utils;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * For random numbers, Duck Library uses "ThreadLocalRandom.current()" method.
 * With this method, we do not have to worry about sharing random seeds between multi-threading(different) tasks.
 */
public final class DuckNumberUtils {

    //Local fields
    private static DecimalFormat baseDecimalFormat = new DecimalFormat("###,###,###,###,###,###,###,###.#");

    /**
     * Gets base decimal format.
     *
     * @param format Decimal format.
     */
    public static void setBaseDecimalFormat(@Nonnull DecimalFormat format) {
        //Objects null check
        Objects.requireNonNull(format, "format cannot be null!");

        baseDecimalFormat = format;
    }


    /*
    BASE NUMBER UTILS
     */

    /**
     * Creates random number maximum of given number.
     *
     * @param maximum Maximum number.
     * @return Random number.
     */
    @SuppressWarnings("unchecked")
    @Nonnull
    public static <T extends Number> T random(@Nonnull T maximum, @Nonnull Class<T> numberClass) {
        //Objects null check
        Objects.requireNonNull(maximum, "maximum value cannot be null!");
        Objects.requireNonNull(numberClass, "number class cannot be null!");
        //Returns
        if (numberClass == int.class || numberClass == Integer.class)
            return (T) (Object) random(maximum.intValue());
        else if (numberClass == double.class || numberClass == Double.class)
            return (T) (Object) random(maximum.doubleValue());
        else if (numberClass == long.class || numberClass == Long.class)
            return (T) (Object) random(maximum.longValue());
        return (T) (Object) 0;
    }

    /**
     * Creates random number in range of minimum and maximum number.
     *
     * @param minimum Minimum number.
     * @param maximum Maximum number.
     * @return Random number.
     */
    @SuppressWarnings("unchecked")
    @Nonnull
    public static <T extends Number> T random(@Nonnull T minimum, @Nonnull T maximum, @Nonnull Class<T> numberClass) {
        //Objects null check
        Objects.requireNonNull(minimum, "minimum value cannot be null!");
        Objects.requireNonNull(maximum, "maximum value cannot be null!");
        Objects.requireNonNull(numberClass, "number class cannot be null!");
        //Returns
        if (numberClass == int.class || numberClass == Integer.class)
            return (T) (Object) random(minimum.intValue(), maximum.intValue());
        else if (numberClass == double.class || numberClass == Double.class)
            return (T) (Object) random(minimum.doubleValue(), maximum.doubleValue());
        else if (numberClass == long.class || numberClass == Long.class)
            return (T) (Object) random(minimum.longValue(), maximum.longValue());
        return (T) (Object) 0;
    }

    /**
     * Creates random integer maximum of given number.
     *
     * @param maximum Maximum integer.
     * @return Random integer.
     */
    public static int random(int maximum) {
        return ThreadLocalRandom.current().ints(0, maximum).findFirst().orElse(0);
    }

    /**
     * Creates random double maximum of given number.
     *
     * @param maximum Maximum double.
     * @return Random double.
     */
    public static double random(double maximum) {
        return ThreadLocalRandom.current().doubles(0.0D, maximum).findFirst().orElse(0.0D);
    }

    /**
     * Creates random long maximum of given number.
     *
     * @param maximum Maximum long.
     * @return Random long.
     */
    public static long random(long maximum) {
        return ThreadLocalRandom.current().longs(0L, maximum).findFirst().orElse(0L);
    }

    /**
     * Creates random integer in range of minimum and maximum number.
     *
     * @param minimum Minimum integer.
     * @param maximum Maximum integer.
     * @return Random integer.
     */
    public static int random(int minimum, int maximum) {
        return ThreadLocalRandom.current().ints(minimum, maximum).findFirst().orElse(0);
    }

    /**
     * Creates random double in range of minimum and maximum number.
     *
     * @param minimum Minimum double.
     * @param maximum Maximum double.
     * @return Random double.
     */
    public static double random(double minimum, double maximum) {
        return ThreadLocalRandom.current().doubles(minimum, maximum).findFirst().orElse(0.0D);
    }

    /**
     * Creates random long in range of minimum and maximum number.
     *
     * @param minimum Minimum long.
     * @param maximum Maximum long.
     * @return Random long.
     */
    public static long random(long minimum, long maximum) {
        return ThreadLocalRandom.current().longs(minimum, maximum).findFirst().orElse(0L);
    }


    /*
    CHANCE UTILS
     */

    /**
     * Generates random number between 0 and 100 then compare with the probability.
     * It is simple math for calculation "chance".
     *
     * @param probability Probability.
     * @return Pass it or not.
     */
    public static boolean chance(int probability) {
        return random(0, 100, int.class) <= probability;
    }


    /*
    FORMAT UTILS
     */

    /**
     * Formats integer as readable text.
     *
     * @param number Integer number.
     * @return Readable text.
     */
    @Nonnull
    public static String format(int number) {
        return baseDecimalFormat.format(number);
    }

    /**
     * Formats double as readable text.
     *
     * @param number Double.
     * @return Readable text.
     */
    @Nonnull
    public static String format(double number) {
        return baseDecimalFormat.format(number);
    }

    /**
     * Formats long as readable text.
     *
     * @param number Long.
     * @return Readable text.
     */
    @Nonnull
    public static String format(long number) {
        return baseDecimalFormat.format(number);
    }
}
