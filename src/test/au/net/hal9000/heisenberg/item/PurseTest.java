package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Purse;

public class PurseTest {

	// TODO put in config
	// private static final float coinsToWeight = 0.1f;
	// TODO give value and put in config
	// private static final float coinsToVolume = 0.01f;
	// Within margin of error
	private static final float MARGIN_OF_ERROR = 0.0001f;

	@Test
	public void testPurse() {
		Purse purse = new Purse();
		assertEquals("getName", "Purse", purse.getName());
		assertEquals("getValue - empty purse", purse.getValueBase(),
				purse.getValue());
		assertEquals("getWeight - empty purse", purse.getWeightBase(),
				purse.getWeight(), MARGIN_OF_ERROR);
		assertEquals("getVolume - empty purse", purse.getVolumeBase(),
				purse.getVolume(), MARGIN_OF_ERROR);
		// TODO add more
	}

	@Test
	public void testPurseString() {
		Purse purse = new Purse("ThisName");
		assertEquals("Name", "ThisName", purse.getName());
	}

	@Test
	public void testGetVolume() {
		// TODO Need to implement adding coins first.
	}

	@Test
	public void testGetValue() {
		// TODO Need to implement adding coins first.
	}

	@Test
	public void testGetWeight() {
		// TODO Need to implement adding coins first.
	}

}
