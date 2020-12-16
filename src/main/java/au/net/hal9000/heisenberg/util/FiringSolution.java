package au.net.hal9000.heisenberg.util;

import org.jbox2d.common.Vec2;

/** A library to calculate the firing solution to hit a target moving linearly in 2D. */
public class FiringSolution {

  /**
   * Return the firing solution for a projectile starting at 'src' with velocity 'v', to hit a
   * target, 'targetPos'.
   *
   * @param Object src position of shooter
   * @param targetPos target position
   * @param targetVelocity target velocity
   * @param projectileSpeed speed of projectile
   * @return Object Coordinate at which to fire (and where intercept occurs)
   *     <p>Credit: broofa http://stackoverflow.com/a/3487761/1647851
   */
  public static Vec2 calculate(
      Vec2 src, Vec2 targetPos, Vec2 targetVelocity, float projectileSpeed) {
    float tx = targetPos.x - src.x;
    float ty = targetPos.y - src.y;
    float tvx = targetVelocity.x;
    float tvy = targetVelocity.y;

    // Get quadratic equation components
    float a = tvx * tvx + tvy * tvy - projectileSpeed * projectileSpeed;
    float b = 2 * (tvx * tx + tvy * ty);
    float c = tx * tx + ty * ty;

    // Solve quadratic
    Vec2 ts = quad(a, b, c); // See quad(), below

    // Find smallest positive solution
    Vec2 sol = null;
    if (ts != null) {
      float t0 = ts.x;
      float t1 = ts.y;
      float t = Math.min(t0, t1);
      if (t < 0) t = Math.max(t0, t1);
      if (t > 0) {
        sol = new Vec2(targetPos.x + targetVelocity.x * t, targetPos.y + targetVelocity.y * t);
      }
    }
    return sol;
  }

  /** Return solutions for quadratic */
  static Vec2 quad(float a, float b, float c) {
    Vec2 sol = null;
    if (Math.abs(a) < 1e-6) {
      if (Math.abs(b) < 1e-6) {
        sol = Math.abs(c) < 1e-6 ? new Vec2() : null;
      } else {
        sol = new Vec2(-c / b, -c / b);
      }
    } else {
      float disc = b * b - 4 * a * c;
      if (disc >= 0) {
        disc = (float) Math.sqrt(disc);
        a = 2 * a;
        sol = new Vec2((-b - disc) / a, (-b + disc) / a);
      }
    }
    return sol;
  }
}
