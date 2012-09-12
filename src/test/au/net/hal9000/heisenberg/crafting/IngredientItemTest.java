package test.au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.IngredientItem;
import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Cookie;

public class IngredientItemTest {

	
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
		IngredientItem ingredientItem = new IngredientItem(new Cookie());
        assertTrue("meetsRequirements",ingredientItem.meetsRequirements(new Cookie()));
        assertFalse("meetsRequirements",ingredientItem.meetsRequirements(new Arrow()));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMeetsRequirementsMinWeight() {
		// The requirement
		IngredientItem ingredientItem = new IngredientItem(new Cookie());
		ingredientItem.setWeightMin(3);


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
		assertTrue("correct type, just meets weight",ingredientItem.meetsRequirements(cookie));
        assertFalse("incorrect type, meets weight",ingredientItem.meetsRequirements(arrow));
		assertFalse("correct type, too lite",ingredientItem.meetsRequirements(cookieLite));
	}
	

	
	@Test
	public void testToString() {
		Cookie cookie = new Cookie();
		IngredientItem ingredientItem = new IngredientItem(cookie);
        assertEquals("toString","Item: Cookie: Cookie",ingredientItem.toString());
	}
	
}
