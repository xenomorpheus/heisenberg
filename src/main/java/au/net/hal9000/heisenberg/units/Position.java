package au.net.hal9000.heisenberg.units;

import javax.persistence.Embeddable;

@Embeddable
public class Position {

  /** move agent North. */
  public static final Position NORTH = new Position(0, -1);
  /** move agent South. */
  public static final Position SOUTH = new Position(0, 1);
  /** move agent East. */
  public static final Position EAST = new Position(1, 0);
  /** move agent West. */
  public static final Position WEST = new Position(-1, 0);

  public static final double DEFAULT_AXIS_TOLERANCE = 0.05;
  private double xPos;
  private double yPos;
  private double zPos;

  // Constructors
  /**
   * 3D Constructor.
   *
   * @param xPos x position.
   * @param yPos y position.
   * @param zPos y position.
   */
  public Position(double xPos, double yPos, double zPos) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.zPos = zPos;
  }

  /**
   * 2D Constructor.
   *
   * @param xPos x position.
   * @param yPos y position.
   */
  public Position(double xPos, double yPos) {
    this(xPos, yPos, 0);
  }

  /**
   * Constructor.
   *
   * @param other position to copy.
   */
  public Position(Position other) {
    this(other.getX(), other.getY(), other.getZ());
  }

  /** Constructor. At origin. */
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
   * @param xPos new x position.
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
   * @param yPos new y position.
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
   * @param zPos new z position.
   */
  public void setZ(double zPos) {
    this.zPos = zPos;
  }

  // Misc
  /**
   * Change the position object by the delta.
   *
   * @param delta the amount to change the local position by.
   * @return current Position object.
   */
  public Position applyDelta(Position delta) {
    xPos += delta.getX();
    yPos += delta.getY();
    zPos += delta.getZ();
    return this;
  }

  /**
   * Return a new Position object that is the current Position, plus argument.
   *
   * @param delta Position to add.
   * @return Return a new Position object that is the current Position, plus argument.
   */
  public Position add(Position delta) {
    return new Position(xPos + delta.getX(), yPos + delta.getY(), zPos + delta.getZ());
  }

  /**
   * Return a new Position object that is the current Position, minus argument.
   *
   * @param other Position to minus.
   * @return Return a new Position object that is the current Position, minus argument.
   */
  public Position subtract(Position other) {
    return new Position(xPos - other.getX(), yPos - other.getY(), zPos - other.getZ());
  }

  /**
   * The length (AKA distance, magnitude) of this position from origin.
   *
   * @return the length.
   */
  public double length() {
    return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2));
  }

  /**
   * The distance (AKA length, magnitude) of this position from other position.
   *
   * @param other the other position which we are using as a reference point.
   * @return the distance to the other point.
   */
  public double distance(Position other) {
    return Math.sqrt(
        Math.pow(getX() - other.getX(), 2)
            + Math.pow(getY() - other.getY(), 2)
            + Math.pow(getZ() - other.getZ(), 2));
  }

  /**
   * Resize the position to the new length, but retain the direction.
   *
   * @param newLength the new length.
   */
  public void setVectorLength(double newLength) {
    vectorMul(newLength / length());
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
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Position other = (Position) obj;
    if (equals(other)) return false;
    return true;
  }

  /**
   * Return true IFF each axis of the two positions are all within default tolerance.
   *
   * @param other position to compare to.
   * @return true IFF each axis of the two positions are all within default tolerance.
   */
  public boolean equals(Position other) {
    return equals(other, DEFAULT_AXIS_TOLERANCE);
  }

  /**
   * Return true IFF each axis of the two positions are all within the tolerance.
   *
   * @param other position to compare to.
   * @param tolerance error factor to allow when comparing.
   * @return Return true IFF each axis of the two positions are all within the tolerance.
   */
  public boolean equals(Position other, double tolerance) {
    return (other != null)
        && (Math.abs(xPos - other.getX()) < tolerance)
        && (Math.abs(yPos - other.getY()) < tolerance)
        && (Math.abs(zPos - other.getZ()) < tolerance);
  }

  /**
   * Multiply a vector by a constant.
   *
   * @param c multiplier.
   */
  public void vectorMul(double c) {
    xPos *= c;
    yPos *= c;
    zPos *= c;
  }

  /**
   * Rotate vectors around each axis Clockwise looking into the origin from along the positive axis.
   *
   * @param theta in radians.
   */
  public void rotateX(double theta) {
    double newY = yPos * Math.cos(theta) + zPos * Math.sin(theta);
    zPos = -yPos * Math.sin(theta) + zPos * Math.cos(theta);
    yPos = newY;
  }

  /**
   * Rotate vectors around each axis Clockwise looking into the origin from along the positive axis.
   *
   * @param theta in radians.
   */
  public void rotateY(double theta) {

    double newX = xPos * Math.cos(theta) - zPos * Math.sin(theta);
    zPos = xPos * Math.sin(theta) + zPos * Math.cos(theta);
    xPos = newX;
  }

  /**
   * Rotate vectors around each axis Clockwise looking into the origin from along the positive axis.
   *
   * @param theta in radians.
   */
  public void rotateZ(double theta) {
    double newX = xPos * Math.cos(theta) + yPos * Math.sin(theta);
    yPos = -xPos * Math.sin(theta) + yPos * Math.cos(theta);
    xPos = newX;
  }

  /** Return string representation of this object. */
  @Override
  public String toString() {
    return String.format(
        getClass().getSimpleName() + "=[%.2f, %.2f, %.2f]", getX(), getY(), getZ());
  }

  /** @return a duplicate of this position. */
  public Position duplicate() {
    return new Position(this);
  }

  /**
   * Set the current position to the same location as the other Postion.
   *
   * @param other Position
   */
  public void set(Position other) {
    xPos = other.getX();
    yPos = other.getY();
    zPos = other.getZ();
  }
}
