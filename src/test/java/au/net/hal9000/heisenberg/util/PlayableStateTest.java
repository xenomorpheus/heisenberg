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
  public void testActionPointsAdjust() {
    var state = new PlayableState();
    state.setActionPoints(10);
    state.actionPointsAdjust(-6);
    assertEquals("actionPoints", 4, state.getActionPoints());
  }

  @Test
  public void testMana() {
    var state = new PlayableState();
    state.setMana(1);
    assertEquals("mana", 1, state.getMana());
  }

  @Test
  public void testManaAdjust() {
    var state = new PlayableState();
    state.setMana(12);
    state.manaAdjust(-3);
  }

  @Test
  public void testToString() {
    {
      var state = new PlayableState();
      assertEquals("", state.toString());
    }
    {
      var state = new PlayableState();
      state.setActionPoints(10);
      assert (state.toString().contains("Action Points: 10"));
    }
    {
      var state = new PlayableState();
      state.setMana(5);
      assert (state.toString().contains("Mana: 5"));
    }
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
    } finally {
      // Clean up the file after the test
      var file = new java.io.File("/tmp/characterSheet.ser");
      if (file.exists()) {
        file.delete();
      }
    }
  }
}
