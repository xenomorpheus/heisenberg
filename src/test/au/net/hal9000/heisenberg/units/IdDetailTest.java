package test.au.net.hal9000.heisenberg.units;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.units.KeywordDetail;

public class IdDetailTest {

	@Test
	public void testIdWithDescriptionStringString() {
		try {
			KeywordDetail iwd = new KeywordDetail("theId",
					"the description");
			assertEquals("id", "theId", iwd.getId());
			assertEquals("description", "the description", iwd.getDescription());
		} catch (Exception e) {
			fail(e.toString());
		}

	}
}
