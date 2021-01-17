package au.net.hal9000.heisenberg.fifthed.spell;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/** */
public class SpellTest {

  /** Method setUp. */
  @Before
  public void setUp() {}

  @Test
  public void testDetails() {
    String s = new Fireball().details();
    assertTrue(s.contains("Name: Fireball"));
    assertTrue(s.contains("SavingThrow: REFLEX_HALF"));
    assertTrue(s.contains("ActionDuration: STANDARD"));
    assertTrue(s.contains("Effect: "));
  }
}
