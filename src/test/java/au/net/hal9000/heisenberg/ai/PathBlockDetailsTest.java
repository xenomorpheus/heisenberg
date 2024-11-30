package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.units.Position;
import org.junit.Test;

/**
 * Test PathBlockDetails class.
 *
 * @author bruins
 */
public class PathBlockDetailsTest {

  /** Test Blocker class. */
  @Test
  public void test() {
    final Position position = new Position(1, 2);
    final String name = "something";
    final PathBlockDetails blocker = new PathBlockDetails(position, name);
    assertTrue(position.equals(blocker.getBlockingPoint()));
    assertTrue(name.equals(blocker.getBlocker()));
  }
}
