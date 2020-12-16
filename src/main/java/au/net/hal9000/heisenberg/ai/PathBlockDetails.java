package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Position;

/**
 * Details about the point in our path that we are blocked by an object.
 *
 * @author bruins
 */
public class PathBlockDetails {
  /** the point in our intended path at which we are blocked. */
  private Position blockingPoint;
  /** the object doing the blocking. */
  private Object blocker;

  /**
   * Constructor.
   *
   * @param point the point where the path is blocked.
   * @param blocker the object blocking the path.
   */
  PathBlockDetails(Position point, Object blocker) {
    super();
    this.blockingPoint = point;
    this.blocker = blocker;
  }

  /** @return get the point where the path is blocked. */
  public Position getBlockingPoint() {
    return blockingPoint;
  }

  /** @return get the object blocking the path. */
  public Object getBlocker() {
    return blocker;
  }
}
