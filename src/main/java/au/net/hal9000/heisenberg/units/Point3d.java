package au.net.hal9000.heisenberg.units;

import java.io.Serializable;

/**
 * 3D point in space.
 */
public class Point3d implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    static final float TOLERANCE = 0.01f;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    /**
     * Constructor - no offset
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

    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distance(Point3d other) {
        return Math.sqrt(Math.pow(x - other.x, 2.0)
                + Math.pow(y - other.y, 2.0) + Math.pow(z - other.z, 2.0));
    }

    public Point3d clone() {
        return new Point3d(x, y, z);
    }
    
    public String toString(){
        return "[" + x + ", "+ y + ", "+ z + "]";
    }

    /**
     * Is this 3d point close enough to the other 3d point to be considered equal?
     * @param other other point
     * @param distance how close to be in order to be considered equal
     * @return true if equal
     */
    public boolean equals(Point3d other, double distance){
        return distance(other) <= distance;        
    }

    /**
     * Is this 3d point close enough to the other 3d point to be considered equal?
     * @param other other point
     * @return true if equal
     */
    public boolean equals(Point3d other){
        return equals(other,TOLERANCE);        
    }
    
}
