package au.net.hal9000.player.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.player.units.Weight;

public class HumanoidTest {

	@Test
	public void testGetWeight() {
		Human human = new Human(); // Close enough for this abstract class
		human.getHead().setWeightBase(1F);
		human.getLeftHand().setWeightBase(2F);
		human.getRightHand().setWeightBase(4F);
		assertTrue("getWeight", human.getWeight().equals(7F));
	}

	@Test
	public void testEquals() {
		Human human = new Human(); // Close enough for this abstract class
		Human other = new Human();
		assertTrue("equals basic", human.equals(other));

		// Alter head
		{
			HumanoidHead headOld = human.getHead(); // Save old
			Weight weightMaxNew = headOld.getWeightMax();
			HumanoidHead headNew = new HumanoidHead();
			if (weightMaxNew == null){
				weightMaxNew = new Weight();
				headNew.setWeightMax(weightMaxNew);
			}
			weightMaxNew.add(0.1f);
			human.setHead(headNew);
			assertFalse("not equals. head differs", human.equals(other));
			human.setHead(headOld); // Restore old
			assertTrue("equals basic", human.equals(other)); // Check restore
																// old
		}
		// Alter leftHand
		{
			Hand leftHandOld = human.getLeftHand(); // Save old
			Weight weightMaxNew = leftHandOld.getWeightMax();
			Hand leftHandNew = new Hand();
			if (weightMaxNew == null){
				weightMaxNew = new Weight();
				leftHandNew.setWeightMax(weightMaxNew);
			}
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
			if (weightMaxNew == null){
				weightMaxNew = new Weight();
				rightHandNew.setWeightMax(weightMaxNew);
			}
			weightMaxNew.add(0.1f);
			human.setRightHand(rightHandNew);
			assertFalse("not equals. right hand differ", human.equals(other));
			human.setRightHand(rightHandOld); // Restore old
			assertTrue("equals basic", human.equals(other)); // Check restore
																// old
		}

	}

}
