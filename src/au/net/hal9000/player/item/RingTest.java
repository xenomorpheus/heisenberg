package au.net.hal9000.player.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.player.units.Currency;

public class RingTest {

	@Test
	public void testRingConstructorNoAguments() {
		Ring r = new Ring();
		assertEquals("description", "small metalic ring", r.getDescription());
		assertTrue("value", r.getValueBase().equals(new Currency(0, 5, 0, 0)));
		assertTrue("weightBase",  r.getWeightBase().equals(0.02F));
	}

	@Test
	public void testImplementsMagic() {
		Ring r = new Ring();
		assertFalse("Implements Magical", r.isMagical());
	}

}
