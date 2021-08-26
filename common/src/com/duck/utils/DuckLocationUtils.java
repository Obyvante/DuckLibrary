package com.duck.utils;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;
import java.util.Objects;

public final class DuckLocationUtils {

    //Local fields
    private static final DecimalFormat axisBaseFormat = new DecimalFormat("####################.#####");


    /*
    Serialization
     */

    /**
     * Converts location to string.
     *
     * @param location Location.
     * @return Location string.
     */
    @Nonnull
    public static String getAsString(@Nonnull Location location) {
        return getAsString(location, true, true);
    }

    /**
     * Converts location to string.
     *
     * @param location      Location.
     * @param include_world Should include world or not.
     * @return Location string.
     */
    @Nonnull
    public static String getAsString(@Nonnull Location location, boolean include_world) {
        return getAsString(location, include_world, true);
    }

    /**
     * Converts location to string.
     *
     * @param location          Location.
     * @param include_world     Should include world or not.
     * @param include_direction Should include direction or not.
     * @return Location string.
     */
    @Nonnull
    public static String getAsString(@Nonnull Location location, boolean include_world, boolean include_direction) {
        //Objects null check.
        Objects.requireNonNull(location, "location cannot be null!");

        //Initialize builder to build location string.
        StringBuilder location_string_builder = new StringBuilder();

        //Includes world if it is not null.
        if (include_world)
            location_string_builder.append(location.getWorld() != null ? location.getWorld().getName() + ":" : "");

        //Sets X, Y and Z location with deleting
        location_string_builder.append(axisBaseFormat.format(location.getX()))
                .append(":")
                .append(axisBaseFormat.format(location.getY())).append(":")
                .append(axisBaseFormat.format(location.getZ()));

        //Includes directions(yaw, pitch)
        if (include_direction) {
            location_string_builder.append(":")
                    .append(axisBaseFormat.format(location.getYaw()))
                    .append(":")
                    .append(axisBaseFormat.format(location.getPitch()));
        }

        //Build builder and return string.
        return location_string_builder.toString();
    }

    /**
     * Converts location string to bukkit location.
     *
     * @param location_string Location string.
     * @return Converted bukkit location from the string.
     */
    public static Location of(@Nonnull String location_string) {
        return of(location_string, false);
    }

    /**
     * Converts location string to bukkit location.
     *
     * @param location_string Location string.
     * @param randomWorld     Should use random world or not.
     * @return Converted bukkit location from the string.
     */
    public static Location of(@Nonnull String location_string, boolean randomWorld) {
        //Objects null check.
        Objects.requireNonNull(location_string, "location string cannot be null!");
        //If it is empty return null.
        if (location_string.isEmpty())
            return null;

        //Separate location string.
        String[] location_string_separate = location_string.split(":");

        //If the location string is broken, return null.
        if (location_string_separate.length < 3)
            return null;

        //Should we include yaw and pitch or not
        boolean direction_included = location_string_separate.length >= 5;

        //Should we include world or not
        boolean world_included = StringUtils.isNumeric(location_string_separate[0]);

        if (world_included) {
            //Initialize target world
            World world = Bukkit.getWorld(location_string_separate[0]);

            //Check if bukkit world is null or not.
            if (world == null)
                return null;

            return direction_included ?
                    new Location(world,
                            Double.parseDouble(location_string_separate[1]),
                            Double.parseDouble(location_string_separate[2]),
                            Double.parseDouble(location_string_separate[3]),
                            Float.parseFloat(location_string_separate[4]),
                            Float.parseFloat(location_string_separate[5]))
                    :
                    new Location(world,
                            Double.parseDouble(location_string_separate[1]),
                            Double.parseDouble(location_string_separate[2]),
                            Double.parseDouble(location_string_separate[3]));
        }

        //Initialize random world if random option is true.
        World world = randomWorld ? Bukkit.getWorlds().get(0) : null;

        //Return calculated location
        return direction_included ?
                new Location(world,
                        Double.parseDouble(location_string_separate[1]),
                        Double.parseDouble(location_string_separate[2]),
                        Double.parseDouble(location_string_separate[3]),
                        Float.parseFloat(location_string_separate[4]),
                        Float.parseFloat(location_string_separate[5]))
                :
                new Location(world,
                        Double.parseDouble(location_string_separate[1]),
                        Double.parseDouble(location_string_separate[2]),
                        Double.parseDouble(location_string_separate[3]));
    }


    /*
    MEASURE
     */

    /**
     * Measures distance between two location(A, B).
     *
     * @param A Location A.
     * @param B Location B.
     * @return Measured distance.
     */
    public static double measure(@Nonnull Location A, @Nonnull Location B) {
        //Objects null check.
        Objects.requireNonNull(A, "location(A) cannot be null!");
        Objects.requireNonNull(A.getWorld(), "world(A) cannot be null!");
        Objects.requireNonNull(B, "location(B) cannot be null!");
        Objects.requireNonNull(B.getWorld(), "world(B) cannot be null!");

        //If one of the two location is in different world, return max integer value.
        if (!A.getWorld().getUID().equals(B.getWorld().getUID()))
            return Integer.MAX_VALUE;

        //Calculates and returns
        return A.distance(B);
    }
}
