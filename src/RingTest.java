import static org.junit.Assert.*;

import org.junit.Test;

public class RingTest {

	@Test
	public void testRingConstructorNoAguments() {
		Ring r = new Ring();
		assertEquals("description", "small metalic ring", r.getDescription());
		assertEquals("value", new CoinCollection(0, 5, 0, 0), r.getValue());
		assertEquals("baseWeight", 0F, r.getBaseWeight(), 0.0001F);
	}

}
