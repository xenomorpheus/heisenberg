package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.item.property.*;

public class ScenarioSet02 {

	/**
	 * Example of making an existing sword Magical
	 */
	@Test
	public void makeMagical() {
		Sword sword = new Sword();
		assertTrue("is sword", sword instanceof Sword);
		assertTrue("is sharp", sword instanceof Sharp);
		assertFalse("is magical", ItemProperty.isMagical(sword));
		// Add code to make magical
		ItemProperty.setMagical(sword, true);
		// Test the results.
		assertTrue("is sword", sword instanceof Sword);
		assertTrue("is sharp", sword instanceof Sharp);
		assertTrue("is magical", ItemProperty.isMagical(sword));
	}


}
