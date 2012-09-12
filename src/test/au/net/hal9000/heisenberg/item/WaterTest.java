package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Water;

public class WaterTest {

	@Test
	public void testsplitByWeightFloat() {
		float TOLLERANCE = 0.0001f;

		// Test for illegal argument throwing
		try {
			Water water = new Water();
			water.splitByWeight(0.0f);
			fail("Expected exception");
		} catch (IllegalArgumentException e) {
			; // Nothing to do
		} catch (Exception e) {
			fail("Unexpected exception: " + e);
		}

		{
			// Test for working
			Water water = new Water();
			water.setWeightBase(6f);
			water.setVolumeBase(3f);
			Water water2 = null;
			try {
				water2 = water.splitByWeight(2.0f);
			} catch (Exception e) {
				fail("Unexpected exception: " + e);
			}
			assertNotNull("new object not null", water2);
			assertEquals("old object weight", 4.0f, water.getWeightBase(),
					TOLLERANCE);
			assertEquals("old object volume", 2.0f, water.getVolumeBase(),
					TOLLERANCE);
			assertEquals("new object weight", 2.0f, water2.getWeightBase(),
					TOLLERANCE);
			assertEquals("new object volume", 1.0f, water2.getVolumeBase(),
					TOLLERANCE);

		}
	}

}
