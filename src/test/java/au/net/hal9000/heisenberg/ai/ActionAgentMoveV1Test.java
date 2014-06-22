package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 */
public class ActionAgentMoveV1Test {

    /**
     * test equals.
     */
    @Test
    public void testEquals() {
        // compare both label and xyz.
        Action action1 = new ActionAgentMoveV1("label", new Point3d(0, 0, 0));
        Action action2 = new ActionAgentMoveV1("label", new Point3d(0, 0, 0));
        assertTrue(action1.equals(action2));

        // different label.
        Action action3 = new ActionAgentMoveV1("label2", new Point3d(0, 0, 0));
        assertFalse(action1.equals(action3));
    
        // no label.
        Action action4 = new ActionAgentMoveV1(null, new Point3d(0, 0, 0));
        assertFalse(action1.equals(action4));
    }

    
}
