package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * unit tests for AbilityScore class.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class AbilityScoreTest {
  /** test number for base value. */
  private static final int BASE = 3;
  /** test number for mod value. */
  private static final int MODIFIER = 5;

  /** test constructor with value/mod int pair. */
  @Test
  public void testAbilityScoreStringIntInt() {
    AbilityScore abilityScore = new AbilityScore("test", BASE, MODIFIER);
    assertEquals("test", abilityScore.getName());
    assertEquals(BASE, abilityScore.getValue());
    assertEquals(MODIFIER, abilityScore.getMod());
  }

  /** test constructor with string for value. */
  @Test
  public void testAbilityScoreStringInt() {

    AbilityScore abilityScore = new AbilityScore("test", "" + BASE);
    assertEquals("test", abilityScore.getName());
    assertEquals(BASE, abilityScore.getValue());
    assertEquals(0, abilityScore.getMod());
  }

  /** test constructor with string for value/mod pair. */
  @Test
  public void testAbilityScoreStringString2() {
    AbilityScore abilityScore = new AbilityScore("test", BASE + "/" + MODIFIER);
    assertEquals("test", abilityScore.getName());
    assertEquals(BASE, abilityScore.getValue());
    assertEquals(MODIFIER, abilityScore.getMod());
  }

  /** test constructor with string for value/mod pair, with mod having plus prefix. */
  @Test
  public void testAbilityScoreStringString3() {
    AbilityScore abilityScore = new AbilityScore("test", BASE + "/+" + MODIFIER);
    assertEquals("test", abilityScore.getName());
    assertEquals(BASE, abilityScore.getValue());
    assertEquals(MODIFIER, abilityScore.getMod());
  }

  /** test constructor with string for value/mod pair, with value and mod having plus prefix. */
  @Test
  public void testAbilityScoreStringString4() {
    AbilityScore abilityScore = new AbilityScore("test", "+" + BASE + "/+" + MODIFIER);
    assertEquals("test", abilityScore.getName());
    assertEquals(BASE, abilityScore.getValue());
    assertEquals(MODIFIER, abilityScore.getMod());
  }

  /** test constructor with string for value/mod pair, with mod having minus prefix. */
  @Test
  public void testAbilityScoreStringString5() {
    AbilityScore abilityScore = new AbilityScore("test asdf", BASE + "/-" + MODIFIER);
    assertEquals("test asdf", abilityScore.getName());
    assertEquals(BASE, abilityScore.getValue());
    assertEquals(-1 * MODIFIER, abilityScore.getMod());
  }

  /** test string generation with +/-/0 modifier. */
  @Test
  public void testToString() {
    AbilityScore abilityScore = new AbilityScore("test", BASE, MODIFIER);
    assertEquals("test: " + BASE + "/+" + MODIFIER, abilityScore.toString());
    abilityScore.setMod(0);
    assertEquals("test: " + BASE, abilityScore.toString());
    abilityScore.setMod(-1 * MODIFIER);
    assertEquals("test: " + BASE + "/-" + MODIFIER, abilityScore.toString());
  }

  /** test value string with +/-/0 modifier. */
  @Test
  public void testValueOptionalMod() {
    AbilityScore abilityScore = new AbilityScore("test", BASE, MODIFIER);
    assertEquals(BASE + "/+" + MODIFIER, abilityScore.valueOptionalMod());
    abilityScore.setMod(0);
    assertEquals(BASE + "", abilityScore.valueOptionalMod());
    abilityScore.setMod(-1 * MODIFIER);
    assertEquals(BASE + "/-" + MODIFIER, abilityScore.valueOptionalMod());
  }
}
