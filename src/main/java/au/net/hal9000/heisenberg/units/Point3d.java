package au.net.hal9000.heisenberg.units;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 3D point in space.
 */
public class Point3d implements Serializable, Cloneable {
    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /** comparison tolerance. */
    static final float TOLERANCE = 0.01f;

    /** zero. */
    private static final Point3d ZERO = new Point3d(0, 0, 0);

    /** x coord. */
    private double x = 0;
    /** y coord. */
    private double y = 0;
    /** z coord. */
    private double z = 0;

    /**
     * Constructor.
     * 
     * @param x
     *            x coord
     * @param y
     *            y coord
     * @param z
     *            z coord
     */
    public Point3d(double x, double y, double z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructor - no offset.
     */
    public Point3d() {
        this(0, 0, 0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public static float getTolerance() {
        return TOLERANCE;
    }

    /**
     * @param other
     *            other Point3d object.
     * @return distance (modulus) to other point.
     */
    public double distance(Point3d other) {
        return Math.sqrt(Math.pow(x - other.x, 2.0)
                + Math.pow(y - other.y, 2.0) + Math.pow(z - other.z, 2.0));
    }

    @Override
    public Point3d clone() {
        return new Point3d(x, y, z);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }

    /**
     * Is this 3d point close enough to the other 3d point to be considered
     * equal?
     * 
     * @param other
     *            other point
     * @param distance
     *            how close to be in order to be considered equal
     * @return true if equal
     */
    public boolean equals(Point3d other, double distance) {
        return distance(other) <= distance;
    }

    /**
     * Is this 3d point close enough to the other 3d point to be considered
     * equal?
     * 
     * @param other
     *            other point
     * @return true if equal
     */
    public boolean equals(Point3d other) {
        return equals(other, TOLERANCE);
    }

    /**
     * @return the hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] { x, y, z });
    }

    /**
     * Apply movement delta to this 3D point.
     * 
     * @param delta
     *            amount of movement
     */
    public void applyDelta(Point3d delta) {
        this.x += delta.getX();
        this.y += delta.getY();
        this.z += delta.getZ();
    }

    /**
     * Create a new object with a sum of this object and the delta.
     * 
     * @param delta
     *            amount of movement
     * @return new object with delta applied.
     */
    public Point3d newWithDelta(Point3d delta) {
        double x = this.x + delta.getX();
        double y = this.y + delta.getY();
        double z = this.z + delta.getZ();
        return new Point3d(x, y, z);
    }

    /**
     * @return the size AKA modulus, absolute value.
     */
    public double size() {
        return this.distance(ZERO);
    }
}
