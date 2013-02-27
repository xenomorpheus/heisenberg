package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.HumanoidHead;

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
    public void testClone() {
        Human human = new Human(); // Close enough for this abstract class
        Human clone = null; // TODO replace with clone

        try {
            clone = (Human) human.clone();
        } catch (CloneNotSupportedException e) {
            fail(e.toString());
        }

        assertFalse("clones have different ID",
                human.getId().equals(clone.getId()));
        assertFalse("clones have different ID", human.equals(clone));

        // Ensure the clone is a deep copy
        // Alter head
        {
            HumanoidHead head = human.getHead();
            HumanoidHead otherHead = clone.getHead();
            float weightBase = head.getWeightBase();// Save old
            assertEquals("getWeight", head.getWeight(), otherHead.getWeight(),
                    WITHIN_MARGIN);
            head.setWeightBase(weightBase + 0.1f);
            // TODO assertNotEquals("getWeight", head.getWeight(),
            // otherHead.getWeight(), WITHIN_MARGIN);

        }
        // Alter leftHand
        {
            // TODO Alter leftHand
        }

        // Alter rightHand
        {
            // TODO Alter rightHand
        }

    }
    // TODO persistence

}
