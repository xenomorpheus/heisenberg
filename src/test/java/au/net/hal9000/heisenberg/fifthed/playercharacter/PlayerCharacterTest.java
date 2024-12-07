package au.net.hal9000.heisenberg.fifthed.playercharacter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.TestHelper;
import au.net.hal9000.heisenberg.units.Position;
import org.junit.Test;

/** */
public class PlayerCharacterTest {
  @Test
  public void testPlayerCharacter() {

    // Misc
    PlayerCharacter pc = TestHelper.getMagus();
    String details = pc.details();
    assertTrue(details.contains("Name: Mr Magus"));
    // TODO assertTrue(details.contains("Species: Human"));
    assertTrue(details.contains("Level: 3"));
    assertTrue(details.contains("Condition: DEAFENED"));
    assertTrue(details.contains("Location (relative): "));
    assertTrue(details.contains("Equipped:"));
  }

  /** Test distance to other character. */
  @Test
  public void testPlayerCharacterDistance() {
    PlayerCharacter fred = new Human().setPosition(new Position(1, 0, 0));
    PlayerCharacter mary = new Human().setPosition(new Position(1, 1, 1));
    double distance = fred.distance(mary);
    double root2 = 1.41421356237f;
    assertEquals("distance ", root2, distance, 0.001f);
  }
}
