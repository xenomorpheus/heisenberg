package au.net.hal9000.heisenberg.pccreator;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.itemcreator.ItemCreator;

public class ItemCreatorTest {

	@Test
	public void testGetWorld() {
		/*
		 * This unit test is here so:<br>
		 * 1) We know the test data will build before running the UI.
		 * 2) Code coverage.
		 */
		try {
		  ItemCreator.getWorld();
		}
		catch (Exception e){
			fail(e.toString());
		}
	}

}
