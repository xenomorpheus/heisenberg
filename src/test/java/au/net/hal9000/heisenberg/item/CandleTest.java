package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Candle;

public class CandleTest {
	private static final float MARGIN = 0.00001f;

	@Test
	public void testCandleString() {
		Candle candle = new Candle("TheCandle");
		assertEquals("name", "TheCandle", candle.getName());
	}

	@Test
	public void testCandle() {
		Candle candle = new Candle();
		assertEquals("name", "Candle", candle.getName());
	}

	@Test
	public void testSetType() {
		int type = 1;
		Candle candle = new Candle();
		candle.setType(type);
		if (type == 1) {
			assertEquals("volumeBase", 0.5f, candle.getVolumeBase(), MARGIN);
			assertEquals("weightBase", 0.5f, candle.getWeightBase(), MARGIN);
		}
	}

    // Also tests light(), getDescription()
	@Test
	public void testIsLit() {
		Candle candle = new Candle("TheCandle");
		assertEquals("unlit description", "TheCandle. Not lit", candle.getDetailedDescription());
		assertFalse("isLit", candle.isLit());
		candle.light();
		assertTrue("isLit", candle.isLit());
		assertEquals("unlit description", "TheCandle. Is lit", candle.getDetailedDescription());
	}


	@Test
	public void testExtinquish() {
		Candle candle = new Candle("TheCandle");
		assertFalse("isLit=false", candle.isLit());
		candle.light();
		assertTrue("isLit=true", candle.isLit());
		candle.extinquish();
		assertFalse("extomsguish isLit=false", candle.isLit());
	}

}
