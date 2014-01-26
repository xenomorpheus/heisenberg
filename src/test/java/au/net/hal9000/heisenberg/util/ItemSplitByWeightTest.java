package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Water;

/**
 * Unit tests for the class ItemSplitByWeight.
 * 
 * @author bruins
 * 
 */
public class ItemSplitByWeightTest {
    /** float comparison tolerance. */
    private static final float TOLERANCE = 0.0001f;

    @Test(expected = IllegalArgumentException.class)
    public void testsplitByWeightZero() {

        // Test for illegal argument throwing
        Water water = new Water();
        water.splitByWeight(0.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testsplitByWeightEqual() {

        // Test for illegal argument throwing
        Water water = new Water();

        water.setWeightBase(1.0f);
        water.splitByWeight(1.0f);
    }

    @Test
    public void testsplitByWeightWorking() {

        // Test for working
        Water water = new Water();
        water.setWeightBase(6f);
        water.setVolumeBase(3f);
        Water water2 = null;
        water2 = (Water) water.splitByWeight(2.0f);
        assertNotNull("new object not null", water2);
        assertEquals("old object weight", 4.0f, water.getWeightBase(),
                TOLERANCE);
        assertEquals("old object volume", 2.0f, water.getVolumeBase(),
                TOLERANCE);
        assertEquals("new object weight", 2.0f, water2.getWeightBase(),
                TOLERANCE);
        assertEquals("new object volume", 1.0f, water2.getVolumeBase(),
                TOLERANCE);

    }

}
