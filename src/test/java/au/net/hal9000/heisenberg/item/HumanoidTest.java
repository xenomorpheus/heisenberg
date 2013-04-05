package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;
import au.net.hal9000.heisenberg.item.Human;

public class HumanoidTest {
    private static final float WITHIN_MARGIN = 0.00009F;

    /*
     * Most tests will use a Human as a representative of Humanoid.
     */
    @Test
    public void testGetWeight() {
        Human human = new Human(); // Close enough for this abstract class
        human.getHead().setWeightBase(1F);
        human.getLeftHand().setWeightBase(2F);
        human.getRightHand().setWeightBase(4F);
        assertEquals("getWeight", human.getWeight(), 7F, WITHIN_MARGIN);
    }

    @Test
    public void testGetIndexOfChild() {
        Human human = new Human(); // Close enough for this abstract class

        assertEquals("getIndexOfChild head", 0,
                human.getIndexOfChild(human.getHead()));
        assertEquals("getIndexOfChild left hand", 1,
                human.getIndexOfChild(human.getLeftHand()));
        assertEquals("getIndexOfChild right hand", 2,
                human.getIndexOfChild(human.getRightHand()));

        Scabbard scabbard = new Scabbard();
        assertEquals("getIndexOfChild scabbard before add", -1,
                human.getIndexOfChild(scabbard));
        human.add(scabbard);
        assertEquals("getIndexOfChild scabbard before add", 3,
                human.getIndexOfChild(scabbard));

    }

    @Test
    public void testGetRace() {
        Human human = new Human();
        assertEquals("getRace","Human", human.getRace());
    }
    
    
}
