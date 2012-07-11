package au.net.hal9000.dnd.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTest {

	@Test
	public void testEquals() {
		Location ref = new Location("World");
		Location other = new Location("World");

		assertTrue("equals", ref.equals(other));
	}
}
