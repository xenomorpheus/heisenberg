package au.net.hal9000.heisenberg.units;

import javax.persistence.Embeddable;

@Embeddable
public class Position {

    private double xPos;
    private double yPos;
    private double zPos;

    // Constructors
    public Position(double xPos, double yPos, double zPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }

    public Position(Position other) {
        this(other.getX(), other.getY(), other.getZ());
    }

    public Position() {
        this(0, 0, 0);
    }

    public Position(double x, double y) {
        this(x, y, 0);
    }

    // Getters and Setters
    public double getX() {
        return xPos;
    }

    public void setX(float xPos) {
        this.xPos = xPos;

    }

    public double getY() {
        return yPos;
    }

    public void setY(float yPos) {
        this.yPos = yPos;

    }

    public double getZ() {
        return zPos;
    }

    public void setZ(float zPos) {
        this.zPos = zPos;

    }

    // Misc
    public void addLocal(Position delta) {
        xPos += delta.getX();
        yPos += delta.getY();
        zPos += delta.getZ();
    }

    public Position add(Position delta) {
        return new Position(xPos + delta.getX(), yPos + delta.getY(), zPos
                + delta.getZ());
    }

    public double length() {
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2)
                + Math.pow(getZ(), 2));
    }

    public double distance(Position other) {
        return Math.sqrt(Math.pow(getX() - other.getX(), 2)
                + Math.pow(getY() - other.getY(), 2)
                + Math.pow(getZ() - other.getZ(), 2));
    }

    public void setVectorLength(double newLength) {
        double oldLength = length();
        double factor = newLength / oldLength;
        xPos *= factor;
        yPos *= factor;
        zPos *= factor;
    }

    public boolean equals(Position other, double tolerance) {
        return (Math.abs(xPos - other.getX()) < tolerance)
                && (Math.abs(yPos - other.getY()) < tolerance);
    }

    /**
     * Normalise a vector. Set length to 1 unit unless it has no length to start
     * with.
     */
    void normalise() {
        double length = this.length();
        if (length > 0) {
            xPos /= length;
            yPos /= length;
            zPos /= length;
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
                .doubleToLongBits(other.xPos))
            return false;
        if (Double.doubleToLongBits(yPos) != Double
                .doubleToLongBits(other.yPos))
            return false;
        if (Double.doubleToLongBits(zPos) != Double
                .doubleToLongBits(other.zPos))
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

}
