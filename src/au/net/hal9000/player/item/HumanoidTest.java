package au.net.hal9000.player.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.player.units.Weight;

public class HumanoidTest {
	private static final float WITHIN_MARGIN = 0.00009F;


	@Test
	public void testGetWeight() {
		Human human = new Human(); // Close enough for this abstract class
		human.getHead().setWeightBase(1F);
		human.getLeftHand().setWeightBase(2F);
		human.getRightHand().setWeightBase(4F);
		assertEquals("getWeight", human.getWeight(),7F, WITHIN_MARGIN);
	}

	@Test
	public void testEquals() {
		Human human = new Human(); // Close enough for this abstract class
		Human other = new Human();
		assertTrue("equals basic", human.equals(other));

		// Alter head
		{
			HumanoidHead headOld = human.getHead(); // Save old
			float weightMaxNew = headOld.getWeightMax();
			HumanoidHead headNew = new HumanoidHead();
			weightMaxNew += 0.1f;
			headNew.setWeightMax(weightMaxNew);
			human.setHead(headNew);
			assertFalse("not equals. head differs", human.equals(other));
			human.setHead(headOld); // Restore old
			assertTrue("equals basic", human.equals(other)); // Check restore
			headNew.setWeightMax(weightMaxNew);
																// old
		}
		// Alter leftHand
		{
			Hand leftHandOld = human.getLeftHand(); // Save old
			float weightMaxNew = leftHandOld.getWeightMax();
			Hand leftHandNew = new Hand();
			weightMaxNew.add(0.1f);
			human.setLeftHand(leftHandNew);
			assertFalse("not equals. left hand differ", human.equals(other));
			human.setLeftHand(leftHandOld); // Restore old
			assertTrue("equals basic", human.equals(other)); // Check restore
																// old
		}

		// Alter rightHand
		{
			Hand rightHandOld = human.getRightHand(); // Save old
			Weight weightMaxNew = rightHandOld.getWeightMax();
			Hand rightHandNew = new Hand();
			weightMaxNew.add(0.1f);
			human.setRightHand(rightHandNew);
			assertFalse("not equals. right hand differ", human.equals(other));
			human.setRightHand(rightHandOld); // Restore old
			assertTrue("equals basic", human.equals(other)); // Check restore
																// old
		}

	}
	// TODO clone
	// TODO persistence

}
