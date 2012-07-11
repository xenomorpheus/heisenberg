package au.net.hal9000.dnd.units;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeightTest {

	private static final float WITHING_MARGIN = 0.00009F;

	@Test
	public void testEquals() {
		Weight ref = new Weight(1.0F);
		Weight eq = new Weight(1.0F);
		Weight within = new Weight(1.0F + WITHING_MARGIN);
		Weight over = new Weight(1.0F + (2 * WITHING_MARGIN));

		assertTrue("equals 0 to 0", ref.equals(eq));
		assertTrue("equals 0 to -1", ref.equals(within));
		assertFalse("equals 0 to +1", ref.equals(over));
	}

}
