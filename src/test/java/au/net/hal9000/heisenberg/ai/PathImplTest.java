package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.units.Position;

/**
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class PathImplTest {

    /**
     * 
     */
    @Test
    public void testEquals() {
        PathImpl p1 = new PathImpl();
        PathImpl p2 = new PathImpl();

        Action action1 = new ActionMoveImpl("label", new Position(0, 0));
        Action action2 = new ActionMoveImpl("label", new Position(0, 0));
        assertTrue(action1.equals(action2));
        p2.add(action2);
        p1.add(action1);
        assertTrue(p1.equals(p2));

    }

    /**
     * test.
     */
    @Test
    public void testEquals2() {
        PathImpl expectedPath1 = new PathImpl();
        expectedPath1.add(new ActionMoveImpl(null, new Position(0.6, 0.8)));
        expectedPath1.add(new ActionMoveImpl(null, new Position(0.6, 0.8)));

        PathImpl expectedPath2 = new PathImpl();
        expectedPath2.add(new ActionMoveImpl(null, new Position(0.6, 0.8)));
        expectedPath2.add(new ActionMoveImpl(null, new Position(0.6, 0.8)));
        assertTrue(expectedPath1.equals(expectedPath2));
    }

    /**
     * Test clone.
     * 
     * @throws CloneNotSupportedException
     * 
     */
    @Test
    public void testClone() throws CloneNotSupportedException {
        PathImpl path = new PathImpl();

        path.add(new ActionMoveImpl("label", new Position(1, 0)));
        path.add(new ActionMoveImpl("label", new Position(2, 0)));
        PathImpl clone = path.duplicate();
        assertTrue(path.equals(clone));
        assertTrue(clone.equals(path));
        path.add(new ActionMoveImpl("label", new Position(3, 0)));
        assertFalse(path.equals(clone));
        assertFalse(clone.equals(path));
    }

}
