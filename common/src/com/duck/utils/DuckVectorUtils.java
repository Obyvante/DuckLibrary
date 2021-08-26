package com.duck.utils;

import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * It is mostly for under 1.9+ version since upper version already have this kind of features.
 */
public final class DuckVectorUtils {

    /*
    EULER ANGLE
     */

    /**
     * Converts euler angle to float array.
     *
     * @param eulerAngle Euler angle.
     * @return Floats array.
     */
    public static float[] floats(@Nonnull EulerAngle eulerAngle) {
        //Objects null check.
        Objects.requireNonNull(eulerAngle, "euler angle cannot be null!");

        //Creates float array and return it.
        return new float[]{
                (float) Math.toDegrees(eulerAngle.getX()),
                (float) Math.toDegrees(eulerAngle.getY()),
                (float) Math.toDegrees(eulerAngle.getZ())
        };
    }

    /**
     * Rotates vector around X axis.
     *
     * @param vector Vector.
     * @param angle  Angle.
     * @return Rotated vector.
     */
    @Nonnull
    public static Vector rotateAroundXAxis(@Nonnull Vector vector, double angle) {
        //Objects null check.
        Objects.requireNonNull(vector, "vector cannot be null!");

        //Converts angle to radians.
        angle = Math.toRadians(angle);

        //Converts angle to cos and sin.
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        //Calculates Y and Z axis.
        double y = vector.getY() * cos - vector.getZ() * sin;
        double z = vector.getY() * sin + vector.getZ() * cos;

        //Sets vector Y and Z axis.
        vector.setY(y);
        vector.setZ(z);

        //Returns calculated vector.
        return vector;
    }

    /**
     * Rotates vector around Y axis.
     *
     * @param vector Vector.
     * @param angle  Angle.
     * @return Rotated vector.
     */
    @Nonnull
    public static Vector rotateAroundYAxis(@Nonnull Vector vector, double angle) {
        //Objects null check.
        Objects.requireNonNull(vector, "vector cannot be null!");

        //Converts angle to radians.
        angle = -angle;
        angle = Math.toRadians(angle);

        //Converts angle to cos and sin.
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        //Calculates X and Z axis.
        double x = vector.getX() * cos + vector.getZ() * sin;
        double z = vector.getX() * -sin + vector.getZ() * cos;

        //Sets vector X and Z axis.
        vector.setX(x);
        vector.setZ(z);

        //Returns calculated vector.
        return vector;
    }

    /**
     * Rotates vector around Y axis.
     *
     * @param vector Vector.
     * @param angle  Angle.
     * @return Rotated vector.
     */
    @Nonnull
    public static Vector rotateAroundZAxis(@Nonnull Vector vector, double angle) {
        //Objects null check.
        Objects.requireNonNull(vector, "vector cannot be null!");

        //Converts angle to cos and sin.
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        //Calculates X and Z axis.
        double x = vector.getX() * cos - vector.getY() * sin;
        double y = vector.getX() * sin + vector.getY() * cos;

        //Sets vector X and Z axis.
        vector.setX(x);
        vector.setY(y);

        //Returns calculated vector.
        return vector;
    }
}
