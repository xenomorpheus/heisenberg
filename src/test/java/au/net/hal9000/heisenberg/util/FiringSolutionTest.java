package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.*;

import org.jbox2d.common.Vec2;
import org.junit.Test;

public class FiringSolutionTest {

  /** Target not moving so aim directly at target. */
  @Test
  public void testCalculateStationaryTarget() {
    Vec2 src = new Vec2(0, 0);
    Vec2 targetPos = new Vec2(0, 1);
    Vec2 targetVel = new Vec2(0, 0);
    float interceptSpeed = 1;
    Vec2 catTarget = FiringSolution.calculate(src, targetPos, targetVel, interceptSpeed);
    assertNotNull(catTarget);
    assertTrue(targetPos.equals(catTarget));
  }

  /** Target too quick, no solution. */
  @Test
  public void testCalculateTargetTooQuick() {
    Vec2 src = new Vec2(0, 0);
    Vec2 targetPos = new Vec2(0, 1);
    Vec2 targetVel = new Vec2(0, 5);
    float interceptSpeed = 1;
    Vec2 catTarget = FiringSolution.calculate(src, targetPos, targetVel, interceptSpeed);
    assertNull(catTarget);
  }

  /**
   * A simple moving target.<br>
   * calculate intercept({x:2, y:4}, {x:5, y:7, vx: 2, vy:1}, 5) = {x: 8, y: 8.5}
   */
  @Test
  public void testCalculateSimple() {
    Vec2 src = new Vec2(2, 4);
    Vec2 targetPos = new Vec2(5, 7);
    Vec2 targetVel = new Vec2(2, 1);
    float interceptSpeed = 5;
    Vec2 catTarget = FiringSolution.calculate(src, targetPos, targetVel, interceptSpeed);
    assertNotNull(catTarget);
    assertTrue(new Vec2(8.0f, 8.5f).equals(catTarget));
  }

  @Test
  public void testQuad() {
    // solutions are -3 and +4.
    // (x + 3) (x-4) = 0
    // xx -x - 12 = 0
    float a = 1;
    float b = -1;
    float c = -12;
    Vec2 ts = FiringSolution.quad(a, b, c);
    assertTrue(new Vec2(-3, +4).equals(ts) || new Vec2(+4, -3).equals(ts));
  }
}
