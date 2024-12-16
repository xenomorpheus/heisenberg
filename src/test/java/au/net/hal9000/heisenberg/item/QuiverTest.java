package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import org.junit.Test;

/** */
public class QuiverTest {

  /** Method quiverAddArrow. */
  @Test
  public void quiverAddArrow() {
    Quiver quiver = new Quiver();
    Arrow arrow = new Arrow();
    quiver.add(arrow);
    quiver.add(new Arrow());
    quiver.add(new Arrow());
    quiver.add(new Arrow());
    quiver.add(new Arrow());
    quiver.add(new Arrow());

    assertEquals("Arrow location ", quiver, arrow.getContainer());
  }

  /** Method quiverAddNonArrow. */
  @Test(expected = InvalidTypeException.class)
  public void quiverAddNonArrow() {
    Quiver quiver = new Quiver();
    quiver.add(new Biscuit());
  }
}
