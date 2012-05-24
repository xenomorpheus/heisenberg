import static org.junit.Assert.*;

import org.junit.Test;

public class RingTest {

	@Test
	public void testRingConstructorNoAguments() {
		Ring r = new Ring();
		assertEquals("description", "small metalic ring", r.getDescription());
		assertTrue("value", r.getCost().equals(new CoinCollection(0, 5, 0, 0)));
		assertEquals("baseWeight", 0F, r.getBaseWeight(), 0.0001F);
	}

	@Test
	public void testImplementsMagic() {
		Ring r = new Ring();
		assertFalse("Implements Magic", r.implementsInterface(Magic.class));
	}

}
