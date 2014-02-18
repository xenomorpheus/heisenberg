package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class DiceTest {
    /** number of tries to see if dice will fail. */
    private static final int TEST_MAX = 10;

    @Test
    public void testDice() {
        new Dice();
    }

    @Test
    public void testRoll() {
        Dice dice = new Dice();
        // Check that default (d6) is within range
        for (int test = 0; test < TEST_MAX; test++) {
            int roll = dice.roll();
            assertTrue("1d6 valid range", (roll >= 1) && (roll <= Dice.D6));
        }
    }

    @Test
    public void testRollInt() {
        Dice dice = new Dice();

        // Check args
        try {
            dice.roll(-1);
            fail("invalid sides accepted");
        } catch (Exception e) {
            // do nothing
        }

        // Check return values
        for (int sides = 1; sides <= Dice.D20; sides++) {
            for (int test = 0; test < TEST_MAX; test++) {
                int roll = dice.roll(sides);
                assertTrue("1d" + sides + " valid range", (roll >= 1)
                        && (roll <= sides));
            }
        }
    }

    @Test
    public void testRollIntInt() {
        Dice dice = new Dice();

        // Check args
        try {
            dice.roll(1, -1);
            fail("invalid sides accepted");
        } catch (Exception e) {
            // do nothing
        }

        // Check args
        try {
            dice.roll(-1, 1);
            fail("invalid rolls accepted");
        } catch (Exception e) {
            // do nothing
        }

        // Check return values
        for (int sides = 1; sides <= Dice.D20; sides++) {
            for (int test = 0; test < TEST_MAX; test++) {
                for (int rolls = 1; rolls <= 10; rolls++) {
                    // Check that roll is within range
                    int total = dice.roll(rolls, sides);
                    assertTrue(total + "d" + sides + " valid range",
                            (total >= rolls) && (total <= (rolls * sides)));
                }
            }
        }
    }
}
