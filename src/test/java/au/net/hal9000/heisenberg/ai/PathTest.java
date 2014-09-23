package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class PathTest{

    /**
     * 
     */
    @Test
    public void testEquals() {
        Path p1 = new Path();
        Path p2 = new Path();

        Action action1 = new ActionV1("label", new Point3d(0, 0, 0));
        Action action2 = new ActionV1("label", new Point3d(0, 0, 0));
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
        Path expectedPath1 = new Path();
        expectedPath1
                .add(new ActionV1(null, new Point3d(0.6, 0.8, 0)));
        expectedPath1
                .add(new ActionV1(null, new Point3d(0.6, 0.8, 0)));

        Path expectedPath2 = new Path();
        expectedPath2
                .add(new ActionV1(null, new Point3d(0.6, 0.8, 0)));
        expectedPath2
                .add(new ActionV1(null, new Point3d(0.6, 0.8, 0)));
        assertTrue(expectedPath1.equals(expectedPath2));
    }

}
