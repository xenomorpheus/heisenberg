package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.being.Human;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** */
public class ScabbardTest {

  /** Method testIsHumanoidClothing. */
  @Test
  public void testIsHumanoidClothing() {
    Scabbard scabbard = new Scabbard();
    assertTrue("is humanoid clothing", ItemProperty.isClothing(scabbard));
  }

  /** Method testIsWearable. */
  @Test
  public void testIsWearable() {
    Human human = new Human();
    Scabbard scabbard = new Scabbard();
    human.add(scabbard);
  }

  /** Method testAddSword. */
  @Test
  public void testAddSword() {
    Scabbard scabbard = new Scabbard();
    Sword sword = new Sword();
    scabbard.add(sword);
  }

  // TODO Add test for adding non-sword, should fail.
  // Perhaps consider volume so a penny would fit.

  /** Method testGetIndexOfChild. */
  @Test
  public void testGetIndexOfChild() {
    Scabbard scabbard = new Scabbard();
    Sword sword = new Sword();
    assertEquals("empty", -1, scabbard.indexOf(sword));
    scabbard.add(sword);
    assertEquals("has sword", 0, scabbard.indexOf(sword));
  }
}
