package au.net.hal9000.dnd.item;
import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.dnd.item.property.Magical;

public class RingTest {

	@Test
	public void testRingConstructorNoAguments() {
		Ring r = new Ring();
		assertEquals("description", "small metalic ring", r.getDescription());
		assertTrue("value", r.getValueBase().equals(new Currency(0, 5, 0, 0)));
		assertEquals("weightBase", 0.02F, r.getWeightBase(), 0.0001F);
	}

	@Test
	public void testImplementsMagic() {
		Ring r = new Ring();
		assertFalse("Implements Magical", r instanceof Magical);
	}

}
