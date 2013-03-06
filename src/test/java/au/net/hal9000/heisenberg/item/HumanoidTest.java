package au.net.hal9000.heisenberg.item;

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

}
