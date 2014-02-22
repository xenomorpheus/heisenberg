package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 */
public class ActionAgentMoveV1Test {

    /**
     * Method testEquals.
     */
    @Test
    public void testEquals() {
        Action action1 = new ActionAgentMoveV1("label", new Point3d(0, 0, 0));
        Action action2 = new ActionAgentMoveV1("label", new Point3d(0, 0, 0));
        assertTrue(action1.equals(action2));
    }

}
