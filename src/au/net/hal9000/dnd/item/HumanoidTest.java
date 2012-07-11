package au.net.hal9000.dnd.item;

import static org.junit.Assert.*;
import org.junit.Test;

public class HumanoidTest {

	@Test
	public void testGetWeight() {
		Human human = new Human(); // Close enough for this abstract class
		human.getHead().setWeightBase(1F);
		human.getLeftHand().setWeightBase(2F);
		human.getRightHand().setWeightBase(4F);
		assertTrue("getWeight", human.getWeight().equals(7F));
	}

}
