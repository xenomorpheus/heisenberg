package au.net.hal9000.player.item;

import static org.junit.Assert.*;
import org.junit.Test;

public class HumanoidTest {
	private static final float WITHIN_MARGIN = 0.00009F;

	@Test
	public void testGetWeight() {
		Human human = new Human(); // Close enough for this abstract class
		human.getHead().setWeightBase(1F);
		human.getLeftHand().setWeightBase(2F);
		human.getRightHand().setWeightBase(4F);
		assertEquals("getWeight", human.getWeight(), 7F, WITHIN_MARGIN);
	}

	@Test
	public void testEquals() {
		Human human = new Human(); // Close enough for this abstract class
		Human other = new Human();
		assertTrue("equals basic", human.equals(other));

		// Alter head
		{
			float weightMax = human.getHead().getWeightMax();// Save old
			human.getHead().setWeightMax(weightMax + 0.1f);
			assertFalse("not equals. head differs", human.equals(other));
			human.getHead().setWeightMax(weightMax);
			assertTrue("equals basic", human.equals(other)); // Check restore
		}
		// Alter leftHand
		{
			float weightMax = human.getLeftHand().getWeightMax();// Save old
			human.getLeftHand().setWeightMax(weightMax + 0.1f);
			assertFalse("not equals. left hand differ", human.equals(other));
			human.getLeftHand().setWeightMax(weightMax);
			assertTrue("equals basic", human.equals(other)); // Check restore
		}

		// Alter rightHand
		{
			float weightMax = human.getRightHand().getWeightMax();// Save old
			human.getRightHand().setWeightMax(weightMax + 0.1f);
			assertFalse("not equals. right hand differ", human.equals(other));
			human.getRightHand().setWeightMax(weightMax); // Restore old
			assertTrue("equals basic", human.equals(other)); // Check restore
		}

	}
	// TODO clone
	// TODO persistence

}
