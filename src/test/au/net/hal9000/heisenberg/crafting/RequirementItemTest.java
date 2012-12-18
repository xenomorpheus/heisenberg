package test.au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.RequirementItem;
import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Cookie;

public class RequirementItemTest {

	
	@Test
	public void testGetDescription() {
//		fail("Not yet implemented");
	}

	@Test
	public void testIsConsumed() {
//		fail("Not yet implemented");
	}

	@Test
	public void testMeetsRequirements() {
		
		// The requirement
		RequirementItem requirementItem = new RequirementItem(new Cookie());
        assertTrue("meetsRequirements",requirementItem.meetsRequirements(new Cookie()));
        assertFalse("meetsRequirements",requirementItem.meetsRequirements(new Arrow()));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMeetsRequirementsMinWeight() {
		// The requirement
		RequirementItem requirementItem = new RequirementItem(new Cookie());
		requirementItem.setWeightMin(3);


		// Correct type and weight
		Cookie cookie = new Cookie();
		cookie.setWeightBase(3);

		// Correct type, too lite.
		Cookie cookieLite = new Cookie();
		cookieLite.setWeightBase(2.95f);

		// Correct weight, wrong type
		Arrow arrow = new Arrow();
		arrow.setWeightBase(3);
		
        // Tests
		assertTrue("correct type, just meets weight",requirementItem.meetsRequirements(cookie));
        assertFalse("incorrect type, meets weight",requirementItem.meetsRequirements(arrow));
		assertFalse("correct type, too lite",requirementItem.meetsRequirements(cookieLite));
	}
	

	
	@Test
	public void testToString() {
		Cookie cookie = new Cookie();
		RequirementItem requirementItem = new RequirementItem(cookie);
        assertEquals("toString","Item: Cookie",requirementItem.toString());
	}
	
}
