package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/** Unit tests for Dice class. */
public class DiceTest {
  /** number of tries to see if dice will fail. */
  private static final int TEST_MAX = 10;

  /** number of rolls per try . */
  private static final int ROLL_MAX = 10;

  /** Test basic constructor. */
  @Test
  public void testDice() {
    new Dice();
  }

  /** test range of default sided dice. */
  @Test
  public void testRoll() {
    Dice dice = new Dice();
    // Check that default (d6) is within range
    for (int test = 0; test < TEST_MAX; test++) {
      int roll = dice.roll();
      assertTrue("1d6 valid range", (roll >= 1) && (roll <= Dice.D_SIX));
    }
  }

  /** test bad number of sides. */
  @Test(expected = IllegalArgumentException.class)
  public void testRollBadSideCount() {
    Dice dice = new Dice();
    dice.roll(-1);
  }

  /** test bad number of rolls. */
  @Test(expected = IllegalArgumentException.class)
  public void testRollBadRollsGoodSideCount() {
    Dice dice = new Dice();
    dice.roll(-1, 1);
  }

  /** test bad side number. */
  @Test(expected = IllegalArgumentException.class)
  public void testRollGoodRollsBadSideCount() {
    Dice dice = new Dice();
    dice.roll(1, -1);
  }

  /** test results are in correct range. */
  @Test
  public void testRollInt() {
    // Check return values
    Dice dice = new Dice();
    for (int sides = 1; sides <= Dice.D_TWENTY; sides++) {
      for (int test = 0; test < TEST_MAX; test++) {
        int roll = dice.roll(sides);
        assertTrue("1d" + sides + " valid range", (roll >= 1) && (roll <= sides));
      }
    }
  }

  /** Method testRollIntInt. */
  @Test
  public void testRollIntInt() {
    Dice dice = new Dice();

    // Check return values
    for (int sides = 1; sides <= Dice.D_TWENTY; sides++) {
      for (int test = 0; test < TEST_MAX; test++) {
        for (int rolls = 1; rolls <= ROLL_MAX; rolls++) {
          // Check that roll is within range
          int total = dice.roll(rolls, sides);
          assertTrue(
              total + "d" + sides + " valid range", (total >= rolls) && (total <= (rolls * sides)));
        }
      }
    }
  }
}
