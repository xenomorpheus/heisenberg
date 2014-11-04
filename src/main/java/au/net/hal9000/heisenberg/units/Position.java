package au.net.hal9000.heisenberg.units;

import javax.persistence.Embeddable;

@Embeddable
public class Position {

    public static final double DEFAULT_AXIS_TOLERANCE = 0.00001;
    private double xPos;
    private double yPos;
    private double zPos;

    // Constructors
    /**
     * 3D Constructor.
     * 
     * @param xPos
     *            x position.
     * @param yPos
     *            y position.
     * @param zPos
     *            y position.
     */
    public Position(double xPos, double yPos, double zPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }

    /**
     * 2D Constructor.
     * 
     * @param xPos
     *            x position.
     * @param yPos
     *            y position.
     */
    public Position(double xPos, double yPos) {
        this(xPos, yPos, 0);
    }

    /**
     * Constructor.
     * 
     * @param other
     *            position to copy.
     */
    public Position(Position other) {
        this(other.getX(), other.getY(), other.getZ());
    }

    /**
     * Constructor. At origin.
     */
    public Position() {
        this(0, 0, 0);
    }

    // Getters and Setters
    /**
     * get x position.
     * 
     * @return x position
     */
    public double getX() {
        return xPos;
    }

    /**
     * Set x position.
     * 
     * @param xPos
     *            new x position.
     */
    public void setX(double xPos) {
        this.xPos = xPos;
    }

    /**
     * get y position.
     * 
     * @return y position
     */
    public double getY() {
        return yPos;
    }

    /**
     * Set y position.
     * 
     * @param yPos
     *            new y position.
     */
    public void setY(double yPos) {
        this.yPos = yPos;
    }

    /**
     * get z position.
     * 
     * @return z position
     */
    public double getZ() {
        return zPos;
    }

    /**
     * Set z position.
     * 
     * @param zPos
     *            new z position.
     */
    public void setZ(double zPos) {
        this.zPos = zPos;
    }

    // Misc
    /**
     * Change the position object by the delta.
     * 
     * @param delta
     *            the amount to change the local position by.
     */
    public void applyDelta(Position delta) {
        xPos += delta.getX();
        yPos += delta.getY();
        zPos += delta.getZ();
    }

    /**
     * Return a new Position object that is the current Position, plus argument.
     * 
     * @param delta
     *            Position to add.
     * @return Return a new Position object that is the current Position, plus
     *         argument.
     */
    public Position add(Position delta) {
        return new Position(xPos + delta.getX(), yPos + delta.getY(), zPos
                + delta.getZ());
    }

    /**
     * Return a new Position object that is the current Position, minus
     * argument.
     * 
     * @param other
     *            Position to minus.
     * @return Return a new Position object that is the current Position, minus
     *         argument.
     */
    public Position subtract(Position other) {
        return new Position(xPos - other.getX(), yPos - other.getY(), zPos
                - other.getZ());
    }

    /**
     * The length (AKA distance, magnitude) of this position from origin.
     * 
     * @return the length.
     */
    public double length() {
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2)
                + Math.pow(getZ(), 2));
    }

    /**
     * The distance (AKA length, magnitude) of this position from other
     * position.
     * 
     * @param other
     *            the other position which we are using as a reference point.
     * @return the distance to the other point.
     */
    public double distance(Position other) {
        return Math.sqrt(Math.pow(getX() - other.getX(), 2)
                + Math.pow(getY() - other.getY(), 2)
                + Math.pow(getZ() - other.getZ(), 2));
    }

    /**
     * Resize the position to the new length, but retain the direction.
     * 
     * @param newLength
     *            the new length.
     */
    public void setVectorLength(double newLength) {
        vectorMul(newLength / length());
    }

    /**
     * Like equals, but supply a tolerance for comparing.
     * 
     * @param other
     *            position to compare to.
     * @param tolerance
     *            error factor to allow when comparing.
     * @return true iff equal.
     */
    public boolean equals(Position other, double tolerance) {
        return (Math.abs(xPos - other.getX()) < tolerance)
                && (Math.abs(yPos - other.getY()) < tolerance)
                && (Math.abs(zPos - other.getY()) < tolerance);
    }

    /**
     * Normalise a vector. Set length to 1 unit unless it has no length to start
     * with.
     */
    void normalise() {
        double length = this.length();
        if (length > 0) {
            vectorMul(1 / length);
        } else {
            xPos = 0;
            yPos = 0;
            zPos = 0;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(xPos);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yPos);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(zPos);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (Double.doubleToLongBits(xPos) != Double
                .doubleToLongBits(other.getX()))
            return false;
        if (Double.doubleToLongBits(yPos) != Double
                .doubleToLongBits(other.getY()))
            return false;
        if (Double.doubleToLongBits(zPos) != Double
                .doubleToLongBits(other.getZ()))
            return false;
        return true;
    }

    /**
     * Multiply a vector by a constant.
     * 
     * @param c
     *            multiplier.
     */
    public void vectorMul(double c) {
        xPos *= c;
        yPos *= c;
        zPos *= c;
    }

    /**
     * Rotate vectors around each axis Clockwise looking into the origin from
     * along the positive axis.
     * 
     * @param theta
     *            in radians.
     */
    public void rotateX(double theta) {
        double newY = yPos * Math.cos(theta) + zPos * Math.sin(theta);
        zPos = -yPos * Math.sin(theta) + zPos * Math.cos(theta);
        yPos = newY;
    }

    /**
     * Rotate vectors around each axis Clockwise looking into the origin from
     * along the positive axis.
     * 
     * @param theta
     *            in radians.
     */
    public void rotateY(double theta) {

        double newX = xPos * Math.cos(theta) - zPos * Math.sin(theta);
        zPos = xPos * Math.sin(theta) + zPos * Math.cos(theta);
        xPos = newX;
    }

    /**
     * Rotate vectors around each axis Clockwise looking into the origin from
     * along the positive axis.
     * 
     * @param theta
     *            in radians.
     */
    public void rotateZ(double theta) {
        double newX = xPos * Math.cos(theta) + yPos * Math.sin(theta);
        yPos = -xPos * Math.sin(theta) + yPos * Math.cos(theta);
        xPos = newX;
    }

    /**
     * Return string representation of this object.
     */
    @Override
    public String toString() {
        return  String.format("[%.2f, %.2f, %.2f]", getX(),getY(),getZ());
    }

}
