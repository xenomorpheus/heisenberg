package au.net.hal9000.heisenberg.jbox2d.demo;

import au.net.hal9000.heisenberg.ai.BarrierShape;
import au.net.hal9000.heisenberg.ai.MemoryOfBarrier;
import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.Memory;
import au.net.hal9000.heisenberg.ai.api.MemorySet;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import org.jbox2d.common.Vec2;

class MazeUtil {

  static Vec2[] getBoundaryShape() {
    /** half the width */
    final float width_half = 10.0f;
    /** half the height */
    final float height_half = 15.0f;
    return new Vec2[] {
      new Vec2(-width_half, -height_half),
      new Vec2(-width_half, +height_half),
      new Vec2(+width_half, +height_half),
      new Vec2(+width_half, -height_half),
      new Vec2(-width_half, -height_half)
    };
  }

  static Vec2[] getBarrierShape() {
    /** half the width */
    final float width_half = 5.0f;
    /** half the height */
    final float height_half = 5.0f;
    final Vec2 bottom_left = new Vec2(-width_half, -height_half);
    final Vec2 top_left = new Vec2(-width_half, 0);
    final Vec2 top_right = new Vec2(+width_half, 0);
    final Vec2 bottom_right = new Vec2(+width_half, -height_half);
    return new Vec2[] {bottom_left, top_left, top_right, bottom_right};
  }

  /**
   * Add to Entity memory an array of barrier lines.
   *
   * @param entity entity.
   * @param shape the barrier as an array of Vec2 points.
   * @param position the position of this shape.
   * @param body the object that is the barrier.
   */
  static void learnBarrierArray(MemorySet memorySet, Vec2[] shape, Vec2 position, Object body) {
    List<Line2D.Double> shapeLine2D = new ArrayList<>();

    for (int index = 1; index < shape.length; index++) {
      shapeLine2D.add(
          new Line2D.Double(
              shape[index - 1].x + position.x,
              shape[index - 1].y + position.y,
              shape[index].x + position.x,
              shape[index].y + position.y));
    }

    Barrier barrier = new BarrierShape(shapeLine2D, body);
    Memory memory = new MemoryOfBarrier(null, 0, barrier);
    memorySet.add(memory);
  }
}
