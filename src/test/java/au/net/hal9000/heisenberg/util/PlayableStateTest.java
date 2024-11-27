package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.junit.Test;

public class PlayableStateTest {
  @Test
  public void testActionPoints() {
    var state = new PlayableState();
    state.setActionPoints(1);
    assertEquals("actionPoints", 1, state.getActionPoints());
  }

  @Test
  public void testActionPointsAdjust() {}

  @Test
  public void testMana() {
    var state = new PlayableState();
    state.setMana(1);
    assertEquals("mana", 1, state.getMana());
  }

  @Test
  public void testManaAdjust() {
    // TODO test manaAdjust
  }

  @Test
  public void testToString() {
    // TODO test toString
  }

  /** Test serialisation. */
  @Test
  public void serialisation() {
    PlayableState state = new PlayableState();
    try (FileOutputStream fos = new FileOutputStream("/tmp/characterSheet.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(state);
      System.out.println("PlayableState object serialized successfully.");

    } catch (IOException e) {
      fail(e.toString());
    }
  }
}
