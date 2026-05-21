package au.net.hal9000.heisenberg.units;

import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

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

  @Setter @Getter private double x;
  @Setter @Getter private double y;
  @Setter @Getter private double z;

  // Constructors
  /**
   * 3D Constructor.
   *
   * @param xpos x position.
   * @param ypos y position.
   * @param zpos y position.
   */
  public Position(double xpos, double ypos, double zpos) {
    this.x = xpos;
    this.y = ypos;
    this.z = zpos;
  }

  /**
   * 2D Constructor.
   *
   * @param xpos x position.
   * @param ypos y position.
   */
  public Position(double xpos, double ypos) {
    this(xpos, ypos, 0);
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

  // Misc
  /**
   * Change the position object by the delta.
   *
   * @param delta the amount to change the local position by.
   * @return current Position object.
   */
  public Position applyDelta(Position delta) {
    x += delta.getX();
    y += delta.getY();
    z += delta.getZ();
    return this;
  }

  /**
   * Return a new Position object that is the current Position, plus argument.
   *
   * @param delta Position to add.
   * @return Return a new Position object that is the current Position, plus argument.
   */
  public Position add(Position delta) {
    return new Position(x + delta.getX(), y + delta.getY(), z + delta.getZ());
  }

  /**
   * Return a new Position object that is the current Position, minus argument.
   *
   * @param other Position to minus.
   * @return Return a new Position object that is the current Position, minus argument.
   */
  public Position subtract(Position other) {
    return new Position(x - other.getX(), y - other.getY(), z - other.getZ());
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
    return Objects.hash(x, y, z);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Position other)) {
      return false;
    }

    return Double.compare(x, other.x) == 0
        && Double.compare(y, other.y) == 0
        && Double.compare(z, other.z) == 0;
  }

  /**
   * Return true IFF each axis of the two positions are all within default tolerance.
   *
   * @param other position to compare to.
   * @return true IFF each axis of the two positions are all within default tolerance.
   */
  public boolean equalsApproximately(Position other) {
    return equalsApproximately(other, DEFAULT_AXIS_TOLERANCE);
  }

  /**
   * Return true IFF each axis of the two positions are all within the tolerance.
   *
   * @param other position to compare to.
   * @param tolerance error factor to allow when comparing.
   * @return Return true IFF each axis of the two positions are all within the tolerance.
   */
  public boolean equalsApproximately(Position other, double tolerance) {
    return other != null
        && Math.abs(x - other.x) < tolerance
        && Math.abs(y - other.y) < tolerance
        && Math.abs(z - other.z) < tolerance;
  }

  /**
   * Multiply a vector by a constant.
   *
   * @param c multiplier.
   */
  public void vectorMul(double c) {
    x *= c;
    y *= c;
    z *= c;
  }

  /**
   * Rotate vectors around each axis Clockwise looking into the origin from along the positive axis.
   *
   * @param theta in radians.
   */
  public void rotateX(double theta) {
    double newY = y * Math.cos(theta) + z * Math.sin(theta);
    z = -y * Math.sin(theta) + z * Math.cos(theta);
    y = newY;
  }

  /**
   * Rotate vectors around each axis Clockwise looking into the origin from along the positive axis.
   *
   * @param theta in radians.
   */
  public void rotateY(double theta) {

    double newX = x * Math.cos(theta) - z * Math.sin(theta);
    z = x * Math.sin(theta) + z * Math.cos(theta);
    x = newX;
  }

  /**
   * Rotate vectors around each axis Clockwise looking into the origin from along the positive axis.
   *
   * @param theta in radians.
   */
  public void rotateZ(double theta) {
    double newX = x * Math.cos(theta) + y * Math.sin(theta);
    y = -x * Math.sin(theta) + y * Math.cos(theta);
    x = newX;
  }

  /** Return string representation of this object. */
  @Override
  public String toString() {
    return String.format(
        getClass().getSimpleName() + "=[%.2f, %.2f, %.2f]", getX(), getY(), getZ());
  }

  /**
   * Duplicate the position object.
   *
   * @return a duplicate of this position.
   */
  public Position duplicate() {
    return new Position(this);
  }

  /**
   * Set the current position to the same location as the other Position.
   *
   * @param other Position
   */
  public void set(Position other) {
    x = other.getX();
    y = other.getY();
    z = other.getZ();
  }
}
