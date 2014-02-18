package au.net.hal9000.heisenberg.units;

import java.io.Serializable;

/**
 * 3D point in space.
 */
public class Point3d implements Serializable, Cloneable {
    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /** comparison tolerance. */
    static final float TOLERANCE = 0.01f;

    /** x coord. */
    private double x = 0;
    /** y coord. */
    private double y = 0;
    /** z coord. */
    private double z = 0;

    /**
     * @return get the tolerance.
     */
    public static float getTolerance() {
        return TOLERANCE;
    }

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

    /**
     * 
     * @return get x coord.
     */
    public double getX() {
        return x;
    }

    /**
     * 
     * @param x
     *            set new x coord.
     */

    public void setX(double x) {
        this.x = x;
    }

    /**
     * 
     * @return get y coord.
     */
    public double getY() {
        return y;
    }

    /**
     * 
     * @param y
     *            set new y coord.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * 
     * @return get z coord.
     */
    public double getZ() {
        return z;
    }

    /**
     * 
     * @param z
     *            set new z coord.
     */
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("[%.2f, %.2f, %.2f]", x, y, z);
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

    /**
     * @param other
     *            other Point3d object.
     * @return delta (change in position) to other point.
     */
    public Point3d delta(Point3d other) {
        return new Point3d(x - other.x, y - other.y, z - other.z);
    }

    @Override
    public Point3d clone() {
        Point3d clone;
        try {
            clone = (Point3d) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        clone.x = this.x;
        clone.y = this.y;
        clone.z = this.z;
        return clone;
    }

    // Autogenerated
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> (prime + 1)));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> (prime + 1)));
        temp = Double.doubleToLongBits(z);
        result = prime * result + (int) (temp ^ (temp >>> (prime + 1)));
        return result;
    }

    // Autogenerated
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point3d other = (Point3d) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        return true;
    }

    /**
     * Return true each difference in x,y,z is less than tolerance.
     * 
     * @param other
     *            point we are comparing to.
     * @param tolerance
     *            amount, per axis, where we consider points are different.
     * @return true on equals.
     */
    public boolean equals(Point3d other, double tolerance) {
        return (Math.abs(x - other.getX()) < tolerance)
                && (Math.abs(y - other.getY()) < tolerance)
                && (Math.abs(z - other.getZ()) < tolerance);
    }

    /**
     * Apply movement delta to this 3D point.
     * 
     * @param delta
     *            amount of movement
     */
    public void applyDelta(Point3d delta) {
        x += delta.getX();
        y += delta.getY();
        z += delta.getZ();
    }

    /**
     * Create a new object with a sum of this object and the delta.
     * 
     * @param delta
     *            amount of movement
     * @return new object with delta applied.
     */
    public Point3d newWithDelta(Point3d delta) {
        return new Point3d(x + delta.getX(), y + delta.getY(), z + delta.getZ());
    }

    /**
     * Compute a point along a spline curve.
     * 
     * @param u
     *            The knots u[] should have been calculated before using this.
     * @param n
     *            There are n+1 control points.
     * @param t
     *            t is the degree, typically 3 or 4.
     * @param v
     *            v ranges from 0 to n-t+2, start of the curve to the end.
     * @param control
     *            control points.
     * @return new point along the spline curve.
     */
    public static Point3d splinePoint(int[] u, int n, int t, double v,
            Point3d[] control) {
        int k;
        double b;
        double x = 0;
        double y = 0;
        double z = 0;

        for (k = 0; k <= n; k++) {
            b = splineBlend(k, t, u, v);
            x += control[k].x * b;
            y += control[k].y * b;
            z += control[k].z * b;
        }
        return new Point3d(x, y, z);
    }

    /**
     * Calculate the blending value for the spline recursively. If the numerator
     * and denominator are 0 the result is 0.
     * 
     * @return bending value for the spline.
     */
    public static double splineBlend(int k, int t, int[] u, double v) {
        double value;

        if (t == 1) {
            if ((u[k] <= v) && (v < u[k + 1])) {
                value = 1;
            } else {
                value = 0;
            }
        } else {
            if ((u[k + t - 1] == u[k]) && (u[k + t] == u[k + 1])) {
                value = 0;
            } else if (u[k + t - 1] == u[k]) {
                value = (u[k + t] - v) / (u[k + t] - u[k + 1])
                        * splineBlend(k + 1, t - 1, u, v);
            } else if (u[k + t] == u[k + 1]) {
                value = (v - u[k]) / (u[k + t - 1] - u[k])
                        * splineBlend(k, t - 1, u, v);
            } else {
                value = (v - u[k]) / (u[k + t - 1] - u[k])
                        * splineBlend(k, t - 1, u, v) + (u[k + t] - v)
                        / (u[k + t] - u[k + 1])
                        * splineBlend(k + 1, t - 1, u, v);
            }
        }

        return value;
    }

    /**
     * Return the distance between two points.
     * 
     * @param p1
     *            first point.
     * @param p2
     *            second point.
     * @return vector length.
     */
    public static double vectorLength(Point3d p1, Point3d p2) {

        double x = p1.x - p2.x;
        double y = p1.y - p2.y;
        double z = p1.z - p2.z;

        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Set the length of a vector, if starting length was non-zero.
     * 
     * @param len
     *            new length of vector.
     */
    public void setVectorLength(double len) {
        this.normalise();
        x *= len;
        y *= len;
        z *= len;
    }

    /**
     * Calculate the length of a vector.
     * 
     * @return the length
     */
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Normalise a vector. Set length to 1 unit unless it has no lenght to start
     * with.
     */
    void normalise() {
        double length = this.length();
        if (length > 0) {
            x /= length;
            y /= length;
            z /= length;
        } else {
            x = 0;
            y = 0;
            z = 0;
        }
    }

    /**
     * Multiply a vector by a constant.
     * 
     * @param c
     *            multiplier.
     */
    public void vectorMul(double c) {
        x *= c;
        y *= c;
        z *= c;
    }

    /**
     * Rotate vectors around each axis Clockwise looking into the origin from
     * along the positive axis.
     * 
     * @param theta
     *            in radians.
     */
    public void rotateX(double theta) {
        double newY = y * Math.cos(theta) + z * Math.sin(theta);
        z = -y * Math.sin(theta) + z * Math.cos(theta);
        y = newY;
    }

    /**
     * Rotate vectors around each axis Clockwise looking into the origin from
     * along the positive axis.
     * 
     * @param theta
     *            in radians.
     */
    public void rotateY(double theta) {

        double newX = x * Math.cos(theta) - z * Math.sin(theta);
        z = x * Math.sin(theta) + z * Math.cos(theta);
        x = newX;
    }

    /**
     * Rotate vectors around each axis Clockwise looking into the origin from
     * along the positive axis.
     * 
     * @param theta
     *            in radians.
     */
    public void rotateZ(double theta) {
        double newX = x * Math.cos(theta) + y * Math.sin(theta);
        y = -x * Math.sin(theta) + y * Math.cos(theta);
        x = newX;
    }

}
