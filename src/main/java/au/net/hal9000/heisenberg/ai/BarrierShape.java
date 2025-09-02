package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.util.Geometry;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Used by MemoryOfBarrier class to remember barriers seen in physics engine. Proof of concept. A
 * very simple Barrier. Just a straight line.
 */
public class BarrierShape implements Barrier {

  /** Lines that make up the barrier. Not required to be an enclosing shape. */
  private List<Line2D.Double> barrierLines;

  /** The object doing the blocking. */
  private Object blocker;

  /**
   * Constructor.
   *
   * @param barrierShape line segment.
   * @param blocker the Object doing the blocking.
   */
  public BarrierShape(List<Line2D.Double> barrierShape, Object blocker) {
    super();
    this.barrierLines = barrierShape;
    this.blocker = blocker;
  }

  /**
   * Returns details of any barrier blocking the path.<br>
   * Returns null if not blocked.<br>
   *
   * @return null or any details of a barrier.
   */
  @Override
  public PathBlockDetails getPathBlockDetailsDetails(Position from, Position to) {
    Line2D movement = new Line2D.Double(from.getX(), from.getY(), to.getX(), to.getY());

    return getPathBlockDetailsDetails(movement);
  }

  /**
   * Returns details of any barrier blocking the path.<br>
   * Returns null if not blocked.<br>
   *
   * @param movement the path traveled between two points.
   * @return null or any details of a barrier.
   */
  private PathBlockDetails getPathBlockDetailsDetails(Line2D movement) {

    // TODO Currently movement is a point mass so this approach may fail if
    // barrier has a gap which is too small for entity to fit through.
    // In future consider movement of shapes through.
    // Perhaps best left to the physics engine.

    PathBlockDetails blocker = null;
    for (Line2D line2D : barrierLines) {
      Position point = Geometry.getLineIntersection(movement, line2D);
      if (point != null) {
        blocker = new PathBlockDetails(point, blocker);
        break;
      }
    }

    return blocker;
  }

  /**
   * Method toString.
   *
   * @return String
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(11);
    DecimalFormat df = new DecimalFormat("#.###");
    stringBuilder.append(getClass().getSimpleName()).append("=[blocker=").append(blocker);
    if (barrierLines == null) {
      stringBuilder.append(", null");
    } else {
      for (Line2D barrierLine : barrierLines) {
        stringBuilder
            .append(", [")
            .append(df.format(barrierLine.getX1()))
            .append(',')
            .append(df.format(barrierLine.getY1()))
            .append("]=>[")
            .append(df.format(barrierLine.getX2()))
            .append(',')
            .append(df.format(barrierLine.getY2()))
            .append("]");
      }
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}
