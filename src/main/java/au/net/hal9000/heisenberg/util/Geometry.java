package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.units.Position;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Geometry utility.
 *
 * @author bruins
 */
public final class Geometry {

  /** Utility Class. */
  private Geometry() {}

  /**
   * Return the intersection point.Warning: Only X,Y considered.
   *
   * @param segment1 first line segment.
   * @param segment2 second line segment.
   * @return the intersection point of the two segments, or null if no intersection.
   */
  public static Position getLineIntersection(Line2D segment1, Line2D segment2) {
    return getLineIntersection(
        segment1.getX1(),
        segment1.getY1(),
        segment1.getX2(),
        segment1.getY2(),
        segment2.getX1(),
        segment2.getY1(),
        segment2.getX2(),
        segment2.getY2());
  }

  /**
   * Return the intersection point, if any, between two lines. First line from p0 to p1. Second line
   * from p2 to p3.
   *
   * @param p0X segment 1, point 1, x.
   * @param p0Y segment 1, point 1, y.
   * @param p1X segment 1, point 2, x.
   * @param p1Y segment 1, point 2, y.
   * @param p2X segment 2, point 1, x.
   * @param p2Y segment 2, point 1, y.
   * @param p3X segment 2, point 2, x.
   * @param p3Y segment 2, point 2, y.
   * @return the intersection point of the two segments, or null if no intersection.
   */
  private static Position getLineIntersection(
      double p0X,
      double p0Y,
      double p1X,
      double p1Y,
      double p2X,
      double p2Y,
      double p3X,
      double p3Y) {
    // TODO
    // http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect
    double s1X, s1Y, s2X, s2Y;
    double s, t, divisor;

    s1X = p1X - p0X;
    s1Y = p1Y - p0Y;
    s2X = p3X - p2X;
    s2Y = p3Y - p2Y;

    divisor = -s2X * s1Y + s1X * s2Y;
    s = (-s1Y * (p0X - p2X) + s1X * (p0Y - p2Y)) / divisor;
    t = (s2X * (p0Y - p2Y) - s2Y * (p0X - p2X)) / divisor;

    if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
      // Collision detected
      return new Position(p0X + (t * s1X), p0Y + (t * s1Y));
    }

    return null; // No intersection
  }

  /**
   * Generate a list of points like where the spokes. First
   *
   * @param delta the first spoke to be returned.
   * @param spokeCount number of spokes.
   * @return a list of points around the center.
   */
  public static List<Position> generateSpokesZ(Position delta, int spokeCount) {
    List<Position> list = new ArrayList<>();

    // full circle divided equally into directions.
    double theta = Math.PI * 2 / spokeCount;
    for (int directionIndex = 0; directionIndex < spokeCount; directionIndex++) {
      list.add(delta);
      delta = new Position(delta);
      delta.rotateZ(theta);
    }
    return list;
  }
}
