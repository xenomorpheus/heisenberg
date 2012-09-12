package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Human;

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
			float weightBase = human.getHead().getWeightBase();// Save old
			human.getHead().setWeightBase(weightBase + 0.1f);
			assertFalse("not equals. head differs", human.equals(other));
			human.getHead().setWeightBase(weightBase);
			assertTrue("equals basic", human.equals(other)); // Check restore
		}
		// Alter leftHand
		{
			float weightBase = human.getLeftHand().getWeightBase();// Save old
			human.getLeftHand().setWeightBase(weightBase + 0.1f);
			assertFalse("not equals. left hand differ", human.equals(other));
			human.getLeftHand().setWeightBase(weightBase);
			assertTrue("equals basic", human.equals(other)); // Check restore
		}

		// Alter rightHand
		{
			float weightBase = human.getRightHand().getWeightBase();// Save old
			human.getRightHand().setWeightBase(weightBase + 0.1f);
			assertFalse("not equals. right hand differ", human.equals(other));
			human.getRightHand().setWeightBase(weightBase); // Restore old
			assertTrue("equals basic", human.equals(other)); // Check restore
		}

	}
	// TODO clone
	// TODO persistence

}
